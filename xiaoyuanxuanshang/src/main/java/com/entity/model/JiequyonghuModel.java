package com.entity.model;

import com.entity.JiequyonghuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 接取用户
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class JiequyonghuModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 接取用户姓名
     */
    private String jiequyonghuName;


    /**
     * 接取用户手机号
     */
    private String jiequyonghuPhone;


    /**
     * 接取用户身份证号
     */
    private String jiequyonghuIdNumber;


    /**
     * 接取用户头像
     */
    private String jiequyonghuPhoto;


    /**
     * 性别
     */
    private Integer sexTypes;


    /**
     * 余额
     */
    private Double newMoney;


    /**
     * 完成率(百分比)
     */
    private Integer wanchenglv;


    /**
     * 接取用户邮箱
     */
    private String jiequyonghuEmail;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 设置：账户
	 */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 设置：密码
	 */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：接取用户姓名
	 */
    public String getJiequyonghuName() {
        return jiequyonghuName;
    }


    /**
	 * 设置：接取用户姓名
	 */
    public void setJiequyonghuName(String jiequyonghuName) {
        this.jiequyonghuName = jiequyonghuName;
    }
    /**
	 * 获取：接取用户手机号
	 */
    public String getJiequyonghuPhone() {
        return jiequyonghuPhone;
    }


    /**
	 * 设置：接取用户手机号
	 */
    public void setJiequyonghuPhone(String jiequyonghuPhone) {
        this.jiequyonghuPhone = jiequyonghuPhone;
    }
    /**
	 * 获取：接取用户身份证号
	 */
    public String getJiequyonghuIdNumber() {
        return jiequyonghuIdNumber;
    }


    /**
	 * 设置：接取用户身份证号
	 */
    public void setJiequyonghuIdNumber(String jiequyonghuIdNumber) {
        this.jiequyonghuIdNumber = jiequyonghuIdNumber;
    }
    /**
	 * 获取：接取用户头像
	 */
    public String getJiequyonghuPhoto() {
        return jiequyonghuPhoto;
    }


    /**
	 * 设置：接取用户头像
	 */
    public void setJiequyonghuPhoto(String jiequyonghuPhoto) {
        this.jiequyonghuPhoto = jiequyonghuPhoto;
    }
    /**
	 * 获取：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 设置：性别
	 */
    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 获取：余额
	 */
    public Double getNewMoney() {
        return newMoney;
    }


    /**
	 * 设置：余额
	 */
    public void setNewMoney(Double newMoney) {
        this.newMoney = newMoney;
    }
    /**
	 * 获取：完成率(百分比)
	 */
    public Integer getWanchenglv() {
        return wanchenglv;
    }


    /**
	 * 设置：完成率(百分比)
	 */
    public void setWanchenglv(Integer wanchenglv) {
        this.wanchenglv = wanchenglv;
    }
    /**
	 * 获取：接取用户邮箱
	 */
    public String getJiequyonghuEmail() {
        return jiequyonghuEmail;
    }


    /**
	 * 设置：接取用户邮箱
	 */
    public void setJiequyonghuEmail(String jiequyonghuEmail) {
        this.jiequyonghuEmail = jiequyonghuEmail;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
