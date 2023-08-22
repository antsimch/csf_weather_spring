package sg.edu.nus.iss.csf_weather_spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.JsonObject;
import sg.edu.nus.iss.csf_weather_spring.service.WeatherService;

@RestController
@RequestMapping(path="/api")
public class WeatherController {

    private WeatherService weatherSvc;
    
    public WeatherController(WeatherService weatherSvc) {
        this.weatherSvc = weatherSvc;
    }

    @GetMapping(path="/weather")
    public ResponseEntity<String> getWeather(
            @RequestParam String city) {
                
        JsonObject result = weatherSvc.getWeatherByCity(city);

        if (result != null) {
            return ResponseEntity.ok(result.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Invalid city name");
        }
    }
}
