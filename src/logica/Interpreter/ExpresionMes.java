/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.Interpreter;

/**
 *
 * @author JuanMistery
 */
public class ExpresionMes implements ExpresionFecha{
    @Override
    public boolean interpret(ContextoFecha contexto){
        try{
            int mes=Integer.parseInt(contexto.getMes());
            if(mes<1||mes>12)
            {
                contexto.setValidez(false);
                contexto.setMensajeError("Mes invalido. debe estar entre 1 y 31");
                return false;
            }
            return true;
        } catch (NumberFormatException e){
            contexto.setValidez(false);
            contexto.setMensajeError("Mes debe ser un numero entero.");
            return false;
        }
    }
}
