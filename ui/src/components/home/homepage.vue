<template>
  <div class="homemain"> 

	    <br />
		<div class="flowing">
		  <el-carousel :interval="5000" arrow="always" style="width: 90vw;"  >
		    <el-carousel-item v-for="item in flowimages" :key="item">
		      <div class="image-container">
		        <img :src="item" class="carousel-image" />
		      </div>
		    </el-carousel-item>
		  </el-carousel>
		</div>
		
		<div class="news">
		  <br />
		  健康动态<br />————————————
          <el-row :gutter="10" class="card-row">
            <el-col v-for="(item, index) in checkboxes" :key="index">
              <el-card shadow="hover" class="card" @click="getdetail(item)">
                <div class="newtitle">
                  {{ item.paperTitle }}
                </div>
                <div class="newcontext">
                  {{ item.paperContent }}
                </div>
				<div class="newdate">
					{{item.paperLocal}} <br />
					{{item.paperTime}}
				</div>
              </el-card>
            </el-col>
          </el-row>

		  <div class="morecares">
		  			
		  		</div>
		  <div class="newsmore" v-if="ifmorecares===true">
		  	        -- 下拉刷新 --
		  			<br />
		  			数据正在加载中&nbsp;        
		  				<el-icon class="loading" >
		             <Loading />
		            </el-icon>
		  	</div>
		  	<div class="newsend" v-if="ifmorecares===false">
		  			<br />
		  			== 没有更多数据了噢 ==
		  	</div>
		  <br /><br /><br />
		</div>
		 <el-backtop :right="40" :bottom="100" />
		
		

  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { defineEmits } from 'vue';
import { onMounted,onUnmounted} from 'vue'
import { useRouter } from "vue-router";
import { ElMessage, ElMessageBox } from 'element-plus'
import type { Action } from 'element-plus'
const router = useRouter();
const userId=localStorage.getItem('userId')
const ifmorecares=ref(true)
const flowimages = [
  'src/assets/home/flow1.jpg',
  'src/assets/home/flow2.jpg',
  'src/assets/home/flow3.jpg'
];
// const checkboxes=ref([
// 	{id:"20240219001",star:true,text:"自贡市2022年世界精神卫生日宣传活动月正式启动",date:"2024年2月29日",description:"10月10日是第31个“世界精神卫生日”，当天上午，由市委政法委、市卫健委、市教体局主办的自贡市2022年“世界精神卫生日”宣传活动月启动仪式在我院举行。市委政法委专职委员刘礼东宣布活动月正式启动，市卫生健康委副主任程文静，市教体局党委委员、机关党委书记杨旖旎，医院院长谭友果，副院长曾伶、陈翠容及职能科室干部参加仪式。仪式由医院党委副书记刘成文主持。"},
// 	{id:"20240219002",star:false,text:"我院集中收看党的二十大开幕会",date:"2024年2月29日",description:"022年10月16日上午10时，举世瞩目的中国共产党第二十次全国代表大会在北京人民大会堂开幕，我院组织全体党员、中层以上干部及后备干部集中收看大会开幕直播，认真聆听习近平总书记代表中国共产党第十九届中央委员会向大会作的报告。"},
// 	{id:"20240219003",star:true,text:"广元万缘医院新院区开业",date:"2024年2月29日",description:"据悉，广元万缘医院新院系原市中医医院万源分院，自2014年10月正式开业以来，一直借助中医药特色优势，服务于医疗和养老事业，积极探索医养结合新模式，具备医养结合综合功能。"},
// 	{id:"20240219004",star:false,text:"守牢防疫减灾防线 实现“健康平安”高考",date:"2024年2月29日",description:"会上，罗强通过考务指挥系统，先后抽查了自贡市、凉山州、达州市，调度了成都市、广安市和雅安市，听取市（州）负责同志汇报，并通过大数据智慧招考决策支撑平台随机查看部分考点考试组织情况，特别是详细了解广安华泰方舱医院考点考场和天全避灾平移考点考场备考和组考情况。"},
// 	{id:"20240219005",star:true,text:"好好睡觉，强化自然免疫",date:"2024年2月29日",description:"  日常生活中最困扰你的问题是什么？你是否和很多人的回答一样，都是睡眠问题。信息大爆炸时代，手机、电脑、游戏机等各种电子产品充斥着人们的生活；每天都要面对着快节奏的生活和工作；"}
// ])
const emit = defineEmits(['selectDes','getdetial'])
const getmore = (index) => {
	emit('selectDes', index)
}
const getdetail = (item) =>{
	emit('selectDes',61)
	emit('getdetial',item)
}


