package ar.edu.uner.ui;

import ar.edu.uner.service.GestionInfraestructura;

public class MenuMediciones implements Ejecutable {
    private GestionInfraestructura gestorInfraestructura;

    public MenuMediciones(GestionInfraestructura gestorInfraestructura) {
        this.gestorInfraestructura = gestorInfraestructura;
    }

    @Override
    public void ejecutar() {
    /*
    Registrar una lectura: sensor, fecha y hora (por defecto, el momento actual) y milímetros. Acá se aplican y se muestran las reglas: rango de 0 a 500, sensor o estación operativos, lectura marcada como anómala y, si se supera un umbral, la alerta de lluvia copiosa que se generó.
    Ver las lecturas de un sensor, indicando cuáles son anómalas.
    Consultar la lluvia acumulada en 24 horas de una zona y su nivel de riesgo.
     */
    }

}
