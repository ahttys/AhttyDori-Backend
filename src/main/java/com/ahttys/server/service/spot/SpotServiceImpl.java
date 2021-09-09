package com.ahttys.server.service.spot;

import com.ahttys.server.domain.spot.Spot;
import com.ahttys.server.repository.SpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotServiceImpl implements SpotService {
    private final SpotRepository spotRepository;
    @Override
    public List<Spot> findAllSpot() {
        return spotRepository.findAll();
    }
}
