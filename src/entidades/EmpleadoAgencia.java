/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import presentacion.FrmPrincipalEmpleado;

public class EmpleadoAgencia extends Empleado {
    private int IDAgencia;

    // Constructor completo con datos personales
    public EmpleadoAgencia(int id, int IDAgencia, String usuario, String contrasenia, String tipoEmpleado,
                           String nombres, String apellidos, String telefono, String direccion) {
        super(id, usuario, contrasenia, tipoEmpleado, nombres, apellidos, telefono, direccion);
        this.IDAgencia = IDAgencia;
    }

    // Constructor con datos de acceso
    public EmpleadoAgencia(int id, int IDAgencia, String usuario, String contrasenia, String tipoEmpleado) {
        super(id, usuario, contrasenia, tipoEmpleado);
        this.IDAgencia = IDAgencia;
    }

    public int getIDAgencia() {
        return IDAgencia;
    }

    public void setIDAgencia(int IDAgencia) {
        this.IDAgencia = IDAgencia;
    }

    @Override
    public void abrirInterfaz() {
        new FrmPrincipalEmpleado().setVisible(true);
    }

    @Override
    public String toString() {
        return "EmpleadoAgencia{" +
                "ID=" + getID() +
                ", IDAgencia=" + IDAgencia +
                ", usuario=" + getUsuario() +
                ", tipoEmpleado=" + getTipoEmpleado() +
                '}';
    }
}
