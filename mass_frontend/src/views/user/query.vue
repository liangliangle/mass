<!--模板-->
<template>
  <section>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>用户管理</span>
      </div>
      <el-table v-loading="loading" :data="this.request.list" style="width: 100%">
        <el-table-column prop="username" label="学号" width="180">
        </el-table-column>
        <el-table-column prop="name" label="姓名">
        </el-table-column>
        <el-table-column prop="sex" label="性别">
        </el-table-column>
        <el-table-column prop="grade" label="班级">
        </el-table-column>
        <el-table-column prop="locked" label="状态">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="text" size="small">
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
            :total="this.request.count"
            @current-change="getpage"
            @prev-click="getpage"
            @next-click="getpage">
          </el-pagination>
        </el-col>
      </el-row>
    </el-card>
  </section>
</template>
<!--JS-->
<script>
  import {getAll} from "@/service/userapi";
  import moment from "moment";

  export default {
    name: "finaAudit",
    data() {
      return {
        loading: true,
        request: {},
        dialogVisible: false,
        query: {
          classes: null,
          name: null,
          page: 0,
          status: null,
          username: null

        }
      };
    },
    //加载组件
    mounted() {
      getAll(this.query).then(req => {
        this.request = req.data;
        this.loading = false;
      });
    },
    components: {},
    methods: {
      getpage(evet) {
        this.loading = true;
        this.query.page = evet;
        getAll(this.query).then(req => {
          this.request = req.data;
          this.loading = false;
        });
      }
    }
  };
</script>



