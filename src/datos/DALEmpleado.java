package datos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DALEmpleado {
    private Connection conexion;

    public DALEmpleado() throws ClassNotFoundException {
        try {
            conexion = Conexion.realizarConexion();
        } catch (SQLException ex) {
            System.out.println("Error al conectar a la base de datos: " + ex.getMessage());

        }
    }

    public Object[] obtenerDatosEmpleado(String usuario, String contrasena) {
        try {
            String sql = "SELECT * FROM empleado WHERE usuario = ? AND contrasena = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, contrasena);
            ResultSet rs = ps.executeQuery();
            

            if (rs.next()) {
               int empleadoID = rs.getInt("empleado_id");
               int agenciaID = rs.getInt("agencia_id");
                String nombre = rs.getString("nombres");
                String tipo = rs.getString("tipo_empleado");

                return new Object[]{empleadoID, nombre, tipo, usuario, contrasena, agenciaID};

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


