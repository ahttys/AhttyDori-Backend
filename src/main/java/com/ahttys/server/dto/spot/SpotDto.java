package com.ahttys.server.dto.spot;

import com.ahttys.server.domain.spot.Spot;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpotDto {
    private String id;
    private String area1;
    private String area2;
    private String businessName;
    private String brandName;
    private String content;
    private List<String> tags;
    private Boolean businessLocationYN;
    private String address;
}
