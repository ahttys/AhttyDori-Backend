package com.ahttys.server.repository;

import com.ahttys.server.domain.spot.Spot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SpotRepository extends MongoRepository<Spot, String> {
    List<Spot> findByArea1(String name);
    List<Spot> findByArea2(String name);
    Spot findByBrandName(String name);
}
