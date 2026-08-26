/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author luisi
 */
import modelo.Ubicacion;
import java.util.ArrayList;
import java.util.List;

public class UbicacionDao {
    private List<Ubicacion> ubicaciones;
    
    public UbicacionDao() {
        this.ubicaciones = new ArrayList<>();
        cargarUbicacionesMock();
    }
    
    private void cargarUbicacionesMock() {
        ubicaciones.add(new Ubicacion(1, "Centro Histórico", 20.6767, -103.3475));
        ubicaciones.add(new Ubicacion(2, "Universidad de Guadalajara (CUCEA)", 20.7027, -103.3354));
        ubicaciones.add(new Ubicacion(3, "Expo Guadalajara", 20.6806, -103.3791));
        ubicaciones.add(new Ubicacion(4, "Estadio Akron", 20.6909, -103.4140));
        ubicaciones.add(new Ubicacion(5, "Plaza del Sol", 20.6718, -103.3769));
        ubicaciones.add(new Ubicacion(6, "Zapopan Centro", 20.7215, -103.3845));
        ubicaciones.add(new Ubicacion(7, "Tlaquepaque Centro", 20.6409, -103.3083));
        ubicaciones.add(new Ubicacion(8, "Aeropuerto GDL", 20.5218, -103.3108));
        ubicaciones.add(new Ubicacion(9, "Parque Metropolitano", 20.6888, -103.4275));
        ubicaciones.add(new Ubicacion(10, "ITESO", 20.5889, -103.4026));
    }
    
    public List<Ubicacion> getAll() {
        return new ArrayList<>(ubicaciones);
    }
    
    public Ubicacion getById(int id) {
        return ubicaciones.stream()
            .filter(u -> u.getId() == id)
            .findFirst()
            .orElse(null);
    }
    
    public Ubicacion getByNombre(String nombre) {
        return ubicaciones.stream()
            .filter(u -> u.getNombre().toLowerCase().contains(nombre.toLowerCase()))
            .findFirst()
            .orElse(null);
    }
    
    public List<Ubicacion> buscarPorNombre(String nombre) {
        List<Ubicacion> resultado = new ArrayList<>();
        for (Ubicacion u : ubicaciones) {
            if (u.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                resultado.add(u);
            }
        }
        return resultado;
    }
}