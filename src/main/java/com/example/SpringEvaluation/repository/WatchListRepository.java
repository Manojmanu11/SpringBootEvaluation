package com.example.SpringEvaluation.repository;



import com.example.SpringEvaluation.entity.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchListRepository extends JpaRepository<WatchList,Long> {

}
