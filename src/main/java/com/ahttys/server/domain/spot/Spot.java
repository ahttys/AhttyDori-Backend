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
    private String Area1;
    private String Area2;
    private String Business_Name;
    private String Brand_Name;
    private String Business_Content;
    private String Keywords;
    private Boolean Business_Location_YN;
    private String Adress;
}
