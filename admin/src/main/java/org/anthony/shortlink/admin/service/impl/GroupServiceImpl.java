package org.anthony.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import groovy.util.logging.Slf4j;
import org.anthony.shortlink.admin.dao.entity.GroupDO;
import org.anthony.shortlink.admin.dao.mapper.GroupMapper;
import org.anthony.shortlink.admin.service.GroupService;
import org.anthony.shortlink.admin.toolkit.RandomGenerator;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {
    @Override
    public void saveGroup(String groupName) {
        String gid;
        while(true){
            gid=RandomGenerator.generateRandom();
            if(hasGid(gid)){
                break;
            }
        }
        GroupDO groupDO= GroupDO.builder()
                .gid(gid)
                .name(groupName)
                .build();
        baseMapper.insert(groupDO);
    }

    private boolean hasGid(String gid){
        LambdaQueryWrapper<GroupDO> queryWrapper= Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid,gid)
                .eq(GroupDO::getUsername,null);
        GroupDO hasGroupFlag=baseMapper.selectOne(queryWrapper);
        return hasGroupFlag==null;
    }
}
