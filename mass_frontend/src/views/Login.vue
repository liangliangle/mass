<!--登录页-->
<template>
  <el-form :model="loginForm" :rules="loginRules" ref="loginForm" label-position="left" label-width="0px"
           class="login-container">
    <h3 class="title text-center">社团管理系统</h3>
    <el-form-item prop="loginuser">
      <el-input type="text" v-model="loginForm.loginuser" auto-complete="off" placeholder="账号"
                @keyup.enter.native="loginIn" :autofocus="true"></el-input>
    </el-form-item>
    <el-form-item prop="loginpwd">
      <el-input type="password" v-model="loginForm.loginpwd" auto-complete="off" placeholder="密码"
                @keyup.enter.native="loginIn"></el-input>
    </el-form-item>
    <el-checkbox v-model="checked" class="remember">记住密码</el-checkbox>
    <el-form-item style="width:100%;margin-top:10px;">
      <el-button type="primary" style="width:100%;" @click.native.prevent="loginIn" :loading="logining">登录</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  import {
    requestLogin, checkRole
  } from "../service/api";

  export default {
    name: "Login",
    data() {
      return {
        logining: false,
        loginForm: {
          loginuser: "",
          loginpwd: ""
        },
        loginRules: {
          loginuser: [{
            required: true,
            message: "请输入账号",
            trigger: "blur"
          }],
          loginpwd: [{
            required: true,
            message: "请输入密码",
            trigger: "blur"
          }]
        },
        checked: false
      };
    },
    mounted() {
      let arr, reg = new RegExp("(^| )tocken=([^;]*)(;|$)");
      if (arr = document.cookie.match(reg)) {
        let tocken = unescape(arr[2]);
        let arr1, reg1 = new RegExp("(^| )roleId=([^;]*)(;|$)");
        if (arr1 = document.cookie.match(reg1)) {
          let roleId = unescape(arr1[2]);
          checkRole({roleId: roleId, tocken: tocken}).then(req => {
            let data = req.data;
            var date = new Date();
            var ms = 1000 * 3600 * 1000;
            date.setTime(date.getTime() + ms);
            document.cookie = 'tocken' + "=" + data.tocken + ";expires=" + date.toGMTString();
            document.cookie = "roleId=" + data.currentRole.id + ";expires=" + date.toGMTString();
            sessionStorage.setItem("user", JSON.stringify(data));
            this.$router.push({
              path: "/"
            });
          })
        }
      }

    },
    methods: {
      resetForm() {
        this.$refs.loginForm.resetFields();
      },
      loginIn(ev) {
        // this.$message.error("ewefgg");
        let param = this.loginForm.loginuser;
        param = param + " " + this.loginForm.loginpwd;
        let data;
        this.logining = true
        requestLogin(param).then(req => {
          data = req.data;
          var date = new Date();
          var ms = 1000 * 3600 * 1000;
          date.setTime(date.getTime() + ms);
          document.cookie = 'tocken' + "=" + data.tocken + ";expires=" + date.toGMTString();
          document.cookie = "roleId=" + data.currentRole.id + ";expires=" + date.toGMTString();
          sessionStorage.setItem("user", JSON.stringify(data));
          this.$router.push({
            path: "/"
          });
        }).catch(err => {
          this.logining = false;
          this.$message.error(err.message);
        });
      }
    }
  };
</script>

<style>
  .login-container {
    /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;

  .title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }

  .remember {
    margin: 0px 0px 35px 0px;
  }

  }
  .text-center {
    text-align: center;
  }
</style>
