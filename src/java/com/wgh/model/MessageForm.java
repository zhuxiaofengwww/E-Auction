package com.wgh.model;

/**
 *
 * @author administrator
 */
public class MessageForm {

    private String from = ""; //发言人
    private String face = ""; //表情
    private String to = ""; //接收者
    private String color = ""; //字体颜色
    private String content = ""; //发言内容
    private String isPrivate = "";			//是否为悄悄话
    private String sendTime = ""; //发言时间

    public String getFrom() {
        return from;
    }


    public void setFrom(String from) {
        this.from = from;
    }

    public String getFace() {
        return face;
    }


    public void setFace(String face) {
        this.face = face;
    }


    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(String isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
