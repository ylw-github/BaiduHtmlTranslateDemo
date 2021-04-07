package com.ylw.translate.api;

import cn.hutool.json.JSONUtil;
import com.ylw.translate.entity.TransResult;
import com.ylw.translate.util.HttpGet;
import com.ylw.translate.util.MD5;

import java.util.HashMap;
import java.util.Map;

/**
 * 百度通用翻译API
 *
 * @author : YangLinWei
 * @createTime: 2021/4/7 9:59 上午
 */
public class TransApi {
    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    private String appid;
    private String securityKey;

    public TransApi(String appid, String securityKey) {
        this.appid = appid;
        this.securityKey = securityKey;
    }

    public String getTransResult(String query, String from, String to) {
        Map<String, String> params = buildParams(query, from, to);
        return HttpGet.get(TRANS_API_HOST, params);
    }

    private Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);

        params.put("appid", appid);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        String src = appid + query + salt + securityKey; // 加密前的原文
        params.put("sign", MD5.md5(src));

        return params;
    }

    public String getZh(String query) {
        TransResult transResult = JSONUtil.toBean(getTransResult(query, "auto", "zh"), TransResult.class);
        if (transResult != null && transResult.getTrans_result() != null && transResult.getTrans_result().size() > 0) {
            return transResult.getTrans_result().get(0).getDst();
        }
        return "";

    }
}
