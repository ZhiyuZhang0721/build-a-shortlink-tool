package org.anthony.shortlink.admin.controller;

import lombok.RequiredArgsConstructor;
import org.anthony.shortlink.admin.common.convention.result.Result;
import org.anthony.shortlink.admin.common.convention.result.Results;
import org.anthony.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import org.anthony.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import org.anthony.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import org.anthony.shortlink.admin.service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    /**
     * 新增短链接分组
     * @param requestParam
     * @return
     */
    @PostMapping("/api/short-link/v1/group")
    public Result<Void> save(@RequestBody ShortLinkGroupSaveReqDTO requestParam){
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }

    @GetMapping("/api/short-link/v1/group")
    public Result<List<ShortLinkGroupRespDTO>> listGroup(){
        return Results.success(groupService.listGroup());
    }

    /**
     * 修改短链接分组名
     * @param requestParam
     * @return
     */
    @PutMapping("/api/short-link/v1/group")
    public Result<Void> updateGroup(@RequestBody ShortLinkGroupUpdateReqDTO requestParam){
        groupService.updateGroup(requestParam);
        return Results.success();
    }
}
