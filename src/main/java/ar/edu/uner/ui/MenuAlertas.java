package ar.edu.uner.ui;



import ar.edu.uner.service.GestionInfraestructura;

public class MenuAlertas {
    private GestionInfraestructura gestorInfraestructura;

    public MenuAlertas(GestionInfraestructura gestorInfraestructura) {
        this.gestorInfraestructura = gestorInfraestructura;
    }

    public void ejecutar() {
    /*
    Registrar una alerta manual (crecida de río o tormenta severa): zona, nivel y descripción.
    Asignar un equipo de respuesta a una alerta naranja o roja, mostrando antes los equipos disponibles.
    Marcar una alerta como atendida, lo que libera al equipo.
    Ver las alertas pendientes y, entre ellas, las que todavía no tienen equipo.
    Configurar los umbrales de lluvia de una zona, validando que amarillo < naranja < rojo.
    Ver el nivel de alerta vigente de cada zona.
     */
    }
}
