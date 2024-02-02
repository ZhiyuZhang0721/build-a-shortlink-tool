package org.anthony.shortlink.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.anthony.shortlink.project.dao.entity.ShortLinkDO;
import org.anthony.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.anthony.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.anthony.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import org.anthony.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import org.anthony.shortlink.project.dto.resp.ShortLinkPageRespDTO;

import java.util.List;

public interface ShortLinkService extends IService<ShortLinkDO> {
    /**
     * 创建短链接
     * @param requestParam 短链接请求信息
     * @return 短链接返回参数
     */
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);

    ShortLinkPageRespDTO pageShortLink(ShortLinkPageReqDTO requestParam);

    /**
     *  查询短链接分组内数量
     * @param requestParam
     * @return
     */
    List<ShortLinkGroupCountQueryRespDTO> listGroupShortLinkCount(List<String> requestParam);
}
