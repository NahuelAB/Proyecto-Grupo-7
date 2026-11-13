package ar.edu.uner.service;

import ar.edu.uner.domain.enums.EstadoOperativo;
import ar.edu.uner.domain.enums.NivelAlerta;
import ar.edu.uner.domain.enums.NivelVulnerabilidad;
import ar.edu.uner.domain.enums.TipoEstacion;
import ar.edu.uner.domain.enums.TipoMantenimiento;
import ar.edu.uner.domain.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Carga los datos de prueba con los que arranca el sistema: zonas,
 * estaciones, sensores, valores históricos, lecturas anteriores,
 * responsables técnicos, mantenimientos y equipos de respuesta.
 * <p>
 * Las lecturas cargadas son de días anteriores, por lo que no entran en
 * el cálculo de lluvia de las últimas 24 horas y no disparan alertas.
 * Las alertas se generan después, registrando lecturas desde el menú.
 *
 * @author Grupo 7 - UNER
 */
public class CargadorDatosPrueba {

    private CargadorDatosPrueba() {
    }

    /**
     * Carga todos los datos iniciales en las gestiones recibidas.
     *
     * @param gestorInfra Gestión de infraestructura (zonas, estaciones y sensores).
     * @param gestorPersonal Gestión de personal (responsables y equipos).
     */
    public static void cargarDatosIniciales(GestionInfraestructura gestorInfra,
                                            GestionPersonal gestorPersonal) {

        // ---------- Zonas geográficas ----------
        ZonaGeografica cuencaUruguay = new ZonaGeografica(
                "Cuenca Baja del Río Uruguay", NivelVulnerabilidad.ALTO, NivelAlerta.NARANJA);
        ZonaGeografica cuencaVillaguay = new ZonaGeografica(
                "Cuenca Media del Río Gualeguay - Villaguay", NivelVulnerabilidad.MEDIO, NivelAlerta.VERDE);
        gestorInfra.registrarZona(cuencaUruguay);
        gestorInfra.registrarZona(cuencaVillaguay);

        // ---------- Valores históricos mensuales (mm acumulados medios) ----------
        double[] historicoUruguay = {130, 120, 140, 110, 80, 60, 55, 60, 85, 120, 135, 125};
        double[] historicoVillaguay = {120, 110, 130, 100, 75, 55, 50, 55, 80, 110, 125, 115};
        for (int mes = 1; mes <= 12; mes++) {
            cuencaUruguay.agregarValorHistorico(new ValorHistorico(mes, historicoUruguay[mes - 1]));
            cuencaVillaguay.agregarValorHistorico(new ValorHistorico(mes, historicoVillaguay[mes - 1]));
        }

        // ---------- Estaciones meteorológicas ----------
        EstacionMeteorologica estacionSalto = new EstacionMeteorologica(
                "Estación Salto Grande Norte", -31, -57, 42, EstadoOperativo.OPERATIVO,
                TipoEstacion.AUTOMATICA);

        EstacionMeteorologica estacionVillaguay = new EstacionMeteorologica(
                "Estación Villaguay Centro", -31, -59, 38, EstadoOperativo.OPERATIVO,
                TipoEstacion.CONVENCIONAL);

        gestorInfra.registrarEstacion(estacionSalto, cuencaUruguay.getIdZona());
        gestorInfra.registrarEstacion(estacionVillaguay, cuencaVillaguay.getIdZona());

        // ---------- Sensores ----------
        // El sensor 2 tiene la calibración vencida (más de 12 meses).
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

        // ---------- Lecturas anteriores (de días pasados) ----------
        // Sensor 1: valores normales.
        sensorSalto1.registrar(12.5, LocalDateTime.of(2026, 11, 2, 8, 0));
        sensorSalto1.registrar(30.0, LocalDateTime.of(2026, 11, 5, 8, 0));
        sensorSalto1.registrar(18.0, LocalDateTime.of(2026, 11, 9, 8, 0));
        // Sensor 2: el salto de 10 a 140 mm (y la vuelta a 25) queda marcado como anómalo.
        sensorSalto2.registrar(10.0, LocalDateTime.of(2026, 11, 2, 8, 0));
        sensorSalto2.registrar(140.0, LocalDateTime.of(2026, 11, 5, 8, 0));
        sensorSalto2.registrar(25.0, LocalDateTime.of(2026, 11, 9, 8, 0));
        // Sensores de Villaguay.
        sensorVillaguay1.registrar(20.0, LocalDateTime.of(2026, 11, 3, 8, 0));
        sensorVillaguay1.registrar(45.0, LocalDateTime.of(2026, 11, 8, 8, 0));
        sensorVillaguay2.registrar(15.0, LocalDateTime.of(2026, 11, 3, 8, 0));
        sensorVillaguay2.registrar(35.0, LocalDateTime.of(2026, 11, 8, 8, 0));

        // ---------- Responsables técnicos ----------
        ResponsableTecnico supervisorUruguay = new ResponsableTecnico(
                "35123456", "Juan", "Pérez", "juan.perez@uner.edu.ar",
                "343111222", "Hidrología", "LIC-001");
        ResponsableTecnico supervisorVillaguay = new ResponsableTecnico(
                "36987654", "María", "Gómez", "maria.gomez@uner.edu.ar",
                "343333444", "Meteorología Operativa", "LIC-002");
        ResponsableTecnico tecnicoMantenimiento = new ResponsableTecnico(
                "38444555", "Carlos", "López", "carlos.lopez@uner.edu.ar",
                "343555666", "Instrumentación y Calibración", "LIC-003");

        gestorPersonal.registrarResponsable(supervisorUruguay);
        gestorPersonal.registrarResponsable(supervisorVillaguay);
        gestorPersonal.registrarResponsable(tecnicoMantenimiento);

        // Cada estación queda a cargo de un responsable.
        gestorPersonal.asignarResponsable(supervisorUruguay.getDni(), estacionSalto.getIdEstacion());
        gestorPersonal.asignarResponsable(supervisorVillaguay.getDni(), estacionVillaguay.getIdEstacion());

        // ---------- Mantenimientos ----------
        gestorPersonal.registrarMantenimiento(estacionSalto.getIdEstacion(),
                TipoMantenimiento.PREVENTIVO, LocalDate.of(2026, 9, 1),
                "Limpieza general de la estación", tecnicoMantenimiento.getDni());
        gestorPersonal.registrarMantenimiento(estacionVillaguay.getIdEstacion(),
                sensorVillaguay1.getIdSensor(), TipoMantenimiento.CORRECTIVO,
                LocalDate.of(2026, 10, 10), "Reemplazo del embudo colector",
                tecnicoMantenimiento.getDni());

        // ---------- Equipos de respuesta ----------
        // Carlos queda sin equipo, para poder probar "Agregar integrante".
        EquipoRespuesta equipoAlfa = new EquipoRespuesta("Alfa", true);
        EquipoRespuesta equipoBravo = new EquipoRespuesta("Bravo", true);
        gestorPersonal.agregarEquipo(equipoAlfa);
        gestorPersonal.agregarEquipo(equipoBravo);
        gestorPersonal.agregarIntegrante(equipoAlfa.getIdEquipo(), supervisorUruguay.getDni());
        gestorPersonal.agregarIntegrante(equipoBravo.getIdEquipo(), supervisorVillaguay.getDni());
    }
}