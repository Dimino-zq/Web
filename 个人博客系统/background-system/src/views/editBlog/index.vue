<template>
  <div class="app-container">
    <!-- 文章标题 -->
    <div class="block">文章标题</div>
    <div style="margin-bottom: 15px">
      <el-input v-model="form.title" placeholder="请输入文章标题"></el-input>
    </div>

    <!-- 文章编辑 -->
    <div class="block">文章编辑</div>
    <editor
      ref="toastuiEditor"
      :initialValue="form.editorText"
      height="600px"
    />

    <!-- 文章描述 -->
    <div class="block">文章描述</div>
    <el-input type="textarea" v-model="form.description" :rows="6"></el-input>

    <!-- 文章头图 -->
    <UpLoad uploadTitle="文章头图" v-model="form.thumb" />

    <!-- 选择分类 -->
    <div class="block">选择分类</div>
    <el-select
      v-model="form.select"
      slot="prepend"
      placeholder="请选择文章分类"
      @change="changeHandle"
    >
      <el-option
        v-for="item in blogType"
        :key="item.id"
        :label="item.name"
        :value="item.id"
      ></el-option>
    </el-select>

    <div>
      <!-- 发布按钮 -->
      <el-button
        type="primary"
        style="margin-top: 15px"
        @click="editArticleHandle"
        >确认修改</el-button
      >
    </div>
  </div>
</template>

<script>
import "@toast-ui/editor/dist/toastui-editor.css";
import { Editor } from "@toast-ui/vue-editor";
import UpLoad from "@/components/UpLoad.vue";
import { getBlogType } from "@/api/blogType.js";
import { editBlog, findOneBlog } from '@/api/blog.js'
export default {
  data() {
    return {
      id : null, // 存储传递过来的 id
      form: {
        title: "", // 文章标题
        editorText: "", // 用户编辑的内容
        description: "", // 文章的描述
        thumb: "", // 文章的图片
        select: "", // 选择分类
      },
      blogType: [], // 存放博客分类
      imageUrl: "", // 图片在服务器上面的地址
    };
  },
  created() {
    // 一进来的时候，就需要拿取分类的数据
    getBlogType().then(({ data }) => {
      this.blogType = data;
    });

    // 一进来的时候，就拿到传递过来的 id，根据这个 id 获取到这篇文章的内容，回填到表单
    this.id = this.$route.params.id;
    findOneBlog(this.id).then(({data})=>{
        // 接下来，将这个内容回填至表单
        this.form = data;
        this.form.select = data.category === null ? '' : data.category.id; 
        this.$refs.toastuiEditor.invoke('setHTML', data.htmlContent);
    })

  },
  components: {
    editor: Editor,
    UpLoad,
  },
  methods: {
    editArticleHandle() {
      // 添加文章的业务逻辑 1. 获取表单内容   2. 发送请求

      let html = this.$refs.toastuiEditor.invoke("getHTML");
      let markdown = this.$refs.toastuiEditor.invoke("getMarkdown");

      // 接下来，我们来组装要传递给服务器的对象

      let obj = {
        title: this.form.title,
        description: this.form.description,
        createDate: new Date().getTime(),
        categoryId: this.form.select,
        // toc 传递一个空数组过去，之后在服务器根据 markdown 的内容来生成 toc 目录
        toc: [],
        htmlContent: html,
        thumb: this.form.thumb,
        markdownContent : markdown
      };


      // 对象组装好以后，就提交给服务器
      if(obj.title && obj.description && obj.htmlContent && obj.categoryId){
        editBlog({id : this.form.id, data : obj}).then(()=>{
            this.$router.push('/blogList'); // 跳转到文章列表
            this.$message.success('文章修改成功');
        })
      } else {
          this.$message.error('请填写所有内容');
      }
    },
    changeHandle(){
        //这个是下拉框的一个bug 需要调用这个方法强制更新一下即可
        this.$forceUpdate();
    }
  },
};
</script>

<style lang="scss" scoped>
.block {
  margin: 15px 0;
  font-weight: 100;
}
</style>