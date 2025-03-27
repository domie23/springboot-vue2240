package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.RenwuEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 任务
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("renwu")
public class RenwuView extends RenwuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 任务类型的值
	*/
	@ColumnInfo(comment="任务类型的字典表值",type="varchar(200)")
	private String renwuValue;
	/**
	* 任务状态的值
	*/
	@ColumnInfo(comment="任务状态的字典表值",type="varchar(200)")
	private String renwuZhuangtaiValue;
	/**
	* 申请状态的值
	*/
	@ColumnInfo(comment="申请状态的字典表值",type="varchar(200)")
	private String renwuYesnoValue;

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
		* 余额
		*/
		@ColumnInfo(comment="余额",type="decimal(10,2)")
		private Double newMoney;
		/**
		* 发布用户邮箱
		*/

		@ColumnInfo(comment="发布用户邮箱",type="varchar(200)")
		private String fabuyonghuEmail;



	public RenwuView() {

	}

	public RenwuView(RenwuEntity renwuEntity) {
		try {
			BeanUtils.copyProperties(this, renwuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
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
	//当前表的
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
	//当前表的
	/**
	* 获取： 申请状态的值
	*/
	public String getRenwuYesnoValue() {
		return renwuYesnoValue;
	}
	/**
	* 设置： 申请状态的值
	*/
	public void setRenwuYesnoValue(String renwuYesnoValue) {
		this.renwuYesnoValue = renwuYesnoValue;
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


	@Override
	public String toString() {
		return "RenwuView{" +
			", renwuValue=" + renwuValue +
			", renwuZhuangtaiValue=" + renwuZhuangtaiValue +
			", renwuYesnoValue=" + renwuYesnoValue +
			", fabuyonghuName=" + fabuyonghuName +
			", fabuyonghuPhone=" + fabuyonghuPhone +
			", fabuyonghuIdNumber=" + fabuyonghuIdNumber +
			", fabuyonghuPhoto=" + fabuyonghuPhoto +
			", newMoney=" + newMoney +
			", fabuyonghuEmail=" + fabuyonghuEmail +
			"} " + super.toString();
	}
}
