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
        <el-col :span="8" style="margin-top:20px">通知时间:<b>{{notice.startTime/1000 | moment("YYYY-M-DD HH:mm") }}-{{notice.startTime/1000 | moment("YYYY-M-DD HH:mm") }}</b>
        </el-col>
        <el-col :span="8" style="margin-top:20px">创建人:<b>{{notice.name}}</b></el-col>
        <el-col :span="8" style="margin-top:20px">通知汇总:<b>{{count.readCount}}/{{count.total}}</b>(已读/总数)</el-col>
      </el-row>
    </el-card>
    <el-table v-loading="loading" :data="this.request.list" style="width: 100%">
      <el-table-column prop="sno" label="学号">
      </el-table-column>
      <el-table-column prop="username" label="用户">
      </el-table-column>
      <el-table-column label="阅读时间">
        <template slot-scope="scope">
          {{scope.row.readTime / 1000 | moment("YYYY-MM-DD HH:mm") }}
        </template>
      </el-table-column>
      <el-table-column
        prop="status"
        label="状态">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.readStatus==0" type="warning">未读</el-tag>
          <el-tag v-if="scope.row.readStatus==1" type="danger">已读</el-tag>
        </template>
      </el-table-column>
    </el-table>
    <el-row justify="center" style="margin-top:10px">
      <el-col :span="4">
      </el-col>
      <el-col :span="4" :offset="16">
        <el-pagination
          layout="prev, pager, next"
          :total="request.total"
          @current-change="getPage"
          @prev-click="getPage"
          @next-click="getPage">
        </el-pagination>
      </el-col>
    </el-row>
    <el-row justify="center" style="margin-top:20px">
      <el-col :span="4">
        <el-button @click='prev'>上一步</el-button>
      </el-col>
    </el-row>
  </section>
</template>
<!--JS-->


<script>
  import {getById, getResult, countResult} from "@/service/noticeapi";
  import moment from "moment";

  export default {
    name: "finaAudit",
    data() {
      return {
        loading: true,
        request: {},
        notice: {},
        query: {
          page: 1,
          sno: null
        },
        count: {}
      };
    },
    //加载组件
    mounted() {
      if (this.$route.params.id) {
        let id = this.$route.params.id;
        getById({id: id}).then(req => {
          this.notice = req.data;
          getResult({noticeId: id, page: 1}).then(req => {
            this.request = req.data;
            this.loading = false;
          })
        });
        countResult({noticeId: id}).then(req => {
          this.count = req.data;
        })

      }
    },
    components: {},

    methods: {
      prev() {
        this.$router.go(-1);
      },
      getPage(event) {
        getResult({noticeId: id, page: even}).then(req => {
          this.request = req.data;
          this.loading = false;
        })
      }
    }
  };
</script>



