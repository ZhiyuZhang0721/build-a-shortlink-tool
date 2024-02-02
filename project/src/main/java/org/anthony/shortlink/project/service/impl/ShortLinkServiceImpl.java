package org.anthony.shortlink.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anthony.shortlink.project.common.exception.ServiceException;
import org.anthony.shortlink.project.dao.entity.ShortLinkDO;
import org.anthony.shortlink.project.dao.mapper.ShortLinkMapper;
import org.anthony.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.anthony.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.anthony.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import org.anthony.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import org.anthony.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import org.anthony.shortlink.project.service.ShortLinkService;
import org.anthony.shortlink.project.toolkit.HashUtil;
import org.redisson.api.RBloomFilter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService{

    private final RBloomFilter<String> shortUriCreateCachePenetrationBloomFilter;


    @Override
    public ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam) {
        String shortLinkSuffix=generateSuffix(requestParam);
        String fullShortUrl=requestParam.getDomain()+"/"+shortLinkSuffix;
        ShortLinkDO.builder()
                .domain(requestParam.getDomain())
                .originUrl(requestParam.getOriginUrl())
                .gid(requestParam.getGid())
                .createdType(requestParam.getCreatedType())
                .validDateType(requestParam.getValidDateType())
                .validDate(requestParam.getValidDate())
                .describe(requestParam.getDescribe())
                .shortUri(shortLinkSuffix)
                .fullShortUrl(fullShortUrl)
                .enableStatus(0)
                .build();
        ShortLinkDO shortLinkDO= BeanUtil.toBean(requestParam, ShortLinkDO.class);
        shortLinkDO.setShortUri(shortLinkSuffix);
        shortLinkDO.setFullShortUrl(requestParam.getDomain()+"/"+shortLinkSuffix);
        shortLinkDO.setEnableStatus(0);
        try {
            baseMapper.insert(shortLinkDO);
        }catch (DuplicateKeyException ex){
            LambdaQueryWrapper<ShortLinkDO> queryWrapper=Wrappers.lambdaQuery(ShortLinkDO.class)
                            .eq(ShortLinkDO::getFullShortUrl,fullShortUrl);
            ShortLinkDO hasShortLinkDo=baseMapper.selectOne(queryWrapper);
            if(hasShortLinkDo!=null){
                log.warn("短链接:{}重复入库",fullShortUrl);
                throw new ServiceException("短链接生成重复");
            }
        }
        shortUriCreateCachePenetrationBloomFilter.add(shortLinkSuffix);
        return ShortLinkCreateRespDTO.builder()
                .fullShortUrl(shortLinkDO.getFullShortUrl())
                .originUrl(requestParam.getOriginUrl())
                .gid(requestParam.getGid())
                .build();
    }

    @Override
    public ShortLinkPageRespDTO pageShortLink(ShortLinkPageReqDTO requestParam) {
        LambdaQueryWrapper<ShortLinkDO> queryWrapper=Wrappers.lambdaQuery(ShortLinkDO.class)
                .eq(ShortLinkDO::getGid,requestParam.getGid())
                .eq(ShortLinkDO::getEnableStatus,0)
                .eq(ShortLinkDO::getDelFlag,0)
                .orderByDesc(ShortLinkDO::getCreateTime);
        IPage<ShortLinkDO> resultPage =baseMapper.selectPage(requestParam,queryWrapper);
        return (ShortLinkPageRespDTO) resultPage.convert(each -> BeanUtil.toBean(each,ShortLinkPageRespDTO.class));

    }

    @Override
    public List<ShortLinkGroupCountQueryRespDTO> listGroupShortLinkCount(List<String> requestParam) {
        QueryWrapper<ShortLinkDO> queryWrapper = Wrappers.query(new ShortLinkDO())
                .select("gid as gid, count(*) as shortLinkCount")
                .in("gid", requestParam)
                .eq("enable_status", 0)
                .eq("del_flag", 0)
                .eq("del_time", 0L)
                .groupBy("gid");
        List<Map<String, Object>> shortLinkDOList = baseMapper.selectMaps(queryWrapper);
        return BeanUtil.copyToList(shortLinkDOList, ShortLinkGroupCountQueryRespDTO.class);
    }

    /**
     * 十进制转62进制
     * @param requestParam
     * @return
     */
    private String generateSuffix(ShortLinkCreateReqDTO requestParam) {

        int customGenerateCount=0;
        String shortUri;
        while (true){
            if(customGenerateCount>10){
                throw new ServiceException("短链接频繁生成，请稍后再试");
            }
            String originalUrl= requestParam.getOriginUrl();
            originalUrl+=System.currentTimeMillis();
            shortUri=HashUtil.hashToBase62(originalUrl);
            if(!shortUriCreateCachePenetrationBloomFilter.contains(requestParam.getDomain()+"/"+shortUri)){
                break;
            }
            customGenerateCount++;
        }

        return shortUri;
    }
}
