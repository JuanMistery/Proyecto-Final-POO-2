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
    private String IDAgengia;
    private String nombreAgencia;
    private String direccion;

    public Agencia(String IDAgengia, String nombreAgencia, String direccion) {
        this.IDAgengia = IDAgengia;
        this.nombreAgencia = nombreAgencia;
        this.direccion = direccion;
    }

    public String getIDAgengia() {
        return IDAgengia;
    }

    public void setIDAgengia(String IDAgengia) {
        this.IDAgengia = IDAgengia;
    }

    public String getNombreAgencia() {
        return nombreAgencia;
    }

    public void setNombreAgencia(String nombreAgencia) {
        this.nombreAgencia = nombreAgencia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Agencia{" + "IDAgengia=" + IDAgengia + ", nombreAgencia=" + nombreAgencia + ", direccion=" + direccion + '}';
    }
    
    
}
