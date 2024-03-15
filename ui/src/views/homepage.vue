<template>
<div class="mainall">

    <div class="header">
    	
		智&nbsp;慧&nbsp;医&nbsp;养&nbsp;大&nbsp;数&nbsp;据&nbsp;平&nbsp;台
    </div>
	
	
	
	
	
	
    <div class="main">

    	<home v-if="currentTab === 1" @selectDes="handleSelectDes" @getdetial="handlegetdetial"></home>
		<medical v-if="currentTab === 2" @selectDes="handleSelectDes" @medicaldetail="handlemedicaldetail"></medical>
		<care v-if="currentTab === 3" @selectDes="handleSelectDes" @caredetail="handlecaredetail"></care>
		<suggest v-if="currentTab === 4"></suggest>
		<my v-if="currentTab === 5" @selectDes="handleSelectDes" 
		    @caredetail="handlecaredetail" 
			@medicaldetail="handlemedicaldetail" 
			@getdetial="handlegetdetial"
			:userinfo="props.userinfo"
			
			
		></my>
		
		
		<detail v-if="currentTab === 6" @from="handleSelectDes" :news="currentnews" :from="newsfrom"></detail>
		
		
		<caredetail v-if="currentTab === 7" @from="handleSelectDes" :cares="currentcare" :from="caresfrom"></caredetail>
		
		
		<medicaldetail v-if="currentTab === 8" @from="handleSelectDes" :meidcals="currentmedical" :from="medicalsfrom"></medicaldetail>
        
		
		<health v-if="currentTab === 9"></health>
		
		

		
		
	</div>
    <div class="footer">
    	<el-menu
    	  :default-active="currentTab"
    	  class="el-menu-demo"
    	  mode="horizontal"
		  background-color="#fff"
		  text-color="black"
		  active-text-color="#FFBB66"
    	>
    	  <el-menu-item index="1" class="menu-item" @click="currentTab = 1" id="step1">
    	    <el-icon><House /></el-icon>
    	    <template #title>首页</template>
    	  </el-menu-item>
    	  
    	  <el-menu-item index="2" class="menu-item" @click="currentTab = 2" id="step2">
         
				  <el-icon><Service /></el-icon>

				  <template #title>医疗</template>
			 

    	  </el-menu-item>
    	  
    	  <el-menu-item index="3" class="menu-item" @click="currentTab = 3" id="step3">
	   
				<el-icon><School /></el-icon>

				<template #title>养老</template>
			  
    	  </el-menu-item>
    	  
    	  <el-menu-item index="4" class="menu-item" @click="currentTab = 4" id="step4">
	            
    	          <el-icon><Star /></el-icon>
	
    	    <template #title>推荐</template>
			
    	  </el-menu-item>
    	  
    	  <el-menu-item index="5" class="menu-item" @click="currentTab = 5" id="step5">
    	    <el-icon><User /></el-icon>
    	    <template #title>我的</template>
    	  </el-menu-item>
    	</el-menu>
			<el-button circle class="detect" color="#FFDDAA" @click="currentTab = 9" id="step6"><el-icon><Sunny /></el-icon></el-button>
      
	</div>
    <el-button circle class="tools" color="#fff" @click="tools" id="step7"><el-icon><Menu /></el-icon></el-button>
    <div class="toolsboard" v-if="iftools===true">
     	     <el-button-group>
				 <el-button circle class="vedio" @click="readtext" v-if="isReading===false"><el-icon><VideoPlay /></el-icon></el-button>
     	        <el-button circle class="vedio" @click="conttext" v-if="isReading===true"><el-icon><VideoPlay /></el-icon></el-button>
				<el-button circle class="vedio" @click="stoptext"><el-icon><VideoPause /></el-icon></el-button>
				<el-button circle class="vedio" @click="deltext"><el-icon><Close /></el-icon></el-button>
     	      </el-button-group>
			  
			  
			  <br />
			  
		 <a href="http://www.people.com.cn/" target="_blank">	  
		   <el-button circle class="links" type="danger"><el-icon><User /></el-icon></el-button>
		 </a>
		 <a href="http://health.people.com.cn/" target="_blank">
		   <el-button circle class="links" type="success"><el-icon><Sunrise /></el-icon></el-button>
		 </a>
		 
		 <a href="https://news.cctv.com/" target="_blank">
		   <el-button circle class="links" type="primary"><el-icon><Film /></el-icon></el-button>
		 </a>
		 
		 <br />人民网&nbsp;&nbsp;&nbsp;&nbsp;健康生活&nbsp;&nbsp;&nbsp;&nbsp;央视频
		 
		 <a href="https://dxy.com/" target="_blank">
		   <el-button circle class="links" color="#8A2BE2"><el-icon><Service /></el-icon></el-button>
		 </a>
		 <a href="https://www.baidu.com/" target="_blank">
		   <el-button circle class="links" color="#79bbff"><el-icon><Search /></el-icon></el-button>
		 </a>
		 <a href="https://www.thepaper.cn/" target="_blank">
		   <el-button circle class="links" color="#FFDDAA"><el-icon><ChatDotRound /></el-icon></el-button>
		 </a>
		 
		  <br />丁香医生&nbsp;&nbsp;&nbsp;&nbsp;百度&nbsp;&nbsp;&nbsp;&nbsp;澎湃新闻
		  
    </div>
	  <div class="overlay" v-if="showOverlay"></div>
	  <div v-if="showOverlay">
		   <el-card class="overcard">
			   <div class="dateimage">
			   	   <el-image :src="dateurl"  style="height: 400px" fit="cover" />
			   	   	<div class="datetext" :class="{ 'white-text': !ifcolor, 'black-text': ifcolor }">
			   	   		{{currentDate.year}}年 <br />
			   	   		{{currentDate.month}}月<br />
			   	   		{{currentDate.day}}日<br />
			   	   	</div>
			
			   </div>

		    </el-card> 
	  </div>
	  <el-button circle :icon="Close" v-show="showButton" class="overb" @click="closedate"/>
	  
