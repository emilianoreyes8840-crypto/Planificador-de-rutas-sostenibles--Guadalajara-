/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author luisi
 */
public enum Transporte {
    BICICLETA("Bicicleta", 18.0, 0.0, 15.0),      // km/h, CO2/kg km, costo
    BUS("Autobús", 25.0, 0.08, 9.50),
    METRO("Metro", 30.0, 0.05, 7.00),
    CAMINATA("Caminata", 5.0, 0.0, 0.0),
    TAXI("Taxi", 30.0, 0.12, 25.00),
    PATIN("Patin Eléctrico", 20.0, 0.02, 5.00);
    
    private String nombre;
    private double velocidadPromedio; // km/h
    private double emisionesPorKm; // kg CO2
    private double costoPorKm; // pesos mexicanos
    
    Transporte(String nombre, double velocidad, double emisiones, double costo) {
        this.nombre = nombre;
        this.velocidadPromedio = velocidad;
        this.emisionesPorKm = emisiones;
        this.costoPorKm = costo;
    }
    
    // Getters
    public String getNombre() { return nombre; }
    public double getVelocidadPromedio() { return velocidadPromedio; }
    public double getEmisionesPorKm() { return emisionesPorKm; }
    public double getCostoPorKm() { return costoPorKm; }
}