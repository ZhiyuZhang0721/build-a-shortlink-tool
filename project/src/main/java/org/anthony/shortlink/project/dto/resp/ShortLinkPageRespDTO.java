package org.anthony.shortlink.project.dto.resp;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Date validDate;

    /**
     * 描述
     */
    @TableField("`describe`")
    private String describe;

    private String favicon;

}
