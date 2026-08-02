package Streaming;

public class UsuarioEstandar extends CuentaUsuario
{
    UsuarioEstandar(String mail, Integer mesesActivo)
    { super(mail, mesesActivo, new PlanEstandar()); }
}