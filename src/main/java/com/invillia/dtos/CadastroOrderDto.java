package com.invillia.dtos;

import java.util.Date;
import java.util.Optional;

import javax.validation.constraints.NotNull;

public class CadastroOrderDto {

	private Optional<Long> id = Optional.empty();
	private String endereco;
	private String dataOrder;
	private String orderStatus;
	private String momentreembolso;
	private Long paymentId;
	
	public CadastroOrderDto() {
	}

	public Optional<Long> getId() {
		return id;
	}

	public void setId(Optional<Long> id) {
		this.id = id;
	}

	@NotNull(message="Endereço não pode ser nulo")
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDataOrder() {
		return dataOrder;
	}

	public void setDataOrder(String dataOrder) {
		this.dataOrder = dataOrder;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public String getMomentreembolso() {
		return momentreembolso;
	}

	public void setMomentreembolso(String momentreembolso) {
		this.momentreembolso = momentreembolso;
	}

	@Override
	public String toString() {
		return "CadastroOrderDto [id=" + id + ", endereco=" + endereco + ", dataOrder=" + dataOrder + ", orderStatus="
				+ orderStatus + "]";
	}
	
	
}
