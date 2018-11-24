<template>
<form>
  <input type="text" v-model="loginForm.username" />
  <input type="password" v-model="loginForm.password" />
  <input type="button" @click.native.prevent="submitClick" value="登录"/>
</form>
</template>

<script>
  import {postRequest} from "../utils/api"
  export default {
    data(){
      return {
        loginForm: {
          username: 'sang',
          password: '123'
        },
        loading: false
      }
    },
  method: {
    submitClick: function () {
      alert('sss');
      var _this = this;
      this.loading = true;
      postRequest('/login',{
        username:this.loginForm.username,
        password:this.loginForm.password
      }).then(resp=> {
        _this.loading = false;
        if(resp.status ==200){
          var json = resp.data;
          if(json.status == 'success'){
            _this.$router.replace({path: '/home'});
          }else {
            alert('登录失败');
          }
        }else {
          alert('登录失败');
        }
      },resp=>{
        _this.loading = false;
        _this.alert('找不到服务器⊙﹏⊙∥!');
        });
    }
  }
}
</script>

<style scoped>

</style>
