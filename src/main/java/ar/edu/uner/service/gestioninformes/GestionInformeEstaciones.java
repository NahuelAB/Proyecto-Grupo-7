/*
 * GestionInformeEstaciones.java
 *
 * Version 1.1
 *
 * 12/11/2026
 *
 * Copyright (c) 2026 Grupo 7 - UNER
 */
package ar.edu.uner.service.gestioninformes;

import ar.edu.uner.domain.model.EstacionMeteorologica;
import ar.edu.uner.domain.model.Medicion;
import ar.edu.uner.domain.model.ValorHistorico;
import ar.edu.uner.domain.model.ZonaGeografica;
import ar.edu.uner.report.InformeHistoricoPrecipitaciones;
import ar.edu.uner.service.GestionInfraestructura;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static ar.edu.uner.service.UtilidadesMenu.leerOpcion;
import static ar.edu.uner.service.UtilidadesMenu.listarEstaciones;
import static ar.edu.uner.service.UtilidadesMenu.listarZonas;

/**
 * Presenta en pantalla el histórico de precipitaciones de una estación o de
 * una zona. Los cálculos los realiza InformeHistoricoPrecipitaciones; esta
 * clase solo imprime los datos que recibe.
 *
 * @author Jmanuel (Grupo-7)
 */
public class GestionInformeEstaciones {

    private Scanner sc;
    private GestionInfraestructura infraestructura;
    private InformeHistoricoPrecipitaciones informe;

    /**
     * Crea el gestor del informe de precipitaciones.
     *
     * @param sc lector de entrada por consola
     * @param infraestructura gestor de infraestructura (se usa para buscar
     *                        estaciones y zonas)
     * @param informe informe que calcula los acumulados
     */
    public GestionInformeEstaciones(Scanner sc,
                                    GestionInfraestructura infraestructura,
                                    InformeHistoricoPrecipitaciones informe) {
        this.sc = sc;
        this.infraestructura = infraestructura;
        this.informe = informe;
    }

    /**
     * Pregunta si se quiere el histórico de una estación o de una zona,
     * pide el identificador y muestra el informe.
     */
    public void mostrar() {
        System.out.println("1. Historico por estacion");
        System.out.println("2. Historico por zona");
        System.out.println("0. Volver");
        int opcion = leerOpcion(sc);

        switch (opcion) {
            case 1:
                listarEstaciones(infraestructura);
                System.out.print("ID de la estación: ");
                mostrarPorEstacion(infraestructura.buscarEstacion(leerOpcion(sc)));
                break;
            case 2:
                listarZonas(infraestructura);
                System.out.print("ID de la zona: ");
                mostrarPorZona(infraestructura.buscarZona(leerOpcion(sc)));
                break;
            case 0:
                break;
            default:
                System.out.println("Opcion invalida");
        }
    }

    /**
     * Muestra el histórico de precipitaciones de una estación. Los
     * acumulados mensuales se comparan con la media histórica de la zona
     * a la que pertenece la estación.
     *
     * @param estacion estación a informar
     */
    public void mostrarPorEstacion(EstacionMeteorologica estacion) {
        if (estacion == null) {
            System.out.println("Estación inexistente.");
            return;
        }
        System.out.println("HISTÓRICO DE PRECIPITACIONES - ESTACIÓN "
                + estacion.getNombre());
        System.out.println("-----------------------------------------");
        ZonaGeografica zona = infraestructura.buscarZonaDeEstacion(estacion);
        mostrarAcumulados(informe.medicionesDeEstacion(estacion), zona);
    }

    /**
     * Muestra el histórico de precipitaciones de una zona. Los acumulados
     * mensuales se comparan con la media histórica de la zona.
     *
     * @param zona zona a informar
     */
    public void mostrarPorZona(ZonaGeografica zona) {
        if (zona == null) {
            System.out.println("Zona inexistente.");
            return;
        }
        System.out.println("HISTÓRICO DE PRECIPITACIONES - ZONA "
                + zona.getNombre());
        System.out.println("-----------------------------------------");
        mostrarAcumulados(informe.medicionesDeZona(zona), zona);
    }

    /**
     * Imprime los acumulados diarios, semanales y mensuales.
     *
     * @param mediciones mediciones a acumular
     * @param zona zona cuya media histórica se usa para comparar los meses
     */
    private void mostrarAcumulados(List<Medicion> mediciones,
                                   ZonaGeografica zona) {
        if (mediciones.isEmpty()) {
            System.out.println("No hay lecturas registradas.");
            return;
        }

        System.out.println("Acumulado diario:");
        for (LocalDate dia : informe.diasConLecturas(mediciones)) {
            System.out.println("  " + dia + " | "
                    + informe.acumuladoDelDia(mediciones, dia) + " mm");
        }

        System.out.println("Acumulado semanal:");
        for (int semana : informe.semanasConLecturas(mediciones)) {
            System.out.println("  Año " + semana / 100 + ", semana "
                    + semana % 100 + " | "
                    + informe.acumuladoDeLaSemana(mediciones, semana) + " mm");
        }

        System.out.println("Acumulado mensual (comparado con la media histórica):");
        for (int clave : informe.mesesConLecturas(mediciones)) {
            int mes = clave % 100;
            double acumulado = informe.acumuladoDelMes(mediciones, clave);
            ValorHistorico historico = informe.buscarValorHistorico(zona, mes);

            String linea = "  " + mes + "/" + clave / 100 + " | "
                    + acumulado + " mm";
            if (historico == null) {
                linea += " | sin media histórica cargada";
            } else {
                linea += " | media histórica: "
                        + historico.getAcumuladoMedio() + " mm";
            }
            System.out.println(linea);
        }
    }
}
