package org.anthony.shortlink.project.controller;

import lombok.RequiredArgsConstructor;
import org.anthony.shortlink.project.common.convention.result.Result;
import org.anthony.shortlink.project.common.convention.result.Results;
import org.anthony.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.anthony.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.anthony.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import org.anthony.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import org.anthony.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import org.anthony.shortlink.project.service.ShortLinkService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShortLinkController {

    private ShortLinkService shortLinkService;
    /**
     * 创建短链接
     * @return
     */
    @PostMapping("api/short-link/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam){
        return Results.success(shortLinkService.createShortLink(requestParam));
    }

    /**
     * 分页查询短链接
     * @param requestParam
     * @return
     */
    @GetMapping("api/short-link/v1/page")
    public Result<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam){
        return Results.success(shortLinkService.pageShortLink(requestParam));
    }

    /**
     * 查询短链接分组内数量
     * @return
     */
    @GetMapping("api/short-link/v1/page")
    public Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount(@RequestParam("requestParam") List<String> requestParam){
        return Results.success(shortLinkService.listGroupShortLinkCount(requestParam));
    }
}
