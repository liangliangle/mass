<!--模板-->
<template>
  <section>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>班级管理</span>
        <div style="float: right">
          <el-button type="primary" size="small" @click="toAdd">添加</el-button>
        </div>
      </div>
      <el-table v-loading="loading" :data="this.classes" style="width: 100%">
        <el-table-column prop="classes" label="班级">
        </el-table-column>
        <el-table-column prop="type" label="类型">
        </el-table-column>
        <el-table-column prop="status" label="状态">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.status==1" type="warning">正常</el-tag>
            <el-tag v-if="scope.row.status==0">已毕业</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="text" size="small" @click="toDelete(scope.row)">
              删除
            </el-button>
            <el-button type="text" size="small" @click="toEdit(scope.row)">
              修改
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog
      title="添加班级"
      :visible.sync="dialogVisible"
      width="30%">
      <el-form :model="clazz">
        <el-form-item label="班级名称" :label-width="formLabelWidth">
          <el-input v-model="clazz.classes" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="班级类型" :label-width="formLabelWidth">
          <el-input v-model="clazz.type" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="submit">确 定</el-button>
  </span>
    </el-dialog>
    <el-dialog
      title="修改班级"
      :visible.sync="editDialogVisible"
      width="30%">
      <el-form :model="clazz">
        <el-form-item label="班级名称" :label-width="formLabelWidth">
          <span>{{clazz.classes}}</span>
        </el-form-item>
        <el-form-item label="班级类型" :label-width="formLabelWidth">
          <span>{{clazz.type}}</span>
        </el-form-item>
        <el-form-item label="状态" :label-width="formLabelWidth">
          <el-switch
            v-model="clazz.status"
            active-value="0"
            inactive-value="1"
            active-text="已毕业"
            inactive-text="正常">
          </el-switch>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="editDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="editTo">确 定</el-button>
  </span>
    </el-dialog>
  </section>
</template>
<!--JS-->
<script>
  import {getClasses, addClasses, editClasses, deleteClasses} from "@/service/dicapi";

  export default {
    name: "finaAudit",
    data() {
      return {
        editDialogVisible: false,
        dialogVisible: false,
        formLabelWidth: "130px",
        loading: true,
        classes: [],
        clazz: {
          classes: null,
          type: null,
          status: 1
        }

      };
    },
    //加载组件
    mounted() {
      getClasses().then(req => {
        this.classes = req.data;
        this.loading = false;
      });
    },
    components: {},
    methods: {
      toAdd() {
        this.clazz = {
          classes: null,
          type: null,
          status: 1
        };
        this.dialogVisible = true;
      },
      toEdit(data) {
        this.clazz = data;
        this.editDialogVisible = true;
      },
      toDelete(data) {
        this.$confirm('删除' + data.classes + ', 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteClasses({classes: data.classes}).then(req => {
            getClasses().then(req => {
              this.classes = req.data;
            });
          })
        })
      },
      editTo() {
        editClasses(this.clazz).then(req => {
          this.editDialogVisible = false;
        });

      },
      submit() {
        addClasses(this.clazz).then(req => {
          getClasses().then(req => {
            this.classes = req.data;

          });
          this.dialogVisible = false;
        })
      }
    }
  }
  ;
</script>



