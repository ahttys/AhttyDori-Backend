package com.ahttys.server.domain.spot;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "Spots")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Spot {
    @Id
    private String id;
    private String area1;
    private String area2;
    private String business_name;
    private String brand_name;
    private String business_content;
    private String keywords;
    private Boolean business_Location_YN;
    private String address;
}
