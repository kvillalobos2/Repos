package com.applonglife.controller;

import com.applonglife.entities.BigTree;
import com.applonglife.service.impl.BigTreeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BigTreeController.class)
@ActiveProfiles("test")

public class BigTreeControllerTest {


        @Autowired
        private MockMvc mockMvc;
        @MockBean
        private BigTreeServiceImpl bigTreeService;

        private List<BigTree> bigTreeList;

        @BeforeEach
        void setUp() {
            bigTreeList = new ArrayList<>();
            bigTreeList.add(new BigTree(1L, "user", "katerin", "villalobos", "fememino", "21-de febrero"));
            bigTreeList.add(new BigTree(1L, "user2", "giane", "coronel", "fememino", "22-de febrero"));

        }

        @Test
        void findAllBigTrees() throws Exception {
            given(bigTreeService.getAll()).willReturn(bigTreeList);
            mockMvc.perform(get("/api/bigTrees")).andExpect(status().isOk());
        }

    @Test
    void updateBigTrees() throws Exception {
        given(bigTreeService.getAll()).willReturn(bigTreeList);
        mockMvc.perform(get("/api/bigTrees")).andExpect(status().isOk());
    }


}
