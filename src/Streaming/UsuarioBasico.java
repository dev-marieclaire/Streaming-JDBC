package Streaming;
public class UsuarioBasico extends CuentaUsuario
{
    public UsuarioBasico(String mail, Integer mesesActivo)
    { super(mail, mesesActivo, 'B', new PlanBasico()); }
}