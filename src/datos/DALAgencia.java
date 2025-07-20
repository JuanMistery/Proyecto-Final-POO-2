/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import entidades.Agencia;
import java.sql.*;
import java.util.ArrayList;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author JuanMistery
 */

public class DALAgencia {
    private static Connection cn = null;
    private static Statement st = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    
    public static String insertarAgencia(Agencia agencia) {
        String mensaje = null;
        String sql = "INSERT INTO Agencia (nombre_agencia, direccion) VALUES (?, ?)";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, agencia.getNombreAgencia());
            ps.setString(2, agencia.getDireccionAgencia());
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    agencia.setIDAgencia(generatedKeys.getInt(1));
                }
            }
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
    
    public static Agencia obtenerAgencia(int agenciaId) {
        Agencia agencia = null;
        String sql = "SELECT * FROM Agencia WHERE agencia_id = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, agenciaId);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                agencia = new Agencia(rs.getInt("agencia_id"),rs.getString("nombre_agencia"),rs.getString("direccion"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            showMessageDialog(null, ex.getMessage(), "Error", 0);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                showMessageDialog(null, ex.getMessage(), "Error", 0);
            }
        }
        return agencia;
    }
    
    public static ArrayList<Agencia> listarAgencias() {
        ArrayList<Agencia> agencias = new ArrayList<>();
        String sql = "SELECT * FROM Agencia";
        
        try {
            cn = Conexion.realizarConexion();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            
            while (rs.next()) {
                Agencia agencia = new Agencia(rs.getInt("agencia_id"),rs.getString("nombre_agencia"),rs.getString("direccion"));
                agencias.add(agencia);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            showMessageDialog(null, ex.getMessage(), "Error", 0);
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (cn != null) cn.close();
            } catch (SQLException ex) {
                showMessageDialog(null, ex.getMessage(), "Error", 0);
            }
        }
        return agencias;
    }
    
    public static String actualizarAgencia(Agencia agencia) {
        String mensaje = null;
        String sql = "UPDATE Agencia SET nombre_agencia = ?, direccion = ? WHERE agencia_id = ?";
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, agencia.getNombreAgencia());
            ps.setString(2, agencia.getDireccionAgencia());
            ps.setInt(3, agencia.getIDAgencia());
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
    
     public static String eliminarAgencia(int IDAgencia) {
        String mensaje = null;
        String sql = "DELETE FROM Agencia WHERE agencia_id = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, IDAgencia);
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
}
