<!--模板-->
<template>
  <section>
    <el-card loading="loading" class="box-card">
      <div slot="header" class="clearfix">
        <span>签到二维码</span>
      </div>
      <img :src="img"/>
    </el-card>
    <el-row justify="center">
      <el-col :span="4">
        <el-button @click="perv">上一步</el-button>
      </el-col>
      <el-col :span="4" :offset="16">
      </el-col>
    </el-row>
  </section>
</template>
<!--JS-->


<script>
  import {getSign} from "@/service/signapi";
  import moment from "moment";

  export default {
    name: "finaAudit",
    data() {
      return {
        loading: true,
        img: null
      };
    },
    //加载组件
    mounted() {
      this.loading = true;
      if (this.$route.params.id) {
        getSign(this.$route.params.id).then(req => {
          this.img = "data:image/png;base64," + req.data.img;
          this.loading = false;
        })
        let now = new Date().getTime() + 10000;
        while (now < new Date().getTime()) {
          /**getSign(this.$route.params.id).then(req => {
            this.img = "data:image/png;base64," + req.data.img;
            this.loading = false;
          })**/
          now=new Date().getTime()+10000;
        }
      }
    },
    components: {},
    methods: {
      perv() {
        this.$router.go(-1);
      }
    }
  }
  ;
</script>



