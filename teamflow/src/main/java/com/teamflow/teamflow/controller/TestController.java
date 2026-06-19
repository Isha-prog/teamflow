package com.teamflow.teamflow.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
@RestController
public class TestController {
    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of(
                "app", "TeamFlow",
                "status", "running",
                "message", "Welcome to TeamFlow API"
        );
    }
}
