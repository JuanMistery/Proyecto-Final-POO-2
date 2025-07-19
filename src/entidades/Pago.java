/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author LOQ
 */
public class Pago {
    private String idPago;
    private String idReserva;
    private String metodoPago;
    private int monto;

    public Pago(String idPago, String idReserva, String metodoPago, int monto) {
        this.idPago = idPago;
        this.idReserva = idReserva;
        this.metodoPago = metodoPago;
        this.monto = monto;
    }

    public String getIdPago() {
        return idPago;
    }

    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }

    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Pago{" + "idPago=" + idPago + ", idReserva=" + idReserva + ", metodoPago=" + metodoPago + ", monto=" + monto + '}';
    }
    
    
}
