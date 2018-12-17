package com.commis.dao;

import com.commis.dao.entity.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserBean, String> {

    @Query("select t from UserBean t where t.username = ?1")
    UserBean findByUsername(String username);
}
