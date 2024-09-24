package com.mytrendfavapi.repository;

import com.mytrendfavapi.model.Roupa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoupaRepository extends JpaRepository<Roupa, Long> {
    List<Roupa> findByUsuarioId(Long usuarioId);
}
