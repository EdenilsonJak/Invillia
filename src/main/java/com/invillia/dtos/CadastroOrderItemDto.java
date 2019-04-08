package com.invillia.dtos;

import java.util.Optional;

import javax.validation.constraints.NotNull;

public class CadastroOrderItemDto {

	private Optional<Long> id = Optional.empty();
	private String description;
	private String unitValue;
	private String qtd;
	private Long orderId;

	public CadastroOrderItemDto() {
	}

	public Optional<Long> getId() {
		return id;
	}

	public void setId(Optional<Long> id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnitValue() {
		return unitValue;
	}

	public void setUnitValue(String unitValue) {
		this.unitValue = unitValue;
	}

	public String getQtd() {
		return qtd;
	}

	public void setQtd(String qtd) {
		this.qtd = qtd;
	}

	@NotNull(message="Código Order é obrigatório")
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "CadastroOrderItemDto [id=" + id + ", description=" + description + ", unitValue=" + unitValue + ", qtd="
				+ qtd + ", orderId=" + orderId + "]";
	}
	
	

}