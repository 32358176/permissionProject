package com.crm.service.rabbit;

import com.crm.view.mysql.SocketController;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author 消息消费者
 */
@Service("messageCustomer")
public class Customer implements MessageListener {

    @Override
    public void onMessage(Message message) {

        for (SocketController s : SocketController.controllers) {
            try {
                s.onSendMessage(new String(message.getBody(), "UTF-8"));
            } catch (IOException e) {
                continue;
            }
        }

    }

}
