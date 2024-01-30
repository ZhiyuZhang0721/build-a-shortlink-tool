package org.anthony.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.anthony.shortlink.admin.dao.entity.GroupDO;

public interface GroupService extends IService<GroupDO> {

    /**
     * 新增短链接分组
     * @param groupName
     */
    void saveGroup(String groupName);
}
