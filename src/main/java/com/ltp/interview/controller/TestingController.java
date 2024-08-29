package com.ltp.interview.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltp.interview.model.dto.UserRegisterRequestDto;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "*", methods = RequestMethod.GET)
public class TestingController {

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @SneakyThrows
    @GetMapping("/")
    public ResponseEntity<?> test() {
        final UserRegisterRequestDto request = new UserRegisterRequestDto();
        final String nonce = UUID.randomUUID().toString().substring(0, 5);
        request.setLogin("test" + nonce);
        request.setPassword("test#123" + nonce);
        request.setFullName("fullname" + nonce);
        request.setGenderName("male");

        final ObjectMapper objectMapper = new ObjectMapper();
        final String requestJson = objectMapper.writeValueAsString(request);

        final HttpRequest httpRequest = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestJson))
                .uri(new URI("http://localhost:8080/api/v1/auth/register"))
                .build();

        final HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode() != 200) {
            return ResponseEntity.accepted().build();
        }

        return ResponseEntity.ok(request);
    }

}
