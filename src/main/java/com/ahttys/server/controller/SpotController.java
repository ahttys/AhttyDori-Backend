package com.ahttys.server.controller;

import com.ahttys.server.domain.spot.Spot;
import com.ahttys.server.service.spot.SpotService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("spot")
public class SpotController {
    private SpotService spotService;

    @GetMapping("")
    public ResponseEntity<?> getSpots() {
        List<Spot> spots = spotService.findAllSpot();
        return ResponseEntity.ok(spots);
    }
}
