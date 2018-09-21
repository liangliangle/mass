<!--模板-->
<template>
  <section>
    <el-form ref="form" :model="form" label-width="100px" v-loading="loading">
      <el-form-item label="社团名称">
        <el-input v-model="form.name" style="width: 300px;"></el-input>
      </el-form-item>
      <el-form-item label="别名">
        <el-input v-model="form.petName" style="width: 300px;"></el-input>
      </el-form-item>
      <el-form-item label="联合申请人">
        <el-input v-model="form.usno1" style="width: 300px;"></el-input>
      </el-form-item>
      <el-form-item label="联合申请人">
        <el-input v-model="form.usno2" style="width: 300px;"></el-input>
      </el-form-item>
      <el-form-item label="联合申请人">
        <el-input v-model="form.usno3" style="width: 300px;"></el-input>
      </el-form-item>
      <el-form-item label="申请书">
        <mavon-editor v-model="form.reason" ref=md ></mavon-editor>
      </el-form-item>
      <el-form-item>
        <el-row justify="center">
          <el-col :span="4">
            <el-button @click="perv">上一步</el-button>
          </el-col>
          <el-col :span="4" :offset="16">
            <el-button type="primary" justify="right" @click="onSubmit" :loading="submitLoading">申请</el-button>
          </el-col>
        </el-row>
      </el-form-item>
    </el-form>
  </section>
</template>
<!--JS-->


<script>
  import {create} from "@/service/orgapi";
  import moment from "moment";

  export default {
    name: "finaAudit",
    data() {
      return {
        loading: false,
        request: {},
        form: {},
        submitLoading: false
      };
    },
    //加载组件
    mounted() {
      let user = JSON.parse(sessionStorage.getItem("user"));
      this.form.presidentSno = user.user.username;
      this.form.presidentName = user.user.name;
    },
    components: {},
    methods: {
      perv() {
        this.$router.go(-1);
      },
      onSubmit() {
        create(this.form).then(req => {
          this.$notify({
            message: "申请成功，请等待审核",
            type: "success"
          });
          this.$router.push({
            path: "/mass/mycreate"
          });
        })
      }
    }
  };
</script>



