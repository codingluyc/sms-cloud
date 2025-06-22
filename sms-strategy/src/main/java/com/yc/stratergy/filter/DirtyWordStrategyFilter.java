package com.yc.stratergy.filter;

import com.yc.common.constants.RedisKeys;
import com.yc.common.enums.ExceptionEnums;
import com.yc.common.exceptions.StrategyException;
import com.yc.common.model.StandardSubmit;
import com.yc.stratergy.client.cache.CacheClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * 敏感詞校驗
 */
@Service("dirtyword")
@Slf4j
public class DirtyWordStrategyFilter implements StrategyFilter{
    @Autowired
    CacheClient cacheClient;
    @Override
    public void check(StandardSubmit submit) throws IOException, StrategyException {
        String text = submit.getText();
        //分词
        Set<String> contents = new HashSet<>();
        IKSegmenter ikSegmenter = new IKSegmenter(new StringReader(text),true);
        Lexeme lexeme = null;
        while((lexeme = ikSegmenter.next()) != null){
            contents.add(lexeme.getLexemeText());
        }
        //redis 交集操作
        Set<Object> result = cacheClient.sinter(RedisKeys.MOBILE_DIRTYWORD, UUID.randomUUID().toString(),contents);
        if(null != result && result.size() > 0){
            throw new StrategyException(ExceptionEnums.DIRTY_WORD);
        }



    }
}
