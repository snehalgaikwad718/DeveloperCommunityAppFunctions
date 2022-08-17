package com.cg.dca.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.dca.entity.ERole;
import com.cg.dca.entity.Role;
@Repository
public interface IRoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByName(ERole name);
}
