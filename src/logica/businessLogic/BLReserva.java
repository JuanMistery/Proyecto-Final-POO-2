/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.businessLogic;

import datos.DALReserva;
import entidades.Reserva;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author JuanMistery
 */

public class BLReserva {

    public static int crearReserva(int clienteId, int agenciaId, LocalDate fechaInicio, 
                              LocalDate fechaFin, float precioTotal) {
        try {
            Reserva reserva = new Reserva();
            reserva.setClienteId(clienteId);
            reserva.setAgenciaId(agenciaId);
            reserva.setFechaInicio(fechaInicio);
            reserva.setFechaFin(fechaFin);
            reserva.setPrecioTotal(precioTotal);
            reserva.setEntregado(false);
            return DALReserva.insertarReserva(reserva);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Reserva obtenerReserva(int reservaId) {
        try {
            return DALReserva.obtenerReserva(reservaId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Reserva> listarReservas() {
        try {
            return DALReserva.listarReservas();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean actualizarReserva(Reserva reserva) {
        try {
            return DALReserva.actualizarReserva(reserva) != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarReserva(int reservaId) {
        try {
            return DALReserva.eliminarReserva(reservaId) != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean marcarComoEntregada(int reservaId) {
        try {
            return DALReserva.marcarComoEntregada(reservaId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int obtenerReservaPorCliente(int clienteId) {
        try {
            return DALReserva.obtenerIDPorCliente(clienteId);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public float calcularPrecioTotal(ArrayList<Float> precios) {
        float total = 0f;
        for (Float precio : precios) {
            if (precio != null) {
                total += precio;
            }
        }
        return total;
    }
}