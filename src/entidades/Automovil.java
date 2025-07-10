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
    private int automovilID;
    private int garajeID;
    private String placa;
    private String modelo;
    private String color;
    private String marca;
    private int estado;

    public Automovil(int automovilID, int garajeID, String placa, String modelo, String color, String marca, int estado) {
        this.automovilID = automovilID;
        this.garajeID = garajeID;
        this.placa = placa;
        this.modelo = modelo;
        this.color = color;
        this.marca = marca;
        this.estado = estado;
    }

    public Automovil() {
    }

    public int getAutomovilID() {
        return automovilID;
    }

    public void setAutomovilID(int automovilID) {
        this.automovilID = automovilID;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Automovil{" + "automovilID=" + automovilID + ", garajeID=" + garajeID + ", placa=" + placa + ", modelo=" + modelo + ", color=" + color + ", marca=" + marca + ", estado=" + estado + '}';
    }
}
