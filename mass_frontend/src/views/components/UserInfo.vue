<!--右上角用户信息操作组件-->
<template>
  <section>
    <!--用户信息下拉菜单-->
    <el-dropdown @command="roles">
      <span class="el-dropdown-link">角色：{{user.currentRole.role}}<i
        class="el-icon-arrow-down el-icon--right"></i></span>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item v-for="(item, index) in user.roles" :key="index" :command=item>{{item.role}}
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <!--用户信息下拉菜单-->
    欢迎：
    <el-dropdown @command="dropdownCommand">
      <span class="el-dropdown-link">欢迎：{{user.user.name}}<i class="el-icon-arrow-down el-icon--right"></i></span>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item command="info">个人信息</el-dropdown-item>
        <el-dropdown-item command="modpass">修改密码</el-dropdown-item>
        <el-dropdown-item command="logout" divided>退出</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <span>
      <img style="border-radius:1px;height: 30px;margin-top: 15px"
           :src="user.user.headImg"/>
    </span>

    <!--修改密码窗体-->
    <el-dialog title="修改密码" :visible.sync="modpassFormVisible" append-to-body width="500px">
      <el-form :model="passForm" label-width="100px" ref="passForm" :rules="passRules">
        <el-form-item label="旧密码" prop="oldPass">
          <el-input type="password" auto-complete="off" v-model="passForm.oldPass"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPass">
          <el-input type="password" auto-complete="off" v-model="passForm.newPass"></el-input>
        </el-form-item>
        <el-form-item label="确认新密码" prop="newPassConfirm">
          <el-input type="password" auto-complete="off" v-model="passForm.newPassConfirm"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="modpassSubmit">确认</el-button>
      </div>
    </el-dialog>
  </section>
</template>

<script>

  import {checkRole} from "@/service/api"

  import {resetpwd} from "@/service/userapi"

  export default {
    data() {
      return {
        user: {},
        modpassFormVisible: false,
        passForm: {
          oldPass: '',
          newPass: '',
          newPassConfirm: ''
        },
        passRules: {
          oldPass: [
            {required: true, message: '旧密码不能为空', trigger: 'blur'}
          ],
          newPass: [
            {required: true, message: '新密码不能为空', trigger: 'blur'}
          ],
          newPassConfirm: [
            {required: true, message: '确认新密码不能为空', trigger: 'blur'}
          ]
        },
      }
    },
    mounted() {
      let user = JSON.parse(sessionStorage.getItem('user'));
      this.user = user;
    },
    methods: {
      roles(event) {
        checkRole({roleId: event.id}).then(req => {
          let data = req.data;
          sessionStorage.setItem("user", JSON.stringify(data));
          let curWwwPath = window.document.location.href;
          //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
          let pathName = window.document.location.pathname;
          let pos = curWwwPath.indexOf(pathName);
          //获取主机地址，如： http://localhost:8083
          let localhostPaht = curWwwPath.substring(0, pos);

          location.replace(localhostPaht);
        });
      },
      dropdownCommand(command) {
        if (command == "logout") {
          this.$confirm('确认退出系统吗?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            sessionStorage.removeItem('user');
            document.cookie = "tocken"+"=;expires="+(new Date(0)).toGMTString();
            document.cookie = "roleId"+"=;expires="+(new Date(0)).toGMTString();
            this.$router.push({path: '/login'});
          }).catch(() => {
          });
        }
        if (command == "info") {
          this.$router.push({path: "/userinfo"});
        }
        if (command == "modpass") {
          this.modpassFormVisible = true;
        }
      },
      modpassSubmit() {
        if (this.$refs.passForm.validate((valid) => {
            if (valid) {
              let data = {
                username: this.user.user.username,
                newPassword: this.passForm.newPass,
                oldPwd: this.passForm.oldPass
              }
              resetpwd(data).then(req => {
                this.$notify({
                  message: '密码修改成功',
                  type: 'success'
                });
                this.modpassFormVisible = false;
                this.passForm = {
                  oldPass: '',
                  newPass: '',
                  newPassConfirm: ''
                }
              })
            }
          })) ;
      }
    }
  }
</script>

<style>
  .el-dropdown-link {
    cursor: pointer;
    color: #8a8f8e;
  }

  .el-icon-arrow-down {
    font-size: 12px;
  }
</style>
