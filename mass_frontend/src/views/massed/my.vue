<!--模板-->
<template>
  <section>
    <el-table v-loading="loading" :data="this.request" style="width: 100%">
      <el-table-column prop="name" label="名称">
      </el-table-column>
      <el-table-column prop="petName" label="别名" width="180">
      </el-table-column>
      <el-table-column
        prop="presidentName"
        label="社长">
      </el-table-column>
      <el-table-column
        label="状态">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status==-2" type="info">退出</el-tag>
          <el-tag v-if="scope.row.status==-1" type="warning">待审核</el-tag>
          <el-tag v-if="scope.row.status==0" type="danger">被拒绝</el-tag>
          <el-tag v-if="scope.row.status==1"> 正常 </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="操作">
        <template slot-scope="scope">
          <el-button @click.native.prevent="rmMass(scope.row)"  v-if="scope.row.status==1" type="text" size="small">
            退出
          </el-button>
        </template>
      </el-table-column>
    </el-table>

  </section>
</template>
<!--JS-->


<script>
  import {getMyMass, exitMass} from "@/service/orgapi";
  import moment from "moment";

  export default {
    name: "finaAudit",
    data() {
      return {
        sno: null,
        loading: true,
        request: [],

      };
    },
    //加载组件
    mounted() {
      let user = JSON.parse(sessionStorage.getItem("user"));
      this.sno = user.user.username;
      this.getData()
    },
    components: {},

    methods: {
      getData() {
        getMyMass({username: this.sno}).then(req => {
          this.request = req.data;
          this.loading = false;
        })
      },
      rmMass(data) {
        this.$confirm('确定退出' + data.orgName + '社团, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          exitMass({orgId: data.orgId, username: this.sno}).then(req => {
            this.getData();
          })
        })

      }
    }
  };
</script>



