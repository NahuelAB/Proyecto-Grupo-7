/*
 * MenuInfraestructura.java
 * Version 1.0
 * 12/11/2026
 * Copyright (c) 2026 Grupo 7 - UNER
 */

package ar.edu.uner.ui;

import ar.edu.uner.domain.enums.EstadoOperativo;
import ar.edu.uner.domain.enums.NivelAlerta;
import ar.edu.uner.domain.enums.NivelVulnerabilidad;
import ar.edu.uner.domain.enums.TipoEstacion;
import ar.edu.uner.domain.model.EstacionMeteorologica;
import ar.edu.uner.domain.model.Sensor;
import ar.edu.uner.domain.model.ValorHistorico;
import ar.edu.uner.domain.model.ZonaGeografica;
import ar.edu.uner.service.GestionInfraestructura;
import ar.edu.uner.service.LectorFechas;
import ar.edu.uner.service.UtilidadesMenu;

import java.time.LocalDate;
import java.util.Scanner;

import static ar.edu.uner.service.LectorFechas.leerFecha;
import static ar.edu.uner.service.UtilidadesMenu.*;


/**
 * Interfaz de usuario por consola encargada de gestionar la infraestructura
 * meteorológica del sistema. Permite registrar zonas geográficas, estaciones
 * y sensores, administrar la instalación y retiro de sensores, actualizar
 * estados operativos, cargar valores históricos mensuales y listar elementos.
 *
 * @author Nahuel
 */
public class MenuInfraestructura implements Ejecutable {
    private GestionInfraestructura gestorInfraestructura;
    private Scanner scanner;

    /**
     * Construye el menú de infraestructura asociando el gestor de servicio y
     * el lector de entrada estándar.
     *
     * @param gestorInfraestructura gestor central de infraestructura meteorológica
     * @param scanner               lector de entrada por consola
     */
    public MenuInfraestructura(GestionInfraestructura gestorInfraestructura, Scanner scanner) {
        this.gestorInfraestructura = gestorInfraestructura;
        this.scanner = scanner;
    }

