package br.com.zup.zupayments.controllers;

import br.com.zup.zupayments.dtos.responsavel.entrada.CadastrarResponsavelDTO;
import br.com.zup.zupayments.models.Responsavel;
import br.com.zup.zupayments.services.ResponsavelService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ResponsavelController.class)
public class ResponsavelControllerTest {

    @MockBean
    private ResponsavelService responsavelService;

    @Autowired
    private MockMvc mockMvc;

    private CadastrarResponsavelDTO cadastrarResponsavelDTO;

    private Responsavel responsavel;

    @BeforeEach
    public void setup() {
        this.cadastrarResponsavelDTO = new CadastrarResponsavelDTO(
                "claudio.silva.neto@zup.com.br",
            "Claúdio da Silva Neto",
            "FACILITIES"
        );

        this.responsavel = new Responsavel(
                "claudio.silva.neto@zup.com.br",
                "Claúdio da Silva Neto",
                "FACILITIES",
                true
        );
    }
}
