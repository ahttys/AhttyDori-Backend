package com.ahttys.server.domain.spot;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.util.List;

@Document(collection = "Spots")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Spot {
    @Id
    private String id;
    private String area1;
    private String area2;
    @Field(name = "business_name")
    private String businessName;
    @Field(name = "brand_name")
    private String brandName;
    private String content;
    private List<String> tags;
    @Field(name = "business_location_yn")
    private Boolean businessLocationYN;
    private String address;
}
