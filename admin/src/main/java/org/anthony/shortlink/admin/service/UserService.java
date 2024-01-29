package org.anthony.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.anthony.shortlink.admin.dao.entity.UserDO;
import org.anthony.shortlink.admin.dto.req.UserLoginReqDTO;
import org.anthony.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.anthony.shortlink.admin.dto.req.UserUpdateReqDTO;
import org.anthony.shortlink.admin.dto.resp.UserLoginRespDTO;
import org.anthony.shortlink.admin.dto.resp.UserRespDTO;

public interface UserService extends IService<UserDO> {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    UserRespDTO getUserByUsername(String username);

    /**
     * 查询用户名是否存在
     * @param username
     * @return 存在返回True
     */
    Boolean hasUsername(String username);


    /**
     * 注册用户
     * @param requestParam
     */
    void register(UserRegisterReqDTO requestParam);

    /**
     * 根据用户名修改用户
     * @param requestParam
     */
    void update(UserUpdateReqDTO requestParam);

    /**
     * 用户登陆
     * @param requestParam
     * @return 用户登陆返回参数
     */
    UserLoginRespDTO login(UserLoginReqDTO requestParam);

    /**
     * 检查用户是否登陆
     * @param token
     * @return
     */
    Boolean checkLogin(String username,String token);

    void logout(String username, String token);
}
