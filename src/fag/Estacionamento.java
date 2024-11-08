package fag;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    private List<Vaga> vagas;
    private List<Registro> registros;

    public Estacionamento() {
        this.vagas = new ArrayList<>();
        this.registros = new ArrayList<>();
    }

    public void adicionarVaga(Vaga vaga) {
        vagas.add(vaga);
    }

    public void registrarEntrada(Carro carro) {
        Vaga vagaDisponivel = encontrarVagaDisponivel(carro.getTamanho());
        if (vagaDisponivel != null) {
            vagaDisponivel.ocupar();
            carro.setHoraEntrada(LocalDateTime.now());
            registros.add(new Registro(carro, vagaDisponivel, carro.getHoraEntrada()));
            System.out.println("Veículo " + carro.getPlaca() + " estacionado na vaga " + vagaDisponivel.getNumero());
        } else {
            System.out.println("Nenhuma vaga disponível para o tamanho do veículo.");
        }
    }

    private Vaga encontrarVagaDisponivel(String tamanho) {
        for (Vaga vaga : vagas) {
            if (vaga.isDisponivel() && vaga.getTamanho().equalsIgnoreCase(tamanho)) {
                return vaga;
            }
        }
        return null;
    }

    public void registrarSaida(String placa) {
        for (Registro registro : registros) {
            if (registro.getCarro().getPlaca().equals(placa) && registro.getCarro().getHoraSaida() == null) {
                LocalDateTime saida = LocalDateTime.now();
                registro.registrarSaida(saida);
                registro.getVaga().liberar();
                System.out.println("Saída registrada para o veículo " + placa + ". Valor a pagar: R$ " + registro.getValorPago());
                return;
            }
        }
        System.out.println("Veículo não encontrado.");
    }

    public void exibirRelatorio() {
        System.out.println("Relatório de Vagas Ocupadas:");
        for (Registro registro : registros) {
            if (registro.getCarro().getHoraSaida() == null) {
                System.out.println("Vaga " + registro.getVaga().getNumero() + 
                                   " | Tamanho: " + registro.getVaga().getTamanho() + 
                                   " | Placa: " + registro.getCarro().getPlaca());
            }
        }
    }

    public void exibirHistorico() {
        System.out.println("Histórico de Permanência:");
        for (Registro registro : registros) {
            if (registro.isCompleto()) {
                System.out.println(registro);
            }
        }
    }
}
