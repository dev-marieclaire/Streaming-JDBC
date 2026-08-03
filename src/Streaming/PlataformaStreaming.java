package Streaming;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import models.Connman;
import models.UI;

public class PlataformaStreaming {
    private static ArrayList<CuentaUsuario> listaUsuarios = new ArrayList<CuentaUsuario>();

    PlataformaStreaming(ArrayList<CuentaUsuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public static void registrarUsuario(CuentaUsuario usuario){
        listaUsuarios.add(usuario);
    }

    public static boolean exists_in_list(int id)
    { return listaUsuarios.stream().anyMatch(usr -> usr.getId() == id); }

    public static boolean email_exists_in_list(String mail)
    { return listaUsuarios.stream().anyMatch(u -> u.geteMail().equals(mail)); }

    public static void display_from_list()
    {
        if (listaUsuarios.size() > 0)
            for (CuentaUsuario usuario : listaUsuarios)
                usuario.display();
    }

    public static void refresh_list() throws Exception
    { listaUsuarios = get_data(); }

    public static void display() throws Exception
    {
        refresh_list();
        display_from_list();
    }

    public static boolean exists(int ID, boolean force_db) throws Exception
    {
        if (exists_in_list(ID) && !force_db)
            return true;

        try
        {
            refresh_list();
            return exists_in_list(ID);
        }
        catch (Exception e)
        { System.out.println("Erreur: " + e.getMessage()); }
        return false;
    }

    public static void add(CuentaUsuario usuario) throws Exception
    {
        if (!email_exists_in_list(usuario.geteMail()))
        {
            if (usuario.save() > 0)
            {
                registrarUsuario(usuario);
                System.out.println("## Mensaje ##\nEnhorabuena, el usuario ha sido creado.\n");
            }
            else System.out.println("!! Mensaje !!\nNo se agregó ningún objeto. En caso de ser un error, intente de nuevo.\n");
        }
        else System.out.println("El vehiculo ya existe.");
    }

    public static void bulk_add(ArrayList<CuentaUsuario> usuarios) throws Exception
    {
        if (usuarios.size() > 0)
            for (CuentaUsuario usuario : usuarios)
                add(usuario);
        else System.out.println("La lista está vacía.");
    }

    public static void create() throws Exception
    {
        CuentaUsuario usr = UI.promptUsuario();
        add(usr);
    }

    public static boolean edit_vehiculo(int ID) throws Exception
    {
        boolean status = false;

        if (exists(ID, false))
        {
            CuentaUsuario cambios = UI.promptUsuario();

            for (CuentaUsuario usuario : listaUsuarios)
            {
                int queryExecStatus = usuario.edit(
                    cambios.geteMail(),
                    cambios.getMesesActivo(),
                    cambios.getTipo()
                );

                if (queryExecStatus > 0)
                    status = true;
                break;
            }

            if (status == true)
            {
                System.out.println("## Mensaje ##\nEnhorabuena, el usuario ha sido editado.\n");
                return status;
            }

            System.out.println("!! Mensaje !!\nNo se pudo modificar al usuario. Intente de nuevo.\n");
            return status;
        }
        System.out.println("!! Mensaje !!\nEl usuario no existe.\n");
        return status;
    }

    public static boolean remove(int ID) throws Exception
    {
        boolean status = false;

        if (exists(ID, false))
        {
            for (CuentaUsuario usuario : listaUsuarios)
            {
                if (usuario.getId() == ID)
                {
                    if (usuario.delete() > 0)
                    {
                        listaUsuarios.remove(usuario);
                        status = true;
                    }
                }

                if (status) break;
            }
            
            if (status)
            {
                System.out.println("## Mensaje ##\nEl usuario se removió exitosamente.");
                return status;
            }

            System.out.println("!! Mensaje !!\nNo se pudo remover al usuario.");
            return status;
        }

        System.out.println("!! Mensaje !!\nEl usuario no existe.\n");
        return status;
    }

    public static ArrayList<CuentaUsuario> get_data() throws Exception
    {
        try (Connection con = Connman.getConnection())
        {
            PreparedStatement statement = con.prepareStatement("select * from Usuario");

            ArrayList<CuentaUsuario> usuarios = new ArrayList<>();

            try (ResultSet rs = statement.executeQuery())
            {
                while (rs.next())
                {
                    CuentaUsuario usr = null;

                    if (rs.getString("tipo").equals("B")) usr = new UsuarioBasico(rs.getString("mail"), rs.getInt("meses_activo"));
                    else if (rs.getString("tipo").equals("E")) usr = new UsuarioEstandar(rs.getString("mail"), rs.getInt("meses_activo"));
                    else usr = new UsuarioPremium(rs.getString("mail"), rs.getInt("meses_activo"));

                    usr.setId(rs.getInt("id"));

                    usuarios.add(usr);
                }

                rs.close();
            }
            catch (Exception e)
            { System.out.println("No se pudo seleccionar la información: " + e.toString()); }
            
            con.close();
            return usuarios;
        }
    }

    // public static ArrayList<CuentaUsuario> getListaUsuarios() throws Exception
    // { return listaUsuarios; }

    // public static void Reporte (){
    //     double dineroTotal = 0.0;

    //     if (listaUsuarios.size() > 0)
    //     {
    //         System.out.println("======= REPORTE DE CUENTAS DE USUARIO =======");
            
    //         display();

    //         System.out.println("===========================");
    //         System.out.println("Dinero total recaudado: "+dineroTotal);
    //     }
    //     else
    //     {
    //         System.out.println("No hay ningún registro.");
    //     }
    // }
}
