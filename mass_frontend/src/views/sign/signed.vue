<!--模板-->
<template>
  <section>
    补签
  </section>
</template>
<!--JS-->


<script>
  import { getNotyet } from "@/service/finaapi";
  import moment from "moment";

  export default {
    name: "finaAudit",
    data() {
      return {
        loading: true,
        request: {}
      };
    },
    //加载组件
    mounted() {

    },
    components: {},

    methods: {

    }
  };
</script>



