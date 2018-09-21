<template>
  <section>
    <el-row :gutter="20" style="padding:10px;">
      <el-col :span="24">
        <el-col :span="12">
          <el-card style="height:500px;" :loading="loading">
            <div style="height:10px;">
              <span>投票结果(柱状图)</span>
            </div>
            <el-col :span="24" id="chartSale" style="height:500px;">
            </el-col>
          </el-card>
        </el-col>
        <el-col :span="12">
          <el-card style="height:500px;" :loading="loading">
            <div style="height:10px;">
              <span>投票结果(饼状图)</span>
            </div>
            <div id="sitein" style="height:500px;">
            </div>
          </el-card>
        </el-col>
      </el-col>
    </el-row>
    <el-row v-if="resultData" :gutter="20" style="padding:10px;">
      <el-col :span="24">
        <el-card :loading="loadingdata">
          <div>
            <span>结果集</span>
          </div>
          <el-table v-loading="loadingdata" :data="this.resultData.list" style="width: 100%">
            <el-table-column prop="userName" label="人员名称">
            </el-table-column>
            <el-table-column prop="option" label="选项">
            </el-table-column>
            <el-table-column prop="option" label="投票时间">
              <template slot-scope="scope">
                {{scope.row.createTime / 1000 | moment("YYYY-MM-DD HH:mm") }}
              </template>
            </el-table-column>
          </el-table>
          <el-row justify="center" style="margin-top:10px">
            <el-col :span="4">
            </el-col>
            <el-col :span="4" :offset="16">
              <el-pagination
                layout="prev, pager, next"
                :total="resultData.total"
                @current-change="getPage"
                @prev-click="getPage"
                @next-click="getPage">
              </el-pagination>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <el-row justify="center" style="margin-top:20px">
      <el-col :span="4">
        <el-button @click="perv">上一步</el-button>
      </el-col>
    </el-row>
  </section>
</template>

<script>
  import echarts from 'echarts'
  import {groupById, getResult, getById} from "@/service/voteapi"

  export default {
    data() {
      return {
        loading: true,
        result: [],
        loadingdata: true,
        resultData: null
      }
    },
    methods: {
      getPage(event) {
        getResult({id: id, page: event - 1}).then(req => {
          this.resultData = req.data;
          this.loadingdata = false;
        })
      },
      perv() {
        this.$router.go(-1);
      },
      drawLine() {
        // 基于准备好的dom，初始化echarts实例
        let siteIn = echarts.init(document.getElementById('sitein'));
        let chartSale = echarts.init(document.getElementById('chartSale'));
        let xAxisData = [];
        let yAxisData = [];
        let data = this.result;
        let pieData = [];
        for (let index in data) {
          xAxisData.push(data[index].value);
          yAxisData.push(data[index].count);
          let tmp = {value: null, name: null};
          tmp.value = data[index].count;
          tmp.name = data[index].value;
          pieData.push(tmp)
        }
        // 绘制图表
        chartSale.setOption({
          color: ['#3398DB'],
          tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
              type: 'line'        // 默认为直线，可选为：'line' | 'shadow'
            }
          },
          grid: {
            left: '1%',
            right: '5%',
            bottom: '20%',
            containLabel: true
          },
          xAxis: [
            {
              type: 'category',
              data: xAxisData,
              axisTick: {
                alignWithLabel: true
              }
            }
          ],
          yAxis: [
            {
              type: 'value'
            }
          ],
          series: [
            {
              name: '直接访问',
              type: 'bar',
              barWidth: '30%',
              data: yAxisData
            }
          ]
        });
        // 绘制图表
        siteIn.setOption({
          series: [
            {
              type: 'pie',
              radius: '55%',
              data: pieData
            }
          ]
        })
      }
    },
    mounted() {
      if (this.$route.params.id) {
        let id = this.$route.params.id;
        groupById(id).then(req => {
          this.result = req.data;
          this.loading = false;
          this.drawLine();
        });
        getById(id).then(req => {
          let vote = req.data;
          if (vote.isAnon == 0) {
            getResult({id: id, page: 0}).then(req => {
              this.resultData = req.data;
              this.loadingdata = false;
            })
          }
        })

      }

    }
  }
</script>
