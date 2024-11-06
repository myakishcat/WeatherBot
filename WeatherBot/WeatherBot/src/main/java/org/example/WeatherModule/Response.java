/**
 * @file Response.java
 * @brief Класс для обработки ответа от HTTPS сервера.
 *
 * Этот класс обрабатывает ответ от сервера, получая код ответа и данные, если код успешен.
 * Он позволяет проверить код ответа и извлечь данные из потока, если код ответа равен 200 (OK).
 */
package org.example.WeatherModule;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @class Response
 * @brief Класс для ответа, получаемого от сервера
 * Класс обрабатывает ответ, получая код ответа и данные, если код ответа успешен.
 */
public class Response {
    /** Код ответа от сервера. */
    private int ResponseCode;

    /** Данные ответа от сервера. */
    private String ResponseData;

    /**
     * @brief Конструктор класса, принимающий код ответа и поток данных.
     *
     * Если код ответа успешен (200), считывает данные из потока. Если код ответа не успешен,
     * генерирует исключение.
     *
     * @param code Код ответа от сервера.
     * @param inputStream Поток данных, полученных от сервера.
     * @throws Exception Если код ответа не успешен или ошибка при чтении данных.
     */
    public Response(int code, InputStream inputStream) throws Exception {
        if(validResponse(code))
            readResponseData(inputStream);
        else
            throw new RuntimeException("Failed HTTPS connection");
    }

    /**
     * @brief Получение кода ответа от сервера.
     *
     * Этот метод возвращает код ответа, полученный от сервера.
     *
     * @return Код ответа от сервера.
     */
    public int getResponseCode() {
        return ResponseCode;
    }

    /**
     * @brief Проверяет, является ли код ответа успешным.
     *
     * Этот метод проверяет, является ли код ответа равным 200, что означает успешное
     * выполнение запроса.
     *
     * @param code Код ответа от сервера.
     * @return true, если код ответа равен 200 (HTTP_OK), иначе false.
     */
    private boolean validResponse(int code){
        return code == HttpsURLConnection.HTTP_OK;
    }

    /**
     * @brief Считывает данные из входного потока.
     *
     * Этот метод считывает данные из потока в строку. Все строки данных добавляются в
     * объект StringBuilder, который затем конвертируется в строку и сохраняется в поле ResponseData.
     *
     * @param inputStream Поток, из которого считываются данные.
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

    /**
     * @brief Получение данных ответа.
     *
     * Этот метод возвращает строку, содержащую данные, полученные от сервера.
     *
     * @return Строка с данными ответа от сервера.
     */
    public String getResponseData() {
        return ResponseData;
    }
}
