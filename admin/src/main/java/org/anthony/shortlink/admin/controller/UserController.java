package org.anthony.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.anthony.shortlink.admin.common.convention.result.Result;
import org.anthony.shortlink.admin.common.convention.result.Results;
import org.anthony.shortlink.admin.common.enums.UserErrorCodeEnum;
import org.anthony.shortlink.admin.dto.req.UserLoginReqDTO;
import org.anthony.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.anthony.shortlink.admin.dto.req.UserUpdateReqDTO;
import org.anthony.shortlink.admin.dto.resp.ActualUserRespDTO;
import org.anthony.shortlink.admin.dto.resp.UserLoginRespDTO;
import org.anthony.shortlink.admin.dto.resp.UserRespDTO;
import org.anthony.shortlink.admin.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 查询脱敏用户信息
     * @param username
     * @return
     */
    @GetMapping("/api/short-link/admin/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        return Results.success(userService.getUserByUsername(username));

    }

    /**
     * 查询无脱敏用户信息
     * @param username
     * @return
     */
    @GetMapping("/api/short-link/admin/v1/actual/user/{username}")
    public Result<ActualUserRespDTO> getActualUserByUsername(@PathVariable("username") String username) {

        return Results.success(BeanUtil.toBean(userService.getUserByUsername(username),ActualUserRespDTO.class));

    }

    /**
     * 查询用户名是否已存在
     * @param username
     * @return
     */
    @GetMapping("/api/short-link/admin/v1/user/has-username")
    public Result<Boolean> hasUsername(@RequestParam("username") String username){
        return Results.success(userService.hasUsername(username));
    }

    /**
     * 用户注册
     * @param requestParam
     * @return
     */
    @PostMapping("/api/short-link/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam){
        userService.register(requestParam);
        return Results.success();
    }

    /**
     * 用户更新
     * @param requestParam
     * @return
     */
    @PutMapping("/api/short-link/v1/user")
    public Result<Void> update(@RequestBody UserUpdateReqDTO requestParam){
        userService.update(requestParam);
        return Results.success();
    }

    /**
     * 用户登陆
     * @param requestParam
     * @return
     */
    @PostMapping("/api/short-link/v1/user/login")
    public Result<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO requestParam){
        UserLoginRespDTO result=userService.login(requestParam);
        return Results.success(userService.login(requestParam));
    }

    /**
     * 检查用户是否登陆
     */
    @PostMapping("/api/short-link/v1/user/check-login")
    public Result<Boolean> checkLogin(@RequestParam("username") String username,@RequestParam("token") String token){
        return Results.success(userService.checkLogin(username,token));
    }

    @DeleteMapping("/api/short-link/v1/user/logout")
    public Result<Void> logout(@RequestParam("username") String username,@RequestParam("token") String token){
        userService.logout(username,token);
        return Results.success();
    }
}
