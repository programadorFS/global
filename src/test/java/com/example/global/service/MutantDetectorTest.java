package com.example.global.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MutantDetectorTest {

    private MutantDetector mutantDetector;

    @BeforeEach
    void setUp() {
        mutantDetector = new MutantDetector();
    }

    @Test
    @DisplayName("Debe detectar mutante con secuencias horizontal y diagonal")
    void testMutantWithHorizontalAndDiagonalSequences() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        assertTrue(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("Debe detectar mutante con secuencias verticales")
    void testMutantWithVerticalSequences() {
        String[] dna = {
                "AAAAGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CACCTA",
                "TCACTG"
        };
        assertTrue(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("Debe detectar mutante con múltiples secuencias horizontales")
    void testMutantWithMultipleHorizontalSequences() {
        String[] dna = {
                "AAAAGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        assertTrue(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("Debe detectar mutante con ambas diagonales")
    void testMutantWithBothDiagonals() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        assertTrue(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("Debe detectar mutante en matriz grande")
    void testMutantWithLargeDna() {
        String[] dna = {
                "ATGCGAATGC",
                "CAGTGCAGTG",
                "TTATGTTTAT",
                "AGAAGGAGAA",
                "CCCCTACCCC",
                "TCACTGTCAC",
                "ATGCGAATGC",
                "CAGTGCAGTG",
                "TTATGTTTAT",
                "AGAAGGAGAA"
        };
        assertTrue(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("Debe detectar mutante cuando todos los caracteres son iguales")
    void testMutantAllSameCharacter() {
        String[] dna = {
                "AAAA",
                "AAAA",
                "AAAA",
                "AAAA"
        };
        assertTrue(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("NO debe detectar mutante con solo una secuencia")
    void testNotMutantWithOnlyOneSequence() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATTT",
                "AGACGG",
                "GCGTCA",
                "TCACTG"
        };
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("NO debe detectar mutante sin secuencias")
    void testNotMutantWithNoSequences() {
        String[] dna = {
                "ATGC",
                "CAGT",
                "TGAC",
                "GCAT"
        };
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("NO debe detectar mutante en matriz pequeña sin secuencias")
    void testNotMutantSmallDna() {
        String[] dna = {
                "ATGC",
                "CAGT",
                "TGAC",
                "GCAT"
        };
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("Debe retornar false con DNA null")
    void testNotMutantWithNullDna() {
        assertFalse(mutantDetector.isMutant(null));
    }

    @Test
    @DisplayName("Debe retornar false con array vacío")
    void testNotMutantWithEmptyDna() {
        String[] dna = {};
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("Debe retornar false con matriz no cuadrada")
    void testNotMutantWithNonSquareDna() {
        String[] dna = {
                "ATGC",
                "CAGT",
                "TGAC"
        };
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("Debe retornar false con caracteres inválidos")
    void testNotMutantWithInvalidCharacters() {
        String[] dna = {
                "ATXC",
                "CAGT",
                "TGAC",
                "GCAT"
        };
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("Debe retornar false con fila null")
    void testNotMutantWithNullRow() {
        String[] dna = {
                "ATGC",
                null,
                "TGAC",
                "GCAT"
        };
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("Debe retornar false con matriz muy pequeña")
    void testNotMutantWithTooSmallDna() {
        String[] dna = {
                "ATG",
                "CAG",
                "TGA"
        };
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    @DisplayName("Debe detectar diagonal en esquina")
    void testMutantDiagonalInCorner() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        assertTrue(mutantDetector.isMutant(dna));
    }
}