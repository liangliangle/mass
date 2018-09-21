<!--模板-->
<template>
  <section>
    <el-table v-loading="loading" :data="this.request.list" style="width: 100%">
      <el-table-column prop="name" label="名称">
      </el-table-column>
      <el-table-column prop="petName" label="别名" width="180">
      </el-table-column>
      <el-table-column
        prop="presidentName"
        label="社长">
      </el-table-column>
      <el-table-column
        label="操作">
        <template slot-scope="scope">
          <el-button @click.native.prevent="toAddMass(scope.row)" type="text" size="small">
            申请加入
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
          :total="request.total"
          @current-change="getPage"
          @prev-click="getPage"
          @next-click="getPage">
        </el-pagination>
      </el-col>
    </el-row>
  </section>
</template>
<!--JS-->


<script>
  import {getAll, addMass} from "@/service/orgapi";
  import moment from "moment";

  export default {
    name: "finaAudit",
    data() {
      return {
        sno: null,
        formVisible: false,
        loading: true,
        query: {
          page: 1
        },
        currentMass: {},
        request: {},
        dto: {
          applyReason: null,
          grade: null,
          orgId: 0,
          orgName: null,
          username: null
        }
      };
    },
    //加载组件
    mounted() {
      let user = JSON.parse(sessionStorage.getItem("user"));
      this.sno = user.user.username;
      this.dto.grade = user.user.grade;
      this.getData()
    },
    components: {},

    methods: {
      toAddMass(data) {
        this.dto.orgId = data.id;
        this.dto.orgName = data.name;
        this.dto.username = this.sno;
        this.$prompt("加入理由", "提示", {
          confirmButtonText: "提交",
          cancelButtonText: "取消"
        }).then(({value}) => {
          this.dto.applyReason = value;
          addMass(this.dto).then(req => {
            this.$notify({
              message: "申请已提交，请等待审核",
              type: "success"
            });
          })
        })
          .catch(() => {
          });
      },
      getPage(event) {
        this.query.page = event
        this.getData();
      },
      getData() {
        getAll(this.query).then(req => {
          this.request = req.data;
          this.loading = false;
        })
      }
    }
  };
</script>



