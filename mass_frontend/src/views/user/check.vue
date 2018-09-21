<!--模板-->
<template>
  <section>
    <el-table v-loading="loading" :data="this.request.list" style="width: 100%">
      <el-table-column prop="username" label="学号" width="180">
      </el-table-column>
      <el-table-column prop="name" label="姓名">
      </el-table-column>
      <el-table-column prop="sex" label="性别">
      </el-table-column>
      <el-table-column prop="grade" label="班级">
      </el-table-column>
      <el-table-column label="状态">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status==-1" type="danger">待审核</el-tag>
          <el-tag v-if="scope.row.status==1" type="info">待审核</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="check(scope.row,1)">
            通过
          </el-button>
          <el-button type="text" size="small" @click="check(scope.row,0)">
            拒绝
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
          :total="this.request.total"
          @current-change="getpage"
          @prev-click="getpage"
          @next-click="getpage"
        >
        </el-pagination>
      </el-col>
    </el-row>
  </section>
</template>
<!--JS-->


<script>
  import {getCheckUser, checkUser} from "@/service/orgapi";
  import moment from "moment";

  export default {
    name: "finaAudit",
    data() {
      return {
        loading: true,
        request: {},
        query: {
          orgId: null,
          page:1
        }
      };
    },
    //加载组件
    mounted() {
      let user = JSON.parse(sessionStorage.getItem("user"));
      this.query.username = user.user.username;
      this.query.orgId = user.manageOrg.id;
      this.queryTO();
    },
    components: {},

    methods: {
      queryTO() {
        getCheckUser(this.query).then(req => {
          this.request = req.data;
          this.loading = false;
        })
      },
      getpage(event) {
        this.query.page = event;
        this.queryTO();
      },
      check(data, stauts) {
        let tit = stauts == 0 ? '拒绝' : '同意'
        this.$confirm(tit + data.name + '加入社团?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          checkUser({id: data.id, status: stauts}).then(req => {
            this.queryTO();
          })
        })

      }
    }
  };
</script>



