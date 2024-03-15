<template>
<div class="caremain">
	<div class="search">
		  <br />
		  <el-input v-model="search" size="big" placeholder="搜索" class="searchall"/>

		   
		   
		     <el-select
		         v-model="valueaddress"
		         multiple
		         placeholder="筛选地址"
				 class="searchaddress"
		       >
		         <el-option
		           v-for="item in cities"
		           :key="item.value"
		           :label="item.label"
		           :value="item.value"
		         />
		       </el-select>
		   
		   <el-popover
		      placement="bottom"
		      title="价格区间"
		      trigger="click"
		      class="custom-popover"
			  :width="365"
		   >
		      <template #reference>
		        <el-button class="searchprice">筛选价格</el-button>
		      </template>
		      <el-slider v-model="valueprice" range :max="10000" class="slider"/>
		   </el-popover>
		 
			 
		   
	</div>
	<br /><br /><br /><br />
	<div class="careinfo">
        <el-row :gutter="10" class="card-row">
          <el-col v-for="(item, index) in filterTableData" :key="index">
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
<!-- 				   <div class="carerate">
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
		
		<div class="morecares">
			 
		</div>
       <div class="caresmore" v-if="ifmorecares===true">
		         -- 下拉刷新 --
       			<br />
       			数据正在加载中&nbsp;        
				<el-icon class="loading" >
                  <Loading />
                 </el-icon>
       	</div>
       	<div class="caresend" v-if="ifmorecares===false">
       			<br />
       			== 没有更多数据了噢 ==
       	</div>
	</div>
	<el-backtop :right="40" :bottom="100" />

 <br /><br /><br />
</div>
</template>

<script lang="ts" setup>
import { computed, ref ,watch} from 'vue'
import type { CheckboxValueType } from 'element-plus'
import { defineEmits } from 'vue';
import { onMounted,onUnmounted} from 'vue'
import { useRouter } from "vue-router";
import { ElMessage, ElMessageBox } from 'element-plus'
import type { Action } from 'element-plus'
const router = useRouter();
const userId=localStorage.getItem('userId')
const emit = defineEmits(['selectDes','caredetail'])
const ifmorecares=ref(true)
const getcaredetail = (item) => {
	//console.log(index)
	emit('selectDes', 7)
	emit('caredetail',item)
	localStorage.setItem('caresearch',search.value);
	localStorage.setItem('careaddress', JSON.stringify(valueaddress.value));
	localStorage.setItem('careprice',JSON.stringify(valueprice.value))
}
const search = ref('')
const checkAll = ref(false)
const indeterminate = ref(false)
const valueaddress = ref([])
const valueprice = ref([1000, 6000])
const cities = ref([
  {
    value: '河北省',
    label: '河北省',
    children: [
      // Add cities for Hebei province if available
    ],
  },
  {
    value: '山西省',
    label: '山西省',
    children: [
      // Add cities for Shanxi province if available
    ],
  },
  {
    value: '辽宁省',
    label: '辽宁省',
    children: [
      // Add cities for Liaoning province if available
    ],
  },
  {
    value: '吉林省',
    label: '吉林省',
    children: [
      // Add cities for Jilin province if available
    ],
  },
  {
    value: '黑龙江省',
    label: '黑龙江省',
    children: [
      // Add cities for Heilongjiang province if available
    ],
  },
  {
    value: '江苏省',
    label: '江苏省',
    children: [
      // Add cities for Jiangsu province if available
    ],
  },
  {
    value: '浙江省',
    label: '浙江省',
    children: [
      // Add cities for Zhejiang province if available
    ],
  },
  {
    value: '安徽省',
    label: '安徽省',
    children: [
      // Add cities for Anhui province if available
    ],
  },
  {
    value: '福建省',
    label: '福建省',
    children: [
      // Add cities for Fujian province if available
    ],
  },
  {
    value: '江西省',
    label: '江西省',
    children: [
      // Add cities for Jiangxi province if available
    ],
  },
  {
    value: '山东省',
    label: '山东省',
    children: [
      // Add cities for Shandong province if available
    ],
  },
  {
    value: '河南省',
    label: '河南省',
    children: [
      // Add cities for Henan province if available
    ],
  },
  {
    value: '湖北省',
    label: '湖北省',
    children: [
      // Add cities for Hubei province if available
    ],
  },
  {
    value: '湖南省',
    label: '湖南省',
    children: [
      // Add cities for Hunan province if available
    ],
  },
  {
    value: '广东省',
    label: '广东省',
    children: [
      // Add cities for Guangdong province if available
    ],
  },
  {
    value: '海南省',
    label: '海南省',
    children: [
      // Add cities for Hainan province if available
    ],
  },
  {
    value: '四川省',
    label: '四川省',
    children: [
      // Add cities for Sichuan province if available
    ],
  },
  {
    value: '贵州省',
    label: '贵州省',
    children: [
      // Add cities for Guizhou province if available
    ],
  },
  {
    value: '云南省',
    label: '云南省',
    children: [
      // Add cities for Yunnan province if available
    ],
  },
  {
    value: '陕西省',
    label: '陕西省',
    children: [
      // Add cities for Shaanxi province if available
    ],
  },
  {
    value: '甘肃省',
    label: '甘肃省',
    children: [
      // Add cities for Gansu province if available
    ],
  },
  {
    value: '青海省',
    label: '青海省',
    children: [
      // Add cities for Qinghai province if available
    ],
  },
  {
    value: '台湾省',
    label: '台湾省',
    children: [
      // Add cities for Taiwan province if available
    ],
  },
  {
    value: '内蒙',
    label: '内蒙古自治区',
    children: [
      // Add cities for Inner Mongolia Autonomous Region if available
    ],
  },
  {
    value: '广西',
    label: '广西壮族自治区',
    children: [
      // Add cities for Guangxi Zhuang Autonomous Region if available
    ],
  },
  {
    value: '西藏',
    label: '西藏自治区',
    children: [
      // Add cities for Tibet Autonomous Region if available
    ],
  },
  {
    value: '宁夏',
    label: '宁夏回族自治区',
    children: [
      // Add cities for Ningxia Hui Autonomous Region if available
    ],
  },
  {
    value: '新疆',
    label: '新疆维吾尔自治区',
    children: [
      // Add cities for Xinjiang Uygur Autonomous Region if available
    ],
  },
  {
    value: '北京市',
    label: '北京市',
  },
  {
    value: '天津市',
    label: '天津市',
  },
  {
    value: '上海市',
    label: '上海市',
  },
  {
    value: '重庆市',
    label: '重庆市',
  },
  {
    value: '香港',
    label: '香港特别行政区',
  },
  {
    value: '澳门',
    label: '澳门特别行政区',
  }
]);



