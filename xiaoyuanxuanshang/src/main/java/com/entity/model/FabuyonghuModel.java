package com.entity.model;

import com.entity.FabuyonghuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 发布用户
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class FabuyonghuModel implements Serializable {
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
     * 发布用户姓名
     */
    private String fabuyonghuName;


    /**
     * 发布用户手机号
     */
    private String fabuyonghuPhone;


    /**
     * 发布用户身份证号
     */
    private String fabuyonghuIdNumber;


    /**
     * 发布用户头像
     */
    private String fabuyonghuPhoto;


    /**
     * 性别
     */
    private Integer sexTypes;


    /**
     * 余额
     */
    private Double newMoney;


    /**
     * 发布用户邮箱
     */
    private String fabuyonghuEmail;


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
	 * 获取：发布用户姓名
	 */
    public String getFabuyonghuName() {
        return fabuyonghuName;
    }


    /**
	 * 设置：发布用户姓名
	 */
    public void setFabuyonghuName(String fabuyonghuName) {
        this.fabuyonghuName = fabuyonghuName;
    }
    /**
	 * 获取：发布用户手机号
	 */
    public String getFabuyonghuPhone() {
        return fabuyonghuPhone;
    }


    /**
	 * 设置：发布用户手机号
	 */
    public void setFabuyonghuPhone(String fabuyonghuPhone) {
        this.fabuyonghuPhone = fabuyonghuPhone;
    }
    /**
	 * 获取：发布用户身份证号
	 */
    public String getFabuyonghuIdNumber() {
        return fabuyonghuIdNumber;
    }


    /**
	 * 设置：发布用户身份证号
	 */
    public void setFabuyonghuIdNumber(String fabuyonghuIdNumber) {
        this.fabuyonghuIdNumber = fabuyonghuIdNumber;
    }
    /**
	 * 获取：发布用户头像
	 */
    public String getFabuyonghuPhoto() {
        return fabuyonghuPhoto;
    }


    /**
	 * 设置：发布用户头像
	 */
    public void setFabuyonghuPhoto(String fabuyonghuPhoto) {
        this.fabuyonghuPhoto = fabuyonghuPhoto;
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
	 * 获取：发布用户邮箱
	 */
    public String getFabuyonghuEmail() {
        return fabuyonghuEmail;
    }


    /**
	 * 设置：发布用户邮箱
	 */
    public void setFabuyonghuEmail(String fabuyonghuEmail) {
        this.fabuyonghuEmail = fabuyonghuEmail;
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
