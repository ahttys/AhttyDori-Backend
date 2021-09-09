package com.ahttys.server.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpotDto {
    private String id;
    private String area1;
    private String area2;
    private String business_name;
    private String brand_name;
    private String business_content;
    private String keywords;
    private Boolean business_location_yn;
    private String adress;
}
