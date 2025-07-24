package com.example.figureshop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	@Override
	@Transactional
	public OrderDtoResponse paymentItem(Long userId, CheckoutDtoRequest checkoutDtoRequest) {
		Order order = new Order();
		int totalPrice = 0;
		
		List<CartItemDtoRequest> items = checkoutDtoRequest.getItems();
		List<ProductWithQuantity> products = new ArrayList<>();
		for(CartItemDtoRequest item : items ) {
			Product product = productRepository.findById(item.getProductId())
			        .orElseThrow(() -> new NotFoundException("Product not found: " + item.getProductId()));			    			   
			totalPrice += product.getProductPrice() * item.getQuantity();
			products.add(new ProductWithQuantity(product, item.getQuantity()));
		}	
		Long shippingId = checkoutDtoRequest.getShippingId();
		order.setUserId(userId);
		order.setShippingId(shippingId);
		order.setPaymentId(1L);
		order.setOrderTotal(String.valueOf(totalPrice));
		order.setOrderStatus("Pending");
		orderRepository.save(order);
		Long orderId = order.getOrderId();
		for(ProductWithQuantity product : products) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrderId(orderId);
			orderDetail.setProductId(product.getProduct().getProductId());
			orderDetail.setProductSalesQuantity(product.getQuantity());
			orderDetailRepository.save(orderDetail);
		}
		cartRepository.deleteByUser_UserId(userId);
		return OrderMapper.toDto(order);
	}

	@Override
	public OrderDtoResponse getInfoByOrderId(Long orderId) {
	    Order order = orderRepository.findById(orderId)
	        .orElseThrow(() -> new RuntimeException("Order ID not found: " + orderId));
	    return OrderMapper.toDto(order);
	}


}
