package org.anthony.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.anthony.shortlink.admin.common.enums.UserErrorCodeEnum;
import org.anthony.shortlink.admin.common.exception.ClientException;
import org.anthony.shortlink.admin.dao.entity.UserDO;
import org.anthony.shortlink.admin.dao.mapper.UserMapper;
import org.anthony.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.anthony.shortlink.admin.dto.resp.UserRespDTO;
import org.anthony.shortlink.admin.service.UserService;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import static org.anthony.shortlink.admin.common.constant.RedisCacheConstant.LOCK_USER_REGISTER_KEY;
import static org.anthony.shortlink.admin.common.enums.UserErrorCodeEnum.USER_NAME_EXIST;
import static org.anthony.shortlink.admin.common.enums.UserErrorCodeEnum.USER_SAVE_ERROR;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;
    private final RedissonClient redissonClient;
    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if(userDO==null){
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
        }
        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO,result);
        return result;
    }

    @Override
    public Boolean hasUsername(String username) {
        return !userRegisterCachePenetrationBloomFilter.contains(username);
    }

    @Override
    public void register(UserRegisterReqDTO requestParam) {
        if(!hasUsername(requestParam.getUsername())){
            throw new ClientException(USER_NAME_EXIST);
        }
        RLock lock=redissonClient.getLock(LOCK_USER_REGISTER_KEY+requestParam.getUsername());
        try {
            if(lock.tryLock()){
                int inserted=baseMapper.insert(BeanUtil.toBean(requestParam,UserDO.class));
                if(inserted<1){
                    throw new ClientException(USER_SAVE_ERROR);
                }
                userRegisterCachePenetrationBloomFilter.add(requestParam.getUsername());
                return;
            }
            throw new ClientException(USER_NAME_EXIST);
        }finally {
            lock.unlock();
        }

    }
}
