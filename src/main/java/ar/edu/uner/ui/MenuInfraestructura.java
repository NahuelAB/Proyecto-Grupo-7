package ar.edu.uner.ui;

import ar.edu.uner.domain.enums.EstadoOperativo;
import ar.edu.uner.domain.enums.NivelAlerta;
import ar.edu.uner.domain.enums.NivelVulnerabilidad;
import ar.edu.uner.domain.enums.TipoEstacion;
import ar.edu.uner.domain.model.EstacionMeteorologica;
import ar.edu.uner.domain.model.Sensor;
import ar.edu.uner.domain.model.ZonaGeografica;
import ar.edu.uner.service.GestionInfraestructura;
import ar.edu.uner.service.UtilidadesMenu;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static ar.edu.uner.service.LectorFechas.leerFecha;
import static ar.edu.uner.service.UtilidadesMenu.*;

public class MenuInfraestructura implements Ejecutable {
    private GestionInfraestructura gestorInfraestructura;
    private Scanner scanner;

    public MenuInfraestructura(GestionInfraestructura gestorInfraestructura, Scanner scanner) {
        this.gestorInfraestructura = gestorInfraestructura;
        this.scanner = scanner;
    }

    @Override
    public void ejecutar() {
        int opcion;

        do {
            mostrarOpciones();
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

                case 6:
                    // cambiarEstado();
                    break;

                case 7:
                    // registrarValoresHistoricos();
                    break;

                case 8:
                    // listarZonasEstacionesSensores();
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

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


    //Metodos para registro Zona
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


    // Metodos para registro estacion
    void registrarEstacion() {
        System.out.println();
        System.out.println("===== REGISTRO DE ESTACION METEOROLOGICA =====");

        if (!mostrarZonas()) {
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
        double latitud = scanner.nextDouble();
        scanner.nextLine();

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

    boolean mostrarZonas() {
        List<ZonaGeografica> zonas = gestorInfraestructura.getZonas();

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

    // Metodos registro sensor

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


    void instalarSensor() {
        System.out.println();
        System.out.println("===== INSTALAR SENSOR =====");

        if (!mostrarSensoresDisponibles()) {
            return;
        }

        System.out.print("Ingrese el ID del Sensor a instalar: ");
        int idSensor = scanner.nextInt();

        if (!mostrarEstacionesParaInstalar()) {
            return;
        }

        System.out.print("Ingrese el ID de la Estación de destino: ");
        int idEstacion = scanner.nextInt();

        boolean resultado = gestorInfraestructura.instalarSensor2(idSensor, idEstacion);
        System.out.println(resultado ? "¡Instalación de sensor Exitoso!" :
                "No se pudo instalar el sensor.");
    }

    boolean mostrarSensoresDisponibles() {
        List<Sensor> sensores = gestorInfraestructura.getSensores();
        List<EstacionMeteorologica> estaciones =
                gestorInfraestructura.getEstaciones();
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

    boolean mostrarEstacionesParaInstalar() {
        List<EstacionMeteorologica> estaciones =
                gestorInfraestructura.getEstaciones();

        if (estaciones.isEmpty()) {
            System.out.println("No hay estaciones registradas en el sistema.");
            return false;
        }

        System.out.println("--- Estaciones Disponibles ---");
        for (EstacionMeteorologica estacion : estaciones) {
            System.out.println("ID: " + estacion.getIdEstacion() +
                    " Nombre: " + estacion.getNombre() +
                    " Estado: " + estacion.getEstadoOp());
        }

        return true;
    }

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