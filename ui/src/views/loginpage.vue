<template>
	<div class="loginmain">
		   <div class="title">
             智慧医养大数据公共服务平台
		   </div>
		<div class="form">
		  <el-form :model="form">
			<el-form-item label="用户名" required class="labels"> 
			  <el-input v-model="form.username" class="input"/>
			</el-form-item>
			<el-form-item label="密码&nbsp;&nbsp;&nbsp;&nbsp;" required class="labels">
			  <el-input v-model="form.password" class="input" type="password"/>
			</el-form-item>
			
			  <el-button type="warning" @click="onSubmit" class="singup">注册</el-button>
			  <el-button type="warning" @click="check" class="login">登录</el-button>   
			
		  </el-form>
		  
		  </div>
		  
		  
		  
		  
	</div>
  
</template>

<script lang="ts" setup>
import { reactive ,ref} from 'vue'
import { useRouter } from "vue-router";
import { ElMessage, ElMessageBox } from 'element-plus'
import type { Action } from 'element-plus'
import home from "../views/homepage.vue"
import axios from 'axios';
const ifittrue=ref(false)
// do not use same name with ref
const form = reactive({
  username: '',
  password:'',
})

const inter = () => {
  ElMessageBox.alert('登录成功', '登录', {
    confirmButtonText: '确认',
    callback: (action) => {
      ElMessage({
        type: 'info',
        message: `action: ${action}`,
      });
    },
  });
};

const close = (msg) => {
  ElMessageBox.alert(msg, '登录', {
    confirmButtonText: '确认',
    callback: (action: Action) => {
      ElMessage({
        type: 'info',
        message: `action: ${action}`,
      })
	location.reload();
    },
  })
}


const onSubmit = () => {
	router.push("/singup");
}


const router = useRouter();

const check = () => {
  fetch('http://101.35.20.149:8081/users/login', {
    method: 'POST',
    body: JSON.stringify(form),
    headers: {
      'Content-Type': 'application/json'
    }
  }).then(response => {
      return response.json(); // 将响应转换为 JSON 格式
  }).then(data => {
      console.log(data)
	  if(data.code==1){
		  localStorage.setItem('userId',data.data.userId)
		  localStorage.setItem('username',data.data.username)
		  //inter();
		  router.push("/");
	  }else{
		  console.log(data.msg)
		  close(data.msg)
	  }

  }).catch(error => {
      console.error('请求出错', error);
  });
};


// const check = () => {
// 	inter();
// 	router.push("/");
// }



</script>
<style>
	.title{
		margin-top: 15vh;
		font-size: 40px;
		font-weight: bold;
		color: white;
	}
	.loginmain{
		width: 96vw;
		height: 96vh;
		background: linear-gradient(to bottom,  #FFDDAA, white);
		position: fixed;
		font-family: 'LESLIE';
		
	}
	.form{
		
		width: 300px;
		margin: 18vh auto; /* 上下居中，左右自动 */
		
	}
	.labels .el-form-item__label {
		font-size: 20px;
	}
	.input{
		height: 40px;
	}
	.singup{
       float: left;
       margin-left: 50px;
       margin-top: 2vh;
       width: 90px;
       height: 35px;
       font-size:18px;
	}
	.login{
	   float: right;
	   margin-right: 50px;
	   margin-top: 2vh;
	   width: 90px;
	   height: 35px;
	   font-size:18px;
	}
</style>