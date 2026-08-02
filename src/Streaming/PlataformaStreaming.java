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
