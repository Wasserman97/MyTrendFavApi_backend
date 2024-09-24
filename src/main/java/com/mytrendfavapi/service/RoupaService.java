package com.mytrendfavapi.service;

import com.mytrendfavapi.model.Roupa;
import com.mytrendfavapi.model.Usuario;
import com.mytrendfavapi.repository.RoupaRepository;
import com.mytrendfavapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoupaService {

    @Autowired
    private RoupaRepository roupaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Roupa> listarRoupasPorUsuario(Long usuarioId) {
        return roupaRepository.findByUsuarioId(usuarioId);
    }

    public Roupa adicionarRoupa(Long usuarioId, Roupa roupa) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        roupa.setUsuario(usuario);
        return roupaRepository.save(roupa);
    }

    public Optional<Roupa> obterRoupaPorId(Long id) {
        return roupaRepository.findById(id);
    }

    public Roupa atualizarRoupa(Long roupaId, Roupa roupaAtualizada) {
        Roupa roupaExistente = roupaRepository.findById(roupaId).orElseThrow(() -> new RuntimeException("Roupa não encontrada"));
        roupaExistente.setDescricao(roupaAtualizada.getDescricao());
        roupaExistente.setTipo(roupaAtualizada.getTipo());
        return roupaRepository.save(roupaExistente);
    }

    public Roupa marcarFavorito(Long roupaId, boolean favorito) {
        Roupa roupa = roupaRepository.findById(roupaId).orElseThrow(() -> new RuntimeException("Roupa não encontrada"));
        roupa.setFavorito(favorito);
        return roupaRepository.save(roupa);
    }

    public void deletarRoupa(Long roupaId) {
        roupaRepository.deleteById(roupaId);
    }
}
