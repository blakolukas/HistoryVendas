package com.projarq.historyservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/history")
public class HistoryController {
    private final OrcamentoEfetivadoService service;

    @Autowired
    public HistoryController(OrcamentoEfetivadoService service) {
        this.service = service;
    }

    @PostMapping("/orcamento")
    public String registrarOrcamento(@RequestBody OrcamentoEfetivado orcamento) {
        return service.salvar(orcamento);
    }

    @GetMapping("/totais")
    public Map<String, Double> totaisPorMesAno(@RequestParam int mes, @RequestParam int ano) {
        List<OrcamentoEfetivado> orcs = service.getRepository().findAll();
        double totalVendido = orcs.stream()
            .filter(o -> o.getDataCriacao().getYear() == ano && o.getDataCriacao().getMonthValue() == mes)
            .mapToDouble(OrcamentoEfetivado::getCustoConsumidor)
            .sum();
        double totalImposto = orcs.stream()
            .filter(o -> o.getDataCriacao().getYear() == ano && o.getDataCriacao().getMonthValue() == mes)
            .mapToDouble(OrcamentoEfetivado::getImposto)
            .sum();
        return Map.of("totalVendido", totalVendido, "totalImposto", totalImposto);
    }
} 