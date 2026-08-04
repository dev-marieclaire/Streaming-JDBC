package Streaming;

import models.UI;

public class Estadisticas
{
    Character tipo;
    Integer cantidad = 0;

    Estadisticas(Character tipo, Integer cantidad)
    { this.tipo = tipo; this.cantidad = cantidad; }

    public String toString()
    {
        return String.format(
            "tipo: %s\ncantidad: %d",
            (tipo == 'B') ? "Básico" :
            ((tipo == 'E') ? "Estándar" : "Premium"),
            cantidad);
    }

    public void display()
    {
        UI.drawLine(64, '-');
        System.out.println();
        System.out.println(toString());
    }
}
