package logica;

import entidades.Administrador;
import entidades.Empleado;

public class BLAdministradorCreator extends BLEmpleadoCreator {

    public BLAdministradorCreator(int id, String nombre, String usuario, String contrasena, String tipoEmpleado) {
        super(id,nombre, usuario, contrasena, tipoEmpleado);
    }

    @Override
    public Empleado crearUsuario() {
        return new Administrador(id, usuario, contrasena, tipo);
    }
}
