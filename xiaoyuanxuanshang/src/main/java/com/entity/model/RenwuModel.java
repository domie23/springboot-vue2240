package com.entity.model;

import com.entity.RenwuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 任务
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class RenwuModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 发布用户
     */
    private Integer fabuyonghuId;


    /**
     * 任务名称
     */
    private String renwuName;


    /**
     * 任务编号
     */
    private String renwuUuidNumber;


    /**
     * 任务照片
     */
    private String renwuPhoto;


    /**
     * 任务附件
     */
    private String renwuFile;


    /**
     * 任务执行时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date zhixingTime;


    /**
     * 执行地点
     */
    private String renwuAddress;


    /**
     * 任务类型
     */
    private Integer renwuTypes;


    /**
     * 悬赏金额
     */
    private Double renwuJine;


    /**
     * 任务热度
     */
    private Integer renwuClicknum;


    /**
     * 任务要求
     */
    private String renwuContent;


    /**
     * 任务状态
     */
    private Integer renwuZhuangtaiTypes;


    /**
     * 逻辑删除
     */
    private Integer renwuDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
