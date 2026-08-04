package Streaming;

public class UsuarioEstandar extends CuentaUsuario
{
    public UsuarioEstandar(String mail, Integer mesesActivo)
    { super(mail, mesesActivo, 'E', new PlanEstandar()); }
}