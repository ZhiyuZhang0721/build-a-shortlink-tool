package org.anthony.shortlink.project.dto.resp;

import lombok.Data;

import java.util.Date;

/**
 * 短链接创建响应对象
 */
@Data
public class ShortLinkCreateRespDTO {

    /**
     * 分组信息
     */
    private String gid;


    /**
     * 原始链接
     */

    private String originUrl;


    /**
     * 短链接
     */

    private String fullShortUrl;

}
