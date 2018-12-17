package com.commis.dao;

import com.commis.dao.entity.RoleBean;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleBean, String> {

    @Query(value = "select u.* from t_user u "
        + "right join t_user_role ur on u.id=ur.user_id "
        + "left join t_role r on ur.role_id=r.id "
        + "where u.username=?1", nativeQuery = true)
    List<RoleBean> findRoleByUsername(String username);
}
