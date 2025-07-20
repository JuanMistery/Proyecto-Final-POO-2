/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.businessLogic;

import logica.Facade.AuthSystem;
import datos.DALAdministrador;
import datos.DALEmpleadoAgencia;
import entidades.Administrador;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JuanMistery
 */
public class BLAdministrador implements AuthSystem {
    private static final int LONGITUD_MINIMA_PASSWORD = 5;
    private static final String FORMATO_TELEFONO = "\\d{9,15}"; // Entre 9 y 15 dígitos
    
    private int adminId; // Para implementar obtenerID()
    private String usuarioActual; // Cache para validaciones

    @Override
    public boolean ValidarCredenciales(String usuario, String contrasenia) {
        try {
            if (!validarFormatoCredenciales(usuario, contrasenia)) {
                return false;
            }

            Administrador admin = DALAdministrador.validarCredenciales(usuario, contrasenia);
            if (admin != null) {
                this.adminId = admin.getIDEmpleado();
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
    
    public static ArrayList<Administrador> listarAdministradores() throws ClassNotFoundException{
        return DALAdministrador.listarAdministradores();
    }

    @Override
    public int obtenerID() {
        return this.adminId;
    }

    // Métodos adicionales de negocio
    public static String registrarAdministrador(String nombres, String apellidos, String telefono,String direccion, String usuario, String contrasenia) {
        try {
            // Validaciones de negocio
            String error = validarDatos(nombres, apellidos, telefono, direccion, usuario, contrasenia);
            if (error != null) return error;

            // Verificar usuario único
            if (DALAdministrador.existeUsuario(usuario) && DALEmpleadoAgencia.existeUsuario(usuario)) {
                return "El usuario ya existe";
            }

            // Crear y guardar
            Administrador admin = new Administrador(0, usuario, contrasenia, nombres, apellidos, telefono, direccion);
            return DALAdministrador.insertarAdministrador(admin);

        } catch (Exception ex) {
            return "Error inesperado: " + ex.getMessage();
        }
    }

    public static Administrador obtenerAdministrador(int id) {
        try {
            return DALAdministrador.obtenerPorId(id);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, 
                "Error al obtener administrador: " + ex.getMessage(), 
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
    
    public static void cargarAdministradores(JTable table) throws ClassNotFoundException {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpiar tabla

        ArrayList<Administrador> empleados = listarAdministradores();
        
        for (Administrador emp : empleados) {
            String nombreCompleto = emp.getNombres() + " " + emp.getApellidos();
            
            model.addRow(new Object[]{
                emp.getIDEmpleado(),
                nombreCompleto,
                emp.getTelefono(),
                emp.getDireccion()
            });
        }
    }
}
