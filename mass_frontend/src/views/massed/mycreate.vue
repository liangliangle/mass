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
        label="发起人">
      </el-table-column>
      <el-table-column
        label="状态">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.stastus==0" type="warning">待审核</el-tag>
          <el-tag v-if="scope.row.stastus==1" type="danger">拒绝</el-tag>
          <el-tag v-if="scope.row.stastus==2" type="success">已通过</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="操作">
        <template slot-scope="scope">
          <el-button @click.native.prevent="detiail(scope.row)" type="text" size="small">
            详情
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="申请详情" :visible.sync="dialogVisible">
      <el-row :gutter="20">
        <el-col :span="24" style="margin-top:20px">社团名称: <b>{{currentMass.name}}</b></el-col>
        <el-col :span="24" style="margin-top:20px">别名: <b>{{currentMass.petName}}</b></el-col>
        <el-col :span="24" style="margin-top:20px">申请人: <b>{{currentMass.presidentName}}</b></el-col>
        <el-col :span="24" style="margin-top:20px">申请人学号: <b>{{currentMass.presidentSno}}</b></el-col>
        <el-col :span="24" style="margin-top:20px">联合创建人: <b>{{currentMass.usno1}}</b></el-col>
        <el-col :span="24" style="margin-top:20px">联合创建人: <b>{{currentMass.usno2}}</b></el-col>
        <el-col :span="24" style="margin-top:20px">联合创建人: <b>{{currentMass.usno3}}</b></el-col>
        <el-col :span="24" style="margin-top:20px">正文: <span v-html="compiledMarkdown"></span></el-col>
      </el-row>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </div>
    </el-dialog>
  </section>
</template>
<!--JS-->


<script>
  import {mycreate} from "@/service/orgapi";
  import moment from "moment";

  export default {
    name: "finaAudit",
    data() {
      return {
        sno: null,
        dialogVisible: false,
        loading: true,
        query: {
          offset: 0,
          limit: 10
        },
        currentMass: {},
        request: []
      };
    },
    computed: {
      compiledMarkdown: function () {
        if (this.currentMass.reason) {
          return marked(this.currentMass.reason, {sanitize: true});
        }
      }
    },
    //加载组件
    mounted() {
      let user = JSON.parse(sessionStorage.getItem("user"));
      this.sno = user.user.username;
      mycreate({username: this.sno}).then(req => {
        this.request = req.data;
        this.loading = false;
      })
    },
    components: {},

    methods: {
      detiail(data) {
        this.dialogVisible = true;
        this.currentMass = data;
      }

    }
  };
</script>



