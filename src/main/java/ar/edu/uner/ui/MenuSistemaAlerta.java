/*
 * MenuSistemaAlerta.java
 *
 * Version 1.0
 *
 * 11/11/2026
 *
 * Copyright (c) 2026 Grupo 7 - UNER
 */
package ar.edu.uner.ui;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import  java.time.LocalDateTime;

import ar.edu.uner.domain.model.ZonaGeografica;
import ar.edu.uner.domain.enums.NivelAlerta;
import ar.edu.uner.domain.enums.TipoEventoClimatico;
import ar.edu.uner.domain.model.Alerta;
import ar.edu.uner.domain.model.EquipoRespuesta;
import ar.edu.uner.service.GestionPersonal;
import ar.edu.uner.service.GestionSistemaAlerta;
import ar.edu.uner.service.GestionInfraestructura;
import ar.edu.uner.service.LectorFechas;
import static ar.edu.uner.service.UtilidadesMenu.leerOpcion;


/**
 * Menú del módulo de sistema de alerta temprana. Permite evaluar el riesgo de
 * una zona, registrar alertas de operador, asignar equipos de respuesta a las
 * alertas que lo requieren y consultar las alertas registradas.
 *
 * @author Guillermina
 */
public class MenuSistemaAlerta implements Ejecutable{
    private Scanner sc;
    private GestionSistemaAlerta gestion;
    private GestionPersonal personal;
    private GestionInfraestructura gestionInfra;
    private int opcion;

     /**
     * Crea el menú con las gestiones que necesita.
     *
     * @param gestion Gestión del sistema de alerta temprana.
     * @param personal Gestión de personal técnico y equipos de respuesta.
     * @param gestionInfra Gestión de infraestructura (zonas geográficas).
     * @param sc Scanner con el que se leen los datos del usuario.
     */
    public MenuSistemaAlerta(GestionSistemaAlerta gestion,
                            GestionPersonal personal, 
                            GestionInfraestructura gestionInfra,
                            Scanner sc) {
        this.gestion = gestion;
        this.personal = personal;
        this.gestionInfra = gestionInfra;
        this.sc = sc;

    }

