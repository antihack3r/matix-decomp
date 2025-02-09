// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.web;

import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.io.DataOutputStream;
import java.util.function.BiConsumer;
import java.util.UUID;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.net.URL;
import java.lang.reflect.Type;
import com.google.gson.Gson;

public class JsonFetcher
{
    private static final Gson gson;
    
    @Deprecated
    public static <T> T fetchData(final String endPoint, final Type type) {
        try {
            final Scanner scanner = new Scanner(new InputStreamReader(new URL(endPoint).openStream()));
            final StringBuilder stringBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine());
            }
            scanner.close();
            return (T)JsonFetcher.gson.fromJson(stringBuilder.toString(), type);
        }
        catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Deprecated
    public static <T> T fetchData(final String endPoint, final Class<T> type) {
        return fetchData(endPoint, (Type)type);
    }
    
    public static <T> T get(final String endPoint, final Class<T> type) {
        return request(endPoint, "GET", "", type, new HashMap<String, String>());
    }
    
    public static <T> T post(final String endPoint, final String body, final Class<T> type) {
        return request(endPoint, "POST", body, type, new HashMap<String, String>());
    }
    
    public static <T> T post(final String endPoint, final String body, final Class<T> type, final HashMap<String, String> requestProperties) {
        return request(endPoint, "POST", body, type, requestProperties);
    }
    
    public static <T> T post(final String endPoint, final HashMap<String, String> body, final Class<T> type, final HashMap<String, String> requestProperties) {
        return request(endPoint, "POST", JsonFetcher.gson.toJson((Object)body), type, requestProperties);
    }
    
    private static <T> T request(final String endPoint, final String method, final String body, final Type type, final HashMap<String, String> properties) {
        final StringBuilder stringBuilder = new StringBuilder();
        try {
            final URL url = new URL(endPoint);
            final HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod(method);
            urlConnection.setRequestProperty("Accept", "application/json,text/plain,text/html");
            urlConnection.setRequestProperty("User-Agent", UUID.randomUUID().toString());
            properties.forEach(urlConnection::setRequestProperty);
            urlConnection.setDoOutput(true);
            if (body.length() > 0 && (!method.equals("GET") || !method.equals("DELETE"))) {
                final DataOutputStream dataOutputStream = new DataOutputStream(urlConnection.getOutputStream());
                dataOutputStream.writeBytes(body);
                dataOutputStream.flush();
                dataOutputStream.close();
            }
            final Scanner scanner = new Scanner(new InputStreamReader(urlConnection.getInputStream()));
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine());
            }
            scanner.close();
        }
        catch (final IOException e) {
            e.printStackTrace();
        }
        if (type != String.class) {
            try {
                return (T)JsonFetcher.gson.fromJson(stringBuilder.toString(), type);
            }
            catch (final JsonSyntaxException e2) {
                e2.printStackTrace();
                return null;
            }
            return (T)stringBuilder.toString();
        }
        return (T)stringBuilder.toString();
    }
    
    static {
        gson = new Gson();
    }
}
