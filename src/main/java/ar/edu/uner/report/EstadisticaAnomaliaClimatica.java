/*
 * EstadisticaAnomaliaClimatica.java
 *
 * Version 1.0
 *
 * 11/11/2026
 *
 * Copyright (c) 2026 Grupo 7 - UNER
 */
package ar.edu.uner.report;

import ar.edu.uner.domain.model.EstacionMeteorologica;
import ar.edu.uner.domain.model.Medicion;
import ar.edu.uner.domain.model.Sensor;
import ar.edu.uner.domain.model.ValorHistorico;
import ar.edu.uner.domain.model.ZonaGeografica;


/**
 * Informe de estadísticas de anomalía climática. Calcula, para una zona
 * geográfica y un mes determinado, el acumulado de precipitación y su desvío
 * porcentual respecto de la media histórica mensual de la zona. Esta clase
 * solo realiza cálculos y consultas: la presentación de los resultados está a
 * cargo de la gestión del informe.
 *
 * @author Guillermina
 */
public class EstadisticaAnomaliaClimatica {

    /**
     * Crea el informe de estadísticas de anomalía climática.
     */
    public EstadisticaAnomaliaClimatica() {}

    /**
     * Cuenta las mediciones registradas por una zona en un mes y año
     * determinados, considerando los sensores de todas sus estaciones.
     *
     * @param z Zona geográfica a consultar.
     * @param anio Año de las mediciones.
     * @param mes Mes de las mediciones (1 a 12).
     * @return Cantidad de mediciones de la zona en ese mes; 0 si no hay ninguna.
     */
    public int cantidadMediciones(ZonaGeografica z, int anio, int mes) {
        int cantidad = 0;
        for (EstacionMeteorologica e : z.getEstacionesMeteorologicas()) {
            for (Sensor s : e.getSensores()) {
                for (Medicion m : s.getMediciones()) {
                    if (m.getFechaHora().getYear() == anio &&
                            m.getFechaHora().getMonthValue() == mes) {
                        cantidad++;
                    }
                }
            }
        }
        return cantidad;
    }

    /**
     * Calcula los milímetros de precipitación acumulados por una zona en un
     * mes y año determinados, sumando las mediciones de los sensores de todas
     * sus estaciones.
     *
     * @param z Zona geográfica a consultar.
     * @param anio Año de las mediciones.
     * @param mes Mes de las mediciones (1 a 12).
     * @return Milímetros acumulados en el mes; 0 si no hay mediciones.
     */
    public double acumuladoDelMes(ZonaGeografica z, int anio, int mes) {
        double acumulado = 0;
        for (EstacionMeteorologica e : z.getEstacionesMeteorologicas()) {
            for (Sensor s : e.getSensores()) {
                for (Medicion m : s.getMediciones()) {
                    if (m.getFechaHora().getYear() == anio 
                            && m.getFechaHora().getMonthValue() == mes) {
                        acumulado+= m.getMilimetros();
                    }
                }
            }
        }
        return acumulado;
    }

    /**
     * Busca el valor histórico de una zona correspondiente a un mes.
     *
     * @param z Zona geográfica a consultar.
     * @param mes Mes buscado (1 a 12).
     * @return El valor histórico de ese mes; null si la zona no tiene uno cargado.
     */
    public ValorHistorico buscarValorHistorico(ZonaGeografica z, int mes) {
        ValorHistorico encontrado = null;
        for (ValorHistorico v : z.getValoresHistoricos()) {
            if (v.getMes() == mes) {
                encontrado = v;
                break;
            }
        }
        return encontrado;
    }

    /**
     * Calcula el desvío porcentual del acumulado de un mes respecto de la
     * media histórica: {@code (acumulado - media) / media * 100}. Un resultado
     * positivo indica que llovió más que lo habitual y uno negativo, menos.
     * La media debe ser mayor que cero; con media cero el resultado no es un
     * número válido.
     *
     * @param acumulado Milímetros acumulados en el mes.
     * @param media Acumulado medio histórico del mismo mes, en milímetros.
     * @return Desvío porcentual del acumulado respecto de la media.
     */
    public double calcularDesvio(double acumulado, double media) {
        return (acumulado - media) / media * 100;
    }
}
