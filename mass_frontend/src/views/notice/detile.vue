<!--模板-->
<template>
  <section>
    <el-card style="margin-top:20px" v-loading="loading">
      <div slot="header">
        <span>主要信息</span>
      </div>
      <el-row :gutter="20">
        <el-col :span="8" style="margin-top:20px">通知名称:<b>{{notice.name}}</b></el-col>
        <el-col :span="8" style="margin-top:20px">通知范围:<b>{{notice.range}}</b></el-col>
        <el-col :span="8" style="margin-top:20px">通知时间:<b>{{notice.startTime / 1000 | moment("YYYY-M-DD HH:mm")
          }}-{{notice.startTime / 1000 | moment("YYYY-M-DD HH:mm") }}</b></el-col>
        <el-col :span="8" style="margin-top:20px">创建人:<b>{{notice.userName}}</b></el-col>
      </el-row>
    </el-card>
    <el-card style="margin-top:20px" v-loading="loading">
      <div slot="header">
        <span>通知正文</span>
      </div>
      <mavon-editor v-model="notice.thetext" ref=md  :editable=false :toolbarsFlag=false
                    :subfield=false></mavon-editor>
    </el-card>
    <el-row justify="center" style="margin-top:20px">
      <el-col :span="4">
        <el-button @click='prev'>上一步</el-button>
      </el-col>
    </el-row>
  </section>
</template>
<!--JS-->


<script>
  import {getById} from "@/service/noticeapi";
  import showdown from "showdown";

  export default {
    name: "activityDetail",
    computed: {
      compiledMarkdown: function () {
        if (this.notice.thetext) {
          let converter = new showdown.Converter();
          return converter.makeHtml(this.notice.thetext);
        }
      }
    },
    //加载组件
    components: {},
    data() {
      return {
        mass: null,
        loading: true,
        submitLoading: false,
        notice: {}
      };
    },
    //加载组件
    mounted() {
      this.loading = true;
      let user = JSON.parse(sessionStorage.getItem("user"));
      if (this.$route.params.id) {
        let id = this.$route.params.id;
        let sno = user.user.username;
        getById({id: id, sno: sno}).then(req => {
          this.notice = req.data;
          this.loading = false;
        });
      }
    },
    methods: {
      prev() {
        this.$router.go(-1);
      }
    }
  };
</script>



