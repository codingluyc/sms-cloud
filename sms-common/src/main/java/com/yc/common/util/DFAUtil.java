package com.yc.common.util;


import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
public class DFAUtil {
    //敏感词树
    private static Map<String,Object> defaultMap = new HashMap<>();

    private static final String IS_END = "isEnd";

    /**
     * 创建敏感词树
     * @param dirtyWords
     */
    public static void create(Set<String> dirtyWords){
        Map<String,Object> nowMap;
        for(String dirtyWord : dirtyWords){
            nowMap = defaultMap;
            for(int i = 0; i < dirtyWord.length(); i++){
                //遍历敏感词中的每个字
                String key = String.valueOf(dirtyWord.charAt(i));
                //找到敏感字的map
                Map<String, Object>  tempMap = (Map<String, Object>) nowMap.get(key);
                if(tempMap == null){
                    tempMap = new HashMap<>();
                    //如果敏感字符不存在，则创建
                    nowMap.put(key,tempMap);
                }
                //操作敏感字的map
                nowMap = tempMap;
                //有is_end，且值为1
                if(nowMap.containsKey(IS_END) && nowMap.get(IS_END).equals("1")){
                    continue;
                }


                //当前字已经是敏感词的最后一个
                if(i== (dirtyWord.length()-1)){
                    nowMap.put(IS_END,"1");
                }else{
                    //之前没有is_end或者之前的值为0
                    nowMap.putIfAbsent(IS_END,"0");
                }
            }
        }
    }

    /**
     * 获取敏感词
     * @param text
     * @return
     */
    public static Set<String> getDirtyWords(String text){

        Set<String> resultSet = new HashSet<>();

        for(int i=0;i<text.length();){
            Map<String,Object> nowMap = defaultMap;
            int nextLength = 0;
            int dirtyWordLength = 0;
            for(int j=i;j<text.length();j++){
                String word = String.valueOf(text.charAt(j));
                nowMap = (Map<String, Object>) nowMap.get(word);
                if(nowMap != null){
                    dirtyWordLength++;
                    if("1".equals(nowMap.get(IS_END))){
                        nextLength = dirtyWordLength;
                        break;
                    }
                }else{
                    break;
                }
            }

            if(nextLength > 0){
                resultSet.add(text.substring(i, i + nextLength));
                i = i+nextLength;
            }else{
                i++;
            }

        }

        return resultSet;
    }

    /**
     * 是否存在敏感词
     * @param text
     * @return
     */
    public static boolean isContainDirtyWord(String text){
        Map<String,Object> nowMap = defaultMap;
        for(int i=0;i<text.length();i++){
            String key = String.valueOf(text.charAt(i));
            if(nowMap.containsKey(key)){
                nowMap = (Map<String, Object>) nowMap.get(key);
                if("1".equals(nowMap.get(IS_END))){
                    return true;
                }else{
                    continue;
                }
            }else{
                nowMap = defaultMap;
                continue;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Set<String> dirtyWords = new HashSet<>();

        dirtyWords.add("三胖");
        dirtyWords.add("三胖子");
        dirtyWords.add("三炮");
        dirtyWords.add("山炮");
        create(dirtyWords);
        log.info("dirtyWords:{}",defaultMap);

        String text = "三胖子是个大山炮";
        if(isContainDirtyWord(text)){
            log.info("存在敏感词");
            Set<String> words = getDirtyWords(text);
            log.info("敏感词：{}",words);
        }else{
            log.info("不存在敏感词");
        }

    }
}
