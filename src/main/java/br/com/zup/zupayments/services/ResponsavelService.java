package br.com.zup.zupayments.services;

import br.com.zup.zupayments.models.Responsavel;
import br.com.zup.zupayments.repositories.ResponsavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponsavelService {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    public Responsavel cadastrarResponsavel (Responsavel responsavel){
        try{
            Responsavel obj = responsavelRepository.save(responsavel);
            return responsavel;
        }catch (Exception error){
            throw new RuntimeException("Responsável já cadastrado");
        }
    }
}
