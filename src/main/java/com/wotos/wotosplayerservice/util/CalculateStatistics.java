package com.wotos.wotosplayerservice.util;

import com.wotos.wotosplayerservice.dto.PlayerTankStatisticsSnapshot;
import com.wotos.wotosplayerservice.jsonao.TankStatisticsJSONAO;
import com.wotos.wotosplayerservice.dto.ExpectedStatistics;
import com.wotos.wotosplayerservice.repo.ExpectedStatisticsRepository;
import com.wotos.wotosplayerservice.repo.PlayerTankStatisticsSnapshotRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CalculateStatistics {

    private final ExpectedStatisticsRepository expectedStatisticsRepository;
    private final PlayerTankStatisticsSnapshotRepository playerTankStatisticsRepository;

    public CalculateStatistics(
            ExpectedStatisticsRepository expectedStatisticsRepository,
            PlayerTankStatisticsSnapshotRepository playerTankStatisticsRepository
    ) {
        this.expectedStatisticsRepository = expectedStatisticsRepository;
        this.playerTankStatisticsRepository = playerTankStatisticsRepository;
    }

    public PlayerTankStatisticsSnapshot calculateTankStatistics(int playerID, TankStatisticsJSONAO tankStatisticsJSONAO) {
        ExpectedStatistics expectedStatistics = expectedStatisticsRepository.findById(tankStatisticsJSONAO.getTankID()).get();

        float wins = tankStatisticsJSONAO.getStatistics().getAll().getWins();
        float battles = tankStatisticsJSONAO.getStatistics().getAll().getBattles();
        float survivedBattles = tankStatisticsJSONAO.getStatistics().getAll().getSurvivedBattles();
        float frags = tankStatisticsJSONAO.getStatistics().getAll().getFrags();
        float spotted = tankStatisticsJSONAO.getStatistics().getAll().getSpotted();
        float damage = tankStatisticsJSONAO.getStatistics().getAll().getDamageDealt();
        float defense = tankStatisticsJSONAO.getStatistics().getAll().getDroppedCapturePoints();
        float xp = tankStatisticsJSONAO.getStatistics().getAll().getXp();

        float winRate = wins / battles;
        float deaths = battles - survivedBattles == 0 ? 1 : battles - survivedBattles;
        float killRatio = frags / deaths;
        float avgKPG = frags / battles;
        float avgSpot = spotted / battles;
        float avgDPG = damage / battles;
        float avgXP = xp / battles;

        float tune = 10000;

        float DAMAGE = Math.round((avgDPG / expectedStatistics.getExpectedDamage()) * tune) / tune;
        float SPOT = Math.round((avgSpot / expectedStatistics.getExpectedSpot()) * tune) / tune;
        float FRAG = Math.round((avgKPG / expectedStatistics.getExpectedFrag()) * tune) / tune;
        float DEFENSE = Math.round(((defense / battles) / expectedStatistics.getExpectedDefense()) * tune) / tune;
        float WIN = Math.round((winRate / expectedStatistics.getExpectedWinRate()) * (tune * 100)) / tune;

        float DAMAGEc = (float) Math.max(0, (DAMAGE - 0.22) / 0.78);
        float SPOTc = (float) Math.max(0, Math.min(DAMAGEc + 0.1, (SPOT - 0.38) / 0.62));
        float FRAGc = (float) Math.max(0, Math.min(DAMAGEc + 0.2, (FRAG - 0.12) / 0.88));
        float DEFENSEc = (float) Math.max(0, Math.min(DAMAGEc + 0.1, (DEFENSE - 0.10) / 0.9));
        float WINc = (float) Math.max(0, (WIN - 0.71) / 0.29);

        float wn8 = (float) ((980 * DAMAGEc) + (210 * DAMAGEc * FRAGc) + (155 * FRAGc * SPOTc) + (75 * DEFENSEc * FRAGc) + (145 * Math.min(1.8, WINc)));
        int tankID = tankStatisticsJSONAO.getTankID();

        PlayerTankStatisticsSnapshot tankStatistics = buildTankStatistics(
                playerID, tankID, wn8, (int) battles, killRatio, avgXP
        );

        return tankStatistics;
    }

    private PlayerTankStatisticsSnapshot buildTankStatistics(Integer playerID, Integer tankID, Float wn8, Integer battles, Float killRatio, Float averageXP) {
        PlayerTankStatisticsSnapshot tankStatistics = new PlayerTankStatisticsSnapshot();
        tankStatistics.setPlayerID(playerID);
        tankStatistics.setTankID(tankID);
        tankStatistics.setAverageWn8(wn8);
        tankStatistics.setTotalBattles(battles);
        tankStatistics.setAverageKillRatio(killRatio);
        tankStatistics.setAverageExperience(averageXP);

        return tankStatistics;
    }

//    private void saveTankStatistics(Integer playerID, Integer tankID, PlayerTankStatisticsSnapshot tankStatistics) {
//        Optional<Integer> tankInDB = playerTankStatisticsRepository.findMaxBattlesByPlayerAndTankID(playerID, tankID);
//
//        // Todo: move 100 battle snapshot check to before calculations
//        if (!tankInDB.isPresent() || tankStatistics.getTotalBattles() - tankInDB.get() >= 100) {
//            playerTankStatisticsRepository.save(tankStatistics);
//        }
//    }

}
