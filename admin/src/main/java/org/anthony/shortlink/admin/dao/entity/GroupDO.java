package org.anthony.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.anthony.shortlink.admin.common.database.BaseDO;

/**
 * 短链接分组实体
 */
@Data
@TableName("t_group")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDO extends BaseDO {

    private Long id;

    private String gid;

    private String name;

    private String username;

    /**
     * 分组排序
     */
    private Integer sortOrder;
}
