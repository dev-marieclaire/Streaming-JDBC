package Streaming;

public class PlanBasico implements iPlanSuscripcion {
    PlanBasico () {}
    
    public Double calcularCosto(Integer meses)
    { return (Double) (5.0 * meses); }
}