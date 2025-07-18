/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.businessLogic;

import entidades.Agencia;
import datos.DALAgencia;
import java.util.ArrayList;
import static javax.swing.JOptionPane.showMessageDialog;
/**
 *
 * @author JuanMistery
 */
public class BLAgencia {
    
    public static int insertarAgencia(String nombre, String direccion) {
        // Validaciones de negocio
        if (nombre == null || nombre.trim().isEmpty() || nombre.trim().length() > 100) {
            showMessageDialog(null, "Nombre no válido (debe tener entre 1 y 100 caracteres)", "Error", 0);
            return 1;
        }
        
        if (direccion == null || direccion.trim().isEmpty() || direccion.trim().length() > 200) {
            showMessageDialog(null, "Dirección no válida (debe tener entre 1 y 200 caracteres)", "Error", 0);
            return 2;
        }
        
        // Crear y guardar la agencia
        Agencia agencia = new Agencia(nombre, direccion);
        
        String resultado = DALAgencia.insertarAgencia(agencia);
        
        if (resultado == null) {
            showMessageDialog(null, "Agencia registrada exitosamente con ID: " + agencia.getIDAgencia(), "Éxito", 1);
            return 0;
        } else {
            showMessageDialog(null, "Error al registrar: " + resultado, "Error", 0);
            return 3;
        }
    }
    
    public static Agencia obtenerAgencia(int agenciaId) {
        return DALAgencia.obtenerAgencia(agenciaId);
    }
    
    public static ArrayList<Agencia> listarAgencias() {
        return DALAgencia.listarAgencias();
    }
    
    public static int actualizarAgencia(int agenciaId, String nombre, String direccion) {
        // Validaciones
        if (nombre == null || nombre.trim().isEmpty() || nombre.trim().length() > 100) {
            showMessageDialog(null, "Nombre no válido", "Error", 0);
            return 1;
        }
        
        if (direccion == null || direccion.trim().isEmpty() || direccion.trim().length() > 200) {
            showMessageDialog(null, "Dirección no válida", "Error", 0);
            return 2;
        }
        
        // Verificar si la agencia existe
        Agencia agenciaExistente = DALAgencia.obtenerAgencia(agenciaId);
        if (agenciaExistente == null) {
            showMessageDialog(null, "Agencia no encontrada", "Error", 0);
            return 3;
        }
        
        // Actualizar la agencia
        Agencia agenciaActualizada = new Agencia(agenciaId, nombre, direccion);
        String resultado = DALAgencia.actualizarAgencia(agenciaActualizada);
        
        if (resultado == null) {
            showMessageDialog(null, "Agencia actualizada exitosamente", "Éxito", 1);
            return 0;
        } else {
            showMessageDialog(null, "Error al actualizar: " + resultado, "Error", 0);
            return 4;
        }
    }
    
    public static int eliminarAgencia(int IDAgencia) {
        // Verificar si la agencia existe
        Agencia agenciaExistente = DALAgencia.obtenerAgencia(IDAgencia);
        if (agenciaExistente == null) {
            showMessageDialog(null, "Agencia no encontrada", "Error", 0);
            return 1;
        }
        
        String resultado = DALAgencia.eliminarAgencia(IDAgencia);
        
        if (resultado == null) {
            showMessageDialog(null, "Agencia eliminada exitosamente", "Éxito", 1);
            return 0;
        } else {
            showMessageDialog(null, "Error al eliminar: " + resultado, "Error", 0);
            return 2;
        }
    }
}
