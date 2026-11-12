/*
 * UtilidadesMenu.java
 * Version 1.0
 * 11/11/2026
 * Copyright (c) 2026 Grupo 7 - UNER
 */

package ar.edu.uner.service;

import ar.edu.uner.domain.enums.EstadoOperativo;
import ar.edu.uner.domain.enums.NivelAlerta;
import ar.edu.uner.domain.enums.NivelVulnerabilidad;
import ar.edu.uner.domain.enums.TipoEstacion;
import ar.edu.uner.domain.model.EstacionMeteorologica;
import ar.edu.uner.domain.model.Sensor;
import ar.edu.uner.domain.model.ValorHistorico;
import ar.edu.uner.domain.model.ZonaGeografica;

import java.util.List;
import java.util.Scanner;

/**
 * Clase utilitaria que agrupa métodos estáticos auxiliares para la lectura de
 * opciones por consola y la visualización de zonas geográficas, estaciones
 * meteorológicas y sensores.
 *
 * @author Nahuel
 */
public class UtilidadesMenu {

    /**
     * Constructor privado para evitar la instanciación de la clase utilitaria.
     */
    private UtilidadesMenu() {}

    /**
     * Lee una opción numérica entera desde la entrada estándar y limpia el buffer.
     *
     * @param scanner lector de entrada estándar
     * @return número de opción seleccionado por el usuario
     */
    public static int leerOpcion(Scanner scanner) {
        int opcion = scanner.nextInt();
        scanner.nextLine();

        return opcion;
    }

    /**
     * Lee una opción numérica por consola y la convierte en un nivel de
     * vulnerabilidad, asignando {@code BAJO} por defecto ante un valor inválido.
     *
     * @param scanner lector de entrada estándar
     * @return constante de {@link NivelVulnerabilidad} correspondiente
     */
    public static NivelVulnerabilidad nivelVulni(Scanner scanner) {
        int opt = scanner.nextInt();
        scanner.nextLine();

        NivelVulnerabilidad nivel;

        switch(opt) {
            case 1: nivel = NivelVulnerabilidad.BAJO;break;
            case 2: nivel = NivelVulnerabilidad.MEDIO;break;
            case 3: nivel = NivelVulnerabilidad.ALTO;break;
            case 4: nivel = NivelVulnerabilidad.MUY_ALTO;break;
            default:
                System.out.println("Opción no válida. Se asignó BAJO por defecto.");
                nivel = NivelVulnerabilidad.BAJO;
        }

        return nivel;
    }

    /**
     * Lee una opción numérica por consola y la convierte en un nivel de alerta,
     * asignando {@code VERDE} por defecto ante un valor inválido.
     *
     * @param scanner lector de entrada estándar
     * @return constante de {@link NivelAlerta} correspondiente
     */
    public static NivelAlerta nivelAlerta(Scanner scanner) {
        int opt = scanner.nextInt();
        scanner.nextLine();

        NivelAlerta nivelA;

        switch(opt) {
            case 1: nivelA = NivelAlerta.VERDE;break;
            case 2: nivelA = NivelAlerta.AMARILLA;break;
            case 3: nivelA = NivelAlerta.NARANJA;break;
            case 4: nivelA = NivelAlerta.ROJA;break;
            default:
                System.out.println("Opción no válida. " +
                        "Se asignó VERDE por defecto.");
                nivelA = NivelAlerta.VERDE;
        }

        return nivelA;
    }

    /**
     * Lee una opción numérica por consola y la convierte en un estado operativo,
     * asignando {@code FUERA_DE_SERVICIO} por defecto ante un valor inválido.
     *
     * @param scanner lector de entrada estándar
     * @return constante de {@link EstadoOperativo} correspondiente
     */
    public static EstadoOperativo estadoOp(Scanner scanner) {
        int opt = scanner.nextInt();
        scanner.nextLine();

        EstadoOperativo estadoOp;

        switch(opt) {
            case 1: estadoOp = EstadoOperativo.OPERATIVO;break;
            case 2: estadoOp = EstadoOperativo.EN_MANTENIMIENTO;break;
            case 3: estadoOp = EstadoOperativo.FUERA_DE_SERVICIO;break;
            default:
                System.out.println("Opción no válida. " +
                        "Se asignó Fuera de servicio por defecto.");
                estadoOp = EstadoOperativo.FUERA_DE_SERVICIO;
        }

        return estadoOp;
    }

