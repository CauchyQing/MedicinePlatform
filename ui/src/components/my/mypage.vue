<template>
	<div class="mymain">
             <br />
			<div class="myinfo">
				<div class="name">
					{{username}}
				</div>
				<div class="userid">
					智慧医疗号：{{userId}}
				</div>
                <div class="change">
                	 <el-button type="warning" plain class="changebu" @click="drawer = true">编辑资料</el-button>
					 <el-button type="warning" plain class="changebu" @click="logout">退出登录</el-button>
					  <el-drawer v-model="drawer" 
					  size="75%" 
					  :with-header="false" 
					  direction="btt" 
					  >
					  <div class="drawer">
					  	
					  
					    <el-form :model="form">
					      <el-form-item label="用户名" class="changelabels">
					        <el-input v-model="form.username" class="changeinput"/>
					      </el-form-item>
					    	<el-form-item label="真实姓名"  class="changelabels">
					    	  <el-input v-model="form.trueName" class="changeinput"/>
					    	</el-form-item>
					    	<el-form-item label="电话"  class="changelabels">
					    	  <el-input v-model="form.phone" class="changeinput"/>
					    	</el-form-item>
					    	<el-form-item label="身份证号"  class="changelabels">
					    	  <el-input v-model="form.id" class="changeinput"/>
					    	</el-form-item>
					    	<el-form-item label="性别"  class="changelabels">
					    	  <el-select v-model="form.sex" placeholder="请选择你的性别">
					    	    <el-option label="男" value="男" />
					    	    <el-option label="女" value="女" />
					    	  </el-select>
					    	</el-form-item>
					    	<el-form-item label="密码"  class="changelabels">
					    	  <el-input v-model="form.password" class="changeinput" type="password"/>
					    	</el-form-item>

					    
					       <el-button type="warning"  class="submit" @click="submit">确认</el-button>
					       <el-button type="warning"  class="withdraw" @click="withdraw">取消</el-button>   
					       			
					    </el-form>
					</div>
					   </el-drawer>
                </div>
				
			</div>
			<div class="mystar">
				<div class="mystartitle">
					我的收藏&nbsp;<el-icon><Star /></el-icon>
				</div>
				<el-tabs
				    v-model="activeName"
				    class="demo-tabs"
				    @tab-click="handleClick"
				>  
				    <el-tab-pane label="新闻" name="first" class="starlabel">
				        <div class="scrollable-content">
				           <el-row :gutter="10" class="card-row">
				             <el-col v-for="(item, index) in myinfo.news" :key="index">
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
						   <br /><br />
				        </div>
						
				    </el-tab-pane>
				
				    <el-tab-pane label="医疗机构" name="second" class="starlabel">
						<div class="scrollable-content">
							<el-row :gutter="10" class="card-row">
							  <el-col v-for="(item, index) in myinfo.medical" :key="index">
							    <el-card shadow="hover" class="card" @click="getmedicaldetail(item)">
							      <el-container>
							        <el-aside style="flex: 42%;">
							          <img :src="item.image" class="careimage"/>
							        </el-aside>
							        <el-main style="flex: 58%;">
									   <div class="careaname">
									   	{{item.name}}
									   </div>
							           <div class="careadress">
							           	{{item.address}}
							           </div>
<!-- 									   <div class="carerate">
									   	 评分：{{item.rate}}分
									   </div> -->
									   <div class="careaname" id="careinfo">
									   	{{item.info}}
									   </div>
							        </el-main>
							      </el-container>
							    </el-card>
							  </el-col>
							</el-row>
							<br /><br />
						</div>
				    </el-tab-pane>
					
				    <el-tab-pane label="养老机构" name="third" class="starlabel">
				        <div class="scrollable-content">
				          <el-row :gutter="10" class="card-row">
				            <el-col v-for="(item, index) in myinfo.cares" :key="index">
				              <el-card shadow="hover" class="card" @click="getcaredetail(item)">
				                <el-container>
				                  <el-aside style="flex: 38%;">
				                    <img :src="item.image[0]" class="careimage"/>
				                  </el-aside>
				                  <el-main style="flex: 62%;">
				          		   <div class="careaname">
				          		   	{{item.name}}
				          		   </div>
				                     <div class="careadress">
				                     	{{item.address}}
				                     </div>
