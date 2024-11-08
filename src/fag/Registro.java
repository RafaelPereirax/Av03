package fag;

import java.time.Duration;
import java.time.LocalDateTime;

public class Registro {
    private Carro carro;
    private Vaga vaga;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;
    private double valorPago;
    private boolean completo = false;

    public Registro(Carro carro, Vaga vaga, LocalDateTime horaEntrada) {
        this.carro = carro;
        this.vaga = vaga;
        this.horaEntrada = horaEntrada;
    }

    public Carro getCarro() {
        return carro;
    }
    public boolean isCompleto() {
    	return completo;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void registrarSaida(LocalDateTime horaSaida) {
        this.horaSaida = horaSaida;
        this.completo = true;
        calcularValor();
    }

    private void calcularValor() {
        long minutos = Duration.between(horaEntrada, horaSaida).toMinutes();
        if (minutos <= 60) {
            valorPago = 5.0;
        } else if (minutos <= 180) {
            valorPago = 10.0;
        } else {
            valorPago = 15.0;
        }
    }

    public double getValorPago() {
        return valorPago;
    }

    @Override
    public String toString() {
        return "Veículo: " + carro.getPlaca() + 
               " | Tempo: " + Duration.between(horaEntrada, horaSaida).toMinutes() + " minutos" +
               " | Valor Pago: R$ " + String.format("%.2f", valorPago);
    }
}
