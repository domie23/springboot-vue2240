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
 * 任务评价
 *
 * @author 
 * @email
 */
@TableName("renwu_commentback")
public class RenwuCommentbackEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public RenwuCommentbackEntity() {

	}

	public RenwuCommentbackEntity(T t) {
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
     * 任务
     */
    @ColumnInfo(comment="任务",type="int(11)")
    @TableField(value = "renwu_id")

    private Integer renwuId;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "jiequyonghu_id")

    private Integer jiequyonghuId;


    /**
     * 评分
     */
    @ColumnInfo(comment="评分",type="int(11)")
    @TableField(value = "renwu_commentback_pingfen_number")

    private Integer renwuCommentbackPingfenNumber;


    /**
     * 评价内容
     */
    @ColumnInfo(comment="评价内容",type="longtext")
    @TableField(value = "renwu_commentback_text")

    private String renwuCommentbackText;


    /**
     * 评价时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="评价时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 回复内容
     */
    @ColumnInfo(comment="回复内容",type="longtext")
    @TableField(value = "reply_text")

    private String replyText;


    /**
     * 回复时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="回复时间",type="timestamp")
    @TableField(value = "update_time",fill = FieldFill.UPDATE)

    private Date updateTime;


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
	 * 获取：任务
	 */
    public Integer getRenwuId() {
        return renwuId;
    }
    /**
	 * 设置：任务
	 */

    public void setRenwuId(Integer renwuId) {
        this.renwuId = renwuId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getJiequyonghuId() {
        return jiequyonghuId;
    }
    /**
	 * 设置：用户
	 */

    public void setJiequyonghuId(Integer jiequyonghuId) {
        this.jiequyonghuId = jiequyonghuId;
    }
    /**
	 * 获取：评分
	 */
    public Integer getRenwuCommentbackPingfenNumber() {
        return renwuCommentbackPingfenNumber;
    }
    /**
	 * 设置：评分
	 */

    public void setRenwuCommentbackPingfenNumber(Integer renwuCommentbackPingfenNumber) {
        this.renwuCommentbackPingfenNumber = renwuCommentbackPingfenNumber;
    }
    /**
	 * 获取：评价内容
	 */
    public String getRenwuCommentbackText() {
        return renwuCommentbackText;
    }
    /**
	 * 设置：评价内容
	 */

    public void setRenwuCommentbackText(String renwuCommentbackText) {
        this.renwuCommentbackText = renwuCommentbackText;
    }
    /**
	 * 获取：评价时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：评价时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：回复内容
	 */
    public String getReplyText() {
        return replyText;
    }
    /**
	 * 设置：回复内容
	 */

    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }
    /**
	 * 获取：回复时间
	 */
    public Date getUpdateTime() {
        return updateTime;
    }
    /**
	 * 设置：回复时间
	 */

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        return "RenwuCommentback{" +
            ", id=" + id +
            ", renwuId=" + renwuId +
            ", jiequyonghuId=" + jiequyonghuId +
            ", renwuCommentbackPingfenNumber=" + renwuCommentbackPingfenNumber +
            ", renwuCommentbackText=" + renwuCommentbackText +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", replyText=" + replyText +
            ", updateTime=" + DateUtil.convertString(updateTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
