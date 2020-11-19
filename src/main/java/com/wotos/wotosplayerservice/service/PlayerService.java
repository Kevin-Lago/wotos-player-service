package com.wotos.wotosplayerservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wotos.wotosplayerservice.model.Player;
import com.wotos.wotosplayerservice.model.PlayerDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final String APP_ID_QUERY = "?application_id=";
    private final String LANGUAGE_QUERY = "&language=";
    private final String LANGUAGE = "en";
    private final String PLAYER_NICKNAME_SEARCH_QUERY = "&search=";
    private final String PLAYER_ID_SEARCH_QUERY = "&account_id=";
    private final String PLAYER_EXACT_QUERY = "&type=exact";
    private final String WOT_API_ACCOUNT_LIST = "https://api.worldoftanks.com/wot/account/list/";
    private final String WOT_API_ACCOUNT_INFO = "https://api.worldoftanks.com/wot/account/info/";

    public PlayerService() {
        mapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false
        );
        httpHeaders.add("Language", LANGUAGE);
        httpHeaders.add("Request Method", "")
    }

    public ResponseEntity<List<Player>> getListOfPlayersByNickname(String nickname) {

        String uri = WOT_API_ACCOUNT_LIST + APP_ID_QUERY + APP_ID +
                LANGUAGE_QUERY + LANGUAGE + PLAYER_NICKNAME_SEARCH_QUERY + nickname;
        String result = restTemplate.getForObject(uri, String.class);

        try {
            JsonNode data = mapper.readTree(result).get("data");
            List<Player> players = new ArrayList<>();

            data.forEach(player -> {
                try {
                    players.add(mapper.treeToValue(player, Player.class));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });

            return new ResponseEntity<>(players, httpHeaders, HttpStatus.OK);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<PlayerDetails> getPlayerIDByNickname(String nickname) {

        String uri = WOT_API_ACCOUNT_LIST + APP_ID_QUERY + APP_ID + PLAYER_EXACT_QUERY +
                LANGUAGE_QUERY + LANGUAGE + PLAYER_NICKNAME_SEARCH_QUERY + nickname;
        String result = restTemplate.getForObject(uri, String.class);

        try {
            String id = mapper.readTree(result).get("data").get(0).get("account_id").toString();

            // check for player in db
            PlayerDetails player = getPlayerById(id);

            return new ResponseEntity<>(player, httpHeaders, HttpStatus.OK);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private PlayerDetails getPlayerById(String id) {

        String uri = WOT_API_ACCOUNT_INFO + APP_ID_QUERY + APP_ID +
                LANGUAGE_QUERY + LANGUAGE + PLAYER_ID_SEARCH_QUERY + id;
        String result = restTemplate.getForObject(uri, String.class);

        try {
            JsonNode data = mapper.readTree(result).get("data").get(id);

            PlayerDetails player = mapper.treeToValue(data, PlayerDetails.class);

            return player;
        } catch(IOException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

}