    /**
     * Lee una opción numérica por consola y la convierte en un tipo de estación,
     * asignando {@code CONVENCIONAL} por defecto ante un valor inválido.
     *
     * @param scanner lector de entrada estándar
     * @return constante de {@link TipoEstacion} correspondiente
     */
    public static TipoEstacion tipoEstacion(Scanner scanner) {
        int opt = scanner.nextInt();
        scanner.nextLine();

        TipoEstacion tipoEstacion;

        switch(opt) {
            case 1: tipoEstacion = TipoEstacion.AUTOMATICA;break;
            case 2: tipoEstacion = TipoEstacion.CONVENCIONAL;break;
            default:
                System.out.println("Opción no válida. " +
                        "Se asignó CONVECIONAL por defecto.");
                tipoEstacion = TipoEstacion.CONVENCIONAL;
        }

        return tipoEstacion;
    }

    /**
     * Muestra por consola el nombre e identificador de las zonas geográficas
     * registradas en el sistema.
     *
     * @param gestor gestor central de infraestructura
     * @return {@code true} si existe al menos una zona registrada; {@code false} en caso contrario
     */
    public static boolean mostrarZonas(GestionInfraestructura gestor) {
        List<ZonaGeografica> zonas = gestor.getZonas();

        if (zonas.isEmpty()) {
            System.out.println("No hay zonas registradas. Registre una primero.");
            return false;
        }

        for (ZonaGeografica zona : zonas) {
            System.out.println("Zona Geografica: " + zona.getNombre()
                    + " ID: " + zona.getIdZona());
        }

        return true;
    }

    /**
     * Muestra por consola los sensores que actualmente se encuentran instalados
     * en alguna estación meteorológica.
     *
     * @param gestor gestor central de infraestructura
     * @return {@code true} si existe al menos un sensor instalado; {@code false} en caso contrario
     */
    public static boolean mostrarSensoresInstalados(GestionInfraestructura gestor) {
        boolean hayInstalados = false;

        System.out.println("--- Sensores Instalados ---");
        for (EstacionMeteorologica estacion : gestor.getEstaciones()) {
            for (Sensor sensor : estacion.getSensores()) {
                System.out.println("ID Sensor: " + sensor.getIdSensor()
                        + " Estación: " + estacion.getNombre()
                        + " (ID: " + estacion.getIdEstacion() + ")");
                hayInstalados = true;
            }
        }

        if (!hayInstalados) {
            System.out.println("No hay sensores instalados en ninguna estación.");
        }

        return hayInstalados;
    }

    /**
     * Muestra por consola los sensores registrados que aún no están asignados
     * a ninguna estación meteorológica.
     *
     * @param gestor gestor central de infraestructura
     * @return {@code true} si existe al menos un sensor disponible; {@code false} en caso contrario
     */
    public static boolean mostrarSensoresDisponibles(GestionInfraestructura gestor) {
        List<Sensor> sensores = gestor.getSensores();
        List<EstacionMeteorologica> estaciones =
                gestor.getEstaciones();
        boolean hayDisponibles = false;

        System.out.println("--- Sensores Libres (No asignados) ---");

        for (Sensor sensor : sensores) {
            boolean asignado = false;

            for (EstacionMeteorologica estacion : estaciones) {
                if (estacion.getSensores() != null &&
                        estacion.getSensores().contains(sensor)) {
                    asignado = true;
                    break;
                }
            }

            if (!asignado) {
                System.out.println("ID: " + sensor.getIdSensor() +
                        " Marca: " + sensor.getMarca() +
                        " Estado: " + sensor.getEstado());
                hayDisponibles = true;
            }
        }

        if (!hayDisponibles) {
            System.out.println("No hay sensores libres registrados.");
        }

        return hayDisponibles;
    }

    /**
     * Muestra por consola las estaciones meteorológicas habilitadas para recibir
     * sensores (aquellas que no están fuera de servicio).
     *
     * @param gestor gestor central de infraestructura
     * @return {@code true} si existen estaciones registradas; {@code false} en caso contrario
     */
    public static boolean mostrarEstacionesParaInstalar(GestionInfraestructura gestor) {
        List<EstacionMeteorologica> estaciones =
                gestor.getEstaciones();

        if (estaciones.isEmpty()) {
            System.out.println("No hay estaciones registradas en el sistema.");
            return false;
        }

        System.out.println("--- Estaciones Disponibles ---");
        for (EstacionMeteorologica estacion : estaciones) {
            if(!estacion.getEstadoOp().equals(EstadoOperativo.FUERA_DE_SERVICIO) )
                System.out.println("ID: " + estacion.getIdEstacion() +
                    " Nombre: " + estacion.getNombre() +
                    " Estado: " + estacion.getEstadoOp());
        }

        return true;
    }

    /**
     * Muestra por consola todas las estaciones meteorológicas registradas junto
     * con su identificador y estado operativo actual.
     *
     * @param gestor gestor central de infraestructura
     * @return {@code true} si existe al menos una estación registrada; {@code false} en caso contrario
     */
    public static boolean mostrarTodasLasEstaciones(GestionInfraestructura gestor) {
        List<EstacionMeteorologica> estaciones = gestor.getEstaciones();

        if (estaciones.isEmpty()) {
            System.out.println("No hay estaciones registradas en el sistema.");
            return false;
        }

        System.out.println("--- Estaciones Registradas ---");
        for (EstacionMeteorologica estacion : estaciones) {
            System.out.println("ID: " + estacion.getIdEstacion()
                    + " Nombre: " + estacion.getNombre()
                    + " Estado actual: " + estacion.getEstadoOp());
        }

        return true;
    }

