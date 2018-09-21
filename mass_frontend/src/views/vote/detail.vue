<!--模板-->
<template>
  <section>

    <el-row :gutter="20">
      <el-col :span="8" style="margin-top:20px">投票名称:{{vote.name}}</el-col>
      <el-col :span="8" style="margin-top:20px">投票范围:{{vote.range}}</el-col>
      <el-col :span="8" style="margin-top:20px">可投数量:{{vote.voteNum}}</el-col>
      <el-col :span="8" style="margin-top:20px">开始时间:{{vote.startTime / 1000 | moment("YYYY-MM-DD HH:mm") }}</el-col>
      <el-col :span="8" style="margin-top:20px">结束时间:{{vote.endTime / 1000 | moment("YYYY-MM-DD HH:mm") }}</el-col>
      <el-col :span="16" style="margin-top:20px">创建人:{{vote.stuName}}</el-col>

    </el-row>
    <el-card class="box-card" style="margin-top:20px">
      <div slot="header" class="clearfix">
        <span>选项列表</span>
      </div>
      <div v-for="(option,index) in vote.optionsDto">
        <el-row :gutter="20">
          <el-col :span="24" style="margin-top:20px">选项{{index+1}}:  {{option.thetext}}</el-col>
        </el-row>
      </div>
    </el-card>
    <el-row justify="center" style="margin-top:20px">
      <el-col :span="4">
        <el-button @click="perv" >上一步</el-button>
      </el-col>
    </el-row>
  </section>
</template>
<!--JS-->


<script>
  import {getById} from "@/service/voteapi";
  import moment from "moment";

  export default {
    name: "finaAudit",
    data() {
      return {
        loading: true,
        vote: {}
      };
    },
    //加载组件
    mounted() {
      this.loading = true;
      if (this.$route.params.id) {
        let id = this.$route.params.id;
        getById(id).then(req => {
          this.vote = req.data;
          this.loading = false;
        })
      }
    },
    components: {},
    methods: {
      perv() {
        this.$router.go(-1);
      }
    }
  };
</script>