    /**
     * Inicia el ciclo principal de interacción del menú de infraestructura
     * hasta que el usuario selecciona la opción de salida.
     */
    @Override
    public void ejecutar() {
        int opcion;

        do {
            mostrarOpciones();
            System.out.println("Ingrese una opción: ");
            opcion = leerOpcion(scanner);

            switch (opcion) {
                case 1:
                    registrarZona();
                    break;

                case 2:
                    registrarEstacion();
                    break;

                case 3:
                    registrarSensor();
                    break;

                case 4:
                    instalarSensor();
                    break;

                case 5:
                    retirarSensor();
                    break;

                case 6:
                    cambiarEstadoEstacionOSensor();
                    break;

                case 7:
                    registrarValoresHistoricos();
                    break;

                case 8:
                    listarZonasEstacionesSensores();
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    /**
     * Despliega por consola las opciones disponibles en el módulo de
     * infraestructura meteorológica.
     */
    public void mostrarOpciones() {
        System.out.println();
        System.out.println("===== SISTEMA DE INFRAESTRUCTURA =====");
        System.out.println("1. Registrar Zona Geográfica");
        System.out.println("2. Registrar Estación Meteorológica");
        System.out.println("3. Registrar Sensor");
        System.out.println("4. Instalar Sensor");
        System.out.println("5. Retirar Sensor");
        System.out.println("6. Cambiar estado de estacion o sensor");
        System.out.println("7. Registrar valores histórico mensual");
        System.out.println("8. Listado de Zonas, Estaciones y Sensores");
        System.out.println("0. Salir");
    }

    /**
     * Solicita los datos por consola y registra una nueva zona geográfica
     * con su nivel de vulnerabilidad y nivel de alerta inicial.
     */
    void registrarZona() {
        System.out.println();
        System.out.println("===== REGISTRO DE ZONA GEOGRAFICA =====");

        String nombre;
        do {
            System.out.println("Ingrese el nombre: ");
            nombre = scanner.nextLine().trim();
            if (nombre.isEmpty()){
                System.out.println("El nombre no puede estar vacio.");
            }
        } while (nombre.isEmpty());

        System.out.println("Ingrese nivel de vulnerabilidad: ");
        System.out.println("1 - Bajo  2 - Medio  3 - Alto  4 - Muy Alta");
        NivelVulnerabilidad nivelV = nivelVulni(scanner);

        System.out.println("Ingrese nivel de alerta: ");
        System.out.println("1 - Verde  2 - Amarilla  3 - Naranja  4 - Roja");
        NivelAlerta nivelA = nivelAlerta(scanner);

        ZonaGeografica zona = new ZonaGeografica(nombre, nivelV, nivelA);

        boolean resultado = gestorInfraestructura.registrarZona(zona);
        System.out.println(resultado ? "¡Registro de zona geográfica exitosa!" :
                "No se pudo registrar la zona.");
    }


    /**
     * Solicita los datos de ubicación, estado y tipo por consola y registra
     * una nueva estación meteorológica vinculada a una zona existente.
     */
    void registrarEstacion() {
        System.out.println();
        System.out.println("===== REGISTRO DE ESTACION METEOROLOGICA =====");

        if (!mostrarZonas(gestorInfraestructura)) {
            return;
        }

        System.out.println("Seleccione un ID: ");
        int idZona = scanner.nextInt();
        scanner.nextLine();


        String nombre;
        do {
            System.out.println("Ingrese el nombre: ");
            nombre = scanner.nextLine().trim();
            if (nombre.isEmpty()){
                System.out.println("El nombre no puede estar vacio.");
            }
        } while (nombre.isEmpty());

        System.out.println("Ingrese nivel de latitud: ");
        double latitud = leerDouble(scanner);

        System.out.println("Ingrese nivel de longitud: ");
        double longitud = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Ingrese nivel de altitud: ");
        double altitud = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Ingrese el estado operativo estación: ");
        System.out.println("1 - Operativo  2 - En Mantenimiento" +
                "  3 - Fuera de servicio");
        EstadoOperativo estado = estadoOp(scanner);

        System.out.println("Ingrese el tipo de estación: ");
        System.out.println(" 1 - Automática 2 - Convencional");
        TipoEstacion tipoEstacion = tipoEstacion(scanner);

        EstacionMeteorologica estacion = new EstacionMeteorologica(nombre, latitud,
                longitud, altitud);
        estacion.setEstadoOp(estado);
        estacion.setTipoEst(tipoEstacion);

        boolean resultado = gestorInfraestructura.registrarEstacion(estacion, idZona);
        System.out.println(resultado ? "¡Registro de estacion meteorologica Exitosa!"
                : "No se pudo registrar la estación.");
    }

    /**
     * Solicita marca, modelo, precisión, estado operativo y fecha de última
     * calibración para dar de alta un nuevo sensor en el sistema.
     */
    void registrarSensor() {
        System.out.println();
        System.out.println("===== REGISTRO DE SENSOR =====");

        String marca;
        do {
            System.out.println("Ingrese la marca del sensor: ");
            marca = scanner.nextLine().trim();
            if (marca.isEmpty()){
                System.out.println("La marca no puede estar vacia.");
            }
        } while (marca.isEmpty());

        System.out.println("Ingrese el modelo del sensor: ");
        String modelo = scanner.nextLine();

        System.out.println("Ingrese la precision del sensor: ");
        double precision = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Ingrese el estado operativo del sensor: ");
        System.out.println("1 - Operativo  2 - En Mantenimiento" +
                "  3 - Fuera de servicio");
        EstadoOperativo estado = estadoOp(scanner);

        System.out.println("Ingrese fecha de la ultima calibración: ");
        LocalDate fecha = leerFecha(scanner);

        Sensor sensor = new Sensor(marca, modelo, precision, estado,
                fecha);

        boolean resultado = gestorInfraestructura.registrarSensor(sensor);
        System.out.println(resultado ? "¡Registro de sensor Exitoso!" :
                "No se pudo registrar el sensor.");
    }

    /**
     * Muestra los sensores disponibles y las estaciones habilitadas para
     * vincular un sensor operativo a una estación meteorológica.
     */
    void instalarSensor() {
        System.out.println();
        System.out.println("===== INSTALAR SENSOR =====");

        if (!mostrarSensoresDisponibles(gestorInfraestructura)) {
            return;
        }

        System.out.print("Ingrese el ID del Sensor a instalar: ");
        int idSensor = scanner.nextInt();

        if (!mostrarEstacionesParaInstalar(gestorInfraestructura)) {
            return;
        }

        System.out.print("Ingrese el ID de la Estación de destino: ");
        int idEstacion = scanner.nextInt();

        boolean resultado = gestorInfraestructura.instalarSensor(idSensor, idEstacion);
        System.out.println(resultado ? "¡Instalación de sensor Exitoso!" :
                "No se pudo instalar el sensor.");
    }

    /**
     * Lista los sensores actualmente instalados y permite retirar uno de su
     * estación meteorológica para dejarlo disponible.
     */
    void retirarSensor() {
        System.out.println();
        System.out.println("===== RETIRAR SENSOR =====");

        if (!UtilidadesMenu.mostrarSensoresInstalados(gestorInfraestructura)) {
            return;
        }

        System.out.print("Ingrese el ID del sensor a retirar: ");
        int idSensor = scanner.nextInt();
        scanner.nextLine();

        boolean resultado = gestorInfraestructura.retirarSensor(idSensor);
        System.out.println(resultado
                ? "¡Sensor retirado exitosamente!"
                : "No se pudo retirar el sensor. Verifique que el ID sea correcto.");
    }

    /**
     * Despliega un submenú para seleccionar si se desea modificar el estado
     * operativo de una estación meteorológica o de un sensor.
     */
    void cambiarEstadoEstacionOSensor() {
        int tipoElemento;

        System.out.println();
        System.out.println("===== CAMBIAR ESTADO OPERATIVO =====");
        System.out.println("1 - Estación Meteorológica");
        System.out.println("2 - Sensor");
        System.out.print("Seleccione qué desea modificar: ");
        tipoElemento = scanner.nextInt();
        scanner.nextLine();

        switch (tipoElemento) {
            case 1:
                cambiarEstadoEstacion();
                break;

            case 2:
                cambiarEstadoSensor();
                break;

            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    /**
     * Permite seleccionar una estación meteorológica por su identificador y
     * actualizar su estado operativo.
     */
    private void cambiarEstadoEstacion() {
        int idEstacion;
        EstadoOperativo nuevoEstado;

        if (!UtilidadesMenu.mostrarTodasLasEstaciones(gestorInfraestructura)) {
            return;
        }

        System.out.print("Ingrese el ID de la estación: ");
        idEstacion = scanner.nextInt();
        scanner.nextLine();

        EstacionMeteorologica estacion = gestorInfraestructura.buscarEstacion(idEstacion);

        if (estacion == null) {
            System.out.println("Error: No existe una estación con el ID ingresado.");
            return;
        }

        System.out.println("Estado actual: " + estacion.getEstadoOp());
        System.out.println("Seleccione el nuevo estado operativo:");
        System.out.println("1 - Operativo  2 - En Mantenimiento" +
                "  3 - Fuera de servicio");
        nuevoEstado = UtilidadesMenu.estadoOp(scanner);

        estacion.setEstadoOp(nuevoEstado);
        System.out.println("¡Estado de la estación actualizado a " + nuevoEstado + "!");
    }

    /**
     * Permite seleccionar un sensor por su identificador y actualizar su
     * estado operativo.
     */
    private void cambiarEstadoSensor() {
        int idSensor;
        EstadoOperativo nuevoEstado;

        if (!UtilidadesMenu.mostrarTodosLosSensores(gestorInfraestructura)) {
            return;
        }

        System.out.print("Ingrese el ID del sensor: ");
        idSensor = scanner.nextInt();
        scanner.nextLine();

        Sensor sensor = gestorInfraestructura.buscarSensor(idSensor);

        if (sensor == null) {
            System.out.println("Error: No existe un sensor con el ID ingresado.");
            return;
        }

        System.out.println("Estado actual: " + sensor.getEstado());
        System.out.println("Seleccione el nuevo estado operativo:");
        System.out.println("1 - Operativo  2 - En Mantenimiento" +
                "  3 - Fuera de servicio");
        nuevoEstado = UtilidadesMenu.estadoOp(scanner);

        sensor.setEstado(nuevoEstado);
        System.out.println("¡Estado del sensor actualizado a " + nuevoEstado + "!");
    }

    /**
     * Solicita una zona geográfica, un mes de referencia y el acumulado medio
     * en milímetros para registrar un valor histórico mensual en dicha zona.
     */
    void registrarValoresHistoricos() {
        int idZona;
        int mes;
        double acumuladoMedio;
        ZonaGeografica zona;
        LocalDate fecha;
        ValorHistorico valorHistorico;
        boolean resultado = false;

        System.out.println();
        System.out.println("===== REGISTRO DE VALORES HISTÓRICOS MENSUALES =====");

        if (gestorInfraestructura.getZonas().isEmpty()) {
            System.out.println("No hay zonas geográficas registradas. Registre una primero.");
            return;
        }

        UtilidadesMenu.listarZonas(gestorInfraestructura);

        System.out.print("Ingrese el ID de la zona geográfica: ");
        idZona = scanner.nextInt();
        scanner.nextLine();

        zona = gestorInfraestructura.buscarZona(idZona);
        if (zona == null) {
            System.out.println("Error: No existe una zona geográfica con ese ID.");
            return;
        }

        if (!zona.getValoresHistoricos().isEmpty()) {
            System.out.println("--- Valores ya cargados en esta zona ---");
            for (ValorHistorico vh : zona.getValoresHistoricos()) {
                System.out.println("Mes: " + vh.getMes()
                        + " | Acumulado medio: " + vh.getAcumuladoMedio() + " mm");
            }
        }

        System.out.println("Ingrese una fecha correspondiente al mes histórico:");
        fecha = LectorFechas.leerFecha(scanner);
        mes = fecha.getMonthValue();

        for (ValorHistorico vh : zona.getValoresHistoricos()) {
            if (vh.getMes() == mes) {
                System.out.println("Error: Ya existe un valor histórico registrado para el mes "
                        + mes + " en esta zona.");
                return;
            }
        }

        do {
            System.out.print("Ingrese el acumulado medio histórico (mm): ");
            acumuladoMedio = scanner.nextDouble();
            scanner.nextLine();

            if (acumuladoMedio < 0) {
                System.out.println("El acumulado medio no puede ser negativo.");
            }
        } while (acumuladoMedio < 0);

        valorHistorico = new ValorHistorico(mes, acumuladoMedio);
        resultado = zona.agregarValorHistorico(valorHistorico);

        System.out.println(resultado
                ? "¡Valor histórico mensual registrado exitosamente!"
                : "No se pudo registrar el valor histórico.");
    }

    /**
     * Despliega un submenú para consultar y listar por consola las zonas
     * geográficas, las estaciones meteorológicas o los sensores registrados.
     */
    void listarZonasEstacionesSensores() {
        int opcion;

        System.out.println();
        System.out.println("===== LISTAR INFRAESTRUCTURA =====");
        System.out.println("1 - Zonas Geográficas");
        System.out.println("2 - Estaciones Meteorológicas");
        System.out.println("3 - Sensores");
        System.out.print("Seleccione qué desea listar: ");
        opcion = leerOpcion(scanner);
        scanner.nextLine();

        switch (opcion) {
            case 1:
                UtilidadesMenu.listarZonas(gestorInfraestructura);
                break;

            case 2:
                UtilidadesMenu.listarEstaciones(gestorInfraestructura);
                break;

            case 3:
                UtilidadesMenu.listarSensores(gestorInfraestructura);
                break;

            default:
                System.out.println("Opción no válida.");
                break;
        }
    }
}