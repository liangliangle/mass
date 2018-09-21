<!--模板-->
<template>
  <section>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>签到结果</span>
        <div style="float: right">
          <el-button size="small" type="text" @click='exportExcel()'>导出</el-button>
          <el-button size="small" type="text" @click='toExporyExcel()'>关联导出</el-button>
        </div>
        <el-table
          v-loading="loading"
          :data="this.request.list"
          style="width: 100%">
          <el-table-column
            prop="name"
            label="签到人"
            width="180">
          </el-table-column>
          <el-table-column
            prop="comments"
            label="备注">
          </el-table-column>
          <el-table-column
            label="签到时间">
            <template slot-scope="scope">
              {{scope.row.createTime / 1000 | moment("YYYY-MM-DD HH:mm:ss") }}
            </template>
          </el-table-column>
          <el-table-column
            prop="type"
            label="签到类型">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.type=='正常'" type="warning">正常</el-tag>
              <el-tag v-if="scope.row.type=='补签'" type="danger">补签</el-tag>
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
        <el-row justify="center" style="margin-top:20px">
          <el-col :span="4">
            <el-button @click='prev'>上一步</el-button>
          </el-col>
        </el-row>
      </div>
    </el-card>

    <!--修改密码窗体-->
    <el-dialog title="关联导出" :visible.sync="exportFormVisible" append-to-body width="500px">
      <el-form :model="form" label-width="100px" ref="passForm">
        <el-form-item label="当前签到" prop="oldPass">
          <span>{{form.name}}</span>>
        </el-form-item>
        <el-form-item label="关联签到" prop="newPass">
          <el-select v-model="form.chindle.id" placeholder="请选择关联签到">
            <el-option v-for="(sign,index) in signs" :key="index" :label="sign.name" :value="sign.id"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitTo" :loading="submitlogining">确认</el-button>
      </div>
    </el-dialog>
  </section>
</template>
<!--JS-->


<script>
  import {getResult, down, getById, getByActivityId} from "@/service/signapi";
  import moment from "moment";

  export default {
    name: "finaAudit",
    data() {
      return {
        submitlogining: false,
        exportFormVisible: false,
        loading: true,
        request: {},
        currentSign: {},
        form: {
          id: null,
          name: null,
          chindle: {}
        },
        query: {
          page: 1,
          id: null
        },
        signs: [],
      };
    },
    //加载组件
    mounted() {
      this.query.id = this.$route.params.id
      this.getData();
      getById(this.$route.params.id).then(req => {
        this.currentSign = req.data;
      })


    },
    components: {},

    methods: {
      prev() {
        this.$router.go(-1);
      },
      getData() {
        getResult(this.query).then(req => {
          this.request = req.data;
          this.loading = false;
        })
      },
      exportExcel() {
        down({id: this.query.id, name: this.currentSign.name});
      },
      toExporyExcel() {
        this.exportFormVisible = true;
        this.form.name = this.currentSign.name;
        this.form.id = this.currentSign.id;
        this.submitlogining = false;
        getByActivityId(this.currentSign.activitiesId).then(req => {
          this.signs = req.data;
        })
      },
      submitTo() {
        console.log(this.form);
        for (let index in this.signs) {
          if (this.signs[index].id == this.form.chindle.id) {
            this.form.chindle.name = this.signs[index].name;
          }
        }
        this.submitlogining = true;
        down(this.form).then(req => {
          this.submitlogining = false;
        })
      },
      getpage(event) {
        this.query.page = event;
        this.getData();
      }
    }
  };
</script>



