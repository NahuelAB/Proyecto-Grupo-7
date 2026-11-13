/*
 * GestionInformeFichaTecnica.java
 *
 * Version 1.0
 *
 * 11/11/2026
 *
 * Copyright (c) 2026 Grupo 7 - UNER
 */
package ar.edu.uner.service.gestioninformes;

import ar.edu.uner.domain.model.EstacionMeteorologica;
import ar.edu.uner.domain.model.Medicion;
import ar.edu.uner.domain.model.Sensor;
import ar.edu.uner.report.FichaTecnica;
import ar.edu.uner.service.GestionInfraestructura;
import ar.edu.uner.service.UtilidadesMenu;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * Muestra por pantalla la ficha técnica operativa de una estación
 * meteorológica, a partir de los datos que obtiene {@link FichaTecnica}.
 *
 * @author Maricela
 */
public class GestionInformeFichaTecnica {

    private GestionInfraestructura infraestructura;
    private FichaTecnica ficha;
    private Scanner scanner;

    /**
     * Crea la gestión del informe de ficha técnica operativa.
     *
     * @param infraestructura gestión de infraestructura del sistema, usada para
     * listar las estaciones disponibles
     * @param ficha informe que obtiene los datos de la ficha técnica
     * @param scanner scanner compartido para leer los datos del usuario
     */
    public GestionInformeFichaTecnica(GestionInfraestructura infraestructura,
            FichaTecnica ficha, Scanner scanner) {
        this.infraestructura = infraestructura;
        this.ficha = ficha;
        this.scanner = scanner;
    }

    /**
     * Muestra las estaciones registradas, pide al usuario el identificador de
     * una de ellas y muestra su ficha técnica operativa.
     */
    public void mostrar() {
        UtilidadesMenu.mostrarTodasLasEstaciones(infraestructura);
        System.out.print("Id de la estación: ");
        int idEstacion = UtilidadesMenu.leerOpcion(scanner);
        mostrarFicha(idEstacion);
    }

    /**
     * Muestra la ficha técnica operativa de una estación: sus datos, el
     * responsable técnico a cargo, y el estado y las últimas lecturas de cada
     * uno de sus sensores.
     *
     * @param idEstacion identificador de la estación
     */
    public void mostrarFicha(int idEstacion) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("FICHA TÉCNICA OPERATIVA");
        System.out.println("---------------------------------");

        EstacionMeteorologica estacion = this.ficha.obtenerEstacion(idEstacion);
        if (estacion == null) {
            System.out.println("No existe una estación con id " + idEstacion + ".");
            return;
        }

        System.out.println("Estación " + estacion.getIdEstacion() + " | "
                + estacion.getNombre() + " | " + estacion.getEstadoOp());

        if (estacion.getResponsable() != null) {
            System.out.println("Responsable a cargo: " + estacion.getResponsable());
        } else {
            System.out.println("Responsable a cargo: sin responsable asignado");
        }

        System.out.println();
        System.out.println("SENSORES");
        System.out.println("---------------------------------");

        if (estacion.getSensores().isEmpty()) {
            System.out.println("Sin sensores instalados.");
            return;
        }

        for (Sensor s : estacion.getSensores()) {
            System.out.println("Sensor " + s.getIdSensor() + " | "
                    + s.getMarca() + " " + s.getModelo() + " | " + s.getEstado());

            List<Medicion> lecturas = this.ficha.ultimasLecturas(s);
            if (lecturas.isEmpty()) {
                System.out.println("    Sin lecturas");
            } else {
                for (Medicion m : lecturas) {
                    System.out.println("    " + m.getFechaHora().format(formato)
                            + " | " + String.format("%.2f", m.getMilimetros()) + " mm");
                }
            }
        }
    }
}
