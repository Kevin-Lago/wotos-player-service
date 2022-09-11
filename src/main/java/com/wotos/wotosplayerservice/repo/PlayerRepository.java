package com.wotos.wotosplayerservice.repo;

import com.wotos.wotosplayerservice.dao.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    Optional<List<Player>> findByNicknameContaining(String nickname);

    Optional<Player> findByNickname(String nickname);

}
