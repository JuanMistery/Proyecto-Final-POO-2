

package entidades;

public abstract class Empleado extends Persona {
    private int id;
    private String usuario;
    private String contrasenia;
    private String tipoEmpleado;

  
    public Empleado(int id, String usuario, String contrasenia, String tipoEmpleado,
                    String nombres, String apellidos, String telefono, String direccion) {
        super(nombres, apellidos, telefono, direccion);
        this.id = id;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.tipoEmpleado = tipoEmpleado;
    }

  
    public Empleado(int id, String usuario, String contrasenia, String tipoEmpleado) {
        this.id = id;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.tipoEmpleado = tipoEmpleado;
    }

    public Empleado(String usuario, String contrasenia, String tipoEmpleado) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.tipoEmpleado = tipoEmpleado;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    // Método que cada tipo de empleado implementará según su interfaz
    public abstract void abrirInterfaz();

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", contrasenia=" + contrasenia +
                ", tipoEmpleado=" + tipoEmpleado +
                '}';
    }
}