     /**
     * Muestra el menú del sistema de alerta temprana y ejecuta la opción
     * elegida hasta que el usuario elige volver.
     */
    @Override
    public void ejecutar() {

        do {
            System.out.println("==== ALERTAS ====");
            System.out.println("1. Evaluar riesgo de una zona");
            System.out.println("2. Registrar alerta de operador");
            System.out.println("3. Asignar equipo de respuesta a una alerta");
            System.out.println("4. Ver alertas");
            System.out.println("0. Volver");
            System.out.print("Ingrese una opcion: ");
            opcion = leerOpcion(sc);

            switch (opcion) {
                case 1:
                    evaluarZona();
                    break;
                
                case 2:
                    registrarAlertaManual();
                    break;
                
                case 3:
                    asignarEquipo();
                    break;
                
                case 4:
                    mostrarAlertas();
                    break;
                
                case 0:
                    System.out.println("Volviendo al menu principal...");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);

    }

    /**
     * Evalúa el riesgo de una zona elegida por el usuario. Informa si se
     * emitió una alerta o si el nivel no cambió o la zona está en VERDE.
     */

    private void evaluarZona() {
        System.out.println();
        System.out.println("==== EVALUAR ZONA ====");
        ZonaGeografica zona = elegirZona();
        if (zona == null) {
             return;
        }
        Alerta alerta = gestion.evaluarZona(zona);
        if (alerta == null) {
            System.out.println("No se emitio alerta: el nivel no cambio o la zona esta en VERDE.");
        } else {
            System.out.println("Alerta emitida: " + alerta);
        }
    }

    /**
     * Registra una alerta de operador. Solicita la zona, el tipo de evento
     * (crecida de río o tormenta severa), el nivel, la descripción y la fecha
     * y hora de la alerta.
     */
    private void registrarAlertaManual() {
        System.out.println();
        System.out.println("==== REGISTRAR ALERTA MANUAL ====");
        ZonaGeografica zona = elegirZona();
        if (zona == null) {
            return;
        }

        System.out.println("Tipo de evento:");
        System.out.println("1. Crecida de rio");
        System.out.println("2. Tormenta severa");
        System.out.print("Ingrese el tipo de evento: ");
        opcion = leerOpcion(sc);
        TipoEventoClimatico tipo;
        if (opcion == 1) {
            tipo = TipoEventoClimatico.CRECIDA_RIO;
        } else if (opcion == 2) {
            tipo = TipoEventoClimatico.TORMENTA_SEVERA;
        } else {
            System.out.println("Tipo invalido.");
            return;
        }

        System.out.println("Nivel de alerta:");
        System.out.println("1. Verde");
        System.out.println("2. Amarilla");
        System.out.println("3. Naranja");
        System.out.println("4. Roja");
        System.out.print("Ingrese el nivel de alerta: ");
        opcion = leerOpcion(sc);
        NivelAlerta nivel;

        switch (opcion) {
            case 1:
                nivel = NivelAlerta.VERDE;
                break;

            case 2:
                nivel = NivelAlerta.AMARILLA;
                break;

            case 3:
                nivel = NivelAlerta.NARANJA;
                break;

            case 4:
                nivel = NivelAlerta.ROJA;
                break;
            
            default:
                System.out.println("Nivel invalido");
                return;
        }

        System.out.println("Descripcion: ");
        String descripcion = sc.nextLine();

        System.out.println("Fecha y hora de la alerta");
        LocalDateTime fechaHora = LectorFechas.leerFechaHora(sc);

        Alerta alerta = gestion.registrarAlertaManual(zona, nivel, tipo, descripcion, fechaHora);
        if (alerta == null) {
            System.out.println("No se pudo registrar la alerta.");
        } else {
            System.out.println("Alerta registrada.");
        }
    }

    /**
     * Asigna un equipo de respuesta a una alerta pendiente. El usuario elige
     * primero la alerta (NARANJA o ROJA sin equipo) y luego un equipo
     * disponible.
     */
    private void asignarEquipo() {
        System.out.println();
        System.out.println("==== ASIGNAR EQUIPO A UNA ALERTA ====");
        Alerta alerta = elegirAlertaPendiente();
        if (alerta == null) {
            return;
        }
        EquipoRespuesta equipo = elegirEquipo();
        if (equipo == null) {
            return;
        }
        if (gestion.asignarEquipo(alerta, equipo)) {
            System.out.println("Equipo asignado.");
        } else {
            System.out.println("No se pudo asignar: la alerta no requiere equipo o el equipo no esta disponible.");
        }
    }

    /**
     * Muestra todas las alertas registradas en el sistema.
     */
    private void mostrarAlertas() {
        System.out.println();
        System.out.println("==== REGISTRO DE ALERTAS ====");
        System.out.println("Alertas registradas: ");
        List<Alerta> alertas = gestion.getAlertas();
        if (alertas.isEmpty()) {
            System.out.println("ninguna.");
        } else {
            for (Alerta a : alertas) {
                System.out.println(a);
            }
        }
    }

    /**
     * Lista las zonas geográficas y permite elegir una por su número.
     *
     * @return La zona elegida; null si no hay zonas cargadas o la opción es inválida.
     */
    private ZonaGeografica elegirZona() {
        List<ZonaGeografica> zonas = gestionInfra.getZonas();
        if (zonas.isEmpty()) {
            System.out.println("No hay zonas cargadas");
            return null;
        }
        for (int i = 0; i < zonas.size(); i++) {
            System.out.println((i + 1) + ". " + zonas.get(i));
        }
        System.out.print("Elegir zona: ");
        opcion = leerOpcion(sc);
        if (opcion < 1 || opcion > zonas.size()) {
            System.out.println("Zona invalida.");
            return null;
        }

        return zonas.get(opcion - 1);
    }

    /**
     * Lista las alertas que requieren equipo de respuesta y todavía no tienen
     * uno asignado, y permite elegir una por su número.
     *
     * @return La alerta elegida; null si no hay alertas pendientes o la opción es inválida.
     */
    private Alerta elegirAlertaPendiente() {
        List<Alerta> pendientes = new ArrayList<Alerta>();
        for (Alerta a : gestion.getAlertas()) {
            if (a.requiereEquipo() && a.getEquipo() == null) {
                pendientes.add(a);
            }
        }
        if (pendientes.isEmpty()) {
            System.out.println("No hay alertas pendientes de equipo");
            return null;
        }
        for (int i = 0; i < pendientes.size(); i++) {
            System.out.println((i + 1) + ". " + pendientes.get(i));
        }
        System.out.print("Elegir alerta: ");
        opcion = leerOpcion(sc);
        if (opcion < 1 || opcion > pendientes.size()) {
            System.out.println("Alerta invalida.");
            return null;
        }

        return pendientes.get(opcion - 1);
    }

    /**
     * Lista los equipos de respuesta disponibles y permite elegir uno por su
     * número.
     *
     * @return El equipo elegido; null si no hay equipos disponibles o la opción es inválida.
     */
    private EquipoRespuesta elegirEquipo() {
        List<EquipoRespuesta> equiposDisp = new ArrayList<EquipoRespuesta>();
        for (EquipoRespuesta e : personal.getEquipos()) {
            if (e.isDisponible()) {
                equiposDisp.add(e);
            }
        }
        if (equiposDisp.isEmpty()) {
            System.out.println("No hay equipos de respuesta disponibles.");
            return null;
        }
        for (int i = 0; i < equiposDisp.size(); i++) {
            System.out.println((i + 1) + ". " + equiposDisp.get(i));
        }
        System.out.print("Elegir equipo: ");
        opcion = leerOpcion(sc);
        if (opcion < 1 || opcion > equiposDisp.size()) {
            System.out.println("Equipo invalido.");
            return null;
        }

        return equiposDisp.get(opcion - 1);
    }
}
