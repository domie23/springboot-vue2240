package com.entity.vo;

import com.entity.RenwuOrderEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 任务订单
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("renwu_order")
public class RenwuOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 订单编号
     */

    @TableField(value = "renwu_order_uuid_number")
    private String renwuOrderUuidNumber;


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
     * 悬赏金额
     */

    @TableField(value = "renwu_order_true_price")
    private Double renwuOrderTruePrice;


    /**
     * 订单类型
     */

    @TableField(value = "renwu_order_types")
    private Integer renwuOrderTypes;


    /**
     * 申请领取时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
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
	 * 设置：订单编号
	 */
    public String getRenwuOrderUuidNumber() {
        return renwuOrderUuidNumber;
    }


    /**
	 * 获取：订单编号
	 */

    public void setRenwuOrderUuidNumber(String renwuOrderUuidNumber) {
        this.renwuOrderUuidNumber = renwuOrderUuidNumber;
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
	 * 设置：悬赏金额
	 */
    public Double getRenwuOrderTruePrice() {
        return renwuOrderTruePrice;
    }


    /**
	 * 获取：悬赏金额
	 */

    public void setRenwuOrderTruePrice(Double renwuOrderTruePrice) {
        this.renwuOrderTruePrice = renwuOrderTruePrice;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getRenwuOrderTypes() {
        return renwuOrderTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setRenwuOrderTypes(Integer renwuOrderTypes) {
        this.renwuOrderTypes = renwuOrderTypes;
    }
    /**
	 * 设置：申请领取时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：申请领取时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
