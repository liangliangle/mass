<!--模板-->
<template>
  <section>
    <!--查询过滤条件部分-->
    <el-col :span="24" class="toolbar" style="padding-top:10px;">
      <el-form :inline="true" :model="query" ref="filtersForm">
        <el-form-item label="签到名称" prop="name">
          <el-input v-model="query.name" placeholder="签到名称名称"></el-input>
        </el-form-item>
        <el-form-item label="开始时间">
          <el-date-picker
            v-model="query.startTime"
            type="date"
            placeholder="选择日期">
          </el-date-picker>
          -
          <el-date-picker
            v-model="query.endTime"
            type="date"
            placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="query.status" placeholder="请选择">
            <el-option label="未开始" value="1"></el-option>
            <el-option label="已开始" value="2"></el-option>
            <el-option label="已结束" value="3"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="queryChange">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button @click="resetFilters">重置</el-button>
        </el-form-item>
      </el-form>
    </el-col>
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
        prop="comments"
        label="备注">
      </el-table-column>
      <el-table-column
        label="开始时间">
        <template slot-scope="scope">
          {{scope.row.startTime / 1000 | moment("YYYY-MM-DD HH:mm") }}
        </template>
      </el-table-column>
      <el-table-column
        label="结束时间">
        <template slot-scope="scope">
          {{scope.row.endTime / 1000 | moment("YYYY-MM-DD HH:mm") }}
        </template>
      </el-table-column>
      <el-table-column
        prop="status"
        label="状态">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status==0" type="danger">终止</el-tag>
          <el-tag v-if="scope.row.status==1" type="warning">未开始</el-tag>
          <el-tag v-if="scope.row.status==2" type="info">已开始</el-tag>
          <el-tag v-if="scope.row.status==3">已结束</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="status"
        label="操作">
        <template slot-scope="scope">
          <el-button
            @click="sign(scope.row)" type="text" size="small" v-if="scope.row.status==2 || scope.row.status==3">补签
          </el-button>
          <el-button @click="getCode(scope.row)" type="text" size="small" v-if="scope.row.status==2">签到</el-button>
          <el-button @click="result(scope.row)" type="text" size="small" v-if="scope.row.status==2 ||scope.row.status==3">结果</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      title="签到码"
      :visible.sync="dialogVisible">
      <el-form :model="signdto">
        <el-form-item label="学号" :label-width="formLabelWidth">
          <el-input v-model="signdto.sno" style="width: 300px" @blur="find"></el-input>
        </el-form-item>
        <el-form-item label="学生姓名" :label-width="formLabelWidth">
          <span style="width: 300px">{{signdto.name}}</span>
        </el-form-item>
        <el-form-item label="备注" :label-width="formLabelWidth">
          <el-input v-model="signdto.comments" style="width: 300px"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible=false">关闭</el-button>
        <el-button type="primary" @click="submit">提交</el-button>
      </span>
    </el-dialog>

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
  import {signQuery, getSign} from "@/service/signapi";
  import {getUserBySno} from "@/service/userapi"
  import moment from "moment";

  export default {
    name: "finaAudit",
    data() {
      return {
        dialogVisible: false,
        loading: true,
        request: {},
        query: {
          endTime: null,
          name: null,
          page: 0,
          startTime: null,
          status: null,
        },
        signdto: {},
        imgstring: null,
        id: null
      };
    },
    //加载组件
    mounted() {
      let user = JSON.parse(sessionStorage.getItem("user"));
      this.getData()
      this.loading = false;
    },
    components: {},

    methods: {
      queryChange() {
        this.query.page=1
        this.getData()
      },
      resetFilters() {
        let page = this.query.page;
        this.query = {
          endTime: null,
          name: null,
          page: page,
          startTime: null,
          status: null,
        }
        this.getData()
      },
      sign(event) {
        this.dialogVisible = true;
        this.signdto.registerId = event.id;
      },
      result(data) {
        this.$router.push({path: "/sign/result/" + data.id});
      },
      getpage(event) {
        this.query.page = event;
        this.getData()
      },
      getCode(data) {
        const {href} = this.$router.resolve({
          path: '/qrcode/' + data.id,
        })
        window.open(href, '_blank')
      },
      getData() {
        this.loading=true;
        signQuery(this.query).then(req => {
          this.request = req.data;
          this.loading = false;
        })
      },
      find() {
        let username;
        getUserBySno({username: this.signdto.sno}).then(req => {
          if (req.data) {
            this.signdto.name = req.data.name;
            this.signdto.classes = req.data.grade;
          } else {
            this.$notify({
              message: "未找到用户",
              type: "warning"
            });
          }
        })
      }
    }
  }
  ;
</script>



