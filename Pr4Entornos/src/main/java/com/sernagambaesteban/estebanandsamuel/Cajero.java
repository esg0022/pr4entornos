/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sernagambaesteban.estebanandsamuel;

import java.util.ArrayList;

/**
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

    private String nombre;
    private int cantidadTickets;
    private double totalDias;
    private ArrayList<Producto> productos;
    
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

    public boolean ticketVacio() {
        return getProductos().isEmpty();
    }

    public int getTicketsEmitidos() {
        return getCantidadTickets();
    }

    public double getTotalDia() {
        return getTotalDias();
    }
}
