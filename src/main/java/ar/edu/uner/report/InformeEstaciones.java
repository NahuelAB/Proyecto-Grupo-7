/*
 * InformeEstaciones.java
 *
 * Version 1.0
 *
 * 09/11/2026
 *
 * Copyright (c) 2026 Grupo 7 - UNER
 */
package ar.edu.uner.report;

import ar.edu.uner.domain.model.EstacionMeteorologica;
import ar.edu.uner.domain.model.Medicion;
import ar.edu.uner.domain.model.Sensor;
import ar.edu.uner.service.GestionInfraestructura;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Genera informes sobre las estaciones meteorológicas: el ranking de
 * estaciones por volumetría y la ficha técnica operativa.
 *
 * @author Maricela
 */
public class InformeEstaciones {
    private GestionInfraestructura infraestructura;

    /**
     * Crea el informe a partir de la gestión de infraestructura, de donde
     * obtiene las estaciones registradas.
     *
     * @param infraestructura gestión de infraestructura del sistema
     */
    public InformeEstaciones(GestionInfraestructura infraestructura) {
        this.infraestructura = infraestructura;
    }
    
        /**
     * Calcula la precipitación acumulada de una estación en un rango de
     * fechas, sumando las mediciones de todos sus sensores. Las fechas
     * de inicio y fin se incluyen en el rango.
     *
     * @param estacion la estación cuya precipitación se calcula
     * @param desde fecha de inicio del rango
     * @param hasta fecha de fin del rango
     * @return los milímetros acumulados en el rango indicado
     */
    public double calcularAcumulado(EstacionMeteorologica estacion, LocalDate desde, LocalDate hasta) {
    double total =0;
    for (Sensor s : estacion.getSensores()) {
        for (Medicion m : s.getMediciones()) {
            LocalDate fecha = m.getFechaHora().toLocalDate();
            if (!fecha.isBefore(desde) && !fecha.isAfter(hasta)) {
                total = total + m.getMilimetros();
            }
        }
    }
    return total;
}
    
        /**
     * Devuelve las estaciones ordenadas de mayor a menor según la
     * precipitación acumulada en un rango de fechas. El acumulado de cada
     * estación se obtiene con
     * {@link #calcularAcumulado(EstacionMeteorologica, LocalDate, LocalDate)}.
     *
     * @param desde fecha de inicio del rango
     * @param hasta fecha de fin del rango
     * @return lista de estaciones ordenada por precipitación acumulada,
     *         de mayor a menor
     */
    public List<EstacionMeteorologica> rankingPorVolumetria(LocalDate desde, LocalDate hasta) {
        List<EstacionMeteorologica> ranking = new ArrayList<>(this.infraestructura.getEstaciones());
        ComparadorVolumetria comparador = new ComparadorVolumetria(this, desde, hasta);
        Collections.sort(ranking, comparador);
        return ranking;
    }
    
}

