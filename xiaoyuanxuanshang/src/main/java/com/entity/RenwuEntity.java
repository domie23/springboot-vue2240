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
 * 任务
 *
 * @author 
 * @email
 */
@TableName("renwu")
public class RenwuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public RenwuEntity() {

	}

	public RenwuEntity(T t) {
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
     * 发布用户
     */
    @ColumnInfo(comment="发布用户",type="int(11)")
    @TableField(value = "fabuyonghu_id")

    private Integer fabuyonghuId;


    /**
     * 任务名称
     */
    @ColumnInfo(comment="任务名称",type="varchar(200)")
    @TableField(value = "renwu_name")

    private String renwuName;


    /**
     * 任务编号
     */
    @ColumnInfo(comment="任务编号",type="varchar(200)")
    @TableField(value = "renwu_uuid_number")

    private String renwuUuidNumber;


    /**
     * 任务照片
     */
    @ColumnInfo(comment="任务照片",type="varchar(200)")
    @TableField(value = "renwu_photo")

    private String renwuPhoto;


    /**
     * 任务附件
     */
    @ColumnInfo(comment="任务附件",type="varchar(200)")
    @TableField(value = "renwu_file")

    private String renwuFile;


    /**
     * 任务执行时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="任务执行时间",type="timestamp")
    @TableField(value = "zhixing_time")

    private Date zhixingTime;


    /**
     * 执行地点
     */
    @ColumnInfo(comment="执行地点",type="varchar(200)")
    @TableField(value = "renwu_address")

    private String renwuAddress;


    /**
     * 任务类型
     */
    @ColumnInfo(comment="任务类型",type="int(11)")
    @TableField(value = "renwu_types")

    private Integer renwuTypes;


    /**
     * 悬赏金额
     */
    @ColumnInfo(comment="悬赏金额",type="decimal(10,2)")
    @TableField(value = "renwu_jine")

    private Double renwuJine;


    /**
     * 任务热度
     */
    @ColumnInfo(comment="任务热度",type="int(11)")
    @TableField(value = "renwu_clicknum")

    private Integer renwuClicknum;


    /**
     * 任务要求
     */
    @ColumnInfo(comment="任务要求",type="longtext")
    @TableField(value = "renwu_content")

    private String renwuContent;


    /**
     * 任务状态
     */
    @ColumnInfo(comment="任务状态",type="int(11)")
    @TableField(value = "renwu_zhuangtai_types")

    private Integer renwuZhuangtaiTypes;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "renwu_delete")

    private Integer renwuDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 申请状态
     */
    @ColumnInfo(comment="申请状态",type="int(11)")
    @TableField(value = "renwu_yesno_types")

    private Integer renwuYesnoTypes;


    /**
     * 审核意见
     */
    @ColumnInfo(comment="审核意见",type="longtext")
    @TableField(value = "renwu_yesno_text")

    private String renwuYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="审核时间",type="timestamp")
    @TableField(value = "renwu_shenhe_time")

    private Date renwuShenheTime;


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
	 * 获取：发布用户
	 */
    public Integer getFabuyonghuId() {
        return fabuyonghuId;
    }
    /**
	 * 设置：发布用户
	 */

    public void setFabuyonghuId(Integer fabuyonghuId) {
        this.fabuyonghuId = fabuyonghuId;
    }
    /**
	 * 获取：任务名称
	 */
    public String getRenwuName() {
        return renwuName;
    }
    /**
	 * 设置：任务名称
	 */

    public void setRenwuName(String renwuName) {
        this.renwuName = renwuName;
    }
    /**
	 * 获取：任务编号
	 */
    public String getRenwuUuidNumber() {
        return renwuUuidNumber;
    }
    /**
	 * 设置：任务编号
	 */

    public void setRenwuUuidNumber(String renwuUuidNumber) {
        this.renwuUuidNumber = renwuUuidNumber;
    }
    /**
	 * 获取：任务照片
	 */
    public String getRenwuPhoto() {
        return renwuPhoto;
    }
    /**
	 * 设置：任务照片
	 */

    public void setRenwuPhoto(String renwuPhoto) {
        this.renwuPhoto = renwuPhoto;
    }
    /**
	 * 获取：任务附件
	 */
    public String getRenwuFile() {
        return renwuFile;
    }
    /**
	 * 设置：任务附件
	 */

    public void setRenwuFile(String renwuFile) {
        this.renwuFile = renwuFile;
    }
    /**
	 * 获取：任务执行时间
	 */
    public Date getZhixingTime() {
        return zhixingTime;
    }
    /**
	 * 设置：任务执行时间
	 */

    public void setZhixingTime(Date zhixingTime) {
        this.zhixingTime = zhixingTime;
    }
    /**
	 * 获取：执行地点
	 */
    public String getRenwuAddress() {
        return renwuAddress;
    }
    /**
	 * 设置：执行地点
	 */

    public void setRenwuAddress(String renwuAddress) {
        this.renwuAddress = renwuAddress;
    }
    /**
	 * 获取：任务类型
	 */
    public Integer getRenwuTypes() {
        return renwuTypes;
    }
    /**
	 * 设置：任务类型
	 */

    public void setRenwuTypes(Integer renwuTypes) {
        this.renwuTypes = renwuTypes;
    }
    /**
	 * 获取：悬赏金额
	 */
    public Double getRenwuJine() {
        return renwuJine;
    }
    /**
	 * 设置：悬赏金额
	 */

    public void setRenwuJine(Double renwuJine) {
        this.renwuJine = renwuJine;
    }
    /**
	 * 获取：任务热度
	 */
    public Integer getRenwuClicknum() {
        return renwuClicknum;
    }
    /**
	 * 设置：任务热度
	 */

    public void setRenwuClicknum(Integer renwuClicknum) {
        this.renwuClicknum = renwuClicknum;
    }
    /**
	 * 获取：任务要求
	 */
    public String getRenwuContent() {
        return renwuContent;
    }
    /**
	 * 设置：任务要求
	 */

    public void setRenwuContent(String renwuContent) {
        this.renwuContent = renwuContent;
    }
    /**
	 * 获取：任务状态
	 */
    public Integer getRenwuZhuangtaiTypes() {
        return renwuZhuangtaiTypes;
    }
    /**
	 * 设置：任务状态
	 */

    public void setRenwuZhuangtaiTypes(Integer renwuZhuangtaiTypes) {
        this.renwuZhuangtaiTypes = renwuZhuangtaiTypes;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getRenwuDelete() {
        return renwuDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setRenwuDelete(Integer renwuDelete) {
        this.renwuDelete = renwuDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：申请状态
	 */
    public Integer getRenwuYesnoTypes() {
        return renwuYesnoTypes;
    }
    /**
	 * 设置：申请状态
	 */

    public void setRenwuYesnoTypes(Integer renwuYesnoTypes) {
        this.renwuYesnoTypes = renwuYesnoTypes;
    }
    /**
	 * 获取：审核意见
	 */
    public String getRenwuYesnoText() {
        return renwuYesnoText;
    }
    /**
	 * 设置：审核意见
	 */

    public void setRenwuYesnoText(String renwuYesnoText) {
        this.renwuYesnoText = renwuYesnoText;
    }
    /**
	 * 获取：审核时间
	 */
    public Date getRenwuShenheTime() {
        return renwuShenheTime;
    }
    /**
	 * 设置：审核时间
	 */

    public void setRenwuShenheTime(Date renwuShenheTime) {
        this.renwuShenheTime = renwuShenheTime;
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
        return "Renwu{" +
            ", id=" + id +
            ", fabuyonghuId=" + fabuyonghuId +
            ", renwuName=" + renwuName +
            ", renwuUuidNumber=" + renwuUuidNumber +
            ", renwuPhoto=" + renwuPhoto +
            ", renwuFile=" + renwuFile +
            ", zhixingTime=" + DateUtil.convertString(zhixingTime,"yyyy-MM-dd") +
            ", renwuAddress=" + renwuAddress +
            ", renwuTypes=" + renwuTypes +
            ", renwuJine=" + renwuJine +
            ", renwuClicknum=" + renwuClicknum +
            ", renwuContent=" + renwuContent +
            ", renwuZhuangtaiTypes=" + renwuZhuangtaiTypes +
            ", renwuDelete=" + renwuDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", renwuYesnoTypes=" + renwuYesnoTypes +
            ", renwuYesnoText=" + renwuYesnoText +
            ", renwuShenheTime=" + DateUtil.convertString(renwuShenheTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
