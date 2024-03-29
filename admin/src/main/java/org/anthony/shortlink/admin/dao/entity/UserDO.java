package org.anthony.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.anthony.shortlink.admin.common.database.BaseDO;

import java.util.Date;

@Data
@TableName("t_user")
public class UserDO extends BaseDO {

    private Long id;

    private String username;

    private String password;

    private String realName;

    private String phone;

    private String mail;

    private Long deletionTime;

}
