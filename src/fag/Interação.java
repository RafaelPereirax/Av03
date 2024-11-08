package fag;

import java.util.Scanner;

public class Interação {
    public static void main(String[] args) {
        Estacionamento estacionamento = new Estacionamento();
        Scanner scanner = new Scanner(System.in);

        // adicao de vagas
        estacionamento.adicionarVaga(new Vaga(1, "pequeno"));
        estacionamento.adicionarVaga(new Vaga(2, "medio"));
        estacionamento.adicionarVaga(new Vaga(3, "grande"));

        while (true) {
            System.out.println("\n--- Sistema de Gerenciamento de Estacionamento ---");
            System.out.println("1. Registrar entrada de veículo");
            System.out.println("2. Registrar saída de veículo");
            System.out.println("3. Exibir relatório de vagas ocupadas");
            System.out.println("4. Exibir histórico de permanência");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("Placa: ");
                    String placa = scanner.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Tamanho (pequeno, medio, grande): ");
                    String tamanho = scanner.nextLine();
                    Carro carro = new Carro(placa, modelo, tamanho);
                    estacionamento.registrarEntrada(carro);
                    break;
                case 2:
                    System.out.print("Placa do veículo: ");
                    String placaSaida = scanner.nextLine();
                    estacionamento.registrarSaida(placaSaida);
                    break;
                case 3:
                    estacionamento.exibirRelatorio();
                    break;
                case 4:
                    estacionamento.exibirHistorico();
                    break;
                case 5:
                    System.out.println("Encerrando o sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
