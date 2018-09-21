<!--模板-->
<template>
  <section>
    <!--查询过滤条件部分-->
    <el-col :span="24" class="toolbar" style="padding-top:10px;">
      <el-form :inline="true" :model="query" ref="filtersForm">
        <el-form-item label="活动名称" prop="name">
          <el-input v-model="query.name" placeholder="活动名称"></el-input>
        </el-form-item>
        <el-form-item label="活动类型">
          <el-select v-model="query.type" placeholder="请选择" style="width: 150px">
            <el-option label="全部" value=null ></el-option>
            <el-option label="内部活动" value="内部活动"></el-option>
            <el-option label="院级活动" value="院级活动"></el-option>
            <el-option label="校级活动" value="校级活动"></el-option>
            <el-option label="其他活动" value="其他活动"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="创建日期">
          <el-date-picker
            v-model="query.holdStartTime"
            type="date"
            style="width: 150px"
            placeholder="选择日期">
          </el-date-picker>
          -
          <el-date-picker
            v-model="query.holdEndTime"
            type="date"
            style="width: 150px"
            placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="query.status" placeholder="请选择" style="width: 150px">
            <el-option label="全部"></el-option>
            <el-option label="终止" value="0"></el-option>
            <el-option label="待审核" value="1"></el-option>
            <el-option label="待修改" value="2"></el-option>
            <el-option label="准备中" value="3"></el-option>
            <el-option label="结束" value="4"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="queryChange">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button @click="resetFilters">重置</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <el-table
      v-loading="loading"
      :data="this.request.list"
      style="width: 100%">
      <el-table-column
        prop="name"
        label="名称"
        width="180">
      </el-table-column>
      <el-table-column
        prop="sponsorMass"
        label="主办社团">
      </el-table-column>
      <el-table-column
        label="举办时间">
        <template slot-scope="scope">
          {{scope.row.holdTime / 1000 | moment("YYYY-MM-DD HH:mm") }}
        </template>
      </el-table-column>
      <el-table-column
        prop="type"
        label="活动类型">
      </el-table-column>
      <el-table-column
        prop="status"
        label="状态">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status==0" type="danger">终止</el-tag>
          <el-tag v-if="scope.row.status==1" type="warning">待审核</el-tag>
          <el-tag v-if="scope.row.status==2" type="info">待修改</el-tag>
          <el-tag v-if="scope.row.status==3">准备中</el-tag>
          <el-tag v-if="scope.row.status==4" type="success">结束</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="操作">
        <template slot-scope="scope">
          <el-button
            @click.native.prevent="detile(scope.row.id)"
            type="text"
            size="small">
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
  import {queryActivity} from "@/service/activityapi";
  import moment from "moment";

  export default {
    name: "activity",
    data() {
      return {
        query: {
          holdEndTime: null,
          holdStartTime: null,
          name: null,
          page: 1,
          sponsorMass: null,
          status: null,
          type: null
        },
        loading: true,
        request: {}
      };
    },
    //加载组件
    mounted() {
      queryActivity({page: 1}).then(req => {
        this.request = req.data;
        this.loading = false;
      });
    },
    components: {},

    methods: {
      resetFilters() {
        this.query = {
          holdEndTime: null,
          holdStartTime: null,
          name: null,
          page: this.query.page,
          sponsorMass: null,
          status: null,
          type: null
        };
      },
      queryChange() {
        this.query.page=1
        this.loading = true;
        queryActivity(this.query).then(req => {
          this.request = req.data;
          this.loading = false;
        });
      },
      getpage(evet) {
        this.loading = true;
        queryActivity({page: evet}).then(req => {
          this.query.page = evet;
          this.request = req.data;
          this.loading = false;
        });
      },
      detile(id) {
        this.$router.push({path: "/activity/detail/" + id});
      }
    }
  };
</script>



