/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import entidades.Cliente;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author JuanMistery
 */

public class DALCliente {
    private static Connection cn = null;
    private static Statement st = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    
    public static String insertarCliente(Cliente cliente) {
        String mensaje = null;
        String sql = "INSERT INTO Cliente (dni, nombres, apellidos, telefono, direccion, sponsor_id) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            cn = Conexion.realizarConexion();
            
            // Verificar si el sponsor ya patrocina a alguien (solo si tiene sponsor)
            if (cliente.getSponsorID() != 0) {
                if (verificarSponsorOcupado(cliente.getSponsorID())) {
                    return "El cliente sponsor ya está patrocinando a otro cliente";
                }
            }
            
            ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cliente.getDNI());
            ps.setString(2, cliente.getNombres());
            ps.setString(3, cliente.getApellidos());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getDireccion());
            
            // Manejo del sponsor (puede ser NULL)
            if (cliente.getSponsorID() == 0) {
                ps.setNull(6, Types.INTEGER);
            } else {
                ps.setInt(6, cliente.getSponsorID());
            }
            
            ps.executeUpdate();
            
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cliente.setClienteID(generatedKeys.getInt(1));
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
    
    public static boolean verificarSponsorOcupado(int sponsorID) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Cliente WHERE sponsor_id = ?";
        ps = cn.prepareStatement(sql);
        ps.setInt(1, sponsorID);
        rs = ps.executeQuery();
        
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
        return false;
    }
    
    public static Cliente obtenerCliente(int clienteID) {
        Cliente cliente = null;
        String sql = "SELECT * FROM Cliente WHERE cliente_id = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, clienteID);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                cliente = new Cliente(
                    rs.getInt("cliente_id"),
                    rs.getString("dni"),
                    rs.getObject("sponsor_id") != null ? rs.getInt("sponsor_id") : 0,
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getString("telefono"),
                    rs.getString("direccion")
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
        return cliente;
    }
    
    public static ArrayList<Cliente> listarClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        
        try {
            cn = Conexion.realizarConexion();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            
            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("cliente_id"),
                    rs.getString("dni"),
                    rs.getObject("sponsor_id") != null ? rs.getInt("sponsor_id") : 0,
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getString("telefono"),
                    rs.getString("direccion")
                );
                clientes.add(cliente);
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
        return clientes;
    }
    
    public static String actualizarCliente(Cliente cliente) {
        String mensaje = null;
        String sql = "UPDATE Cliente SET dni = ?, nombres = ?, apellidos = ?, telefono = ?, direccion = ?, sponsor_id = ? WHERE cliente_id = ?";
        
        try {
            cn = Conexion.realizarConexion();
            
            // Verificar si el nuevo sponsor ya patrocina a alguien (solo si tiene sponsor)
            if (cliente.getSponsorID() != 0) {
                if (verificarSponsorOcupado(cliente.getSponsorID())) {
                    return "El cliente sponsor ya está patrocinando a otro cliente";
                }
            }
            
            ps = cn.prepareStatement(sql);
            ps.setString(1, cliente.getDNI());
            ps.setString(2, cliente.getNombres());
            ps.setString(3, cliente.getApellidos());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getDireccion());
            
            // Manejo del sponsor (puede ser NULL)
            if (cliente.getSponsorID() == 0) {
                ps.setNull(6, Types.INTEGER);
            } else {
                ps.setInt(6, cliente.getSponsorID());
            }
            
            ps.setInt(7, cliente.getClienteID());
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
    
    public static String eliminarCliente(int clienteID) {
        String mensaje = null;
        String sql = "DELETE FROM Cliente WHERE cliente_id = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, clienteID);
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