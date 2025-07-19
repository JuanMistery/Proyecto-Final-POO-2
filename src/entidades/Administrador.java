

package entidades;

import presentacion.FrmPrincipalAdministrador;

public class Administrador extends Empleado {

    // Constructor completo con datos personales
    public Administrador(int id, String usuario, String contrasenia, String tipoEmpleado,
                         String nombres, String apellidos, String telefono, String direccion) {
        super(id, usuario, contrasenia, tipoEmpleado, nombres, apellidos, telefono, direccion);
    }

    // Constructor con datos de acceso
    public Administrador(int id, String usuario, String contrasenia, String tipoEmpleado) {
        super(id, usuario, contrasenia, tipoEmpleado);
    }

    @Override
    public void abrirInterfaz() {
        new FrmPrincipalAdministrador().setVisible(true);
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "ID=" + getID() +
                ", usuario=" + getUsuario() +
                ", tipoEmpleado=" + getTipoEmpleado() +
                '}';
    }
}
