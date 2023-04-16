package com.dev.mapdb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.mapdb.entity.BuildingInfo;
import com.dev.mapdb.model.BuildingInfoDto;
import com.dev.mapdb.service.MapDbService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/map-db")
@RequiredArgsConstructor
public class MapDbController {

    private final MapDbService mapDbService;

    // java thymeleaf用処理コメントアウト

    // @PostMapping("/building-info/create")
    // public String createBuildingInfo(@RequestParam(value = "buildingId") String
    // buildingId,
    // @RequestParam(value = "name") String name,
    // @RequestParam(value = "address") String address) {
    // String message = mapDbService.addBuildingInfo(buildingId, name, address);
    // return message;
    // }

    // @GetMapping("/building-info/get")
    // public Optional<BuildingInfo> getBuildingInfo(@RequestParam(value =
    // "buildingId") String buildingId) {
    // Optional<BuildingInfo> result = mapDbService.getBuildingInfo(buildingId);
    // return result;
    // }

    // /*
    // * 建物情報の登録
    // */
    // @PostMapping("/building-info/create")
    // @CrossOrigin(origins = {"*"})
    // public String createBuildingInfo(BuildingInfoDto buildingInfoDto) {
    // mapDbService.createBuildingInfo(buildingInfoDto);
    // return "redirect:/map-db/building-info/list";
    // }

    /*
     * 建物情報一覧画面の表示
     */
    // @GetMapping("/building-info/list")
    // public String displayBuildingInfoList(Model model) {
    // List<BuildingInfo> buildingInfoList = mapDbService.getBuildingnInfoList();
    // model.addAttribute("buildingInfoList", buildingInfoList);
    // return "building-info/list";
    // }

    /*
     * // * 建物情報登録画面の表示
     * //
     */
    // @GetMapping("/building-info/add")
    // public String displayBuildingInfoAddForm(Model model, @RequestParam(name =
    // "lat", required = true) String lat,
    // @RequestParam(name = "lng", required = true) String lng) {
    // model.addAttribute("buildingInfoDto", new BuildingInfoDto());
    // model.addAttribute("buildingId", UUID.randomUUID().toString());
    // model.addAttribute("lat", lat);
    // model.addAttribute("lng", lng);
    // return "building-info/add";
    // }

    /*
     * 建物情報画面の表示
     */
    // @GetMapping("/building-info/{buildingId}")
    // public String displayBuildingInfo(@PathVariable String buildingId, Model
    // model) {
    // BuildingInfo buildingInfo = mapDbService.getBuildingInfoById(buildingId);
    // model.addAttribute("buildingInfo", buildingInfo);
    // return "building-info/get";
    // }

    // @GetMapping("/search")
    // public String seachBuilding(Model model) {
    // List<BuildingInfo> buildingInfoList = mapDbService.getBuildingnInfoList();
    // model.addAttribute("buildingInfoList", buildingInfoList);
    // return "map/search";
    // }

    /*
     * 建物情報の登録
     */
    @PostMapping("/building-info/create")
    @CrossOrigin(origins = { "*" })
    public void createBuildingInfo(@RequestBody BuildingInfoDto buildingInfoDto) {
        mapDbService.createBuildingInfo(buildingInfoDto);
    }

    /**
     * 建物情報の更新
     */
    @PutMapping("/building-info/update")
    @CrossOrigin(origins = { "*" })
    public void updatesBuildingInfo(@RequestBody BuildingInfoDto buildingInfoDto) {
        mapDbService.updateBuildingInfo(buildingInfoDto);
    }

    /**
     * 建物情報の一覧取得
     */
    @GetMapping("/building-info/list")
    @CrossOrigin(origins = { "*" })
    public List<BuildingInfo> displayBuildingInfoList() {
        List<BuildingInfo> buildingInfoList = mapDbService.getBuildingnInfoList();
        return buildingInfoList;
    }

    /*
     * 建物情報の取得
     */
    @GetMapping("/building-info/{buildingId}")
    @CrossOrigin(origins = { "*" })
    public BuildingInfo displayBuildingInfo(@PathVariable String buildingId) {
        BuildingInfo buildingInfo = mapDbService.getBuildingInfoById(buildingId);
        System.out.println(buildingInfo);
        return buildingInfo;
    }
}
