package com.itislevel.lyl.mvp.model.bean;

/**
 * desc:功能说明必写
 * user:itisi
 * date:2017/11/27.14:18
 * path:com.itislevel.lyl.mvp.model.bean.UserInfoBean
 **/
public class UserInfoBean {


    /**
     * birthday : 2017-12-20
     * getImgUrl : http://119.27.169.152:8020/yby/img_server/
     * phone : 1222222222
     * nickname : itisi
     * status : 2
     * userid : 3
     * idcard : 522221111111111122
     * username : itisi1
     * token :
     * CA146E758186237C80EC7CBB48BCCE6B492A1A766A3716AA3C187C9329D128D1CFA90372820DF5D518A8C1D95D34FE14
     * logintime : 1513761295326
     * gender : 1
     * saveImgUrl : http://119.27.169.152:8030/img-server/uploadImgs?token=2017ybyimgserver
     * realname : 周一
     * usernum : 9AE5BEE160AF
     * imgurl : "null"
     * iscustom
     * isadviser
     */

    private String birthday;
    private String getImgUrl;
    private String phone;
    private String nickname;
    private String status;
    private int userid;
    private String idcard;
    private String username;
    private String token;
    private long logintime;
    private String gender;
    private String saveImgUrl;
    private String realname;
    private String usernum;
    private String imgurl;
    private String rytoken;
    private int iscustom; // 0用户 1客服
    private int isadviser;//是否是顾问

    public int getIsadviser() {
        return isadviser;
    }

    public void setIsadviser(int isadviser) {
        this.isadviser = isadviser;
    }

    public int getIscustom() {
        return iscustom;
    }

    public void setIscustom(int iscustom) {
        this.iscustom = iscustom;
    }

    public String getRytoken() {
        return rytoken;
    }

    public void setRytoken(String rytoken) {
        this.rytoken = rytoken;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGetImgUrl() {
        return getImgUrl;
    }

    public void setGetImgUrl(String getImgUrl) {
        this.getImgUrl = getImgUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getLogintime() {
        return logintime;
    }

    public void setLogintime(long logintime) {
        this.logintime = logintime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSaveImgUrl() {
        return saveImgUrl;
    }

    public void setSaveImgUrl(String saveImgUrl) {
        this.saveImgUrl = saveImgUrl;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getUsernum() {
        return usernum;
    }

    public void setUsernum(String usernum) {
        this.usernum = usernum;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
