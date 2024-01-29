package org.anthony.shortlink.admin.dto.req;

import lombok.Data;

/**
 * 用户登陆请求参数
 */
@Data
public class UserLoginReqDTO {

    private String username;

    private String password;

}
