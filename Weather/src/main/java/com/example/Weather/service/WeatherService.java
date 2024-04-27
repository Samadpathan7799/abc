package com.example.Weather.service;

import com.example.Weather.model.WeatherForecast;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class WeatherService {
    public ResponseEntity<String> callWeatherApi() {
        HttpResponse<String> response = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://forecast9.p.rapidapi.com/"))
                    .header("X-RapidAPI-Key", "42fca82b77mshaa8656914f5ef92p179b87jsn597fc8ae7024")
                    .header("X-RapidAPI-Host", "forecast9.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            // Handle exceptions
        }
        return ResponseEntity.status(response.statusCode()).body(response.body());
    }

    @Autowired
    private RestTemplate restTemplate;

    private final String rapidApiKey = "42fca82b77mshaa8656914f5ef92p179b87jsn597fc8ae7024";
    private final String rapidApiHost = "forecast9.p.rapidapi.com";


    public ResponseEntity<?> getForecastSummary(String city) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://forecast9.p.rapidapi.com/rapidapi/forecast/Berlin/summary/"))
                .header("X-RapidAPI-Key", "42fca82b77mshaa8656914f5ef92p179b87jsn597fc8ae7024")
                .header("X-RapidAPI-Host", "forecast9.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return ResponseEntity.status(response.statusCode()).body(response.body());
    }

    public ResponseEntity<?> getHourlyForecast(String city) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://forecast9.p.rapidapi.com/rapidapi/forecast/Berlin/hourly/"))
                .header("X-RapidAPI-Key", "42fca82b77mshaa8656914f5ef92p179b87jsn597fc8ae7024")
                .header("X-RapidAPI-Host", "forecast9.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());   return ResponseEntity.status(response.statusCode()).body(response.body());
    }

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Value("${weather.api.key}")
    private String weatherApiKey;

    @Value("${weather.api.host}")
    private String weatherApiHost;
    public WeatherForecast getWeatherForecast(String cityName) throws IOException, InterruptedException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", rapidApiKey);
        headers.set("X-RapidAPI-Host", rapidApiHost);

        HttpEntity<String> entity = new HttpEntity<String>(String.valueOf(headers));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://forecast9.p.rapidapi.com/"))
                .header("X-RapidAPI-Key", "42fca82b77mshaa8656914f5ef92p179b87jsn597fc8ae7024")
                .header("X-RapidAPI-Host", "forecast9.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        ObjectMapper mapper = new ObjectMapper();
        try {
            WeatherForecast weatherForecast = mapper.readValue(response.body(), WeatherForecast.class);
            return weatherForecast;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
