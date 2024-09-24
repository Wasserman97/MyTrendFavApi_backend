package com.mytrendfavapi.model;

import jakarta.persistence.*;
import java.util.List;

@Entity  // Anotação que define esta classe como uma entidade JPA (tabela no banco de dados)
@Table(name = "usuarios")  // Nome da tabela no banco de dados
public class Usuario {

    @Id  // Define a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto incremento do ID
    private Long id;

    @Column(nullable = false)  // O campo 'nome' não pode ser nulo
    private String nome;

    @Column(nullable = false, unique = true)  // O campo 'email' é único e não pode ser nulo
    private String email;

    @Column(nullable = false)  // O campo 'senha' não pode ser nulo
    private String senha;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    // Um usuário pode ter várias roupas, mapeamento bidirecional
    private List<Roupa> roupas;

    // Construtores
    public Usuario() {
    }

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Roupa> getRoupas() {
        return roupas;
    }

    public void setRoupas(List<Roupa> roupas) {
        this.roupas = roupas;
    }

    // Método utilitário para adicionar uma roupa à lista de roupas
    public void adicionarRoupa(Roupa roupa) {
        roupas.add(roupa);
        roupa.setUsuario(this);
    }

    // Método utilitário para remover uma roupa da lista de roupas
    public void removerRoupa(Roupa roupa) {
        roupas.remove(roupa);
        roupa.setUsuario(null);
    }
}
