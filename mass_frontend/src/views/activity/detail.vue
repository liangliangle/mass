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
        <el-col :span="8" style="margin-top:20px">举办时间:{{activity.holdTime / 1000 | moment("YYYY-M-DD HH:mm") }}
        </el-col>
        <el-col :span="8" style="margin-top:20px">活动类型:{{activity.type}}</el-col>
        <el-col :span="16" style="margin-top:20px">活动地点:{{activity.place}}</el-col>
        <el-col :span="8" v-if="activity.status==3" style="margin-top:20px">节目菜单:
          <el-button type="text" @click="program">查看详情</el-button>
        </el-col>
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
      <el-col :span="2" :offset="18">
        <el-button type="primary"
                   v-if="(activity.status==1 ||activity.status==2)&& mass==activity.sponsorMass "
                   justify="right" @click="edit" :loading="submitLoading">编辑
        </el-button>
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
          let converter = new showdown.Converter()
          return converter.makeHtml(this.activity.value);
        }
      }
    },
    //加载组件
    components: {},
    data() {
      return {
        mass: null,
        loading: true,
        submitLoading: false,
        activity: {}
      };
    },
    //加载组件
    mounted() {
      let user = JSON.parse(sessionStorage.getItem("user"));
      if (user.role != "社长") {
        this.mass = ["全部社团"];
      } else {
        this.mass = user.mass;
      }
      if (this.$route.params.id) {
        getById(this.$route.params.id).then(req => {
          this.form = req.data;
        });
      }
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
        this.$router.go(-1);
      },
      edit() {
        this.$router.push({path: "/activity/edit/" + this.activity.id});
      },
      program() {
        this.$router.push({path: "/program/list/" + this.activity.id});
      }
    }
  };
</script>



