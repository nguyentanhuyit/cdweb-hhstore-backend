package com.springboot.bookshop.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "product_detail")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProductDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "language")
	private String language;

	@Column(name = "format")
	private int format;

	@Column(name = "dimensions")
	private String dimensions;

	@Column(name = "country")
	private String country;

	@Column(name = "code")
	private String code;

	@Column(name = "detail_description")
	private String detailDescription;

	@Column(name = "date_release")
	private LocalDate dateRelease;

	@Column(name = "image_1")
	private String image1;

	@Column(name = "image_2")
	private String image2;

	@Column(name = "number_of_product")
	private int numberOfProduct;

	@Column(name = "purchase_number")
	private int purchaseNumber;

	@Column(name = "promotion")
	private int promotion;

	@JsonIdentityReference(alwaysAsId = true)
	@OneToOne(mappedBy = "productDetail", cascade = { CascadeType.ALL })
	private Product product;

	public ProductDetail() {

	}

	public ProductDetail(int id, String language, int format, String dimensions, String country, String code,
			String detailDescription, LocalDate dateRelease, String image1, String image2, int numberOfProduct,
			int purchaseNumber, int promotion, Product product) {
		this.id = id;
		this.language = language;
		this.format = format;
		this.dimensions = dimensions;
		this.country = country;
		this.code = code;
		this.detailDescription = detailDescription;
		this.dateRelease = dateRelease;
		this.image1 = image1;
		this.image2 = image2;
		this.numberOfProduct = numberOfProduct;
		this.purchaseNumber = purchaseNumber;
		this.promotion = promotion;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getFormat() {
		return format;
	}

	public void setFormat(int format) {
		this.format = format;
	}

	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDetailDescription() {
		return detailDescription;
	}

	public void setDetailDescription(String detailDescription) {
		this.detailDescription = detailDescription;
	}

	public LocalDate getDateRelease() {
		return dateRelease;
	}

	public void setDateRelease(LocalDate dateRelease) {
		this.dateRelease = dateRelease;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public int getNumberOfProduct() {
		return numberOfProduct;
	}

	public void setNumberOfProduct(int numberOfProduct) {
		this.numberOfProduct = numberOfProduct;
	}

	public int getPurchaseNumber() {
		return purchaseNumber;
	}

	public void setPurchaseNumber(int purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}

	public int getPromotion() {
		return promotion;
	}

	public void setPromotion(int promotion) {
		this.promotion = promotion;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "ProductDetail [id=" + id + ", language=" + language + ", format=" + format + ", dimensions="
				+ dimensions + ", country=" + country + ", code=" + code + ", detailDescription=" + detailDescription
				+ ", dateRelease=" + dateRelease + ", image1=" + image1 + ", image2=" + image2 + ", numberOfProduct="
				+ numberOfProduct + ", purchaseNumber=" + purchaseNumber + ", promotion=" + promotion + "]";
	}

	

}
