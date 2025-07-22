/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.businessLogic;

import datos.DALReservaCoche;
import entidades.ReservaCoche;
import java.util.ArrayList;

public class BLReservaCoche {
    
    public static String insertarCochesReserva(int reservaID, ArrayList<String> placas, 
                                            ArrayList<Float> preciosAlquiler, 
                                            ArrayList<Float> litrosIniciales) {
        // Validaciones iniciales
        if (placas == null || preciosAlquiler == null || litrosIniciales == null) {
            return "Los datos de los coches no pueden ser nulos";
        }
        
        if (placas.size() != preciosAlquiler.size() || placas.size() != litrosIniciales.size()) {
            return "La cantidad de placas, precios y litros iniciales no coincide";
        }
        
        if (placas.isEmpty()) {
            return "No se han proporcionado coches para la reserva";
        }
        
        String mensaje = "";
        
        try {
            // Insertar cada coche de la reserva
            for (int i = 0; i < placas.size(); i++) {
                ReservaCoche reservaCoche = new ReservaCoche(
                    reservaID,
                    placas.get(i),
                    preciosAlquiler.get(i),
                    litrosIniciales.get(i)
                );
                
                String resultado = DALReservaCoche.insertarReservaCoche(reservaCoche);
                
                if (resultado != null) {
                    mensaje += "Error al insertar coche " + placas.get(i) + ": " + resultado + "\n";
                }
            }
            
            return null; // Inserción exitosa
            
        } catch (Exception ex) {
            return "Error al procesar los coches de la reserva: " + ex.getMessage();
        }
    }
    
    public static ArrayList<ReservaCoche> obtenerCochesPorReserva(int reservaID) {
        // Validación básica
        if (reservaID <= 0) {
            return new ArrayList<>();
        }
        
        return DALReservaCoche.obtenerCochesPorReserva(reservaID);
    }
    
    public static ReservaCoche obtenerReservaCoche(int reservaID, String placa) {
        if (reservaID <= 0 || placa == null || placa.trim().isEmpty()) {
            return null;
        }
        
        return DALReservaCoche.obtenerReservaCoche(reservaID, placa);
    }
    
    public static float calcularTotalReserva(int reservaID) {
        ArrayList<ReservaCoche> coches = obtenerCochesPorReserva(reservaID);
        float total = 0;
        
        for (ReservaCoche coche : coches) {
            total += coche.getPrecioAlquiler();
        }
        
        return total;
    }
    
    public static boolean existeCocheEnReserva(int reservaID, String placa) {
        if (reservaID <= 0 || placa == null || placa.trim().isEmpty()) {
            return false;
        }
        
        return DALReservaCoche.obtenerReservaCoche(reservaID, placa) != null;
    }
}
