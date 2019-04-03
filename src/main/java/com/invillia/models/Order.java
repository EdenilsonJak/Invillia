package com.invillia.models;

import java.io.Serializable;
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

import org.springframework.lang.NonNull;

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
	
	@NonNull
	private String endereco;

	@Column(name="dataorder")
	private Date dataOrder;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	@OneToOne(fetch=FetchType.EAGER)
	private Payment payment;
	
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

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
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
		return "Order [id=" + id + ", endereco=" + endereco + ", data=" + dataOrder + "]";
	}
	

}
