package com.wotos.wotosplayerservice.util.feign;

import com.wotos.wotosplayerservice.util.model.VehicleStatistics;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "WoTPlayerVehiclesFeignClient", url = "${env.urls.world_of_tanks_api}")
@RequestMapping("/tanks")
public interface WotPlayerVehiclesFeignClient {

    @GetMapping("/stats/")
    ResponseEntity<Map<String, VehicleStatistics>> getPlayerTankStatistics(
            @RequestParam("application_id") String appId,
            @RequestParam("account_id") String accountId,
            @RequestParam("access_token") String accessToken,
            @RequestParam("extra") String extra,
            @RequestParam("fields") String fields,
            @RequestParam("in_garage") String inGarage,
            @RequestParam("language") String language,
            @RequestParam("tank_id") String tankId
    );

    @GetMapping("/achievements/")
    ResponseEntity<String> getPlayerTankAchievements(
            @RequestParam("application_id") String appId,
            @RequestParam("account_id") String accountId,
            @RequestParam("access_token") String accessToken,
            @RequestParam("fields") String fields,
            @RequestParam("in_garage") String inGarage,
            @RequestParam("language") String language,
            @RequestParam("tank_id") String tankId
    );



}
