package logica;

import entidades.Empleado;
import entidades.EmpleadoAgencia;

public class BLEmpleadoAgenciaCreator extends BLEmpleadoCreator {

    private int IDAgencia; 

    public BLEmpleadoAgenciaCreator(int id, String nombre, String usuario, String contrasena, String tipoEmpleado) {
        super(id,null, usuario, contrasena, tipoEmpleado);
        this.IDAgencia = IDAgencia;
    }

    @Override
    public Empleado crearUsuario() {
        return new EmpleadoAgencia(id, IDAgencia, usuario, contrasena, tipo);
    }
}
