/*
 * MenuPrincipal.java
 *
 * Version 1.0
 *
 * 08/11/2026
 *
 * Copyright (c) UNER - Taller de Programación II
 */

package ar.edu.uner.ui;

import ar.edu.uner.service.*;

import java.util.Scanner;

import static ar.edu.uner.service.UtilidadesMenu.leerOpcion;

public class MenuPrincipal implements Ejecutable {
    private GestionInfraestructura gestorInfraestructura;
    private GestionPersonal gestorPersonal;
    private GestionSistemaAlerta gestorSistemaAlerta;
    private Scanner scanner;

    public MenuPrincipal(GestionInfraestructura gestorInfraestructura,
                         GestionPersonal gestorPersonal,
                        GestionSistemaAlerta gestorSistemaAlerta) {
        this.gestorInfraestructura = gestorInfraestructura;
        this.gestorPersonal = gestorPersonal;
        this.gestorSistemaAlerta = gestorSistemaAlerta;
        scanner = new Scanner(System.in);
    }

    public void mostrarOpciones() {
        System.out.println();
        System.out.println("===== SISTEMA METEOROLOGICO =====");
        System.out.println("1. Infraestructura");
        System.out.println("2. Personal técnico");
        System.out.println("3. Mediciones");
        System.out.println("4. Alertas");
        System.out.println("5. Reportes");
        System.out.println("0. Salir");
    }


    @Override
    public void ejecutar(){
        int opcion;

        do {
            mostrarOpciones();
            opcion = leerOpcion(scanner);

            switch (opcion) {
                case 1:
                    MenuInfraestructura menuInfra = new MenuInfraestructura(gestorInfraestructura, scanner);
                    menuInfra.ejecutar();
                    break;
                case 2:
                    MenuPersonalTecnico menuPersonal = new MenuPersonalTecnico(gestorPersonal);
                    menuPersonal.ejecutar();
                    break;
                case 3:
                    MenuMediciones menuMediciones = new MenuMediciones(gestorInfraestructura);
                    menuMediciones.ejecutar();
                    break;
                case 4:
                    MenuSistemaAlerta menuAlertas = new MenuSistemaAlerta(gestorSistemaAlerta, gestorPersonal, gestorInfraestructura, scanner);
                    menuAlertas.ejecutar();
                    break;
                case 5:
                    MenuReportes menuReportes = new MenuReportes(gestorInfraestructura);
                    menuReportes.ejecutar();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        } while (opcion != 0);
    }
}
