package org.example.WeatherModule;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Response {
    private int ResponseCode; ///< Код ответа от сервиса
    private String ResponseData;

    public Response(int code, InputStream inputStream) throws Exception {
        if(validResponse(code)) readResponseData(inputStream);
        else throw new RuntimeException("Failed HTTPS connection");
    }

    public int getResponseCode() {
        return ResponseCode;
    }

    /**
     * @param code код ответа
     * @return true если код ответа 200
     */
    private boolean validResponse(int code){
        return code == HttpsURLConnection.HTTP_OK;
    }

    /**
     * @param inputStream поток из которого считываем данные
     * Потом перепишем, цикл считывания лучше убрать в отдельный метод
     */
    private void readResponseData(InputStream inputStream){
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while (true) {
            try {
                if ((inputLine = in.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            response.append(inputLine);
        }

        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ResponseData = response.toString();
    }

    public String getResponseData() {
        return ResponseData;
    }
}
