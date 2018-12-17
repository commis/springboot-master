package com.commis.dao;

import com.commis.dao.entity.PermissionBean;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionBean, String> {

    /*SELECT
    sys_role.id,
    sys_role.available,
    sys_role.description,
    sys_role.role,
    sys_permission.`name`,
    sys_permission.permission,
    sys_permission.url
    FROM
        (sys_permission, sys_role)
    RIGHT JOIN sys_role_permission ON sys_permission.id = sys_role_permission.permission_id
    AND sys_role_permission.role_id = sys_role.id
    WHERE sys_role.id=#{roleId}*/

    @Query(value = "select * from ", nativeQuery = true)
    List<PermissionBean> findPermissionByRoleId(Integer roleId);
}
