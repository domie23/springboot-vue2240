package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.RenwuChatEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 任务咨询
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("renwu_chat")
public class RenwuChatView extends RenwuChatEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 状态的值
	*/
	@ColumnInfo(comment="状态的字典表值",type="varchar(200)")
	private String zhuangtaiValue;
	/**
	* 数据类型的值
	*/
	@ColumnInfo(comment="数据类型的字典表值",type="varchar(200)")
	private String renwuChatValue;

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
	//级联表 发布用户
		/**
		* 发布用户姓名
		*/

		@ColumnInfo(comment="发布用户姓名",type="varchar(200)")
		private String fabuyonghuName;
		/**
		* 发布用户手机号
		*/

		@ColumnInfo(comment="发布用户手机号",type="varchar(200)")
		private String fabuyonghuPhone;
		/**
		* 发布用户身份证号
		*/

		@ColumnInfo(comment="发布用户身份证号",type="varchar(200)")
		private String fabuyonghuIdNumber;
		/**
		* 发布用户头像
		*/

		@ColumnInfo(comment="发布用户头像",type="varchar(200)")
		private String fabuyonghuPhoto;
		/**
		* 发布用户邮箱
		*/

		@ColumnInfo(comment="发布用户邮箱",type="varchar(200)")
		private String fabuyonghuEmail;

	//重复字段
			/**
			* 重复字段 的types
			*/
			@ColumnInfo(comment="重复字段 的types",type="Integer")
			private Integer sexTypes;
				@ColumnInfo(comment="重复字段 的值",type="varchar(200)")
				private String sexValue;


	public RenwuChatView() {

	}

	public RenwuChatView(RenwuChatEntity renwuChatEntity) {
		try {
			BeanUtils.copyProperties(this, renwuChatEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 状态的值
	*/
	public String getZhuangtaiValue() {
		return zhuangtaiValue;
	}
	/**
	* 设置： 状态的值
	*/
	public void setZhuangtaiValue(String zhuangtaiValue) {
		this.zhuangtaiValue = zhuangtaiValue;
	}
	//当前表的
	/**
	* 获取： 数据类型的值
	*/
	public String getRenwuChatValue() {
		return renwuChatValue;
	}
	/**
	* 设置： 数据类型的值
	*/
	public void setRenwuChatValue(String renwuChatValue) {
		this.renwuChatValue = renwuChatValue;
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
	//级联表的get和set 发布用户

		/**
		* 获取： 发布用户姓名
		*/
		public String getFabuyonghuName() {
			return fabuyonghuName;
		}
		/**
		* 设置： 发布用户姓名
		*/
		public void setFabuyonghuName(String fabuyonghuName) {
			this.fabuyonghuName = fabuyonghuName;
		}

		/**
		* 获取： 发布用户手机号
		*/
		public String getFabuyonghuPhone() {
			return fabuyonghuPhone;
		}
		/**
		* 设置： 发布用户手机号
		*/
		public void setFabuyonghuPhone(String fabuyonghuPhone) {
			this.fabuyonghuPhone = fabuyonghuPhone;
		}

		/**
		* 获取： 发布用户身份证号
		*/
		public String getFabuyonghuIdNumber() {
			return fabuyonghuIdNumber;
		}
		/**
		* 设置： 发布用户身份证号
		*/
		public void setFabuyonghuIdNumber(String fabuyonghuIdNumber) {
			this.fabuyonghuIdNumber = fabuyonghuIdNumber;
		}

		/**
		* 获取： 发布用户头像
		*/
		public String getFabuyonghuPhoto() {
			return fabuyonghuPhoto;
		}
		/**
		* 设置： 发布用户头像
		*/
		public void setFabuyonghuPhoto(String fabuyonghuPhoto) {
			this.fabuyonghuPhoto = fabuyonghuPhoto;
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
		* 获取： 发布用户邮箱
		*/
		public String getFabuyonghuEmail() {
			return fabuyonghuEmail;
		}
		/**
		* 设置： 发布用户邮箱
		*/
		public void setFabuyonghuEmail(String fabuyonghuEmail) {
			this.fabuyonghuEmail = fabuyonghuEmail;
		}

	//重复字段
			/**
			* 获取： 重复字段 的types
			*/
			public Integer getSexTypes() {
			return sexTypes;
			}
			/**
			* 设置： 重复字段 的types
			*/
			public void setSexTypes(Integer sexTypes) {
			this.sexTypes = sexTypes;
			}
				public String getSexValue() {
				return sexValue;
				}
				public void setSexValue(String sexValue) {
				this.sexValue = sexValue;
				}

	@Override
	public String toString() {
		return "RenwuChatView{" +
			", zhuangtaiValue=" + zhuangtaiValue +
			", renwuChatValue=" + renwuChatValue +
			", jiequyonghuName=" + jiequyonghuName +
			", jiequyonghuPhone=" + jiequyonghuPhone +
			", jiequyonghuIdNumber=" + jiequyonghuIdNumber +
			", jiequyonghuPhoto=" + jiequyonghuPhoto +
			", newMoney=" + newMoney +
			", wanchenglv=" + wanchenglv +
			", jiequyonghuEmail=" + jiequyonghuEmail +
			", fabuyonghuName=" + fabuyonghuName +
			", fabuyonghuPhone=" + fabuyonghuPhone +
			", fabuyonghuIdNumber=" + fabuyonghuIdNumber +
			", fabuyonghuPhoto=" + fabuyonghuPhoto +
			", newMoney=" + newMoney +
			", fabuyonghuEmail=" + fabuyonghuEmail +
			"} " + super.toString();
	}
}
