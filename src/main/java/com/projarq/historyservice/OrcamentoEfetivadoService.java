package com.projarq.historyservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrcamentoEfetivadoService {
    private final OrcamentoEfetivadoRepository repository;

    @Autowired
    public OrcamentoEfetivadoService(OrcamentoEfetivadoRepository repository) {
        this.repository = repository;
    }

    public String salvar(OrcamentoEfetivado orcamento) {
        repository.save(orcamento);
        return "Orçamento efetivado registrado com sucesso!";
    }

    public OrcamentoEfetivadoRepository getRepository() {
        return repository;
    }
} 