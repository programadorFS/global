package com.example.global.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MutantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /mutant debe retornar 200 para mutante")
    void testCheckMutant_ReturnOk_WhenIsMutant() throws Exception {
        String jsonRequest = """
            {
                "dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
            }
            """;

        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /mutant debe retornar 403 para humano")
    void testCheckMutant_ReturnForbidden_WhenIsNotMutant() throws Exception {
        String jsonRequest = """
            {
                "dna": ["ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"]
            }
            """;

        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("POST /mutant debe retornar 400 con DNA inválido")
    void testCheckMutant_ReturnBadRequest_WhenDnaIsInvalid() throws Exception {
        String jsonRequest = """
            {
                "dna": ["ATXC","CAGT","TTAT","AGAC"]
            }
            """;

        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /mutant debe retornar 400 con matriz no cuadrada")
    void testCheckMutant_ReturnBadRequest_WhenDnaIsNotSquare() throws Exception {
        String jsonRequest = """
            {
                "dna": ["ATGC","CAGT","TTAT"]
            }
            """;

        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /mutant debe retornar 400 con array vacío")
    void testCheckMutant_ReturnBadRequest_WhenDnaIsEmpty() throws Exception {
        String jsonRequest = """
            {
                "dna": []
            }
            """;

        mockMvc.perform(post("/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("GET /stats debe retornar estadísticas correctas")
    void testGetStats_ReturnCorrectStats() throws Exception {
        mockMvc.perform(get("/stats"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.count_mutant_dna").exists())
                .andExpect(jsonPath("$.count_human_dna").exists())
                .andExpect(jsonPath("$.ratio").exists());
    }
}