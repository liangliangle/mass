<!--模板-->
<template>
  <section>
    <el-table v-loading="loading" :data="this.request.list" style="width: 100%">

      <el-table-column prop="name" label="名称" width="180">
      </el-table-column>
      <el-table-column prop="massName" label="申请社团">
      </el-table-column>
      <el-table-column label="申请时间">
        <template slot-scope="scope">
          {{scope.row.times / 1000 | moment("YYYY-MM-DD HH:mm") }}
        </template>
      </el-table-column>
      <el-table-column
        prop="totalFree"
        label="总额">
      </el-table-column>
      <el-table-column
        prop="status"
        label="状态">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status==1" type="warning">
            待审核
          </el-tag>
          <el-tag v-if="scope.row.status==2" type="danger">待修改</el-tag>
          <el-tag v-if="scope.row.status==3" type="info">待发放</el-tag>
          <el-tag v-if="scope.row.status==4" type="success">完成</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="操作">
        <template slot-scope="scope">
          <el-button v-if="scope.row.status==1 || scope.row.status==2" @click.native.prevent="edit(scope.row.id)"
                     type="text" size="small">
            修改
          </el-button>
          <el-button v-if="scope.row.status==3" @click.native.prevent="settle(scope.row)" type="text" size="small">
            结算
          </el-button>
          <el-button @click.native.prevent="detile(scope.row.id)" type="text" size="small">
            详情
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
          @current-change="getpage"
          @prev-click="getpage"
          @next-click="getpage">
        </el-pagination>
      </el-col>
    </el-row>

  </section>
</template>
<!--JS-->


<script>
  import {mymass, settle} from "@/service/finaapi";
  import moment from "moment";

  export default {
    name: "finaAudit",
    data() {
      return {
        loading: true,
        request: {}
      };
    },
    //加载组件
    mounted() {
      //TODO massId
      let user = JSON.parse(sessionStorage.getItem("user"));
      mymass({
        page: 1,
        massId: user.manageOrg.id
      }).then(req => {
        this.request = req.data;
        this.loading = false;
      });
    },
    components: {},
    methods: {
      detile(id) {
        this.$router.push({
          path: "/fina/detail/" + id
        });
      },
      settle(data) {
        this.$alert("申请名称【" + data.name + "】", "确定发放", {
          confirmButtonText: "确定",
          callback: action => {
            settle(data.id);
            this.getpage(this.request.pageNum);
          }
        });
      },
      edit(id) {
        this.$router.push({
          path: "/fina/edit/" + id
        });
      },
      getpage(evet) {
        this.loading = true;
        mymass({
          page: evet,
          massId: 1
        }).then(req => {
          this.request = req.data;
          this.loading = false;
        });
      }
    }
  };
</script>



