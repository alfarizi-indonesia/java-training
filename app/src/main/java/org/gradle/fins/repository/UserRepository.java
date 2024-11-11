package org.gradle.fins.repository;

import org.gradle.fins.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {
    @Query(value = "SELECT * FROM TCB_FINS_USER WHERE USER_NAME LIKE %:userName%", nativeQuery = true)
    List<UserModel> findLikeUserName(@Param("userName") String userName);
}