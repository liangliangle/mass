<template>
  <section>
    <el-row :gutter="20" style="padding:10px;">
      <el-col :span="12">
        <el-card class="box-card" v-loading="aloading" style="height:300px;">
          <div slot="header" class="clearfix">
            <span>最近活动</span>
          </div>
          <div v-if="activitys.length==0" style="text-align: center">暂无最近活动</div>
          <el-carousel v-if="activitys.length>0">
            <el-carousel-item v-for="(item,index) in activitys" :key="index">
              <div style="line-height:200%">
                <div>活动名称：{{item.name}}</div>
                <div>主办社团： {{item.sponsorMass}}</div>
                <div>活动类型： {{item.type}}</div>
                <div>举办时间： {{item.holdTime / 1000 | moment("YYYY年MM月DD日HH:mm") }}</div>
                <div>活动地点：{{item.place}}</div>
              </div>
            </el-carousel-item>
          </el-carousel>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="box-card" v-loading="nloading" style="height:300px;">
          <div slot="header" class="clearfix">
            <span>未读通知</span>
          </div>
          <el-table :data="this.notices.list" style="width: 100%">
            <el-table-column prop="name" label="名称">
            </el-table-column>
            <el-table-column label="结束时间">
              <template slot-scope="scope">
                {{scope.row.endTime / 1000 | moment("YYYY-MM-DD HH:mm") }}
              </template>
            </el-table-column>
            <el-table-column
              label="操作">
              <template slot-scope="scope">
                <el-button @click.native.prevent="noticedetile(scope.row)" type="text" size="small">阅读</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="box-card" style="height:300px;margin-top: 20px;">
          <div slot="header" class="clearfix">
            <span>未投投票</span>
          </div>
          <el-table v-loading="vloading" :data="this.votes.list" style="width: 100%">
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

            <el-table-column label="结束时间">
              <template slot-scope="scope">
                {{scope.row.endTime / 1000 | moment("YYYY-MM-DD HH:mm") }}
              </template>
            </el-table-column>
            <el-table-column
              label="操作">
              <template slot-scope="scope">
                <el-button @click.native.prevent="votedetile(scope.row)" type="text" size="small">
                  投票
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card class="box-card" style="height:300px;margin-top: 20px;">
          <div slot="header" class="clearfix">
            <span>个人信息</span>
          </div>
          <el-row :gutter="20">
            <el-col :span="24">学号:{{userinfo.username}}</el-col>
            <el-col :span="24" style="margin-top: 16px">姓名:{{userinfo.name}}</el-col>
            <el-col :span="24" style="margin-top: 16px">性别:{{userinfo.sex}}</el-col>
            <el-col :span="24" style="margin-top: 16px">班级:{{userinfo.grade}}</el-col>
            <el-col :span="24" style="margin-top: 16px">邮箱:{{userinfo.email}}</el-col>
            <el-col :span="24" style="margin-top: 16px">手机号:{{userinfo.phone}}</el-col>
          </el-row>
        </el-card>
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
              <el-checkbox v-for="(o,index) in vote.optionsDto" :index="index" :label="o.thetext"
                           :key="o.thetext"></el-checkbox>
            </el-col>
          </el-row>
        </el-checkbox-group>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="funvote()">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="通知详情" :visible.sync="noticeDialogVisible">
      <div slot="footer" class="dialog-footer">
        <mavon-editor v-model="currentNotice.thetext" ref=md  :editable=false :toolbarsFlag=false
                      :subfield=false></mavon-editor>
        <el-button @click="noticeDialogVisible = false">确定</el-button>
      </div>
    </el-dialog>
  </section>
</template>

<script>
  import {latelyAvtivity} from "@/service/activityapi";
  import {getMy as mynotice, getById as getNotice} from "@/service/noticeapi";
  import {getBySno as getVote, getById as getVoteId, voteDo} from "@/service/voteapi";
  import {getUserBySno} from "@/service/userapi"

  import showdown from "showdown";

  export default {
    computed: {
      compiledMarkdown: function (data) {
        if (data) {
          converter = new showdown.Converter();
          return converter.makeHtml(data);
        }
      }
    },
    data() {
      return {
        nloading: true,
        aloading: true,
        vloading: true,
        uloading: true,
        noticeDialogVisible: false,
        sno: null,
        activitys: [],
        notices: {},
        votes: {},
        userinfo: {},
        dialogVisible: false,
        vote: {},
        options: [],
        query: {
          page: 1,
          sno: null,
          status: 0
        },
        currentNotice: {}
      }
    },
    methods: {
      getVoteTo() {
        getVote(this.query).then(req => {
          this.votes = req.data;
          this.vloading = false;
        })
      },
      getNoticeTo() {
        mynotice(this.query).then(req => {
          this.notices = req.data;
          this.nloading = false;
        })
      },
      votedetile(event) {
        getVoteId(event.id).then(req => {
          this.vote = req.data;
          this.dialogVisible = true;
        })
      },
      funvote() {
        let options = this.options;
        let vote = this.vote;
        this.dovote = {
          options: [],
          sno: null,
          username: null,
          voteId: 0
        }
        let user = JSON.parse(sessionStorage.getItem("user"));
        this.dovote.sno = user.user.username;
        this.dovote.username = user.user.name;
        this.dovote.voteId = vote.id;
        this.vote.options = [];
        if (options.length < 1) {
          this.$notify({
            message: "您还没有选择",
            type: "error"
          });
          return;
        }
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
      },
      noticedetile(event) {
        let id = event.id;
        let sno = this.sno;
        getNotice({id: id, sno: sno}).then(req => {
          this.noticeDialogVisible = true;
          this.currentNotice = req.data;
          this.getNoticeTo();
        });

      }
    },
    mounted() {
      latelyAvtivity({}).then(req => {
        this.activitys = req.data;
        this.aloading = false;
      });
      let user = JSON.parse(sessionStorage.getItem("user"));
      this.query.sno = user.user.username;
      this.sno = user.user.username;
      this.getNoticeTo();
      this.getVoteTo();
      this.userinfo = user.user;
      this.uloading = false;

    }
  }
</script>
