/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import entidades.Registro;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author JuanMistery
 */

public class DALRegistro {
    private static Connection cn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    private static Statement st = null;

    /**
     * Registra una nueva actividad en el sistema
     * @param registro Objeto Registro a insertar
     * @return null si éxito, mensaje de error si falla
     */
    public static String insertarRegistro(Registro registro) throws ClassNotFoundException {
        String sql = "INSERT INTO registro (empleado_id,empleado_rol, fecha, tipo_evento, evento, descripcion) VALUES (?,?, ?, ?, ?, ?)";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, registro.getEmpleadoID());
            ps.setString(2, registro.getRolEmpleado());
            ps.setTimestamp(3, Timestamp.valueOf(registro.getFecha()));
            ps.setString(4, registro.getTipoEvento());
            ps.setString(5, registro.getModulo());
            ps.setString(6, registro.getDescripcion());
            
            if (ps.executeUpdate() == 0) {
                return "No se pudo registrar la actividad";
            }
            
            // Obtener ID generado
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    registro.setRegistroID(generatedKeys.getInt(1));
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
     * Obtiene un registro por su ID
     * @param id ID del registro
     * @return Objeto Registro o null si no existe
     */
    public static Registro obtenerPorId(int id) {
        String sql = "SELECT * FROM registro WHERE registro_id = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return new Registro(
                    rs.getInt("registro_id"),
                    rs.getInt("empleado_id"),
                    rs.getString("empleado_rol"),
                    rs.getTimestamp("fecha").toLocalDateTime(),
                    rs.getString("tipo_evento"),
                    rs.getString("evento"),
                    rs.getString("descripcion")
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
     * Lista todos los registros de actividad
     * @return ArrayList de Registro ordenados por fecha descendente
     */
    public static ArrayList<Registro> listarTodos() {
        ArrayList<Registro> registros = new ArrayList<>();
        String sql = "SELECT * FROM registro ORDER BY fecha DESC";
        
        try {
            cn = Conexion.realizarConexion();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            
            while (rs.next()) {
                registros.add(new Registro(
                    rs.getInt("registro_id"),
                    rs.getInt("empleado_id"),
                    rs.getString("empleado_rol"),
                    rs.getTimestamp("fecha").toLocalDateTime(),
                    rs.getString("tipo_evento"),
                    rs.getString("evento"),
                    rs.getString("descripcion")
                ));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            mostrarError(ex);
        } finally {
            cerrarRecursos();
        }
        return registros;
    }

    /**
     * Filtra registros por tipo de evento
     * @param tipoEvento Tipo de evento a filtrar (LOGIN, INSERT, UPDATE, etc.)
     * @return ArrayList de Registro filtrados
     */
    public static ArrayList<Registro> filtrarPorTipo(String tipoEvento) {
        ArrayList<Registro> registros = new ArrayList<>();
        String sql = "SELECT * FROM registro WHERE tipo_evento = ? ORDER BY fecha DESC";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, tipoEvento);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                registros.add(new Registro(
                    rs.getInt("registro_id"),
                    rs.getInt("empleado_id"),
                    rs.getString("empleado_rol"),
                    rs.getTimestamp("fecha").toLocalDateTime(),
                    rs.getString("tipo_evento"),
                    rs.getString("evento"),
                    rs.getString("descripcion")
                ));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            mostrarError(ex);
        } finally {
            cerrarRecursos();
        }
        return registros;
    }

    /**
     * Filtra registros por rango de fechas
     * @param desde Fecha inicial
     * @param hasta Fecha final
     * @return ArrayList de Registro filtrados
     */
    public static ArrayList<Registro> filtrarPorFecha(LocalDate desde, LocalDate hasta) {
        ArrayList<Registro> registros = new ArrayList<>();
        String sql = "SELECT * FROM registro WHERE fecha BETWEEN ? AND ? ORDER BY fecha DESC";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(desde));
            ps.setDate(2, Date.valueOf(hasta));
            rs = ps.executeQuery();
            
            while (rs.next()) {
                registros.add(new Registro(
                    rs.getInt("registro_id"),
                    rs.getInt("empleado_id"),
                    rs.getString("empleado_rol"),
                    rs.getTimestamp("fecha").toLocalDateTime(),
                    rs.getString("tipo_evento"),
                    rs.getString("evento"),
                    rs.getString("descripcion")
                ));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            mostrarError(ex);
        } finally {
            cerrarRecursos();
        }
        return registros;
    }

    /**
     * Elimina registros antiguos (ejemplo: mayores a 1 año)
     * @return Número de registros eliminados
     */
    public static int limpiarRegistrosAntiguos() throws ClassNotFoundException {
        String sql = "DELETE FROM registro WHERE fecha < ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(LocalDate.now().minusYears(1)));
            
            return ps.executeUpdate();
        } catch (SQLException ex) {
            mostrarError(ex);
            return 0;
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
