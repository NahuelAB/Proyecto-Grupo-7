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
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Muestra por pantalla la ficha técnica operativa de una estación
 * meteorológica, a partir de los datos que obtiene {@link FichaTecnica}.
 *
 * @author Maricela
 */
public class GestionInformeFichaTecnica {
    private FichaTecnica ficha;

    /**
     * Crea la gestión del informe a partir de la gestión de infraestructura
     * del sistema.
     *
     * @param infraestructura gestión de infraestructura del sistema
     */
    public GestionInformeFichaTecnica(GestionInfraestructura infraestructura) {
        this.ficha = new FichaTecnica(infraestructura);
    }

    /**
     * Muestra la ficha técnica operativa de una estación: sus datos, el
     * responsable técnico a cargo, y el estado y las últimas lecturas de
     * cada uno de sus sensores.
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
                + estacion.getNombre() + " | " + estacion.getEstado());

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