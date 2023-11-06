package com.example.SpringEvaluation.repository;



import com.example.SpringEvaluation.entity.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSession,Long> {

    UserSession findByUserId(Long id);

    void deleteByUserId(Long id);
}
