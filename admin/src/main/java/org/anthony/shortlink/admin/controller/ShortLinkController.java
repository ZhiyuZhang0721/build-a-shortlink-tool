package org.anthony.shortlink.admin.controller;

import org.anthony.shortlink.admin.common.convention.result.Result;
import org.anthony.shortlink.admin.remote.ShortLinkRemoteService;
import org.anthony.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import org.anthony.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import org.anthony.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import org.anthony.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短链接后管控制层
 */
@RestController
public class ShortLinkController {
    ShortLinkRemoteService shortLinkRemoteService=new ShortLinkRemoteService() {
    };

    /**
     * 创建短链接
     * @return
     */
    @PostMapping("api/short-link/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam){
        return shortLinkRemoteService.createShortLink(requestParam);
    }

    /**
     * 分页查询短链接
     * @param requestParam
     * @return
     */
    @GetMapping("api/short-link/v1/page")
    public Result<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam){
        return shortLinkRemoteService.pageShortLink(requestParam);
    }

}
