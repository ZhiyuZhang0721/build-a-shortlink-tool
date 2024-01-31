package org.anthony.shortlink.project.controller;

import lombok.RequiredArgsConstructor;
import org.anthony.shortlink.project.common.convention.result.Result;
import org.anthony.shortlink.project.common.convention.result.Results;
import org.anthony.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.anthony.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import org.anthony.shortlink.project.service.ShortLinkService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
