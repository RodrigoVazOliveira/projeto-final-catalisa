package br.com.zup.zupayments.repositories;

import br.com.zup.zupayments.models.NotaFiscal;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface NotaFiscalRepository extends CrudRepository<NotaFiscal, Long> {
    Iterable<NotaFiscal> findAllByDataDeEmissaoBetween(LocalDate dataInicial, LocalDate dataFinal);
}