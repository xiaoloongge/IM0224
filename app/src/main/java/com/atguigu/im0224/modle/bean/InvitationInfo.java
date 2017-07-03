package com.atguigu.im0224.modle.bean;

/**
 * Created by Administrator on 2017/7/3.
 */

public class InvitationInfo {

    private UserInfo userInfo;
    private String reason;
    private InvitationStatus status;

    public InvitationInfo() {

    }

    public InvitationInfo(UserInfo userInfo,String reason,InvitationStatus status){
        this.userInfo = userInfo;
        this.reason = reason;
        this.status = status;
    }


    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public InvitationStatus getStatus() {
        return status;
    }

    public void setStatus(InvitationStatus status) {
        this.status = status;
    }

    public enum InvitationStatus{
        // contact invite status
        NEW_INVITE,// 新邀请
        INVITE_ACCEPT,//接受邀请
        INVITE_ACCEPT_BY_PEER,// 邀请被接受
    }


}
