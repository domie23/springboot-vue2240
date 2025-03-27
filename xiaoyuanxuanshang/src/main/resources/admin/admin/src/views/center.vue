<template>
  <div>
    <el-form
      class="detail-form-content"
      ref="ruleForm"
      :model="ruleForm"
      label-width="auto"
    >  
     <el-row>
                    <el-col :span="12">
           <el-form-item v-if="flag=='jiequyonghu'"  label='接取用户姓名' prop="jiequyonghuName">
               <el-input v-model="ruleForm.jiequyonghuName"  placeholder='接取用户姓名' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='jiequyonghu'"  label='接取用户手机号' prop="jiequyonghuPhone">
               <el-input v-model="ruleForm.jiequyonghuPhone"  placeholder='接取用户手机号' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='jiequyonghu'"  label='接取用户身份证号' prop="jiequyonghuIdNumber">
               <el-input v-model="ruleForm.jiequyonghuIdNumber"  placeholder='接取用户身份证号' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
             <el-form-item v-if="flag=='jiequyonghu'" label='接取用户头像' prop="jiequyonghuPhoto">
                 <file-upload
                         tip="点击上传照片"
                         action="file/upload"
                         :limit="3"
                         :multiple="true"
                         :fileUrls="ruleForm.jiequyonghuPhoto?$base.url+ruleForm.jiequyonghuPhoto:''"
                         @change="jiequyonghuPhotoUploadChange"
                 ></file-upload>
             </el-form-item>
         </el-col>
         <el-col :span="12">
             <el-form-item v-if="flag=='jiequyonghu'"  label='余额' prop="newMoney">
                 <el-input v-model="ruleForm.newMoney"  placeholder='余额' disabled style="width: 100px"></el-input>
                 <a id="btn-recharge" @click="chongzhi" href="javascript:void(0)">点我充值</a>
                 <a id="btn-recharge" @click="tixian" href="javascript:void(0)">点我提现</a>
             </el-form-item>
         </el-col>
         <el-col :span="12">
           <el-form-item v-if="flag=='jiequyonghu'"  label='完成率(百分比)' prop="wanchenglv">
               <el-input v-model="ruleForm.wanchenglv"  placeholder='完成率(百分比)' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='jiequyonghu'"  label='接取用户邮箱' prop="jiequyonghuEmail">
               <el-input v-model="ruleForm.jiequyonghuEmail"  placeholder='接取用户邮箱' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='fabuyonghu'"  label='发布用户姓名' prop="fabuyonghuName">
               <el-input v-model="ruleForm.fabuyonghuName"  placeholder='发布用户姓名' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='fabuyonghu'"  label='发布用户手机号' prop="fabuyonghuPhone">
               <el-input v-model="ruleForm.fabuyonghuPhone"  placeholder='发布用户手机号' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='fabuyonghu'"  label='发布用户身份证号' prop="fabuyonghuIdNumber">
               <el-input v-model="ruleForm.fabuyonghuIdNumber"  placeholder='发布用户身份证号' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
             <el-form-item v-if="flag=='fabuyonghu'" label='发布用户头像' prop="fabuyonghuPhoto">
                 <file-upload
                         tip="点击上传照片"
                         action="file/upload"
                         :limit="3"
                         :multiple="true"
                         :fileUrls="ruleForm.fabuyonghuPhoto?$base.url+ruleForm.fabuyonghuPhoto:''"
                         @change="fabuyonghuPhotoUploadChange"
                 ></file-upload>
             </el-form-item>
         </el-col>
         <el-col :span="12">
             <el-form-item v-if="flag=='fabuyonghu'"  label='余额' prop="newMoney">
                 <el-input v-model="ruleForm.newMoney"  placeholder='余额' disabled style="width: 100px"></el-input>
                 <a id="btn-recharge" @click="chongzhi" href="javascript:void(0)">点我充值</a>
               &nbsp;&nbsp;&nbsp;
                 <a id="btn-recharge" @click="tixian" href="javascript:void(0)">点我提现</a>
             </el-form-item>
         </el-col>
         <el-col :span="12">
           <el-form-item v-if="flag=='fabuyonghu'"  label='发布用户邮箱' prop="fabuyonghuEmail">
               <el-input v-model="ruleForm.fabuyonghuEmail"  placeholder='发布用户邮箱' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-form-item v-if="flag=='users'" label="用户名" prop="username">
             <el-input v-model="ruleForm.username"
                       placeholder="用户名"></el-input>
         </el-form-item>
         <el-col :span="12">
             <el-form-item v-if="flag!='users' &&( 1==2|| flag=='jiequyonghu'
|| flag=='fabuyonghu')"  label="性别" prop="sexTypes">
                 <el-select v-model="ruleForm.sexTypes" placeholder="请选择性别">
                     <el-option
                             v-for="(item,index) in sexTypesOptions"
                             v-bind:key="item.codeIndex"
                             :label="item.indexName"
                             :value="item.codeIndex">
                     </el-option>
                 </el-select>
             </el-form-item>
         </el-col>
         <el-col :span="24">
             <el-form-item>
                 <el-button type="primary" @click="onUpdateHandler">修 改</el-button>
             </el-form-item>
         </el-col>
     </el-row>
    </el-form>
  </div>
</template>
<script>
// 数字，邮件，手机，url，身份证校验
import { isNumber,isIntNumer,isEmail,isMobile,isPhone,isURL,checkIdCard } from "@/utils/validate";

