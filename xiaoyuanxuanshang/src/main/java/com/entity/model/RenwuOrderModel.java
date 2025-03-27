package com.entity.model;

import com.entity.RenwuOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 任务订单
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class RenwuOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 订单编号
     */
    private String renwuOrderUuidNumber;


    /**
     * 任务
     */
    private Integer renwuId;


    /**
     * 用户
     */
    private Integer jiequyonghuId;


    /**
     * 悬赏金额
     */
    private Double renwuOrderTruePrice;


    /**
     * 订单类型
     */
    private Integer renwuOrderTypes;


    /**
     * 申请领取时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
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
	 * 获取：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3 listShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
