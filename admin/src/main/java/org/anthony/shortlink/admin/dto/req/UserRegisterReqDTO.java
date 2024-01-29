package org.anthony.shortlink.admin.dto.req;

import lombok.Data;

@Data
public class UserRegisterReqDTO {
    private Long id;

    private String username;

    private String password;

    private String realName;

    private String phone;

    private String mail;
}
