package com.example.websocket.ws;

import com.example.websocket.configuration.SpringContext;
import com.example.websocket.dto.Message;
import com.example.websocket.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@Slf4j
@Component
@ServerEndpoint(value = "/cars/{id}", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class CarWebSocket {

    private final MessageService messageService;

    public CarWebSocket() {
        this.messageService = SpringContext.getBean(MessageService.class);
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) {
        log.info("onOpen " + session.getId());
    }

    @OnMessage
    public void onMessage(Message message, Session session) {
        log.debug("Received message. Session ID: {} contains message: {} ", session.getId(), message.getData());
        messageService.proceedMessage(message, session);
    }

    @OnClose
    public void onClose(Session session) {
        log.info("onClose " + session.getId());
    }

    @OnError
    public void onError(Throwable t) {
        log.error(t.getMessage(), t);
    }
}