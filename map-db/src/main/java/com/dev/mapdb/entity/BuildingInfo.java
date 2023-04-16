package com.dev.mapdb.entity;

import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "buildingInfo")
public class BuildingInfo {

    @DynamoDBHashKey
    @DynamoDBAttribute(attributeName = "buildingId")
    private String buildingId;

    @DynamoDBAttribute(attributeName = "name")
    private String name;

    @DynamoDBAttribute(attributeName = "address")
    private String address;

    @DynamoDBAttribute(attributeName = "latitude")
    private String latitude;

    @DynamoDBAttribute(attributeName = "longitude")
    private String longitude;

}
