package com.commis.dao;

import com.commis.dao.entity.PermissionBean;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionBean, String> {

    @Query(value = "select r.id,r.name,r.role_level,r.description "
        + "from t_permission p, t_role r "
        + "right join t_role_permission rp on p.id=rp.permission_id and rp.role_id=r.id "
        + "where r.id=?1", nativeQuery = true)
    List<PermissionBean> findPermissionByRoleId(Integer roleId);
}
