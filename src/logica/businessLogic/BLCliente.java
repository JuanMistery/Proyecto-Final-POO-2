/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.businessLogic;

import datos.DALCliente;
import entidades.Cliente;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author JuanMistery
 */
public class BLCliente {
    
    // Códigos de error
    private static final int EXITO = 0;
    private static final int ERROR_DNI = 1;
    private static final int ERROR_NOMBRES = 2;
    private static final int ERROR_APELLIDOS = 3;
    private static final int ERROR_TELEFONO = 4;
    private static final int ERROR_DIRECCION = 5;
    private static final int ERROR_SPONSOR_NO_EXISTE = 6;
    private static final int ERROR_SPONSOR_OCUPADO = 7;
    private static final int ERROR_BASE_DATOS = 8;
    private static final int ERROR_CLIENTE_NO_EXISTE = 9;
    private static final int ERROR_SPONSOR_A_SI_MISMO = 10;
    private static final int ERROR_CLIENTE_ES_SPONSOR = 11;

    public static int insertarCliente(String dni, int sponsorID, String nombres, 
                                    String apellidos, String telefono, String direccion) {
        // Validar DNI
        if (!validarDNI(dni)) {
            mostrarError("DNI no válido. Debe tener 8 caracteres numéricos.");
            return ERROR_DNI;
        }

        // Validar nombres
        if (!validarNombres(nombres)) {
            mostrarError("Nombres no válidos. Debe tener entre 1 y 100 caracteres.");
            return ERROR_NOMBRES;
        }

        // Validar apellidos
        if (!validarApellidos(apellidos)) {
            mostrarError("Apellidos no válidos. Debe tener entre 1 y 100 caracteres.");
            return ERROR_APELLIDOS;
        }

        // Validar teléfono
        if (!validarTelefono(telefono)) {
            mostrarError("Teléfono no válido. Debe tener entre 1 y 15 caracteres.");
            return ERROR_TELEFONO;
        }

        // Validar dirección
        if (!validarDireccion(direccion)) {
            mostrarError("Dirección no válida. Debe tener entre 1 y 200 caracteres.");
            return ERROR_DIRECCION;
        }

        // Validar sponsor (si aplica)
        if (sponsorID != 0) {
            if (!validarSponsor(sponsorID)) {
                return ERROR_SPONSOR_NO_EXISTE;
            }
        }

        // Crear y guardar el cliente
        Cliente cliente = new Cliente(dni, sponsorID, nombres, apellidos, telefono, direccion);
        String resultado = DALCliente.insertarCliente(cliente);

        if (resultado == null) {
            mostrarExito("Cliente registrado exitosamente con ID: " + cliente.getClienteID());
            return EXITO;
        } else {
            mostrarError("Error al registrar: " + resultado);
            return ERROR_BASE_DATOS;
        }
    }

