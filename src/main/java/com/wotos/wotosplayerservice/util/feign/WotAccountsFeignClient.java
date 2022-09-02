package com.wotos.wotosplayerservice.util.feign;

import com.wotos.wotosplayerservice.util.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "WotAccountsFeignClient", url = "${env.urls.world_of_tanks_api}")
@RequestMapping("/account")
public interface WotAccountsFeignClient {

    @GetMapping(value = "/list/", consumes = "application/json")
    ResponseEntity<WotApiResponse<List<Player>>> getPlayersByNickName(
            @RequestParam(name = "application_id") String appId,
            @RequestParam(name = "search") String nickname,
            @RequestParam(name = "language") String language,
            @RequestParam(name = "limit", required = false) String limit,
            @RequestParam(name = "type", required = false) String searchType
    );

    @GetMapping(value = "/info/", consumes = "application/json")
    ResponseEntity<WotApiResponse<Map<String, PlayerDetails>>> getPlayerDetails(
            @RequestParam("application_id") String appId,
            @RequestParam("language") String language,
            @RequestParam("account_id") String accountId
    );

    @GetMapping(value = "/tanks/")
    ResponseEntity<WotApiResponse<List<PlayerVehicle>>> getPlayerVehicles(
            @RequestParam("application_id") String appId,
            @RequestParam("account_id") String accountId,
            @RequestParam("access_token") String accessToken,
            @RequestParam("fields") String fields,
            @RequestParam("language") String language,
            @RequestParam("tank_id") String tankIds
    );

    @GetMapping(value = "/achievements/")
    ResponseEntity<WotApiResponse<Achievements>> getPlayerAchievements(
            @RequestParam("application_id") String appId,
            @RequestParam("account_id") String accountId,
            @RequestParam("fields") String fields,
            @RequestParam("language") String language
    );

}
