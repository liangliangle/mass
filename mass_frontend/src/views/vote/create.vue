<!--模板-->
<template>
  <section>
    <el-form ref="form" :model="form" label-width="80px" v-loading="loading">
      <el-form-item label="投票名称">
        <el-input v-model="form.name" style="width: 51%"></el-input>
      </el-form-item>
      <el-form-item label="是否匿名">
        <el-switch v-model="form.isAnon" active-value="0" inactive-value="1"></el-switch>
        <el-tooltip>
          <div slot="content">匿名投票只可查看汇总结果，不可查看成员的投票详情</div>
          <el-button icon="el-icon-info" type="text" style="color: #8c939d"></el-button>
        </el-tooltip>
      </el-form-item>
      <el-form-item label="开始时间">
        <el-date-picker
          v-model="form.startTime"
          type="datetime" style="width: 50%;">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间">
        <el-date-picker
          v-model="form.endTime"
          type="datetime" style="width: 50%;">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="投票范围">
        <el-select v-model="form.range" multiple style="width: 51%;" placeholder="请选择">
          <el-option
            v-for="(item,index) in mass"
            :key="index"
            :label="item.name"
            :value="item.name">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="可投数量">
        <el-input-number v-model="form.voteNum" :min=1 step=1></el-input-number>
      </el-form-item>
      <el-form-item label="选项">
        <el-input v-for="(option, index) in form.optionsDtos" :key="index" v-model="option.thetext" placeholder="选项"
                  style="width: 51%;margin-bottom: 20px"></el-input>
        <el-button type="primary" icon="el-icon-plus" circle @click="add()"></el-button>
      </el-form-item>
      <el-form-item>
        <el-row justify="center">
          <el-col :span="4">
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
  import {create} from "@/service/voteapi";

  import {getAllNoPage} from "@/service/orgapi";

  export default {
    name: "activity",
    //加载组件

    //加载组件
    mounted() {
      //todo 查找全部社团
      getAllNoPage().then(req => {
        this.mass = req.data;
        this.loading = false;
      })
      let user = JSON.parse(sessionStorage.getItem("user"));
      this.form.sno = user.user.username;
      this.form.stuName = user.user.name;
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
          name: null,
          sno: null,
          stuName: null,
          range: [],
          isAnon: 0,
          voteNum: 1,
          optionsDtos: [
            {
              thetext: null
            }
          ]
        }
      };
    },
    methods: {
      add() {
        let falg = true;
        this.form.optionsDtos.map(function (option) {
          if (option.thetext == null || option.thetext == "") {
            falg = false;
          }
        });
        if (falg) {
          this.form.optionsDtos.push({
            thetext: null
          });
        }
      },
      notify(message, type) {
        this.$notify({
          message: message,
          type: type
        });
      },
      onSubmit() {
        this.submitLoading = true;
        create(this.form)
          .then(res => {
            this.$router.push({
              path: "/vote/see"
            });
          })
          .catch(req => {
            this.submitLoading = false;
          });

      },
      perv() {
        this.$router.go(-1);
      }
    }
  };
</script>



