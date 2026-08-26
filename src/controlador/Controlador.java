/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author luisi
 */
import modelo.*;
import dao.*;
import service.*;
import java.util.*;

public class Controlador {
    private UbicacionDao ubicacionDAO;
    private RutaDAO rutaDAO;
    private CalculadoraService calculadoraService;
    
    public Controlador() {
        this.ubicacionDAO = new UbicacionDao();
        this.rutaDAO = new RutaDAO(this.ubicacionDAO);
        this.calculadoraService = new CalculadoraService();
    }
    
    public List<Ubicacion> getTodasUbicaciones() {
        return ubicacionDAO.getAll();
    }
    
    public Ubicacion buscarUbicacion(String nombre) {
        return ubicacionDAO.getByNombre(nombre);
    }
    
    public List<Ubicacion> buscarUbicaciones(String texto) {
        return ubicacionDAO.buscarPorNombre(texto);
    }
    
    public List<Tramo> getOpcionesViaje(Ubicacion origen, Ubicacion destino) {
        return rutaDAO.getTramos(origen, destino);
    }
    
    public Ruta getMejorRuta(Ubicacion origen, Ubicacion destino) {
        List<Tramo> opciones = getOpcionesViaje(origen, destino);
        if (opciones.isEmpty()) return null;
        return calculadoraService.calcularRutaOptima(origen, destino, opciones);
    }
    
    public Ruta getRutaMasEcologica(Ubicacion origen, Ubicacion destino) {
        List<Tramo> opciones = getOpcionesViaje(origen, destino);
        if (opciones.isEmpty()) return null;
        return calculadoraService.rutaMasEcologica(origen, destino, opciones);
    }
    
    public Ruta getRutaMasRapida(Ubicacion origen, Ubicacion destino) {
        List<Tramo> opciones = getOpcionesViaje(origen, destino);
        if (opciones.isEmpty()) return null;
        return calculadoraService.rutaMasRapida(origen, destino, opciones);
    }
    
    public List<Ruta> getTodasRutas(Ubicacion origen, Ubicacion destino) {
        List<Tramo> opciones = getOpcionesViaje(origen, destino);
        List<Ruta> rutas = new ArrayList<>();
        
        for (Tramo tramo : opciones) {
            Ruta ruta = new Ruta(origen, destino);
            ruta.agregarTramo(tramo);
            rutas.add(ruta);
        }
        return rutas;
    }
}