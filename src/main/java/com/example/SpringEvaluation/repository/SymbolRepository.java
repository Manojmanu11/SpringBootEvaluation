package com.example.SpringEvaluation.repository;

import com.example.SpringEvaluation.entity.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymbolRepository extends JpaRepository<Symbol,Long> {
    boolean existsBySymbol(String symbol);

}
