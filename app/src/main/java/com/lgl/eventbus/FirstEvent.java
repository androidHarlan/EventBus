package com.lgl.eventbus;

/**
 * Created by a1 on 2017/7/10.
 */
public class FirstEvent {

    private String mMsg;//消息内容
    private String tag; //消息类型

    public FirstEvent(String mMsg, String tag) {
        super();
        this.mMsg = mMsg;
        this.tag = tag;
    }
    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public FirstEvent(String msg) {
        mMsg = msg;
    }
    public String getMsg(){
        return mMsg;
    }
}
