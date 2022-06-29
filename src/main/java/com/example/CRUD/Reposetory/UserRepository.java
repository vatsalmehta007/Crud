package com.example.CRUD.Reposetory;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.example.CRUD.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findOneByEmailIgnoreCase(String email);

	@Modifying
	@Transactional
	@Query("DELETE FROM UserEntity m Where m.id = :id")
	void deleteUser(Long id);

}
