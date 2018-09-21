<!--模板-->
<template>
  <section>
    <el-table v-loading="loading" :data="this.request.list" style="width: 100%">
      <el-table-column prop="name" label="名称">
      </el-table-column>
      <el-table-column prop="range" label="通知范围" width="180">
      </el-table-column>
      <el-table-column label="开始时间">
        <template slot-scope="scope">
          {{scope.row.startTime / 1000 | moment("YYYY-MM-DD HH:mm") }}
        </template>
      </el-table-column>
      <el-table-column label="结束时间">
        <template slot-scope="scope">
          {{scope.row.endTime / 1000 | moment("YYYY-MM-DD HH:mm") }}
        </template>
      </el-table-column>
      <el-table-column
        prop="userName"
        label="创建人">
      </el-table-column>
      <el-table-column
        prop="status"
        label="状态">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status==1" type="warning">
            未开始
          </el-tag>
          <el-tag v-if="scope.row.status==2" type="danger">已开始</el-tag>
          <el-tag v-if="scope.row.status==3" type="info">已结束</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="操作">
        <template slot-scope="scope">

          <el-button @click.native.prevent="detile(scope.row)" type="text" size="small">
            详情
          </el-button>
          <el-button v-if="scope.row.status==2||scope.row.status==3" @click.native.prevent="result(scope.row)"
                     type="text" size="small">
            通知结果
          </el-button>
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

  </section>
</template>
<!--JS-->


<script>
  import {mycreate} from "@/service/noticeapi";
  import moment from "moment";

  export default {
    name: "finaAudit",
    data() {
      return {
        loading: true,
        request: {},
        query: {
          page: 1,
          sno: null
        }
      };
    },
    //加载组件
    mounted() {
      let user = JSON.parse(sessionStorage.getItem("user"));
      this.query.sno = user.user.username;
      mycreate(this.query).then(req => {
        this.request = req.data;
        this.loading = false;
      }).catch(err => {

      })
    },
    components: {},

    methods: {
      result(event) {
        this.$router.push({path: "/notice/result/" + event.id});
      },
      detile(event) {
        this.$router.push({path: "/notice/detile/" + event.id});
      },
      getPage(page) {
        this.loading = true;
        this.query.page = page;
        mycreate(this.query).then(req => {
          this.request = req.data;
          this.loading = false;
        }).catch(err => {

        })
      }
    }
  };
</script>



