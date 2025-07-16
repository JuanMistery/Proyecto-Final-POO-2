/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.Interpreter;

/**
 *
 * @author JuanMistery
 */
public class EsprexionFechaCompleta implements ExpresionFecha {
    private ExpresionDia dia = new ExpresionDia();
    private ExpresionMes mes = new ExpresionMes();
    private ExpresionAnio anio = new ExpresionAnio();
    
    @Override
    public boolean interpret(ContextoFecha contexto){
        if(!contexto.isValidez()){
            return false;
        }
        if(!dia.interpret(contexto)||!mes.interpret(contexto)||!anio.interpret(contexto)){
            return false;
        }
        int dia = Integer.parseInt(contexto.getDia());
        int mes = Integer.parseInt(contexto.getMes());
        int anio = Integer.parseInt(contexto.getAnio());
        
        if (mes == 2) {
            int maxDias = esBisiesto(anio) ? 29 : 28;
            if (dia > maxDias) {
                contexto.setValidez(false);
                contexto.setMensajeError(String.format("Febrero de %d solo tiene %d días.", anio, maxDias));
                return false;
            }
        } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            if (dia > 30) {
                contexto.setValidez(false);
                contexto.setMensajeError(String.format("El mes %d solo tiene 30 días.", mes));
                return false;
            }
        } else {
            if (dia > 31) {
                contexto.setValidez(false);
                contexto.setMensajeError(String.format("El mes %d solo tiene 31 días.", mes));
                return false;
            }
        }
        return true;
    }
    
    private boolean esBisiesto(int anio) {
        return (anio % 400 == 0) || (anio % 100 != 0 && anio % 4 == 0);
    }
}
