package Streaming;
public class UsuarioBasico extends CuentaUsuario
{
    UsuarioBasico(String mail, Integer mesesActivo)
    { super(mail, mesesActivo, new PlanBasico()); }
}