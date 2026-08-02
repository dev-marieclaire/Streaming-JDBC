package models;

import java.util.Scanner;

import Streaming.CuentaUsuario;
import Streaming.UsuarioBasico;
import Streaming.UsuarioEstandar;
import Streaming.UsuarioPremium;
import Streaming.PlataformaStreaming;

public class UI
{
    private static String option = new String();
    private static String prompt = ">> ";
    private static Scanner clavier = new Scanner(System.in);

    public static String promptString(String text)
    {
        System.out.print(text + "\n" + prompt);
        return clavier.next();
    }

    public static String promptStringLine(String text)
    {
        System.out.print(text + "\n" + prompt);
        return clavier.nextLine();
    }

    public static Character promptChar(String text)
    {
        System.out.print(text + "\n" + prompt);
        return clavier.next().charAt(0);
    }

    public static Integer promptInteger(String text)
    {
        System.out.print(text + "\n" + prompt);
        return clavier.nextInt();
    }

    public static Double promptDouble(String text)
    {
        System.out.print(text + "\n" + prompt);
        return clavier.nextDouble();
    }

    public static void drawLine(int length, char character)
    {
        for (int i = 0; i < length; i++)
            System.out.print(character);
    }

    public static CuentaUsuario promptUsuario()
    {
        Character confirmation;

        CuentaUsuario usuario = null;

        do {
            String mail;
            do { mail = promptString("Introduce el correo electrónico del usuario.");
            } while (mail.length() <= 0);

            int mesesActivo;
            do { mesesActivo = promptInteger("Introduce la cantidad de meses de actividad del usuario.");
            } while (mesesActivo <= 0);

            Character tipo;
            do {
                tipo = promptChar("Introduce el tipo de cuenta, [B]ásica, [E]stándar, [P]remium");
                tipo = Character.toUpperCase(tipo);
            } while (!tipo.equals('B') && !tipo.equals('E') && !tipo.equals('P'));

            if (tipo.equals('B')) usuario = (CuentaUsuario) new UsuarioBasico(mail, mesesActivo);
            else if (tipo.equals('E')) usuario = (CuentaUsuario) new UsuarioEstandar(mail, mesesActivo);
            else usuario = (CuentaUsuario) new UsuarioPremium(mail, mesesActivo);

            confirmation = UI.promptChar("¿Desea efectuar los cambios? (S/N)");
        } while (Character.toUpperCase(confirmation) != 'S');

        return usuario;
    }

    public static void menu() {
        do 
        {
            try {
                option = promptString("Introduce una opción:\n0) Salir.\n1) Mostrar usuarios.\n2) Registrar un usuario.\n3) Editar un usuario.\n4) Remover un usuario.");
                
                switch (option)
                {
                    case "0":
                        System.out.println("¡Hasta luego!");
                        break;
                    
                    case "1":
                        PlataformaStreaming.display();
                        break;
                    
                    case "2":
                        PlataformaStreaming.create();
                        break;
                    
                    case "3":
                        PlataformaStreaming.display();
                        int id = promptInteger("Introduce el ID del usuario:");

                        PlataformaStreaming.edit_vehiculo(id);
                        break;
                    
                    case "4":
                        PlataformaStreaming.display();
                        id = promptInteger("Introduce el ID del usuario:");

                        PlataformaStreaming.remove(id);
                        break;
                    
                    default:
                        System.out.println("Error: opción inválida. Vuelve a intentar.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (!option.equals("0"));
    }
}
