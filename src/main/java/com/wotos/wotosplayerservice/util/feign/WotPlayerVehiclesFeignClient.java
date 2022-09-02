package com.wotos.wotosplayerservice.util.feign;

import com.wotos.wotosplayerservice.config.FeignConfig;
import com.wotos.wotosplayerservice.util.model.wot.VehicleStatistics;
import com.wotos.wotosplayerservice.util.model.wot.WotApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

@FeignClient(name = "WoTPlayerVehiclesFeignClient", url = "${env.urls.world_of_tanks_api}", configuration = FeignConfig.class)
@RequestMapping("/tanks")
public interface WotPlayerVehiclesFeignClient {

    @GetMapping("/stats/")
    ResponseEntity<WotApiResponse<Map<Integer, List<VehicleStatistics>>>> getPlayerTankStatistics(
            @RequestParam("application_id") String appId,
            @RequestParam("account_id") Integer accountId,
            @RequestParam("access_token") String accessToken,
            @RequestParam("extra") String extra,
            @RequestParam("fields") String fields,
            @RequestParam("in_garage") @Min(0) @Max(1) Integer inGarage,
            @RequestParam("language") String language,
            @RequestParam("tank_id") Integer[] tankIds
    );

    @GetMapping("/achievements/")
    ResponseEntity<WotApiResponse<String>> getPlayerTankAchievements(
            @RequestParam("application_id") String appId,
            @RequestParam("account_id") String accountId,
            @RequestParam("access_token") String accessToken,
            @RequestParam("fields") String fields,
            @RequestParam("in_garage") String inGarage,
            @RequestParam("language") String language,
            @RequestParam("tank_id") String tankId
    );

}