export default {
  data() {
    return {
        ruleForm: {},
        flag: '',
        usersFlag: false,
        // sexTypesOptions : [],
// 注册表 接取用户
    // 注册的类型字段 接取用户
        // 性别
        sexTypesOptions : [],
// 注册表 发布用户
    // 注册的类型字段 发布用户
        // 性别
        sexTypesOptions : [],
    };
  },
  mounted() {
    //获取当前登录用户的信息
    var table = this.$storage.get("sessionTable");
    this.sessionTable = this.$storage.get("sessionTable");
    this.role = this.$storage.get("role");
    if (this.role != "管理员"){
    }

    this.flag = table;
    this.$http({
      url: `${this.$storage.get("sessionTable")}/session`,
      method: "get"
    }).then(({ data }) => {
      if (data && data.code === 0) {
        this.ruleForm = data.data;
// 注册表 接取用户
// 注册表 发布用户
      } else {
        this.$message.error(data.msg);
      }
    });

// 注册表 接取用户 的级联表
// 注册表 发布用户 的级联表

      this.$http({
          url: `dictionary/page?page=1&limit=100&sort=&order=&dicCode=sex_types`,
          method: "get"
      }).then(({ data }) => {
          if (data && data.code === 0) {
              this.sexTypesOptions = data.data.list;
          } else {
              this.$message.error(data.msg);
          }
      });
  },
  methods: {
    chongzhi() {
      this.$router.replace({ path: "/pay" });
    },
    tixian() {
      let _this=this;
      this.$confirm(`确定要提现么?`, "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
      }).then(() => {
          _this.ruleForm.newMoney=0;
          _this.$http({
              url: `${this.$storage.get("sessionTable")}/update`,
              method: "post",
              data: this.ruleForm
          }).then(({ data }) => {
              if (data && data.code === 0) {
                  _this.$message({message: "提现成功",type: "success",duration: 1500,onClose: () => {}});
              } else {this.$message.error(data.msg);}
          });
      });
    },
    jiequyonghuPhotoUploadChange(fileUrls) {
        this.ruleForm.jiequyonghuPhoto = fileUrls;
    },
    fabuyonghuPhotoUploadChange(fileUrls) {
        this.ruleForm.fabuyonghuPhoto = fileUrls;
    },


    onUpdateHandler() {
                         if((!this.ruleForm.jiequyonghuName)&& 'jiequyonghu'==this.flag){
                             this.$message.error('接取用户姓名不能为空');
                             return
                         }

                             if( 'jiequyonghu' ==this.flag && this.ruleForm.jiequyonghuPhone&&(!isMobile(this.ruleForm.jiequyonghuPhone))){
                                 this.$message.error(`手机应输入手机格式`);
                                 return
                             }
                         if((!this.ruleForm.jiequyonghuIdNumber)&& 'jiequyonghu'==this.flag){
                             this.$message.error('接取用户身份证号不能为空');
                             return
                         }

                         if((!this.ruleForm.jiequyonghuPhoto)&& 'jiequyonghu'==this.flag){
                             this.$message.error('接取用户头像不能为空');
                             return
                         }

                         if((!this.ruleForm.wanchenglv)&& 'jiequyonghu'==this.flag){
                             this.$message.error('完成率(百分比)不能为空');
                             return
                         }

                             if( 'jiequyonghu' ==this.flag && this.ruleForm.jiequyonghuEmail&&(!isEmail(this.ruleForm.jiequyonghuEmail))){
                                 this.$message.error(`邮箱应输入邮箱格式`);
                                 return
                             }
                         if((!this.ruleForm.fabuyonghuName)&& 'fabuyonghu'==this.flag){
                             this.$message.error('发布用户姓名不能为空');
                             return
                         }

                             if( 'fabuyonghu' ==this.flag && this.ruleForm.fabuyonghuPhone&&(!isMobile(this.ruleForm.fabuyonghuPhone))){
                                 this.$message.error(`手机应输入手机格式`);
                                 return
                             }
                         if((!this.ruleForm.fabuyonghuIdNumber)&& 'fabuyonghu'==this.flag){
                             this.$message.error('发布用户身份证号不能为空');
                             return
                         }

                         if((!this.ruleForm.fabuyonghuPhoto)&& 'fabuyonghu'==this.flag){
                             this.$message.error('发布用户头像不能为空');
                             return
                         }

                             if( 'fabuyonghu' ==this.flag && this.ruleForm.fabuyonghuEmail&&(!isEmail(this.ruleForm.fabuyonghuEmail))){
                                 this.$message.error(`邮箱应输入邮箱格式`);
                                 return
                             }
        if((!this.ruleForm.sexTypes) && (1==2|| this.flag=='jiequyonghu'
|| this.flag=='fabuyonghu')){
            this.$message.error('性别不能为空');
            return
        }
      if('users'==this.flag && this.ruleForm.username.trim().length<1) {
        this.$message.error(`用户名不能为空`);
        return	
      }
      this.$http({
        url: `${this.$storage.get("sessionTable")}/update`,
        method: "post",
        data: this.ruleForm
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message({
            message: "修改信息成功",
            type: "success",
            duration: 1500,
            onClose: () => {
            }
          });
        } else {
          this.$message.error(data.msg);
        }
      });
    }
  }
};
</script>
<style lang="scss" scoped>
</style>
