package com.lgl.eventbus;

/**
 * 实体类
 * Created by lgl on 2016/5/9.
 */
public class UserEvent {

    /**
     * 这里你传递什么类型你就写什么类型
     */

    //文本
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