<!-- 				          		   <div class="carerate">
				          		   	 评分：{{item.rate}}分
				          		   </div> -->
				          		   <div class="carebed">
				          		   	 床位：{{item.oldHomeBeds}}
				          		   </div>
				          		   <div class="careprice">
				          		   	￥{{item.price}}元起
				          		   </div>
				          		   <div class="careaname" id="careinfo">
				          		   	{{item.info}}
				          		   </div>
				          
				                  </el-main>
				                </el-container>
				              </el-card>
				            </el-col>
				          </el-row>
						  <br /><br />
				        </div>
				    </el-tab-pane>
					
				</el-tabs>
			</div>
	</div>
	
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import type { TabsPaneContext } from 'element-plus'
import { reactive } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import type { Action } from 'element-plus'
import { defineEmits } from 'vue';
import {defineProps  } from 'vue';
import { onMounted} from 'vue'
import { useRouter } from "vue-router";
const userId=localStorage.getItem('userId')
const username=localStorage.getItem('username')
const props = defineProps(['userinfo']);
const router = useRouter();
const emit = defineEmits(['selectDes','caredetail','medicaldetail','getdetial'])

const logout = () => {
  ElMessageBox.confirm(
    '您是否确认要退出登录？',
    '退出登录',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
      localStorage.removeItem('userId');
      localStorage.removeItem('username');
	  
	  localStorage.removeItem('meddata');
	  localStorage.removeItem('medpage');
	  localStorage.removeItem('medflag');
	  localStorage.removeItem('medaddress');
	  localStorage.removeItem('medsearch');
	  localStorage.removeItem('medscroll');
	  
	  localStorage.removeItem('caredata');
	  localStorage.removeItem('carepage');
	  localStorage.removeItem('careflag');
	  localStorage.removeItem('careaddress');
	  localStorage.removeItem('careprice');
	  localStorage.removeItem('caresearch');
	  localStorage.removeItem('carescroll');
	  
	  localStorage.removeItem('homedata');
	  localStorage.removeItem('homepage');
	  localStorage.removeItem('homeflag');
	  localStorage.removeItem('homescroll');
	  
	  localStorage.removeItem('sugscroll');
	  localStorage.removeItem('sugdata');
	  
      router.push("/login");
      ElMessage({
        type: 'success',
        message: '退出登录成功',
      });
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '取消退出登录',
      });
    });
}

const getcaredetail = (item) => {
	emit('selectDes', 75)
	emit('caredetail',item)
}
const getmedicaldetail = (item) => {
	emit('selectDes', 85)
	emit('medicaldetail',item)
}
const getdetail = (item) =>{
	emit('selectDes',65)
	emit('getdetial',item)
}
const activeName = ref('first')
const drawer = ref(false)
const handleClick = (tab: TabsPaneContext, event: Event) => {
  console.log(tab, event)
}

const changeput = () => {
  fetch('http://101.35.20.149:8081/users/update', {
    method: 'PUT', // 使用 PUT 方法
    body: JSON.stringify(form), // 添加需要更新的数据
    headers: {
      'Content-Type': 'application/json'
    }
  }).then(response => {
    return response.json(); // 将响应转换为 JSON 格式
  }).then(data => {
    console.log(data)
	if(data.code==1){
		localStorage.setItem('username',form.username)
		location.reload();
	}
  }).catch(error => {
    console.error('请求出错', error);
  });
}

const submit = () => {
 ElMessageBox.confirm(
   '您是否确认要修改信息',
   '修改信息',
   {
     confirmButtonText: '确认',
     cancelButtonText: '取消',
   }
 )
   .then(() => {
     changeput();
	 
     ElMessage({
       type: 'success',
       message: '修改信息成功',
     });
   })
   .catch(() => {
     ElMessage({
       type: 'info',
       message: '取消修改信息',
     });
   });
}

const withdraw=()=>{
	  drawer.value=false;
}

const form = reactive({
  userId:userId,
  username: null,
  trueName:null,
  phone:null,
  sex:null,
  id:null,
  password:null,

})

