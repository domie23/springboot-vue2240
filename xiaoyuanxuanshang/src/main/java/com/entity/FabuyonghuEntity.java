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
 * 发布用户
 *
 * @author 
 * @email
 */
@TableName("fabuyonghu")
public class FabuyonghuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public FabuyonghuEntity() {

	}

	public FabuyonghuEntity(T t) {
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
     * 发布用户姓名
     */
    @ColumnInfo(comment="发布用户姓名",type="varchar(200)")
    @TableField(value = "fabuyonghu_name")

    private String fabuyonghuName;


    /**
     * 发布用户手机号
     */
    @ColumnInfo(comment="发布用户手机号",type="varchar(200)")
    @TableField(value = "fabuyonghu_phone")

    private String fabuyonghuPhone;


    /**
     * 发布用户身份证号
     */
    @ColumnInfo(comment="发布用户身份证号",type="varchar(200)")
    @TableField(value = "fabuyonghu_id_number")

    private String fabuyonghuIdNumber;


    /**
     * 发布用户头像
     */
    @ColumnInfo(comment="发布用户头像",type="varchar(200)")
    @TableField(value = "fabuyonghu_photo")

    private String fabuyonghuPhoto;


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
     * 发布用户邮箱
     */
    @ColumnInfo(comment="发布用户邮箱",type="varchar(200)")
    @TableField(value = "fabuyonghu_email")

    private String fabuyonghuEmail;


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

    @Override
    public String toString() {
        return "Fabuyonghu{" +
            ", id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", fabuyonghuName=" + fabuyonghuName +
            ", fabuyonghuPhone=" + fabuyonghuPhone +
            ", fabuyonghuIdNumber=" + fabuyonghuIdNumber +
            ", fabuyonghuPhoto=" + fabuyonghuPhoto +
            ", sexTypes=" + sexTypes +
            ", newMoney=" + newMoney +
            ", fabuyonghuEmail=" + fabuyonghuEmail +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
