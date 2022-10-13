package com.dev.mapdb.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.mapdb.entity.BuildingInfo;
import com.dev.mapdb.model.BuildingInfoDto;
import com.dev.mapdb.repository.BuildingInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MapDbService {

    private final BuildingInfo buildingInfo;
    private final BuildingInfoRepository buildingInfoRepository;

    // public String addBuildingInfo(String buildingId, String name, String address)
    // {
    // buildingInfo.setBuildingId(buildingId);
    // buildingInfo.setName(name);
    // buildingInfo.setAddress(address);
    // buildingInfoRepository.save(buildingInfo);
    // return "Successfully buildingId: " + buildingId + " saved";
    // }

    // public Optional<BuildingInfo> getBuildingInfo(String buildingId) {
    // return buildingInfoRepository.findById(buildingId);
    // }

    public void createBuildingInfo(BuildingInfoDto buildingInfoDto) {
        buildingInfo.setBuildingId(buildingInfoDto.getBuildingId());
        buildingInfo.setName(buildingInfoDto.getName());
        buildingInfo.setAddress(buildingInfoDto.getAddress());
        buildingInfo.setLatitude(buildingInfoDto.getLatitude());
        buildingInfo.setLongtitude(buildingInfoDto.getLongtitude());
        buildingInfoRepository.save(buildingInfo);
    }

    /*
     * 建物情報の一覧情報の取得
     */
    public List<BuildingInfo> getBuildingnInfoList() {
        List<BuildingInfo> buildingInfoList = new ArrayList<BuildingInfo>();
        Iterator<BuildingInfo> buildingInfo = buildingInfoRepository.findAll().iterator();
        while (buildingInfo.hasNext()) {
            buildingInfoList.add(buildingInfo.next());
        }
        buildingInfoList.sort(Comparator.comparing(BuildingInfo::getBuildingId));
        return buildingInfoList;
    }

    /*
     * 建物IDに紐づく建物詳細情報の取得
     */
    public BuildingInfo getBuildingInfoById(String buildingId) {
        return buildingInfoRepository.findById(buildingId).get();
    }
}
