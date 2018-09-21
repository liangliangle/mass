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
          <el-tag v-if="scope.row.status==3">准备中</el-tag>
          <el-tag v-if="scope.row.status==4" type="success">结束</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="操作">
        <template slot-scope="scope">
          <el-button
            @click="adds(scope.row)"
            type="text"
            size="small" v-if="scope.row.status==3">
            创建签到
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
    <el-dialog
      title="创建签到"
      :visible.sync="dialogVisible"
      width="30%">

      <el-form :model="sign">
        <el-form-item label="活动名称" :label-width="formLabelWidth">
          <el-input :disabled="true" v-model="currentActivity.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="签到名称" :label-width="formLabelWidth">
          <el-input v-model="sign.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="签到类型" :label-width="formLabelWidth">
          <el-select v-model="sign.type" placeholder="请选择签到类型">
            <el-option label="观众" value="观众"></el-option>
            <el-option label="参与者" value="参与者"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="开始时间" :label-width="formLabelWidth">
          <el-date-picker
            v-model="sign.startTime"
            type="datetime">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" :label-width="formLabelWidth">
          <el-date-picker
            v-model="sign.endTime"
            type="datetime">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="submit">确 定</el-button>
  </span>
    </el-dialog>
  </section>
</template>
<!--JS-->


<script>
  import {mymass} from "@/service/activityapi";

  import {create} from "@/service/signapi"
  import moment from "moment";

  export default {
    name: "program",
    data() {
      return {
        mass: [],
        loading: true,
        request: {},
        dialogVisible: false,
        currentActivity: {},
        sign: {
          activitiesId: null,
          comments: null,
          endTime: null,
          mark: 0,
          name: null,
          sno: null,
          startTime: null,
          type: null
        },
        formLabelWidth: "130px"

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
      this.sign.sno = user.user.username;
      mymass({page: 1, mass: this.mass[0], status: 3}).then(req => {
        this.request = req.data;
        this.loading = false;
      });
    },
    components: {},

    methods: {

      adds(event) {
        this.currentActivity = event;
        this.sign.activitiesId = event.id;
        this.dialogVisible = true;

      },
      submit() {
        this.sign.comments = this.currentActivity.name + " - " + this.sign.name;
        create(this.sign).then(req => {
          this.dialogVisible = false;
          this.$notify({
            message: "创建成功"
          });
        })

      },
      getpage(event) {
        mymass({page: event, mass: this.mass[0], status: 3}).then(req => {
          this.request = req.data;
          this.loading = false;
        });
      }
    }
  }
  ;
</script>



