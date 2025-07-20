/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.businessLogic;

import datos.DALGaraje;
import entidades.Garaje;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JuanMistery
 */

public class BLGaraje {
    // Constantes para validaciones
    private static final int MAX_LONGITUD_NOMBRE = 100;
    private static final int MAX_LONGITUD_DIRECCION = 200;

    /**
     * Registra un nuevo garaje con validaciones
     * @param nombre Nombre del garaje
     * @param direccion Dirección física
     * @return null si éxito, mensaje de error si falla
     */
    public static String registrarGaraje(String nombre, String direccion) throws ClassNotFoundException {
        // Validaciones
        String error = validarDatosGaraje(nombre, direccion);
        if (error != null) return error;

        Garaje garaje = new Garaje(0, nombre, direccion);
        return DALGaraje.insertarGaraje(garaje);
    }

    /**
     * Actualiza un garaje existente
     */
    public static String actualizarGaraje(int garajeId, String nombre, String direccion) throws ClassNotFoundException {
        // Validaciones
        String error = validarDatosGaraje(nombre, direccion);
        if (error != null) return error;

        // Verificar existencia
        if (DALGaraje.obtenerPorId(garajeId) == null) {
            return "No existe un garaje con el ID especificado";
        }

        Garaje garaje = new Garaje(garajeId, nombre, direccion);
        return DALGaraje.actualizarGaraje(garaje);
    }

    /**
     * Elimina un garaje con validaciones
     */
    public static String eliminarGaraje(int garajeId) throws ClassNotFoundException {
        // Verificar que no tenga automóviles asociados
        if (DALGaraje.tieneAutomoviles(garajeId)) {
            return "No se puede eliminar: El garaje tiene automóviles registrados";
        }

        return DALGaraje.eliminarGaraje(garajeId);
    }

    /**
     * Obtiene un garaje por su ID
     */
    public static Garaje obtenerGaraje(int garajeId) {
        return DALGaraje.obtenerPorId(garajeId);
    }
    
    public static Garaje obtenerGaraje(String nombre) {
        return DALGaraje.obtenerGaraje(nombre);
    }

    /**
     * Lista todos los garajes registrados
     */
    public static ArrayList<Garaje> listarGarajes() {
        return DALGaraje.listarTodos();
    }
    
    public static void cargarAgenciasEnComboBox(JComboBox<String> comboBox) {
        // Limpiar el comboBox primero
        comboBox.removeAllItems();
        
        // Obtener todas las agencias desde el DAL
        ArrayList<Garaje> garajes = listarGarajes();
        
        // Agregar cada nombre de agencia al comboBox
        for (Garaje garaje : garajes) {
            comboBox.addItem(garaje.getNombre());
        }
        
        // Opcional: Agregar un ítem vacío al inicio
        comboBox.insertItemAt("-- Seleccione un Garaje --", 0);
        comboBox.setSelectedIndex(0);
    }

    /**
     * Muestra los garajes en una JTable
     */
    public static void mostrarEnTabla(JTable tabla, ArrayList<Garaje> garajes) {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla no editable
            }
        };

        // Columnas
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Dirección");

        // Datos
        for (Garaje garaje : garajes) {
            Object[] fila = {
                garaje.getGarajeID(),
                garaje.getNombre(),
                garaje.getDireccion()
            };
            modelo.addRow(fila);
        }

        tabla.setModel(modelo);
    }

    // Validaciones privadas
    private static String validarDatosGaraje(String nombre, String direccion) {
        if (nombre == null || nombre.trim().isEmpty() || nombre.length() > MAX_LONGITUD_NOMBRE) {
            return "Nombre no válido (máx " + MAX_LONGITUD_NOMBRE + " caracteres)";
        }
        
        if (direccion == null || direccion.trim().isEmpty() || direccion.length() > MAX_LONGITUD_DIRECCION) {
            return "Dirección no válida (máx " + MAX_LONGITUD_DIRECCION + " caracteres)";
        }
        
        return null;
    }

    /**
     * Obtiene los IDs de garaje para un JComboBox
     */
    public static ArrayList<Integer> obtenerIdsParaComboBox() {
        ArrayList<Garaje> garajes = listarGarajes();
        ArrayList<Integer> ids = new ArrayList<>();
        
        for (Garaje garaje : garajes) {
            ids.add(garaje.getGarajeID());
        }
        
        return ids;
    }
}
