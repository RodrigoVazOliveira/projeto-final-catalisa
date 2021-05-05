package br.com.zup.zupayments.dtos.pedidodecompras.saida;

import br.com.zup.zupayments.models.Fornecedor;

public class PedidoDeCompraFornecedorDTO {
    private String cnpjOuCpf;
    private String razaoSocial;

    public PedidoDeCompraFornecedorDTO() {
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
        PedidoDeCompraFornecedorDTO dto = new PedidoDeCompraFornecedorDTO();
        dto.setCnpjOuCpf(fornecedor.getCnpjOuCpf());
        dto.setRazaoSocial(fornecedor.getRazaoSocial());
        return dto;
    }
}
