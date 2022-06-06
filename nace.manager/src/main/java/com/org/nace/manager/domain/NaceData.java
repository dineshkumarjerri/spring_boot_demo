package com.org.nace.manager.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "nace_data")
public class NaceData  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
	 @SequenceGenerator(name = "seqGen", sequenceName = "seq", initialValue = 1)
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	

	@Column(name="order_Id", unique=true, nullable = false )
	private Integer orderId;
	
	@Column(name="level", nullable = false)
	private Integer level;
	
	@Column(name="code", nullable = false)
	private String code;
	
	@Column(name="parent")
	private String parent;
	
	@Column(name="description")
	private String description;
	
	@Column(name="item_Includes")
	private String itemIncludes;
	
	@Column(name="item_Also_Includes")
	private String itemAlsoIncludes;
	
	@Column(name="rulings")
	private String rulings;
	
	@Column(name="item_Excludes")
	private String itemExcludes;
	
	@Column(name="isic_Reference")
	private String 	isicReference;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getItemIncludes() {
		return itemIncludes;
	}

	public void setItemIncludes(String itemIncludes) {
		this.itemIncludes = itemIncludes;
	}

	public String getItemAlsoIncludes() {
		return itemAlsoIncludes;
	}

	public void setItemAlsoIncludes(String itemAlsoIncludes) {
		this.itemAlsoIncludes = itemAlsoIncludes;
	}

	public String getRulings() {
		return rulings;
	}

	public void setRulings(String rulings) {
		this.rulings = rulings;
	}

	public String getItemExcludes() {
		return itemExcludes;
	}

	public void setItemExcludes(String itemExcludes) {
		this.itemExcludes = itemExcludes;
	}

	public String getIsicReference() {
		return isicReference;
	}

	public void setIsicReference(String isicReference) {
		this.isicReference = isicReference;
	}
}
