package Streaming;

import java.util.ArrayList;

public class PlataformaStreaming {
    private static ArrayList<CuentaUsuario> listaUsuarios = new ArrayList<CuentaUsuario>();

    PlataformaStreaming(ArrayList<CuentaUsuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public static void registrarUsuario(CuentaUsuario usuario){
        listaUsuarios.add(usuario);
    }

    public static boolean email_exists_in_list(String mail)
    { return listaUsuarios.stream().anyMatch(u -> u.geteMail().equals(mail)); }

    public static void display_from_list()
    {
        if (listaUsuarios.size() > 0)
            for (CuentaUsuario usuario : listaUsuarios)
                usuario.display();
    }

    public static void add(CuentaUsuario usuario)
    {
        if (!email_exists_in_list(usuario.geteMail()))
        {
            registrarUsuario(usuario);
        }
    }

    public static void bulk_add(ArrayList<CuentaUsuario> usuarios)
    {
        if (usuarios.size() > 0)
            for (CuentaUsuario usuario : usuarios)
                add(usuario);
        else System.out.println("La lista está vacía.");
    }

    public static ArrayList<CuentaUsuario> getListaUsuarios()
    { return listaUsuarios; }

    public static void Reporte (){
        double dineroTotal = 0.0;

        if (listaUsuarios.size() > 0)
        {
            System.out.println("======= REPORTE DE CUENTAS DE USUARIO =======");
            for (CuentaUsuario u : listaUsuarios){
                double totalCuenta = u.ObtenerTotalPagar();
                dineroTotal += totalCuenta;

                System.out.println("Email: " + u.geteMail());
                System.out.println("Meses Activo: " + u.getMesesActivo());
                System.out.println("Total a Pagar " + totalCuenta);
            }

            System.out.println("===========================");
            System.out.println("Dinero total recaudado: "+dineroTotal);
        }
        else
        {
            System.out.println("No hay ningún registro.");
        }
    }
}
