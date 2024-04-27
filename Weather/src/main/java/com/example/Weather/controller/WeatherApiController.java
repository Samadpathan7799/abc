package com.example.Weather.controller;

import com.example.Weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherApiController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/summary/{city}")
    public ResponseEntity<?> getForecastSummary(@PathVariable String city) {
        ResponseEntity<?> response = weatherService.getForecastSummary(city);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @GetMapping("/hourly/{city}")
    public ResponseEntity<?> getHourlyForecast(@PathVariable String city) {
        ResponseEntity<?> response = weatherService.getHourlyForecast(city);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
