/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author LOQ
 */
public class EmpleadoAgencia extends Empleado {
    private String IDEmpleadoAgencia;
    private String IDAgencia;

    public EmpleadoAgencia(String IDEmpleadoAgencia, String IDAgencia, String usuario, String contrasenia, String tipoEmpleado, String nombres, String apellidos, String telefono, String direccion) {
        super(usuario, contrasenia, tipoEmpleado, nombres, apellidos, telefono, direccion);
        this.IDEmpleadoAgencia = IDEmpleadoAgencia;
        this.IDAgencia = IDAgencia;
    }

    public EmpleadoAgencia(String IDEmpleadoAgencia, String IDAgencia, String usuario, String contrasenia, String tipoEmpleado) {
        super(usuario, contrasenia, tipoEmpleado);
        this.IDEmpleadoAgencia = IDEmpleadoAgencia;
        this.IDAgencia = IDAgencia;
    }

    public String getIDEmpleadoAgencia() {
        return IDEmpleadoAgencia;
    }

    public void setIDEmpleadoAgencia(String IDEmpleadoAgencia) {
        this.IDEmpleadoAgencia = IDEmpleadoAgencia;
    }

    public String getIDAgencia() {
        return IDAgencia;
    }

    public void setIDAgencia(String IDAgencia) {
        this.IDAgencia = IDAgencia;
    }

    @Override
    public String toString() {
        return "EmpleadoAgencia{" + "IDEmpleadoAgencia=" + IDEmpleadoAgencia + ", IDAgencia=" + IDAgencia + '}';
    }
    
   
}
