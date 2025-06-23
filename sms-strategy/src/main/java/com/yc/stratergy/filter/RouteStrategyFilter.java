package com.yc.stratergy.filter;

import com.yc.common.constants.RedisKeys;
import com.yc.common.enums.ExceptionEnums;
import com.yc.common.exceptions.StrategyException;
import com.yc.common.model.StandardSubmit;
import com.yc.stratergy.client.cache.CacheClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 路由校驗
 */
@Service("route")
@Slf4j
public class RouteStrategyFilter implements StrategyFilter{
    @Autowired
    CacheClient cacheClient;

    @Override
    public void check(StandardSubmit submit) throws StrategyException {
        //获取用户的所有通道信息
        Set<Map> channelInfos = cacheClient.sget(RedisKeys.CLIENT_CHANNEL + submit.getClientId());
        //根据权重排序
        TreeSet<Map> clientWeightChannels = new TreeSet<Map>(new Comparator<Map>() {
            @Override
            public int compare(Map o1, Map o2) {
                int ow1 = Integer.parseInt(o1.get("clientChannelWeight")+"");
                int ow2 = Integer.parseInt(o2.get("clientChannelWeight")+"");
                return ow2 - ow1;
            }
        });
        clientWeightChannels.addAll(channelInfos);

        Map channel = null;
        Map clientChannel = null;
        boolean flag = false;
        for(Map channelInfo:clientWeightChannels){
            if(Integer.parseInt(channelInfo.get("isAvailable")+"") != 0){
                continue;
            }
            channel = cacheClient.hgetAll(RedisKeys.CLIENT_CHANNEL+channelInfo.get("channelId"));
            if(channelInfo == null || Integer.parseInt(channelInfo.get("isAvailable")+"") != 0 ){
                continue;
            }

            Integer channelType = Integer.parseInt(channelInfo.get("channelType")+"");
            if(channelType != 0 && submit.getOperatorId() != channelType){
                continue;
            }

            //通道转换
            //......

            flag = true;
            clientChannel = channelInfo;
            break;
        }

        if(!flag){
            throw new StrategyException(ExceptionEnums.CHANNEL_NOT_FOUND);
        }

        submit.setChannelId(Long.parseLong(channel.get("channelId")+""));
        submit.setSrcNumber(""+channel.get("channelNumber")+clientChannel.get("clientChannelNumber"));
    }
}
