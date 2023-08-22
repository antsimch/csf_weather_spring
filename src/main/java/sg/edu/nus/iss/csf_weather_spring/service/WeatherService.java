package sg.edu.nus.iss.csf_weather_spring.service;

import java.io.StringReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@Service
public class WeatherService {
    
    private static final String API_URL = 
            "https://api.openweathermap.org/data/2.5/weather";

    @Value("${weather.api.key}")
    private String API_KEY;


    public JsonObject getWeatherByCity(String city) {
        String url = UriComponentsBuilder
                .fromUriString(API_URL)
                .queryParam("q", city)
                .queryParam("appid", API_KEY)
                .toUriString();

        System.out.println("\n\n" + ">>>> api url: " + url + "\n\n");

        RequestEntity<Void> req = RequestEntity.get(url).build();
        RestTemplate template = new RestTemplate();

        try {
            ResponseEntity<String> resp = template.exchange(
                    req, String.class);

            return Json.createReader(new StringReader(resp.getBody()))
                    .readObject();

        } catch (Exception e) {
            return null;
        }
    }
}
