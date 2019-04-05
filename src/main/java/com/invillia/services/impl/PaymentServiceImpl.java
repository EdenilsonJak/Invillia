package com.invillia.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invillia.models.Payment;
import com.invillia.repository.PaymentRepository;
import com.invillia.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public Optional<Payment> buscarPorId(Long id) {
		log.info("consulta com id: {}", id);
		return this.paymentRepository.findById(id);
	}

	@Override
	public Payment persistir(Payment payment) {
		log.info("cadastrar payment: {}", payment);
		return this.paymentRepository.save(payment);
	}

	@Override
	public Optional<Payment> buscarPorOrderId(Long id) {
		log.info("consulta payment por id Order: {}", id);
		return this.paymentRepository.findByOrderId(id);
	}

}
