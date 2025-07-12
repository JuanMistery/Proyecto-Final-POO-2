/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author JuanMistery
 */
public class Cliente extends Persona{
    private int clienteID;
    private String DNI;
    private int sponsorID;

    public Cliente(int clienteID, String DNI, int sponsorID, String nombres, String apellidos, String telefono, String direccion) {
        super(nombres, apellidos, telefono, direccion);
        this.clienteID = clienteID;
        this.DNI = DNI;
        this.sponsorID = sponsorID;
    }

    public int getClienteID() {
        return clienteID;
    }

    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public int getSponsorID() {
        return sponsorID;
    }

    public void setSponsorID(int sponsorID) {
        this.sponsorID = sponsorID;
    }

    @Override
    public String toString() {
        return "Cliente{" + "clienteID=" + clienteID + ", DNI=" + DNI + ", sponsorID=" + sponsorID + '}';
    }
}
