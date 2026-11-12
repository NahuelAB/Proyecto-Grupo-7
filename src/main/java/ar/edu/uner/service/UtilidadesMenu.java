package ar.edu.uner.service;

import ar.edu.uner.domain.enums.EstadoOperativo;
import ar.edu.uner.domain.enums.NivelAlerta;
import ar.edu.uner.domain.enums.NivelVulnerabilidad;
import ar.edu.uner.domain.enums.TipoEstacion;
import ar.edu.uner.domain.model.EstacionMeteorologica;
import ar.edu.uner.domain.model.Sensor;
import ar.edu.uner.domain.model.ZonaGeografica;

import java.util.List;
import java.util.Scanner;

public class UtilidadesMenu {

    private UtilidadesMenu() {};

    public static int leerOpcion(Scanner scanner) {
        int opcion = scanner.nextInt();
        scanner.nextLine();

        return opcion;
    }

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
}
