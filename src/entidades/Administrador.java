/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author LOQ
 */
public class Administrador extends Empleado {
    private String IDEmpleado;

    public Administrador(String IDEmpleado, String usuario, String contrasenia, String tipoEmpleado, String nombres, String apellidos, String telefono, String direccion) {
        super(usuario, contrasenia, tipoEmpleado, nombres, apellidos, telefono, direccion);
        this.IDEmpleado = IDEmpleado;
    }

    public Administrador(String IDEmpleado, String usuario, String contrasenia, String tipoEmpleado) {
        super(usuario, contrasenia, tipoEmpleado);
        this.IDEmpleado = IDEmpleado;
    }

    public String getIDEmpleado() {
        return IDEmpleado;
    }

    public void setIDEmpleado(String IDEmpleado) {
        this.IDEmpleado = IDEmpleado;
    }

    @Override
    public String toString() {
        return "Administrador{" + "IDEmpleado=" + IDEmpleado + '}';
    }
    
    
}
