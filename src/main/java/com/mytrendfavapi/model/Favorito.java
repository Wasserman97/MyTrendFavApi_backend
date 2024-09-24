package com.mytrendfavapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "favoritos")
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne  // Vários favoritos podem pertencer a um único usuário
    @JoinColumn(name = "usuario_id", nullable = false)  // Chave estrangeira para usuário
    private Usuario usuario;

    @ManyToOne  // Vários favoritos podem estar relacionados a uma única roupa
    @JoinColumn(name = "roupa_id", nullable = false)  // Chave estrangeira para roupa
    private Roupa roupa;

    // Construtores
    public Favorito() {
    }

    public Favorito(Usuario usuario, Roupa roupa) {
        this.usuario = usuario;
        this.roupa = roupa;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Roupa getRoupa() {
        return roupa;
    }

    public void setRoupa(Roupa roupa) {
        this.roupa = roupa;
    }
}
