/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logica.Interpreter;

import java.time.LocalDate;

/**
 *
 * @author JuanMistery
 */
public interface DateExpresion {
    LocalDate interpret(DateContexto contexto) throws IllegalArgumentException;
}
