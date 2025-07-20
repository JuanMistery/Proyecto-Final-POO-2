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

    public void autenticar(String usuario, String contrasenia, JFrame parentFrame) {
        if (adminSubsystem.ValidarCredenciales(usuario, contrasenia)) {
            new FrmPrincipalAdministrador(adminSubsystem.obtenerID()).setVisible(true);
            parentFrame.dispose();
        } 
        else if (empleadoSubsystem.ValidarCredenciales(usuario, contrasenia)) {
            new FrmPrincipalEmpleado(empleadoSubsystem.obtenerID()).setVisible(true);
            parentFrame.dispose();
        } 
        else {
            JOptionPane.showMessageDialog(parentFrame, "Credenciales inválidas");
        }
    }
}
