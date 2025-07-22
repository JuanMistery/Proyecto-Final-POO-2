/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.Interpreter;

import java.time.LocalDate;
import java.util.regex.*;

/**
 *
 * @author JuanMistery
 */
public class HyphenDateExpresion implements DateExpresion {
    private static final Pattern PATTERN = 
        Pattern.compile("^(\\d{2})/(\\d{2})/(\\d{4})$");

    @Override
    public LocalDate interpret(DateContexto context) {
        Matcher matcher = PATTERN.matcher(context.getInput());
        if (matcher.matches()) {
            int day = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(2));
            int year = Integer.parseInt(matcher.group(3));
            return validateAndCreate(day, month, year);
        }
        throw new IllegalArgumentException("Formato no coincide con dd/mm/aaaa");
    }

    private LocalDate validateAndCreate(int day, int month, int year) {
        if (day < 1 || day > 31 || month < 1 || month > 12) {
            throw new IllegalArgumentException("Día o mes inválido");
        }
        return LocalDate.of(year, month, day);
    }
}
