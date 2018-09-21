<!--模板-->
<template>
  <section>
    <el-card style="margin-top:20px" v-loading="loading">
      <div slot="header">
        <span>活动名称{{currentActivity.name}}</span>
        <el-button style="float: right;" v-if="currentActivity.status==3" size="small" @click="toAdd">添加节目</el-button>
      </div>
      <el-table
        :data="this.request"
        style="width: 100%">
        <el-table-column
          prop="name"
          label="名称"
        >
        </el-table-column>
        <el-table-column
          prop="mass"
          label="参加社团">
        </el-table-column>
        <el-table-column
          label="操作">
          <template slot-scope="scope">
            <el-button
              @click="detailDalog(scope.row)"
              type="text"
              size="small">
              详情
            </el-button>
            <el-button
              @click="deleteProgram(scope.row)"
              icon="el-icon-delete" type="text">
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-row justify="center" style="margin-top:20px">
      <el-col :span="4">
        <el-button @click='prev'>上一步</el-button>
      </el-col>
    </el-row>
    <el-dialog title="添加节目" label-width="80px" :visible.sync="dialogFormVisible">
      <el-form ref="form" :model="program" label-width="80px">
        <el-form-item label="活动名称">
          <span>{{currentActivity.name}}</span>
        </el-form-item>
        <el-form-item label="所属社团">
          <el-select v-model="program.massId" placeholder="请选择社团">
            <el-option v-for="(mass ,index) in mass" :key="index" :label="mass.name" :value="mass.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="节目名称">
          <el-input v-model="program.name"></el-input>
        </el-form-item>
        <el-form v-for="(programMem, index) in program.programMemDtoList" :key="index" :inline="true"
                 class="demo-form-inline"
                 label-width="80px">
          <el-form-item label="学号">
            <el-input v-model="programMem.sno" placeholder="输入学号" @blur="find(index)"></el-input>
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
            <el-button type="primary" icon="el-icon-plus" circle @click="addMem()"></el-button>
          </el-form-item>
        </el-form>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="onSubmit">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="节目详情" label-width="80px" :visible.sync="detileVisible">
      <el-dialog
        width="30%"
        title="添加人员"
        label-width="80px"
        :visible.sync="addMemForm"
        append-to-body>
        <el-form>
          <el-form-item label="学号">
            <el-input v-model="mem.sno" placeholder="输入学号" style="width:250px;" @blur="memfind()"></el-input>
          </el-form-item>
          <el-form-item label="姓名">
            <span>{{mem.name}}</span>
          </el-form-item>
          <el-form-item label="参与类型">
            <el-select v-model="mem.type" placeholder="请选择参与类型">
              <el-option label="组织者" value="组织者"></el-option>
              <el-option label="参与者" value="参与者"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="addMemTo">确 定</el-button>
        </div>
      </el-dialog>
      <span style="float: left; margin-top: 10px">节目名称：{{detail.name}}</span>
      <el-button size="small" style="float: right;" @click="toAddMem">添加人员</el-button>
      <el-table :data="detail.programMemberDtoList">
        <el-table-column property="sno" label="学号" width="150"></el-table-column>
        <el-table-column property="name" label="姓名" width="200"></el-table-column>
        <el-table-column property="type" label="类型"></el-table-column>
        <el-table-column
          label="操作">
          <template slot-scope="scope">
            <el-button type="text" icon="el-icon-delete" @click="delMemTo(scope.row)"></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>
  </section>
</template>
<!--JS-->


<script>
  import {getList, delProgram, delMem, addMem} from "@/service/programapi";
  import moment from "moment";
  import {create} from "@/service/programapi";
  import {getAllNoPage} from "@/service/orgapi";
  import {getUserBySno} from "@/service/userapi"
  import {getById as activityById} from "@/service/activityapi"

  export default {
    name: "program",
    data() {
      return {
        addMemForm: false,
        detail: {},
        detileVisible: false,
        activityId: 0,
        currentActivity: {},
        dialogFormVisible: false,
        mass: [],
        loading: true,
        request: [],
        activityId: 0,
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
        mem: {
          id: 0,
          programName: null,
          sno: null,
          name: null,
          type: null
        }
      };
    },
    //加载组件
    mounted() {
      this.activityId = this.$route.params.id
      this.getList();
      getAllNoPage().then(req => {
        this.mass = req.data;
        this.loading = false;
      })
      activityById(this.activityId).then(req => {
        this.currentActivity = req.data;
      })
    },
    components: {},

    methods: {
      //显示内层
      toAddMem() {
        this.addMemForm = true;
      },
      //显示详情
      detailDalog(event) {
        this.detileVisible = true;
        this.detail = event;
      },
      toAdd() {
        this.dialogFormVisible = true;
      },
      onSubmit() {
        this.submitLoading = true;
        for (let m in this.mass) {
          if (this.mass[m].id == this.program.massId) {
            this.program.mass = this.mass[m].name;
          }
        }
        for (let index in this.program.programMemDtoList) {
          this.program.programMemDtoList[index].programName = this.program.name;
        }
        this.program.activitiesId = this.currentActivity.id;
        create(this.program).then(req => {
          this.dialogFormVisible = false;
          this.getList();
        })
      },
      addMem() {
        let falg = true;
        for (let index in this.program.programMemDtoList) {
          let program = this.program.programMemDtoList[index];
          if (program.sno == null || program.name == null) {
            falg = false;
            this.$notify({
              message: "请填写用户,并保证用户学号正确",
              type: "error"
            });
          }
        }
        if (falg) {
          this.program.programMemDtoList.push({
            programName: null,
            type: null
          });
        }
      },
      find(index) {
        let sno = this.program.programMemDtoList[index].sno;
        if (!sno) {
          return
        }
        getUserBySno({username: sno}).then(req => {
          if (req.data) {
            this.program.programMemDtoList[index].name = req.data.name;
            return name;
          } else {
            this.$notify({
              message: "未找到用户",
              type: "warning"
            });
          }
        })
      },
      memfind() {
        let sno = this.mem.sno
        if (!sno) {
          return
        }
        getUserBySno({username: sno}).then(req => {
          if (req.data) {
            this.mem.name = req.data.name;
            this.getData();
            return name;
          } else {
            this.$notify({
              message: "未找到用户",
              type: "warning"
            });
          }
        })
      },
      getList() {
        getList({activityId: this.activityId}).then(req => {
            this.request = req.data;
            this.loading = false;
          }
        )
      },
      addMemTo() {
        let data = this.mem;
        addMem({id: this.detail.id, data: data}).then(req => {
          this.getList();
          this.addMemForm = false;
          this.dialogFormVisible = false;

        })
      },
      delMemTo(event) {
        this.$confirm('此操作将永久删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          delMem(event.id).then(req => {
            this.getList();
            this.addMemForm = false;
            this.dialogFormVisible = false;

          })
        })

      },
      deleteProgram(event) {
        this.$confirm('此操作将永久删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          delProgram(event.id).then(req => {
            this.getList();
            this.addMemForm = false;
            this.dialogFormVisible = false;
          })
        })
      },
      prev() {
        this.$router.go(-1);
      }
    }
  };
</script>



