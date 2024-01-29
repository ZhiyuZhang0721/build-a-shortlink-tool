package org.anthony.shortlink.admin.dto.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.anthony.shortlink.admin.common.serialize.PhoneDesensitizationSerializer;

/**
 * 用户返回参数相应
 */
@Data
public class ActualUserRespDTO {

    private Long id;

    private String username;

    private String realName;

    private String phone;

    private String mail;
}
