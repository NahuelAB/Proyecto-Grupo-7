package ar.edu.uner.ui;

import ar.edu.uner.domain.enums.NivelAlerta;
import ar.edu.uner.domain.enums.NivelVulnerabilidad;
import ar.edu.uner.domain.model.ZonaGeografica;
import ar.edu.uner.service.GestionInfraestructura;

import java.util.Scanner;

public class MenuInfraestructura implements Ejecutable {
    private GestionInfraestructura gestorInfraestructura;
    private Scanner scanner;

    public MenuInfraestructura(GestionInfraestructura gestorInfraestructura) {
        this.gestorInfraestructura = gestorInfraestructura;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void ejecutar() {
        int opcion;

        do {
            mostrarOpciones();
            opcion = leerOpcion();


        } while (opcion != 0);
    }

    public void mostrarOpciones() {
        System.out.println();
        System.out.println("===== SISTEMA DE INFRAESTRUCTURA =====");
        System.out.println("1. Registrar Zona Geográfica");
        System.out.println("2. Registrar Estación Meteorológica");
        System.out.println("3. Registrar Sensor");
        System.out.println("4. Retirar Sensor");
        System.out.println("5. Cambiar estado de estacion o sensor");
        System.out.println("6. Registrar valores histórico mensual");
        System.out.println("7. Listado de Zonas, Estaciones y Sensores");
        System.out.println("0. Salir");
    }

    private int leerOpcion() {
        System.out.print("Elija una opción: ");

        return scanner.nextInt();
    }

    void registrarZona() {
        System.out.println();
        System.out.println("===== REGISTRO DE ZONA GEOGRAFICA =====");

        System.out.println("Ingrese el nombre: ");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese nivel de vulnerabilidad: ");
        System.out.println("1 - Bajo  2 - Medio  3 - Alto  4 - Muy Alta");
        NivelVulnerabilidad nivelV = nivelVulni();

        System.out.println("Ingrese nivel de alerta: ");
        System.out.println("1 - Verde  2 - Amarilla  3 - Naranja  4 - Roja");
        NivelAlerta nivelA = nivelAlerta();

        ZonaGeografica zona = new ZonaGeografica(nombre, nivelV, nivelA);

        boolean resultado = gestorInfraestructura.registrarZona(zona);
        System.out.println(resultado ? "¡Registro Exitoso!" : "No se pudo registrar la zona.");
    }

    NivelVulnerabilidad nivelVulni() {
        int opt = scanner.nextInt();
        scanner.nextInt();
        NivelVulnerabilidad nivel;

        switch(opt) {
            case 1: nivel = NivelVulnerabilidad.BAJO;
            break;
            case 2: nivel = NivelVulnerabilidad.MEDIO;
            break;
            case 3: nivel = NivelVulnerabilidad.ALTO;
            break;
            case 4: nivel = NivelVulnerabilidad.MUY_ALTO;
            break;
            default:
                System.out.println("Opción no válida. Se asignó BAJO por defecto.");
                nivel = NivelVulnerabilidad.BAJO;
        }
        return nivel;
    }

    NivelAlerta nivelAlerta() {
        int opt = scanner.nextInt();
        scanner.nextInt();
        NivelAlerta nivelA;

        switch(opt) {
            case 1: nivelA = NivelAlerta.VERDE;
                break;
            case 2: nivelA = NivelAlerta.AMARILLA;
                break;
            case 3: nivelA = NivelAlerta.NARANJA;
                break;
            case 4: nivelA = NivelAlerta.ROJA;
                break;
            default:
                System.out.println("Opción no válida. Se asignó VERDE por defecto.");
                nivelA = NivelAlerta.VERDE;
        }
        return nivelA;
    }
}


/*
    Registrar zona: nombre y nivel de vulnerabilidad. El id lo asigna el sistema.
    Registrar estación: elegir zona, tipo (automática o convencional), coordenadas y estado.
    Registrar sensor: marca, modelo, precisión y fecha de última calibración.
    Instalar sensor en una estación, con la regla de que un sensor está en una sola estación.
    Retirar o trasladar un sensor a otra estación.
    Cambiar el estado de una estación o de un sensor (operativo, en mantenimiento o fuera de servicio).
    Cargar los valores históricos mensuales de una zona.
    Listar zonas, estaciones y sensores con sus ids.
     */