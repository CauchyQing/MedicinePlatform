<template>
	<div class="singupmain">
		   <div class="title">
		   	 智慧医养大数据公共服务平台
		   </div>
	<div class="formsing">
  <el-form :model="form">
    <el-form-item label="用户名" required class="labels">
      <el-input v-model="form.username" class="input"/>
    </el-form-item>
	<el-form-item label="真实姓名" required class="labels">
	  <el-input v-model="form.trueName" class="input"/>
	</el-form-item>
	<el-form-item label="电话" required class="labels">
	  <el-input v-model="form.phone" class="input"/>
	</el-form-item>
	<el-form-item label="身份证号" required class="labels">
	  <el-input v-model="form.id" class="input"/>
	</el-form-item>
	<el-form-item label="性别" required class="labels">
	  <el-select v-model="form.sex" placeholder="请选择你的性别">
	    <el-option label="男" value="男" />
	    <el-option label="女" value="女" />
	  </el-select>
	</el-form-item>
	<el-form-item label="密码" required class="labels">
	  <el-input v-model="form.password" class="input" type="password"/>
	</el-form-item>
	<el-form-item label="确认密码" required class="labels">
	  <el-input v-model="form.password1" class="input" type="password"/>
	</el-form-item>


 
     <el-button type="warning" @click="onSubmit" class="singup">注册</el-button>
     <el-button type="warning" @click="check" class="login">返回</el-button>   
     			
  </el-form>
  </div>
</div>
  
</template>

<script lang="ts" setup>
import { reactive } from 'vue'
import { useRouter } from "vue-router";
import { ElMessage, ElMessageBox } from 'element-plus'
import type { Action } from 'element-plus'

// do not use same name with ref
const form = reactive({
  username: null,
  trueName:null,
  phone:null,
  sex:null,
  id:null,
  password:null,
  password1:null,
})

const open = () => {
  ElMessageBox.alert('恭喜你注册成功！（点击确认后登录）', '注册', {
    // if you want to disable its autofocus
    // autofocus: false,
    confirmButtonText: '确认',
    callback: (action: Action) => {
      ElMessage({
        type: 'info',
        message: `action: ${action}`,
      })
	  router.push("/login");
    },
  })
}
const close = () => {
  ElMessageBox.alert('两次输入密码不一致，请重新输入', '注册', {
    // if you want to disable its autofocus
    // autofocus: false,
    confirmButtonText: '确定',
    callback: (action: Action) => {
      ElMessage({
        type: 'info',
        message: `action: ${action}`,
      })
	location.reload();
    },
  })
}
const close1 = () => {
  ElMessageBox.alert('用户名已存在，请重新输入用户名！', '注册', {
    // if you want to disable its autofocus
    // autofocus: false,
    confirmButtonText: '确定',
    callback: (action: Action) => {
      ElMessage({
        type: 'info',
        message: `action: ${action}`,
      })
	location.reload();
    },
  })
}
import axios from 'axios';


const onSubmit = () => {
	  if(form.password==form.password1){
		  delete form.password1;
		  fetch('http://101.35.20.149:8081/users/register', {
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
		  			  open();
		  		  }
		      else{
		  			  close1();
		  		  }
		  }).catch(error => {
		      console.error('请求出错', error);
		  });
	  }
	  else{
		  close();
	  }
	
}

const router = useRouter();
const check = () => {
	router.push("/login");
}
</script>
<style>
	.title{
		margin-top: 15vh;
		font-size: 40px;
		font-weight: bold;
		color: white;
	}
	.singupmain{
		width: 96vw;
		height: 96vh;
		background: linear-gradient(to bottom,  #FFDDAA, white);
		position: fixed;
		font-family: 'LESLIE';
		
	}
	.formsing{
		
		width: 300px;
		margin: 5vh auto; /* 上下居中，左右自动 */
		
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