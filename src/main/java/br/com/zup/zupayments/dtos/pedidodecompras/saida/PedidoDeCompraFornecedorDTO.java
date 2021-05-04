package br.com.zup.zupayments.dtos.pedidodecompras.saida;

import br.com.zup.zupayments.models.Fornecedor;

public class PedidoDeCompraFornecedorDTO {
    private String cnpjOuCpf;
    private String razaoSocial;

    public PedidoDeCompraFornecedorDTO() {
    }

    public PedidoDeCompraFornecedorDTO(String cnpjOuCpf, String razaoSocial) {
        this.cnpjOuCpf = cnpjOuCpf;
        this.razaoSocial = razaoSocial;
    }

    public String getCnpjOuCpf() {
        return cnpjOuCpf;
    }

    public void setCnpjOuCpf(String cnpjOuCpf) {
        this.cnpjOuCpf = cnpjOuCpf;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public static PedidoDeCompraFornecedorDTO converterModeloParaDto(Fornecedor fornecedor) {
        return new PedidoDeCompraFornecedorDTO(fornecedor.getCnpjOuCpf(), fornecedor.getRazaoSocial());
    }
}
