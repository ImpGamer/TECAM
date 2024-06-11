package com.tecnam.api_get.repository;

import com.tecnam.api_get.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso,Long> {
    @Query(value = "SELECT c FROM Curso c WHERE c.titulo LIKE %:titulo%")
    List<Curso> findCursoByName(@Param("titulo")String titulo);
}
