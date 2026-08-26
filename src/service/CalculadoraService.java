/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author luisi
 */
import modelo.*;
import java.util.*;

public class CalculadoraService {
    
    public Ruta calcularRutaOptima(Ubicacion origen, Ubicacion destino, List<Tramo> opciones) {
        Ruta mejorRuta = null;
        double mejorPuntuacion = Double.MAX_VALUE;
        
        for (Tramo tramo : opciones) {
            Ruta ruta = new Ruta(origen, destino);
            ruta.agregarTramo(tramo);
            
            // Puntuación balanceada (pesos ajustables)
            double puntuacion = calcularPuntuacion(ruta);
            
            if (puntuacion < mejorPuntuacion) {
                mejorPuntuacion = puntuacion;
                mejorRuta = ruta;
            }
        }
        return mejorRuta;
    }
    
    private double calcularPuntuacion(Ruta ruta) {
        // Ponderación: tiempo (40%), emisiones (30%), costo (30%)
        double tiempoScore = ruta.getTiempoTotal() * 0.4;
        double emisionesScore = ruta.getEmisionesTotal() * 30; // escala kg CO2
        double costoScore = ruta.getCostoTotal() * 0.3;
        
        return tiempoScore + emisionesScore + costoScore;
    }
    
    public Ruta rutaMasEcologica(Ubicacion origen, Ubicacion destino, List<Tramo> opciones) {
        Ruta rutaEco = null;
        double menorEmision = Double.MAX_VALUE;
        
        for (Tramo tramo : opciones) {
            Ruta ruta = new Ruta(origen, destino);
            ruta.agregarTramo(tramo);
            double emision = ruta.getEmisionesTotal();
            
            if (emision < menorEmision) {
                menorEmision = emision;
                rutaEco = ruta;
            }
        }
        return rutaEco;
    }
    
    public Ruta rutaMasRapida(Ubicacion origen, Ubicacion destino, List<Tramo> opciones) {
        Ruta rutaRapida = null;
        int menorTiempo = Integer.MAX_VALUE;
        
        for (Tramo tramo : opciones) {
            Ruta ruta = new Ruta(origen, destino);
            ruta.agregarTramo(tramo);
            int tiempo = ruta.getTiempoTotal();
            
            if (tiempo < menorTiempo) {
                menorTiempo = tiempo;
                rutaRapida = ruta;
            }
        }
        return rutaRapida;
    }
}
