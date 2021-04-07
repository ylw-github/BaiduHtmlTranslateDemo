package com.ylw.translate.constant;

/**
 * 常量定义
 *
 * @author : YangLinWei
 * @createTime: 2021/4/7 2:32 下午
 */
public class Const {

    // APP ID 具体请在 { http://api.fanyi.baidu.com/api/trans/product/desktop }上申请查看
    public static final String APPID = "appid";

    // 密钥 具体请在 { http://api.fanyi.baidu.com/api/trans/product/desktop }上申请查看
    public static final String SECRET_KEY = "secret key";

    // 需要翻译的资源文件目录
    public static final String SRC_HTML_DIR = "origin_html";

    // 翻译后输出的目录
    public static final String DEST_OUPUT_DIR = "/Users/xxx/Desktop/output";

    // 正则定义
    public static final String[] REGEXS = new String[]{
            "<p>(.*?)<",
            "</code>(.*?)<",
            "<h2>(.*?)</h2>",
            "</a>(.*?)</li>",
            "<a.*?>(.*?)</a>",
            "<li>(.*?)<",
            "</a>(.*?)<",
    };
}