</div>

	
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import home from "../components/home/homepage.vue"
import medical from "../components/medical/medicalpage.vue"
import care from "../components/care/carepage.vue"
import my from "../components/my/mypage.vue"
import suggest from "../components/suggest/suggestpage.vue"
import detail from "../components/detail/detailpage.vue"
import caredetail from "../components/care/caredetail.vue"
import medicaldetail from "../components/medical/medicaldetail.vue"
import health from "../components/health/healthpage.vue"
import {defineProps  } from 'vue';
import { driver } from "driver.js";
// 引入driver样式
import "driver.js/dist/driver.css";
import { Config } from "driver.js";
import {onMounted} from "vue";
import { useWindowSize } from '@vueuse/core';
import {
  Close
} from '@element-plus/icons-vue'



const showOverlay=ref(true)
const showButton=ref(true)

const closedate=()=>{
	showOverlay.value=false
	showButton.value=false
}
const driverObj = driver({
  // 显示步骤进度
  showProgress: true,
  // 不允许其它方式关闭
  allowClose: true,
  steps: [
  	{ element: '#step1', popover: { title: '首页：',description: '展示近期的健康新闻动态，可以对新闻进行收藏', side: "top" } },
  	{ element: '#step2', popover: { title: '医疗：',description: '展示医院信息，可以对其进行咨询，收藏和评论', side: "right" } },
  	{ element: '#step3', popover: { title: '养老：',description: '展示养老院信息，可以对其进行咨询，收藏和评论', side: "right" } },
  	{ element: '#step4', popover: { title: '推荐：',description: '根据个人健康状态推荐新闻', side: "top" } },
  	{ element: '#step5', popover: { title: '我的：',description: '查找自己的收藏，可以修改个人信息', side: "left" } },
  	{ element: '#step6', popover: { title: '健康：',description: '录入修改自己的健康信息，生成健康报告', side: "left" } },
	{ element: '#step7', popover: { title: '工具箱：',description: '可以朗读页面文字，点击图标跳转到其他健康网站页面', side: "right" } }
  	],
	onPopoverRender: (popover, { config }) => {

	  popover.nextButton.textContent = "下一步";
	  popover.closeButton.textContent = "x";
	  popover.previousButton.textContent="上一步";
	}
  
});

const currentDate = ref({
	year:null,
	month:null,
	day:null,
});
 const getCurrentDate = () => {
      const now = new Date();
      currentDate.value.year = now.getFullYear();
      currentDate.value.month = (now.getMonth() + 1).toString().padStart(2, '0');
      currentDate.value.day = now.getDate().toString().padStart(2, '0');
     
    };

const windowSize = useWindowSize();
const dateurl=ref();
 const ifcolor = ref(false);
onMounted(() => {
   
	console.log(windowSize.width.value)
	if(windowSize.width.value>660){
		driverObj.drive();
		ifcolor.value=true;
	}
	getCurrentDate();
	dateurl.value="src/assets/dateimage/"+currentDate.value.day+".jpg"
	//dateurl.value="src/assets/dateimage/"+25+".jpg"
	console.log(currentDate.value)

	
});
const iftools=ref(false)
const tools=()=>{
	iftools.value=!iftools.value
}


const synth = window.speechSynthesis;
const msg = new SpeechSynthesisUtterance();
const isReading = ref(false);

const handleSpeak = (text) => {
  msg.text = text;
  msg.lang = "zh-CN";
  msg.volume = 1;
  msg.rate = 0.8;
  msg.pitch = 1;
  isReading.value = true;
 synth.speak(msg);
   msg.onend = () => {
     isReading.value = false;
   };
}

const stoptext = () => {
  isReading.value = true;
  synth.pause();
}

const conttext = () => {
  isReading.value = true;
  synth.resume();
}
const deltext = () => {
  isReading.value = false;
  synth.cancel();
}
const readtext = () => {
  const textId="main"
  const text = document.querySelector(`.${textId}`).textContent;
  
  handleSpeak(text);

}


