package com.entity.vo;

import com.entity.RenwuCommentbackEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 任务评价
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("renwu_commentback")
public class RenwuCommentbackVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 任务
     */

    @TableField(value = "renwu_id")
    private Integer renwuId;


    /**
     * 用户
     */

    @TableField(value = "jiequyonghu_id")
    private Integer jiequyonghuId;


    /**
     * 评分
     */

    @TableField(value = "renwu_commentback_pingfen_number")
    private Integer renwuCommentbackPingfenNumber;


    /**
     * 评价内容
     */

    @TableField(value = "renwu_commentback_text")
    private String renwuCommentbackText;


    /**
     * 评价时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 回复内容
     */

    @TableField(value = "reply_text")
    private String replyText;


    /**
     * 回复时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "update_time")
    private Date updateTime;


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
	 * 设置：任务
	 */
    public Integer getRenwuId() {
        return renwuId;
    }


    /**
	 * 获取：任务
	 */

    public void setRenwuId(Integer renwuId) {
        this.renwuId = renwuId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getJiequyonghuId() {
        return jiequyonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setJiequyonghuId(Integer jiequyonghuId) {
        this.jiequyonghuId = jiequyonghuId;
    }
    /**
	 * 设置：评分
	 */
    public Integer getRenwuCommentbackPingfenNumber() {
        return renwuCommentbackPingfenNumber;
    }


    /**
	 * 获取：评分
	 */

    public void setRenwuCommentbackPingfenNumber(Integer renwuCommentbackPingfenNumber) {
        this.renwuCommentbackPingfenNumber = renwuCommentbackPingfenNumber;
    }
    /**
	 * 设置：评价内容
	 */
    public String getRenwuCommentbackText() {
        return renwuCommentbackText;
    }


    /**
	 * 获取：评价内容
	 */

    public void setRenwuCommentbackText(String renwuCommentbackText) {
        this.renwuCommentbackText = renwuCommentbackText;
    }
    /**
	 * 设置：评价时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：评价时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：回复内容
	 */
    public String getReplyText() {
        return replyText;
    }


    /**
	 * 获取：回复内容
	 */

    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }
    /**
	 * 设置：回复时间
	 */
    public Date getUpdateTime() {
        return updateTime;
    }


    /**
	 * 获取：回复时间
	 */

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
