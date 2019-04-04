package com.invillia.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.invillia.status.OrderStatus;

@Entity
@Table(name="pedido")
public class Order implements Serializable{

	/**
	 * Dev - Edenilson Mendonça
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String endereco;

	@Column(name="dataorder")
	private Date dataOrder;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderstatus;
	
	@OneToOne(fetch=FetchType.EAGER)
	private Payment payment;
	
	private Date momentreembolso;

	
	@OneToMany(mappedBy = "order", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<OrderItem> itens;
	
	public Order() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Date getDataOrder() {
		return dataOrder;
	}

	public void setDataOrder(Date dataOrder) {
		this.dataOrder = dataOrder;
	}

	public OrderStatus getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(OrderStatus orderstatus) {
		this.orderstatus = orderstatus;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	
	public List<OrderItem> getItens() {
		return itens;
	}

	public void setItens(List<OrderItem> itens) {
		this.itens = itens;
	}
	
	public Date getMomentreembolso() {
		return momentreembolso;
	}

	public void setMomentreembolso(Date momentreembolso) {
		this.momentreembolso = momentreembolso;
	}

	@Transient
	public void checkReembolso() {
		if(this.orderstatus.ACCOMPLISHED.equals(OrderStatus.ACCOMPLISHED)) {
			if(this.payment.getPaymentstatus().CONFIRMATION_PAYMENT.equals(OrderStatus.CONFIRMATION_PAYMENT)) {
				LocalDate instant = dataOrder.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				if(instant.lengthOfMonth() > 10) {
					System.out.println("Pedido não pode ser reembolsado");
				}
				else {
					System.out.println("Pedido pode ser reembolsado");
				}
			}
			
		}
	}
	
	@PrePersist
	public void prePersist() {
		this.dataOrder = new Date();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", endereco=" + endereco + ", dataOrder=" + dataOrder + ", orderstatus="
				+ orderstatus + ", payment=" + payment + "]";
	}

	
	

}
