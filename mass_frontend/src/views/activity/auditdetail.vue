<!--模板-->
<template>
  <section>
    <el-card style="margin-top:20px" v-loading="loading">
      <div slot="header">
        <span>主要信息</span>
      </div>
      <el-row :gutter="20">
        <el-col :span="8" style="margin-top:20px">活动名称:{{activity.name}}</el-col>
        <el-col :span="8" style="margin-top:20px">主办社团:{{activity.sponsorMass}}</el-col>
        <el-col :span="8" style="margin-top:20px">举办时间:{{activity.holdTime / 1000 | moment("YYYY-MM-DD HH:mm") }}
        </el-col>
        <el-col :span="8" style="margin-top:20px">活动类型:{{activity.type}}</el-col>
        <el-col :span="16" style="margin-top:20px">活动地点:{{activity.place}}</el-col>
      </el-row>
    </el-card>
    <el-card style="margin-top:20px" v-loading="loading">
      <div slot="header">
        <span>申请书</span>
      </div>
      <mavon-editor v-model="activity.value" ref=md  :editable=false :toolbarsFlag=false
                    :subfield=false></mavon-editor>
    </el-card>
    <el-row justify="center" style="margin-top:20px">
      <el-col :span="4">
        <el-button @click='prev'>上一步</el-button>
      </el-col>
      <el-col :span="2" :offset="14">
        <el-button type="danger" justify="right" @click="danger()" :loading="submitLoading">终止</el-button>
      </el-col>
      <el-col :span="2">
        <el-button type="warning" justify="right" @click="error()" :loading="submitLoading">拒绝</el-button>
      </el-col>
      <el-col :span="2">
        <el-button type="primary" justify="right" @click="success" :loading="submitLoading">通过</el-button>
      </el-col>
    </el-row>
  </section>
</template>
<!--JS-->


<script>
  import {getById, audit} from "@/service/activityapi";
  import showdown from "showdown";

  export default {
    name: "activityDetail",
    computed: {
      compiledMarkdown: function () {
        if (this.activity.value) {
          let converter = new showdown.Converter();
          return converter.makeHtml(this.activity.value);
        }
      }
    },
    //加载组件
    components: {},
    data() {
      return {
        loading: true,
        submitLoading: false,
        activity: {}
      };
    },
    //加载组件
    mounted() {
      this.loading = true;
      getById(this.$route.params.id)
        .then(req => {
          this.activity = req.data;
          this.loading = false;
        })
        .catch(req => {
          this.loading = false;
        });
    },
    methods: {
      prev() {
        this.$router.push({path: "/activity/audit"});
      },
      notify(message, type) {
        this.$notify({
          message: message,
          type: type
        });
      },
      success() {
        this.$confirm('同意该活动申请, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.submitLoading = true;
          audit({id: this.activity.id, status: 1})
            .then(req => {
              this.notify("已通过", "success");
              this.prev();
              this.submitLoading = false;
            })
            .catch(req => {
              this.submitLoading = false;
            });
        })
      },
      danger() {
        this.$confirm('终止该活动申请, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'error'
        }).then(() => {
          audit({id: this.activity.id, status: -1})
            .then(req => {
              this.notify("已通过", "success");
              this.prev();
              this.submitLoading = false;
            })
            .catch(req => {
              this.submitLoading = false;
            });
        })
      },
      error() {
        this.$prompt("请输入拒绝理由", "提示", {
          confirmButtonText: "提交",
          cancelButtonText: "取消"
        }).then(({value}) => {
          audit({id: this.activity.id, proposal: value, status: 0})
            .then(req => {
              this.notify("已拒绝", "success");
              this.prev();
            })
            .catch(req => {
            });
        })

      }
    }
  }
  ;
</script>



