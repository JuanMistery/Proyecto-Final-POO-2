/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.businessLogic;

import entidades.Agencia;
import datos.DALAgencia;
import java.util.ArrayList;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.*;
import javax.swing.table.*;
/**
 *
 * @author JuanMistery
 */
public class BLAgencia {
    
    public static int insertarAgencia(String nombre, String direccion) {
        // Validaciones de negocio
        if (nombre == null || nombre.trim().isEmpty() || nombre.trim().length() > 100) {
            showMessageDialog(null, "Nombre no válido (debe tener entre 1 y 100 caracteres)", "Error", 0);
            return 1;
        }
        
        if (direccion == null || direccion.trim().isEmpty() || direccion.trim().length() > 200) {
            showMessageDialog(null, "Dirección no válida (debe tener entre 1 y 200 caracteres)", "Error", 0);
            return 2;
        }
        
        // Crear y guardar la agencia
        Agencia agencia = new Agencia(nombre, direccion);
        
        String resultado = DALAgencia.insertarAgencia(agencia);
        
        if (resultado == null) {
            showMessageDialog(null, "Agencia registrada exitosamente con ID: " + agencia.getIDAgencia(), "Éxito", 1);
            return 0;
        } else {
            showMessageDialog(null, "Error al registrar: " + resultado, "Error", 0);
            return 3;
        }
    }
    
    public static void cargarAgenciasEnComboBox(JComboBox<String> comboBox) {
        // Limpiar el comboBox primero
        comboBox.removeAllItems();
        
        // Obtener todas las agencias desde el DAL
        ArrayList<Agencia> agencias = DALAgencia.listarAgencias();
        
        // Agregar cada nombre de agencia al comboBox
        for (Agencia agencia : agencias) {
            comboBox.addItem(agencia.getNombreAgencia());
        }
        
        // Opcional: Agregar un ítem vacío al inicio
        comboBox.insertItemAt("-- Seleccione una agencia --", 0);
        comboBox.setSelectedIndex(0);
    }
    
    public static Agencia obtenerAgencia(int agenciaId) {
        return DALAgencia.obtenerAgencia(agenciaId);
    }
    
    public static Agencia obtenerAgencia(String nombre) {
        return DALAgencia.obtenerAgencia(nombre);
    }
    
    public static ArrayList<Agencia> listarAgencias() {
        return DALAgencia.listarAgencias();
    }
    
    public static int actualizarAgencia(int agenciaId, String nombre, String direccion) {
        // Validaciones
        if (nombre == null || nombre.trim().isEmpty() || nombre.trim().length() > 100) {
            showMessageDialog(null, "Nombre no válido", "Error", 0);
            return 1;
        }
        
        if (direccion == null || direccion.trim().isEmpty() || direccion.trim().length() > 200) {
            showMessageDialog(null, "Dirección no válida", "Error", 0);
            return 2;
        }
        
        // Verificar si la agencia existe
        Agencia agenciaExistente = DALAgencia.obtenerAgencia(agenciaId);
        if (agenciaExistente == null) {
            showMessageDialog(null, "Agencia no encontrada", "Error", 0);
            return 3;
        }
        
        // Actualizar la agencia
        Agencia agenciaActualizada = new Agencia(agenciaId, nombre, direccion);
        String resultado = DALAgencia.actualizarAgencia(agenciaActualizada);
        
        if (resultado == null) {
            showMessageDialog(null, "Agencia actualizada exitosamente", "Éxito", 1);
            return 0;
        } else {
            showMessageDialog(null, "Error al actualizar: " + resultado, "Error", 0);
            return 4;
        }
    }
    
    public static int eliminarAgencia(int IDAgencia) {
        // Verificar si la agencia existe
        Agencia agenciaExistente = DALAgencia.obtenerAgencia(IDAgencia);
        if (agenciaExistente == null) {
            showMessageDialog(null, "Agencia no encontrada", "Error", 0);
            return 1;
        }
        
        String resultado = DALAgencia.eliminarAgencia(IDAgencia);
        
        if (resultado == null) {
            showMessageDialog(null, "Agencia eliminada exitosamente", "Éxito", 1);
            return 0;
        } else {
            showMessageDialog(null, "Error al eliminar: " + resultado, "Error", 0);
            return 2;
        }
    }
    
    public static void actualizarTablaAgencias(JTable table) {
        // Definir las columnas
        String[] columnNames = {"ID", "Nombre", "Dirección"};
        
        // Crear el modelo de tabla
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Todas las celdas no editables
                return false;
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                // Tipo de datos para cada columna (para mejor renderizado)
                if (columnIndex == 0) return Integer.class;  // ID
                return String.class;  // Las demás columnas son String
            }
        };
        
        // Obtener datos de la capa DAL
        ArrayList<Agencia> agencias = DALAgencia.listarAgencias();
        
        // Llenar el modelo con los datos
        for (Agencia agencia : agencias) {
            Object[] rowData = {
                agencia.getIDAgencia(),
                agencia.getNombreAgencia(),
                agencia.getDireccionAgencia()
            };
            model.addRow(rowData);
        }
        
        // Aplicar el modelo a la tabla
        table.setModel(model);
        
        // Configurar propiedades adicionales de la tabla
        configurarPropiedadesTabla(table);
    }

    private static void configurarPropiedadesTabla(JTable table) {
        // Habilitar ordenamiento al hacer clic en columnas
        table.setAutoCreateRowSorter(true);
        
        // Ajustar ancho de columnas
        table.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
        table.getColumnModel().getColumn(1).setPreferredWidth(200); // Nombre
        table.getColumnModel().getColumn(2).setPreferredWidth(300); // Dirección
        
        // Permitir selección de una sola fila a la vez
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Renderizado mejorado para números (ID)
        table.setDefaultRenderer(Integer.class, new DefaultTableCellRenderer() {
            @Override
            public void setValue(Object value) {
                setHorizontalAlignment(SwingConstants.CENTER);
                super.setValue(value);
            }
        });
    }

}
