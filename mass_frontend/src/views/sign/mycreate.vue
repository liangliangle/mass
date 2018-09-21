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
          <el-tag v-if="scope.row.status==1" type="warning">未开始</el-tag>
          <el-tag v-if="scope.row.status==2" type="danger">已开始</el-tag>
          <el-tag v-if="scope.row.status==3">已结束</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="操作">
        <template slot-scope="scope">
          <el-button
            @click="sign(scope.row)"
            type="text"
            size="small" v-if="scope.row.status==2 || scope.row.status==3">
            补签
          </el-button>
          <el-button
            @click="getCode(scope.row)"
            type="text"
            size="small" v-if="scope.row.status==2">
            签到
          </el-button>
          <el-button
            @click="result(scope.row)"
            type="text"
            size="small" v-if="scope.row.status==2">
            结果
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog
      title="签到码"
      :visible.sync="dialogVisible">
      <el-form :model="signdto">
        <el-form-item label="学号" :label-width="formLabelWidth">
          <el-input v-model="signdto.sno" style="width: 300px"  @blur="find"></el-input>
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
  import {mycreate, getSign, sign} from "@/service/signapi";
  import {getUserBySno} from "@/service/userapi"

  import moment from "moment";

  export default {
    name: "finaAudit",
    data() {
      return {
        dialogVisible: false,
        loading: true,
        request: {},
        signdto: {},
        query: {
          sno: null,
          page: 1
        },
        imgstring: null,
        id: null,
        formLabelWidth: "130px"
      };
    },
    //加载组件
    mounted() {
      let user = JSON.parse(sessionStorage.getItem("user"));
      this.query.sno = user.user.username;
      mycreate(this.query).then(req => {
        this.request = req.data;
        this.loading = false;
      })
    },
    components: {},

    methods: {
      getpage(event) {
        this.query.page = event;
        mycreate(this.query).then(req => {
          this.request = req.data;
          this.loading = false;
        })
      },
      getCode(data) {
        const {href} = this.$router.resolve({
          path: '/qrcode/' + data.id,
        })
        window.open(href, '_blank')
      },
      sign(event) {
        this.dialogVisible = true;
        this.signdto.registerId = event.id;
      },
      result(data){
        this.$router.push({path: "/sign/result/" + data.id});
      },
      submit() {
        sign(this.signdto).then(req => {
          this.$notify({
            message: "补签成功",
            type: "success"
          });
          this.signdto = {};
          this.dialogVisible = false;
        }).catch(err => {

        });
      },
      find() {
        let username;
        getUserBySno({username: this.signdto.sno}).then(req => {
          if (req.data) {
            this.signdto.name = req.data.name;
            this.signdto.classes=req.data.grade;
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



