/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import entidades.ReservaCoche;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author JuanMistery
 */
public class DALReservaCoche {
    private static Connection cn = null;
    private static Statement st = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    
    public static String insertarReservaCoche(ReservaCoche reservaCoche) {
        String mensaje = null;
        String sql = "INSERT INTO detalle_reserva (reserva_id, placa, precio_alquiler, litros_inicial) VALUES (?, ?, ?, ?)";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, reservaCoche.getReservaID());
            ps.setString(2, reservaCoche.getPlaca());
            ps.setFloat(3, reservaCoche.getPrecioAlquiler());
            ps.setFloat(4, reservaCoche.getLitrosInicial());
            
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
    
    public static ArrayList<ReservaCoche> obtenerCochesPorReserva(int reservaID) {
        ArrayList<ReservaCoche> coches = new ArrayList<>();
        String sql = "SELECT * FROM detalle_reserva WHERE reserva_id = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, reservaID);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                ReservaCoche reservaCoche = new ReservaCoche(
                    rs.getInt("reserva_id"),
                    rs.getString("placa"),
                    rs.getFloat("precio_alquiler"),
                    rs.getFloat("litros_inicial")
                );
                coches.add(reservaCoche);
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
        return coches;
    }
    
    public static ReservaCoche obtenerReservaCoche(int reservaID, String placa) {
        ReservaCoche reservaCoche = null;
        String sql = "SELECT * FROM detalle_reserva WHERE reserva_id = ? AND placa = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, reservaID);
            ps.setString(2, placa);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                reservaCoche = new ReservaCoche(
                    rs.getInt("reserva_id"),
                    rs.getString("placa"),
                    rs.getFloat("precio_alquiler"),
                    rs.getFloat("litros_inicial")
                );
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
        return reservaCoche;
    }
}