const checkboxes = ref([]); // 使用 ref 创建响应式数据
const pageSize = 8; // 每页加载的数据量
const flag=ref()
const page=ref();
const scoll=ref();
const fetchData = () => {
	page.value++;
	localStorage.setItem('homepage',page.value);
     if(flag.value){
			fetch(`http://101.35.20.149:8081/news/get?page=${page.value}&pageSize=${pageSize}&userId=${userId}`, {
			    method: 'GET',
			    headers: {
			        'Content-Type': 'application/json'
			    }
			}).then(response => {
			    return response.json(); // 将响应转换为 JSON 格式
			}).then(data => {
			    checkboxes.value = [...checkboxes.value, ...data.data]; // 追加新数据
				 localStorage.setItem('homedata', JSON.stringify(checkboxes.value));
				console.log(data.data.length);
				if(data.data==0){
					ifmorecares.value=false;
					flag.value=false;
					localStorage.setItem('homeflag',flag.value);
					console.log('finiallll')
				}
				else{
					ifmorecares.value=true;
					localStorage.setItem('homeflag',flag.value);
					
				}
			    
			}).catch(error => {
			    console.error('请求出错', error);
			});
		}

};

const homehandleScroll = () => {
   if(window.scrollY<16&&parseInt(localStorage.getItem('homescroll'))!=0){
   	window.scrollTo(0,parseInt(localStorage.getItem('homescroll')));
   }
   
   scoll.value=window.scrollY
   localStorage.setItem('homescroll',scoll.value)
   const moreCares = document.querySelector('.morecares');
   if (moreCares) {
       const moreCaresOffsetTop = moreCares.getBoundingClientRect().top;
       const windowHeight = window.innerHeight;
       if (moreCaresOffsetTop <= windowHeight) {
   		     console.log('moredata')
   		     fetchData();	 
   				
       }
   }
};

window.addEventListener('scroll', homehandleScroll);

onMounted(() => {
	flag.value=localStorage.getItem('homeflag')||true;
	checkboxes.value=JSON.parse(localStorage.getItem('homedata') || '[]');
	page.value=localStorage.getItem('homepage') || 0;
	fetchData();
   
});

onUnmounted(() => {
    window.removeEventListener('scroll',homehandleScroll);
})

</script>

<style scoped>
.newsmore{
	text-align: center;
}
.newsend{
	text-align: center;
}
.morecares{
	height: 2vh;
}
.getmore{
	font-size: 20px;
	text-align: right;
    color: #999;
}
.newdate{
	font-size: 15px;
	text-align: right;
	color: #999;
}
.news{
	font-weight: bold;
	font-size: 25px;
	text-align: left;
	margin: 4vw;
	
}
.newtitle{
	font-weight: bold;
	font-size: 25px;
	text-align: left;
	white-space: nowrap; /* 文本不换行 */
	overflow: hidden;
	text-overflow: ellipsis;
}
.newcontext {
  font-size: 20px;
  text-align: left;
  overflow: hidden;
  text-overflow: ellipsis;
  text-indent: 2em;
  display: -webkit-box;
  -webkit-line-clamp: 5; /* 规定显示的行数 */
  -webkit-box-orient: vertical;
}

@supports (-webkit-line-clamp: 5) {
  .newcontext {
    display: block;
    display: -webkit-box;
    -webkit-line-clamp: 5;
    -webkit-box-orient: vertical;
  }
}
.card-row {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr)); /* 每列最小宽度为 200px，自适应屏幕宽度 */
  gap: 10px; /* 列之间的间隔 */
  //height: 30vh;
}
.homemain{
	//height:300vh;
	background-color: #FFDDAA;
}

.flowing {
  width: 90vw;
  margin: 0 auto; /* 水平居中 */
}

.el-carousel {
  width: 100%; /* 走马灯宽度固定 */
  height: 40vh;
}

.image-container {
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.carousel-image {
  max-width: 100%;
  max-height: 100%;
  object-fit: contain; /* 保持图片比例并完整显示 */
}
</style>