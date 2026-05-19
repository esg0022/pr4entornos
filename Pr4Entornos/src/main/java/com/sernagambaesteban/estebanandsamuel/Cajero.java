/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sernagambaesteban.estebanandsamuel;

import java.util.ArrayList;

/**
 * Representa a un cajero que gestiona productos, genera tickets
 * y realiza el cierre de caja al final del día.
 * Lleva un registro del número de tickets emitidos y del total facturado.
 *
 * @author loren
 */
public class Cajero {

    /** Nombre del cajero. */
    String n;

    /** Número de tickets emitidos. */
    int c;

    /** Total facturado en el día. */
    double t;

    /** Lista de productos añadidos al ticket actual. */
    ArrayList<Producto> ps;

    /**
     * Crea un nuevo cajero con su nombre identificativo.
     *
     * @param n nombre del cajero
     */
    public Cajero(String n) {
        this.n = n;
        this.c = 0;
        this.t = 0;
        this.ps = new ArrayList<>();
    }

    /**
     * Añade un producto al ticket actual.
     *
     * @param p producto a añadir
     */
    public void ANADIRPRODUCTO(Producto p) {
        ps.add(p);
    }

    /**
     * Elimina un producto del ticket actual.
     *
     * @param p producto a eliminar
     */
    public void eliminarProDUCTO(Producto p) {
        ps.remove(p);
    }

    /**
     * Calcula el importe total del ticket, imprime el recibo por consola
     * y actualiza el total del día y el número de tickets emitidos.
     * Después de cobrar, la lista de productos se vacía.
     */
    public void cobrar() {
        double subt = 0;
        for (Producto p : ps) {
            subt = subt + p.calcularImporte();
        }
        double iva = subt * 0.21;
        double tot = subt + iva;

        System.out.println("===== TICKET =====");
        System.out.println("Cajero: " + n);
        for (Producto p : ps) {
            System.out.println(p.getNombre() + " x" + p.getCantidad()
                    + " = " + String.format("%.2f", p.calcularImporte()) + " EUR");
        }
        System.out.println("------------------");
        System.out.println("Subtotal: " + String.format("%.2f", subt) + " EUR");
        System.out.println("IVA (21%): " + String.format("%.2f", iva) + " EUR");
        System.out.println("TOTAL: " + String.format("%.2f", tot) + " EUR");
        System.out.println("==================");

        c = c + 1;
        t = t + tot;
        ps.clear();
    }

    /**
     * Muestra por consola un resumen del cierre de caja,
     * incluyendo tickets emitidos, total facturado e IVA recaudado.
     */
    public void cierreCaja() {
        double ivaRec = t - (t / (1 + 0.21));

        System.out.println("===== CIERRE DE CAJA =====");
        System.out.println("Cajero: " + n);
        System.out.println("--------------------------");
        System.out.println("Tickets emitidos: " + c);
        System.out.println("Total facturado:  " + String.format("%.2f", t) + " EUR");
        System.out.println("IVA recaudado:    " + String.format("%.2f", ivaRec) + " EUR");
        System.out.println("==========================");
    }

    /**
     * Indica si el ticket actual no contiene productos.
     *
     * @return {@code true} si no hay productos, {@code false} en caso contrario
     */
    public boolean ticketVacio() {
        return ps.isEmpty();
    }

    /**
     * Devuelve el número de tickets emitidos por el cajero.
     *
     * @return número de tickets emitidos
     */
    public int getTicketsEmitidos() {
        return c;
    }

    /**
     * Devuelve el total facturado en el día.
     *
     * @return total facturado
     */
    public double getTotalDia() {
        return t;
    }
}
