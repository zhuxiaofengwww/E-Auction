package com.wgh.model;

/**
 *
 * @author administrator
 */
public class MessageForm {

    private String from = ""; //������
    private String face = ""; //����
    private String to = ""; //������
    private String color = ""; //������ɫ
    private String content = ""; //��������
    private String isPrivate = "";			//�Ƿ�Ϊ���Ļ�
    private String sendTime = ""; //����ʱ��

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
