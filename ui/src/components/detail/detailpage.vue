<template>
<div class="newsback">
	<div class="newsmain">
	
		<br /><br />
		<div class="newstitle">
			{{props.news.paperTitle}}
			<el-icon @click="toggleStar" class="stars">
			   <StarFilled v-if="ifstar" />
			   <Star v-else />
			 </el-icon>
		</div>
		<div class="newsdate">
			
			{{props.news.paperTime}}  <br />
			{{props.news.paperLocal}}
		</div>
		<div class="newscontext">
		  {{props.news.paperContent}}
		</div>
		<br /><br /><br />
		
		<el-backtop :right="40" :bottom="100" />
		<el-button circle class="back" color="white" @click="back"><el-icon><ArrowLeftBold /></el-icon></el-button>
		
	</div>
</div>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { defineEmits } from 'vue';
import {defineProps } from 'vue';
import { onMounted,onUnmounted} from 'vue'
const props = defineProps(['news',"from"]);
const emit = defineEmits(['from'])
const ifstar = ref(props.news.star);
const userId =  Number(localStorage.getItem('userId'));
const starinfo=ref({
	userId:userId,
	newsId:props.news.paperId,
})
const toggleStar = () => {
      ifstar.value = !ifstar.value;
	  console.log(starinfo)
	  if(ifstar.value==true){
	  		  fetch('http://101.35.20.149:8081/news/star', {
	  		    method: 'POST',
	  		    body: JSON.stringify(starinfo.value),
	  		    headers: {
	  		      'Content-Type': 'application/json'
	  		    }
	  		  }).then(response => {
	  		      return response.json(); // 将响应转换为 JSON 格式
	  		  }).then(data => {
	  		      console.log(data)
	  		  
	  		  }).catch(error => {
	  		      console.error('请求出错', error);
	  		  });
	  }else{
	  		  fetch('http://101.35.20.149:8081/news/unstar', {
	  		    method: 'DELETE',
	  		    body: JSON.stringify(starinfo.value),
	  		    headers: {
	  		      'Content-Type': 'application/json'
	  		    }
	  		  }).then(response => {
	  		      return response.json(); // 将响应转换为 JSON 格式
	  		  }).then(data => {
	  		      console.log(data)
	  		  
	  		  }).catch(error => {
	  		      console.error('请求出错', error);
	  		  });
	  
	  }	
	  
	  
	  
    };
const back = () => {
	emit('from', props.from)
	
}
onMounted(() => {
	window.scrollTo(0,0);
});
onUnmounted(() => {
    window.scrollTo(0,0);
})
</script>

<style>
.newsback{
	background-color: #FFDDAA;
}
.newsmain{
	margin: 4vw;
	
}
.newstitle{
	font-weight: bold;
	font-size: 30px;
	text-align: center;
	
}
.newscontext {
  font-size: 20px;
  text-align: left;
  text-indent: 2em;
}
.newsdate{
	font-size: 16px;
	text-align: right;
	color: #999;
}
.back{
	position: fixed;
    left:4vw;
	top: 5.5vh;
	z-index: 999;
	width: 5vh; /* 设置按钮的宽度 */
	height: 5vh; /* 设置按钮的高度 */ 
	//font-size: 20px; /* 设置按钮的字体大小 */
	
}
</style>