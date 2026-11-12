package ar.edu.uner.service;

import ar.edu.uner.domain.enums.EstadoOperativo;
import ar.edu.uner.domain.enums.NivelAlerta;
import ar.edu.uner.domain.enums.NivelVulnerabilidad;
import ar.edu.uner.domain.enums.TipoEstacion;
import ar.edu.uner.domain.model.*;

import java.time.LocalDate;

public class CargadorDatosPrueba {

    private CargadorDatosPrueba(){}

    public static void cargarDatosIniciales(GestionInfraestructura gestorInfra,
                                            GestionPersonal gestorPersonal) {

        // Registro de Zonas Geográficas
        ZonaGeografica cuencaUruguay = new ZonaGeografica(
                "Cuencua Baja del Rio Uruguay", NivelVulnerabilidad.ALTO, NivelAlerta.VERDE);
        ZonaGeografica cuencaVillaguay = new ZonaGeografica("Cuenca Media del Río Gualeguay" +
                " - Villaguay", NivelVulnerabilidad.MEDIO, NivelAlerta.VERDE);
        gestorInfra.registrarZona(cuencaUruguay);
        gestorInfra.registrarZona(cuencaVillaguay);

        // Registro Estaciones Meteorológicas
        EstacionMeteorologica estacionSalto = new EstacionMeteorologica("Estación Salto Grande Norte", -31, -57, 42);
        estacionSalto.setTipoEst(TipoEstacion.AUTOMATICA);
        estacionSalto.setEstadoOp(EstadoOperativo.OPERATIVO);

        EstacionMeteorologica estacionVillaguay = new EstacionMeteorologica("Estación Villaguay Centro", -31, -59, 38);
        estacionVillaguay.setTipoEst(TipoEstacion.CONVENCIONAL);
        estacionVillaguay.setEstadoOp(EstadoOperativo.OPERATIVO);
        gestorInfra.registrarEstacion(estacionSalto, cuencaUruguay.getIdZona());
        gestorInfra.registrarEstacion(estacionVillaguay, cuencaVillaguay.getIdZona());

        // Registro Sensores
        Sensor sensorSalto1 = new Sensor("Davis Instruments", "Vantage Pro2 6465",
                0.2, EstadoOperativo.OPERATIVO, LocalDate.of(2026, 8, 15));
        Sensor sensorSalto2 = new Sensor("Campbell Scientific", "TE525MM",
                0.1, EstadoOperativo.OPERATIVO, LocalDate.of(2025, 6, 10));

        Sensor sensorVillaguay1 = new Sensor("OTT HydroMet", "Pluvio2 L",
                0.05, EstadoOperativo.OPERATIVO, LocalDate.of(2026, 9, 20));
        Sensor sensorVillaguay2 = new Sensor("Vaisala", "RG13H",
                0.2, EstadoOperativo.OPERATIVO, LocalDate.of(2026, 3, 5));

        gestorInfra.registrarSensor(sensorSalto1);
        gestorInfra.registrarSensor(sensorSalto2);
        gestorInfra.registrarSensor(sensorVillaguay1);
        gestorInfra.registrarSensor(sensorVillaguay2);

        gestorInfra.instalarSensor(sensorSalto1.getIdSensor(), estacionSalto.getIdEstacion());
        gestorInfra.instalarSensor(sensorSalto2.getIdSensor(), estacionSalto.getIdEstacion());
        gestorInfra.instalarSensor(sensorVillaguay1.getIdSensor(), estacionVillaguay.getIdEstacion());
        gestorInfra.instalarSensor(sensorVillaguay2.getIdSensor(), estacionVillaguay.getIdEstacion());


        ResponsableTecnico supervisorUruguay = new ResponsableTecnico(
                "35123456", "Juan", "Pérez", "juan.perez@uner.edu.ar",
                "343111222", "Hidrología", "LIC-001" );
        ResponsableTecnico supervisorVillaguay = new ResponsableTecnico(
                "36987654", "María", "Gómez", "maria.gomez@uner.edu.ar",
                "343333444", "Meteorología Operativa", "LIC-002" );
        ResponsableTecnico tecnicoMantenimiento = new ResponsableTecnico(
                "38444555", "Carlos", "López", "carlos.lopez@uner.edu.ar",
                "343555666", "Instrumentación y Calibración", "LIC-003" );
    }
}



        // Creacion Responsables


