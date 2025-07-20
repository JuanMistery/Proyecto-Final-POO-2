/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import javax.swing.*;
import entidades.Automovil;
import datos.DALAutomovil;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JuanMistery
 */

public class BLAutomovil {
    // Constantes para validaciones
    private static final int MAX_LONGITUD_PLACA = 10;
    private static final int MAX_LONGITUD_MODELO = 50;
    private static final int MAX_LONGITUD_MARCA = 30;
    private static final int MAX_LONGITUD_COLOR = 20;
    private static final int ESTADO_DISPONIBLE = 1;
    private static final int ESTADO_OCUPADO = 2;
    private static final int ESTADO_MANTENIMIENTO = 3;

    /**
     * Registra un nuevo automóvil con validaciones
     */
    public static String registrarAutomovil(String placa, String modelo, String color, 
                                          String marca, int estado, int garajeId) throws ClassNotFoundException {
        // Validaciones
        String error = validarDatosAutomovil(placa, modelo, color, marca, estado);
        if (error != null) return error;
        
        // Verificar placa única
        if (DALAutomovil.obtenerPorPlaca(placa) != null) {
            return "La placa ya está registrada";
        }

        Automovil auto = new Automovil(garajeId, placa, modelo, color, marca, estado);
        return DALAutomovil.insertarAutomovil(auto);
    }

    /**
     * Actualiza un automóvil existente
     */
    public static String actualizarAutomovil(String placaOriginal, String nuevaPlaca, String modelo, 
                                            String color, String marca, int estado, int garajeId) throws ClassNotFoundException {
        // Validaciones
        String error = validarDatosAutomovil(nuevaPlaca, modelo, color, marca, estado);
        if (error != null) return error;

        // Verificar existencia
        Automovil autoExistente = DALAutomovil.obtenerPorPlaca(placaOriginal);
        if (autoExistente == null) {
            return "No existe un automóvil con la placa especificada";
        }

        // Verificar nueva placa (si cambió)
        if (!placaOriginal.equals(nuevaPlaca) && DALAutomovil.obtenerPorPlaca(nuevaPlaca) != null) {
            return "La nueva placa ya está registrada";
        }

        Automovil autoActualizado = new Automovil(garajeId, nuevaPlaca, modelo, color, marca, estado);
        return DALAutomovil.actualizarAutomovil(autoActualizado);
    }

    /**
     * Elimina un automóvil por placa
     */
    public static String eliminarAutomovil(String placa) throws ClassNotFoundException {
        // Verificar existencia
        if (DALAutomovil.obtenerPorPlaca(placa) == null) {
            return "No existe un automóvil con la placa especificada";
        }
        return DALAutomovil.eliminarAutomovil(placa);
    }

    /**
     * Obtiene un automóvil por placa
     */
    public static Automovil buscarAutomovil(String placa) throws ClassNotFoundException {
        return DALAutomovil.obtenerPorPlaca(placa);
    }

    /**
     * Lista todos los automóviles de un garaje específico
     */
    public static ArrayList<Automovil> listarPorGaraje(int garajeId) throws ClassNotFoundException {
        return DALAutomovil.listarPorGaraje(garajeId);
    }

    /**
     * Lista todos los automóviles en el sistema
     */
    public static ArrayList<Automovil> listarTodos() throws ClassNotFoundException {
        return DALAutomovil.listarTodos();
    }

    /**
     * Lista automóviles disponibles (estado = 1)
     */
    public static ArrayList<Automovil> listarDisponibles() throws ClassNotFoundException {
        ArrayList<Automovil> todos = DALAutomovil.listarTodos();
        ArrayList<Automovil> disponibles = new ArrayList<>();
        
        for (Automovil auto : todos) {
            if (auto.getEstado() == ESTADO_DISPONIBLE) {
                disponibles.add(auto);
            }
        }
        return disponibles;
    }

    /**
     * Muestra los automóviles en una JTable
     */
    public static void mostrarEnTabla(JTable tabla, ArrayList<Automovil> autos) {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla no editable
            }
        };
        
        // Columnas
        modelo.addColumn("Placa");
        modelo.addColumn("Modelo");
        modelo.addColumn("Marca");
        modelo.addColumn("Color");
        modelo.addColumn("Estado");
        modelo.addColumn("Garaje ID");

        // Datos
        for (Automovil auto : autos) {
            Object[] fila = {
                auto.getPlaca(),
                auto.getModelo(),
                auto.getMarca(),
                auto.getColor(),
                convertirEstadoTexto(auto.getEstado()),
                auto.getGarajeID()
            };
            modelo.addRow(fila);
        }

        tabla.setModel(modelo);
    }

    // Métodos auxiliares privados
    private static String validarDatosAutomovil(String placa, String modelo, String color, 
                                              String marca, int estado) {
        if (placa == null || placa.trim().isEmpty() || placa.length() > MAX_LONGITUD_PLACA) {
            return "Placa no válida (máx " + MAX_LONGITUD_PLACA + " caracteres)";
        }
        
        if (modelo == null || modelo.trim().isEmpty() || modelo.length() > MAX_LONGITUD_MODELO) {
            return "Modelo no válido (máx " + MAX_LONGITUD_MODELO + " caracteres)";
        }
        
        if (marca == null || marca.trim().isEmpty() || marca.length() > MAX_LONGITUD_MARCA) {
            return "Marca no válida (máx " + MAX_LONGITUD_MARCA + " caracteres)";
        }
        
        if (color == null || color.trim().isEmpty() || color.length() > MAX_LONGITUD_COLOR) {
            return "Color no válido (máx " + MAX_LONGITUD_COLOR + " caracteres)";
        }
        
        if (estado < 1 || estado > 3) {
            return "Estado no válido (1-Disponible, 2-Ocupado, 3-Mantenimiento)";
        }
        
        return null;
    }

    private static String convertirEstadoTexto(int estado) {
        switch (estado) {
            case ESTADO_DISPONIBLE: return "Disponible";
            case ESTADO_OCUPADO: return "Ocupado";
            case ESTADO_MANTENIMIENTO: return "Mantenimiento";
            default: return "Desconocido";
        }
    }
}