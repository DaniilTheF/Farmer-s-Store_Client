package com.example.client.Interfaces;

import java.io.IOException;

public interface IConnection {
    public void sendMessage(String message);
    public void sendObject(Object object);
    public String readMessage()throws IOException;
    public Object readObject();
    public void close();
}
