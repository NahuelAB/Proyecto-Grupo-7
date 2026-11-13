/*
 * MenuPersonalTecnico.java
 *
 * Version 1.2
 *
 * 12/11/2026
 *
 * Copyright (c) UNER - Taller de Programación II
 */

package ar.edu.uner.ui;

import ar.edu.uner.domain.enums.TipoMantenimiento;
import ar.edu.uner.domain.model.EquipoRespuesta;
import ar.edu.uner.domain.model.EstacionMeteorologica;
import ar.edu.uner.domain.model.ResponsableTecnico;
import ar.edu.uner.domain.model.Sensor;
import ar.edu.uner.service.GestionPersonal;
import ar.edu.uner.service.UtilidadesMenu;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static ar.edu.uner.service.LectorFechas.leerFecha;

/**
 * Menú de consola para administrar el personal técnico: responsables
 * técnicos, mantenimientos y calibraciones, y equipos de respuesta.
 *
 * @author Jmanuel (Grupo-7)
 */
public class MenuPersonalTecnico implements Ejecutable {
    private GestionPersonal gestorPersonal;
    private Scanner scanner;

    /**
     * Crea el menú con el gestor de personal que va a utilizar.
     *
     * @param gestorPersonal gestor del personal técnico
     */
    public MenuPersonalTecnico(GestionPersonal gestorPersonal) {
        this.gestorPersonal = gestorPersonal;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void ejecutar() {
        int opcion;

        do {
            mostrarOpciones();
            opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    registrarResponsable();
                    break;

                case 2:
                    asignarResponsable();
                    break;

                case 3:
                    registrarMantenimiento();
                    break;

                case 4:
                    crearEquipo();
                    break;

                case 5:
                    agregarIntegrante();
                    break;

                case 6:
                    consultar();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    public void mostrarOpciones() {
        System.out.println();
        System.out.println("===== PERSONAL TECNICO =====");
        System.out.println("1. Registrar responsable técnico");
        System.out.println("2. Asignar responsable a una estación");
        System.out.println("3. Registrar mantenimiento o calibración");
        System.out.println("4. Crear equipo de respuesta");
        System.out.println("5. Agregar integrante a un equipo");
        System.out.println("6. Consultas");
        System.out.println("0. Volver");
    }

    private int leerOpcion() {
        System.out.print("Elija una opción: ");

        return UtilidadesMenu.leerOpcion(scanner);
    }

    private String leerTexto(String mensaje) {
        String texto;
        do {
            System.out.println(mensaje);
            texto = scanner.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("El dato no puede estar vacio.");
            }
        } while (texto.isEmpty());

        return texto;
    }

    //Metodos para registro de responsables y mantenimientos

    private void registrarResponsable() {
        System.out.println();
        System.out.println("===== REGISTRO DE RESPONSABLE TECNICO =====");

        System.out.println("Ingrese el DNI: ");
        String dni = String.valueOf(UtilidadesMenu.leerOpcion(scanner));
        if (gestorPersonal.buscarResponsable(dni) != null) {
            System.out.println("Ya existe un responsable con ese DNI.");
            return;
        }
        String nombre = leerTexto("Ingrese el nombre: ");
        String apellido = leerTexto("Ingrese el apellido: ");
        String correo = leerTexto("Ingrese el correo electrónico: ");
        String celular = leerTexto("Ingrese el celular: ");
        String especialidad = leerTexto("Ingrese la especialidad: ");
        String licencia = leerTexto("Ingrese el número de licencia: ");

        ResponsableTecnico responsable = new ResponsableTecnico(dni, nombre,
                apellido, correo, celular, especialidad, licencia);

        boolean resultado = gestorPersonal.registrarResponsable(responsable);
        System.out.println(resultado ? "¡Registro de responsable técnico exitoso!" :
                "No se pudo registrar el responsable.");
    }

    private void asignarResponsable() {
        System.out.println();
        System.out.println("===== ASIGNAR RESPONSABLE A ESTACION =====");

        ResponsableTecnico responsable = elegirResponsable();
        if (responsable == null) {
            return;
        }
        EstacionMeteorologica estacion = elegirEstacion();
        if (estacion == null) {
            return;
        }

        boolean resultado = gestorPersonal.asignarResponsable(
                responsable.getDni(), estacion.getIdEstacion());
        System.out.println(resultado ? "¡Responsable asignado a la estación!" :
                "No se pudo asignar el responsable.");
    }

    private void registrarMantenimiento() {
        System.out.println();
        System.out.println("===== REGISTRO DE MANTENIMIENTO =====");

        EstacionMeteorologica estacion = elegirEstacion();
        if (estacion == null) {
            return;
        }
        TipoMantenimiento tipo = elegirTipoMantenimiento();
        if (tipo == null) {
            return;
        }

        // La calibración siempre se aplica a un sensor
        boolean conSensor = tipo == TipoMantenimiento.CALIBRACION;
        if (!conSensor) {
            System.out.println("¿Se aplica a un sensor puntual?");
            System.out.println("1 - Si  2 - No");
            conSensor = leerOpcion() == 1;
        }
        Sensor sensor = null;
        if (conSensor) {
            sensor = elegirSensor(estacion);
            if (sensor == null) {
                return;
            }
        }

        System.out.println("Fecha del mantenimiento: ");
        LocalDate fecha = leerFecha(scanner);
        String descripcion = leerTexto("Ingrese la descripción: ");
        ResponsableTecnico responsable = elegirResponsable();
        if (responsable == null) {
            return;
        }

        boolean resultado;
        if (sensor == null) {
            resultado = gestorPersonal.registrarMantenimiento(
                    estacion.getIdEstacion(), tipo, fecha, descripcion,
                    responsable.getDni());
        } else {
            resultado = gestorPersonal.registrarMantenimiento(
                    estacion.getIdEstacion(), sensor.getIdSensor(), tipo,
                    fecha, descripcion, responsable.getDni());
        }
        System.out.println(resultado ? "¡Registro de mantenimiento exitoso!" :
                "No se pudo registrar el mantenimiento.");
    }

    //Metodos para equipos de respuesta

    private void crearEquipo() {
        System.out.println();
        System.out.println("===== CREAR EQUIPO DE RESPUESTA =====");

        String nombre = leerTexto("Ingrese el nombre del equipo: ");
        EquipoRespuesta equipo = new EquipoRespuesta(nombre, true);

        boolean resultado = gestorPersonal.agregarEquipo(equipo);
        System.out.println(resultado ? "¡Equipo de respuesta creado!" :
                "No se pudo crear el equipo.");
    }

    private void agregarIntegrante() {
        System.out.println();
        System.out.println("===== AGREGAR INTEGRANTE A EQUIPO =====");

        EquipoRespuesta equipo = elegirEquipo();
        if (equipo == null) {
            return;
        }
        ResponsableTecnico responsable = elegirResponsable();
        if (responsable == null) {
            return;
        }

        boolean resultado = gestorPersonal.agregarIntegrante(
                equipo.getIdEquipo(), responsable.getDni());
        System.out.println(resultado ? "¡Integrante agregado al equipo!" :
                "No se pudo agregar: el responsable ya integra un equipo.");
    }

    //Metodos para consultas

    private void consultar() {
        int opcion;

        do {
            System.out.println();
            System.out.println("===== CONSULTAS DE PERSONAL =====");
            System.out.println("1. Responsables técnicos");
            System.out.println("2. Supervisores de estaciones");
            System.out.println("3. Técnicos de mantenimiento");
            System.out.println("4. Técnicos de calibración");
            System.out.println("5. Equipos disponibles");
            System.out.println("0. Volver");
            opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    mostrarResponsables("Responsables técnicos:",
                            gestorPersonal.getResponsables());
                    break;

                case 2:
                    mostrarResponsables("Supervisores de estaciones:",
                            gestorPersonal.obtenerSupervisores());
                    break;

                case 3:
                    mostrarResponsables("Técnicos de mantenimiento:",
                            gestorPersonal.obtenerResponsablesMantenimiento());
                    break;

                case 4:
                    mostrarResponsables("Técnicos de calibración:",
                            gestorPersonal.obtenerResponsablesCalibracion());
                    break;

                case 5:
                    mostrarEquipos(gestorPersonal.obtenerEquiposDisponibles());
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    private void mostrarResponsables(String titulo,
                                     List<ResponsableTecnico> responsables) {
        System.out.println(titulo);
        if (responsables.isEmpty()) {
            System.out.println("ninguno.");
        } else {
            for (ResponsableTecnico r : responsables) {
                System.out.println(r);
            }
        }
    }

    private void mostrarEquipos(List<EquipoRespuesta> equipos) {
        System.out.println("Equipos disponibles:");
        if (equipos.isEmpty()) {
            System.out.println("ninguno.");
        } else {
            for (EquipoRespuesta e : equipos) {
                System.out.println(describirEquipo(e));
            }
        }
    }

    //Metodos auxiliares para elegir datos de una lista

    private ResponsableTecnico elegirResponsable() {
        List<ResponsableTecnico> responsables = gestorPersonal.getResponsables();
        if (responsables.isEmpty()) {
            System.out.println("No hay responsables técnicos registrados.");
            return null;
        }
        for (int i = 0; i < responsables.size(); i++) {
            System.out.println((i + 1) + ". " + responsables.get(i));
        }
        System.out.println("Elegir responsable: ");
        int opcion = leerOpcion();
        if (opcion < 1 || opcion > responsables.size()) {
            System.out.println("Opción inválida.");
            return null;
        }

        return responsables.get(opcion - 1);
    }

    private EstacionMeteorologica elegirEstacion() {
        List<EstacionMeteorologica> estaciones = gestorPersonal.getEstaciones();
        if (estaciones.isEmpty()) {
            System.out.println("No hay estaciones cargadas.");
            return null;
        }
        for (int i = 0; i < estaciones.size(); i++) {
            EstacionMeteorologica e = estaciones.get(i);
            System.out.println((i + 1) + ". " + e.getIdEstacion() + " | "
                    + e.getNombre());
        }
        System.out.println("Elegir estación: ");
        int opcion = leerOpcion();
        if (opcion < 1 || opcion > estaciones.size()) {
            System.out.println("Opción inválida.");
            return null;
        }

        return estaciones.get(opcion - 1);
    }

    private Sensor elegirSensor(EstacionMeteorologica estacion) {
        List<Sensor> sensores = estacion.getSensores();
        if (sensores.isEmpty()) {
            System.out.println("La estación no tiene sensores instalados.");
            return null;
        }
        for (int i = 0; i < sensores.size(); i++) {
            System.out.println((i + 1) + ". " + sensores.get(i));
        }
        System.out.println("Elegir sensor: ");
        int opcion = leerOpcion();
        if (opcion < 1 || opcion > sensores.size()) {
            System.out.println("Opción inválida.");
            return null;
        }

        return sensores.get(opcion - 1);
    }

    private EquipoRespuesta elegirEquipo() {
        List<EquipoRespuesta> equipos = gestorPersonal.getEquipos();
        if (equipos.isEmpty()) {
            System.out.println("No hay equipos de respuesta registrados.");
            return null;
        }
        for (int i = 0; i < equipos.size(); i++) {
            System.out.println((i + 1) + ". " + describirEquipo(equipos.get(i)));
        }
        System.out.println("Elegir equipo: ");
        int opcion = leerOpcion();
        if (opcion < 1 || opcion > equipos.size()) {
            System.out.println("Opción inválida.");
            return null;
        }

        return equipos.get(opcion - 1);
    }

    private TipoMantenimiento elegirTipoMantenimiento() {
        System.out.println("Tipo de mantenimiento:");
        System.out.println("1 - Preventivo  2 - Correctivo  3 - Calibración");
        int opcion = leerOpcion();

        TipoMantenimiento tipo;
        switch (opcion) {
            case 1:
                tipo = TipoMantenimiento.PREVENTIVO;
                break;
            case 2:
                tipo = TipoMantenimiento.CORRECTIVO;
                break;
            case 3:
                tipo = TipoMantenimiento.CALIBRACION;
                break;
            default:
                System.out.println("Tipo inválido.");
                tipo = null;
        }
        return tipo;
    }

    private String describirEquipo(EquipoRespuesta equipo) {
        return equipo.getIdEquipo() + " | " + equipo.getNombreEquipo() + " | "
                + (equipo.isDisponible() ? "Disponible" : "No disponible")
                + " | Integrantes: " + equipo.getIntegrantes().size();
    }

}
