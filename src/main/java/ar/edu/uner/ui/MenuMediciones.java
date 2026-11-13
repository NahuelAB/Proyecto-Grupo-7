/*
 * MenuMediciones.java
 *
 * Version 1.0
 *
 * 12/11/2026
 *
 * Copyright (c) 2026 Grupo 7 - UNER
 */
package ar.edu.uner.ui;

import ar.edu.uner.domain.model.Medicion;
import ar.edu.uner.domain.model.Sensor;
import ar.edu.uner.domain.model.ZonaGeografica;
import ar.edu.uner.exception.LecturaInvalidaException;
import ar.edu.uner.service.GestionInfraestructura;
import ar.edu.uner.service.GestionMediciones;
import ar.edu.uner.service.GestionSistemaAlerta;
import ar.edu.uner.service.LectorFechas;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import ar.edu.uner.service.UtilidadesMenu;

/**
 * Menú de mediciones: permite registrar lecturas de precipitación, ver las
 * lecturas de un sensor y consultar la lluvia acumulada de una zona.
 *
 * @author Maricela
 */
public class MenuMediciones implements Ejecutable {
    private GestionInfraestructura gestorInfraestructura;
    private GestionMediciones gestorMediciones;
    private GestionSistemaAlerta gestorAlertas;
    private Scanner scanner;
    private DateTimeFormatter formato;

    /**
     * Crea el menú de mediciones.
     *
     * @param gestorInfraestructura gestión de infraestructura del sistema
     * @param scanner scanner compartido para leer los datos del usuario
     */
    public MenuMediciones(GestionMediciones gestorMediciones, GestionInfraestructura gestorInfraestructura,
                         GestionSistemaAlerta gestorAlerta, Scanner scanner) {
        this.gestorMediciones = gestorMediciones;
        this.gestorInfraestructura = gestorInfraestructura;
        this.gestorAlertas = gestorAlerta;
        this.scanner = scanner;
        this.formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    }

    /**
     * Muestra las opciones del menú de mediciones.
     */
    public void mostrarOpciones() {
        System.out.println();
        System.out.println("===== MEDICIONES =====");
        System.out.println("1. Registrar una lectura");
        System.out.println("2. Ver lecturas de un sensor");
        System.out.println("3. Consultar lluvia de una zona");
        System.out.println("0. Volver");
    }

    /**
     * Lee un número entero ingresado por el usuario y limpia el buffer.
     *
     * @param mensaje texto que se muestra antes de leer
     * @return el número ingresado, si lo ingresado no es un número lo vuelve a pedir
     */
    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return UtilidadesMenu.leerOpcion(scanner) ;
    }

    /**
     * Ejecuta el menú hasta que el usuario elige volver.
     */
    @Override
    public void ejecutar() {
        int opcion;

        do {
            mostrarOpciones();
            opcion = leerEntero("Elija una opción: ");

            switch (opcion) {
                case 1:
                    registrarLectura();
                    break;
                case 2:
                    verLecturasSensor();
                    break;
                case 3:
                    consultarLluviaZona();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        } while (opcion != 0);
    }

    /**
     * Pide los datos de una lectura y la registra. Informa si la lectura
     * fue rechazada (valor fuera de rango, sensor inexistente o no
     * operativo) y si quedó marcada como anómala.
     */
    private void registrarLectura() {
        System.out.println("REGISTRAR LECTURA");
        System.out.println("---------------------------------");

        int idSensor = leerEntero("Id del sensor: ");

        System.out.print("Milímetros: ");
        double mm = UtilidadesMenu.leerDouble(scanner);

        System.out.print("¿Usar la fecha y hora actual? (s/n): ");
        String respuesta = scanner.nextLine();
        LocalDateTime fechaHora;
        if (respuesta.equalsIgnoreCase("n")) {
            fechaHora = LectorFechas.leerFechaHora(scanner);
        } else {
            fechaHora = LocalDateTime.now();
        }

        try {
            Medicion m = gestorMediciones.registrarLectura(idSensor, mm, fechaHora);
            if (m == null) {
                System.out.println("No se registró la lectura: el sensor no existe, "
                        + "no está instalado en una estación, o el sensor o la "
                        + "estación no están operativos.");
            } else {
                System.out.println("Lectura registrada: " + m.getFechaHora().format(formato)
                        + " | " + String.format("%.2f", m.getMilimetros()) + " mm");
                if (m.isAnomala()) {
                    System.out.println("ATENCIÓN: la lectura quedó marcada como anómala.");
                }
            }
        } catch (LecturaInvalidaException ex) {
            System.out.println("Lectura inválida: " + ex.getMessage());
        }
    }

    /**
     * Muestra todas las lecturas de un sensor, indicando cuáles son anómalas.
     */
    private void verLecturasSensor() {
        System.out.println("LECTURAS DE UN SENSOR");
        System.out.println("---------------------------------");

        int idSensor = leerEntero("Id del sensor: ");
        Sensor s = gestorInfraestructura.buscarSensor(idSensor);

        if (s == null) {
            System.out.println("No existe un sensor con id " + idSensor + ".");
            return;
        }

        List<Medicion> mediciones = s.getMediciones();
        if (mediciones.isEmpty()) {
            System.out.println("El sensor " + idSensor + " no tiene lecturas.");
            return;
        }

        for (Medicion m : mediciones) {
            String linea = m.getFechaHora().format(formato)
                    + " | " + String.format("%.2f", m.getMilimetros()) + " mm";
            if (m.isAnomala()) {
                linea = linea + " | ANÓMALA";
            }
            System.out.println(linea);
        }
    }

    /**
     * Muestra la lluvia acumulada en las últimas 24 horas de una zona y su
     * nivel de alerta actual. Solo consulta: no evalúa el riesgo ni emite
     * alertas.
     */
    private void consultarLluviaZona() {
        System.out.println("LLUVIA DE UNA ZONA");
        System.out.println("---------------------------------");

        int idZona = leerEntero("Id de la zona: ");
        ZonaGeografica zona = gestorInfraestructura.buscarZona(idZona);

        if (zona == null) {
            System.out.println("No existe una zona con id " + idZona + ".");
            return;
        }

        System.out.println("Zona " + zona.getIdZona() + " | " + zona.getNombre()
                + " | Lluvia últimas 24 hs: "
                + String.format("%.2f", zona.lluviaAcumulada24hs()) + " mm"
                + " | Nivel actual: " + zona.getNivelAlertaActual());
    }
}