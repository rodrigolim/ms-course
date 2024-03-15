package com.developer.hrpayroll.resources;

import com.developer.hrpayroll.entities.Payment;
import com.developer.hrpayroll.services.PaymentService;
import com.developer.hrworker.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/payments")
public class PaymentResource {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/{workerId}/days/{days}")
    public ResponseEntity<Payment> getPayment(@PathVariable("workerId") Long workerId, @PathVariable("days") Integer days) {
        Payment payment = paymentService.getPayment(workerId, days);
        if (payment == null) {
            throw new ResourceNotFoundException("Payment not found");
        }
        return ResponseEntity.ok(payment);
    }

}
