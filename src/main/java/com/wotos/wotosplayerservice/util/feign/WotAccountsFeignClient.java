package com.wotos.wotosplayerservice.util.feign;

import com.wotos.wotosplayerservice.config.FeignConfig;
import com.wotos.wotosplayerservice.util.model.wot.*;
import com.wotos.wotosplayerservice.util.model.wot.achievements.WotAchievements;
import com.wotos.wotosplayerservice.util.model.wot.player.WotPlayer;
import com.wotos.wotosplayerservice.util.model.wot.player.WotPlayerDetails;
import com.wotos.wotosplayerservice.util.model.wot.player.WotPlayerVehicle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "WotAccountsFeignClient", url = "${env.urls.world_of_tanks_api}", configuration = FeignConfig.class)
@RequestMapping("/account")
public interface WotAccountsFeignClient {

    @GetMapping(value = "/list/", consumes = "application/json")
    ResponseEntity<WotApiResponse<List<WotPlayer>>> getPlayersByNickName(
            @RequestParam(name = "application_id") String appId,
            @RequestParam(name = "search") String nickname,
            @RequestParam(name = "language") String language,
            @RequestParam(name = "limit", required = false) String limit,
            @RequestParam(name = "type", required = false) String searchType
    );

    @GetMapping(value = "/info/", consumes = "application/json")
    ResponseEntity<WotApiResponse<Map<Integer, WotPlayerDetails>>> getPlayerDetails(
            @RequestParam("application_id") String appId,
            @RequestParam("access_token") String accessToken,
            @RequestParam("extra") String extra,
            @RequestParam("fields") String fields,
            @RequestParam("language") String language,
            @RequestParam("account_id") Integer accountId
    );

    @GetMapping(value = "/tanks/")
    ResponseEntity<WotApiResponse<List<WotPlayerVehicle>>> getPlayerVehicles(
            @RequestParam("application_id") String appId,
            @RequestParam("account_id") Integer accountId,
            @RequestParam("access_token") String accessToken,
            @RequestParam("fields") String fields,
            @RequestParam("language") String language,
            @RequestParam("tank_id") Integer[] tankIds
    );

    @GetMapping(value = "/achievements/")
    ResponseEntity<WotApiResponse<WotAchievements>> getPlayerAchievements(
            @RequestParam("application_id") String appId,
            @RequestParam("account_id") Integer accountId,
            @RequestParam("fields") String fields,
            @RequestParam("language") String language
    );

}
