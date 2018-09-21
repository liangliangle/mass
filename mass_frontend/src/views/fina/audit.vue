<!--模板-->
<template>
  <section>
    <el-table
      v-loading="loading"
      :data="this.request.list"
      style="width: 100%">
      <el-table-column
        prop="name"
        label="名称"
        width="180">
      </el-table-column>
      <el-table-column
        prop="massName"
        label="申请社团">
      </el-table-column>
      <el-table-column
        label="申请时间">
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
          <el-tag v-if="scope.row.status==1" type="warning">待审核</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="操作">
        <template slot-scope="scope">
          <el-button
            @click.native.prevent="detile(scope.row.id)"
            type="text"
            size="small">
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
  import {getNotyet} from "@/service/finaapi";
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
      getNotyet({page: 1}).then(req => {
        this.request = req.data;
        this.loading = false;
      });
    },
    components: {},

    methods: {
      detile(id) {
        this.$router.push({path: "/fina/auditdetail/" + id});
      },
      getpage(evet) {
        this.loading = true;
        getNotyet({page: evet}).then(req => {
          this.request = req.data;
          this.loading = false;
        });
      }
    }
  };
</script>



