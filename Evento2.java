package Entidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Evento {
    private String codigo;
    private String nome;
    private Date data;
    private double valorIngresso;
    private int lugaresDisponiveis;
    private boolean ocorrido;

    public Evento(String codigo, String nome, Date data, double valorIngresso, int lugaresDisponiveis) {
        this.codigo = codigo;
        this.nome = nome;
        this.data = data;
        this.valorIngresso = valorIngresso;
        this.lugaresDisponiveis = lugaresDisponiveis;
        this.ocorrido = false;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public Date getData() {
        return data;
    }

    public double getValorIngresso() {
        return valorIngresso;
    }

    public int getLugaresDisponiveis() {
        return lugaresDisponiveis;
    }

    public boolean isOcorrido() {
        return ocorrido;
    }

    public void setOcorrido(boolean ocorrido) {
        this.ocorrido = ocorrido;
    }

    public void venderIngresso(int quantidade) {
        if (!ocorrido && quantidade <= lugaresDisponiveis) {
            lugaresDisponiveis -= quantidade;
            System.out.println("Venda realizada com sucesso!");
        } else {
            System.out.println("Não é possível realizar a venda.");
        }
    }

    public double calcularValorArrecadado() {
        return (lugaresDisponiveis + getLugaresVendidos()) * valorIngresso;
    }

    private int getLugaresVendidos() {
        return lugaresDisponiveisInicial - lugaresDisponiveis;
    }

    private int lugaresDisponiveisInicial;

    public void iniciarVenda() {
        lugaresDisponiveisInicial = lugaresDisponiveis;
    }

    public void encerrarVenda() {
        lugaresDisponiveis = 0;
    }

    public void cancelarEvento() {
        ocorrido = true;
        lugaresDisponiveis = 0;
    }

    public void marcarComoOcorrido() {
        ocorrido = true;
    }
}
