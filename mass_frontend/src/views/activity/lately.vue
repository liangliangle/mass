<!--模板-->
<template>
  <section>
    <div v-if="request.length==0" style="text-align: center">暂无最近活动</div>
   <el-row :gutter="20" v-loading="loading" >
    <el-col :span="8" v-for="item in request" :key="item.id" >
<el-card >
  <div slot="header" >
    <span>{{item.name}}</span>
  </div>
  <div style="line-height:250%" >
    <div>
    主办社团： {{item.sponsorMass}}
  </div>
  <div>
   活动类型： {{item.type}}
  </div>
  <div>
    举办时间： {{item.holdTime/1000 | moment("YYYY年MM月DD日HH:mm") }}
  </div>
  <div>
    活动地点：{{item.place}}
  </div>
  </div>
</el-card>
  </el-col>
</el-row>
  </section>
</template>
<!--JS-->


<script>
import { latelyAvtivity } from "@/service/activityapi";

export default {
  name: "latelyAvtivity",
  data() {
    return {
      loading: true,
      request: []
    };
  },
  //加载组件
  mounted() {
    latelyAvtivity({}).then(req => {
      this.request = req.data;
      this.loading = false;
    });
  },
  components: {},

  methods: {}
};
</script>



