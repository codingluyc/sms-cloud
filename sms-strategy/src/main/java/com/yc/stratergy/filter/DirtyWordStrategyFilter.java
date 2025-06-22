package com.yc.stratergy.filter;

import com.yc.common.constants.RedisKeys;
import com.yc.common.enums.ExceptionEnums;
import com.yc.common.exceptions.StrategyException;
import com.yc.common.model.StandardSubmit;
import com.yc.common.util.DFAUtil;
import com.yc.stratergy.client.cache.CacheClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import javax.annotation.PostConstruct;
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

    @PostConstruct
    public void init(){
        Set<String> dirtyWords = cacheClient.smembers(RedisKeys.MOBILE_DIRTYWORD);
        DFAUtil.create(dirtyWords);
    }
    @Override
    public void check(StandardSubmit submit) throws IOException, StrategyException {
        String text = submit.getText();

//        ikRedis(text);

        dfa(text);



    }

    /**
     * 通过ik分词器和redis set做交集操作判断是否存在敏感词
     * @param text
     */
    private void ikRedis(String text) throws IOException, StrategyException {
        //分词
        Set<String> contents = new HashSet<>();
        IKSegmenter ikSegmenter = new IKSegmenter(new StringReader(text),false);
        Lexeme lexeme = null;
        while(true){
            lexeme = ikSegmenter.next();
            if(lexeme == null){
                break;
            }
            contents.add(lexeme.getLexemeText());
        }
        //redis 交集操作
        Set<Object> result = cacheClient.sinter(RedisKeys.MOBILE_DIRTYWORD, UUID.randomUUID().toString(),contents);
        if(null != result && result.size() > 0){
            throw new StrategyException(ExceptionEnums.DIRTY_WORD);
        }
    }


    private void dfa(String text) throws StrategyException {
        if(DFAUtil.isContainDirtyWord(text)){
            Set<String> dirtyWords = DFAUtil.getDirtyWords(text);;
            log.info("dirtyWords:{}",dirtyWords);
            throw new StrategyException(ExceptionEnums.DIRTY_WORD);
        }
    }

}
