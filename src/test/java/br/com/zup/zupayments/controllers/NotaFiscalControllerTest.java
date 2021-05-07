package br.com.zup.zupayments.controllers;

import br.com.zup.zupayments.dtos.notafiscal.entrada.CadastrarNotaFiscalDTO;
import br.com.zup.zupayments.models.Fornecedor;
import br.com.zup.zupayments.models.NotaFiscal;
import br.com.zup.zupayments.models.PedidoDeCompra;
import br.com.zup.zupayments.models.Responsavel;
import br.com.zup.zupayments.services.NotaFiscalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@WebMvcTest(NotaFiscalController.class)
public class NotaFiscalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotaFiscalService notaFiscalService;

    private NotaFiscal notaFiscalteste;
    private CadastrarNotaFiscalDTO cadastrarNotaFiscalDTO;

    @BeforeEach
    public void setup() {
        this.cadastrarNotaFiscalDTO = new CadastrarNotaFiscalDTO();
        this.cadastrarNotaFiscalDTO.setNumeroDaNota(1L);
        this.cadastrarNotaFiscalDTO.setCnpjOuCpfFornecedor("05.792.077/0001-65");
        this.cadastrarNotaFiscalDTO.setValorAPagar(2000.00);
        this.cadastrarNotaFiscalDTO.setDataDeEmissao(LocalDate.now());
        this.cadastrarNotaFiscalDTO.setPedidoDeCompras(Arrays.asList(1L, 2L));
        this.cadastrarNotaFiscalDTO.setDataDeEnvio(LocalDate.now());
        this.cadastrarNotaFiscalDTO.setEmailDoResponsavel("rodrigo.vaz@zup.com.br");

        this.notaFiscalteste = this.cadastrarNotaFiscalDTO.converterDtoParaModelo();
    }


    @Test
    public void testarCadastrarNotaFiscal() throws Exception {
        this.notaFiscalteste.setId(1L);
        SimpleDateFormat dfDto = new SimpleDateFormat("dd/MM/yyyy");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
        objectMapper.setDateFormat(dfDto);

        notaFiscalteste.setId(1L);
        String jsonEntrada = objectMapper.writeValueAsString(this.cadastrarNotaFiscalDTO);
        SimpleDateFormat df = new SimpleDateFormat("yyyyy-MM-dd");
        objectMapper.setDateFormat(df);
        String jsonSaida   = objectMapper.writeValueAsString(this.notaFiscalteste);

        Mockito.when(notaFiscalService.cadastrarNotaFiscal(Mockito.any())).thenReturn(notaFiscalteste);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/notas_fiscais/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonEntrada)
        ).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(jsonSaida));
    }

    @Test
    public void testarCancelamentoDeNotaFiscal() throws Exception {
        this.notaFiscalteste.setId(1L);
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
        objectMapper.setDateFormat(df);

        String jsonSaida = objectMapper.writeValueAsString(this.notaFiscalteste);

        Mockito.when(notaFiscalService.cancelarNF(Mockito.anyLong())).thenReturn(this.notaFiscalteste);
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/notas_fiscais/1/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(jsonSaida));
    }
}
