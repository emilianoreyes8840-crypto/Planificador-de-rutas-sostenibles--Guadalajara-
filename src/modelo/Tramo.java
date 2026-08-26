/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author luisi
 */
public class Tramo {
    private Ubicacion origen;
    private Ubicacion destino;
    private double distanciaKm;
    private Transporte transporte;
    private int tiempoMinutos;
    
    public Tramo(Ubicacion origen, Ubicacion destino, double distanciaKm, Transporte transporte) {
        this.origen = origen;
        this.destino = destino;
        this.distanciaKm = distanciaKm;
        this.transporte = transporte;
        this.tiempoMinutos = calcularTiempo();
    }
    
    private int calcularTiempo() {
        double tiempoHoras = distanciaKm / transporte.getVelocidadPromedio();
        return (int) Math.ceil(tiempoHoras * 60);
    }
    
    public double getEmisionesCO2() {
        return distanciaKm * transporte.getEmisionesPorKm();
    }
    
    public double getCosto() {
        return distanciaKm * transporte.getCostoPorKm();
    }
    
    // Getters
    public Ubicacion getOrigen() { return origen; }
    public Ubicacion getDestino() { return destino; }
    public double getDistanciaKm() { return distanciaKm; }
    public Transporte getTransporte() { return transporte; }
    public int getTiempoMinutos() { return tiempoMinutos; }
    
    @Override
    public String toString() {
        return String.format("%s → %s (%s): %.1f km, %d min, %.2f kg CO₂, $%.2f",
            origen.getNombre(), destino.getNombre(), 
            transporte.getNombre(), distanciaKm, 
            tiempoMinutos, getEmisionesCO2(), getCosto());
    }
}