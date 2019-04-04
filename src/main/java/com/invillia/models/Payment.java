package com.invillia.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.invillia.status.OrderStatus;

@Entity
@Table(name="payment")
public class Payment implements Serializable {

	/**
	 * Dev - EDENILSON
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus paymentstatus;
	
	private Date momentpgto;
	
	private Long numCredito;
	
	@OneToOne(fetch=FetchType.EAGER)
	private Order order;
	
	public Payment() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrderStatus getPaymentstatus() {
		return paymentstatus;
	}

	public void setPaymentstatus(OrderStatus paymentstatus) {
		this.paymentstatus = paymentstatus;
	}

	public Date getMomentpgto() {
		return momentpgto;
	}

	public void setMomentpgto(Date momentpgto) {
		this.momentpgto = momentpgto;
	}

	public Long getNumCredito() {
		return numCredito;
	}

	public void setNumCredito(Long numCredito) {
		this.numCredito = numCredito;
	}
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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
		Payment other = (Payment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", orderStatus=" + paymentstatus + ", momentpgto=" + momentpgto + ", numCredito="
				+ numCredito + "]";
	}


}
