package com.dev.mapdb.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class BuildingInfoDto implements Serializable {

    private String buildingId;

    private String name;

    private String address;

    private String latitude;

    private String longitude;

}
