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
          <el-button @click.native.prevent="setMass(scope.row)" type="text" size="small">
            更换社长
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--修改密码窗体-->
    <el-dialog title="指定社长" :visible.sync="formVisible" append-to-body width="500px">
      <el-form :model="currentMass" label-width="100px" ref="passForm" >
        <el-form-item label="当前社团" prop="oldPass">
          <span>{{currentMass.name}}</span>
        </el-form-item>
        <el-form-item label="当前社长" prop="newPass">
          <span>{{currentMass.presidentName}}</span>
        </el-form-item>
        <el-form-item label="新社长学号" prop="newPass">
          <el-input auto-complete="off" v-model="newsno"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submit">确认</el-button>
      </div>
    </el-dialog>
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
  import {getAll, setPresident} from "@/service/orgapi";
  import moment from "moment";

  export default {
    name: "finaAudit",
    data() {
      return {
        formVisible: false,
        loading: true,
        query: {
          page: 1
        },
        currentMass: {},
        request: {},
        newsno: null,
      };
    },
    //加载组件
    mounted() {
      getAll(this.query).then(req => {
        this.request = req.data;
        this.loading = false;
      })
    },
    components: {},

    methods: {
      setMass(data) {
        this.formVisible = true;
        this.currentMass = data;
      },
      getPage(event) {
        this.query.offset = (10 * event) - 10;
        this.getData();
      },
      getData() {
        getAll(this.query).then(req => {
          this.request = req.data;
          this.loading = false;
        })
      },
      submit() {
        let orgId = this.currentMass.id;
        let sno = this.newsno;
        setPresident({orgId: orgId, username: sno}).then(req => {
          this.sno = null;
          this.formVisible = false;
          this.getData()

        })

      }
    }
  };
</script>
