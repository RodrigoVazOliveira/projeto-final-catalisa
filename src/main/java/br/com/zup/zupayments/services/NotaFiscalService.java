package br.com.zup.zupayments.services;

import br.com.zup.zupayments.models.NotaFiscal;
import br.com.zup.zupayments.repositories.NotaFiscalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotaFiscalService {

    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    public NotaFiscal cadastrarNotaFiscal(NotaFiscal fiscal){
        try{
            NotaFiscal objFiscal = notaFiscalRepository.save(fiscal);
            return objFiscal;
        }catch (Exception error){
            throw new RuntimeException("Nota fiscal já cadastrada");
        }
    }
}
