package ar.edu.uner.service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import static ar.edu.uner.service.UtilidadesMenu.leerOpcion;
 

public class LectorFechas {

    public static LocalDate leerFecha(Scanner sc) {
        LocalDate fecha = null;

        while (fecha == null) {
            System.out.print("Año: ");
            int anio = leerOpcion(sc);
            System.out.print("Mes: ");
            int mes = leerOpcion(sc);
            System.out.print("Día: ");
            int dia = leerOpcion(sc);

            try {
                fecha = LocalDate.of(anio, mes, dia);
            } catch (DateTimeException ex) {
                System.out.println("Fecha inválida, ingresela de nuevo.");
            }
        }
        return fecha;
    }

    public static LocalDateTime leerFechaHora(Scanner sc) {
        LocalDateTime fechaHora = null;

        while (fechaHora == null) {
            LocalDate fecha = leerFecha(sc);
            System.out.print("Hora: ");
            int hora = leerOpcion(sc);
            System.out.print("Minuto: ");
            int minuto = leerOpcion(sc);
            try {
                fechaHora = fecha.atTime(hora, minuto);
            } catch (DateTimeException ex) {
                System.out.println("Hora inválida, ingresela de nuevo.");
            }
        }
        return fechaHora;
    }
}
