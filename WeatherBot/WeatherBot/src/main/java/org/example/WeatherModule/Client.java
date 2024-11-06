package org.example.WeatherModule;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Класс {@code Client} представляет собой клиент для работы с HTTP-соединением.
 * Он позволяет устанавливать соединение с указанным URL, настраивать методы и заголовки запросов, а также получать ответы от сервера.
 */

/**
 * @class Client
 * @brief Класс клиента для установления соединения с сервером по HTTP
 *
 * Этот класс отправляет HTTP-запрос к API погоды, получает ответ, а затем парсит данные
 * для дальнейшего использования.
 */
public class Client {
    /**
     * URL, к которому будет подключаться клиент.
     */
    private URL ClientURL = null;

    /**
     * HTTP-соединение, используемое для выполнения запросов.
     */
    private HttpURLConnection ClientConnection;

    /**
     * Конструктор {@code Client} инициализирует объект с заданным URL.
     *
     * @param url Строка, представляющая URL для подключения.
     * @throws MalformedURLException если переданный URL имеет неправильный формат.
     */
    public Client(String url) throws MalformedURLException {
        ClientURL = new URL(url);
    }

    /**
     * Метод {@code Connect} устанавливает HTTP-соединение с указанным URL.
     * В случае ошибки при открытии соединения генерируется исключение {@code RuntimeException}.
     */
    public void Connect(){
        if(ClientURL != null) {
            try {
                ClientConnection = (HttpURLConnection) ClientURL.openConnection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Метод {@code SetMethod} устанавливает метод HTTP-запроса (например, GET, POST).
     *
     * @param method Метод HTTP-запроса, который будет использоваться (например, "GET" или "POST").
     * @throws RuntimeException если возникла ошибка при установке метода запроса.
     */
    public void SetMethod(String method) {
        try {
            ClientConnection.setRequestMethod(method);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод {@code SetProperties} устанавливает заголовок HTTP-запроса.
     *
     * @param key Имя заголовка.
     * @param value Значение заголовка.
     */
    public void SetProperties(String key, String value){
        ClientConnection.setRequestProperty(key, value);
    }

    /**
     * Метод {@code GetResponseCode} возвращает код ответа HTTP-сервера.
     *
     * @return Код ответа от сервера (например, 200 для успешного запроса).
     * @throws RuntimeException если возникла ошибка при получении кода ответа.
     */
    public int GetResponseCode(){
        try {
            return ClientConnection.getResponseCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод {@code GetInputStream} возвращает входной поток для чтения данных из ответа HTTP-сервера.
     *
     * @return Входной поток данных ответа от сервера.
     * @throws RuntimeException если возникла ошибка при получении входного потока.
     */
    public InputStream GetInputStream(){
        try {
            return ClientConnection.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод {@code setRequestProperty} устанавливает заголовок запроса для авторизации.
     *
     * @param authorization Имя заголовка (например, "Authorization").
     * @param yourApiKey Значение заголовка (например, API-ключ).
     */
    public void setRequestProperty(String authorization, String yourApiKey) {
        ClientConnection.setRequestProperty(authorization, yourApiKey);
    }
}
