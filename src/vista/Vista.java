/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

/**
 *
 * @author luisi
 */
import modelo.*;
import controlador.*;
import java.util.*;

public class Vista {
    private Controlador controller;
    private Scanner scanner;
    
    public Vista() {
        this.controller = new Controlador();
        this.scanner = new Scanner(System.in);
    }
    
    public void iniciar() {
        System.out.println("=== 🌱 PLANIFICADOR DE RUTAS SOSTENIBLES - GDL ===\n");
        
        while (true) {
            mostrarMenu();
            int opcion = leerOpcion();
            
            switch (opcion) {
                case 1:
                    buscarRuta();
                    break;
                case 2:
                    verTodasUbicaciones();
                    break;
                case 3:
                    compararRutas();
                    break;
                case 4:
                    System.out.println("¡Gracias por usar el planificador! 🌿");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
    
    private void mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Planificar ruta");
        System.out.println("2. Ver todas las ubicaciones");
        System.out.println("3. Comparar todas las rutas");
        System.out.println("4. Salir");
        System.out.print("Elige una opción: ");
    }
    
    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private void buscarRuta() {
        System.out.println("\n--- PLANIFICAR RUTA ---");
        
        Ubicacion origen = seleccionarUbicacion("origen");
        if (origen == null) return;
        
        Ubicacion destino = seleccionarUbicacion("destino");
        if (destino == null) return;
        
        System.out.println("\nBuscando opciones de viaje...");
        
        // Mostrar todas las opciones
        List<Ruta> todasRutas = controller.getTodasRutas(origen, destino);
        if (todasRutas.isEmpty()) {
            System.out.println("❌ No hay rutas disponibles entre estas ubicaciones.");
            return;
        }
        
        System.out.println("\n📊 OPCIONES DE VIAJE:");
        for (int i = 0; i < todasRutas.size(); i++) {
            Ruta r = todasRutas.get(i);
            System.out.printf("%d. %s → %s: %d min, %.2f kg CO₂, $%.2f\n",
                i+1, 
                r.getTramos().get(0).getTransporte().getNombre(),
                r.getTramos().get(0).getTransporte().getNombre(),
                r.getTiempoTotal(),
                r.getEmisionesTotal(),
                r.getCostoTotal());
        }
        
        // Mejor opción automática
        Ruta mejor = controller.getMejorRuta(origen, destino);
        Ruta eco = controller.getRutaMasEcologica(origen, destino);
        Ruta rapida = controller.getRutaMasRapida(origen, destino);
        
        System.out.println("\n🏆 RECOMENDACIONES:");
        System.out.println("✅ Mejor balance: " + mejor.getTramos().get(0).getTransporte().getNombre());
        System.out.println("🌱 Más ecológica: " + eco.getTramos().get(0).getTransporte().getNombre());
        System.out.println("⚡ Más rápida: " + rapida.getTramos().get(0).getTransporte().getNombre());
        
        System.out.println("\n¿Quieres ver los detalles de una opción? (1-" + todasRutas.size() + " o 0 para salir)");
        int detalle = leerOpcion();
        if (detalle > 0 && detalle <= todasRutas.size()) {
            System.out.println(todasRutas.get(detalle - 1));
        }
    }
    
    private Ubicacion seleccionarUbicacion(String tipo) {
        System.out.print("\nBuscar " + tipo + " (escribe parte del nombre): ");
        String busqueda = scanner.nextLine();
        
        List<Ubicacion> resultados = controller.buscarUbicaciones(busqueda);
        
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron ubicaciones con ese nombre.");
            return null;
        }
        
        System.out.println("\nUbicaciones encontradas:");
        for (int i = 0; i < resultados.size(); i++) {
            System.out.println((i+1) + ". " + resultados.get(i).getNombre());
        }
        
        System.out.print("Selecciona una (1-" + resultados.size() + "): ");
        int seleccion = leerOpcion();
        
        if (seleccion > 0 && seleccion <= resultados.size()) {
            return resultados.get(seleccion - 1);
        }
        
        return null;
    }
    
    private void verTodasUbicaciones() {
        System.out.println("\n--- UBICACIONES DISPONIBLES ---");
        for (Ubicacion u : controller.getTodasUbicaciones()) {
            System.out.println("• " + u.getNombre());
        }
    }
    
    private void compararRutas() {
        System.out.println("\n--- COMPARAR RUTAS ---");
        
        Ubicacion origen = seleccionarUbicacion("origen");
        if (origen == null) return;
        
        Ubicacion destino = seleccionarUbicacion("destino");
        if (destino == null) return;
        
        List<Ruta> rutas = controller.getTodasRutas(origen, destino);
        if (rutas.isEmpty()) {
            System.out.println("No hay rutas disponibles.");
            return;
        }
        
        System.out.println("\n📊 COMPARATIVA DE RUTAS:");
        System.out.println("---------------------------------------------------");
        System.out.printf("%-12s | %-8s | %-10s | %-10s | %-10s\n", 
            "Transporte", "Tiempo", "Distancia", "CO₂", "Costo");
        System.out.println("---------------------------------------------------");
        
        for (Ruta r : rutas) {
            String trans = r.getTramos().get(0).getTransporte().getNombre();
            System.out.printf("%-12s | %4d min  | %6.1f km  | %5.2f kg  | $%5.2f\n",
                trans,
                r.getTiempoTotal(),
                r.getDistanciaTotal(),
                r.getEmisionesTotal(),
                r.getCostoTotal());
        }
        System.out.println("---------------------------------------------------");
    }
}
