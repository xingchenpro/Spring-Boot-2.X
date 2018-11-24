<template>
  <form>
    <input type="text" placeholder="用户名"/>
    <input type="password" placeholder="密码"/>
    <input type="button" @click="submitClick" value="登录"/>
  </form>
</template>

<script>
  export default {
    methods: {
      submitClick: function () {
        alert('登录!');
      }
    }
  }
</script>

<style scoped>

</style>
