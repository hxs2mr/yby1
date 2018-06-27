package com.itislevel.lyl.mvp.model.bean;

/**
 * Created by Administrator on 2018\4\11 0011.
 */

public class AdviserBean {

    /**
     * countname : 市辖区
     * companyrem :  家政服务是指将部分家庭事务社会化、职业化、市场化，属于民生范畴。由社会专业机构、社区机构、非盈利组织、家政服务公司和专业家政服务人员来承担，帮助家庭与社会互动，构建家庭规范，提高家庭生活质量，以此促进整个社会的发展。
     * provname : 贵州省
     * STATUS : 1
     * provid : 520000
     * companyname : 神通快递
     * countid : 520101
     * cityid : 522300
     * id : 5
     * companyimge : E447C767-7850-4857-A47E-8A5A67070AE1.1,4CEF107D-1F10-49E6-8F75-68E2725F9151.2,33F0FED6-10B5-4ED0-AA20-0C3056962262.3
     * contactname : 勾昌俊
     * address : 兴义市凤凰城酒店旁
     * createdtime : 1527408783000
     * contactphone : 13605566777
     * cityname : 黔西南自治州
     * companylogo : CF488C05-EC2D-4E9F-A10C-30AAE9E4F471.77
     */

    private String countname;
    private String companyrem;
    private String provname;
    private String STATUS;
    private int provid;
    private String companyname;
    private int countid;
    private int cityid;
    private int id;
    private String companyimge;
    private String contactname;
    private String address;
    private long createdtime;
    private String contactphone;
    private String cityname;
    private String companylogo;


    public AdviserBean(String companyrem, String provname, String companyname, int id, String companyimge, String contactname, String address, long createdtime, String cityname, String companylogo,String contactphone) {
        this.companyrem = companyrem;
        this.provname = provname;
        this.companyname = companyname;
        this.id = id;
        this.companyimge = companyimge;
        this.contactname = contactname;
        this.address = address;
        this.createdtime = createdtime;
        this.cityname = cityname;
        this.companylogo = companylogo;
        this.contactphone = contactphone;
    }

    public String getCountname() {
        return countname;
    }

    public void setCountname(String countname) {
        this.countname = countname;
    }

    public String getCompanyrem() {
        return companyrem;
    }

    public void setCompanyrem(String companyrem) {
        this.companyrem = companyrem;
    }

    public String getProvname() {
        return provname;
    }

    public void setProvname(String provname) {
        this.provname = provname;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public int getProvid() {
        return provid;
    }

    public void setProvid(int provid) {
        this.provid = provid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public int getCountid() {
        return countid;
    }

    public void setCountid(int countid) {
        this.countid = countid;
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

    public String getCompanyimge() {
        return companyimge;
    }

    public void setCompanyimge(String companyimge) {
        this.companyimge = companyimge;
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(long createdtime) {
        this.createdtime = createdtime;
    }

    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getCompanylogo() {
        return companylogo;
    }

    public void setCompanylogo(String companylogo) {
        this.companylogo = companylogo;
    }
}
