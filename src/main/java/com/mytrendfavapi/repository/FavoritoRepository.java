package com.mytrendfavapi.repository;

import com.mytrendfavapi.model.Favorito;
import com.mytrendfavapi.model.Roupa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {

    @Query("SELECT f.roupa FROM Favorito f WHERE f.usuario.id = :usuarioId")
    List<Roupa> findRoupasFavoritasByUsuarioId(Long usuarioId);

    Optional<Favorito> findByUsuarioIdAndRoupaId(Long usuarioId, Long roupaId);

    void deleteByUsuarioIdAndRoupaId(Long usuarioId, Long roupaId);
}
