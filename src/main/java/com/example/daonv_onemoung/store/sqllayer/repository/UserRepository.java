package com.example.daonv_onemoung.store.sqllayer.repository;

import com.example.daonv_onemoung.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
