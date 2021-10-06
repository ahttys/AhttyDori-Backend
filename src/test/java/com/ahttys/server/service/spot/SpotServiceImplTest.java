package com.ahttys.server.service.spot;

import com.ahttys.server.domain.spot.Spot;
import com.ahttys.server.repository.SpotRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@WebAppConfiguration
class SpotServiceImplTest {
    @Autowired
    private SpotRepository spotRepository;

    @Test
    public void testSpotsFindByArea1() {
        List<Spot> spots = spotRepository.findByArea1("경기");
        spots.forEach(spot -> {
            assertEquals(spot.getArea1(), "경기");
        });
    }

    @Test
    public void testSpotsFindByArea2() {
        List<Spot> spots = spotRepository.findByArea2("안성시");
        spots.forEach(spot -> {
            assertEquals(spot.getArea2(), "안성시");
        });
    }
}