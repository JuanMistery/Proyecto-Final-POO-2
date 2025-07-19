package logica;

import datos.DALEmpleado;
import entidades.Empleado;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BLEmpleado {
    public static Empleado login(String usuario, String contrasena){
        DALEmpleado dalEmpleado = null;
        try {
            dalEmpleado = new DALEmpleado();
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al cargar el driver: " + ex.getMessage());
        }

        Object[] datos = dalEmpleado.obtenerDatosEmpleado(usuario, contrasena);

        if (datos != null) {
            int id = (int) datos[0];
            String nombre = (String) datos[1];
            String tipo = (String) datos[2];
            String user = (String) datos[3];
            String pass = (String) datos[4];
            int agenciaID = (int) datos[5];

            BLEmpleadoCreator creator;
            if (tipo.equalsIgnoreCase("Administrador")) {
                creator = new BLAdministradorCreator(id, nombre, user, pass, tipo);
            } else if (tipo.equalsIgnoreCase("Empleado")) {
                creator = new BLEmpleadoAgenciaCreator(id, nombre,user, pass, tipo);

            } else {
                return null;
            }

            return creator.crearUsuario();
        }

        return null;
    }
}