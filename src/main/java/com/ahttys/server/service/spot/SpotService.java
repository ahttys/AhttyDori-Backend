package com.ahttys.server.service.spot;

import com.ahttys.server.domain.spot.Spot;
import com.ahttys.server.dto.spot.SpotDto;

import java.util.List;

public interface SpotService {
    List<SpotDto> findAllSpot();
    List<SpotDto> findByArea1(String area1);
    List<SpotDto> findByArea2(String area2);
}
