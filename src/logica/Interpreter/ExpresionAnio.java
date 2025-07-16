/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.Interpreter;

/**
 *
 * @author JuanMistery
 */
public class ExpresionAnio implements ExpresionFecha{
    @Override
    public boolean interpret(ContextoFecha contexto){
        try{
            int anio=Integer.parseInt(contexto.getAnio());
            if(anio<1||anio>12)
            {
                contexto.setValidez(false);
                contexto.setMensajeError("Año invalido. debe estar entre 1 y 31");
                return false;
            }
            return true;
        } catch (NumberFormatException e){
            contexto.setValidez(false);
            contexto.setMensajeError("Año debe ser un numero entero.");
            return false;
        }
    }
}
