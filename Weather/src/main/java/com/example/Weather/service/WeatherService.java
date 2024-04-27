package com.example.Weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    private final String rapidApiKey = "42fca82b77mshaa8656914f5ef92p179b87jsn597fc8ae7024";
    private final String rapidApiHost = "forecast9.p.rapidapi.com";

    public ResponseEntity<?> getForecastSummary(String city) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", rapidApiKey);
        headers.set("x-rapidapi-host", rapidApiHost);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        String url = "https://forecast9.p.rapidapi.com/forecastSummaryByLocationName?locationName=" + city;

        ResponseEntity<Object> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                Object.class
        );
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    public ResponseEntity<?> getHourlyForecast(String city) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", rapidApiKey);
        headers.set("x-rapidapi-host", rapidApiHost);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        String url = "https://forecast9.p.rapidapi.com/hourlyForecastByLocationName?locationName=" + city;

        ResponseEntity<Object> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                Object.class
        );
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
