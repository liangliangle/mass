<template>
  <section>
    <el-card style="margin-top:20px" v-loading="loading">
      <div slot="header">
        <span>个人信息</span>
        <el-button style="float: right;" size="small" @click="dialogVisible">修改信息</el-button>
      </div>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-col :span="24" style="margin-top:20px">学号:{{userinfo.username}}</el-col>
          <el-col :span="24" style="margin-top:20px">姓名:{{userinfo.name}}</el-col>
          <el-col :span="24" style="margin-top:20px">性别:{{userinfo.sex}}</el-col>
          <el-col :span="24" style="margin-top:20px">班级:{{userinfo.grade}}</el-col>
          <el-col :span="24" style="margin-top:20px">邮箱:{{userinfo.email}}</el-col>
          <el-col :span="24" style="margin-top:20px">手机号:{{userinfo.phone}}</el-col>
          <el-col :span="24" style="margin-top:20px">已加入社团:
            <el-tag v-for="(tag,index) in userinfo.sysOrganizations"
                    :key="index" style="margin-left: 5px">
              {{tag.name}}
            </el-tag>
          </el-col>
          <el-col :span="24" style="margin-top:20px">拥有的角色:
            <el-tag
              v-for="(tag,index) in userinfo.sysRoles"
              :key="index" type="info" style="margin-left: 5px">
              {{tag.role}}
            </el-tag>
          </el-col>
        </el-col>
        <el-col :span="12">
          <el-upload
            class="avatar-uploader"
            :show-file-list="false"
            :http-request="myUpload"
            :before-upload="beforeAvatarUpload">
            <img style="border-radius:5%;height: 200px"
                 :src="userinfo.headImg"/>
          </el-upload>
        </el-col>
      </el-row>
    </el-card>
    <el-dialog title="个人信息" label-width="80px" :visible.sync="dialogFormVisible">
      <el-form>
        性别：
        <el-radio-group v-model="sex">
          <el-radio-button label="男"></el-radio-button>
          <el-radio-button label="女"></el-radio-button>
        </el-radio-group>
        <el-form-item label="邮箱">
          <el-input v-model="email" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="phone" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submit">确 定</el-button>
      </div>
    </el-dialog>
  </section>
</template>

<script>
  import {getDetail, update, setImg} from "@/service/userapi"

  import {uploadFile} from "@/service/fileapi"

  export default {
    data() {
      return {
        sno: null,
        userinfo: {},
        loading: true,
        dialogFormVisible: false,
        email: null,
        phone: null,
        sex: null,
        defaultImg: 'https://zut-mass.oss-cn-beijing.aliyuncs.com/2cb9440f-86d6-4f21-98a4-aece8b6740c5.jpg'
      }
    },
    methods: {
      dialogVisible() {
        this.email = this.userinfo.email;
        this.phone = this.userinfo.phone;
        this.sex = this.userinfo.sex
        this.dialogFormVisible = true;
      },
      myUpload(content) {
        // 第一步.将图片上传到服务器.
        console.log(content);
        var formdata = new FormData();
        formdata.append("file", content.file);
        uploadFile(formdata).then(req => {
          setImg({username: this.userinfo.username, url: req.data}).then(req => {
              this.getData();
            }
          )
        }).catch(error => {
          if (error.response) {
            // The request was made and the server responded with a status code
            // that falls out of the range of 2xx
            content.onError('配时文件上传失败(' + error.response.status + ')，' + error.response.data
            )
            ;
          } else if (error.request) {
            // The request was made but no response was received
            // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
            // http.ClientRequest in node.js
            content.onError('配时文件上传失败，服务器端无响应')
            ;
          } else {
            // Something happened in setting up the request that triggered an Error
            content.onError('配时文件上传失败，请求封装失败')
            ;
          }
        })
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      }
      ,
      submit() {
        let emailRole = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        let regexEmail = RegExp(emailRole);
        let phoneRole = "1[3|5|7|8|]\\d{9}"
        let regexPhone = RegExp(phoneRole);
        if (!regexEmail.test(this.email)) {
          this.$notify({
            message: "邮箱格式不正确",
            type: "warning"
          });
          return;
        }
        if (!regexPhone.test(this.phone)) {
          this.$notify({
            message: "手机号格式不正确",
            type: "warning"
          });
          return;
        }
        this.userinfo.email = this.email;
        this.userinfo.phone = this.phone;
        this.userinfo.sex = this.sex;
        update(this.userinfo).then(req => {
          this.dialogFormVisible = false;
        })
      },
      getData() {
        getDetail({username: this.sno}).then(req => {
          this.userinfo = req.data;
          if (!this.userinfo.headImg) {
            this.userinfo.headImg = this.defaultImg;
          }
          this.loading = false;
        })
      }
    },
    mounted() {
      let user = JSON.parse(sessionStorage.getItem("user"));
      this.sno = user.user.username;
      this.getData();
    }
  }
</script>
