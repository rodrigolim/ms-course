package com.developer.hrpayroll.services;


import com.developer.hrpayroll.entities.Payment;
import com.developer.hrpayroll.entities.Worker;
import com.developer.hrpayroll.feignclients.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private WorkerFeignClient workerFeignClient;

    public Payment getPayment(long workerId, Integer days){
        Worker worker = workerFeignClient.findWorkerById(workerId).getBody();
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }

}
