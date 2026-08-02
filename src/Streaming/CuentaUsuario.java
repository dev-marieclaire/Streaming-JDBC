package Streaming;

public abstract class CuentaUsuario {
    private Integer id;
    private String eMail;
    private Integer mesesActivo;
    private Character tipo;

    // Añadiendo Plan dentro de la clase abstracta.
    private iPlanSuscripcion planSuscripcion;

    public CuentaUsuario(String eMail, Integer mesesActivo, iPlanSuscripcion planSuscripcion)
    {
        this.eMail = eMail;
        this.mesesActivo = mesesActivo;
        this.planSuscripcion = planSuscripcion;
    }

    public String toString()
    {
        return (id != null) ? String.format(
            "ID: %d\n"
            + "e-mail: %s\n"
            + "meses activo: %d\n"
            + "tipo: %s\n",
            id, eMail, mesesActivo,tipo
        ) : String.format(
            "e-mail: %s\n"
            + "meses activo: %d\n"
            + "tipo: %s\n",
            eMail, mesesActivo,tipo
        );
    }

    public void display()
    { System.out.println(toString()); }

    // Con este metodo luego le agregamos para que llame al ooooootro metodo
    // Luego no, ahora
    public Double ObtenerTotalPagar()
    { return (Double) planSuscripcion.calcularCosto(this.mesesActivo); }

    public String geteMail() {
        return eMail;
    }

    //oye daniel y los cambios we

    public Integer getMesesActivo() {
        return mesesActivo;
    }
}
