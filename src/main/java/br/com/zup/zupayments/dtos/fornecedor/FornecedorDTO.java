package br.com.zup.zupayments.dtos.fornecedor;

import br.com.zup.zupayments.enums.CategoriaDeCusto;
import br.com.zup.zupayments.models.Fornecedor;

public class FornecedorDTO {

    private String CnpjOuCpf;

    private String RazaoSocial;

    private String logradouro;

    private Integer numero;

    private CategoriaDeCusto categoriaDeCusto;

    public FornecedorDTO() {
    }

    public String getCnpjOuCpf() {
        return CnpjOuCpf;
    }

    public void setCnpjOuCpf(String cnpjOuCpf) {
        CnpjOuCpf = cnpjOuCpf;
    }

    public String getRazaoSocial() {
        return RazaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        RazaoSocial = razaoSocial;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public CategoriaDeCusto getCategoriaDeCusto() {
        return categoriaDeCusto;
    }

    public void setCategoriaDeCusto(CategoriaDeCusto categoriaDeCusto) {
        this.categoriaDeCusto = categoriaDeCusto;
    }

    public static FornecedorDTO converterModelParaDTO(Fornecedor fornecedor){
        FornecedorDTO fornecedorDTO = new FornecedorDTO();

        fornecedorDTO.setCnpjOuCpf((fornecedor.getCnpjOuCpf()));
        fornecedorDTO.setRazaoSocial(fornecedor.getRazaoSocial());
        fornecedorDTO.setLogradouro(fornecedor.getLogradouro());
        fornecedorDTO.setNumero(fornecedor.getNumero());
        fornecedorDTO.setCategoriaDeCusto(fornecedor.getCategoriaDeCusto());

        return  fornecedorDTO;
    }
}