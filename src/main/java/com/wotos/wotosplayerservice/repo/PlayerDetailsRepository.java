package com.wotos.wotosplayerservice.repo;

import com.wotos.wotosplayerservice.dao.PlayerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerDetailsRepository extends JpaRepository<PlayerDetails, Integer> {

    Optional<List<PlayerDetails>> findByNicknameContaining(String nickname);

    Optional<PlayerDetails> findByNickname(String nickname);

    Optional<PlayerDetails> findByAccountId(Integer accountId);

    Optional<Long> findUpdatedAtByAccountId(Integer accountId);

}
