package com.mytrendfavapi.service;

import com.mytrendfavapi.model.Favorito;
import com.mytrendfavapi.model.Roupa;
import com.mytrendfavapi.model.Usuario;
import com.mytrendfavapi.repository.FavoritoRepository;
import com.mytrendfavapi.repository.RoupaRepository;
import com.mytrendfavapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritoService {

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private RoupaRepository roupaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Roupa> listarFavoritosPorUsuario(Long usuarioId) {
        return favoritoRepository.findRoupasFavoritasByUsuarioId(usuarioId);
    }

    public Favorito adicionarFavorito(Long usuarioId, Long roupaId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Roupa roupa = roupaRepository.findById(roupaId).orElseThrow(() -> new RuntimeException("Roupa não encontrada"));

        Favorito favorito = new Favorito(usuario, roupa);
        return favoritoRepository.save(favorito);
    }

    public Optional<Favorito> obterFavorito(Long usuarioId, Long roupaId) {
        return favoritoRepository.findByUsuarioIdAndRoupaId(usuarioId, roupaId);
    }

    public void removerFavorito(Long usuarioId, Long roupaId) {
        favoritoRepository.deleteByUsuarioIdAndRoupaId(usuarioId, roupaId);
    }
}
