package com.itislevel.lyl.mvp.model.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2018\4\27 0027.
 */

public class ListPersonBean extends AbstractExpandableItem<ListCommentItemBean> implements MultiItemEntity{
    /**
     * shmlist : []
     * nickname : pmg123456
     * status : 1
     * userid : 39
     * img_h : 621
     * provid : 520000
     * cityid : 520100
     * id : 1
     * content : 哈哈哈有点
     * giftnum : 0
     * username : pmg123456
     * imge : EA73422C-2EDE-4E0D-8FB2-AC1DDF8C595E.jpeg
     * provincename : 贵州
     * createdtime : 1524810659000
     * looknum : 0
     * isfollow : 0
     * cityname : 贵阳
     * useridfabulous : null
     * ispoint : 0
     * nmpointlist : null
     * img_w : 828
     * fabulousnumber : 0
     * imgurl : null
     */
    private String nickname;
    private String status;
    private int userid;
    private String img_h;
    private int provid;
    private int cityid;
    private int id;
    private String content;
    private int giftnum;
    private String username;
    private String imge;
    private String provincename;
    private long createdtime;
    private int looknum;
    private String isfollow;
    private String cityname;
    private Object useridfabulous;
    private String ispoint;
    private List<String> nmpointlist;
    private String img_w;
    private int fabulousnumber;
    private String imgurl;
    private String writingDate;
    private boolean isdianzan;
    private List<ListCommentItemBean> shmlist;
    private List<GiftListBean> giftList;

    public boolean getIsdianzan() {
        return isdianzan;
    }

    public void setIsdianzan(boolean isdianzan) {
        this.isdianzan = isdianzan;
    }

    public String getWritingDate() {
        return writingDate;
    }

    public void setWritingDate(String writingDate) {
        this.writingDate = writingDate;
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

    public String getImg_h() {
        return img_h;
    }

    public void setImg_h(String img_h) {
        this.img_h = img_h;
    }

    public int getProvid() {
        return provid;
    }

    public void setProvid(int provid) {
        this.provid = provid;
    }

    public int getCityid() {
        return cityid;
    }

    public void setCityid(int cityid) {
        this.cityid = cityid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getGiftnum() {
        return giftnum;
    }

    public void setGiftnum(int giftnum) {
        this.giftnum = giftnum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImge() {
        return imge;
    }

    public void setImge(String imge) {
        this.imge = imge;
    }

    public String getProvincename() {
        return provincename;
    }

    public void setProvincename(String provincename) {
        this.provincename = provincename;
    }

    public long getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(long createdtime) {
        this.createdtime = createdtime;
    }

    public int getLooknum() {
        return looknum;
    }

    public void setLooknum(int looknum) {
        this.looknum = looknum;
    }

    public String getIsfollow() {
        return isfollow;
    }

    public void setIsfollow(String isfollow) {
        this.isfollow = isfollow;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public Object getUseridfabulous() {
        return useridfabulous;
    }

    public void setUseridfabulous(Object useridfabulous) {
        this.useridfabulous = useridfabulous;
    }

    public String getIspoint() {
        return ispoint;
    }

    public void setIspoint(String ispoint) {
        this.ispoint = ispoint;
    }

    public List<String> getNmpointlist() {
        return nmpointlist;
    }

    public void setNmpointlist(List<String> nmpointlist) {
        this.nmpointlist = nmpointlist;
    }

    public String getImg_w() {
        return img_w;
    }

    public void setImg_w(String img_w) {
        this.img_w = img_w;
    }

    public int getFabulousnumber() {
        return fabulousnumber;
    }

    public void setFabulousnumber(int fabulousnumber) {
        this.fabulousnumber = fabulousnumber;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public List<ListCommentItemBean> getShmlist() {
        return shmlist;
    }

    public void setShmlist(List<ListCommentItemBean> shmlist) {
        this.shmlist = shmlist;
    }

    @Override
    public int getItemType() {
        return 0;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    public List<GiftListBean> getGiftList() {
        return giftList;
    }

    public void setGiftList(List<GiftListBean> giftList) {
        this.giftList = giftList;
    }

    public static class GiftListBean {
        public GiftListBean(String goodsname, int buyuserid, int count, int goodsid, String nicknameX, String imgurlX) {
            this.goodsname = goodsname;
            this.buyuserid = buyuserid;
            this.count = count;
            this.goodsid = goodsid;
            this.nicknameX = nicknameX;
            this.imgurlX = imgurlX;
        }

        /**
         * goodsname : 白蛋糕
         * buyuserid : 1
         * count : 1
         * goodsid : 1
         * nickname : 老沟
         * imgurl : 9ED75A1E-6DC9-4EF4-9C73-E136892D563D.png
         */

        private String goodsname;
        private int buyuserid;
        private int count;
        private int goodsid;
        @SerializedName("nickname")
        private String nicknameX;
        @SerializedName("imgurl")
        private String imgurlX;

        public String getGoodsname() {
            return goodsname;
        }

        public void setGoodsname(String goodsname) {
            this.goodsname = goodsname;
        }

        public int getBuyuserid() {
            return buyuserid;
        }

        public void setBuyuserid(int buyuserid) {
            this.buyuserid = buyuserid;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getGoodsid() {
            return goodsid;
        }

        public void setGoodsid(int goodsid) {
            this.goodsid = goodsid;
        }

        public String getNicknameX() {
            return nicknameX;
        }

        public void setNicknameX(String nicknameX) {
            this.nicknameX = nicknameX;
        }

        public String getImgurlX() {
            return imgurlX;
        }

        public void setImgurlX(String imgurlX) {
            this.imgurlX = imgurlX;
        }
    }

    public static class ListCommentItemBean {
        /**
         * id : 1
         * createdtime : 1524818030000
         * userid : 39
         * dynamicid : 1
         * touserid : 0
         * answerer :
         * parentid :
         * comment : 哈哈哈有意思吗
         * observer : pmg123456
         * fabulous : 0
         */

        private int id;
        private long createdtime;
        private int userid;
        private int dynamicid;
        private int touserid;
        private String answerer;
        private String parentid;
        private String comment;
        private String observer;
        private int fabulous;
        private int ids;

        public int getIds() {
            return ids;
        }

        public void setIds(int ids) {
            this.ids = ids;
        }

        public ListCommentItemBean(int id, long createdtime, int userid, int dynamicid, int touserid, String answerer, String parentid, String comment, String observer, int fabulous) {
            this.ids = id;
            this.createdtime = createdtime;
            this.userid = userid;
            this.dynamicid = dynamicid;
            this.touserid = touserid;
            this.answerer = answerer;
            this.parentid = parentid;
            this.comment = comment;
            this.observer = observer;
            this.fabulous = fabulous;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(long createdtime) {
            this.createdtime = createdtime;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public int getDynamicid() {
            return dynamicid;
        }

        public void setDynamicid(int dynamicid) {
            this.dynamicid = dynamicid;
        }

        public int getTouserid() {
            return touserid;
        }

        public void setTouserid(int touserid) {
            this.touserid = touserid;
        }

        public String getAnswerer() {
            return answerer;
        }

        public void setAnswerer(String answerer) {
            this.answerer = answerer;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getObserver() {
            return observer;
        }

        public void setObserver(String observer) {
            this.observer = observer;
        }

        public int getFabulous() {
            return fabulous;
        }

        public void setFabulous(int fabulous) {
            this.fabulous = fabulous;
        }
    }

}
