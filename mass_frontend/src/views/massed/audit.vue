<!--模板-->
<template>
  <section>
    <el-table v-loading="loading" :data="this.request.list" style="width: 100%">
      <el-table-column prop="name" label="名称">
      </el-table-column>
      <el-table-column prop="petName" label="别名" width="180">
      </el-table-column>
      <el-table-column
        prop="presidentName"
        label="社长">
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
        <el-button type="warning" @click="auditTo(1)">拒绝</el-button>
        <el-button type="primary" @click="auditTo(2)">确 定</el-button>
      </div>
    </el-dialog>
  </section>
</template>
<!--JS-->


<script>
  import {getAudit,checkMass} from "@/service/orgapi";
  import moment from "moment";
  import showdown from "showdown";
  export default {
    name: "finaAudit",
    data() {
      return {
        dialogVisible: false,
        sno:null,
        formVisible: false,
        loading: true,
        query: {
          page: 1,
          status:0
        },
        currentMass: {},
        request: {}
      };
    },
    computed: {
      compiledMarkdown: function () {
        if (this.currentMass.reason) {
          let converter = new showdown.Converter();
          return converter.makeHtml(this.currentMass.reason);
        }
      }
    },
    //加载组件
    mounted() {
      let user = JSON.parse(sessionStorage.getItem("user"));
      getAudit(this.query).then(req => {
        this.request = req.data;
        this.loading = false;
      })
    },
    components: {},

    methods: {
      getPage(event) {
        this.query.page = event;
        this.getData();
      },
      getData() {
        getAudit(this.query).then(req => {
          this.request = req.data.data;
          this.loading = false;
        })
      },
      detiail(data) {
        this.dialogVisible = true;
        this.currentMass = data;
      },
      auditTo(status){
        let data={
          id:this.currentMass.id,
          status:status
        }
        checkMass(data).then(req=>{
          this.$notify({
            message: "审核完成",
            type: "success"
          });
          this.dialogVisible = false;
          this.getData();
        })
      }
    }
  };
</script>



