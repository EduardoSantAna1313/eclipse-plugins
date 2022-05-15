package com.edu.postman.service.postman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Class to PostmanConnection.
 *
 * @author Eduardo
 */
class PostmanConnection {

    /**
     * String - BASE_URL.
     */
    private static final String BASE_URL = "https://api.getpostman.com/";

    /**
     * String - HEADER_X_API_KEY.
     */
    private static final String HEADER_X_API_KEY = "X-Api-Key";

    /**
     * String - METHOD_GET.
     */
    private static final String METHOD_GET = "GET";

    /**
     * String - METHOD_PUT.
     */
    private static final String METHOD_PUT = "PUT";

    /**
     * String - METHOD_POST.
     */
    private static final String METHOD_POST = "POST";

    /**
     * String - METHOD_DELETE.
     */
    private static final String METHOD_DELETE = "DELETE";

    /**
     * New instance of PostmanConnection
     */
    private PostmanConnection() {
        // NA
    }

    static HttpURLConnection createGetConnection(final String pSuffix, final String key) throws IOException {
        final HttpURLConnection conn = createConnection(pSuffix, key);
        conn.setRequestMethod(METHOD_GET);
        conn.setDoInput(true);
        return conn;
    }

    static HttpURLConnection createConnection(final String pSuffix, final String key) throws IOException {
        final URL url = new URL(BASE_URL + (pSuffix != null ? pSuffix : ""));

        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty(HEADER_X_API_KEY, key);
        return conn;
    }

    static String get(final String pSuffix, final String key) throws IOException {

        final HttpURLConnection conn = createGetConnection(pSuffix, key);

        return readResponse(conn);

    }

    public static InputStream getStream(final String pSuffix, final String key) throws IOException {

        final HttpURLConnection conn = createGetConnection(pSuffix, key);

        return conn.getInputStream();

    }

    static String post(final String suffix, final String key, final String content) throws IOException {
        final HttpURLConnection conn = createConnection(suffix, key);
        conn.setRequestMethod(METHOD_POST);
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");

        try (final OutputStream outputStream = conn.getOutputStream();) {
            outputStream.write(content.getBytes());
            outputStream.flush();
        }

        return readResponse(conn);
    }

    static String put(final String suffix, final String key, final String content) throws IOException {
        final HttpURLConnection conn = createConnection(suffix, key);
        conn.setRequestMethod(METHOD_PUT);
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");

        try (final OutputStream outputStream = conn.getOutputStream();) {
            outputStream.write(content.getBytes());
            outputStream.flush();
        }

        return readResponse(conn);
    }

    public static String delete(final String suffix, final String key) throws IOException {
        final HttpURLConnection conn = createConnection(suffix, key);
        conn.setRequestMethod(METHOD_DELETE);
        conn.setDoOutput(true);

        return readResponse(conn);
    }

    private static String readResponse(final HttpURLConnection conn) throws IOException {

        try (final BufferedReader buffer = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            final StringBuilder response = new StringBuilder();
            String responseLine = null;

            while ((responseLine = buffer.readLine()) != null) {
                response.append(responseLine.trim());
            }

            return response.toString();
        }

    }

}
