package com.ahttys.server.controller;

import com.ahttys.server.dto.spot.SpotDto;
import com.ahttys.server.service.spot.SpotService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("spot")
public class SpotController {
    private SpotService spotService;

    @GetMapping("")
    public ResponseEntity<?> getSpots() {
        List<SpotDto> spots = spotService.findAllSpot();
        return ResponseEntity.ok(spots);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getSpotsByArea1(@RequestParam(name = "area1") String area1) {
        List<SpotDto> spots = spotService.findByArea1(area1);
        return ResponseEntity.ok(spots);
    }
}
