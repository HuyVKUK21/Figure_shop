package com.example.figureshop.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long productId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "brand_id")
	private BrandProduct brandProduct;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private CategoryProduct categoryProduct;

	@Column(name = "product_name")
	private String productName;
	@Column(name = "product_desc")
	private String productDesc;
	@Column(name = "product_series")
	private String productSeries;
	@Column(name = "product_size")
	private String productSize;
	@Column(name = "product_proportion")
	private String productProportion;
	@Column(name = "product_date")
	private String productDate;
	@Column(name = "productPrice")
	private int productPrice;
	@Column(name = "product_price_log")
	private String productPriceLog;
	@Column(name = "product_status")
	private int productStatus;
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	
	
	public Product() {
		super();
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	
	public BrandProduct getBrandProduct() {
		return brandProduct;
	}
	public void setBrandProduct(BrandProduct brandProduct) {
		this.brandProduct = brandProduct;
	}
	public CategoryProduct getCategoryProduct() {
		return categoryProduct;
	}
	public void setCategoryProduct(CategoryProduct categoryProduct) {
		this.categoryProduct = categoryProduct;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getProductSeries() {
		return productSeries;
	}
	public void setProductSeries(String productSeries) {
		this.productSeries = productSeries;
	}
	public String getProductSize() {
		return productSize;
	}
	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}
	public String getProductProportion() {
		return productProportion;
	}
	public void setProductProportion(String productProportion) {
		this.productProportion = productProportion;
	}
	public String getProductDate() {
		return productDate;
	}
	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductPriceLog() {
		return productPriceLog;
	}
	public void setProductPriceLog(String productPriceLog) {
		this.productPriceLog = productPriceLog;
	}
	public int getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(int productStatus) {
		this.productStatus = productStatus;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
	}
  
	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now(); 
	}
	
}
