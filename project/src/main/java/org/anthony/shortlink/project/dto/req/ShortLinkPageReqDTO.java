package org.anthony.shortlink.project.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.anthony.shortlink.project.dao.entity.ShortLinkDO;

import java.util.Date;

/**
 * 短链接分页请求对象
 */
@Data
public class ShortLinkPageReqDTO extends Page<ShortLinkDO> {



    /**
     * 分组标识
     */

    private String gid;


}
