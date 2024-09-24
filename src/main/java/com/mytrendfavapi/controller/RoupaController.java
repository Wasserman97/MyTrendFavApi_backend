package com.mytrendfavapi.controller;

import com.mytrendfavapi.model.Roupa;
import com.mytrendfavapi.service.RoupaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roupas")  // Base URL para os endpoints de roupas
public class RoupaController {

    @Autowired
    private RoupaService roupaService;  // Injeção do serviço de roupa

    // Endpoint para listar todas as roupas de um usuário
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Roupa>> listarRoupasPorUsuario(@PathVariable Long usuarioId) {
        List<Roupa> roupas = roupaService.listarRoupasPorUsuario(usuarioId);
        if (roupas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(roupas);
    }

    // Endpoint para adicionar uma nova roupa para um usuário
    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<Roupa> adicionarRoupa(@PathVariable Long usuarioId, @RequestBody Roupa roupa) {
        Roupa novaRoupa = roupaService.adicionarRoupa(usuarioId, roupa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaRoupa);
    }

    // Endpoint para atualizar uma roupa existente
    @PutMapping("/{roupaId}")
    public ResponseEntity<Roupa> atualizarRoupa(@PathVariable Long roupaId, @RequestBody Roupa roupa) {
        Optional<Roupa> roupaExistente = roupaService.obterRoupaPorId(roupaId);
        if (roupaExistente.isPresent()) {
            Roupa roupaAtualizada = roupaService.atualizarRoupa(roupaId, roupa);
            return ResponseEntity.ok(roupaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para favoritar ou desfavoritar uma roupa
    @PutMapping("/{roupaId}/favoritar")
    public ResponseEntity<Roupa> marcarFavorito(@PathVariable Long roupaId, @RequestParam boolean favorito) {
        Optional<Roupa> roupaExistente = roupaService.obterRoupaPorId(roupaId);
        if (roupaExistente.isPresent()) {
            Roupa roupaAtualizada = roupaService.marcarFavorito(roupaId, favorito);
            return ResponseEntity.ok(roupaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para deletar uma roupa
    @DeleteMapping("/{roupaId}")
    public ResponseEntity<Void> deletarRoupa(@PathVariable Long roupaId) {
        Optional<Roupa> roupaExistente = roupaService.obterRoupaPorId(roupaId);
        if (roupaExistente.isPresent()) {
            roupaService.deletarRoupa(roupaId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
