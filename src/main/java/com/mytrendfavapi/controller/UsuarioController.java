package com.mytrendfavapi.controller;

import com.mytrendfavapi.model.Usuario;
import com.mytrendfavapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController  // Indica que esta classe é um controlador REST
@RequestMapping("/api/usuarios")  // Base URL para os endpoints do controlador
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;  // Injeção do serviço de usuário

    // Endpoint para listar todos os usuários (opcional)
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarTodosUsuarios();
    }

    // Endpoint para obter um usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obterUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.obterUsuarioPorId(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para criar um novo usuário
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.criarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    // Endpoint para atualizar um usuário existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioService.obterUsuarioPorId(id);
        if (usuarioExistente.isPresent()) {
            Usuario usuarioAtualizado = usuarioService.atualizarUsuario(id, usuario);
            return ResponseEntity.ok(usuarioAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para deletar um usuário por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.obterUsuarioPorId(id);
        if (usuario.isPresent()) {
            usuarioService.deletarUsuario(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
