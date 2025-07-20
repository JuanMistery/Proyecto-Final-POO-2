/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.businessLogic;

import datos.DALRegistro;
import entidades.Registro;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JuanMistery
 */

public class BLRegistro {
    /**
     * Registra una nueva actividad en el sistema
     * @param empleadoID ID del empleado que realiza la acción
     * @param tipoEvento Tipo de evento (LOGIN, INSERT, UPDATE, DELETE)
     * @param modulo Módulo afectado (CLIENTES, VEHÍCULOS, etc.)
     * @param descripcion Descripción detallada del evento
     * @return String con mensaje de error (null si éxito)
     */
    public static String registrarActividad(int empleadoID, String rolEmpleado, String tipoEvento, String modulo, String descripcion) {
        try {
            Registro nuevoRegistro = new Registro(
                0, // ID se autogenera
                empleadoID,
                rolEmpleado,
                LocalDateTime.now(),
                tipoEvento,
                modulo,
                descripcion
            );
            return DALRegistro.insertarRegistro(nuevoRegistro);
        } catch (ClassNotFoundException ex) {
            return "Error: " + ex.getMessage();
        }
    }

    /**
     * Obtiene todos los registros de actividad
     * @return ArrayList<Registro> con todos los registros
     */
    public static ArrayList<Registro> obtenerTodosRegistros() {
        return DALRegistro.listarTodos();
    }

    /**
     * Filtra registros por tipo de evento
     * @param tipoEvento Tipo de evento (LOGIN, INSERT, etc.)
     * @return ArrayList<Registro> filtrado
     */
    public static ArrayList<Registro> filtrarPorTipo(String tipoEvento) {
        return DALRegistro.filtrarPorTipo(tipoEvento);
    }

    /**
     * Filtra registros por rango de fechas
     * @param desde Fecha de inicio
     * @param hasta Fecha de fin
     * @return ArrayList<Registro> filtrado
     */
    public static ArrayList<Registro> filtrarPorFecha(LocalDate desde, LocalDate hasta) {
        return DALRegistro.filtrarPorFecha(desde, hasta);
    }

    /**
     * Carga los registros en una JTable
     * @param tabla JTable donde se mostrarán los datos
     * @param registros Lista de registros a mostrar
     */
    
    public static void cargarRegistrosEnTabla(JTable tabla, ArrayList<Registro> registros) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer la tabla de solo lectura
            }
        };

        // Definir columnas
        model.setColumnIdentifiers(new String[]{
            "ID", 
            "Empleado", 
            "Fecha", 
            "Tipo Evento", 
            "Descripción"
        });

        // Llenar con datos
        for (Registro reg : registros) {
            model.addRow(new Object[]{
                reg.getRegistroID(),
                obtenerEmpleado(reg.getEmpleadoID(),reg.getRolEmpleado()),
                reg.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                reg.getTipoEvento(),
                reg.getDescripcion()
            });
        }

        // Asignar el modelo a la tabla
        tabla.setModel(model);

        // Ajustar ancho de columnas
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
        tabla.getColumnModel().getColumn(1).setPreferredWidth(100); // Empleado
        tabla.getColumnModel().getColumn(2).setPreferredWidth(120); // Fecha
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100); // Tipo Evento
        tabla.getColumnModel().getColumn(4).setPreferredWidth(300); // Descripción

        // Actualizar la tabla
        tabla.revalidate();
        tabla.repaint();
    }

    /**
     * Método auxiliar para obtener el nombre del empleado
     * @param empleadoID ID del empleado
     * @return Nombre del empleado o "N/A" si no se encuentra
     */
    private static String obtenerEmpleado(int empleadoID,String rol) {
        if(empleadoID>0){
            return rol+" "+ empleadoID;
        } else {
            return rol;
        }
    }

    /**
     * Elimina registros antiguos (ejemplo: mayores a 1 año)
     * @return Mensaje de éxito/error
     */
    public static String limpiarRegistrosAntiguos() {
        try {
            int registrosEliminados = DALRegistro.limpiarRegistrosAntiguos();
            return "Se eliminaron " + registrosEliminados + " registros antiguos";
        } catch (ClassNotFoundException ex) {
            return "Error al limpiar registros: " + ex.getMessage();
        }
    }
}
