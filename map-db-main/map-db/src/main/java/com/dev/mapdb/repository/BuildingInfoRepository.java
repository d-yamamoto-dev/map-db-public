package com.dev.mapdb.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dev.mapdb.entity.BuildingInfo;

@Repository
@EnableScan
public interface BuildingInfoRepository extends CrudRepository<BuildingInfo, String>{
}
