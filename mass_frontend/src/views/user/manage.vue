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
      <el-table-column label="状态">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status==0" type="warning">待审核</el-tag>
          <el-tag v-if="scope.row.status==0" type="danger">退出</el-tag>
          <el-tag v-if="scope.row.status==1">正常</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="detail(scope.row)">
            详情
          </el-button>
          <el-button type="text" size="small" @click="rmUser(scope.row)">
            踢出
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
          @next-click="getpage">
        </el-pagination>
      </el-col>
    </el-row>
    <el-dialog title="用户详情" :visible.sync="dialogDetailVisible">
      <el-row :gutter="20">
        <el-col :span="24" style="margin-top:20px">学号:{{userinfo.username}}</el-col>
        <el-col :span="24" style="margin-top:20px">姓名:{{userinfo.name}}</el-col>
        <el-col :span="24" style="margin-top:20px">性别:{{userinfo.sex}}</el-col>
        <el-col :span="24" style="margin-top:20px">班级:{{userinfo.grade}}</el-col>
        <el-col :span="24" style="margin-top:20px">邮箱:{{userinfo.email}}</el-col>
        <el-col :span="24" style="margin-top:20px">手机号:{{userinfo.phone}}</el-col>
        <el-col :span="24" style="margin-top:20px">已加入社团:
          <el-tag v-for="(tag,index) in userinfo.sysOrganizations"
                  :key="index" style="margin-left: 5px">
            {{tag.name}}
          </el-tag>
        </el-col>
      </el-row>
    </el-dialog>
  </section>
</template>
<!--JS-->


<script>
  import {getUser, exitMass} from "@/service/orgapi";
  import { getDetail} from "@/service/userapi";
  import moment from "moment";

  export default {
    name: "finaAudit",
    data() {
      return {
        loading: true,
        request: {},
        query: {
          orgId: null,
          page: 1
        },
        dialogDetailVisible: false,
        userinfo: {}
      };
    },
    //加载组件
    mounted() {
      let user = JSON.parse(sessionStorage.getItem("user"));
      this.query.orgId = user.manageOrg.id;
      this.getData();
    },
    components: {},
    methods: {
      rmUser(data) {

      },
      detail(data) {

      },
      getData() {
        this.loading = true;
        getUser(this.query).then(req => {
          this.request = req.data;
          this.loading = false;
        })
      },
      getpage(event) {
        this.query.page = event;
        this.getData();
      },
      detail(data) {
        this.dialogDetailVisible = true;
        getDetail({username: data.username}).then(req => {
          this.userinfo = req.data;
        })
      },
      rmUser(data) {
        this.$confirm('是否将' + data.name + '移出社团, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          exitMass({orgId: this.query.orgId, username: data.username}).then(req => {
            this.getData();
          })
        })

      }
    }
  };
</script>



