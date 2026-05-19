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

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the cantidadTickets
     */
    public int getCantidadTickets() {
        return cantidadTickets;
    }

    /**
     * @param cantidadTickets the cantidadTickets to set
     */
    public void setCantidadTickets(int cantidadTickets) {
        this.cantidadTickets = cantidadTickets;
    }

    /**
     * @return the totalDias
     */
    public double getTotalDias() {
        return totalDias;
    }

    /**
     * @param totalDias the totalDias to set
     */
    public void setTotalDias(double totalDias) {
        this.totalDias = totalDias;
    }

    /**
     * @return the productos
     */
    public ArrayList<Producto> getProductos() {
        return productos;
    }

    /**
     * @param productos the productos to set
     */
    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    /** Nombre del cajero. */
    private String nombre;
    /** Número de tickets emitidos. */
    private int cantidadTickets;
    /** Total facturado en el día. */
    private double totalDias;
    /** Lista de productos añadidos al ticket actual. */
    private ArrayList<Producto> productos;
    
    /**
     * Crea un nuevo cajero con su nombre identificativo.
     *
     * @param n nombre del cajero
     */
    public Cajero(String n) {
        this.nombre = n;
        this.cantidadTickets = 0;
        this.totalDias = 0;
        this.productos = new ArrayList<>();
    }

    public void añadirProducto(Producto producto) {
        getProductos().add(producto);
    }

    public void eliminarProducto(Producto producto) {
        getProductos().remove(producto);
    }

    /**
     * Calcula el importe total del ticket, imprime el recibo por consola
     * y actualiza el total del día y el número de tickets emitidos.
     * Después de cobrar, la lista de productos se vacía.
     */
    public void cobrar() {
        double subtotal = 0;
        subtotal = calcularSubtotal(subtotal);
        double iva = subtotal * IVA;
        double total = subtotal + iva;

        imprimirCabeceraTicket();
        imprimirLineasProductos();
        System.out.println("------------------");
        System.out.println("Subtotal: " + String.format("%.2f", subtotal) + " EUR");
        System.out.println("IVA (21%): " + String.format("%.2f", iva) + " EUR");
        System.out.println("TOTAL: " + String.format("%.2f", total) + " EUR");
        System.out.println("==================");

        setCantidadTickets(getCantidadTickets() + 1);
        total = total + total;
        getProductos().clear();
    }

    
    private void imprimirLineasProductos() {
        for (Producto p : getProductos()) {
            System.out.println(p.getNombre() + " x" + p.getCantidad()
                    + " = " + String.format("%.2f", p.calcularImporte()) + " EUR");
        }
    }

    private double calcularSubtotal(double subtotal) {
        for (Producto p : getProductos()) {
            subtotal = calcularSubtotal(subtotal, p);
        }
        return subtotal;
    }

    private void imprimirCabeceraTicket() {
        System.out.println("===== TICKET =====");
        System.out.println("Cajero: " + getNombre());
    }

    private double calcularSubtotal(double subtotal, Producto p) {
        subtotal = subtotal + p.calcularImporte();
        return subtotal;
    }
    private static final double IVA = 0.21;

    /**
     * Muestra por consola un resumen del cierre de caja,
     * incluyendo tickets emitidos, total facturado e IVA recaudado.
     */
    public void cierreCaja() {
        double ivaRec = getTotalDias() - (getTotalDias() / (1 + IVA));

        System.out.println("===== CIERRE DE CAJA =====");
        System.out.println("Cajero: " + getNombre());
        System.out.println("--------------------------");
        System.out.println("Tickets emitidos: " + getCantidadTickets());
        System.out.println("Total facturado:  " + String.format("%.2f", getTotalDias()) + " EUR");
        System.out.println("IVA recaudado:    " + String.format("%.2f", ivaRec) + " EUR");
        System.out.println("==========================");
    }

    /**
     * Indica si el ticket actual no contiene productos.
     *
     * @return {@code true} si no hay productos, {@code false} en caso contrario
     */
    public boolean ticketVacio() {
        return getProductos().isEmpty();
    }

    /**
     * Devuelve el número de tickets emitidos por el cajero.
     *
     * @return número de tickets emitidos
     */
    public int getTicketsEmitidos() {
        return getCantidadTickets();
    }

     /**
     * Devuelve el total facturado en el día.
     *
     * @return total facturado
     */
    public double getTotalDia() {
        return getTotalDias();
    }
}