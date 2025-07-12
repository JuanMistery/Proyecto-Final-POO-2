/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author JuanMistery
 */
public class Garaje {
    private int garajeID;
    private String nombre;
    private String direccion;

    public Garaje() {
    }

    public Garaje(int garajeID, String nombre, String direccion) {
        this.garajeID = garajeID;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public int getGarajeID() {
        return garajeID;
    }

    public void setGarajeID(int garajeID) {
        this.garajeID = garajeID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Garaje{" + "garajeID=" + garajeID + ", nombre=" + nombre + ", direccion=" + direccion + '}';
    }
}
