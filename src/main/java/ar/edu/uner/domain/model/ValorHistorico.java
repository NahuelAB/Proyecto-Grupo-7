/*
 * ValorHistorico.java
 *
 * Version 1.0
 *
 * 18/10/2026
 *
 * Copyright (c) 2026 Grupo 7 - UNER
 */


package ar.edu.uner.domain.model;


/**
 * Clase valor historico.
 *
 * @author Nahuel
 */
public class ValorHistorico {
    private int mes;
    private double acumuladoMedio;

    /**
     * Crea un valor histórico con valores predeterminados.
     */
    public ValorHistorico() {
        this(0,
                0);
    }

    /**
     * Crea un valor histórico con valores específicos.
     *
     * @param mes Mes del año (1 a 12).
     * @param acumuladoMedio Acumulado medio de precipitación, en milímetros.
     */
    public ValorHistorico(int mes, double acumuladoMedio) {
        this.mes = mes;
        this.acumuladoMedio = acumuladoMedio;
    }

    /**
     * Obtiene el mes del año (1 a 12).
     *
     * @return mes del año.
     */
    public int getMes() {
        return mes;
    }

    /**
     * Establece el mes del año (1 a 12).
     *
     * @param mes Mes a asignar.
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * Obtiene el acumulado medio de precipitación.
     *
     * @return Acumulado medio en milímetros.
     */
    public double getAcumuladoMedio() {
        return acumuladoMedio;
    }

    /**
     * Establece el acumulado medio de precipitación.
     *
     * @param acumuladoMedio Acumulado medio a asignar, en milímetros.
     */
    public void setAcumuladoMedio(double acumuladoMedio) {
        this.acumuladoMedio = acumuladoMedio;
    }

    @Override
    public String toString() {
        return "Mes: " + mes + ", Acumulado: " + acumuladoMedio;
    }
}