    /**
     * Muestra por consola todos los sensores registrados junto con su marca,
     * modelo y estado operativo actual.
     *
     * @param gestor gestor central de infraestructura
     * @return {@code true} si existe al menos un sensor registrado; {@code false} en caso contrario
     */
    public static boolean mostrarTodosLosSensores(GestionInfraestructura gestor) {
        List<Sensor> sensores = gestor.getSensores();

        if (sensores.isEmpty()) {
            System.out.println("No hay sensores registrados en el sistema.");
            return false;
        }

        System.out.println("--- Sensores Registrados ---");
        for (Sensor sensor : sensores) {
            System.out.println("ID: " + sensor.getIdSensor()
                    + " Marca/Modelo: " + sensor.getMarca() + " " + sensor.getModelo()
                    + " Estado actual: " + sensor.getEstado());
        }

        return true;
    }

    /**
     * Lista todas las zonas geográficas registradas con su ID y niveles de riesgo
     * y último valor histórico registrado.
     *
     * @param gestor gestor central de infraestructura
     */
    public static void listarZonas(GestionInfraestructura gestor) {
        List<ZonaGeografica> zonas = gestor.getZonas();
        List<ValorHistorico> historicos;
        ValorHistorico ultimoHistorico;
        String infoHistorico;

        if (zonas.isEmpty()) {
            System.out.println("No hay zonas geográficas registradas.");
            return;
        }

        System.out.println("--- Zonas Geográficas ---");
        for (ZonaGeografica zona : zonas) {
            historicos = zona.getValoresHistoricos();

            if (historicos.isEmpty()) {
                infoHistorico = "Sin valores cargados";
            } else {
                ultimoHistorico = historicos.getLast();
                infoHistorico = "Mes " + ultimoHistorico.getMes()
                        + " (" + ultimoHistorico.getAcumuladoMedio() + " mm)";
            }

            System.out.println("ID: " + zona.getIdZona()
                    + " Nombre: " + zona.getNombre()
                    + " Vulnerabilidad: " + zona.getNivelVulnerabilidad()
                    + " Alerta actual: " + zona.getNivelAlertaActual()
                    + " Último valor histórico: " + infoHistorico);

        }
    }

    /**
     * Lista todas las estaciones meteorológicas indicando la zona geográfica
     * a la que pertenecen.
     *
     * @param gestor gestor central de infraestructura
     */
    public static void listarEstaciones(GestionInfraestructura gestor) {
        List<EstacionMeteorologica> estaciones = gestor.getEstaciones();
        ZonaGeografica zona = null;
        String infoZona = "";

        if (estaciones.isEmpty()) {
            System.out.println("No hay estaciones meteorológicas registradas.");
            return;
        }

        System.out.println("--- Estaciones Meteorológicas ---");
        for (EstacionMeteorologica estacion : estaciones) {
            zona = gestor.buscarZonaDeEstacion(estacion);
            infoZona = (zona != null)
                    ? zona.getNombre() + " (ID: " + zona.getIdZona() + ")"
                    : "Sin zona asignada";

            System.out.println("ID: " + estacion.getIdEstacion()
                    + " | Nombre: " + estacion.getNombre()
                    + " | Tipo: " + estacion.getTipoEst()
                    + " | Estado: " + estacion.getEstadoOp()
                    + " | Zona: " + infoZona);
        }
    }

    /**
     * Lista todos los sensores registrados indicando la estación donde están
     * instalados o "Sin asignar" si se encuentran disponibles.
     *
     * @param gestor gestor central de infraestructura
     */
    public static void listarSensores(GestionInfraestructura gestor) {
        List<Sensor> sensores = gestor.getSensores();
        EstacionMeteorologica estacion = null;
        String infoEstacion = "";

        if (sensores.isEmpty()) {
            System.out.println("No hay sensores registrados.");
            return;
        }

        System.out.println("--- Sensores ---");
        for (Sensor sensor : sensores) {
            estacion = gestor.buscarEstacionDeSensor(sensor);
            infoEstacion = (estacion != null)
                    ? estacion.getNombre() + " (ID: " + estacion.getIdEstacion() + ")"
                    : "Sin asignar";

            System.out.println("ID: " + sensor.getIdSensor()
                    + " | Marca/Modelo: " + sensor.getMarca() + " " + sensor.getModelo()
                    + " | Estado: " + sensor.getEstado()
                    + " | Estación: " + infoEstacion);
        }
    }
}
