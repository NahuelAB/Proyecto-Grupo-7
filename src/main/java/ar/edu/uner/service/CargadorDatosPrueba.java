package ar.edu.uner.service;

import ar.edu.uner.domain.enums.EstadoOperativo;
import ar.edu.uner.domain.enums.NivelAlerta;
import ar.edu.uner.domain.enums.NivelVulnerabilidad;
import ar.edu.uner.domain.enums.TipoEstacion;
import ar.edu.uner.domain.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CargadorDatosPrueba {
    public static void cargadorDatosIniciales(String[] args) {

        // Creacion gestor Personal
        GestionPersonal gestorPersonal = new GestionPersonal();

        // Creacion Responsables
        ResponsableTecnico supervisorUruguay = new ResponsableTecnico(
                "35123456", "Juan", "Pérez", "juan.perez@uner.edu.ar",
                "343111222", "Hidrología", "LIC-001" );
        ResponsableTecnico supervisorParana = new ResponsableTecnico(
                "36987654", "María", "Gómez", "maria.gomez@uner.edu.ar",
                "343333444", "Meteorología Operativa", "LIC-002" );
        ResponsableTecnico tecnicoMantenimiento = new ResponsableTecnico(
                "38444555", "Carlos", "López", "carlos.lopez@uner.edu.ar",
                "343555666", "Instrumentación y Calibración", "LIC-003" );

        // Creacion EquipoRespuesta
        EquipoRespuesta equipoAlpha = new EquipoRespuesta(
                "Equipo Alpha", true);
        equipoAlpha.agregarIntegrante(supervisorParana);
        equipoAlpha.agregarIntegrante(tecnicoMantenimiento);

        gestorPersonal.agregarEquipo(equipoAlpha);

        // Creacion Sensor
        Sensor pluviometroConcordia = new Sensor("Vaisala", "RG13",
                0.1, EstadoOperativo.OPERATIVO, 2026, 8, 20);
        pluviometroConcordia.registrar(25.5, LocalDateTime.now().minusHours(2));
        pluviometroConcordia.registrar(30.0, LocalDateTime.now().minusHours(8));
        pluviometroConcordia.registrar(15.0, LocalDateTime.now().minusHours(30));

        // Creacion Estacion meteorologica
        EstacionMeteorologica estacionConcordia = new EstacionMeteorologica(
                "Estacion Concordia", 20, 10, 30);
        estacionConcordia.setTipoEst(TipoEstacion.AUTOMATICA);
        estacionConcordia.setEstadoOp(EstadoOperativo.OPERATIVO);
        estacionConcordia.setResponsable(supervisorUruguay);
        estacionConcordia.instalarSensor(pluviometroConcordia);

        // Creacion Zona Geografica
        ZonaGeografica cuencaUruguay = new ZonaGeografica(
                "Cuencua Rio Uruguay", NivelVulnerabilidad.ALTO, NivelAlerta.VERDE);
        cuencaUruguay.agregarEstacion(estacionConcordia);
        gestorPersonal.agregarZona(cuencaUruguay);
    }

}
