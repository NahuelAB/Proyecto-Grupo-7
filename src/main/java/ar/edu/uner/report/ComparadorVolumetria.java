/*
 * ComparadorVolumetria.java
 *
 * Version 1.0
 *
 * 10/11/2026
 *
 * Copyright (c) 2026 Grupo 7 - UNER
 */
package ar.edu.uner.report;

import ar.edu.uner.domain.model.EstacionMeteorologica;
import java.time.LocalDate;
import java.util.Comparator;

/**
 * Compara estaciones meteorológicas según la precipitación acumulada en
 * un rango de fechas, de mayor a menor.
 *
 * @author Maricela
 */
public class ComparadorVolumetria implements Comparator<EstacionMeteorologica> {
    private InformeEstaciones informe;
    private LocalDate desde;
    private LocalDate hasta;

    /**
     * Crea un comparador para el rango de fechas indicado.
     *
     * @param informe informe que calcula la precipitación acumulada
     * @param desde fecha de inicio del rango
     * @param hasta fecha de fin del rango
     */
    public ComparadorVolumetria(InformeEstaciones informe, LocalDate desde, LocalDate hasta) {
        this.informe = informe;
        this.desde = desde;
        this.hasta = hasta;
    }

    /**
     * Compara dos estaciones por su precipitación acumulada en el rango,
     * de modo que la de mayor acumulado quede primero.
     *
     * @param a primera estación a comparar
     * @param b segunda estación a comparar
     * @return un número negativo si {@code a} acumuló más lluvia que
     *         {@code b}, positivo si acumuló menos, o cero si acumularon
     *         lo mismo
     */
    @Override
    public int compare(EstacionMeteorologica a, EstacionMeteorologica b) {
        double mmA = this.informe.calcularAcumulado(a, desde, hasta);
        double mmB = this.informe.calcularAcumulado(b, desde, hasta);
        return Double.compare(mmB, mmA);
    }
}