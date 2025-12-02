package thelp.desktop.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpService {

    private static final HttpClient client = HttpClient.newHttpClient();
    private static String token = null;

    private static final String BASE_URL = "http://localhost:8080/api/v1"; // TrustHelp API

    public static void setToken(String t) {
        token = t;
    }

    public static HttpResponse<String> post(String endpoint, String json) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint))
                .header("Content-Type", "application/json")
                .header("Authorization", token != null ? "Bearer " + token : "")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static HttpResponse<String> get(String endpoint) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint))
                .header("Authorization", token != null ? "Bearer " + token : "")
                .GET()
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
