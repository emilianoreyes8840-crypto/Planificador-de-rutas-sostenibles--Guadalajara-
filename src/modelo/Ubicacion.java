/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author luisi
 */
public class Ubicacion {
    private int id;
    private String nombre;
    private double latitud;
    private double longitud;
    
    public Ubicacion(int id, String nombre, double latitud, double longitud) {
        this.id = id;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }
    
    // Getters y Setters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getLatitud() { return latitud; }
    public double getLongitud() { return longitud; }
    
    @Override
    public String toString() {
        return nombre + " (" + latitud + ", " + longitud + ")";
    }
}
