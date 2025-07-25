/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.Date;

/**
 *
 * @author JuanMistery
 */
public class Registro {
    private int registroID;
    private int empleadoID;
    private Date fecha;
    private String tipoEvento;
    private String modulo;
    private String descripcion;

    public Registro(int registroID, int empleadoID, Date fecha, String tipoEvento, String modulo, String descripcion) {
        this.registroID = registroID;
        this.empleadoID = empleadoID;
        this.fecha = fecha;
        this.tipoEvento = tipoEvento;
        this.modulo = modulo;
        this.descripcion = descripcion;
    }

    public Registro() {
    }

    public int getRegistroID() {
        return registroID;
    }

    public void setRegistroID(int registroID) {
        this.registroID = registroID;
    }

    public int getEmpleadoID() {
        return empleadoID;
    }

    public void setEmpleadoID(int empleadoID) {
        this.empleadoID = empleadoID;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Registro{" + "registroID=" + registroID + ", empleadoID=" + empleadoID + ", fecha=" + fecha + ", tipoEvento=" + tipoEvento + ", modulo=" + modulo + ", descripcion=" + descripcion + '}';
    }
}
