package com.crm.view;


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
    public String messageProducer(){
        producer.setMessageForQueue("mq_exchange","mq_url","中国");
        return "发送成功";
    }


}
