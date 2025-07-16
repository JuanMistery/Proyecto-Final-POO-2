/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.Interpreter;

/**
 *
 * @author JuanMistery
 */
public class ExpresionDia implements ExpresionFecha{
    @Override
    public boolean interpret(ContextoFecha contexto){
        try{
            int dia=Integer.parseInt(contexto.getDia());
            if(dia<1||dia>31)
            {
                contexto.setValidez(false);
                contexto.setMensajeError("Dia invalido. debe estar entre 1 y 31");
                return false;
            }
            return true;
        } catch (NumberFormatException e){
            contexto.setValidez(false);
            contexto.setMensajeError("Dia debe ser un numero entero.");
            return false;
        }
    }
}
