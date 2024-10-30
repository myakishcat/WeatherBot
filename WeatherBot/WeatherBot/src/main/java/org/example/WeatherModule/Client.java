package org.example.WeatherModule;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Client {
    private URL ClientURL = null;
    private HttpURLConnection ClientConnection;

    public Client(String url) throws MalformedURLException {
        ClientURL = new URL(url);
    }

    public void Connect(){
        if(ClientURL != null) {
            try {
                ClientConnection = (HttpURLConnection) ClientURL.openConnection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void SetMethod(String method) {
        try {
            ClientConnection.setRequestMethod(method);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        }
    }

    public void SetProperties(String key, String value){
        ClientConnection.setRequestProperty(key, value);
    }

    public int GetResponseCode(){
        try {
            return ClientConnection.getResponseCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public InputStream GetInputStream(){
        try {
            return ClientConnection.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setRequestProperty(String authorization, String yourApiKey) {
        ClientConnection.setRequestProperty(authorization, yourApiKey);
    }
}
