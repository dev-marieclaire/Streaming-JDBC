package Streaming;

public class PlanEstandar implements iPlanSuscripcion {
    public Double calcularCosto(Integer meses)
    { return (Double) (9.0 * meses); }
}