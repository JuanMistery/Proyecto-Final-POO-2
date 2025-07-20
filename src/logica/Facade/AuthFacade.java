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
            new FrmPrincipalAdministrador(BLAdministrador.obtenerIdPorUsuario(usuario)).setVisible(true);
            BLRegistro.registrarActividad(BLAdministrador.obtenerIdPorUsuario(usuario),"ADMINISTRADOR", "LOGIN" , "AUTH", "Inicio Exitoso:" + BLAdministrador.obtenerAdministrador(adminSubsystem.obtenerID()).getUsuario());
            parentFrame.dispose();
        } 
        else if (empleadoSubsystem.ValidarCredenciales(usuario, contrasenia)) {
            new FrmPrincipalEmpleado(empleadoSubsystem.obtenerID()).setVisible(true);
            BLRegistro.registrarActividad(adminSubsystem.obtenerID(),"EMPLEADO", "LOGIN" , "AUTH", "Inicio Exitoso:" + BLEmpleadoAgencia.obtenerPorId(adminSubsystem.obtenerID()).getUsuario());
            parentFrame.dispose();
        } 
        else {
            BLRegistro.registrarActividad(0 ,"ANONIMO", "LOGIN_FALLIDO" , "AUTH", "Intento Fallido por Usuario Anonimo");
            JOptionPane.showMessageDialog(parentFrame, "Credenciales inválidas");
        }
    }
}