// const myinfo=ref({
// 	name: '下雨的树',
// 	realname:'卿柯序',
// 	phone:'8888888',
// 	sex: '男',
// 	id:'0123456789101',
// 	code:'0519',
// 	userid:'001',
// 	cares:[
// 		{
// 			name:"江安养老院",
// 			address:"成都市双流区",
// 			info:"有不高山，长桥等多个景点，环境优美",
// 			price:250,
// 			rate:10,
// 			star:true,
// 			phone:"1111111",
// 			image:["src/assets/care/care1.png","src/assets/care/care2.png","src/assets/care/care3.png"],
// 			description:"在川大江安校区近一千米长的景观水道两侧，坐落着七十二幅日历造型的雕塑作品群。这就是校长谢和平院士首倡，作为校园文化融合标志性成果的四川大学历史文化长廊。它努力体现四川大学文化融合的主旋律，用简要的文字、形象的历史照片和雅致的艺术设计，将学校110年来的重大事件、杰出人物和重要成果版块式、艺术化地加以呈现，成为学校见证悠久的发展历史、彰显一流的办学成就、传承深厚的文化底蕴和展现辉煌的远景蓝图的重要人文景观。同时，它与各大教学楼和各学院的学术长廊相得益彰，更使全校师生时时处处感受四川大学校园内高雅的文化品位和浓郁的学术气息 。从此处看景观水道，远处东大门国立四川大学的牌坊隐约可见。",
// 		},
// 		{
// 			name:"望江养老院",
// 			address:"成都市武侯区",
// 			info:"环境优美",
// 			price:300,
// 			rate:20,
// 			star:true,
// 			phone:"22222222",
// 			image:["src/assets/care/care2.png","src/assets/care/care1.png","src/assets/care/care3.png"],
// 			description:"四川大学望江校区是原四川大学与原成都科技大学的所在地。总占地3000余亩，主要为四川大学大三、大四及硕、博士研究生同学学习的地方，是四川大学的校本部。望江校区位于成都市一环路上，分设东西南北四个门，交通方便，地理位置优越。作为最早的川大所在地，望江校区历经百年风雨，巴山灵秀，咸聚于此，蜀地彦才，聚汇于斯，校园内历史文化底蕴厚重，是读书治学的理想园地。大三学子搬到望江校区之后，大家都有一种“所谓大学当如是”的感觉。",
// 		},
// 	],
// 	medical:[
// 		{
// 			name:"成都市第二人民医院",
// 			address:"四川省成都市青羊区草市街165号",
// 			info:"三级甲等，综合医院，公立公办医院",
// 			phone:"028-67833912",
// 			rate:10,
// 			star:true,
// 			image:["src/assets/medical/medical1.jpg","src/assets/medical/medical2.jpg","src/assets/medical/medical3.jpg"],
// 			description:"成都市第二人民医院，是一所集医疗、教学、科研、预防、保健、康复为一体的国家三级甲等综合医院。始建于1892年，由加拿大基督教会医学传教士创办，原名福音医院，后又称四川红十字会福音医院、仁济医院，是西医入川原点、四川红十字运动发源地和第一家红十字医院，也是四川大学华西医院的前身。为“全国文明单位”、全国总工会“模范职工之家”、全国卫生系统“卫生文化建设先进单位”、全国“改革创新医院”“改善服务创新医院”“改善医疗服务示范医院”。",
// 		 },
// 		 {
// 		 	name:"成都市第二人民医院",
// 		 	address:"四川省成都市青龙街82号",
// 		 	info:"三级甲等，综合医院，公立公办医院",
// 		 	phone:"028-67833333",
// 			rate:20,
// 			star:true,
// 		 	image:["src/assets/medical/medical2.jpg","src/assets/medical/medical2.jpg","src/assets/medical/medical1.jpg"],
// 		 	description:"成都市第三人民医院，始建于1941年7月，是集医疗、科研、教学、预防、保健和康复为一体的国家三级甲等综合性医院。2014年被省卫生计生委确定为四川省九大区域医疗中心之一，是四川省首批现代医院管理制度试点单位，承担成都中心城区医疗保障的核心功能。医院占地面积138亩，开放床位2200张，年诊疗约154万余人次，出院6.4万余人次，住院手术3.8万余台次。 [5]",
// 		},
// 	],
// 	news:[
// 		{id:"20240219001",star:true,text:"自贡市2022年世界精神卫生日宣传活动月正式启动",date:"2024年2月29日",description:"10月10日是第31个“世界精神卫生日”，当天上午，由市委政法委、市卫健委、市教体局主办的自贡市2022年“世界精神卫生日”宣传活动月启动仪式在我院举行。市委政法委专职委员刘礼东宣布活动月正式启动，市卫生健康委副主任程文静，市教体局党委委员、机关党委书记杨旖旎，医院院长谭友果，副院长曾伶、陈翠容及职能科室干部参加仪式。仪式由医院党委副书记刘成文主持。"},
// 		{id:"20240219002",star:true,text:"我院集中收看党的二十大开幕会",date:"2024年2月29日",description:"022年10月16日上午10时，举世瞩目的中国共产党第二十次全国代表大会在北京人民大会堂开幕，我院组织全体党员、中层以上干部及后备干部集中收看大会开幕直播，认真聆听习近平总书记代表中国共产党第十九届中央委员会向大会作的报告。"},
// 		{id:"20240219003",star:true,text:"广元万缘医院新院区开业",date:"2024年2月29日",description:"据悉，广元万缘医院新院系原市中医医院万源分院，自2014年10月正式开业以来，一直借助中医药特色优势，服务于医疗和养老事业，积极探索医养结合新模式，具备医养结合综合功能。"},
// 	],
	
