package com.ahttys.server.service.spot;

import com.ahttys.server.domain.spot.Spot;
import com.ahttys.server.dto.SpotDto;

import java.util.List;

public interface SpotService {
    List<Spot> findAllSpot();
}
