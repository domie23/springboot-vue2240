package com.entity.vo;

import com.entity.JiequyonghuEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 接取用户
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("jiequyonghu")
public class JiequyonghuVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 账户
     */

    @TableField(value = "username")
    private String username;


    /**
     * 密码
     */

    @TableField(value = "password")
    private String password;


    /**
     * 接取用户姓名
     */

    @TableField(value = "jiequyonghu_name")
    private String jiequyonghuName;


    /**
     * 接取用户手机号
     */

    @TableField(value = "jiequyonghu_phone")
    private String jiequyonghuPhone;


    /**
     * 接取用户身份证号
     */

    @TableField(value = "jiequyonghu_id_number")
    private String jiequyonghuIdNumber;


    /**
     * 接取用户头像
     */

    @TableField(value = "jiequyonghu_photo")
    private String jiequyonghuPhoto;


    /**
     * 性别
     */

    @TableField(value = "sex_types")
    private Integer sexTypes;


    /**
     * 余额
     */

    @TableField(value = "new_money")
    private Double newMoney;


    /**
     * 完成率(百分比)
     */

    @TableField(value = "wanchenglv")
    private Integer wanchenglv;


    /**
     * 接取用户邮箱
     */

    @TableField(value = "jiequyonghu_email")
    private String jiequyonghuEmail;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：接取用户姓名
	 */
    public String getJiequyonghuName() {
        return jiequyonghuName;
    }


    /**
	 * 获取：接取用户姓名
	 */

    public void setJiequyonghuName(String jiequyonghuName) {
        this.jiequyonghuName = jiequyonghuName;
    }
    /**
	 * 设置：接取用户手机号
	 */
    public String getJiequyonghuPhone() {
        return jiequyonghuPhone;
    }


    /**
	 * 获取：接取用户手机号
	 */

    public void setJiequyonghuPhone(String jiequyonghuPhone) {
        this.jiequyonghuPhone = jiequyonghuPhone;
    }
    /**
	 * 设置：接取用户身份证号
	 */
    public String getJiequyonghuIdNumber() {
        return jiequyonghuIdNumber;
    }


    /**
	 * 获取：接取用户身份证号
	 */

    public void setJiequyonghuIdNumber(String jiequyonghuIdNumber) {
        this.jiequyonghuIdNumber = jiequyonghuIdNumber;
    }
    /**
	 * 设置：接取用户头像
	 */
    public String getJiequyonghuPhoto() {
        return jiequyonghuPhoto;
    }


    /**
	 * 获取：接取用户头像
	 */

    public void setJiequyonghuPhoto(String jiequyonghuPhoto) {
        this.jiequyonghuPhoto = jiequyonghuPhoto;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：余额
	 */
    public Double getNewMoney() {
        return newMoney;
    }


    /**
	 * 获取：余额
	 */

    public void setNewMoney(Double newMoney) {
        this.newMoney = newMoney;
    }
    /**
	 * 设置：完成率(百分比)
	 */
    public Integer getWanchenglv() {
        return wanchenglv;
    }


    /**
	 * 获取：完成率(百分比)
	 */

    public void setWanchenglv(Integer wanchenglv) {
        this.wanchenglv = wanchenglv;
    }
    /**
	 * 设置：接取用户邮箱
	 */
    public String getJiequyonghuEmail() {
        return jiequyonghuEmail;
    }


    /**
	 * 获取：接取用户邮箱
	 */

    public void setJiequyonghuEmail(String jiequyonghuEmail) {
        this.jiequyonghuEmail = jiequyonghuEmail;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