const props = defineProps(['userinfo']);
const currentTab=ref(1)
const currentnews=ref()
const newsfrom=ref()
const caresfrom=ref()
const currentcare=ref()
const currentmedical=ref()
const medicalsfrom=ref()

const handlemedicaldetail=(item)=>{
	console.log(currentmedical.value)
	currentmedical.value=item
}
const handlecaredetail=(item)=>{
	currentcare.value=item
}
const handlegetdetial= (item) =>{
	//console.log(item)
	//console.log(currentTab.value)
	currentnews.value=item
}
const handleSelectDes = (index) => {
  if(index==0){
	  currentTab.value=4;
  }
  else if(index==1){
	  console.log(1111)
	  console.log('debug'+parseInt(localStorage.getItem('medscroll')))
	  window.scrollTo(0,parseInt(localStorage.getItem('medscroll')));
	  currentTab.value=2;
  }
  else if(index==2){
	   window.scrollTo(0,parseInt(localStorage.getItem('carescroll')));
  	  currentTab.value=3;
  }
  else if(index==3){
	  window.scrollTo(0,parseInt(localStorage.getItem('homescroll')));
  	  currentTab.value=1;
  }
  else if(index==5){
  	  currentTab.value=5;
  }
  else if(index==7){
  	  currentTab.value=7;
	  caresfrom.value=2;
  }
  else if(index==75){
  	  currentTab.value=7;
	  caresfrom.value=5;
  }
  else if(index==8){
  	  currentTab.value=8;
	  medicalsfrom.value=1;
  }
  else if(index==85){
  	  currentTab.value=8;
  	  medicalsfrom.value=5;
  }
  else if(index==61){
  	  currentTab.value=6;
	  newsfrom.value=3;
  }
  else if(index==65){
  	  currentTab.value=6;
  	  newsfrom.value=5;
  }
  console.log(index)
 
};
</script>

<style>
.white-text {
  color: #fff; /* 设置文字颜色为白色 */
}

.black-text {
  color: #000; /* 设置文字颜色为黑色 */
}
.datetext{
	z-index: 10001; /* 设置按钮在最上层 */
	position:fixed;
	top:45vh;
	right: 23.2vw;
	font-size: 40px;
	font-weight: bold;
	
}
.overcard{
	  position:fixed;
	  z-index: 9999; /* 设置按钮在最上层 */
	  top:18vh;
	  left: 20vw;
	  width:60vw;
	  height:450px;
	  font-size: 20px;
}
.dateimage {
  width:50vw;
  height:450px;
}
  .overlay {
    position: fixed;
    top: 0vh;
    left: 0vw;
    width: 99vw;
    height: 99vh;
    background-color: rgba(0, 0, 0, 0.5); /* 半透明黑色 */
    z-index: 9998; /* 确保遮罩层在最上层 */
  }
  
  .overb {
    position: fixed;
    z-index: 10000; /* 设置按钮在最上层 */
	top:16.2vh;
	left: 77.7vw;
	width: 45px;
	height: 45px;
	font-size: 20px;
  }

.zhidao{
	position: absolute;
	top: 90vh;
	left: 10vw;
	z-index: 9999;
}
.mainall{
	margin-top: 2vh;
	width:96vw;
	height:96vh;
	font-family: 'LESLIE';
}
.header{
	background-color: white;
	height: 5vh;
	font-size: 26px;
	font-weight: bold;
	line-height: 30px;
	z-index: 999;
	text-align: center;
	position: fixed;
	top: 0vh;
	width: 96vw;

}
.main{
	height: 90vh;
	background-color: #FFDDAA;
}
.footer{
	height: 6vh;
	z-index: 999;
	text-align: center;
	position: fixed;
	bottom: 0.5vh;
	width: 96vw;
}

.menu-item{
	width: 15.5vw;
    
}  

.detect{
	position: fixed;
	left:85vw;
	bottom: 0.5vh;
	z-index: 999;
	width: 5vh; /* 设置按钮的宽度 */
	height: 5vh; /* 设置按钮的高度 */ 
	//font-size: 20px; /* 设置按钮的字体大小 */
	
}

.tools {
  position: fixed;
  left: 40px;
  bottom: 95px;
  z-index: 999;
  width: 6vh;
  height: 6vh;
  //border: 0.5px solid black;
  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.3); /* 添加阴影效果 */
}

.toolsboard {
  position: fixed;
  left: 40px;
  bottom: 95px;
  z-index: 998;
  width: 28vh;
  height: 37vh;
  background-color: #fff;
  //border: 0.5px solid black;
  border-radius: 25px;
  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.3); /* 添加阴影效果 */
}
.vedio{
	margin-top: 1vh;
	width: 7vh;
	height: 6vh;
}
.links{
	margin: 1.5vh;
	width: 5.5vh;
	height: 5.5vh;
}
</style>