/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author JuanMistery
 */
public class Automovil {
    private int garajeID;
    private String placa;
    private String modelo;
    private String color;
    private String marca;
    private String estado;

    public Automovil(int garajeID, String placa, String modelo, String color, String marca, String estado) {
        this.garajeID = garajeID;
        this.placa = placa;
        this.modelo = modelo;
        this.color = color;
        this.marca = marca;
        this.estado = estado;
    }

    public Automovil() {
    }

    public int getGarajeID() {
        return garajeID;
    }

    public void setGarajeID(int garajeID) {
        this.garajeID = garajeID;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Automovil{" +", garajeID=" + garajeID + ", placa=" + placa + ", modelo=" + modelo + ", color=" + color + ", marca=" + marca + ", estado=" + estado + '}';
    }
}
