<!--模板-->
<template>
  <section>
    <!--查询过滤条件部分-->
    <el-col :span="24" class="toolbar" style="padding-top:10px;">
      <el-form :inline="true" :model="query" ref="filtersForm">
        <el-form-item label="学生姓名" prop="name">
          <el-input v-model="query.name" placeholder="学生姓名"></el-input>
        </el-form-item>
        <el-form-item label="学生学号" prop="username">
          <el-input v-model="query.username" placeholder="学生学号"></el-input>
        </el-form-item>
        <el-form-item label="班级" prop="classes">
          <el-input v-model="query.classes" placeholder="班级"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="queryChange">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button @click="resetFilters">重置</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>用户管理</span>
        <div style="float: right;margin-right: 5px">
          <el-upload
            style="float: right"
            class="upload-demo"
            multiple
            action=""
            :show-file-list="false"
            :http-request="myUpload"
            accept="xls"
            :limit="3">
            <el-button   style="float: right;margin-right: 10px" size="small" type="primary">导入</el-button>
          </el-upload>
          <el-button style="margin-left: 30px;" size="small" @click="exportFile">导出</el-button>
        </div>
      </div>
      <el-table v-loading="loading" :data="this.request.list" style="width: 100%">
        <el-table-column prop="username" label="学号" width="180">
        </el-table-column>
        <el-table-column prop="name" label="姓名">
        </el-table-column>
        <el-table-column prop="sex" label="性别">
        </el-table-column>
        <el-table-column prop="grade" label="班级">
        </el-table-column>
        <el-table-column prop="locked" label="状态">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="text" size="small" @click="detail(scope.row)">
              详情
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-row justify="center" style="margin-top:10px">
        <el-col :span="4">
        </el-col>
        <el-col :span="4" :offset="16">
          <el-pagination
            layout="prev, pager, next"
            :total="this.request.total"
            @current-change="getpage"
            @prev-click="getpage"
            @next-click="getpage">
          </el-pagination>
        </el-col>
      </el-row>
    </el-card>
    <el-dialog title="用户详情" :visible.sync="dialogDetailVisible">
      <el-row :gutter="20">
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
        <el-col :span="24" style="margin-top:20px">当前角色:
          <el-tag
            v-for="(tag,index) in userinfo.sysRoles"
            :key="index" type="info" style="margin-left: 5px">
            {{tag.role}}
          </el-tag>
          <el-button type="text" size="small" @click="toEditRole()">修改</el-button>
        </el-col>
      </el-row>
      <el-dialog
        width="30%"
        title="修改角色"
        :visible.sync="editRoleVisible"
        append-to-body>
        <el-checkbox-group
          v-model="currentRole">
          <el-checkbox v-for="(o,index) in role" :label="o.role" :key="o.id">{{o.role}}</el-checkbox>
        </el-checkbox-group>
        <div slot="footer" class="dialog-footer">
          <el-button @click="editRoleVisible = false">取 消</el-button>
          <el-button type="primary" @click="editRoleTo()">确 定</el-button>
        </div>
      </el-dialog>
    </el-dialog>
  </section>
</template>
<!--JS-->
<script>
  import {getAll, getDetail, importFile, exportFile} from "@/service/userapi";
  import {getFilteRole, setUserRole} from "@/service/systemapi"
  import moment from "moment";
  import ElButton from "../../../node_modules/element-ui/packages/button/src/button.vue";

  export default {
    name: "finaAudit",
    data() {
      return {
        dialogDetailVisible: false,
        editRoleVisible: false,
        loading: true,
        request: {},
        dialogVisible: false,
        query: {
          classes: null,
          name: null,
          page: 0,
          status: null,
          username: null
        },
        userinfo: {},
        role: [],
        currentRole: []
      };
    },
    //加载组件
    mounted() {
      this.getData();
      getFilteRole().then(req => {
        this.role = req.data;
      })
    },
    components: {ElButton},
    methods: {
      myUpload(content) {
        // 第一步.将图片上传到服务器.
        console.log(content);
        var formdata = new FormData();
        formdata.append("fileName", content.file);
        importFile(formdata).then(req => {
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
      exportFile() {
        exportFile();
      },
      getData() {
        this.loading = true;
        getAll(this.query).then(req => {
          this.request = req.data;
          this.loading = false;
        });
      },
      getpage(evet) {
        this.loading = true;
        this.query.page = evet;
        getAll(this.query).then(req => {
          this.request = req.data;
          this.loading = false;
        });
      },
      detail(data) {
        this.dialogDetailVisible = true;
        getDetail({username: data.username}).then(req => {
          this.userinfo = req.data;
        })
      },
      resetFilters() {
        this.query.classes = null;
        this.query.name = null;
        this.query.username = null;
        this.getData();
      },
      toEditRole(data) {
        this.currentRole = [];
        console.log(this.userinfo);
        for (let index in this.userinfo.sysRoles) {
          console.log(this.userinfo.sysRoles[index])
          this.currentRole.push(this.userinfo.sysRoles[index].role)
        }
        this.editRoleVisible = true;

      },
      queryChange() {
        this.query.page = 1
        this.getData();
      },
      editRoleTo() {
        let roleNames = this.currentRole;
        console.log(roleNames);
        let roleId = [];
        for (let index1 in roleNames) {
          for (let index2 in this.role) {
            if (roleNames[index1] == this.role[index2].role) {
              roleId.push(this.role[index2].id);
            }
          }
        }
        let data = {
          username: this.userinfo.username,
          roleId: roleId
        }
        setUserRole(data).then(req => {
          this.editRoleVisible = false;
          getDetail({username: data.username}).then(req => {
            this.userinfo = req.data;
          })
        })

      }
    }
  };
</script>



