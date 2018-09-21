<!--模板-->
<template>
  <section>
    <el-form ref="form" :model="form" label-width="80px" v-loading="loading">
      <el-form-item label="活动名称">
        <el-input v-model="form.name" style="width: 300px;"></el-input>
      </el-form-item>
      <el-form-item label="活动类型">
        <el-select v-model="form.type" placeholder="请选择活动类型">
          <el-option label="内部活动" value="内部活动"></el-option>
          <el-option label="院级活动" value="院级活动"></el-option>
          <el-option label="校级活动" value="校级活动"></el-option>
          <el-option label="其他活动" value="其他活动"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="主办社团">
        <el-select v-model="form.sponsorMass" placeholder="请选择活动社团">
          <el-option v-for="massname in mass" :key="massname" :label="massname" :value="massname"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="活动时间">
        <el-col :span="11">
          <el-date-picker type="datetime" placeholder="选择日期" v-model="form.holdTime"
                          style="width: 100%;"></el-date-picker>
        </el-col>
      </el-form-item>
      <el-form-item label="活动地点">
        <el-input v-model="form.place"></el-input>
      </el-form-item>
      <el-form-item label="申请书">
        <mavon-editor v-model="form.value" ref=md  @imgAdd="$imgAdd"></mavon-editor>
      </el-form-item>
      <el-form-item>
        <el-row justify="center">
          <el-col :span="4">
            <el-button @click="perv" v-if="!form.id" disabled>上一步</el-button>
            <el-button @click="perv" v-if="form.id">上一步</el-button>
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
  import {getById, createAvtivity, editAvtivity} from "@/service/activityapi";
  import {uploadFile} from "@/service/fileapi"

  export default {
    name: "activity",
    //加载组件

    //加载组件
    mounted() {
      let user = JSON.parse(sessionStorage.getItem("user"));
      if (user.manageOrg) {
        this.mass.push(user.manageOrg.name);
      } else {
        this.mass = ["全部社团"];
      }
      if (this.$route.params.id) {
        getById(this.$route.params.id).then(req => {
          this.form = req.data;
        });
      }
      this.loading = false;
    },
    components: {},
    data() {
      return {
        loading: true,
        toolbars: {
          save: false // 保存（触发events中的save事件）
        },
        submitLoading: false,
        mass: [],
        form: {
          description: "",
          doctype: 1,
          holdTime: "",
          name: "",
          place: "",
          sponsorMass: "",
          type: "",
          value: ""
        }
      };
    },
    methods: {
      notify(message, type) {
        this.$notify({
          message: message,
          type: type
        });
      },
      onSubmit() {
        this.submitLoading = true;
        if (this.form.id) {
          editAvtivity(this.form)
            .then(res => {
              this.$router.push({path: "/activity/mymass"});
            })
            .catch(req => {
              this.notify(req.message, "warning");
              this.submitLoading = false;
            });
        } else {
          createAvtivity(this.form)
            .then(res => {
              this.$router.push({path: "/activity/mymass"});
            })
            .catch(req => {
              this.submitLoading = false;
            });
        }
      },
      perv() {
        this.$router.go(-1);
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