const filterTableData = computed(() =>
  tableData.value.filter((data) =>
    (!search.value ||
      data.info.toLowerCase().includes(search.value.toLowerCase()) ||
      data.name.toLowerCase().includes(search.value.toLowerCase()) ||
      data.address.toLowerCase().includes(search.value.toLowerCase())||
	  data.description.toLowerCase().includes(search.value.toLowerCase())
    )&&
    (!valueaddress.value.length || 
      valueaddress.value.some(address => data.address.toLowerCase().includes(address.toLowerCase()))
    )&&
    // 根据 valueprice 进行价格区间的过滤
    (data.price >= valueprice.value[0] && data.price <= valueprice.value[1])
  )
)
// const tableData=ref([
// 	{
// 		name:"江安养老院",
// 		address:"成都市双流区",
// 		info:"有不高山，长桥等多个景点，环境优美",
// 		price:250,
// 		rate:10,
// 		star:true,
// 		phone:"1111111",
// 		image:["src/assets/care/care1.png","src/assets/care/care2.png","src/assets/care/care3.png"],
// 		description:"在川大江安校区近一千米长的景观水道两侧，坐落着七十二幅日历造型的雕塑作品群。这就是校长谢和平院士首倡，作为校园文化融合标志性成果的四川大学历史文化长廊。它努力体现四川大学文化融合的主旋律，用简要的文字、形象的历史照片和雅致的艺术设计，将学校110年来的重大事件、杰出人物和重要成果版块式、艺术化地加以呈现，成为学校见证悠久的发展历史、彰显一流的办学成就、传承深厚的文化底蕴和展现辉煌的远景蓝图的重要人文景观。同时，它与各大教学楼和各学院的学术长廊相得益彰，更使全校师生时时处处感受四川大学校园内高雅的文化品位和浓郁的学术气息 。从此处看景观水道，远处东大门国立四川大学的牌坊隐约可见。",
// 	},
// 	{
// 		name:"望江养老院",
// 		address:"成都市武侯区",
// 		info:"环境优美",
// 		price:300,
// 		rate:20,
// 		star:false,
// 		phone:"22222222",
// 		image:["src/assets/care/care2.png","src/assets/care/care1.png","src/assets/care/care3.png"],
// 		description:"四川大学望江校区是原四川大学与原成都科技大学的所在地。总占地3000余亩，主要为四川大学大三、大四及硕、博士研究生同学学习的地方，是四川大学的校本部。望江校区位于成都市一环路上，分设东西南北四个门，交通方便，地理位置优越。作为最早的川大所在地，望江校区历经百年风雨，巴山灵秀，咸聚于此，蜀地彦才，聚汇于斯，校园内历史文化底蕴厚重，是读书治学的理想园地。大三学子搬到望江校区之后，大家都有一种“所谓大学当如是”的感觉。",
// 	},
// 	{
// 		name:"华西养老院",
// 		address:"成都市武侯区",
// 		info:"环境优美，有良好的医疗设施",
// 		price:350,
// 		rate:30,
// 		star:true,
// 		phone:"33333333",
// 		image:["src/assets/care/care3.png","src/assets/care/care2.png","src/assets/care/care1.png"],
// 		description:"华西医学中心前身私立华西协合大学由美国、英国、加拿大等5个教会组织在成都华西坝创办创办于1910年3月11日，历经华西大学、四川医学院、华西医科大学等阶段，于1998年成为首批4所“211工程”医学类院校之一。2000年9月四川大学和华西医科大学合并，组建新四川大学，华西医科大学更名为四川大学华西医学中心。",
// 	},
// ])
const tableData = ref([]); // 使用 ref 创建响应式数据
const pageSize = 8; // 每页加载的数据量
const flag=ref()
const page=ref();
const scoll=ref();
const fetchData = () => {
	page.value++;
	localStorage.setItem('carepage',page.value);
		if(flag.value){
			fetch(`http://101.35.20.149:8081/resthomes/get?page=${page.value}&pageSize=${pageSize}&userId=${userId}`, {
			    method: 'GET',
			    headers: {
			        'Content-Type': 'application/json'
			    }
			}).then(response => {
			    return response.json(); // 将响应转换为 JSON 格式
			}).then(data => {
			    tableData.value = [...tableData.value, ...data.data]; // 追加新数据
			    localStorage.setItem('caredata', JSON.stringify(tableData.value));
			    console.log(data.data.length);
			    if(data.data==0){
			    	ifmorecares.value=false;
			    	flag.value=false;
			    	localStorage.setItem('careflag',flag.value);
			    	console.log('finiallll')
			    }
			    else{
			    	ifmorecares.value=true;
			    	localStorage.setItem('careflag',flag.value);
			    	
			    }
			    
			}).catch(error => {
			    console.error('请求出错', error);
			});
		}
        
    //}
};

