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
 * 任务订单
 *
 * @author 
 * @email
 */
@TableName("renwu_order")
public class RenwuOrderEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public RenwuOrderEntity() {

	}

	public RenwuOrderEntity(T t) {
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
     * 订单编号
     */
    @ColumnInfo(comment="订单编号",type="varchar(200)")
    @TableField(value = "renwu_order_uuid_number")

    private String renwuOrderUuidNumber;


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
     * 悬赏金额
     */
    @ColumnInfo(comment="悬赏金额",type="decimal(10,2)")
    @TableField(value = "renwu_order_true_price")

    private Double renwuOrderTruePrice;


    /**
     * 订单类型
     */
    @ColumnInfo(comment="订单类型",type="int(11)")
    @TableField(value = "renwu_order_types")

    private Integer renwuOrderTypes;


    /**
     * 申请领取时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="申请领取时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间  listShow
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
	 * 获取：订单编号
	 */
    public String getRenwuOrderUuidNumber() {
        return renwuOrderUuidNumber;
    }
    /**
	 * 设置：订单编号
	 */

    public void setRenwuOrderUuidNumber(String renwuOrderUuidNumber) {
        this.renwuOrderUuidNumber = renwuOrderUuidNumber;
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
	 * 获取：悬赏金额
	 */
    public Double getRenwuOrderTruePrice() {
        return renwuOrderTruePrice;
    }
    /**
	 * 设置：悬赏金额
	 */

    public void setRenwuOrderTruePrice(Double renwuOrderTruePrice) {
        this.renwuOrderTruePrice = renwuOrderTruePrice;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getRenwuOrderTypes() {
        return renwuOrderTypes;
    }
    /**
	 * 设置：订单类型
	 */

    public void setRenwuOrderTypes(Integer renwuOrderTypes) {
        this.renwuOrderTypes = renwuOrderTypes;
    }
    /**
	 * 获取：申请领取时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：申请领取时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "RenwuOrder{" +
            ", id=" + id +
            ", renwuOrderUuidNumber=" + renwuOrderUuidNumber +
            ", renwuId=" + renwuId +
            ", jiequyonghuId=" + jiequyonghuId +
            ", renwuOrderTruePrice=" + renwuOrderTruePrice +
            ", renwuOrderTypes=" + renwuOrderTypes +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
