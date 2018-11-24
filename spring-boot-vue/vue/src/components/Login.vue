<template>
<form>
  <input type="text" v-model="loginForm.username" />
  <input type="password" v-model="loginForm.password" />
  <input type="button" @click="submitClick" value="登录"/>
</form>
</template>

<script>
  import {postRequest} from "../utils/api"
  export default {
    data(){
      return {
        rules: {
          account: [{required: true, message: '请输入用户名', trigger: 'blur'}],
          checkPass: [{required: true, message: '请输入密码', trigger: 'blur'}]
        },
        checked: true,
        loginForm: {
          username: 'hly',
          password: '123'
        },
        loading: false
      }
    },
    methods: {
      submitClick: function () {
        var _this = this;
        this.loading = true;
        postRequest('/login', {
          username: this.loginForm.username,
          password: this.loginForm.password
        }).then(resp=> {
          _this.loading = false;
          if (resp.status == 200) {
            //成功
            var json = resp.data;
            if (json.status == 'success') {
              _this.$router.replace({path: '/home'});
            } else {
              alert('登录失败!');
            }
          } else {
            //失败
            alert('登录失败!');
          }
        }, resp=> {
          _this.loading = false;
          alert('找不到服务器⊙﹏⊙∥!');
        });
      }
    }
}
</script>

<style scoped>

</style>