const carehandleScroll = () => {
	console.log('scroll')
	console.log('外面的scroll为'+parseInt(localStorage.getItem('carescroll')))
	if(window.scrollY<16&&parseInt(localStorage.getItem('carescroll'))!=0){
		window.scrollTo(0,parseInt(localStorage.getItem('carescroll')));
	}
	
	scoll.value=window.scrollY
	localStorage.setItem('carescroll',scoll.value)
    const moreCares = document.querySelector('.morecares');
    if (moreCares) {
        const moreCaresOffsetTop = moreCares.getBoundingClientRect().top;
        const windowHeight = window.innerHeight;
		//console.log(moreCaresOffsetTop,windowHeight)
        if (moreCaresOffsetTop <= windowHeight) {
			     console.log('moredata')
			     fetchData();	 
					
        }
    }
};

window.addEventListener('scroll', carehandleScroll);

onMounted(() => {
    console.log('打开界面')
    flag.value=localStorage.getItem('careflag')||true;
    tableData.value=JSON.parse(localStorage.getItem('caredata') || '[]');
    //window.scrollTo(0,parseInt(localStorage.getItem('medscroll')));
    page.value=localStorage.getItem('carepage') || 0;
    //console.log(flag.value)
    //console.log(tableData.value)
    //console.log(page.value)
    fetchData();
    
    const storedAddress = JSON.parse(localStorage.getItem('careaddress') || '[]');
    valueaddress.value = storedAddress;
    search.value=localStorage.getItem('caresearch')||'';
	valueprice.value=JSON.parse(localStorage.getItem('careprice') || '[1000,6000]');
    console.log('mounted')
	
});

onUnmounted(() => {
	console.log('unmounted')
	console.log(localStorage.getItem('careflag'),flag.value)
	console.log(localStorage.getItem('caredata').length)
	console.log(localStorage.getItem('carepage'))
    window.removeEventListener('scroll',carehandleScroll);

	
})



</script>
<style>
.loading {
    animation: rotate 2s linear infinite;
}

@keyframes rotate {
    from {
        transform: rotate(0deg);
    }

    to {
        transform: rotate(360deg);
    }
}

.slider{
	width: 340px;
}
.searchprice{
	width:30vw ;
}
.searchaddress{
	width:30vw ;
	
}
.morecares{
	height: 2vh;
}
.searchall{
	width: 30vw;
	margin-left: 3vw;
	
}
.careprice{
	color:#E6A23C;
	font-size: 16px;
	text-align: left;
	font-weight: bold;
	
}
.carebed{
	color:#E6A23C;
	font-size: 16px;
	text-align: left;
	font-weight: bold;
	
}
#careinfo{
	color: #b88230;
	font-size: 17px;
	text-indent: 1em;
	height: 18vh;
}
.careadress{
	font-size: 15px;
	text-align: left;
	color: #999;
	display: -webkit-box;
	-webkit-line-clamp: 3;
	-webkit-box-orient: vertical;
	overflow: hidden;
}
.careaname {
    font-size: 22px;
    font-weight: bold;
    text-align: left;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    overflow: hidden;
}
.card{
	//height:60vh ;
}
.card-row {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr)); /* 每列最小宽度为 200px，自适应屏幕宽度 */
  gap: 10px; /* 列之间的间隔 */
}
.search{
	position: fixed;
	z-index: 999;
	height: 10vh;
	background-color: white;
	width: 96vw;
}
.careinfo{
	width: 86vw;
	margin: 0 auto;
}
.careimage{
  height: 70%;
  object-fit: cover;
  overflow: hidden; 
}
.caremain{
	margin-top: 5vh;
	background-color: #FFDDAA;
}
.carerate{
	color:#E6A23C;
	font-size: 20px;
	text-align: right;
	font-weight: bold;

}
</style>