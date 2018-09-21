<!--模板-->
<template>
  <section>
    <el-card class="box-card" :loading="loading">
      <div slot="header" class="clearfix">
        <span>详情</span>
      </div>
      <el-tree :data="menus" show-checkbox node-key="id" highlight-current :check-strictly="checkStrictly" ref="tree"
               :props="defaultProps">
      </el-tree>
    </el-card>
    <template>
      <el-row justify="center">
        <el-col :span="4">
          <el-button @click="perv">上一步</el-button>
        </el-col>
        <el-col :span="4" :offset="16">
          <el-button type="primary" justify="right" @click="onSubmit" :loading="submitLoading">提交</el-button>
        </el-col>
      </el-row>
    </template>
  </section>
</template>
<!--JS-->
<script>
  import {
    getMenus,
    getRoleById,
    addRoleMenus
  } from "@/service/systemapi";
  import moment from "moment";

  export default {
    name: "finaAudit",
    data() {
      return {
        checkStrictly: true,
        loading: true,
        request: {},
        submitLoading: false,
        menus: [],
        dialogVisible: false,
        rolesmenus: [],
        defaultProps: {
          children: 'children',
          label: 'name'
        },
        roleMenusChindle: [],
        setLoading: false
      };
    },
    //加载组件
    mounted() {
      this.loading = true;
      let id = this.$route.params.id;
      getMenus().then(req => {
        this.menus = req.data;
        this.roleMenusIds = [];
        getRoleById({
          roleId: id
        }).then(req => {
          this.rolesmenus = req.data;

          if (this.rolesmenus) {
            for (let e1 in this.rolesmenus) {
              this.roleMenusChindle.push(this.rolesmenus[e1]);
              if (this.rolesmenus[e1].children) {
                for (let e2 in this.rolesmenus[e1].children) {
                  this.roleMenusChindle.push(this.rolesmenus[e1].children[e2]);
                }
              }
            }
          }
          this.loading = true;
          this.$refs.tree.setCheckedNodes(this.roleMenusChindle);
        });
      })
    },
    components: {},
    methods: {
      onSubmit() {
        this.submitLoading = true;
        let id = this.$route.params.id;
        let newMenus = this.$refs.tree.getCheckedNodes();
        let newMenusIds = [];
        for (let menu in newMenus) {
          newMenusIds.push(newMenus[menu].id)
        }
        addRoleMenus({
          roleId: id,
          resourceIds: newMenusIds
        }).then(
          req => {
            this.submitLoading = false;
            this.$router.go(-1);
          }
        )
      },
      perv() {
        this.$router.go(-1);
      },
      check(data, node, event) {
      }
    }
  };
</script>



