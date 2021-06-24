package com.example.websocket.ws;

import com.example.websocket.dto.Message;
import com.example.websocket.dto.response.CarResponse;
import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<CarResponse> {

    private final static Gson GSON = new Gson();

    @Override
    public String encode(CarResponse message) throws EncodeException {
        return GSON.toJson(message);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
