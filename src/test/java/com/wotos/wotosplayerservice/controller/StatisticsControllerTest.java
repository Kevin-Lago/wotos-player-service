package com.wotos.wotosplayerservice.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StatisticsControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    private StatisticsController statisticsController;
//
//    @Test
//    public void conextLoads() {
//        assertThat(statisticsController).isNotNull();
//    }

    @Test
    public void testGetTankStatisticsByPlayer() throws Exception {
        this.mockMvc.perform(get("/stats/tank?playerId=1&tankId=1"))
                .andDo(print())
                .andExpect(status().isOk());
//                .andExpect(content().string());
    }

    public void testGetAllTankStatisticsByPlayer() {
    }

}