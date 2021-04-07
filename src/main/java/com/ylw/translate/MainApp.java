package com.ylw.translate;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.ylw.translate.api.TransApi;
import com.ylw.translate.constant.Const;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 百度翻译器
 *
 * @author : YangLinWei
 * @createTime: 2021/4/7 2:32 下午
 */
public class MainApp {

    public static void main(String[] args) throws InterruptedException {


        String originDir = MainApp.class.getClassLoader().getResource(Const.SRC_HTML_DIR).getFile();
        File file = new File(originDir);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (File fileItem : files) {
                    String content = FileUtil.readUtf8String(fileItem);
                    String translateContent = translate(content);
                    FileUtil.writeString(translateContent, String.format("%s/%s", Const.DEST_OUPUT_DIR, fileItem.getName()), "utf-8");

                }
            }
        }
    }

    private static String translate(String content) throws InterruptedException {
        //System.out.println("翻译前---->" + content);
        TransApi api = new TransApi(Const.APPID, Const.SECRET_KEY);
        List<String> replaceStrList = new ArrayList<>();
        for (String regex : Const.REGEXS) {
            List<String> scrapConents = ReUtil.findAll(regex, content, 1);
            for (String scrapText : scrapConents) {
                if (!replaceStrList.contains(scrapText)) {
                    replaceStrList.add(scrapText);
                }
            }
        }
        if (replaceStrList.size() > 0) {
            for (String srcTxt : replaceStrList) {
                srcTxt = srcTxt.trim();
                srcTxt = srcTxt.replace("-", "");
                if (StrUtil.isNotBlank(srcTxt)) {
                    String zhTxt = srcTxt;
                    if ("or".equals(srcTxt)) { // 特殊翻译处理
                        content = content.replace("</code>or", "</code>或者");
                        content = content.replace("</code> or", "</code>或者");
                    } else if ("See".equals(srcTxt)) { // 特殊翻译处理
                        content = content.replace("<h2>See", "<h2>请参阅");
                    } else {
                        zhTxt = api.getZh(srcTxt);
                        while (zhTxt.isEmpty()) {
                            //Thread.sleep(1 * 1000);
                            zhTxt = api.getZh(srcTxt);
                        }
                    }
                    content = content.replace(srcTxt, zhTxt);

                }

            }
        }
        System.out.println("翻译后---->" + content);
        return content;
    }
}
