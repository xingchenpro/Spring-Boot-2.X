package com.javahly.producerservice.producer;

import com.javahly.producerservice.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
    @Autowired
    private ProducerService producerService;

    @RequestMapping("/sendOrder")
    public String sendOrder() {
        return producerService.saveOrder();
    }
}
