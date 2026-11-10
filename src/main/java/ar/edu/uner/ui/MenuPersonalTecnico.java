package ar.edu.uner.ui;

import ar.edu.uner.service.GestionInfraestructura;
import ar.edu.uner.service.GestionPersonal;

public class MenuPersonalTecnico implements Ejecutable {
    GestionPersonal gestorPersonal;

    public MenuPersonalTecnico(GestionPersonal gestorPersonal) {
        this.gestorPersonal = gestorPersonal;
    }

    @Override
    public void ejecutar() {
    /*
    Registrar responsable técnico, con DNI único.
    Asignar un responsable a una estación.
    Registrar un mantenimiento o una calibración: estación, sensor opcional, tipo, fecha, descripción y técnico. Una calibración actualiza la fecha de última calibración del sensor.
    Crear un equipo de respuesta.
    Agregar un integrante a un equipo, con la regla de que un técnico integra a lo sumo uno.
    Consultas: responsables, supervisores, técnicos de mantenimiento, de calibración y equipos disponibles.
     */
    }

}
