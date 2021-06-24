package com.example.websocket.ws;

import com.example.websocket.dto.Message;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

@Slf4j
@RequiredArgsConstructor
public class MessageDecoder implements Decoder.Text<Message> {

    private static final Gson GSON = new Gson();

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public Message decode(String s) {
        try {
            return GSON.fromJson(s, Message.class);
        } catch (Exception e) {
            log.debug("Cannot decode message: {}", s);
        }
        return null;
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

}
