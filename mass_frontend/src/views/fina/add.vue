<!--模板-->
<template>
  <section>
    <el-form ref="form" :model="form" label-width="80px" v-loading="loading">
      <el-form-item label="申请名称">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="申请类型">
        <el-select v-model="form.type" placeholder="请选择报销类型">
          <el-option label="报销" value="报销"></el-option>
          <el-option label="报备" value="报备"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="申请社团">
        <el-select v-model="form.massName" placeholder="请选择社团">
          <el-option v-for="massname in mass" :key="massname" :label="massname" :value="massname"></el-option>
        </el-select>
      </el-form-item>
      <el-form v-for="(good, index) in form.goodsDtoList" :key="index" :inline="true" class="demo-form-inline">
        <el-form-item label="物资名称">
          <el-input v-model="good.name" placeholder="物资名称"></el-input>
        </el-form-item>
        <el-form-item label="是否一次性">
          <el-switch v-model="good.isExpendable" active-value=0  inactive-value="1"></el-switch>
        </el-form-item>
        <el-form-item label="数量">
          <el-input-number v-model="good.num"  placeholder="数量"></el-input-number>
        </el-form-item>
        <el-form-item label="单价">
          <el-input-number v-model="good.price"  placeholder="单价"></el-input-number>
        </el-form-item>
        <el-form-item>
          <el-button type="danger" v-if="index != form.goodsDtoList.length-1" icon="el-icon-delete" circle
                     @click="remove(index)"></el-button>
          <el-button type="primary" v-if="index == form.goodsDtoList.length-1" icon="el-icon-plus" circle
                     @click="add()"></el-button>
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
  import {getById, create, edit} from "@/service/finaapi";

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
          activitiesId: null,
          activitiesName: null,
          goodsDtoList: [
            {
              isExpendable: null,
              name: "",
              num: 0,
              price: 0
            }
          ],
          massId: null,
          massName: null,
          name: null,
          totalFree: null,
          type: null
        }
      };
    },
    methods: {
      add() {
        let falg = true;
        this.form.goodsDtoList.map(function (good) {
          if (good.name == "") {
            falg = false;
          }
        });
        if (falg) {
          this.form.goodsDtoList.push({
            isExpendable: null,
            name: "",
            num: 0,
            price: 0
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
        if (this.form.id) {
          edit(this.form)
            .then(res => {
              this.$router.push({
                path: "/fina/mymass"
              });
            })
            .catch(req => {
              this.submitLoading = false;
            });
        } else {
          this.form.massId = 0;
          create(this.form)
            .then(res => {
              this.$router.push({
                path: "/fina/mymass"
              });
            })
            .catch(req => {
              this.submitLoading = false;
            });
        }
      },
      remove(i) {
        console.log(i);
        let oldList = this.form.goodsDtoList;
        let newList = [];
        for (let index in oldList) {
          console.log(index);
          if (index != i) {
            newList.push(oldList[index]);
          }
        }
        this.form.goodsDtoList = newList;
      },
      perv() {
        this.$router.go(-1);
      }
    }
  };
</script>



