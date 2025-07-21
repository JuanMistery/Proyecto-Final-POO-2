/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logica.Facade;

/**
 *
 * @author JuanMistery
 */
public interface AuthSystem {
    boolean ValidarCredenciales(String usuario, String contrasenia);
    int obtenerIdPorUsuario(String usuario);
}
