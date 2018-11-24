<template>
  <form>
    <input type="text" placeholder="用户名"/>
    <input type="password" placeholder="密码"/>
    <input type="button" @click.native.prevent="submitClick" value="登录"/>
  </form>
</template>

<script>
  export default {
    method: {
      submitClick: function () {
        alert('sss');
      }
    }
  }
</script>

<style scoped>

</style>
