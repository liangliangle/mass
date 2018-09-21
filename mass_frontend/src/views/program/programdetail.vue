<!--模板-->
<template>
  <section>
    <el-card style="margin-top:20px" v-loading="loading">
      <div slot="header">
        <span>活动信息</span>
      </div>
      <el-row :gutter="20">
        <el-col :span="8" style="margin-top:20px">活动名称:{{activity.name}}</el-col>
        <el-col :span="8" style="margin-top:20px">主办社团:{{activity.sponsorMass}}</el-col>
        <el-col :span="8" style="margin-top:20px">举办时间:{{activity.holdTime/1000 | moment("YYYY-MM-DD HH:mm") }}</el-col>
        <el-col :span="8" style="margin-top:20px">活动类型:{{activity.type}}</el-col>
        <el-col :span="16" style="margin-top:20px">活动地点:{{activity.place}}</el-col>
      </el-row>
    </el-card>
    <el-card style="margin-top:20px" v-loading="loading">
      <div slot="header">
        <span>节目列表</span>
      </div>
      <el-table
        v-loading="loading"
        :data="programs"
        style="width: 100%">
        <el-table-column
          prop="name"
          label="节目名称"
          width="180">
        </el-table-column>
        <el-table-column
          prop="mass"
          label="社团"
          width="180">
        </el-table-column>
        <el-table-column
          prop="type"
          label="节目类型"
          width="180">
        </el-table-column>
      </el-table>
    </el-card>

    <el-row justify="center" style="margin-top:20px">
      <el-col :span="4">
        <el-button @click='prev'>上一步</el-button>
      </el-col>
    </el-row>
  </section>
</template>
<!--JS-->


<script>
  import {getById, getByActivity} from "@/service/programapi";

  import {getById as getActivity} from "@/service/activityapi"

  export default {
    name: "activityDetail",
    computed: {
      compiledMarkdown: function () {
        if (this.activity.value) {
          return marked(this.activity.value, {sanitize: true});
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
        programs: [],
        activity: {}
      };
    },
    //加载组件
    mounted() {

      if (this.$route.params.id) {

        getActivity(this.$route.params.id).then(req => {
          this.activity = req.data;
          getByActivity({
            activityId: this.$route.params.id
          }).then(req => {
            this.programs = req.data;
            this.loading = false;
          });
        })
      }
    },
    methods: {
      prev() {
        this.$router.go(-1);
      },
      edit() {
        this.$router.push({path: "/activity/edit/" + this.activity.id});
      }
    }
  };
</script>



