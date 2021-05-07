package br.com.zup.zupayments.enums;

public enum RolesEnum {
    PERFIL_MASTER(2,"ROLE_MASTER","hasRole('PERFIL_MASTER')","/notas_ficais/**","Administra tudo"),
    PERFIL_FINANCEIRO(1,"ROLE_FINANCEIRO","hasRole('PERFIL_FINANCEIRO')","/notas_ficais/**","Administra notas fiscais"),
    PERFIL_COMPRAS(3,"ROLE_COMPRAS","hasRole('PERFIL_COMPRAS')","/fornecedores/**","Administra fornecedores");

    private Integer id;
    private String role;
    private String roleAcess;
    private String url;
    private String descricao;

    RolesEnum(Integer id, String role, String roleAcess, String url, String descricao) {
        this.id = id;
        this.role = role;
        this.roleAcess = roleAcess;
        this.url = url;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleAcess() {
        return roleAcess;
    }

    public void setRoleAcess(String roleAcess) {
        this.roleAcess = roleAcess;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
