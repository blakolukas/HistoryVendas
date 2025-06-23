package com.projarq.historyservice;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrcamentoEfetivado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double custoItens;
    private double imposto;
    private double desconto;
    private double custoConsumidor;
    private String estado;
    private LocalDateTime dataCriacao;

    @ElementCollection
    private List<String> itens;

    public OrcamentoEfetivado() {}

    public OrcamentoEfetivado(double custoItens, double imposto, double desconto, double custoConsumidor, String estado, LocalDateTime dataCriacao, List<String> itens) {
        this.custoItens = custoItens;
        this.imposto = imposto;
        this.desconto = desconto;
        this.custoConsumidor = custoConsumidor;
        this.estado = estado;
        this.dataCriacao = dataCriacao;
        this.itens = itens;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public double getCustoConsumidor() {
        return custoConsumidor;
    }

    public double getImposto() {
        return imposto;
    }

    // Getters e setters omitidos para brevidade
} 