/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author LOQ
 */
public class Empleado extends Persona {
    private int IDEmpleado;
    private String usuario;
    private String contrasenia;

    public Empleado(int IDEmpleado, String usuario, String contrasenia, String nombres, String apellidos, String telefono, String direccion) {
        super(nombres, apellidos, telefono, direccion);
        this.IDEmpleado = IDEmpleado;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public Empleado() {
    }    

    public int getIDEmpleado() {
        return IDEmpleado;
    }

    public void setIDEmpleado(int IDEmpleado) {
        this.IDEmpleado = IDEmpleado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "Empleado{" + "usuario=" + usuario + ", contrasenia=" + contrasenia + '}';
    }
    
    
}