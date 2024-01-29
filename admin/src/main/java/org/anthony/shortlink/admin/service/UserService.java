package org.anthony.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.anthony.shortlink.admin.dao.entity.UserDO;
import org.anthony.shortlink.admin.dto.resp.UserRespDTO;

public interface UserService extends IService<UserDO> {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    UserRespDTO getUserByUsername(String username);
}
