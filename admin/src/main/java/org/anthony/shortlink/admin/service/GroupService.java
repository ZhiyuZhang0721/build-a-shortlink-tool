package org.anthony.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.anthony.shortlink.admin.dao.entity.GroupDO;
import org.anthony.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import org.anthony.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import org.anthony.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;

import java.util.List;

public interface GroupService extends IService<GroupDO> {

    /**
     * 新增短链接分组
     * @param groupName
     */
    void saveGroup(String groupName);

    /**
     * 查询用户短链接分组集合
     * @return
     */
    List<ShortLinkGroupRespDTO> listGroup();

    /**
     * 修改用户名分组
     * @param requestParam
     */
    void updateGroup(ShortLinkGroupUpdateReqDTO requestParam);

    /**
     * 删除短链接
     * @param gid
     */
    void deleteGroup(String gid);

    /**
     * 短链接分组排序
     * @param requestParam
     */
    void sortGroup(List<ShortLinkGroupSortReqDTO> requestParam);
}
