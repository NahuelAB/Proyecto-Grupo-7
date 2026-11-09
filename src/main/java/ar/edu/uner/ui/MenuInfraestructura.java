package ar.edu.uner.ui;

import ar.edu.uner.service.GestionInfraestructura;

public class MenuInfraestructura {
    private GestionInfraestructura gestorInfraestructura;

    public MenuInfraestructura(GestionInfraestructura gestorInfraestructura) {
        this.gestorInfraestructura = gestorInfraestructura;
    }

    public void ejecutar() {
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
    }


}
