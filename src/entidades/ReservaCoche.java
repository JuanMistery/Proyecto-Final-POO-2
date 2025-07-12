/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author JuanMistery
 */
public class ReservaCoche {
    private int reservaID;
    private String placa;
    private float precioAlquiler;
    private float litrosInicial;

    public ReservaCoche() {
    }

    public ReservaCoche(int reservaID, String placa, float precioAlquiler, float litrosInicial) {
        this.reservaID = reservaID;
        this.placa = placa;
        this.precioAlquiler = precioAlquiler;
        this.litrosInicial = litrosInicial;
    }

    public int getReservaID() {
        return reservaID;
    }

    public void setReservaID(int reservaID) {
        this.reservaID = reservaID;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public float getPrecioAlquiler() {
        return precioAlquiler;
    }

    public void setPrecioAlquiler(float precioAlquiler) {
        this.precioAlquiler = precioAlquiler;
    }

    public float getLitrosInicial() {
        return litrosInicial;
    }

    public void setLitrosInicial(float litrosInicial) {
        this.litrosInicial = litrosInicial;
    }

    @Override
    public String toString() {
        return "ReservaCoche{" + "reservaID=" + reservaID + ", placa=" + placa + ", precioAlquiler=" + precioAlquiler + ", litrosInicial=" + litrosInicial + '}';
    }
}
