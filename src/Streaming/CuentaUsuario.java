package Streaming;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.Connman;

public abstract class CuentaUsuario {
    private Integer id;
    private String eMail;
    private Integer mesesActivo;
    private Character tipo;
    private Double deuda;

    // Añadiendo Plan dentro de la clase abstracta.
    private iPlanSuscripcion planSuscripcion;

    public CuentaUsuario(String eMail, Integer mesesActivo, Character tipo, iPlanSuscripcion planSuscripcion)
    {
        this.eMail = eMail;
        this.mesesActivo = mesesActivo;
        this.tipo = tipo;
        this.planSuscripcion = planSuscripcion;
        deuda = ObtenerTotalPagar();
    }

    public String toString()
    {
        return (id != null) ? String.format(
            "ID: %d\n"
            + "e-mail: %s\n"
            + "meses activo: %d\n"
            + "tipo: %s\n"
            + "deuda: %f\n",
            id, eMail, mesesActivo, tipo, deuda
        ) : String.format(
            "e-mail: %s\n"
            + "meses activo: %d\n"
            + "tipo: %s\n"
            + "deuda: %f\n",
            eMail, mesesActivo, tipo, deuda
        );
    }

    public void display()
    { System.out.println(toString()); }

    public int save() throws Exception
    {
        try (Connection con = Connman.getConnection())
        {
            PreparedStatement statement = con.prepareStatement(
                "insert into Usuario(mail, meses_activo, tipo, deuda) "
                + "values (?, ?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, this.eMail);
            statement.setInt(2, this.mesesActivo);
            statement.setString(3, this.tipo.toString());
            statement.setDouble(4, this.deuda);

            int queryExecStatus = statement.executeUpdate();

            if (queryExecStatus > 0)
            {
                ResultSet rs = statement.getGeneratedKeys();

                if (rs.next()) this.id = rs.getInt(1);

                return queryExecStatus;
            }

            return queryExecStatus;
        }
    }

    public int edit(String mail, Integer meses, Character tipo) throws Exception
    {
        try (Connection con = Connman.getConnection())
        {
            PreparedStatement statement = con.prepareStatement(
                "update Usuario set "
                + "mail = ?, "
                + "meses_activo = ?, "
                + "tipo = ?, "
                + "deuda = ? "
                + "where mail = ?"
            );

            if (mail != null) statement.setString(1, mail);
            else statement.setString(1, this.eMail);

            if (meses != null)
            {
                statement.setInt(2, meses);
                statement.setDouble(4, ObtenerTotalPagar());
            }
            else
            {
                statement.setInt(2, this.mesesActivo);
                statement.setDouble(4, this.deuda);
            }

            if (tipo != null) statement.setString(3, tipo.toString());
            else statement.setString(3, this.tipo.toString());

            statement.setString(5, this.eMail);

            int queryExecStatus = statement.executeUpdate();

            if (queryExecStatus > 0)
            {
                if (mail != null) this.eMail = mail;
                if (meses != null)
                {
                    this.mesesActivo = meses;
                    this.deuda = ObtenerTotalPagar();
                }
                if (tipo != null) this.tipo = tipo;

                return queryExecStatus;
            }

            return queryExecStatus;
        }
    }

    public int delete() throws Exception
    {
        try (Connection con = Connman.getConnection())
        {
            PreparedStatement statement = con.prepareStatement("delete from Usuario where id = ?");
            statement.setInt(1, this.id);
            return statement.executeUpdate();
        }
    }

    // Con este metodo luego le agregamos para que llame al ooooootro metodo
    // Luego no, ahora
    public Double ObtenerTotalPagar()
    { return (Double) planSuscripcion.calcularCosto(this.mesesActivo); }

    public String geteMail() {
        return eMail;
    }

    public int getId()
    { return id; }

    public Character getTipo()
    { return tipo; }

    //oye daniel y los cambios we

    public Integer getMesesActivo() {
        return mesesActivo;
    }

    public void setId(int id)
    { this.id = id; }
}
