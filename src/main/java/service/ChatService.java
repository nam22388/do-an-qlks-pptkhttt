package service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ChatService {

    private static final String API_URL = "http://localhost:5000/chat";

    private final HttpClient client;

    public ChatService() {
        client = HttpClient.newHttpClient();
    }

    public String askBot(String message) {

        try {
            if (message == null || message.trim().isEmpty()) {
                return "Vui lòng nhập câu hỏi.";
            }

            // JSON gửi sang Flask
            JsonObject json = new JsonObject();
            json.addProperty("message", message);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .timeout(Duration.ofSeconds(10))
                    .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            // check HTTP status
            if (response.statusCode() != 200) {
                return "Chatbot lỗi server (" + response.statusCode() + ")";
            }

            // parse JSON từ Flask
            JsonObject result = JsonParser
                    .parseString(response.body())
                    .getAsJsonObject();

            // Flask báo lỗi
            if (result.has("success") && !result.get("success").getAsBoolean()) {
                return result.get("message").getAsString();
            }

            return result.get("answer").getAsString();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Không thể kết nối tới Chatbot.";
        }
    }
}