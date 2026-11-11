/*
 * InformeHistoricoPrecipitaciones.java
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Informe del histórico de precipitaciones de una estación o de una zona:
 * milímetros acumulados diarios, semanales y mensuales. Los acumulados
 * mensuales se comparan con la media histórica mensual de la zona.
 * Las semanas son de 7 días, contadas desde el 1 de enero de cada año.
 *
 * @author Jmanuel (Grupo-7)
 */
public class InformeHistoricoPrecipitaciones {

    /**
     * Muestra el histórico de precipitaciones de una estación. El acumulado
     * mensual se compara con la media histórica mensual de la zona a la
     * que pertenece la estación.
     *
     * @param estacion estación a informar
     * @param zona zona de la estación, con la media histórica mensual
     */
    public void mostrarEstacion(EstacionMeteorologica estacion, ZonaGeografica zona) {
        System.out.println("HISTORICO DE PRECIPITACIONES - ESTACION " + estacion.getNombre());
        System.out.println("---------------------------------");

        List<Medicion> mediciones = medicionesDe(List.of(estacion));
        mostrarDiario(mediciones);
        mostrarSemanal(mediciones);
        mostrarMensual(mediciones, zona);
    }

    /**
     * Muestra el histórico de precipitaciones de una zona, sumando todas
     * sus estaciones. El acumulado mensual se compara con la media
     * histórica mensual de la zona.
     *
     * @param zona zona a informar
     */
    public void mostrarZona(ZonaGeografica zona) {
        System.out.println("HISTORICO DE PRECIPITACIONES - ZONA " + zona.getNombre());
        System.out.println("---------------------------------");

        List<Medicion> mediciones = medicionesDe(zona.getEstacionesMeteorologicas());
        mostrarDiario(mediciones);
        mostrarSemanal(mediciones);
        mostrarMensual(mediciones, zona);
    }

    /**
     * Reúne las mediciones de todos los sensores de las estaciones indicadas.
     *
     * @param estaciones estaciones cuyas mediciones se reúnen
     * @return lista con todas las mediciones
     */
    private List<Medicion> medicionesDe(List<EstacionMeteorologica> estaciones) {
        List<Medicion> res = new ArrayList<>();
        for (EstacionMeteorologica e : estaciones) {
            for (Sensor s : e.getSensores()) {
                res.addAll(s.getMediciones());
            }
        }
        return res;
    }

    /**
     * Calcula un número que identifica la semana de una medición. Las
     * semanas son de 7 días contadas desde el 1 de enero. El número es
     * año * 100 + semana (por ejemplo, 202645 es la semana 45 de 2026),
     * así se pueden ordenar de la más antigua a la más nueva.
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
     * Calcula un número que identifica el mes de una medición. El número
     * es año * 100 + mes (por ejemplo, 202611 es noviembre de 2026), así
     * se pueden ordenar del más antiguo al más nuevo.
     *
     * @param m medición de la que se obtiene el mes
     * @return número que identifica el año y el mes
     */
    private int claveMes(Medicion m) {
        LocalDate f = m.getFechaHora().toLocalDate();
        return f.getYear() * 100 + f.getMonthValue();
    }

    /**
     * Muestra los milímetros acumulados por día, de la fecha más antigua
     * a la más nueva.
     *
     * @param mediciones mediciones a acumular
     */
    private void mostrarDiario(List<Medicion> mediciones) {
        System.out.println("Acumulado diario: ");
        if (mediciones.isEmpty()) {
            System.out.println("sin lecturas.");
            return;
        }

        List<LocalDate> fechas = new ArrayList<>();
        for (Medicion m : mediciones) {
            LocalDate f = m.getFechaHora().toLocalDate();
            if (!fechas.contains(f)) {
                fechas.add(f);
            }
        }
        Collections.sort(fechas);

        for (LocalDate f : fechas) {
            double total = 0;
            for (Medicion m : mediciones) {
                if (m.getFechaHora().toLocalDate().equals(f)) {
                    total += m.getMilimetros();
                }
            }
            System.out.println(f + " | " + total + " mm");
        }
    }

    /**
     * Muestra los milímetros acumulados por semana, de la más antigua a
     * la más nueva.
     *
     * @param mediciones mediciones a acumular
     */
    private void mostrarSemanal(List<Medicion> mediciones) {
        System.out.println("Acumulado semanal: ");
        if (mediciones.isEmpty()) {
            System.out.println("sin lecturas.");
            return;
        }

        List<Integer> semanas = new ArrayList<>();
        for (Medicion m : mediciones) {
            int semana = claveSemana(m);
            if (!semanas.contains(semana)) {
                semanas.add(semana);
            }
        }
        Collections.sort(semanas);

        for (int semana : semanas) {
            double total = 0;
            for (Medicion m : mediciones) {
                if (claveSemana(m) == semana) {
                    total += m.getMilimetros();
                }
            }
            System.out.println((semana / 100) + "-S" + String.format("%02d", semana % 100)
                    + " | " + total + " mm");
        }
    }

    /**
     * Muestra los milímetros acumulados por mes, del más antiguo al más
     * nuevo, junto con la media histórica de ese mes en la zona para
     * compararla. Si la zona no tiene cargada la media de un mes, lo indica.
     *
     * @param mediciones mediciones a acumular
     * @param zona zona con los valores históricos mensuales
     */
    private void mostrarMensual(List<Medicion> mediciones, ZonaGeografica zona) {
        System.out.println("Acumulado mensual: ");
        if (mediciones.isEmpty()) {
            System.out.println("sin lecturas.");
            return;
        }

        List<Integer> meses = new ArrayList<>();
        for (Medicion m : mediciones) {
            int mes = claveMes(m);
            if (!meses.contains(mes)) {
                meses.add(mes);
            }
        }
        Collections.sort(meses);

        for (int mes : meses) {
            double total = 0;
            for (Medicion m : mediciones) {
                if (claveMes(m) == mes) {
                    total += m.getMilimetros();
                }
            }

            String media = " | sin media historica";
            for (ValorHistorico v : zona.getValoresHistoricos()) {
                if (v.getMes() == mes % 100) {
                    media = " | media historica: " + v.getAcumuladoMedio() + " mm";
                }
            }
            System.out.println((mes / 100) + "-" + String.format("%02d", mes % 100)
                    + " | " + total + " mm" + media);
        }
    }
}
