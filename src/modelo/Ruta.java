/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author luisi
 */
import java.util.ArrayList;
import java.util.List;

public class Ruta {
    private Ubicacion origen;
    private Ubicacion destino;
    private List<Tramo> tramos;
    
    public Ruta(Ubicacion origen, Ubicacion destino) {
        this.origen = origen;
        this.destino = destino;
        this.tramos = new ArrayList<>();
    }
    
    public void agregarTramo(Tramo tramo) {
        this.tramos.add(tramo);
    }
    
    public double getDistanciaTotal() {
        return tramos.stream().mapToDouble(Tramo::getDistanciaKm).sum();
    }
    
    public int getTiempoTotal() {
        return tramos.stream().mapToInt(Tramo::getTiempoMinutos).sum();
    }
    
    public double getEmisionesTotal() {
        return tramos.stream().mapToDouble(Tramo::getEmisionesCO2).sum();
    }
    
    public double getCostoTotal() {
        return tramos.stream().mapToDouble(Tramo::getCosto).sum();
    }
    
    // Getters
    public Ubicacion getOrigen() { return origen; }
    public Ubicacion getDestino() { return destino; }
    public List<Tramo> getTramos() { return tramos; }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== RUTA DE ").append(origen.getNombre())
          .append(" A ").append(destino.getNombre()).append(" ===\n");
        sb.append("Distancia total: ").append(String.format("%.1f", getDistanciaTotal())).append(" km\n");
        sb.append("Tiempo total: ").append(getTiempoTotal()).append(" min\n");
        sb.append("Emisiones: ").append(String.format("%.2f", getEmisionesTotal())).append(" kg CO₂\n");
        sb.append("Costo: $").append(String.format("%.2f", getCostoTotal())).append("\n");
        sb.append("\nTramos:\n");
        for (Tramo t : tramos) {
            sb.append("  • ").append(t.toString()).append("\n");
        }
        return sb.toString();
    }
}