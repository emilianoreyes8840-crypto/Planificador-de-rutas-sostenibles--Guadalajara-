/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author luisi
 */

import modelo.*;
import java.util.*;

public class RutaDAO {
    private UbicacionDao ubicacionDAO;
    private Map<String, List<Tramo>> rutasPredefinidas;
    
    public RutaDAO(UbicacionDao ubicacionDAO) {
        this.ubicacionDAO = ubicacionDAO;
        this.rutasPredefinidas = new HashMap<>();
        cargarRutasMock();
    }
    
    private void cargarRutasMock() {
        Ubicacion centro = ubicacionDAO.getById(1);
        Ubicacion universidad = ubicacionDAO.getById(2);
        Ubicacion expo = ubicacionDAO.getById(3);
        Ubicacion estadio = ubicacionDAO.getById(4);
        Ubicacion plazadelSol = ubicacionDAO.getById(5);
        Ubicacion zapopan = ubicacionDAO.getById(6);
        Ubicacion tlaquepaque = ubicacionDAO.getById(7);
        Ubicacion aeropuerto = ubicacionDAO.getById(8);
        Ubicacion parque = ubicacionDAO.getById(9);
        Ubicacion iteso = ubicacionDAO.getById(10);
        
        // Centro → Universidad
        agregarTramo(centro, universidad, 3.5, Transporte.BUS);
        agregarTramo(centro, universidad, 3.5, Transporte.BICICLETA);
        agregarTramo(centro, universidad, 3.5, Transporte.CAMINATA);
        
        // Centro → Expo
        agregarTramo(centro, expo, 4.2, Transporte.BUS);
        agregarTramo(centro, expo, 4.2, Transporte.BICICLETA);
        agregarTramo(centro, expo, 4.2, Transporte.PATIN);
        
        // Centro → Plaza del Sol
        agregarTramo(centro, plazadelSol, 3.8, Transporte.BUS);
        agregarTramo(centro, plazadelSol, 3.8, Transporte.BICICLETA);
        agregarTramo(centro, plazadelSol, 3.8, Transporte.CAMINATA);
        
        // Centro → Zapopan
        agregarTramo(centro, zapopan, 7.5, Transporte.METRO);
        agregarTramo(centro, zapopan, 7.5, Transporte.BUS);
        agregarTramo(centro, zapopan, 7.5, Transporte.BICICLETA);
        
        // Universidad → Expo
        agregarTramo(universidad, expo, 5.1, Transporte.BUS);
        agregarTramo(universidad, expo, 5.1, Transporte.BICICLETA);
        agregarTramo(universidad, expo, 5.1, Transporte.PATIN);
        
        // Expo → Plaza del Sol
        agregarTramo(expo, plazadelSol, 2.8, Transporte.BUS);
        agregarTramo(expo, plazadelSol, 2.8, Transporte.BICICLETA);
        agregarTramo(expo, plazadelSol, 2.8, Transporte.CAMINATA);
        agregarTramo(expo, plazadelSol, 2.8, Transporte.PATIN);
        
        // Zapopan → Parque Metropolitano
        agregarTramo(zapopan, parque, 4.3, Transporte.BUS);
        agregarTramo(zapopan, parque, 4.3, Transporte.BICICLETA);
        
        // Más rutas...
        agregarTramo(centro, tlaquepaque, 8.2, Transporte.BUS);
        agregarTramo(centro, tlaquepaque, 8.2, Transporte.BICICLETA);
        agregarTramo(centro, tlaquepaque, 8.2, Transporte.PATIN);
        
        agregarTramo(centro, estadio, 9.5, Transporte.BUS);
        agregarTramo(centro, estadio, 9.5, Transporte.BICICLETA);
        agregarTramo(centro, estadio, 9.5, Transporte.METRO);
        
        agregarTramo(centro, iteso, 12.3, Transporte.BUS);
        agregarTramo(centro, iteso, 12.3, Transporte.BICICLETA);
        agregarTramo(centro, aeropuerto, 18.5, Transporte.BUS);
        agregarTramo(centro, aeropuerto, 18.5, Transporte.TAXI);
    }
    
    private void agregarTramo(Ubicacion origen, Ubicacion destino, double distancia, Transporte transporte) {
        String key = origen.getId() + "-" + destino.getId();
        rutasPredefinidas.computeIfAbsent(key, k -> new ArrayList<>())
            .add(new Tramo(origen, destino, distancia, transporte));
    }
    
    public List<Tramo> getTramos(Ubicacion origen, Ubicacion destino) {
        String key = origen.getId() + "-" + destino.getId();
        return rutasPredefinidas.getOrDefault(key, new ArrayList<>());
    }
    
    public List<Tramo> getTramos(Ubicacion origen, Ubicacion destino, Transporte transporte) {
        List<Tramo> todos = getTramos(origen, destino);
        List<Tramo> filtrados = new ArrayList<>();
        for (Tramo t : todos) {
            if (t.getTransporte() == transporte) {
                filtrados.add(t);
            }
        }
        return filtrados;
    }
    
}