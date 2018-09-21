<!--模板-->
<template>
  <section>
    <el-card style="margin-top:20px" v-loading="loading">
      <div slot="header">
        <span>主要信息</span>
      </div>
      <el-row :gutter="20">
        <el-col :span="8" style="margin-top:20px">批次名称:{{fina.name}}</el-col>
        <el-col :span="8" style="margin-top:20px">申请社团:{{fina.massName}}</el-col>
        <el-col :span="8" style="margin-top:20px">申请时间:{{fina.times / 1000 | moment("YYYY-MM-DD HH:mm") }}</el-col>
        <el-col :span="8" style="margin-top:20px">申请类型:{{fina.type}}</el-col>
        <el-col :span="16" style="margin-top:20px">总额:{{fina.totalFree}}</el-col>
      </el-row>
    </el-card>
    <el-card style="margin-top:20px" v-loading="loading">
      <div slot="header">
        <span>物资信息</span>
      </div>
      <el-table
        v-loading="loading"
        :data="fina.goodsDtoList"
        style="width: 100%">
        <el-table-column
          prop="id"
          label="编号"
          width="180">
        </el-table-column>
        <el-table-column
          prop="name"
          label="名称"
          width="180">
        </el-table-column>
        <el-table-column
          prop="num"
          label="数量"
          width="180">
        </el-table-column>
        <el-table-column
          prop="price"
          label="单价"
          width="180">
        </el-table-column>
        <el-table-column
          prop="price"
          label="价格"
          width="180">
          <template slot-scope="scope">
            {{scope.row.price * scope.row.num }}
          </template>
        </el-table-column>
        <el-table-column
          prop="isExpendable"
          label="是否一次用品">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.isExpendable==1" type="info">是</el-tag>
            <el-tag v-if="scope.row.isExpendable!=1" type="info">否</el-tag>
          </template>
        </el-table-column>
      </el-table>

    </el-card>
    <el-row justify="center" style="margin-top:20px">
      <el-col :span="4">
        <el-button @click='prev'>上一步</el-button>
      </el-col>
    </el-row>
  </section>
</template>
<!--JS-->


<script>
  import {getById, audit} from "@/service/finaapi";

  export default {
    name: "finaDetail",
    //加载组件
    components: {},
    data() {
      return {
        loading: true,
        submitLoading: false,
        fina: {}
      };
    },
    //加载组件
    mounted() {
      this.loading = true;
      getById(this.$route.params.id)
        .then(req => {
          this.fina = req.data;
          this.loading = false;
        })
        .catch(req => {
          this.loading = false;
        });
    },
    methods: {
      getpage() {
      },
      prev() {
        this.$router.go(-1);
      },
      notify(message, type) {
        this.$notify({
          message: message,
          type: type
        });
      },
      success() {
        this.submitLoading = true;
        audit({id: this.fina.id, status: 1})
          .then(req => {
            this.notify("审核成功", "success");
            this.prev();
            this.submitLoading = false;
          })
          .catch(req => {
            this.submitLoading = false;
          });
      },
      error() {
        audit({id: this.fina.id, status: 0})
          .then(req => {
            this.notify("审核成功", "success");
            this.prev();
          })
          .catch(req => {
          });
      }
    }
  };
</script>



