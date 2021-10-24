package com.wotos.wotosplayerservice.util;

import com.wotos.wotosplayerservice.jsonao.TankStatisticsJSONAO;
import com.wotos.wotosplayerservice.dto.ExpectedStatistics;
import com.wotos.wotosplayerservice.dto.PlayerTankStatistics;
import com.wotos.wotosplayerservice.repo.ExpectedStatisticsRepository;
import com.wotos.wotosplayerservice.repo.PlayerTankStatisticsRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CalculateStatistics {

    private final ExpectedStatisticsRepository expectedStatisticsRepository;
    private final PlayerTankStatisticsRepository tankStatisticsRepository;

    public CalculateStatistics(
            ExpectedStatisticsRepository expectedStatisticsRepository,
            PlayerTankStatisticsRepository tankStatisticsRepository
    ) {
        this.expectedStatisticsRepository = expectedStatisticsRepository;
        this.tankStatisticsRepository = tankStatisticsRepository;
    }

    public PlayerTankStatistics calculateTankStatistics(int playerID, TankStatisticsJSONAO tankStatisticsJSONAO) {
        ExpectedStatistics expectedStatistics = expectedStatisticsRepository.findById(tankStatisticsJSONAO.getTank_id()).get();

        float wins = tankStatisticsJSONAO.getStatistics().getAll().getWins();
        float battles = tankStatisticsJSONAO.getStatistics().getAll().getBattles();
        float survivedBattles = tankStatisticsJSONAO.getStatistics().getAll().getSurvived_battles();
        float frags = tankStatisticsJSONAO.getStatistics().getAll().getFrags();
        float spotted = tankStatisticsJSONAO.getStatistics().getAll().getSpotted();
        float damage = tankStatisticsJSONAO.getStatistics().getAll().getDamage_dealt();
        float defense = tankStatisticsJSONAO.getStatistics().getAll().getDropped_capture_points();
        float xp = tankStatisticsJSONAO.getStatistics().getAll().getXp();

        float winRate = wins / battles;
        float deaths = battles - survivedBattles == 0 ? 1 : battles - survivedBattles;
        float killRatio = frags / deaths;
        float avgKPG = frags / battles;
        float avgSpot = spotted / battles;
        float avgDPG = damage / battles;
        float avgXP = xp / battles;

        float tune = 10000;

        float DAMAGE = Math.round((avgDPG / expectedStatistics.getExpected_damage()) * tune) / tune;
        float SPOT = Math.round((avgSpot / expectedStatistics.getExpected_spot()) * tune) / tune;
        float FRAG = Math.round((avgKPG / expectedStatistics.getExpected_frag()) * tune) / tune;
        float DEFENSE = Math.round(((defense / battles) / expectedStatistics.getExpected_defense()) * tune) / tune;
        float WIN = Math.round((winRate / expectedStatistics.getExpected_win_rate()) * (tune * 100)) / tune;

        float DAMAGEc = (float) Math.max(0, (DAMAGE - 0.22) / 0.78);
        float SPOTc = (float) Math.max(0, Math.min(DAMAGEc + 0.1, (SPOT - 0.38) / 0.62));
        float FRAGc = (float) Math.max(0, Math.min(DAMAGEc + 0.2, (FRAG - 0.12) / 0.88));
        float DEFENSEc = (float) Math.max(0, Math.min(DAMAGEc + 0.1, (DEFENSE - 0.10) / 0.9));
        float WINc = (float) Math.max(0, (WIN - 0.71) / 0.29);

        float wn8 = (float) ((980 * DAMAGEc) + (210 * DAMAGEc * FRAGc) + (155 * FRAGc * SPOTc) + (75 * DEFENSEc * FRAGc) + (145 * Math.min(1.8, WINc)));
        int tankID = tankStatisticsJSONAO.getTank_id();

        PlayerTankStatistics tankStatistics = buildTankStatistics(
                playerID, tankID, wn8, (int) battles, killRatio, avgXP
        );

        saveTankStatistics(playerID, tankID, tankStatistics);

        return tankStatistics;
    }

    private PlayerTankStatistics buildTankStatistics(Integer playerID, Integer tankID, Float wn8, Integer battles, Float killRatio, Float averageXP) {
        PlayerTankStatistics tankStatistics = new PlayerTankStatistics();
        tankStatistics.setPlayer_id(playerID);
        tankStatistics.setTank_id(tankID);
        tankStatistics.setWn8(wn8);
        tankStatistics.setBattles(battles);
        tankStatistics.setKill_ratio(killRatio);
        tankStatistics.setAverage_xp(averageXP);

        return tankStatistics;
    }

    private void saveTankStatistics(Integer playerID, Integer tankID, PlayerTankStatistics tankStatistics) {
        Optional<Integer> tankInDB = tankStatisticsRepository.findMaxBattlesByPlayerAndTankID(playerID, tankID);

        // Todo: move 100 battle snapshot check to before calculations
        if (!tankInDB.isPresent() || tankStatistics.getBattles() - tankInDB.get() >= 100) {
            tankStatisticsRepository.save(tankStatistics);
        }
    }

}
