package com.sen.api.functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式功能   __regex(regex,str,index)   从Str中匹配符合该正则的内容
 */
public class RegexFunction implements Function {
    @Override
    public String execute(String[] args) {
        // 按指定模式在字符串查找
        String regex = args[0];
        String str;
        int length=args.length;
        if (length==3){
            str = args[1];
        }else{
            str=args[1];
            for(int i=2;i<args.length-1;i++){
                str+=","+args[i];
            }
        }
        Integer index=Integer.valueOf(args[args.length-1]);
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(regex);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(str);
        if (m.find()){
            return m.group(index);
        }
        return null;
    }

    @Override
    public String getReferenceKey() {
        return "regex";
    }

    public static void main(String[] args) {
        Pattern r = Pattern.compile("([^;]*)=([^;]*)");
        Matcher m=r.matcher("$.code=100000;$.success=true;$.data!null");
        while(m.find()){

            System.out.println(m.group(1));
            System.out.println(m.group(2));
        }
    }
}
