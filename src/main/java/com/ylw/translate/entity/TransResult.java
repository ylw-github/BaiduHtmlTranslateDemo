package com.ylw.translate.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 服务器响应实体
 *
 * @author : YangLinWei
 * @createTime: 2021/4/7 10:11 上午
 */
public class TransResult implements Serializable {

    private static final long serialVersionUID = -2465501972668008701L;
    /**
     * from : en
     * to : zh
     * trans_result : [{"src":"apple banana","dst":"苹果香蕉"}]
     */

    private String from;
    private String to;
    private List<TransResultBean> trans_result;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<TransResultBean> getTrans_result() {
        return trans_result;
    }

    public void setTrans_result(List<TransResultBean> trans_result) {
        this.trans_result = trans_result;
    }

    public static class TransResultBean {
        /**
         * src : apple banana
         * dst : 苹果香蕉
         */

        private String src;
        private String dst;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getDst() {
            return dst;
        }

        public void setDst(String dst) {
            this.dst = dst;
        }
    }
}
