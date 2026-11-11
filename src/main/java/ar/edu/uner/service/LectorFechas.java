package ar.edu.uner.service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class LectorFechas {

    public static LocalDate leerFecha(Scanner sc) {
        LocalDate fecha = null;
        while (fecha == null) {
            System.out.print("Año: ");
            int anio = sc.nextInt();
            System.out.print("Mes: ");
            int mes = sc.nextInt();
            System.out.print("Día: ");
            int dia = sc.nextInt();
            try {
                fecha = LocalDate.of(anio, mes, dia);
            } catch (DateTimeException ex) {
                System.out.println("Fecha inválida, ingresela de nuevo.");
            }
        }
        return fecha;
    }

    public LocalDateTime leerFechaHora(Scanner sc) {
        LocalDateTime fechaHora = null;
        while (fechaHora == null) {
            LocalDate fecha = leerFecha(sc);
            System.out.print("Hora: ");
            int hora = sc.nextInt();
            System.out.print("Minuto: ");
            int minuto = sc.nextInt();
            try {
                fechaHora = fecha.atTime(hora, minuto);
            } catch (DateTimeException ex) {
                System.out.println("Hora inválida, ingresela de nuevo.");
            }
        }
        return fechaHora;
    }
}
