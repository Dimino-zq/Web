<template>
  <div class="app-container">
    <!-- element-ui文档里面可以查询 这个是表单的方法 -->
    <el-table :data="data" style="width: 100%" border>
      <!-- 每一列的格式如下 el-table-column-->
      <el-table-column prop="date" label="序号" width="60" align="center">
        <!-- 每一列的内容 从上到下根据服务器返回结果自动填充-->
        <template slot-scope="scope">{{ scope.$index + 1 }}</template>
      </el-table-column>

      <el-table-column prop="title" label="标题" width="150" align="center">
        <!-- 每一列的内容 从上到下根据服务器返回结果自动填充 
              scope.row.title数据要写成这个样子 因为里面标签
              slot-scope="scope"属性-->
        <template slot-scope="scope">{{ scope.row.title }}</template>
      </el-table-column>
      <el-table-column prop="description" label="描述">
        <template slot-scope="scope">{{ scope.row.description }}</template>
      </el-table-column>
      <el-table-column label="中图预览" align="center">
        <template slot-scope="scope">
          <el-image
            style="width: 100px"
            :src="scope.row.midImg2"
            fit="fill"
          ></el-image>
        </template>
      </el-table-column>
      <el-table-column label="大图预览" align="center">
        <template slot-scope="scope">
          <el-image
            style="width: 100px"
            :src="scope.row.bigImg2"
            fit="fill"
          ></el-image>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <!-- 这个地方 slot-scope="scope" 点击可以获取不同的行的数据 -->
        <template slot-scope="scope">
          <el-tooltip
            class="item"
            effect="dark"
            content="编辑"
            placement="top"
            :hide-after="2000"
          >
            <!-- element-ui文档里面可以查询 这个是按钮的方法 size型号 -->
            <el-button
              type="primary"
              icon="el-icon-edit"
              circle
              size="mini"
              @click="editBannerHandle(scope.row)"
            ></el-button>
          </el-tooltip>
        </template>
      </el-table-column>
    </el-table>

    <!-- 对话框里的内容 v-model 和value有关系 改变的是value-->
    <el-dialog title="请编辑信息" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <!-- 标题 -->
        <el-form-item label="标题">
          <el-input v-model="form.title"></el-input>
        </el-form-item>

        <!-- 描述 -->
        <el-form-item label="描述">
          <el-input v-model="form.description"></el-input>
        </el-form-item>

        <!-- 下面位图片展示区 需要用到Layout栅格布局 一共24列
              12 12 布局 -->
        <el-row>
          <el-col :span="12">
            <el-form-item label="首页中图">
              <!-- 中图 -->
              <Upload v-model="form.midImg" />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="首页中图">
              <!-- 大图 -->
              <Upload v-model="form.bigImg" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="editBannerConfirm"
          >确 定</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getBanner , setBanner } from "@/api/banner.js";
import { server_URL } from "@/urlConfig.js";
import Upload from "@/components/UpLoad.vue"
export default {
  data() {
    return {
      data: [], // 存储数据 130行
      dialogFormVisible: false,// 编辑对话框是否显示
      form: {
        id: "",
        bigImg:"",
        description: "",
        midImg: "",
        title:" "
      },
    };
  },
  components:{
     Upload
  },
  created() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      getBanner().then((res) => {
        console.log(res);
        this.data = res.data;
        //因为返回的数据前面都要拼接本地地址才能能够访问
        for (var item of this.data) {
          item.midImg2 = server_URL + item.midImg;
          item.bigImg2 = server_URL + item.bigImg;
        }
      });
    },
    editBannerHandle(bannerInfo){
      this.form = { ...bannerInfo };
      this.dialogFormVisible = true;
    },
    editBannerConfirm(){
      // 从表单里面拿到用户修改的数据，发送给服务器
      // 因为 api 文档要求三个首页标语都要发送过去，哪怕只改了其中一个
      let arr = [...this.data];
      for(var i=0;i<arr.length;i++){
        if(arr[i].id == this.form.id){
          arr[i] = this.form
        }
      }
      setBanner(arr).then(res=>{
        this.dialogFormVisible = false; // 关闭掉对话框
        this.$message({
          message: '修改成功',
          type: 'success'
        });
        this.fetchData();
      })
    }
  },
 
};
</script>

<style>
</style>
