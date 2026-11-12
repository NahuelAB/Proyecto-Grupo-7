/*
 * GestionInformeVolumetria.java
 *
 * Version 1.0
 *
 * 11/11/2026
 *
 * Copyright (c) 2026 Grupo 7 - UNER
 */
package ar.edu.uner.service.gestioninformes;

import ar.edu.uner.domain.model.EstacionMeteorologica;
import ar.edu.uner.report.RankingVolumetria;
import ar.edu.uner.service.GestionInfraestructura;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Muestra por pantalla el ranking de estaciones meteorológicas por
 * volumetría, a partir de los datos que calcula {@link RankingVolumetria}.
 *
 * @author Maricela
 */
public class GestionInformeVolumetria {
    private RankingVolumetria ranking;

    /**
     * Crea la gestión del informe a partir de la gestión de infraestructura
     * del sistema.
     *
     * @param infraestructura gestión de infraestructura del sistema
     */
    public GestionInformeVolumetria(GestionInfraestructura infraestructura) {
        this.ranking = new RankingVolumetria(infraestructura);
    }

    /**
     * Muestra el ranking de estaciones ordenado de mayor a menor según la
     * precipitación acumulada en un rango de fechas. Las fechas de inicio
     * y fin se incluyen en el rango.
     *
     * @param desde fecha de inicio del rango
     * @param hasta fecha de fin del rango
     */
    public void mostrarRanking(LocalDate desde, LocalDate hasta) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("RANKING DE ESTACIONES POR VOLUMETRÍA");
        System.out.println("---------------------------------");

        if (desde == null || hasta == null || desde.isAfter(hasta)) {
            System.out.println("Rango de fechas inválido.");
            return;
        }

        System.out.println("Período: " + desde.format(formato)
                + " al " + hasta.format(formato));

        List<EstacionMeteorologica> estaciones = this.ranking.rankingPorVolumetria(desde, hasta);

        if (estaciones.isEmpty()) {
            System.out.println("No hay estaciones registradas.");
            return;
        }

        int posicion = 1;
        for (EstacionMeteorologica e : estaciones) {
            double mm = this.ranking.calcularAcumulado(e, desde, hasta);
            System.out.println(posicion + " | " + e.getNombre()
                    + " (id " + e.getIdEstacion() + ") | "
                    + String.format("%.2f", mm) + " mm");
            posicion++;
        }
    }
}
