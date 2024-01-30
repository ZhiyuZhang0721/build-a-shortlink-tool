package org.anthony.shortlink.admin.dto.resp;

import lombok.Data;

@Data
public class ShortLinkGroupRespDTO {
    private String gid;

    private String name;

    private String username;

    /**
     * 分组排序
     */
    private Integer sortOrder;
}
