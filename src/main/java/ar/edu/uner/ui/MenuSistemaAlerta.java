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

public class MenuSistemaAlerta implements Ejecutable{
    private Scanner sc;
    private GestionSistemaAlerta gestion;
    private GestionPersonal personal;
    private GestionInfraestructura gestionInfra;

    public MenuSistemaAlerta(GestionSistemaAlerta gestion,
                            GestionPersonal personal, 
                            GestionInfraestructura gestionInfra,
                            Scanner sc) {
        this.gestion = gestion;
        this.personal = personal;
        this.gestionInfra = gestionInfra;
        this.sc = sc;

    }

    @Override
    public void ejecutar() {
        int opcion;

        do {
            System.out.println("SISTEMA DE ALERTA TEMPRANA");
            System.out.println("--------------------------");
            System.out.println("1. Evaluar riesgo de una zona");
            System.out.println("2. Registrar alerta de operador");
            System.out.println("3. Asignar equipo de respuesta a una alerta");
            System.out.println("4. Ver alertas");
            System.out.println("0. Volver");
            System.out.println("Opcion: ");
            opcion = sc.nextInt();

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
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);

    }
    private void evaluarZona() {
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

    private void registrarAlertaManual() {
        ZonaGeografica zona = elegirZona();
        if (zona == null) {
            return;
        }

        System.out.println("Tipo de evento:");
        System.out.println("1. Crecida de rio");
        System.out.println("2. Tormenta severa");
        System.out.print("Opcion: ");
        int opcion = sc.nextInt();
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
        System.out.print("Opcion: ");
        opcion = sc.nextInt();
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

        sc.nextLine();
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

    private void asignarEquipo() {
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

    private void mostrarAlertas() {
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

    private ZonaGeografica elegirZona() {
        List<ZonaGeografica> zonas = gestionInfra.getZonas();
        if (zonas.isEmpty()) {
            System.out.println("No hay zonas cargadas");
            return null;
        }
        for (int i = 0; i < zonas.size(); i++) {
            System.out.println((i + 1) + ". " + zonas.get(i));
        }
        System.out.println("Elegir zona: ");
        int opcion = sc.nextInt();
        if (opcion < 1 || opcion > zonas.size()) {
            System.out.println("Opcion invalida.");
            return null;
        }

        return zonas.get(opcion - 1);
    }

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
        System.out.println("Elegir alerta: ");
        int opcion = sc.nextInt();
        if (opcion < 1 || opcion > pendientes.size()) {
            System.out.println("Opcion invalida.");
            return null;
        }

        return pendientes.get(opcion - 1);
    }

    private EquipoRespuesta elegirEquipo() {
        List<EquipoRespuesta> equiposDisp = personal.getEquipos();
        for (EquipoRespuesta e : personal.getEquipos()) {
            if (e.isDisponible()) {
                equiposDisp.add(e);
            }
        }
        if (equiposDisp.isEmpty()) {
            System.out.println("No hay equipos de respuesta disponibles.");
        }
        for (int i = 0; i < equiposDisp.size(); i++) {
            System.out.println((i + 1) + ". " + equiposDisp.get(i));
        }
        System.out.println("Elegir equipo: ");
        int opcion = sc.nextInt();
        if (opcion < 1 || opcion > equiposDisp.size()) {
            System.out.println("Opcion invalida.");
            return null;
        }

        return equiposDisp.get(opcion - 1);
    }
}
