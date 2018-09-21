<!--模板-->
<template>
  <section>
    <el-table v-loading="loading" :data="this.request.list" style="width: 100%">
      <el-table-column prop="role" label="角色名称" width="180">
      </el-table-column>
      <el-table-column prop="description" label="备注">
      </el-table-column>
      <el-table-column label="操作">
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
          :total="this.request.total"
          @current-change="getpage"
          @prev-click="getpage"
          @next-click="getpage">
        </el-pagination>
      </el-col>
    </el-row>
  </section>
</template>
<!--JS-->
<script>
  import {
    getRole,
    getMenus,
    getRoleById
  } from "@/service/systemapi";
  import moment from "moment";

  export default {
    name: "finaAudit",
    data() {
      return {
        loading: true,
        request: {},
        menus: [],
        dialogVisible: false,
        rolesmenus: [],
        defaultProps: {
          children: 'children',
          label: 'name'
        },
        roleMenusIds: [],
        setLoading: false,
        editRole: {},
        formLabelWidth: '120px',
        query: {
          page: 1
        }
      };
    },
    //加载组件
    mounted() {
      getRole(this.query).then(req => {
        this.request = req.data;
        this.loading = false;
      });
      getMenus().then(req => {
        this.menus = req.data;
      })
    },
    components: {},
    methods: {
      edit(event) {
        this.editRole = event;
        this.dialogVisible = true;
      },
      detile(id) {
        this.$router.push({
          path: "/system/roledetile/" + id
        });
      },
      getpage(evet) {
        this.loading = true;
        this.query.page = event;
        getRole(this.query).then(req => {
          this.request = req.data;
          this.loading = false;
        });
      }
    }
  };
</script>



