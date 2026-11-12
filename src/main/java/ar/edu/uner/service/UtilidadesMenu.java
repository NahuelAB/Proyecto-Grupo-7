package ar.edu.uner.service;

import ar.edu.uner.domain.enums.EstadoOperativo;
import ar.edu.uner.domain.enums.NivelAlerta;
import ar.edu.uner.domain.enums.NivelVulnerabilidad;
import ar.edu.uner.domain.enums.TipoEstacion;
import ar.edu.uner.domain.model.EstacionMeteorologica;
import ar.edu.uner.domain.model.Sensor;

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
                System.out.println("Opción no válida. Se asignó VERDE por defecto.");
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
                System.out.println("Opción no válida. Se asignó VERDE por defecto.");
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
                System.out.println("Opción no válida. Se asignó CONVECIONAL por defecto.");
                tipoEstacion = TipoEstacion.CONVENCIONAL;
        }

        return tipoEstacion;
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
}
