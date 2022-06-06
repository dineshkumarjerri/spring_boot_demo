package com.org.nace.manager.dtos;


public class NaceDataDto   {
	
	private Long id;
	private Integer orderId;
	private Integer level;
	private String code;
	private String parent;
	private String description;
	private String itemIncludes;
	private String itemAlsoIncludes;
	private String rulings;
	private String itemExcludes;
	private String isicReference;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderID(Integer orderId) {
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

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	

}
