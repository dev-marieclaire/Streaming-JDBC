package Streaming;

public class UsuarioPremium extends CuentaUsuario
{
    UsuarioPremium(String mail, Integer mesesActivo)
    { super(mail, mesesActivo, new PlanPremium()); }
}