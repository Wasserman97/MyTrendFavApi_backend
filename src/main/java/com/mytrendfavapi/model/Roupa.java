package com.mytrendfavapi.model;

import jakarta.persistence.*;

@Entity  // Define que esta classe será mapeada como uma tabela no banco de dados
@Table(name = "roupas")  // Especifica o nome da tabela no banco de dados
public class Roupa {

    @Id  // Define o ID como a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // O valor do ID será gerado automaticamente
    private Long id;

    @Column(nullable = false)  // O campo 'descricao' não pode ser nulo
    private String descricao;

    @Column(nullable = false)  // O campo 'tipo' não pode ser nulo (Ex: camiseta, calça)
    private String tipo;

    @Column(nullable = false)  // O campo 'favorito' é um booleano que indica se a roupa é favorita
    private boolean favorito;

    @ManyToOne  // Muitos itens de roupa podem pertencer a um único usuário
    @JoinColumn(name = "usuario_id", nullable = false)  // Define a chave estrangeira
    private Usuario usuario;  // Cada roupa pertence a um usuário

    // Construtores
    public Roupa() {
    }

    public Roupa(String descricao, String tipo, boolean favorito, Usuario usuario) {
        this.descricao = descricao;
        this.tipo = tipo;
        this.favorito = favorito;
        this.usuario = usuario;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
