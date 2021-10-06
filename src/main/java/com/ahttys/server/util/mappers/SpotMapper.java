package com.ahttys.server.util.mappers;

import com.ahttys.server.domain.spot.Spot;
import com.ahttys.server.dto.spot.SpotDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SpotMapper {
    SpotMapper INSTANCE = Mappers.getMapper(SpotMapper.class);
    SpotDto toDto(Spot spot);

    Spot toSpot(SpotDto spotDto);
}
