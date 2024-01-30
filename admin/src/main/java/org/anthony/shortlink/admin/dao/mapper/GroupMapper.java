package org.anthony.shortlink.admin.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.anthony.shortlink.admin.dao.entity.GroupDO;

/**
 * 短链接分组持久层
 */
public interface GroupMapper extends BaseMapper<GroupDO> {

    void saveGroup();
}
