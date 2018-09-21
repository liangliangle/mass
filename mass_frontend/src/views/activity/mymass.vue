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
        prop="sponsorMass"
        label="主办社团">
      </el-table-column>
           <el-table-column
        label="举办时间">
        <template slot-scope="scope">
        {{scope.row.holdTime/1000 | moment("YYYY-MM-DD HH:mm") }}
        </template>
      </el-table-column>
           <el-table-column
        prop="type"
        label="活动类型">
      </el-table-column>
           <el-table-column
        prop="status"
        label="状态">
         <template slot-scope="scope">
           <el-tag v-if="scope.row.status==0" type="danger">终止</el-tag>
<el-tag v-if="scope.row.status==1" type="warning">待审核</el-tag>
<el-tag v-if="scope.row.status==2" type="info">待修改</el-tag>
<el-tag v-if="scope.row.status==3" >准备中</el-tag>
<el-tag v-if="scope.row.status==4" type="success">结束</el-tag>
  </template>
      </el-table-column>
           <el-table-column
        label="操作">
         <template slot-scope="scope">
              <el-button
          @click="edit(scope.row.id)"
          type="text"
          size="small"   v-if="scope.row.status==1 ||scope.row.status==2" >
          修改
        </el-button>
              <el-button
          @click="detile(scope.row.id)"
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
    @next-click="getpage"
    >
  </el-pagination>
          </el-col>
        </el-row>

  </section>
</template>
<!--JS-->


<script>
import { mymass } from "@/service/activityapi";
import moment from "moment";

export default {
  name: "activity",
  data() {
    return {
      mass: [],
      loading: true,
      request: {}
    };
  },
  //加载组件
  mounted() {
    let user = JSON.parse(sessionStorage.getItem("user"));
    if (user.manageOrg) {
      this.mass.push(user.manageOrg.name);
    } else {
      this.mass = ["全部社团"];
    }
    mymass({ page: 1, mass: this.mass[0] }).then(req => {
      this.request = req.data;
      this.loading = false;
    });
  },
  components: {},

  methods: {
    detile(id) {
      this.$router.push({ path: "/activity/detail/" + id });
    },
    edit(id) {
      this.$router.push({ path: "/activity/edit/" + id });
    },
    getpage(evet) {
      this.loading = true;
      mymass({ page: evet, mass: this.mass[0] }).then(req => {
        this.request = req.data;
        this.loading = false;
      });
    }
  }
};
</script>



