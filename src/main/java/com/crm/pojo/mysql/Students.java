package com.crm.pojo.mysql;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Students implements Serializable {
    private Integer id;

    private String name;

    private Integer age;

    private String sex;

    private String phone;

    private String stustatus;

    private String perstate;

    private String msgsource;

    private String sourceurl;

    private String sourcekeyword;

    private String address;

    private String netpusherid;

    private String askerid;

    private String qq;

    private String weixin;

    private String content;

    private Date createtime;

    private String learnforward;

    private String isvalid;

    private String record;

    private String isreturnvist;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date firstvisittime;

    private String ishome;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hometime;

    private String lostvalid;

    private String ispay;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date paytime;

    private Double money;

    private String isreturnmoney;

    private String isinclass;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inclasstime;

    private String inclasscontent;

    private String askercontent;

    private String isdel;

    private String frompart;

    private String stuconcern;

    private String isbaobei;

    private String zixunname;

    private String createuser;

    private String returnmoneyreason;

    private Double premoney;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date premoneytime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getStustatus() {
        return stustatus;
    }

    public void setStustatus(String stustatus) {
        this.stustatus = stustatus == null ? null : stustatus.trim();
    }

    public String getPerstate() {
        return perstate;
    }

    public void setPerstate(String perstate) {
        this.perstate = perstate == null ? null : perstate.trim();
    }

    public String getMsgsource() {
        return msgsource;
    }

    public void setMsgsource(String msgsource) {
        this.msgsource = msgsource == null ? null : msgsource.trim();
    }

    public String getSourceurl() {
        return sourceurl;
    }

    public void setSourceurl(String sourceurl) {
        this.sourceurl = sourceurl == null ? null : sourceurl.trim();
    }

    public String getSourcekeyword() {
        return sourcekeyword;
    }

    public void setSourcekeyword(String sourcekeyword) {
        this.sourcekeyword = sourcekeyword == null ? null : sourcekeyword.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getNetpusherid() {
        return netpusherid;
    }

    public void setNetpusherid(String netpusherid) {
        this.netpusherid = netpusherid == null ? null : netpusherid.trim();
    }

    public String getAskerid() {
        return askerid;
    }

    public void setAskerid(String askerid) {
        this.askerid = askerid == null ? null : askerid.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin == null ? null : weixin.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getLearnforward() {
        return learnforward;
    }

    public void setLearnforward(String learnforward) {
        this.learnforward = learnforward == null ? null : learnforward.trim();
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid == null ? null : isvalid.trim();
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record == null ? null : record.trim();
    }

    public String getIsreturnvist() {
        return isreturnvist;
    }

    public void setIsreturnvist(String isreturnvist) {
        this.isreturnvist = isreturnvist == null ? null : isreturnvist.trim();
    }

    public Date getFirstvisittime() {
        return firstvisittime;
    }

    public void setFirstvisittime(Date firstvisittime) {
        this.firstvisittime = firstvisittime;
    }

    public String getIshome() {
        return ishome;
    }

    public void setIshome(String ishome) {
        this.ishome = ishome == null ? null : ishome.trim();
    }

    public Date getHometime() {
        return hometime;
    }

    public void setHometime(Date hometime) {
        this.hometime = hometime;
    }

    public String getLostvalid() {
        return lostvalid;
    }

    public void setLostvalid(String lostvalid) {
        this.lostvalid = lostvalid == null ? null : lostvalid.trim();
    }

    public String getIspay() {
        return ispay;
    }

    public void setIspay(String ispay) {
        this.ispay = ispay == null ? null : ispay.trim();
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getIsreturnmoney() {
        return isreturnmoney;
    }

    public void setIsreturnmoney(String isreturnmoney) {
        this.isreturnmoney = isreturnmoney == null ? null : isreturnmoney.trim();
    }

    public String getIsinclass() {
        return isinclass;
    }

    public void setIsinclass(String isinclass) {
        this.isinclass = isinclass == null ? null : isinclass.trim();
    }

    public Date getInclasstime() {
        return inclasstime;
    }

    public void setInclasstime(Date inclasstime) {
        this.inclasstime = inclasstime;
    }

    public String getInclasscontent() {
        return inclasscontent;
    }

    public void setInclasscontent(String inclasscontent) {
        this.inclasscontent = inclasscontent == null ? null : inclasscontent.trim();
    }

    public String getAskercontent() {
        return askercontent;
    }

    public void setAskercontent(String askercontent) {
        this.askercontent = askercontent == null ? null : askercontent.trim();
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel == null ? null : isdel.trim();
    }

    public String getFrompart() {
        return frompart;
    }

    public void setFrompart(String frompart) {
        this.frompart = frompart == null ? null : frompart.trim();
    }

    public String getStuconcern() {
        return stuconcern;
    }

    public void setStuconcern(String stuconcern) {
        this.stuconcern = stuconcern == null ? null : stuconcern.trim();
    }

    public String getIsbaobei() {
        return isbaobei;
    }

    public void setIsbaobei(String isbaobei) {
        this.isbaobei = isbaobei == null ? null : isbaobei.trim();
    }

    public String getZixunname() {
        return zixunname;
    }

    public void setZixunname(String zixunname) {
        this.zixunname = zixunname == null ? null : zixunname.trim();
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    public String getReturnmoneyreason() {
        return returnmoneyreason;
    }

    public void setReturnmoneyreason(String returnmoneyreason) {
        this.returnmoneyreason = returnmoneyreason == null ? null : returnmoneyreason.trim();
    }

    public Double getPremoney() {
        return premoney;
    }

    public void setPremoney(Double premoney) {
        this.premoney = premoney;
    }

    public Date getPremoneytime() {
        return premoneytime;
    }

    public void setPremoneytime(Date premoneytime) {
        this.premoneytime = premoneytime;
    }

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", stustatus='" + stustatus + '\'' +
                ", perstate='" + perstate + '\'' +
                ", msgsource='" + msgsource + '\'' +
                ", sourceurl='" + sourceurl + '\'' +
                ", sourcekeyword='" + sourcekeyword + '\'' +
                ", address='" + address + '\'' +
                ", netpusherid='" + netpusherid + '\'' +
                ", askerid='" + askerid + '\'' +
                ", qq='" + qq + '\'' +
                ", weixin='" + weixin + '\'' +
                ", content='" + content + '\'' +
                ", createtime=" + createtime +
                ", learnforward='" + learnforward + '\'' +
                ", isvalid='" + isvalid + '\'' +
                ", record='" + record + '\'' +
                ", isreturnvist='" + isreturnvist + '\'' +
                ", firstvisittime=" + firstvisittime +
                ", ishome='" + ishome + '\'' +
                ", hometime=" + hometime +
                ", lostvalid='" + lostvalid + '\'' +
                ", ispay='" + ispay + '\'' +
                ", paytime=" + paytime +
                ", money=" + money +
                ", isreturnmoney='" + isreturnmoney + '\'' +
                ", isinclass='" + isinclass + '\'' +
                ", inclasstime=" + inclasstime +
                ", inclasscontent='" + inclasscontent + '\'' +
                ", askercontent='" + askercontent + '\'' +
                ", isdel='" + isdel + '\'' +
                ", frompart='" + frompart + '\'' +
                ", stuconcern='" + stuconcern + '\'' +
                ", isbaobei='" + isbaobei + '\'' +
                ", zixunname='" + zixunname + '\'' +
                ", createuser='" + createuser + '\'' +
                ", returnmoneyreason='" + returnmoneyreason + '\'' +
                ", premoney=" + premoney +
                ", premoneytime=" + premoneytime +
                '}';
    }
}