package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 接取用户
 *
 * @author 
 * @email
 */
@TableName("jiequyonghu")
public class JiequyonghuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JiequyonghuEntity() {

	}

	public JiequyonghuEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 账户
     */
    @ColumnInfo(comment="账户",type="varchar(200)")
    @TableField(value = "username")

    private String username;


    /**
     * 密码
     */
    @ColumnInfo(comment="密码",type="varchar(200)")
    @TableField(value = "password")

    private String password;


    /**
     * 接取用户姓名
     */
    @ColumnInfo(comment="接取用户姓名",type="varchar(200)")
    @TableField(value = "jiequyonghu_name")

    private String jiequyonghuName;


    /**
     * 接取用户手机号
     */
    @ColumnInfo(comment="接取用户手机号",type="varchar(200)")
    @TableField(value = "jiequyonghu_phone")

    private String jiequyonghuPhone;


    /**
     * 接取用户身份证号
     */
    @ColumnInfo(comment="接取用户身份证号",type="varchar(200)")
    @TableField(value = "jiequyonghu_id_number")

    private String jiequyonghuIdNumber;


    /**
     * 接取用户头像
     */
    @ColumnInfo(comment="接取用户头像",type="varchar(200)")
    @TableField(value = "jiequyonghu_photo")

    private String jiequyonghuPhoto;


    /**
     * 性别
     */
    @ColumnInfo(comment="性别",type="int(11)")
    @TableField(value = "sex_types")

    private Integer sexTypes;


    /**
     * 余额
     */
    @ColumnInfo(comment="余额",type="decimal(10,2)")
    @TableField(value = "new_money")

    private Double newMoney;


    /**
     * 完成率(百分比)
     */
    @ColumnInfo(comment="完成率(百分比)",type="int(11)")
    @TableField(value = "wanchenglv")

    private Integer wanchenglv;


    /**
     * 接取用户邮箱
     */
    @ColumnInfo(comment="接取用户邮箱",type="varchar(200)")
    @TableField(value = "jiequyonghu_email")

    private String jiequyonghuEmail;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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

    @Override
    public String toString() {
        return "Jiequyonghu{" +
            ", id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", jiequyonghuName=" + jiequyonghuName +
            ", jiequyonghuPhone=" + jiequyonghuPhone +
            ", jiequyonghuIdNumber=" + jiequyonghuIdNumber +
            ", jiequyonghuPhoto=" + jiequyonghuPhoto +
            ", sexTypes=" + sexTypes +
            ", newMoney=" + newMoney +
            ", wanchenglv=" + wanchenglv +
            ", jiequyonghuEmail=" + jiequyonghuEmail +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
