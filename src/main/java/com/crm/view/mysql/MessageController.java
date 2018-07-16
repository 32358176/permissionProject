package com.crm.view.mysql;


import com.crm.service.rabbit.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private Producer producer;

    @GetMapping("/producer")
    public String messageProducer(String mess) {
        producer.setMessageForQueue("mq_exchange", "mq_url", mess);
        return "发送成功";
    }


}
