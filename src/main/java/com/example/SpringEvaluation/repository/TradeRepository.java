package com.example.SpringEvaluation.repository;

import com.example.SpringEvaluation.entity.Trade;
import com.example.SpringEvaluation.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeRepository extends JpaRepository<Trade,Long> {

    List<Trade> findByUserTradeId(UserDetail currentUserDetail);

}
