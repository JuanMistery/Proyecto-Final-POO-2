/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author LOQ
 */
public class Agencia {
    private int IDAgencia;
    private String nombreAgencia;
    private String direccionAgencia;

    public Agencia(int IDAgencia, String nombreAgencia, String direccionAgencia) {
        this.IDAgencia = IDAgencia;
        this.nombreAgencia = nombreAgencia;
        this.direccionAgencia = direccionAgencia;
    }

    public Agencia(String nombreAgencia, String direccionAgencia) {
        this.nombreAgencia = nombreAgencia;
        this.direccionAgencia = direccionAgencia;
    }

    public int getIDAgencia() {
        return IDAgencia;
    }

    public void setIDAgencia(int IDAgencia) {
        this.IDAgencia = IDAgencia;
    }

    public String getNombreAgencia() {
        return nombreAgencia;
    }

    public void setNombreAgencia(String nombreAgencia) {
        this.nombreAgencia = nombreAgencia;
    }

    public String getDireccionAgencia() {
        return direccionAgencia;
    }

    public void setDireccion(String direccionAgencia) {
        this.direccionAgencia = direccionAgencia;
    }

    @Override
    public String toString() {
        return "Agencia{" + "IDAgencia=" + IDAgencia + ", nombreAgencia=" + nombreAgencia + ", direccion=" + direccionAgencia + '}';
    }
    
    
}
