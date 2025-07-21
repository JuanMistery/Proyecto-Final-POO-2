/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import entidades.EmpleadoAgencia;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author JuanMistery
 */

public class DALEmpleadoAgencia {
    private static Connection cn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    private static Statement st = null;
    
    // Insertar empleado
    public static String insertarEmpleado(EmpleadoAgencia empleado) throws ClassNotFoundException {
        String sql = "INSERT INTO empleado (agencia_id, nombres, apellidos, telefono, direccion, usuario, contrasena) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, empleado.getIDAgencia());
            ps.setString(2, empleado.getNombres());
            ps.setString(3, empleado.getApellidos());
            ps.setString(4, empleado.getTelefono());
            ps.setString(5, empleado.getDireccion());
            ps.setString(6, empleado.getUsuario());
            ps.setString(7, empleado.getContrasenia());
            
            if (ps.executeUpdate() == 0) {
                return "No se insertó el empleado";
            }
            
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    empleado.setIDEmpleado(generatedKeys.getInt(1));
                }
            }
            return null;
            
        } catch (SQLException ex) {
            return ex.getMessage();
        } finally {
            cerrarRecursos();
        }
    }

    // Validar credenciales
    public static EmpleadoAgencia validarCredenciales(String usuario, String contrasenia) throws ClassNotFoundException {
        String sql = "SELECT * FROM empleado WHERE usuario = ? AND contrasena = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, contrasenia);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return new EmpleadoAgencia(
                    rs.getInt("agencia_id"),
                    rs.getInt("empleado_id"),
                    rs.getString("usuario"),
                    rs.getString("contrasena"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getString("telefono"),
                    rs.getString("direccion")
                );
            }
        } catch (SQLException ex) {
            mostrarErrorSQL(ex);
        } finally {
            cerrarRecursos();
        }
        return null;
    }

    // Listar todos los empleados
    public static ArrayList<EmpleadoAgencia> listarEmpleados() throws ClassNotFoundException {
        ArrayList<EmpleadoAgencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleado";
        
        try {
            cn = Conexion.realizarConexion();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            
            while (rs.next()) {
                lista.add(new EmpleadoAgencia(
                    rs.getInt("agencia_id"),
                    rs.getInt("empleado_id"),
                    rs.getString("usuario"),
                    rs.getString("contrasena"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getString("telefono"),
                    rs.getString("direccion")
                ));
            }
        } catch (SQLException ex) {
            mostrarErrorSQL(ex);
        } finally {
            cerrarRecursos();
        }
        return lista;
    }
    
    public static EmpleadoAgencia obtenerPorId(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM empleado WHERE empleado_id = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return new EmpleadoAgencia(
                    rs.getInt("agencia_id"),
                    rs.getInt("empleado_id"),
                    rs.getString("usuario"),
                    rs.getString("contrasena"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getString("telefono"),
                    rs.getString("direccion")
                );
            }
            return null;
        } finally {
            cerrarRecursos();
        }
    }
    
    public static boolean existeUsuario(String usuario) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM empleado WHERE usuario = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, usuario);
            rs = ps.executeQuery();
            
            return rs.next() && rs.getInt(1) > 0;
        } finally {
            cerrarRecursos();
        }
    }
    
    public static int ObtenerID(String usuario) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM empleado WHERE usuario = ?";
        try{
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, usuario);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("empleado_id");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                "Error al obtener empleado: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        } finally {
            cerrarRecursos();
        }
        return 0;
    }
    
    public static ArrayList<EmpleadoAgencia> listarPorAgencia(int agenciaId) throws SQLException, ClassNotFoundException {
        ArrayList<EmpleadoAgencia> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleado WHERE agencia_id = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, agenciaId);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                empleados.add(new EmpleadoAgencia(
                    rs.getInt("agencia_id"),
                    rs.getInt("empleado_id"),
                    rs.getString("usuario"),
                    rs.getString("contrasena"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getString("telefono"),
                    rs.getString("direccion")
                ));
            }
            return empleados;
        } finally {
            cerrarRecursos();
        }
    }

    // Actualizar empleado
    public static String actualizarEmpleado(EmpleadoAgencia empleado) throws ClassNotFoundException {
        String sql = "UPDATE empleado SET agencia_id = ?, nombres = ?, apellidos = ?, telefono = ?, direccion = ?, usuario = ?, contrasenia = ? WHERE empleado_id = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, empleado.getIDAgencia());
            ps.setString(2, empleado.getNombres());
            ps.setString(3, empleado.getApellidos());
            ps.setString(4, empleado.getTelefono());
            ps.setString(5, empleado.getDireccion());
            ps.setString(6, empleado.getUsuario());
            ps.setString(7, empleado.getContrasenia());
            ps.setInt(8, empleado.getIDEmpleado());
            
            if (ps.executeUpdate() == 0) {
                return "No se encontró el empleado con ID: " + empleado.getIDEmpleado();
            }
            return null;
        } catch (SQLException ex) {
            return ex.getMessage();
        } finally {
            cerrarRecursos();
        }
    }

    // Eliminar empleado
    public static String eliminarEmpleado(int idEmpleado) throws ClassNotFoundException {
        String sql = "DELETE FROM empleado WHERE empleado_id = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, idEmpleado);
            
            if (ps.executeUpdate() == 0) {
                return "No se encontró el empleado con ID: " + idEmpleado;
            }
            return null;
        } catch (SQLException ex) {
            return ex.getMessage();
        } finally {
            cerrarRecursos();
        }
    }

    // Método privado para cerrar recursos
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

    // Método privado para mostrar errores SQL
    private static void mostrarErrorSQL(SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error de base de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}