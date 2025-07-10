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
    private String usuario;
    private String contrasenia;
    private String tipoEmpleado;

    public Empleado(String usuario, String contrasenia, String tipoEmpleado, String nombres, String apellidos, String telefono, String direccion) {
        super(nombres, apellidos, telefono, direccion);
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.tipoEmpleado = tipoEmpleado;
    }

    public Empleado(String usuario, String contrasenia, String tipoEmpleado) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.tipoEmpleado = tipoEmpleado;
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

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    @Override
    public String toString() {
        return "Empleado{" + "usuario=" + usuario + ", contrasenia=" + contrasenia + ", tipoEmpleado=" + tipoEmpleado + '}';
    }
    
    
}
