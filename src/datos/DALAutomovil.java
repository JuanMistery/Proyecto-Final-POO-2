/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import entidades.Automovil;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 *
 * @author JuanMistery
 */
public class DALAutomovil {
    private static Connection cn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    private static Statement st = null;

    public static String insertarAutomovil(Automovil auto) throws ClassNotFoundException {
        String sql = "INSERT INTO vehiculo (placa, modelo, color, marca, estado, garaje_id) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, auto.getPlaca());
            ps.setString(2, auto.getModelo());
            ps.setString(3, auto.getColor());
            ps.setString(4, auto.getMarca());
            ps.setString(5, auto.getEstado());
            ps.setInt(6, auto.getGarajeID());
            
            if (ps.executeUpdate() == 0) {
                return "No se pudo insertar el vehículo";
            }
            return null;
        } catch (SQLException ex) {
            return ex.getMessage();
        } finally {
            cerrarRecursos();
        }
    }

    public static Automovil obtenerPorPlaca(String placa) throws ClassNotFoundException {
        String sql = "SELECT * FROM vehiculo WHERE placa = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, placa);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return new Automovil(
                    rs.getInt("garaje_id"),
                    rs.getString("placa"),
                    rs.getString("modelo"),
                    rs.getString("color"),
                    rs.getString("marca"),
                    rs.getString("estado")
                );
            }
        } catch (SQLException ex) {
            mostrarError(ex);
        } finally {
            cerrarRecursos();
        }
        return null;
    }
    
    public static boolean ObtenerDisponibilidad(String placa) throws ClassNotFoundException {
        String sql = "SELECT * FROM vehiculo WHERE placa = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, placa);
            rs = ps.executeQuery();
            if (rs.next()) {
                String estado = rs.getString("estado");
                return estado.equalsIgnoreCase("disponible");
            }
            return false;
        } catch (SQLException ex) {
            mostrarError(ex);
            return false;
        } finally {
            cerrarRecursos();
        }
    }

    public static ArrayList<Automovil> listarPorGaraje(int garajeId) throws ClassNotFoundException {
        ArrayList<Automovil> autos = new ArrayList<>();
        String sql = "SELECT * FROM vehiculo WHERE garaje_id = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, garajeId);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                autos.add(new Automovil(
                    rs.getInt("garaje_id"),
                    rs.getString("placa"),
                    rs.getString("modelo"),
                    rs.getString("color"),
                    rs.getString("marca"),
                    rs.getString("estado")
                ));
            }
        } catch (SQLException ex) {
            mostrarError(ex);
        } finally {
            cerrarRecursos();
        }
        return autos;
    }

    public static ArrayList<Automovil> listarTodos() throws ClassNotFoundException {
        ArrayList<Automovil> autos = new ArrayList<>();
        String sql = "SELECT * FROM vehiculo";
        
        try {
            cn = Conexion.realizarConexion();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            
            while (rs.next()) {
                autos.add(new Automovil(
                    rs.getInt("garaje_id"),
                    rs.getString("placa"),
                    rs.getString("modelo"),
                    rs.getString("color"),
                    rs.getString("marca"),
                    rs.getString("estado")
                ));
            }
        } catch (SQLException ex) {
            mostrarError(ex);
        } finally {
            cerrarRecursos();
        }
        return autos;
    }

    public static String actualizarAutomovil(Automovil auto) throws ClassNotFoundException {
        String sql = "UPDATE vehiculo SET modelo = ?, color = ?, marca = ?, estado = ?, garaje_id = ? WHERE placa = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, auto.getModelo());
            ps.setString(2, auto.getColor());
            ps.setString(3, auto.getMarca());
            ps.setString(4, auto.getEstado());
            ps.setInt(5, auto.getGarajeID());
            ps.setString(6, auto.getPlaca());
            
            if (ps.executeUpdate() == 0) {
                return "No se encontró el vehículo con placa: " + auto.getPlaca();
            }
            return null;
        } catch (SQLException ex) {
            return ex.getMessage();
        } finally {
            cerrarRecursos();
        }
    }

    public static String eliminarAutomovil(String placa) throws ClassNotFoundException {
        String sql = "DELETE FROM vehiculo WHERE placa = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, placa);
            
            if (ps.executeUpdate() == 0) {
                return "No se encontró el vehículo con placa: " + placa;
            }
            return null;
        } catch (SQLException ex) {
            return ex.getMessage();
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
            JOptionPane.showMessageDialog(null, "Error al cerrar recursos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void mostrarError(SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error de base de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

