package br.com.zup.zupayments.services;

import br.com.zup.zupayments.exceptions.erros.ResponsavelJaCadastradoException;
import br.com.zup.zupayments.exceptions.erros.ResponsavelNaoExisteException;
import br.com.zup.zupayments.models.Responsavel;
import br.com.zup.zupayments.repositories.ResponsavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResponsavelService {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    public Responsavel cadastrarResponsavel (Responsavel responsavel){
        if (responsavelRepository.existsByEmail(responsavel.getEmail())) {
            throw new ResponsavelJaCadastradoException("Um responsável com e-mail " + responsavel.getEmail() + " já foi cadastrado!");
        }

        return responsavelRepository.save(responsavel);
    }

    public Responsavel procurarResponsavelPorEmail(String email) {
        Optional<Responsavel> optionalResponsavel = responsavelRepository.findById(email);

        if (optionalResponsavel.isEmpty()) {

            throw new ResponsavelNaoExisteException("Não existe responsável com e-mail" + email);
        }

        return optionalResponsavel.get();
    }

    public void ativarOuDesativarResponsavel(String emailResponsavel) {
        Responsavel responsavelAtual = procurarResponsavelPorEmail(emailResponsavel);
        responsavelAtual.setAtivo(!responsavelAtual.getAtivo());
        responsavelRepository.save(responsavelAtual);
    }
}
