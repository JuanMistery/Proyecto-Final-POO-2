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
    private int IDAgencia;

    public EmpleadoAgencia(int IDAgencia, int IDEmpleado, String usuario, String contrasenia, String nombres, String apellidos, String telefono, String direccion) {
        super(IDEmpleado, usuario, contrasenia, nombres, apellidos, telefono, direccion);
        this.IDAgencia = IDAgencia;
    }

    public EmpleadoAgencia() {
    }

    public int getIDAgencia() {
        return IDAgencia;
    }

    public void setIDAgencia(int IDAgencia) {
        this.IDAgencia = IDAgencia;
    }

    @Override
    public String toString() {
        return "EmpleadoAgencia{" + ", IDAgencia=" + IDAgencia + '}';
    }
    
   
}
