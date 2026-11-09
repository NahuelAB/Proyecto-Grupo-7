package ar.edu.uner;

import ar.edu.uner.service.GestionInfraestructura;
import ar.edu.uner.service.GestionPersonal;
import ar.edu.uner.ui.MenuPrincipal;

public class Main {
    public static void main(String[] args) {
        // 1. Instaciar los servicios centrales
        GestionInfraestructura gestorInfra = new GestionInfraestructura();
        GestionPersonal gestorPersonal = new GestionPersonal(gestorInfra);

        // 2. Cargar datos iniciales
        // CargadorDatosPruebas.cargarDatosIniciales(gestorInfra, gestorPersonal);

        // 3. Crear e iniciar el menu principal
        MenuPrincipal menu = new MenuPrincipal(gestorInfra, gestorPersonal);
        menu.ejecutar();
    }
}
