package com.dev.mapdb.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

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

    /**
     * 建物情報の登録
     * 
     * @param buildingInfoDto
     */
    public void createBuildingInfo(BuildingInfoDto buildingInfoDto) {
        // ランダムID
        String id = UUID.randomUUID().toString();
        // 各フィールドセット
        buildingInfo.setBuildingId(id);
        buildingInfo.setName(buildingInfoDto.getName());
        buildingInfo.setAddress(buildingInfoDto.getAddress());
        buildingInfo.setLatitude(buildingInfoDto.getLatitude());
        buildingInfo.setLongitude(buildingInfoDto.getLongitude());
        // DBへ登録
        buildingInfoRepository.save(buildingInfo);
    }

    /**
     * 建物情報の更新
     * 
     * @param buildingInfoDto
     */
    public void updateBuildingInfo(BuildingInfoDto buildingInfoDto) {
        // 各フィールドセット
        buildingInfo.setBuildingId(buildingInfoDto.getBuildingId());
        buildingInfo.setName(buildingInfoDto.getName());
        buildingInfo.setAddress(buildingInfoDto.getAddress());
        buildingInfo.setLatitude(buildingInfoDto.getLatitude());
        buildingInfo.setLongitude(buildingInfoDto.getLongitude());
        // DBへ登録
        buildingInfoRepository.save(buildingInfo);
    }

    /*
     * 建物情報の一覧取得
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
     * 建物情報の取得
     */
    public BuildingInfo getBuildingInfoById(String buildingId) {
        return buildingInfoRepository.findById(buildingId).get();
    }
}
