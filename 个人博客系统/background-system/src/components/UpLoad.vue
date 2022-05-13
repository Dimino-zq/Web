<template>
  <div>
    <!-- 上传组件标题 -->
    <div class="block">{{ uploadTitle }}</div>
    <!-- 上传控件 action接口 需要授权 必须要带入token headers -->
    <el-upload
      class="avatar-uploader"
      action="/api/upload" 
      :show-file-list="false"
      :on-success="handleAvatarSuccess"  
      :headers="headers"
    >
      <img v-if="value" :src="imageUrl" class="avatar" />
      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>
  </div>
</template>

<script>
import {server_URL} from '@/urlConfig.js'
export default {
  props: ["uploadTitle", "value"],
  computed : {
    imageUrl(){
      if(this.value){
        return server_URL + this.value;
      }
    },
    //完成授权 将token带过去
    headers(){
        return {
            Authorization : 'Bearer ' + localStorage.getItem('adminToken'), // 从本地获取 token，添加到 header 里面
        }
    }
  },
   methods: {
      handleAvatarSuccess(res){
          if(!res.code && res.data){
              // 说明上传成功，
              //服务器给我们返回了图片上传后的服务器地址
              //重写图片值 并且展示
              this.$emit('input', res.data);
          }
      }
  },
}
</script>

<style>
.block {
  margin: 15px 0;
  font-weight: 100;
}
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>