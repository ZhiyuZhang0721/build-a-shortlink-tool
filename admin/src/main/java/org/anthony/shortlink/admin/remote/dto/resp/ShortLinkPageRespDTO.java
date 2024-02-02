package org.anthony.shortlink.admin.remote.dto.resp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 短链接分页响应对象
 */
@Data
public class ShortLinkPageRespDTO {
    /**
     * ID
     */

    private Long id;

    /**
     * 域名
     */

    private String domain;

    /**
     * 短链接
     */

    private String shortUri;

    /**
     * 完整短链接
     */

    private String fullShortUrl;

    /**
     * 原始链接
     */

    private String originUrl;

    /**
     * 分组标识
     */

    private String gid;

    /**
     * 有效期类型 0：永久有效 1：用户自定义
     */

    private Integer validDateType;

    /**
     * 有效期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date validDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    /**
     * 描述
     */
    @TableField("`describe`")
    private String describe;

    private String favicon;

}
