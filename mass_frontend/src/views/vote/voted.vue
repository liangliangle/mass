<!--模板-->
<template>
  <section>
    <el-table v-loading="loading" :data="this.request.list" style="width: 100%">
      <el-table-column prop="name" label="名称">
      </el-table-column>
      <el-table-column
        prop="isAnon"
        label="投票类型">
        <template slot-scope="scope">
          <span v-if="scope.row.isAnon==1">匿名</span>
          <span v-if="scope.row.isAnon==0">公开</span>
        </template>
      </el-table-column>
      <el-table-column prop="range" label="投票范围" width="180">
      </el-table-column>

      <el-table-column label="结束时间">
        <template slot-scope="scope">
          {{scope.row.endTime / 1000 | moment("YYYY-MM-DD HH:mm") }}
        </template>
      </el-table-column>
      <el-table-column
        prop="stuName"
        label="创建人">
      </el-table-column>
      <el-table-column
        label="状态">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status==1" type="warning">未开始</el-tag>
          <el-tag v-if="scope.row.status==2" type="danger">已开始</el-tag>
          <el-tag v-if="scope.row.status==3" type="info">已结束</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="投票状态">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.redundancy==0" type="warning">未投</el-tag>
          <el-tag v-if="scope.row.redundancy==1">已投</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="操作">
        <template slot-scope="scope">
          <el-button @click.native.prevent="tovote(scope.row)" v-if="scope.row.status==2 && scope.row.redundancy==0"
                     type="text" size="small">投票
          </el-button>
          <el-button @click.native.prevent="count(scope.row)" v-if="scope.row.status>=2 && scope.row.redundancy==1"
                     type="text" size="small">
            汇总数据
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
    <el-dialog title="投票" :visible.sync="dialogVisible">
      <el-row :gutter="20">
        <el-col :span="24" style="margin-top:20px">投票名称: <b>{{vote.name}}</b></el-col>
        <el-col :span="24" style="margin-top:20px">投票范围: <b>{{vote.range}}</b></el-col>
        <el-col :span="24" style="margin-top:20px">可投数量: <b>{{vote.voteNum}}</b></el-col>
        <el-col :span="24" style="margin-top:20px">创建人: <b>{{vote.stuName}}</b></el-col>
      </el-row>
      <el-form :model="vote">
        <el-checkbox-group
          v-model="options"
          :max="vote.voteNum">
          <el-row :gutter="20">
            <el-col :span="24" style="margin-top:20px">
              <div v-for="(o,index) in vote.optionsDto" :key="o.thetext">
                <el-checkbox :label="o.thetext" :index="index"></el-checkbox>
              </div>
            </el-col>
          </el-row>
        </el-checkbox-group>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="funvote()">确 定</el-button>
      </div>
    </el-dialog>
  </section>
</template>
<!--JS-->


<script>
  import {getBySno, getById, voteDo} from "@/service/voteapi";
  import moment from "moment";

  export default {
    name: "finaAudit",
    data() {
      return {
        dialogVisible: false,
        loading: true,
        request: {},
        vote: {},
        options: [],
        query: {
          page: 0,
          sno: null
        },
        dovote: {
          options: [],
          sno: null,
          username: null,
          voteId: null
        }

      };
    },
    //加载组件
    mounted() {
      let user = JSON.parse(sessionStorage.getItem("user"));
      this.query.sno = user.user.username;
      getBySno(this.query).then(req => {
        this.request = req.data;
        this.loading = false;
      }).catch(err => {

      })
    },
    components: {},

    methods: {
      tovote(event) {
        this.options = [];
        getById(event.id).then(req => {
          this.vote = req.data;
          this.dialogVisible = true;
        })

      },
      funvote() {
        let options = this.options;
        let vote = this.vote;
        if (options.length < 1) {
          this.$notify({
            message: "您还没有选择",
            type: "error"
          });
          return;
        }
        let user = JSON.parse(sessionStorage.getItem("user"));
        this.dovote.sno = user.user.username;
        this.dovote.username = user.user.name;
        this.dovote.voteId = vote.id;
        this.vote.options = [];
        for (let index in options) {
          for (let oindex in vote.optionsDto) {
            if (options[index] == vote.optionsDto[oindex].thetext) {
              if (options[index]) {
                this.dovote.options.push({option: options[index], optionId: vote.optionsDto[oindex].id});
              }
            }
          }
        }
        voteDo(this.dovote).then(req => {
          this.dialogVisible = false;
          this.vote.options = [];
        })
        this.options = [];

      },
      count(event) {
        this.$router.push({path: "/vote/result/" + event.id});
      },
      getPage(page) {
        this.loading = true;
        this.query.page = page;
        getBySno(this.query).then(req => {
          this.request = req.data;
          this.loading = false;
        }).catch(err => {

        })
      }
    }
  };
</script>



