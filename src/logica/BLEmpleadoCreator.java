package logica;

import entidades.Empleado;

public abstract class BLEmpleadoCreator {
    protected int id;
    protected String nombre;
    protected String tipo;
    protected String usuario;
    protected String contrasena;

    public BLEmpleadoCreator(int id, String nombre, String tipo, String usuario, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public abstract Empleado crearUsuario();
}

