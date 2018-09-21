<!--模板-->
<template>
  <section>
    <el-form ref="form" :model="form" label-width="80px" v-loading="loading">
      <el-form-item label="所属社团">
        <el-select v-model="program.massId" placeholder="请选择社团">
          <el-option v-for="(mass ,index) in mass" :key="index" :label="mass.name" :value="mass.id"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="活动名称">
        <el-input v-model="form.name"></el-input>
      </el-form-item>

      <el-form-item label="节目名称">
        <el-input v-model="program.name"></el-input>
      </el-form-item>
      <el-form v-for="(programMem, index) in program.programMemDtoList" :key="index" :inline="true"
               class="demo-form-inline"
               label-width="80px">
        <el-form-item label="学号">
          <el-input v-model="programMem.sno" placeholder="输入学号"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="query(index)">查找</el-button>
        </el-form-item>
        <el-form-item label="姓名">
          <span>{{programMem.name}}</span>
        </el-form-item>
        <el-form-item label="参与类型">
          <el-select v-model="programMem.type" placeholder="请选择参与类型">
            <el-option label="组织者" value="组织者"></el-option>
            <el-option label="参与者" value="参与者"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-plus" circle @click="add()"></el-button>
        </el-form-item>

      </el-form>
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
  import {create} from "@/service/programapi";
  import {getAll} from "@/service/orgapi";
  import {getUserBySno} from "@/service/userapi"

  import {getById as activityById} from "@/service/activityapi"

  export default {
    name: "activity",
    //加载组件

    //加载组件
    mounted() {
      this.loading = true;
      if (this.$route.params.id) {
        this.program.activitiesId=this.$route.params.id
        activityById(this.$route.params.id).then(req => {
          this.form = req.data;
          getAll({offset: 0, limit: 1000}).then(req => {
            this.mass = req.data.data.listData;
            this.loading = false;
          })
        });
      }
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
        program: {
          activitiesId: null,
          id: 0,
          mass: null,
          massId: null,
          name: null,
          programMemDtoList:
            [{
              id: 0,
              programName: null,
              sno: null,
              name: null,
              type: null
            }]
        },
        form: {}
      };
    },
    methods: {
      query(index) {
        let programMem = this.program.programMemDtoList[index];
        getUserBySno({username: programMem.sno}).then(req => {
          if (req.data.data) {
            this.program.programMemDtoList[index].name = req.data.data.name;
          } else {
            this.$notify({
              message: "未找到用户",
              type: "warning"
            });

          }
        })
      },
      add() {
        let falg = true;
        this.program.programMemDtoList.map(function (program) {
          if (program.programName == "") {
            falg = false;
          }
        });
        if (falg) {
          this.program.programMemDtoList.push({
            programName: null,
            type: null
          });
        }
      },
      onSubmit() {
        this.submitLoading = true;
        for (let m in this.mass) {
          if (this.mass[m].id == this.program.massId) {
            this.program.mass = this.mass[m].name;
          }
        }
        create(this.program).then(req => {
          this.$router.go(-1);
        }).cache(err => {
          this.$message.error(err.message);
        })
      },
      perv() {
        this.$router.go(-1);
      }
    }
  };
</script>



