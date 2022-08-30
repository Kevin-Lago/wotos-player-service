package com.wotos.wotosplayerservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wotos.wotosplayerservice.dto.Player;
import com.wotos.wotosplayerservice.dto.PlayerPersonalData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    private ObjectMapper mapper = new ObjectMapper();
    private RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders httpHeaders = new HttpHeaders();

    @Value("${env.app_id}")
    private String APP_ID;
    @Value("${env.urls.wot_player_details}")
    private String WOT_API_ACCOUNT_DETAILS_URL;
    @Value("${env.urls.wot_player_list}")
    private String WOT_API_ACCOUNT_LIST_URL;
    private final String LANGUAGE = "en";
    private final String PLAYER_EXACT_QUERY = "&type=exact";

    public PlayerService() {
        mapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true
        );
    }

    public List<Player> fetchListOfPlayersByNickname(String nickname) {
        String url = String.format(WOT_API_ACCOUNT_LIST_URL, APP_ID, LANGUAGE, nickname);
        String result = restTemplate.getForObject(url, String.class);

        try {
            JsonNode data = mapper.readTree(result).get("data");
            List<Player> Players = new ArrayList<>();

            data.forEach(player -> {
                try {
                    Players.add(mapper.treeToValue(player, Player.class));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });

            return Players;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Player fetchPlayerIDByNickname(String nickname) {
//        String url = String.format(WOT_API_ACCOUNT_LIST_URL + PLAYER_EXACT_QUERY, APP_ID, LANGUAGE, nickname);
        String result = restTemplate.getForObject(WOT_API_ACCOUNT_LIST_URL, String.class, APP_ID, LANGUAGE, nickname + PLAYER_EXACT_QUERY);

        try {
            JsonNode data = mapper.readTree(result).get("data");
            Player player = mapper.treeToValue(data, Player.class);

            return player;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
//            return new ResponseEntity<>(httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public PlayerPersonalData fetchPlayerDetailsById(Integer account_id) {

        String url = String.format(WOT_API_ACCOUNT_DETAILS_URL, APP_ID, LANGUAGE, account_id);
        String result = restTemplate.getForObject(url, String.class);

        try {
            JsonNode data = mapper.readTree(result).get("data").get(account_id);
            PlayerPersonalData playerPersonalData = mapper.treeToValue(data, PlayerPersonalData.class);

            return playerPersonalData;
        } catch(IOException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

}
