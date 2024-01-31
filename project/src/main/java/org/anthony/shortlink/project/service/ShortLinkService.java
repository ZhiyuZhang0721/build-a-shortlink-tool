package org.anthony.shortlink.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.anthony.shortlink.project.dao.entity.ShortLinkDO;
import org.anthony.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.anthony.shortlink.project.dto.resp.ShortLinkCreateRespDTO;

public interface ShortLinkService extends IService<ShortLinkDO> {
    /**
     * 创建短链接
     * @param requestParam 短链接请求信息
     * @return 短链接返回参数
     */
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);
}
