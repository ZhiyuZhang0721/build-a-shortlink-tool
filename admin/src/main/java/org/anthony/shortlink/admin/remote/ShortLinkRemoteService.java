package org.anthony.shortlink.admin.remote;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.anthony.shortlink.admin.common.convention.result.Result;
import org.anthony.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import org.anthony.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import org.anthony.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import org.anthony.shortlink.admin.remote.dto.resp.ShortLinkGroupCountQueryRespDTO;
import org.anthony.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 短链接中台远程调用服务
 */
public interface ShortLinkRemoteService {

    default Result<ShortLinkCreateRespDTO> createShortLink(ShortLinkCreateReqDTO requestParam){
        String resultBodyStr=HttpUtil.post("http://127.0.0.1:8001/api/short-link/v1/create",JSON.toJSONString(requestParam));
        return JSON.parseObject(resultBodyStr, new TypeReference<Result<ShortLinkCreateRespDTO>>() {
        });
    }
    default Result<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam){
        Map<String,Object> requestMap=new HashMap<>();
        requestMap.put("gid",requestParam.getGid());
        requestMap.put("current",requestParam.getCurrent());
        requestMap.put("size",requestParam.getSize());
        String resultPageStr=HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/page",requestMap);
        return JSON.parseObject(resultPageStr, new TypeReference<Result<ShortLinkPageRespDTO>>() {
        });
    }

    default List<ShortLinkGroupCountQueryRespDTO> listGroupShortLinkCount(List<String> requestParam) {
        Map<String,Object> requestMap=new HashMap<>();
        requestMap.put("requestParam",requestParam);
        String resultPageStr=HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/count",requestMap);
        return JSON.parseObject(resultPageStr, new TypeReference<>() {
        });
    }
}
