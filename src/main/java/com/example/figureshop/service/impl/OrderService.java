package com.example.figureshop.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.figureshop.dto.request.CartItemDtoRequest;
import com.example.figureshop.dto.request.CheckoutDtoRequest;
import com.example.figureshop.dto.request.ShippingDtoRequest;
import com.example.figureshop.dto.response.OrderDtoResponse;
import com.example.figureshop.entity.Order;
import com.example.figureshop.entity.OrderDetail;
import com.example.figureshop.entity.Product;
import com.example.figureshop.entity.Shipping;
import com.example.figureshop.exception.NotFoundException;
import com.example.figureshop.mapper.OrderMapper;
import com.example.figureshop.mapper.ShippingMapper;
import com.example.figureshop.repository.CartRepository;
import com.example.figureshop.repository.OrderDetailRepository;
import com.example.figureshop.repository.OrderRepository;
import com.example.figureshop.repository.ProductRepository;
import com.example.figureshop.repository.ShippingRepository;
import com.example.figureshop.service.IOrderService;
import com.example.figureshop.service.model.ProductWithQuantity;
import com.example.figureshop.util.VnPayHelper;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Service
public class OrderService implements IOrderService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private VnPayHelper vnPayHelper;

	@Override
	@Transactional
	public OrderDtoResponse paymentItem(Long userId, CheckoutDtoRequest checkoutDtoRequest) {
		boolean isSame = true;
		List<CartItemDtoRequest> items = checkoutDtoRequest.getItems();
		Long shippingId = checkoutDtoRequest.getShippingId();
		List<Order> oldPendingOrders = orderRepository.findByUserIdAndOrderStatus(userId, "Pending");
		LocalDateTime now = LocalDateTime.now();

		for (Order pending : oldPendingOrders) {
		    if (pending.getCreatedAt() != null && Duration.between(pending.getCreatedAt(), now).toMinutes() > 15) {
		        pending.setOrderStatus("Cancelled");
		        orderRepository.save(pending);
		    }
		}
		Optional<Order> pendingOrderOption = orderRepository.findTopByUserIdAndOrderStatusOrderByCreatedAtDesc(userId, "Pending");

		if (pendingOrderOption.isPresent()) {
			Order orderExist = pendingOrderOption.get();
			List<OrderDetail> existDetails = orderDetailRepository.findByOrder_OrderId(orderExist.getOrderId());

			Map<Long, Integer> cartMap = items.stream()
					.collect(Collectors.toMap(CartItemDtoRequest::getProductId, CartItemDtoRequest::getQuantity));

			for (OrderDetail detail : existDetails) {
				Integer cartQty = cartMap.get(detail.getProductId());
				if (cartQty == null || !cartQty.equals(detail.getProductSalesQuantity())) {
					isSame = false;
					break;
				}
			}
			
			if (shippingId == orderExist.getShippingId() && isSame && cartMap.size() == existDetails.size()) {
	            return OrderMapper.toDto(orderExist);
	        }
		}

		Order order = new Order();
		int totalPrice = 0;

		List<ProductWithQuantity> products = new ArrayList<>();

		for (CartItemDtoRequest item : items) {
			Product product = productRepository.findById(item.getProductId())
					.orElseThrow(() -> new NotFoundException("Product not found: " + item.getProductId()));
			totalPrice += product.getProductPrice() * item.getQuantity();
			products.add(new ProductWithQuantity(product, item.getQuantity()));

		}

		order.setUserId(userId);
		order.setShippingId(shippingId);
		order.setPaymentId(1L);
		order.setOrderTotal(String.valueOf(totalPrice));
		order.setOrderStatus("Pending");
		orderRepository.save(order);
		Long orderId = order.getOrderId();
		for (ProductWithQuantity product : products) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrder(order);
			orderDetail.setProductId(product.getProduct().getProductId());
			orderDetail.setProductSalesQuantity(product.getQuantity());
			orderDetailRepository.save(orderDetail);
		}
		return OrderMapper.toDto(order);
	}

	@Override
	public OrderDtoResponse getInfoByOrderId(Long orderId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new RuntimeException("Order ID not found: " + orderId));
		return OrderMapper.toDto(order);
	}

	@Override
	public Map<String, String> getParamVnPay(HttpServletRequest request) {
		return vnPayHelper.getParamVnPay(request);
	}

	@Override
	@Transactional
	public OrderDtoResponse updateOrderStatus(Long orderId) {
		orderRepository.updateOrderStatus(orderId, "PAID");
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("OrderId not found"));
		return OrderMapper.toDto(order);
	}

}
