package com.ahttys.server.service.spot;

import com.ahttys.server.domain.spot.Spot;
import com.ahttys.server.dto.spot.SpotDto;
import com.ahttys.server.repository.SpotRepository;
import com.ahttys.server.util.mappers.SpotMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpotServiceImpl implements SpotService {
    private final SpotRepository spotRepository;

    private List<SpotDto> toListDto(List<Spot> spots) {
        return spots.stream().map(SpotMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

    @Override
    public List<SpotDto> findAllSpot() {
        return toListDto(spotRepository.findAll());
    }

    @Override
    public List<SpotDto> findByArea1(String area1) {
        return toListDto(spotRepository.findByArea1(area1));
    }

    @Override
    public List<SpotDto> findByArea2(String area2) {
        return toListDto(spotRepository.findByArea2(area2));
    }
}
