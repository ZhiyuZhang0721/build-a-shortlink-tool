package org.anthony.shortlink.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.anthony.shortlink.project.dao.entity.ShortLinkDO;
import org.anthony.shortlink.project.dao.mapper.ShortLinkMapper;
import org.anthony.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.anthony.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import org.anthony.shortlink.project.service.ShortLinkService;
import org.anthony.shortlink.project.toolkit.HashUtil;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService{
    @Override
    public ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam) {
        String shortLinkSuffix=generateSuffix(requestParam);
        ShortLinkDO shortLinkDO= BeanUtil.toBean(requestParam, ShortLinkDO.class);
        shortLinkDO.setFullShortUrl(requestParam.getDomain()+"/"+shortLinkSuffix);
        baseMapper.insert(shortLinkDO);
        return ShortLinkCreateRespDTO.builder()
                .fullShortUrl(shortLinkDO.getFullShortUrl())
                .originUrl(requestParam.getOriginUrl())
                .gid(requestParam.getGid())
                .build();
    }

    private String generateSuffix(ShortLinkCreateReqDTO requestParam) {
        String originalUrl= requestParam.getOriginUrl();
        return HashUtil.hashToBase62(originalUrl);
    }
}
