package Aplicaçao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import Entidades.Evento;

public class MenuEvento {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Evento> eventos = new ArrayList<>();

        int opcao;
        do {
            System.out.println("\n----- MENU -----");
            System.out.println("1. Cadastrar um Evento");
            System.out.println("2. Remover um Evento");
            System.out.println("3. Consultar os dados de um determinado evento");
            System.out.println("4. Iniciar a venda de ingressos para um determinado evento");
            System.out.println("5. Encerrar a venda de ingressos para um determinado evento");
            System.out.println("6. Cancelar um determinado evento");
            System.out.println("7. Marcar um determinado evento como já ocorrido");
            System.out.println("8. Consultar a quantidade de lugares livres para um determinado evento");
            System.out.println("9. Vender ingresso de um determinado evento");
            System.out.println("10. Vender ingresso de um evento com quantidade de ingressos default");
            System.out.println("11. Listar os dados de todos os eventos");
            System.out.println("12. Valor total arrecadado");
            System.out.println("13. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    // Cadastro de Evento
                    System.out.print("Digite o título do evento: ");
                    scanner.nextLine(); // Limpar o buffer do teclado
                    String titulo = scanner.nextLine();
                    System.out.print("Digite o local do evento: ");
                    String local = scanner.nextLine();
                    System.out.print("Digite a data do evento (dd/MM/yyyy): ");
                    String data = scanner.next();
                    System.out.print("Digite a hora do evento: ");
                    String hora = scanner.next();
                    System.out.print("Digite a capacidade do evento: ");
                    int capacidade = scanner.nextInt();
                    System.out.print("Digite o valor do ingresso: ");
                    double valorIngresso = scanner.nextDouble();

                    Evento evento = new Evento(titulo, local, data, hora, capacidade, valorIngresso);
                    eventos.add(evento);
                    System.out.println("Evento cadastrado com sucesso.");
                    break;
                case 2:
                    // Remover Evento
                    System.out.print("Digite o código do evento a ser removido: ");
                    int codigoRemover = scanner.nextInt();
                    boolean eventoRemovido = false;
                    for (Evento ev : eventos) {
                        if (ev.getCodigo() == codigoRemover) {
                            eventos.remove(ev);
                            eventoRemovido = true;
                            break;
                        }
                    }
                    if (eventoRemovido) {
                        System.out.println("Evento removido com sucesso.");
                    } else {
                        System.out.println("Evento não encontrado.");
                    }
                    break;
                case 3:
                    // Consultar dados do Evento
                    System.out.print("Digite o código do evento: ");
                    int codigoConsultar = scanner.nextInt();
                    boolean eventoEncontrado = false;
                    for (Evento ev : eventos) {
                        if (ev.getCodigo() == codigoConsultar) {
                            System.out.println(ev.toString());
                            eventoEncontrado = true;
                            break;
                        }
                    }
                    if (!eventoEncontrado) {
                        System.out.println("Evento não encontrado.");
                    }
                    break;
                case 4:
                    // Iniciar venda de ingressos
                    System.out.print("Digite o código do evento: ");
                    int codigoIniciarVenda = scanner.nextInt();
                    boolean vendaIniciada = false;
                    for (Evento ev : eventos) {
                        if (ev.getCodigo() == codigoIniciarVenda) {
                            ev.iniciarVenda();
                            vendaIniciada = true;
                            break;
                        }
                    }
                    if (vendaIniciada) {
                        System.out.println("Venda de ingressos iniciada com sucesso.");
                    } else {
                        System.out.println("Evento não encontrado.");
                    }
                    break;
                case 5:
                    // Encerrar venda de ingressos
                    System.out.print("Digite o código do evento: ");
                    int codigoEncerrarVenda = scanner.nextInt();
                    boolean vendaEncerrada = false;
                    for (Evento ev : eventos) {
                        if (ev.getCodigo() == codigoEncerrarVenda) {
                            ev.finalizarVenda();
                            vendaEncerrada = true;
                            break;
                        }
                    }
                    if (vendaEncerrada) {
                        System.out.println("Venda de ingressos encerrada com sucesso.");
                    } else {
                        System.out.println("Evento não encontrado.");
                    }
                    break;
                case 6:
                    // Cancelar Evento
                    System.out.print("Digite o código do evento: ");
                    int codigoCancelarEvento = scanner.nextInt();
                    boolean eventoCancelado = false;
                    for (Evento ev : eventos) {
                        if (ev.getCodigo() == codigoCancelarEvento) {
                            ev.cancelarEvento();
                            eventoCancelado = true;
                            break;
                        }
                    }
                    if (eventoCancelado) {
                        System.out.println("Evento cancelado com sucesso.");
                    } else {
                        System.out.println("Evento não encontrado.");
                    }
                    break;
                case 7:
                    // Marcar Evento como ocorrido
                    System.out.print("Digite o código do evento: ");
                    int codigoMarcarOcorrido = scanner.nextInt();
                    boolean eventoMarcadoOcorrido = false;
                    for (Evento ev : eventos) {
                        if (ev.getCodigo() == codigoMarcarOcorrido) {
                            ev.marcarComoOcorrido();
                            eventoMarcadoOcorrido = true;
                            break;
                        }
                    }
                    if (eventoMarcadoOcorrido) {
                        System.out.println("Evento marcado como ocorrido com sucesso.");
                    } else {
                        System.out.println("Evento não encontrado.");
                    }
                    break;
                case 8:
                    // Consultar quantidade de lugares livres
                    System.out.print("Digite o código do evento: ");
                    int codigoConsultarLugaresLivres = scanner.nextInt();
                    boolean eventoEncontradoLugaresLivres = false;
                    for (Evento ev : eventos) {
                        if (ev.getCodigo() == codigoConsultarLugaresLivres) {
                            System.out.println("Lugares livres: " + ev.qtdLugarLivre());
                            eventoEncontradoLugaresLivres = true;
                            break;
                        }
                    }
                    if (!eventoEncontradoLugaresLivres) {
                        System.out.println("Evento não encontrado.");
                    }
                    break;
                case 9:
                    // Vender ingresso
                    System.out.print("Digite o código do evento: ");
                    int codigoVenderIngresso = scanner.nextInt();
                    boolean eventoEncontradoVenderIngresso = false;
                    for (Evento ev : eventos) {
                        if (ev.getCodigo() == codigoVenderIngresso) {
                            System.out.print("Digite a quantidade de ingressos a vender: ");
                            int quantidadeIngressos = scanner.nextInt();
                            ev.venda(quantidadeIngressos);
                            eventoEncontradoVenderIngresso = true;
                            break;
                        }
                    }
                    if (!eventoEncontradoVenderIngresso) {
                        System.out.println("Evento não encontrado.");
                    }
                    break;
                case 10:
                    // Vender ingresso com quantidade default
                    System.out.print("Digite o código do evento: ");
                    int codigoVenderIngressoDefault = scanner.nextInt();
                    boolean eventoEncontradoVenderIngressoDefault = false;
                    for (Evento ev : eventos) {
                        if (ev.getCodigo() == codigoVenderIngressoDefault) {
                            ev.venda();
                            eventoEncontradoVenderIngressoDefault = true;
                            break;
                        }
                    }
                    if (!eventoEncontradoVenderIngressoDefault) {
                        System.out.println("Evento não encontrado.");
                    }
                    break;
                case 13:
                    // Sair
                    System.out.println("Encerrando o programa...");
                    break;
                case 12:
                    // Mostrar valor arrecadado
                    double valorTotalArrecadado = 0.0;
                    for (Evento ev : eventos) {
                        valorTotalArrecadado += ev.calcularValorArrecadado();
                    }
                    System.out.println("Valor total arrecadado: R$" + valorTotalArrecadado);
                    break;
                default:
                    System.out.println("Opção inválida. Digite um número de 1 a 13.");
                    break;
            }
        } while (opcao != 12);}
}