// })


const myinfo = ref({
	cares:[],
	medical:[],
	news:[],
}); 

const flag=ref(true)
const fetchData = () => {
    if (userId == null) {
        ElMessageBox.alert('您还没有登录！请先登录！', '登录', {
            confirmButtonText: '确认',
            callback: (action) => {
                ElMessage({
                    type: 'info',
                    message: `action: ${action}`,
                });
				router.push("/login");
            },
        });
    } else {
		if(flag.value==true){
			fetch(`http://101.35.20.149:8081/allmystar/${userId}`, {
			    method: 'GET',
			    headers: {
			        'Content-Type': 'application/json'
			    }
			}).then(response => {
			    return response.json(); // 将响应转换为 JSON 格式
			}).then(data => {
			    myinfo.value = data.data; // 追加新数据
				console.log(myinfo.value);
				if(data.data==0){
					flag.value=false;
					console.log()
				}
				else{
				}
			    
			}).catch(error => {
			    console.error('请求出错', error);
			});
		}
        
    }
};


onMounted(() => {
    fetchData();
});




</script>

<style>
.mymain{
	margin-top: 5vh;
}
.drawer{
	width: 330px;
	margin: 3vh auto;
}
.changeinput{
	height: 40px;
}
.changelabels .el-form-item__label {
	font-size: 20px;
}
.submit{
	   float: left;
	   margin-left: 50px;
	   margin-top: 2vh;
	   width: 100px;
	   height: 35px;
	   font-size:18px;
}
.withdraw{
	   float: right;
	   margin-right: 50px;
	   margin-top: 2vh;
	   width: 100px;
	   height: 35px;
	   font-size:18px;
}
.mystartitle{
	text-align: left;
	margin-left: 2vw;
	font-size: 30px;
}
.mystar{
	margin-top: 1vh;
	background-color: #fff;
	margin-left: 5vw;
	width: 85vw;
	border-radius: 10px; /* 设置边框圆角为 10px */
	height: 67vh;
}

.myinfo {
    background-color: #fff;
    margin-left: 5vw;
    width: 85vw;
    border-radius: 10px; /* 设置边框圆角为 10px */
	height: 20vh;
	
}
.name{
	margin-top: 5vh;
	font-size: 35px;
	font-weight: bold;
	text-align: left;
	margin-left: 2vw;
	
}
.userid{
	text-align: right;
	margin-right: 2vw;
	margin-top: 2vh;
	font-size: 18px;
	color: #999;
}
.change{
	margin-top: 2vh;
	float: left;
	margin-left: 2vw;
}
.changebu{
	
	width: 100px;
	height: 35px;
	font-size:18px;
	
}
.demo-tabs {
   margin-left: 2vw;
   width: 82vw;
   font-size: 20px;
   height: 60vh;
   overflow: hidden;
}
.scrollable-content {
    overflow-y: auto; /* 添加纵向滚动条 */
    max-height: 60vh;
	overflow-x: hidden;
}
.starlabel{
	font-size: 30px;
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
</style>