package org.anthony.shortlink.project.controller;

import org.anthony.shortlink.project.common.convention.result.Result;
import org.anthony.shortlink.project.common.convention.result.Results;
import org.anthony.shortlink.project.dto.req.ShortLinkCreateRepDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortLinkController {

    /**
     * 创建短链接
     * @return
     */
    @PostMapping("api/short-link/v1/create")
    public Result createShortLink(@RequestBody ShortLinkCreateRepDTO requestParam){
        return Results.success();
    }
}
