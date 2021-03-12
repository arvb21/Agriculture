package com.example.Agriculture.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Agriculture.model.DAOUser;



public interface UserRepository extends JpaRepository<DAOUser, Long> {
	boolean existsByEmail(String email);

	@Query(value = "SELECT * FROM user WHERE user_id = ?1", nativeQuery = true)
	DAOUser findByUserID(long userId);
	
	@Query(value = "SELECT * FROM user WHERE email = ?1", nativeQuery = true)
	DAOUser findByEmail(String email);
}
