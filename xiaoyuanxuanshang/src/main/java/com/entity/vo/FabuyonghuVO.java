package com.entity.vo;

import com.entity.FabuyonghuEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 发布用户
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("fabuyonghu")
public class FabuyonghuVO implements Serializable {
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
     * 发布用户姓名
     */

    @TableField(value = "fabuyonghu_name")
    private String fabuyonghuName;


    /**
     * 发布用户手机号
     */

    @TableField(value = "fabuyonghu_phone")
    private String fabuyonghuPhone;


    /**
     * 发布用户身份证号
     */

    @TableField(value = "fabuyonghu_id_number")
    private String fabuyonghuIdNumber;


    /**
     * 发布用户头像
     */

    @TableField(value = "fabuyonghu_photo")
    private String fabuyonghuPhoto;


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
     * 发布用户邮箱
     */

    @TableField(value = "fabuyonghu_email")
    private String fabuyonghuEmail;


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
	 * 设置：发布用户姓名
	 */
    public String getFabuyonghuName() {
        return fabuyonghuName;
    }


    /**
	 * 获取：发布用户姓名
	 */

    public void setFabuyonghuName(String fabuyonghuName) {
        this.fabuyonghuName = fabuyonghuName;
    }
    /**
	 * 设置：发布用户手机号
	 */
    public String getFabuyonghuPhone() {
        return fabuyonghuPhone;
    }


    /**
	 * 获取：发布用户手机号
	 */

    public void setFabuyonghuPhone(String fabuyonghuPhone) {
        this.fabuyonghuPhone = fabuyonghuPhone;
    }
    /**
	 * 设置：发布用户身份证号
	 */
    public String getFabuyonghuIdNumber() {
        return fabuyonghuIdNumber;
    }


    /**
	 * 获取：发布用户身份证号
	 */

    public void setFabuyonghuIdNumber(String fabuyonghuIdNumber) {
        this.fabuyonghuIdNumber = fabuyonghuIdNumber;
    }
    /**
	 * 设置：发布用户头像
	 */
    public String getFabuyonghuPhoto() {
        return fabuyonghuPhoto;
    }


    /**
	 * 获取：发布用户头像
	 */

    public void setFabuyonghuPhoto(String fabuyonghuPhoto) {
        this.fabuyonghuPhoto = fabuyonghuPhoto;
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
	 * 设置：发布用户邮箱
	 */
    public String getFabuyonghuEmail() {
        return fabuyonghuEmail;
    }


    /**
	 * 获取：发布用户邮箱
	 */

    public void setFabuyonghuEmail(String fabuyonghuEmail) {
        this.fabuyonghuEmail = fabuyonghuEmail;
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
