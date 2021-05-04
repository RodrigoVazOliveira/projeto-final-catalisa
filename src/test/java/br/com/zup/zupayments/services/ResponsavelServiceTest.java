package br.com.zup.zupayments.services;

import br.com.zup.zupayments.models.Responsavel;
import br.com.zup.zupayments.repositories.ResponsavelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

@Test
public class ResponsavelServiceTest {

    @Autowired
    private ResponsavelService responsavelService;

    @MockBean
    private ResponsavelRepository responsavelRepository;
    private Responsavel responsavel;

    @BeforeEach
    public void setup(){
        this.responsavel = new Responsavel(
                "bomdia@zup.com",
                "Bom dia Silva",
                "Facilites",
                true
        );
    }
}
