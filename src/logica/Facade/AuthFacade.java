/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.Facade;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import logica.businessLogic.*;
import presentacion.*;
/**
 *
 * @author JuanMistery
 */
public class AuthFacade {
    private final BLAdministrador adminSubsystem = new BLAdministrador();
    private final BLEmpleadoAgencia empleadoSubsystem = new BLEmpleadoAgencia();

    public void autenticar(String usuario, String contrasenia, JFrame parentFrame) throws ClassNotFoundException {
        if (adminSubsystem.ValidarCredenciales(usuario, contrasenia)) {
            new FrmPrincipalAdministrador(adminSubsystem.obtenerIdPorUsuario(usuario)).setVisible(true);
            BLRegistro.registrarActividad(adminSubsystem.obtenerIdPorUsuario(usuario),"ADMINISTRADOR", "LOGIN" , "AUTH", "Inicio Exitoso:" + BLAdministrador.obtenerAdministrador(adminSubsystem.obtenerIdPorUsuario(usuario)).getUsuario());
            parentFrame.dispose();
        } 
        else if (empleadoSubsystem.ValidarCredenciales(usuario, contrasenia)) {
            new FrmPrincipalEmpleado(empleadoSubsystem.obtenerIdPorUsuario(usuario)).setVisible(true);
            BLRegistro.registrarActividad(empleadoSubsystem.obtenerIdPorUsuario(usuario),"EMPLEADO", "LOGIN" , "AUTH", "Inicio Exitoso:" + BLEmpleadoAgencia.obtenerPorId(empleadoSubsystem.obtenerIdPorUsuario(usuario)).getUsuario());
            parentFrame.dispose();
        } 
        else {
            BLRegistro.registrarActividad(0 ,"ANONIMO", "LOGIN_FALLIDO" , "AUTH", "Intento Fallido por Usuario Anonimo");
            JOptionPane.showMessageDialog(parentFrame, "Credenciales inválidas");
        }
    }
}
