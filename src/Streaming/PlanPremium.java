package Streaming;

public class PlanPremium implements iPlanSuscripcion {
    public Double calcularCosto(Integer meses)
    { return (Double) (14.0 * meses); }
}