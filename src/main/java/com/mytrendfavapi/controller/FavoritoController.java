package com.mytrendfavapi.controller;

import com.mytrendfavapi.model.Favorito;
import com.mytrendfavapi.model.Roupa;
import com.mytrendfavapi.service.FavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/favoritos")  // Base URL para os endpoints de favoritos
public class FavoritoController {

    @Autowired
    private FavoritoService favoritoService;  // Injeção do serviço de favoritos

    // Endpoint para listar todas as roupas favoritas de um usuário
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Roupa>> listarFavoritosPorUsuario(@PathVariable Long usuarioId) {
        List<Roupa> favoritos = favoritoService.listarFavoritosPorUsuario(usuarioId);
        if (favoritos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(favoritos);
    }

    // Endpoint para adicionar uma roupa aos favoritos de um usuário
    @PostMapping("/usuario/{usuarioId}/roupa/{roupaId}")
    public ResponseEntity<Favorito> adicionarFavorito(@PathVariable Long usuarioId, @PathVariable Long roupaId) {
        Favorito novoFavorito = favoritoService.adicionarFavorito(usuarioId, roupaId);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoFavorito);
    }

    // Endpoint para remover uma roupa dos favoritos de um usuário
    @DeleteMapping("/usuario/{usuarioId}/roupa/{roupaId}")
    public ResponseEntity<Void> removerFavorito(@PathVariable Long usuarioId, @PathVariable Long roupaId) {
        Optional<Favorito> favoritoExistente = favoritoService.obterFavorito(usuarioId, roupaId);
        if (favoritoExistente.isPresent()) {
            favoritoService.removerFavorito(usuarioId, roupaId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
