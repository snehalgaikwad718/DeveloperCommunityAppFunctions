package com.cg.dca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.dca.entity.User;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository <User , Long>{

	Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username);
}
