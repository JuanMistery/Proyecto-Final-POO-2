/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import entidades.Administrador;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
/**
 *
 * @author JuanMistery
 */
public class DALAdministrador {
    private static Connection cn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    private static Statement st = null;  // Atributo agregado
    
    // Insertar administrador
    public static String insertarAdministrador(Administrador admin) throws ClassNotFoundException {
        String sql = "INSERT INTO Administrador (nombres, apellidos, telefono, direccion, usuario, contrasena) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, admin.getNombres());
            ps.setString(2, admin.getApellidos());
            ps.setString(3, admin.getTelefono());
            ps.setString(4, admin.getDireccion());
            ps.setString(5, admin.getUsuario());
            ps.setString(6, admin.getContrasenia());
            
            if (ps.executeUpdate() == 0) {
                return "No se insertó el administrador";
            }
            
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    admin.setIDEmpleado(generatedKeys.getInt(1));
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
    public static Administrador validarCredenciales(String usuario, String contrasenia) throws ClassNotFoundException {
        String sql = "SELECT * FROM Administrador WHERE usuario = ? AND contrasena = ?";
        
        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, contrasenia);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return new Administrador(
                    rs.getInt("admin_id"),
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
    
    public static boolean existeUsuario(String usuario) throws ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM Administrador WHERE usuario = ?";

        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, usuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                "Error al verificar usuario: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        } finally {
            cerrarRecursos();
        }
        return false;
    }
    
    public static Administrador obtenerPorId(int id) throws ClassNotFoundException {
        String sql = "SELECT * FROM Administrador WHERE admin_id = ?";

        try {
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new Administrador(
                    rs.getInt("admin_id"),
                    rs.getString("usuario"),
                    rs.getString("contrasena"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getString("telefono"),
                    rs.getString("direccion")
                );
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                "Error al obtener administrador: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        } finally {
            cerrarRecursos();
        }
        return null;
    }
    
    public static int obtenerIdPorUsuario(String usuario) throws ClassNotFoundException{
        String sql = "SELECT * FROM Administrador WHERE usuario = ?";
        try{
            cn = Conexion.realizarConexion();
            ps = cn.prepareStatement(sql);
            ps.setString(1, usuario);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("admin_id");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, 
                "Error al obtener administrador: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        } finally {
            cerrarRecursos();
        }
        return 0;
    }

    // Listar todos los administradores (usa Statement)
    public static ArrayList<Administrador> listarAdministradores() throws ClassNotFoundException {
        ArrayList<Administrador> lista = new ArrayList<>();
        String sql = "SELECT * FROM Administrador";
        
        try {
            cn = Conexion.realizarConexion();
            st = cn.createStatement();  // Usa el atributo st declarado
            rs = st.executeQuery(sql);
            
            while (rs.next()) {
                lista.add(new Administrador(
                    rs.getInt("admin_id"),
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

    // Método privado para cerrar recursos
    private static void cerrarRecursos() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (st != null) st.close();  // Cierra el Statement
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

