package com.commis.service.user;

import com.commis.dao.entity.UserBean;
import java.util.List;

public interface UserService {

    List<UserBean> list();

    UserBean findByUsername(String username);
}
