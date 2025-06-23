package com.projarq.historyservice;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrcamentoEfetivadoListener {
    private final OrcamentoEfetivadoService service;

    @Autowired
    public OrcamentoEfetivadoListener(OrcamentoEfetivadoService service) {
        this.service = service;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receive(OrcamentoEfetivadoMessage msg) {
        OrcamentoEfetivado orc = new OrcamentoEfetivado(
            msg.custoItens,
            msg.imposto,
            msg.desconto,
            msg.custoConsumidor,
            msg.estado,
            LocalDateTime.parse(msg.dataCriacao),
            msg.itens
        );
        service.salvar(orc);
    }

    public static class OrcamentoEfetivadoMessage {
        public double custoItens;
        public double imposto;
        public double desconto;
        public double custoConsumidor;
        public String estado;
        public String dataCriacao;
        public List<String> itens;
    }
} 