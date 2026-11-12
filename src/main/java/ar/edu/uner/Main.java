package ar.edu.uner;

import ar.edu.uner.service.*;
import ar.edu.uner.ui.MenuPrincipal;

public class Main {
    public static void main(String[] args) {
        // 1. Instaciar los servicios centrales
        GestionInfraestructura gestorInfra = new GestionInfraestructura();
        GestionPersonal gestorPersonal = new GestionPersonal(gestorInfra);
        GestionSistemaAlerta gestorSistAlerta = new GestionSistemaAlerta();

        // 2. Cargar datos iniciales
        CargadorDatosPrueba.cargarDatosIniciales(gestorInfra, gestorPersonal);

        // 3. Crear e iniciar el menu principal
        MenuPrincipal menu = new MenuPrincipal(gestorInfra, gestorPersonal, gestorSistAlerta);
        menu.ejecutar();
    }
}
