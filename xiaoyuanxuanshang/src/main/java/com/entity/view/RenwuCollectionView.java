package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.RenwuCollectionEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 任务收藏
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("renwu_collection")
public class RenwuCollectionView extends RenwuCollectionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 类型的值
	*/
	@ColumnInfo(comment="类型的字典表值",type="varchar(200)")
	private String renwuCollectionValue;

	//级联表 接取用户
		/**
		* 接取用户姓名
		*/

		@ColumnInfo(comment="接取用户姓名",type="varchar(200)")
		private String jiequyonghuName;
		/**
		* 接取用户手机号
		*/

		@ColumnInfo(comment="接取用户手机号",type="varchar(200)")
		private String jiequyonghuPhone;
		/**
		* 接取用户身份证号
		*/

		@ColumnInfo(comment="接取用户身份证号",type="varchar(200)")
		private String jiequyonghuIdNumber;
		/**
		* 接取用户头像
		*/

		@ColumnInfo(comment="接取用户头像",type="varchar(200)")
		private String jiequyonghuPhoto;
		/**
		* 余额
		*/
		@ColumnInfo(comment="余额",type="decimal(10,2)")
		private Double newMoney;
		/**
		* 完成率(百分比)
		*/

		@ColumnInfo(comment="完成率(百分比)",type="int(11)")
		private Integer wanchenglv;
		/**
		* 接取用户邮箱
		*/

		@ColumnInfo(comment="接取用户邮箱",type="varchar(200)")
		private String jiequyonghuEmail;
	//级联表 任务
					 
		/**
		* 任务 的 发布用户
		*/
		@ColumnInfo(comment="发布用户",type="int(11)")
		private Integer renwuFabuyonghuId;
		/**
		* 任务名称
		*/

		@ColumnInfo(comment="任务名称",type="varchar(200)")
		private String renwuName;
		/**
		* 任务编号
		*/

		@ColumnInfo(comment="任务编号",type="varchar(200)")
		private String renwuUuidNumber;
		/**
		* 任务照片
		*/

		@ColumnInfo(comment="任务照片",type="varchar(200)")
		private String renwuPhoto;
		/**
		* 任务附件
		*/

		@ColumnInfo(comment="任务附件",type="varchar(200)")
		private String renwuFile;
		/**
		* 任务执行时间
		*/
		@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
		@DateTimeFormat
		@ColumnInfo(comment="任务执行时间",type="timestamp")
		private Date zhixingTime;
		/**
		* 执行地点
		*/

		@ColumnInfo(comment="执行地点",type="varchar(200)")
		private String renwuAddress;
		/**
		* 任务类型
		*/
		@ColumnInfo(comment="任务类型",type="int(11)")
		private Integer renwuTypes;
			/**
			* 任务类型的值
			*/
			@ColumnInfo(comment="任务类型的字典表值",type="varchar(200)")
			private String renwuValue;
		/**
		* 悬赏金额
		*/
		@ColumnInfo(comment="悬赏金额",type="decimal(10,2)")
		private Double renwuJine;
		/**
		* 任务热度
		*/

		@ColumnInfo(comment="任务热度",type="int(11)")
		private Integer renwuClicknum;
		/**
		* 任务要求
		*/

		@ColumnInfo(comment="任务要求",type="longtext")
		private String renwuContent;
		/**
		* 任务状态
		*/
		@ColumnInfo(comment="任务状态",type="int(11)")
		private Integer renwuZhuangtaiTypes;
			/**
			* 任务状态的值
			*/
			@ColumnInfo(comment="任务状态的字典表值",type="varchar(200)")
			private String renwuZhuangtaiValue;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer renwuDelete;



	public RenwuCollectionView() {

	}

	public RenwuCollectionView(RenwuCollectionEntity renwuCollectionEntity) {
		try {
			BeanUtils.copyProperties(this, renwuCollectionEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 类型的值
	*/
	public String getRenwuCollectionValue() {
		return renwuCollectionValue;
	}
	/**
	* 设置： 类型的值
	*/
	public void setRenwuCollectionValue(String renwuCollectionValue) {
		this.renwuCollectionValue = renwuCollectionValue;
	}


	//级联表的get和set 接取用户

		/**
		* 获取： 接取用户姓名
		*/
		public String getJiequyonghuName() {
			return jiequyonghuName;
		}
		/**
		* 设置： 接取用户姓名
		*/
		public void setJiequyonghuName(String jiequyonghuName) {
			this.jiequyonghuName = jiequyonghuName;
		}

		/**
		* 获取： 接取用户手机号
		*/
		public String getJiequyonghuPhone() {
			return jiequyonghuPhone;
		}
		/**
		* 设置： 接取用户手机号
		*/
		public void setJiequyonghuPhone(String jiequyonghuPhone) {
			this.jiequyonghuPhone = jiequyonghuPhone;
		}

		/**
		* 获取： 接取用户身份证号
		*/
		public String getJiequyonghuIdNumber() {
			return jiequyonghuIdNumber;
		}
		/**
		* 设置： 接取用户身份证号
		*/
		public void setJiequyonghuIdNumber(String jiequyonghuIdNumber) {
			this.jiequyonghuIdNumber = jiequyonghuIdNumber;
		}

		/**
		* 获取： 接取用户头像
		*/
		public String getJiequyonghuPhoto() {
			return jiequyonghuPhoto;
		}
		/**
		* 设置： 接取用户头像
		*/
		public void setJiequyonghuPhoto(String jiequyonghuPhoto) {
			this.jiequyonghuPhoto = jiequyonghuPhoto;
		}

		/**
		* 获取： 余额
		*/
		public Double getNewMoney() {
			return newMoney;
		}
		/**
		* 设置： 余额
		*/
		public void setNewMoney(Double newMoney) {
			this.newMoney = newMoney;
		}

		/**
		* 获取： 完成率(百分比)
		*/
		public Integer getWanchenglv() {
			return wanchenglv;
		}
		/**
		* 设置： 完成率(百分比)
		*/
		public void setWanchenglv(Integer wanchenglv) {
			this.wanchenglv = wanchenglv;
		}

		/**
		* 获取： 接取用户邮箱
		*/
		public String getJiequyonghuEmail() {
			return jiequyonghuEmail;
		}
		/**
		* 设置： 接取用户邮箱
		*/
		public void setJiequyonghuEmail(String jiequyonghuEmail) {
			this.jiequyonghuEmail = jiequyonghuEmail;
		}
	//级联表的get和set 任务
		/**
		* 获取：任务 的 发布用户
		*/
		public Integer getRenwuFabuyonghuId() {
			return renwuFabuyonghuId;
		}
		/**
		* 设置：任务 的 发布用户
		*/
		public void setRenwuFabuyonghuId(Integer renwuFabuyonghuId) {
			this.renwuFabuyonghuId = renwuFabuyonghuId;
		}

		/**
		* 获取： 任务名称
		*/
		public String getRenwuName() {
			return renwuName;
		}
		/**
		* 设置： 任务名称
		*/
		public void setRenwuName(String renwuName) {
			this.renwuName = renwuName;
		}

		/**
		* 获取： 任务编号
		*/
		public String getRenwuUuidNumber() {
			return renwuUuidNumber;
		}
		/**
		* 设置： 任务编号
		*/
		public void setRenwuUuidNumber(String renwuUuidNumber) {
			this.renwuUuidNumber = renwuUuidNumber;
		}

		/**
		* 获取： 任务照片
		*/
		public String getRenwuPhoto() {
			return renwuPhoto;
		}
		/**
		* 设置： 任务照片
		*/
		public void setRenwuPhoto(String renwuPhoto) {
			this.renwuPhoto = renwuPhoto;
		}

		/**
		* 获取： 任务附件
		*/
		public String getRenwuFile() {
			return renwuFile;
		}
		/**
		* 设置： 任务附件
		*/
		public void setRenwuFile(String renwuFile) {
			this.renwuFile = renwuFile;
		}

		/**
		* 获取： 任务执行时间
		*/
		public Date getZhixingTime() {
			return zhixingTime;
		}
		/**
		* 设置： 任务执行时间
		*/
		public void setZhixingTime(Date zhixingTime) {
			this.zhixingTime = zhixingTime;
		}

		/**
		* 获取： 执行地点
		*/
		public String getRenwuAddress() {
			return renwuAddress;
		}
		/**
		* 设置： 执行地点
		*/
		public void setRenwuAddress(String renwuAddress) {
			this.renwuAddress = renwuAddress;
		}
		/**
		* 获取： 任务类型
		*/
		public Integer getRenwuTypes() {
			return renwuTypes;
		}
		/**
		* 设置： 任务类型
		*/
		public void setRenwuTypes(Integer renwuTypes) {
			this.renwuTypes = renwuTypes;
		}


			/**
			* 获取： 任务类型的值
			*/
			public String getRenwuValue() {
				return renwuValue;
			}
			/**
			* 设置： 任务类型的值
			*/
			public void setRenwuValue(String renwuValue) {
				this.renwuValue = renwuValue;
			}

		/**
		* 获取： 悬赏金额
		*/
		public Double getRenwuJine() {
			return renwuJine;
		}
		/**
		* 设置： 悬赏金额
		*/
		public void setRenwuJine(Double renwuJine) {
			this.renwuJine = renwuJine;
		}

		/**
		* 获取： 任务热度
		*/
		public Integer getRenwuClicknum() {
			return renwuClicknum;
		}
		/**
		* 设置： 任务热度
		*/
		public void setRenwuClicknum(Integer renwuClicknum) {
			this.renwuClicknum = renwuClicknum;
		}

		/**
		* 获取： 任务要求
		*/
		public String getRenwuContent() {
			return renwuContent;
		}
		/**
		* 设置： 任务要求
		*/
		public void setRenwuContent(String renwuContent) {
			this.renwuContent = renwuContent;
		}
		/**
		* 获取： 任务状态
		*/
		public Integer getRenwuZhuangtaiTypes() {
			return renwuZhuangtaiTypes;
		}
		/**
		* 设置： 任务状态
		*/
		public void setRenwuZhuangtaiTypes(Integer renwuZhuangtaiTypes) {
			this.renwuZhuangtaiTypes = renwuZhuangtaiTypes;
		}


			/**
			* 获取： 任务状态的值
			*/
			public String getRenwuZhuangtaiValue() {
				return renwuZhuangtaiValue;
			}
			/**
			* 设置： 任务状态的值
			*/
			public void setRenwuZhuangtaiValue(String renwuZhuangtaiValue) {
				this.renwuZhuangtaiValue = renwuZhuangtaiValue;
			}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getRenwuDelete() {
			return renwuDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setRenwuDelete(Integer renwuDelete) {
			this.renwuDelete = renwuDelete;
		}


	@Override
	public String toString() {
		return "RenwuCollectionView{" +
			", renwuCollectionValue=" + renwuCollectionValue +
			", renwuName=" + renwuName +
			", renwuUuidNumber=" + renwuUuidNumber +
			", renwuPhoto=" + renwuPhoto +
			", renwuFile=" + renwuFile +
			", zhixingTime=" + DateUtil.convertString(zhixingTime,"yyyy-MM-dd") +
			", renwuAddress=" + renwuAddress +
			", renwuJine=" + renwuJine +
			", renwuClicknum=" + renwuClicknum +
			", renwuContent=" + renwuContent +
			", renwuDelete=" + renwuDelete +
			", jiequyonghuName=" + jiequyonghuName +
			", jiequyonghuPhone=" + jiequyonghuPhone +
			", jiequyonghuIdNumber=" + jiequyonghuIdNumber +
			", jiequyonghuPhoto=" + jiequyonghuPhoto +
			", newMoney=" + newMoney +
			", wanchenglv=" + wanchenglv +
			", jiequyonghuEmail=" + jiequyonghuEmail +
			"} " + super.toString();
	}
}
