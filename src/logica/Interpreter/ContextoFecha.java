/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.Interpreter;

/**
 *
 * @author JuanMistery
 */
public class ContextoFecha {
    private String dia;
    private String mes;
    private String anio;
    private boolean validez;
    private String mensajeError;
    
    public ContextoFecha(String fecha){
        String[] partes = fecha.split("/");
        if(partes.length!=3){
            this.validez=false;
            this.mensajeError="FormatoInvalido.Debe ser dd/mm/aaaa";
        } else {
            this.dia=partes[0];
            this.mes=partes[1];
            this.anio=partes[2];
            this.validez=true;
            this.mensajeError=null;
        }
    }

    public String getDia() {
        return dia;
    }

    public String getMes() {
        return mes;
    }

    public String getAnio() {
        return anio;
    }

    public boolean isValidez() {
        return validez;
    }

    public void setValidez(boolean validez) {
        this.validez = validez;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }
}
