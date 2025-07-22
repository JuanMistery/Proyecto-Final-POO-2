/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import entidades.Reserva;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;
import javax.swing.JOptionPane;

/**
 *
 * @author JuanMistery
 */
public class DALReserva {
    private static Connection cn = null;
    private static Statement st = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    
    public static int insertarReserva(Reserva reserva) {
        String mensaje="";
        String sql = "INSERT INTO reserva (cliente_id, agencia_id, fecha_inicio, fecha_fin, precio_total, entregado) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(3, java.sql.Date.valueOf(reserva.getFechaInicio())); // Para LocalDate
            ps.setDate(4, java.sql.Date.valueOf(reserva.getFechaFin()));   // Para LocalDate
            ps.setInt(1, reserva.getClienteId());
            ps.setInt(2, reserva.getAgenciaId());
            
            ps.setFloat(5, reserva.getPrecioTotal());
            ps.setBoolean(6, reserva.isEntregado());
            
            ps.executeUpdate();
            
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    reserva.setReservaId(generatedKeys.getInt(1));
                }
            }
            return reserva.getReservaId();
        } catch (ClassNotFoundException | SQLException ex) {
            mensaje = ex.getMessage();
            return -1;
        } finally {
            try {
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                mensaje = ex.getMessage();
                return -1;
            }
        }
    }
    
    public static Reserva obtenerReserva(int reservaId) {
        Reserva reserva = null;
        String sql = "SELECT * FROM reserva WHERE reserva_id = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, reservaId);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                reserva = new Reserva();
                reserva.setReservaId(rs.getInt("reserva_id"));
                reserva.setClienteId(rs.getInt("cliente_id"));
                reserva.setAgenciaId(rs.getInt("agencia_id"));
                reserva.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
                reserva.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
                reserva.setPrecioTotal(rs.getFloat("precio_total"));
                reserva.setEntregado(rs.getBoolean("entregado"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return reserva;
    }
    
    public static ArrayList<Reserva> listarReservas() {
        ArrayList<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reserva";
        
        try {
            cn = Conexion.realizarConexion();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            
            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setReservaId(rs.getInt("reserva_id"));
                reserva.setClienteId(rs.getInt("cliente_id"));
                reserva.setAgenciaId(rs.getInt("agencia_id"));
                reserva.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
                reserva.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
                reserva.setPrecioTotal(rs.getFloat("precio_total"));
                reserva.setEntregado(rs.getBoolean("entregado"));
                
                reservas.add(reserva);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return reservas;
    }
    
    public static String actualizarReserva(Reserva reserva) {
        String mensaje = null;
        String sql = "UPDATE reserva SET cliente_id = ?, agencia_id = ?, fecha_inicio = ?, "
                   + "fecha_fin = ?, precio_total = ?, entregado = ? WHERE reserva_id = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            
            ps.setInt(1, reserva.getClienteId());
            ps.setInt(2, reserva.getAgenciaId());
            ps.setDate(3, java.sql.Date.valueOf(reserva.getFechaInicio())); // Solo fecha
            ps.setDate(4, java.sql.Date.valueOf(reserva.getFechaFin()));    // Solo fecha
            ps.setFloat(5, reserva.getPrecioTotal());
            ps.setBoolean(6, reserva.isEntregado());
            ps.setInt(7, reserva.getReservaId());
            
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            mensaje = ex.getMessage();
        } finally {
            try {
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                mensaje = ex.getMessage();
            }
        }
        return mensaje;
    }
    
    public static String eliminarReserva(int reservaId) {
        String mensaje = null;
        String sql = "DELETE FROM reserva WHERE reserva_id = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, reservaId);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            mensaje = ex.getMessage();
        } finally {
            try {
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                mensaje = ex.getMessage();
            }
        }
        return mensaje;
    }
    
    public static int obtenerIDPorCliente(int clienteId) {
        String sql = "SELECT reserva_id FROM reserva WHERE cliente_id = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, clienteId);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("reserva_id");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return 0;
    }
    
    public static boolean marcarComoEntregada(int reservaId) {
        String sql = "UPDATE reserva SET entregado = true WHERE reserva_id = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, reservaId);
            
            return ps.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}