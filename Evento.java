package Entidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Evento {
    // Atributos privados da classe Evento
    private int codigo;
    private String titulo;
    private String local;
    private Date data;
    private String hora;
    private int capacidade;
    private int lugaresVendidos;
    private double valorIngresso;
    private String status;

    // Variáveis estáticas e finais
    private static final double VALOR_DEFAULT_INGRESSO = 100.0;
    private static int proximoCodigo = 1;
    private static final int CAPACIDADE_DEFAULT = 3000;
    private static final int QTD_INGRESSOS_VENDA_DEFAULT = 2;

    // Construtores sobrecarregados
    public Evento(String titulo, String local, String dataStr, String hora, int capacidade, double valorIngresso) {
        this.codigo = proximoCodigo++;
        this.titulo = titulo;
        this.local = local;
        this.data = parseDate(dataStr);
        this.hora = hora;
        this.capacidade = capacidade;
        this.lugaresVendidos = 0;
        this.valorIngresso = valorIngresso;
        this.status = "Vendas não iniciadas";
    }

    public Evento(String titulo, String local, String dataStr, String hora) {
        this(titulo, local, dataStr, hora, CAPACIDADE_DEFAULT, VALOR_DEFAULT_INGRESSO);
    }

    // Métodos getter e setter
    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Date getData() {
        return data;
    }

    public String getDataStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }

    public void setData(String dataStr) {
        this.data = parseDate(dataStr);
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getLugaresVendidos() {
        return lugaresVendidos;
    }

    public double getValorIngresso() {
        return valorIngresso;
    }

    public void setValorIngresso(double valorIngresso) {
        this.valorIngresso = valorIngresso;
    }

    public String getStatus() {
        return status;
    }

    // Método para iniciar a venda de ingressos
    public void iniciarVenda() {
        if (status.equals("Vendas não iniciadas")) {
            status = "Vendas em andamento";
        } else {
            System.out.println("A venda de ingressos já foi iniciada.");
        }
    }

    // Método para finalizar a venda de ingressos
    public void finalizarVenda() {
        if (status.equals("Vendas em andamento")) {
            status = "Vendas encerradas";
        } else {
            System.out.println("A venda de ingressos já foi encerrada.");
        }
    }

    // Método para cancelar o evento
    public void cancelarEvento() {
        if (!status.equals("Evento cancelado")) {
            status = "Evento cancelado";
        } else {
            System.out.println("O evento já foi cancelado.");
        }
    }

    // Método para marcar o evento como ocorrido
    public void marcarComoOcorrido() {
        if (!status.equals("Evento cancelado") && !status.equals("Evento ocorrido")) {
            status = "Evento ocorrido";
        } else {
            System.out.println("O evento já foi cancelado ou já ocorreu.");
        }
    }

    // Método para realizar a venda de ingressos
    public void venda(int quantidade) {
        if (status.equals("Vendas em andamento")) {
            int lugaresDisponiveis = capacidade - lugaresVendidos;
            if (quantidade <= lugaresDisponiveis) {
                lugaresVendidos += quantidade;
                System.out.println(quantidade + " ingresso(s) vendido(s) para o evento " + titulo + ".");
            } else {
                System.out.println("Não há lugares suficientes disponíveis.");
            }
        } else {
            System.out.println("A venda de ingressos ainda não foi iniciada.");
        }
    }

    // Sobrecarga do método venda() para vender a quantidade default de ingressos
    public void venda() {
        venda(QTD_INGRESSOS_VENDA_DEFAULT);
    }

    // Método para obter a quantidade de lugares livres
    public int qtdLugarLivre() {
        return capacidade - lugaresVendidos;
    }

    // Método para calcular o valor arrecadado
    public double calcularValorArrecadado() {
        return lugaresVendidos * valorIngresso;
    }


    @Override
    public String toString() {
        return "Código: " + codigo +
                "\nTítulo: " + titulo +
                "\nLocal: " + local +
                "\nData: " + getDataStr() +
                "\nHora: " + hora +
                "\nCapacidade: " + capacidade +
                "\nLugares Vendidos: " + lugaresVendidos +
                "\nValor do Ingresso: " + valorIngresso +
                "\nStatus: " + status;
    }

    // Método privado para realizar a conversão de String para Date
    private boolean validarData(String dataStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // Impede que a data seja interpretada de forma flexível

        try {
            sdf.parse(dataStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private Date parseDate(String dataStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            if (!validarData(dataStr)) {
                throw new IllegalArgumentException("Data inválida. Digite no formato dd/MM/yyyy.");
            }

            return sdf.parse(dataStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
    }}
    
}
