/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.businessLogic;

import datos.DALEmpleadoAgencia;
import entidades.EmpleadoAgencia;
import java.util.*;
import logica.Facade.AuthSystem;
import javax.swing.JOptionPane;

/**
 *
 * @author JuanMistery
 */
public class BLEmpleadoAgencia implements AuthSystem {
    private static final int LONGITUD_MINIMA_PASSWORD = 8;
    private static final String FORMATO_TELEFONO = "\\d{9,15}";
    
    private int empleadoId; // Cache para obtenerID()
    private String usuarioActual;

    // Implementación de AuthSystem
    @Override
    public boolean ValidarCredenciales(String usuario, String contrasenia) {
        try {
            if (!validarFormatoCredenciales(usuario, contrasenia)) {
                return false;
            }

            EmpleadoAgencia empleado = DALEmpleadoAgencia.validarCredenciales(usuario, contrasenia);
            if (empleado != null) {
                this.empleadoId = empleado.getIDEmpleado();
                this.usuarioActual = usuario;
                return true;
            }
            return false;
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, 
                "Error al validar credenciales: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public int obtenerID() {
        return this.empleadoId;
    }

    // Métodos específicos de empleado
    public static String registrarEmpleado(int agenciaId, String nombres, String apellidos, 
                                         String telefono, String direccion, String usuario, 
                                         String contrasenia) {
        try {
            // Validaciones
            String error = validarDatos(nombres, apellidos, telefono, direccion, usuario, contrasenia);
            if (error != null) return error;

            if (DALEmpleadoAgencia.existeUsuario(usuario)) {
                return "El usuario ya existe";
            }

            EmpleadoAgencia empleado = new EmpleadoAgencia(0, agenciaId, nombres, apellidos, 
                                                          telefono, direccion, usuario, contrasenia);
            return DALEmpleadoAgencia.insertarEmpleado(empleado);

        } catch (Exception ex) {
            return "Error inesperado: " + ex.getMessage();
        }
    }

    public static EmpleadoAgencia obtenerPorId(int id) {
        try {
            return DALEmpleadoAgencia.obtenerPorId(id);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, 
                "Error al obtener empleado: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Validaciones privadas
    private static String validarDatos(String nombres, String apellidos, String telefono,
                                     String direccion, String usuario, String contrasenia) {
        if (nombres == null || nombres.trim().isEmpty() || nombres.length() > 50) {
            return "Nombres no válidos (máx 50 caracteres)";
        }
        
        if (apellidos == null || apellidos.trim().isEmpty() || apellidos.length() > 50) {
            return "Apellidos no válidos (máx 50 caracteres)";
        }
        
        if (telefono == null || !telefono.matches(FORMATO_TELEFONO)) {
            return "Teléfono debe tener 9-15 dígitos";
        }
        
        if (direccion == null || direccion.trim().isEmpty() || direccion.length() > 100) {
            return "Dirección no válida (máx 100 caracteres)";
        }
        
        if (usuario == null || usuario.trim().isEmpty() || usuario.length() > 20) {
            return "Usuario no válido (máx 20 caracteres)";
        }
        
        if (contrasenia == null || contrasenia.length() < LONGITUD_MINIMA_PASSWORD) {
            return "Contraseña debe tener al menos " + LONGITUD_MINIMA_PASSWORD + " caracteres";
        }
        
        return null;
    }

    private boolean validarFormatoCredenciales(String usuario, String contrasenia) {
        if (usuario == null || usuario.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese un usuario válido", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (contrasenia == null || contrasenia.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese una contraseña válida", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }

    // Método adicional para listar empleados por agencia
    public static ArrayList<EmpleadoAgencia> listarPorAgencia(int agenciaId) {
        try {
            return DALEmpleadoAgencia.listarPorAgencia(agenciaId);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                "Error al listar empleados: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }
}