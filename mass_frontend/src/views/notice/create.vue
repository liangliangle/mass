<!--模板-->
<template>
  <section>
    <el-form ref="form" :model="request" label-width="80px" v-loading="loading">

      <el-form-item label="通知名称">
        <el-input v-model="request.name" style="width: 50%;"></el-input>
      </el-form-item>
      <el-form-item label="通知范围">
        <el-select v-model="request.range" multiple style="width: 50%;" placeholder="请选择">
          <el-option
            v-for="(item,index) in mass"
            :key="index"
            :label="item.name"
            :value="item.name">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="开始时间">
        <el-date-picker
          v-model="request.startTime"
          type="datetime" style="width: 50%;">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间">
        <el-date-picker
          v-model="request.endTime"
          type="datetime" style="width: 50%;">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="通知正文">
        <mavon-editor v-model="request.thetext" ref=md @imgAdd="$imgAdd"></mavon-editor>
      </el-form-item>
      <el-row justify="center">
        <el-col :span="4" :offset="20">
          <el-button type="primary" justify="right" @click="onSubmit" :loading="submitLoading">申请</el-button>
        </el-col>
      </el-row>
    </el-form>
  </section>
</template>
<!--JS-->


<script>
  import {getAllNoPage} from "@/service/orgapi";
  import {uploadFile} from "@/service/fileapi"
  import {create} from "@/service/noticeapi"
  import moment from "moment";

  export default {
    name: "finaAudit",
    data() {
      return {
        submitLoading: false,
        loading: true,
        request: {
          name: null,
          range: [],
          sno: null,
          userName: null,
          thetext: "",
          startTime: null,
          endTime: null
        },
        mass: []
      };
    },
    //加载组件
    mounted() {
      //TODO  不分页查找全部社团
      getAllNoPage().then(req => {
        this.mass = req.data
        this.loading = false;
      })
      let user = JSON.parse(sessionStorage.getItem("user"));
      this.request.sno = user.user.username;
      this.request.userName = user.user.name;
    },
    components: {},

    methods: {
      onSubmit() {
        create(this.request).then(req => {
          this.$router.push({path: "/notice/mycreate"});
        }).catch(req => {

        })
      },
      // 绑定@imgAdd event
      $imgAdd(pos, $file) {
        // 第一步.将图片上传到服务器.
        var formdata = new FormData();
        formdata.append("file", $file);
        uploadFile(formdata).then(req => {

          this.$refs.md.$img2Url(pos, req.data);
        });
      }
    }
  };
</script>



