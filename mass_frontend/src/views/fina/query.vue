<!--模板-->
<template>
  <section>
    <!--查询过滤条件部分-->
    <el-col :span="24" class="toolbar" style="padding-top:10px;">
      <el-form :inline="true" :model="query" ref="filtersForm">
        <el-form-item label="申请名称" prop="name">
          <el-input v-model="query.name" placeholder="财务名称"></el-input>
        </el-form-item>
        <el-form-item label="活动类型">
          <el-select v-model="query.type" style="width: 100px" placeholder="请选择">
            <el-option label="报销" value="报销"></el-option>
            <el-option label="报备" value="报备"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="创建日期">
          <el-date-picker v-model="query.startTimes" style="width: 150px" type="date" placeholder="选择日期">
          </el-date-picker>
          -
          <el-date-picker v-model="query.endTimes"  style="width: 150px" type="date" placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="query.status" style="width: 150px"  placeholder="请选择">
            <el-option label="待审核" value="1"></el-option>
            <el-option label="待修改" value="2"></el-option>
            <el-option label="待发放" value="3"></el-option>
            <el-option label="完成" value="4"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="toquery">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button @click="resetFilters">重置</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <el-table v-loading="loading" :data="this.request.list" style="width: 100%">
      <el-table-column prop="name" label="名称" width="180">
      </el-table-column>
      <el-table-column prop="massName" label="申请社团">
      </el-table-column>
      <el-table-column label="申请时间">
        <template slot-scope="scope">
          {{scope.row.times / 1000 | moment("YYYY-MM-DD HH:mm") }}
        </template>
      </el-table-column>
      <el-table-column
        prop="type"
        label="类型">
      </el-table-column>
      <el-table-column
        prop="totalFree"
        label="总额">
      </el-table-column>
      <el-table-column
        prop="status"
        label="状态">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status==1" type="warning">
            待审核
          </el-tag>
          <el-tag v-if="scope.row.status==2" type="danger">待修改</el-tag>
          <el-tag v-if="scope.row.status==3" type="info">待发放</el-tag>
          <el-tag v-if="scope.row.status==4" type="success">完成</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="操作">
        <template slot-scope="scope">
          <el-button @click.native.prevent="detile(scope.row.id)" type="text" size="small">
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
          :total="request.total"
          @current-change="getpage"
          @prev-click="getpage"
          @next-click="getpage"
        >
        </el-pagination>
      </el-col>
    </el-row>

  </section>
</template>
<!--JS-->


<script>
  import {query} from "@/service/finaapi";
  import moment from "moment";

  export default {
    name: "finaAudit",
    data() {
      return {
        loading: true,
        request: {},
        query: {
          endTimes: null,
          massId: null,
          massName: null,
          name: null,
          page: 1,
          startTimes: null,
          status: null,
          type: null
        }
      };
    },
    //加载组件
    mounted() {
      this.toquery();
    },
    components: {},
    methods: {
      detile(id) {
        this.$router.push({
          path: "/fina/detail/" + id
        });
      },
      toquery() {
        this.query.page=1
        this.loading=true;
        query(this.query).then(req => {
          this.request = req.data;
          this.loading = false;
        });
      },
      resetFilters() {
        this.query = {
          endTimes: null,
          massId: null,
          massName: null,
          name: null,
          page: 1,
          startTimes: null,
          status: null,
          type: null
        };
      },
      getpage(evet) {
        this.loading = true;
        this.query.page = evet;
        this.toquery();
      },
      detile(id) {
        this.$router.push({
          path: "/fina/detail/" + id
        });
      }
    }
  };
</script>



