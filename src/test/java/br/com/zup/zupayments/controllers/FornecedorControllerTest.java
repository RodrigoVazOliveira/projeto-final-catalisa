package br.com.zup.zupayments.controllers;

import br.com.zup.zupayments.services.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class FornecedorControllerTest {

    @MockBean
    private FornecedorService fornecedorService;

    @Autowired
    private MockMvc mockMvc;
}
