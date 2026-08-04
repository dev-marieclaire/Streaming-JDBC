package Streaming;

public class UsuarioPremium extends CuentaUsuario
{
    public UsuarioPremium(String mail, Integer mesesActivo)
    { super(mail, mesesActivo, 'P', new PlanPremium()); }
}