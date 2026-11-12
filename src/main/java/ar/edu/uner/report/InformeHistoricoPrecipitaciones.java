/*
 * InformeHistoricoPrecipitaciones.java
 *
 * Version 1.1
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Informe del histórico de precipitaciones de una estación o de una zona.
 * Calcula los milímetros acumulados diarios, semanales y mensuales y
 * devuelve los datos; la presentación en pantalla está a cargo de
 * GestionInformeEstaciones.
 * Las semanas son de 7 días, contadas desde el 1 de enero de cada año.
 *
 * @author Jmanuel (Grupo-7)
 */
public class InformeHistoricoPrecipitaciones {

    /**
     * Reúne las mediciones de todos los sensores de una estación.
     *
     * @param estacion estación de la que se obtienen las mediciones
     * @return lista con todas las mediciones de la estación
     */
    public List<Medicion> medicionesDeEstacion(EstacionMeteorologica estacion) {
        List<Medicion> res = new ArrayList<>();
        for (Sensor s : estacion.getSensores()) {
            res.addAll(s.getMediciones());
        }
        return res;
    }

    /**
     * Reúne las mediciones de todas las estaciones de una zona.
     *
     * @param zona zona de la que se obtienen las mediciones
     * @return lista con todas las mediciones de la zona
     */
    public List<Medicion> medicionesDeZona(ZonaGeografica zona) {
        List<Medicion> res = new ArrayList<>();
        for (EstacionMeteorologica e : zona.getEstacionesMeteorologicas()) {
            res.addAll(medicionesDeEstacion(e));
        }
        return res;
    }

    /**
     * Obtiene los días que tienen lecturas.
     *
     * @param mediciones mediciones a revisar
     * @return fechas sin repetir, de la más antigua a la más nueva
     */
    public List<LocalDate> diasConLecturas(List<Medicion> mediciones) {
        List<LocalDate> fechas = new ArrayList<>();
        for (Medicion m : mediciones) {
            LocalDate f = m.getFechaHora().toLocalDate();
            if (!fechas.contains(f)) {
                fechas.add(f);
            }
        }
        Collections.sort(fechas);
        return fechas;
    }

    /**
     * Calcula los milímetros acumulados en un día.
     *
     * @param mediciones mediciones a acumular
     * @param fecha día a acumular
     * @return milímetros acumulados en ese día
     */
    public double acumuladoDelDia(List<Medicion> mediciones, LocalDate fecha) {
        double total = 0;
        for (Medicion m : mediciones) {
            if (m.getFechaHora().toLocalDate().equals(fecha)) {
                total += m.getMilimetros();
            }
        }
        return total;
    }

    /**
     * Obtiene las semanas que tienen lecturas. Cada semana se identifica
     * con año * 100 + semana (por ejemplo, 202645 es la semana 45 de 2026).
     *
     * @param mediciones mediciones a revisar
     * @return claves de semana sin repetir, de la más antigua a la más nueva
     */
    public List<Integer> semanasConLecturas(List<Medicion> mediciones) {
        List<Integer> semanas = new ArrayList<>();
        for (Medicion m : mediciones) {
            int semana = claveSemana(m);
            if (!semanas.contains(semana)) {
                semanas.add(semana);
            }
        }
        Collections.sort(semanas);
        return semanas;
    }

    /**
     * Calcula los milímetros acumulados en una semana.
     *
     * @param mediciones mediciones a acumular
     * @param semana clave de la semana (año * 100 + semana)
     * @return milímetros acumulados en esa semana
     */
    public double acumuladoDeLaSemana(List<Medicion> mediciones, int semana) {
        double total = 0;
        for (Medicion m : mediciones) {
            if (claveSemana(m) == semana) {
                total += m.getMilimetros();
            }
        }
        return total;
    }

    /**
     * Obtiene los meses que tienen lecturas. Cada mes se identifica con
     * año * 100 + mes (por ejemplo, 202611 es noviembre de 2026).
     *
     * @param mediciones mediciones a revisar
     * @return claves de mes sin repetir, del más antiguo al más nuevo
     */
    public List<Integer> mesesConLecturas(List<Medicion> mediciones) {
        List<Integer> meses = new ArrayList<>();
        for (Medicion m : mediciones) {
            int mes = claveMes(m);
            if (!meses.contains(mes)) {
                meses.add(mes);
            }
        }
        Collections.sort(meses);
        return meses;
    }

    /**
     * Calcula los milímetros acumulados en un mes.
     *
     * @param mediciones mediciones a acumular
     * @param mes clave del mes (año * 100 + mes)
     * @return milímetros acumulados en ese mes
     */
    public double acumuladoDelMes(List<Medicion> mediciones, int mes) {
        double total = 0;
        for (Medicion m : mediciones) {
            if (claveMes(m) == mes) {
                total += m.getMilimetros();
            }
        }
        return total;
    }

    /**
     * Busca el valor histórico de un mes en una zona.
     *
     * @param zona zona con los valores históricos mensuales
     * @param mes mes del año (1 a 12)
     * @return el valor histórico del mes, o null si la zona es null o no
     *         tiene cargado ese mes
     */
    public ValorHistorico buscarValorHistorico(ZonaGeografica zona, int mes) {
        ValorHistorico encontrado = null;
        if (zona != null) {
            for (ValorHistorico v : zona.getValoresHistoricos()) {
                if (v.getMes() == mes) {
                    encontrado = v;
                    break;
                }
            }
        }
        return encontrado;
    }

    /**
     * Calcula un número que identifica la semana de una medición:
     * año * 100 + semana, con semanas de 7 días desde el 1 de enero.
     *
     * @param m medición de la que se obtiene la semana
     * @return número que identifica el año y la semana
     */
    private int claveSemana(Medicion m) {
        LocalDate f = m.getFechaHora().toLocalDate();
        int semana = (f.getDayOfYear() - 1) / 7 + 1;
        return f.getYear() * 100 + semana;
    }

    /**
     * Calcula un número que identifica el mes de una medición:
     * año * 100 + mes.
     *
     * @param m medición de la que se obtiene el mes
     * @return número que identifica el año y el mes
     */
    private int claveMes(Medicion m) {
        LocalDate f = m.getFechaHora().toLocalDate();
        return f.getYear() * 100 + f.getMonthValue();
    }
}
