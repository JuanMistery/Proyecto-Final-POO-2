/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import entidades.Garaje;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author JuanMistery
 */

public class DALGaraje {
    private static Connection cn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    private static Statement st = null;

    /**
     * Registra un nuevo garaje en la base de datos
     * @param garaje Objeto Garaje a insertar
     * @return null si éxito, mensaje de error si falla
     */
    public static String insertarGaraje(Garaje garaje) throws ClassNotFoundException {
        String sql = "INSERT INTO garaje (nombre, direccion) VALUES (?, ?)";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, garaje.getNombre());
            ps.setString(2, garaje.getDireccion());
            
            if (ps.executeUpdate() == 0) {
                return "No se pudo insertar el garaje";
            }
            
            // Obtener ID generado
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    garaje.setGarajeID(generatedKeys.getInt(1));
                }
            }
            return null;
        } catch (SQLException ex) {
            return ex.getMessage();
        } finally {
            cerrarRecursos();
        }
    }

    /**
     * Obtiene un garaje por su ID
     * @param id ID del garaje
     * @return Objeto Garaje o null si no existe
     */
    public static Garaje obtenerPorId(int id) {
        String sql = "SELECT * FROM garaje WHERE garaje_id = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return new Garaje(
                    rs.getInt("garaje_id"),
                    rs.getString("nombre"),
                    rs.getString("direccion")
                );
            }
            return null;
        } catch (SQLException | ClassNotFoundException ex) {
            mostrarError(ex);
            return null;
        } finally {
            cerrarRecursos();
        }
    }

    /**
     * Lista todos los garajes registrados
     * @return ArrayList de Garaje
     */
    public static ArrayList<Garaje> listarTodos() {
        ArrayList<Garaje> garajes = new ArrayList<>();
        String sql = "SELECT * FROM garaje";
        
        try {
            cn = Conexion.realizarConexion();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            
            while (rs.next()) {
                garajes.add(new Garaje(
                    rs.getInt("garaje_id"),
                    rs.getString("nombre"),
                    rs.getString("direccion")
                ));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            mostrarError(ex);
        } finally {
            cerrarRecursos();
        }
        return garajes;
    }

    /**
     * Actualiza un garaje existente
     * @param garaje Objeto Garaje con datos actualizados
     * @return null si éxito, mensaje de error si falla
     */
    public static String actualizarGaraje(Garaje garaje) throws ClassNotFoundException {
        String sql = "UPDATE garaje SET nombre = ?, direccion = ? WHERE garaje_id = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, garaje.getNombre());
            ps.setString(2, garaje.getDireccion());
            ps.setInt(3, garaje.getGarajeID());
            
            if (ps.executeUpdate() == 0) {
                return "No se encontró el garaje con ID: " + garaje.getGarajeID();
            }
            return null;
        } catch (SQLException ex) {
            return ex.getMessage();
        } finally {
            cerrarRecursos();
        }
    }

    /**
     * Elimina un garaje por su ID
     * @param id ID del garaje a eliminar
     * @return null si éxito, mensaje de error si falla
     */
    public static String eliminarGaraje(int id) throws ClassNotFoundException {
        String sql = "DELETE FROM garaje WHERE garaje_id = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            
            if (ps.executeUpdate() == 0) {
                return "No se encontró el garaje con ID: " + id;
            }
            return null;
        } catch (SQLException ex) {
            return ex.getMessage();
        } finally {
            cerrarRecursos();
        }
    }

    /**
     * Verifica si un garaje tiene automóviles asociados
     * @param garajeId ID del garaje
     * @return true si tiene automóviles, false si está vacío
     */
    public static boolean tieneAutomoviles(int garajeId) {
        String sql = "SELECT COUNT(*) FROM vehiculo WHERE garaje_id = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, garajeId);
            rs = ps.executeQuery();
            
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException | ClassNotFoundException ex) {
            mostrarError(ex);
            return false;
        } finally {
            cerrarRecursos();
        }
    }

    private static void cerrarRecursos() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (st != null) st.close();
            if (cn != null) cn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                "Error al cerrar recursos: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void mostrarError(Exception ex) {
        JOptionPane.showMessageDialog(null, 
            "Error de base de datos: " + ex.getMessage(), 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
}