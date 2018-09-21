<!--面包屑组件-->
<template>
  <section >
    <!--读取roter显示为面包屑项-->
    <el-breadcrumb separator="/" style="margin-left:20px;margin-top: 10px;height: 45px;" separator-class="el-icon-arrow-right">
      <el-breadcrumb-item v-for="item in $route.matched" :key="item.path">
        {{ item.name }}
      </el-breadcrumb-item>
    </el-breadcrumb>
  </section>
</template>
