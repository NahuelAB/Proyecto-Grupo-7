package ar.edu.uner.ui;

import ar.edu.uner.service.GestionInfraestructura;

public class MenuReportes implements Ejecutable {
    GestionInfraestructura gestorInfraestructura;

    public MenuReportes(GestionInfraestructura gestorInfraestructura) {
        this.gestorInfraestructura = gestorInfraestructura;
    }

    @Override
    public void ejecutar() {
    /*
    Histórico de precipitaciones por estación o zona: diario, semanal y mensual, con el mensual comparado contra la media histórica.
    Ranking de estaciones por volumetría en un rango de fechas.
    Historial de alertas, con filtros opcionales de nivel, fechas y zona.
    Ficha técnica operativa de una estación.
    Informe de fallas y mantenimiento.
    Estadística de anomalía climática: zona y mes, con el porcentaje de desviación.
     */
    }

}
