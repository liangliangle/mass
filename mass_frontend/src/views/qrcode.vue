<!--权限不足提示内页-->
<template>
    <span class="page-container">
        <p>
          <el-slider v-model="code.expireTime" :max="10" :min="0"></el-slider>
          <img :src="code.img" style="width: 50%"/>

        </p>
    </span>
</template>

<script>
  import {getSign} from "@/service/signapi";
  import moment from "moment";

  export default {
    name: "finaAudit",
    data() {
      return {
        currentId: null,
        code: {
          img: null,
          expireTime: null
        }
      };
    },
    //加载组件
    mounted() {
      this.currentId = this.$route.params.id;
      this.getData();

    },
    methods: {
      getData() {
        getSign(this.currentId).then(req => {
          let falg = false;
          if (this.code.img) {
            falg = true;
          }
          this.code.img = "data:image/png;base64," + req.data.img;
          this.code.expireTime = req.data.expireTime;
          if (!falg) {
            this.after();
          }
        });
      },
      detection() {
        console.log(this.code);
        this.code.expireTime = this.code.expireTime - 1;
        if (this.code.expireTime <= 0) {
          this.getData();
        }
      },
      after() {
        let that = this;
        if (this.code.expireTime) {
          setInterval(() => {
            that.detection()
          }, 1000);
        }
      },

    }
  }
</script>

<style lang="scss" scoped>
  .page-container {
    font-size: 20px;
    text-align: center;
    color: rgb(192, 204, 218);
  }
</style>
