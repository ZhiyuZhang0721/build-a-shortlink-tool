package org.anthony.shortlink.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.anthony.shortlink.project.dao.entity.ShortLinkDO;
import org.anthony.shortlink.project.dao.mapper.ShortLinkMapper;
import org.anthony.shortlink.project.service.ShortLinkService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService{
}
