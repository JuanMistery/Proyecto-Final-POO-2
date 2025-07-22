/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.Interpreter;

import java.util.List;
import java.time.LocalDate;
import java.util.Arrays;

/**
 *
 * @author JuanMistery
 */
public class DateInterpreterCliente {
    private final List<DateExpresion> expresiones;

    public DateInterpreterCliente() {
        this.expresiones = Arrays.asList(
            new SlashDateExpresion(), // dd/mm/aaaa
            new HyphenDateExpresion() // dd-mm-aaaa
        );
    }

    public LocalDate interpret(String input) {
        DateContexto context = new DateContexto(input);
        
        for (DateExpresion expr : expresiones) {
            try {
                return expr.interpret(context);
            } catch (IllegalArgumentException e) {
                // Intenta con la siguiente expresión
            }
        }
        throw new IllegalArgumentException("Formato no soportado. Use dd/mm/aaaa o dd-mm-aaaa");
    }
}
