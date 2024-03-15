<template>
  <div class="suggestmain"> 
		<div class="suggestions" v-if="ifdetails===false">
			<br /><br />
		  	<img :src="backimage" class="background" alt=""/>
			<!-- <p class="background">
				针对每个人的身体健康情况，党和政府做出了许多有效的行动，为每一个人的身体健康保驾护航
			</p> -->
			
			<br /><br />
          <el-row :gutter="10" class="card-row">
            <el-col v-for="(item, index) in checkboxes" :key="index">
              <el-card shadow="hover" class="card" @click="getdetail(item)">
                <div class="suggesttitle">
                  {{ item.title }}
                </div>
                <div class="suggestcontext">
                  {{ item.abstract1}}
                </div>
				<div class="suggestdate">
					{{item.layout}} 
				</div>
              </el-card>
            </el-col>
          </el-row>



            <div class="caresmore" v-if="flag===true">
                    -- 下拉刷新 --
            		<br />
            		数据正在加载中&nbsp;        
            			<el-icon class="loading" >
                   <Loading />
                  </el-icon>
            </div>
			
		  	<div class="suggestend" v-if="flag===false">
				    以上数据均来自人民网，网址：http://www.people.com.cn/
		  			<br />
		  			== 没有更多数据了噢 ==
		  	</div>
			
			
			
		  <br /><br /><br />
		  <el-backtop :right="40" :bottom="100" />
		</div>
		 
		<div class="suggestiondetails" v-if="ifdetails===true">
			<div class="suggestiontemp">
				<br /><br />
				<div class="suggestiontitle">
					{{nowdetails.title}}
				</div>
				<div class="suggestiondate">
					{{nowdetails.layout}}
				</div>
				<div class="suggestioncontext">
				  {{nowdetails.content}}
				</div>
				<div class="suggestiondate">
					{{nowdetails.publicationTime}}
				</div>
				<br /><br /><br />
			</div>
			
			
			<el-backtop :right="40" :bottom="100" />
			<el-button circle class="back" color="white" @click="back"><el-icon><ArrowLeftBold /></el-icon></el-button>
			
		</div>
		

  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { defineEmits } from 'vue';
import { onMounted} from 'vue'


const userId=localStorage.getItem('userId')
const ifdetails=ref(false)
const emit = defineEmits(['selectDes','getdetial'])
const nowdetails=ref()



const getdetail = (item) =>{
	
	  nowdetails.value=item
	  window.scrollTo(0,0);
	  console.log(nowdetails.value)
	  ifdetails.value=true
	  
}


const back=()=>{
	ifdetails.value=false
	
}
const backimage = "src/assets/background/background1.jpg"
const checkboxes = ref([]); 
const flag=ref(true)
const fetchData = () => {
		if(flag.value==true){
			fetch(`http://101.35.20.149:8081/users/recommend/${userId}`, {
			    method: 'GET',
			    headers: {
			        'Content-Type': 'application/json'
			    }
			}).then(response => {
			    return response.json(); // 将响应转换为 JSON 格式
			}).then(data => {
			    checkboxes.value = [...checkboxes.value, ...data.data]; // 追加新数据
				localStorage.setItem('sugdata', JSON.stringify(checkboxes.value));
				console.log(checkboxes.value);
			   	flag.value=false;
				
			}).catch(error => {
			    console.error('请求出错', error);
			});
		}
        
};



onMounted(() => {
	checkboxes.value=JSON.parse(localStorage.getItem('sugdata') || '[]');
    fetchData();
});



</script>

<style scoped>

.background{
	width: 88vw;
	font-size: 28px;
	color: black;
	text-align: center;
	//height: 30vh;
}
.suggestions{
	margin: 4vw;
	background: linear-gradient(to left,  #FFDDAA,#F56C6C);
}
.suggestiondetails{
	
	background-color: #FFDDAA;
}
.suggestiontemp{
	margin: 4vw;
}
.suggestiontitle{
	font-weight: bold;
	font-size: 30px;
	text-align: center;
	
}
.suggestioncontext{
  font-size: 20px;
  text-align: left;
  text-indent: 2em;
}
.suggestiondate{
	font-size: 15px;
	text-align: right;
	color: #999;
}

.suggestend{
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
.suggestdate{
	font-size: 15px;
	text-align: right;
	color: #999;
}
.suggest{
	font-weight: bold;
	font-size: 25px;
	text-align: left;
	margin: 4vw;
	
}
.suggesttitle{
	font-weight: bold;
	font-size: 25px;
	text-align: left;
	white-space: nowrap; /* 文本不换行 */
	overflow: hidden;
	text-overflow: ellipsis;
}
.suggestcontext {
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
  .suggestcontext {
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
.suggestmain{
	background: linear-gradient(to left,  #FFDDAA,#F56C6C);
}
</style>