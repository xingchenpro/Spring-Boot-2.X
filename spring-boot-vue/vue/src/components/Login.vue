<template>
  <form>
    <input type="text" v-model="loginForm.username"/>
    <input type="password" v-model="loginForm.password"/>
    <input type="button" @click="submitClick" value="登录"/>
  </form>
</template>

<script>
  import {postRequest} from "../utils/api"
  import {getRequest} from "../utils/api"
  import {putRequest} from "../utils/api"
  export default {
    data() {
      return {
        loginForm: {
          username: 'hly',
          password: '123'
        },
      }
    },
    methods: {
      submitClick: function () {
        var _this = this;
        postRequest('/login', {
          username: this.loginForm.username,
          password: this.loginForm.password
        }).then(resp => {
          if (resp.status == 200) {
            //成功
            var json = resp.data;
            if (json.status == 'success') {
              alert('登录成功');
              _this.$router.replace({path: '/hello'});

            } else {
              alert('账号密码错误!');
            }
          } else {
            //失败
            alert('登录失败!');
          }
        }, resp => {
          alert('找不到服务器!');
        });
      }
    }
  }
</script>

<style>

</style>
