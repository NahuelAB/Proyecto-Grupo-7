/*
 * NivelVulnerabilidad.java
 *
 * Version 1.0
 *
 * 20/10/2026
 *
 * Copyright (c) 2026 Grupo 7 - UNER
 */


package ar.edu.uner.domain.enums;
/**
 * Representa el nivel de vulnerabilidad frente a un evento climatico.
 *
 * @author Maricela
 */


public enum NivelVulnerabilidad {
    /**Riesgo bajo: el impacto esperado es minimo */
    BAJO,
    /**Riesgo medio: puede haber daños moderados */
    MEDIO,
    /**Riesgo alto: se esperan daños importantes */
    ALTO,
    /**Riesgo muy alto: se requiere atención o evacuación prioritaria */
    MUY_ALTO
}
