package com.example.SpringEvaluation.repository;
import com.example.SpringEvaluation.constants.StringConstants;
import com.example.SpringEvaluation.entity.Order;
import com.example.SpringEvaluation.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query(StringConstants.FindByOrders)
    List<Order> findBuyOrders();

    @Query(StringConstants.FindByUserOrderIdOrderType)
    List<Order> findSellOrders();
    List<Order> findByUserOrdersIdAndOrderTypeAndQuantityGreaterThanAndStatus(UserDetail userDetail, String orderType, int quantity, String status);

    List<Order> findByUserOrdersId( UserDetail currentUserDetails);
}