    public static int actualizarCliente(int clienteID, String dni, int sponsorID, 
                                      String nombres, String apellidos, 
                                      String telefono, String direccion) throws SQLException {
        // Validar que el cliente exista
        if (DALCliente.obtenerCliente(clienteID) == null) {
            mostrarError("Cliente no encontrado");
            return ERROR_CLIENTE_NO_EXISTE;
        }

        // Validar DNI
        if (!validarDNI(dni)) {
            mostrarError("DNI no válido. Debe tener 8 caracteres numéricos.");
            return ERROR_DNI;
        }

        // Validar nombres
        if (!validarNombres(nombres)) {
            mostrarError("Nombres no válidos. Debe tener entre 1 y 100 caracteres.");
            return ERROR_NOMBRES;
        }

        // Validar apellidos
        if (!validarApellidos(apellidos)) {
            mostrarError("Apellidos no válidos. Debe tener entre 1 y 100 caracteres.");
            return ERROR_APELLIDOS;
        }

        // Validar teléfono
        if (!validarTelefono(telefono)) {
            mostrarError("Teléfono no válido. Debe tener entre 1 y 15 caracteres.");
            return ERROR_TELEFONO;
        }

        // Validar dirección
        if (!validarDireccion(direccion)) {
            mostrarError("Dirección no válida. Debe tener entre 1 y 200 caracteres.");
            return ERROR_DIRECCION;
        }

        // Validar sponsor (si aplica)
        if (sponsorID != 0) {
            // No puede ser sponsor de sí mismo
            if (sponsorID == clienteID) {
                mostrarError("Un cliente no puede ser sponsor de sí mismo");
                return ERROR_SPONSOR_A_SI_MISMO;
            }

            if (!validarSponsor(sponsorID)) {
                return ERROR_SPONSOR_NO_EXISTE;
            }

            // Verificar si el sponsor ya está asignado a otro cliente
            if (DALCliente.verificarSponsorOcupado(sponsorID)) {
                Cliente actual = DALCliente.obtenerCliente(clienteID);
                if (actual.getSponsorID() != sponsorID) {
                    mostrarError("El cliente sponsor ya está patrocinando a otro cliente");
                    return ERROR_SPONSOR_OCUPADO;
                }
            }
        }

        // Actualizar el cliente
        Cliente clienteActualizado = new Cliente(clienteID, dni, sponsorID, nombres, apellidos, telefono, direccion);
        String resultado = DALCliente.actualizarCliente(clienteActualizado);

        if (resultado == null) {
            mostrarExito("Cliente actualizado exitosamente");
            return EXITO;
        } else {
            mostrarError("Error al actualizar: " + resultado);
            return ERROR_BASE_DATOS;
        }
    }

    public static int eliminarCliente(int clienteID) throws SQLException {
        // Verificar si el cliente existe
        Cliente clienteExistente = DALCliente.obtenerCliente(clienteID);
        if (clienteExistente == null) {
            mostrarError("Cliente no encontrado");
            return ERROR_CLIENTE_NO_EXISTE;
        }

        // Verificar si el cliente es sponsor de alguien
        if (DALCliente.verificarSponsorOcupado(clienteID)) {
            mostrarError("No se puede eliminar. Este cliente es sponsor de otro cliente.");
            return ERROR_CLIENTE_ES_SPONSOR;
        }

        String resultado = DALCliente.eliminarCliente(clienteID);

        if (resultado == null) {
            mostrarExito("Cliente eliminado exitosamente");
            return EXITO;
        } else {
            mostrarError("Error al eliminar: " + resultado);
            return ERROR_BASE_DATOS;
        }
    }

    // Métodos de validación privados
    private static boolean validarDNI(String dni) {
        return dni != null && dni.matches("\\d{8}");
    }

    private static boolean validarNombres(String nombres) {
        return nombres != null && !nombres.trim().isEmpty() && nombres.trim().length() <= 100;
    }

    private static boolean validarApellidos(String apellidos) {
        return apellidos != null && !apellidos.trim().isEmpty() && apellidos.trim().length() <= 100;
    }

    private static boolean validarTelefono(String telefono) {
        return telefono != null && !telefono.trim().isEmpty() && telefono.trim().length() <= 15;
    }

    private static boolean validarDireccion(String direccion) {
        return direccion != null && !direccion.trim().isEmpty() && direccion.trim().length() <= 200;
    }

    private static boolean validarSponsor(int sponsorID) {
        if (DALCliente.obtenerCliente(sponsorID) == null) {
            mostrarError("El cliente sponsor no existe");
            return false;
        }
        return true;
    }

    // Métodos auxiliares para mostrar mensajes
    private static void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private static void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    // Métodos de consulta
    public static Cliente obtenerCliente(int clienteID) {
        return DALCliente.obtenerCliente(clienteID);
    }

    public static ArrayList<Cliente> listarClientes() {
        return DALCliente.listarClientes();
    }
}
