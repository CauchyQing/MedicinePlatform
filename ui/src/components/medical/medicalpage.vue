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
			 
		   
	</div>
	<br /><br /><br /><br />
	<div class="careinfo">
        <el-row :gutter="10" class="card-row">
          <el-col v-for="(item, index) in filterTableData" :key="index">
            <el-card shadow="hover" class="card" @click="getcaredetail(item)">
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
<!-- 				   <div class="carerate">
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
const ifmorecares=ref(true)
const emit = defineEmits(['selectDes','medicaldetail'])
const getcaredetail = (item) => {
	//console.log(index)
	emit('selectDes', 8)
	emit('medicaldetail',item)
	localStorage.setItem('meddata', JSON.stringify(tableData.value));
	localStorage.setItem('medsearch',search.value);
	localStorage.setItem('medaddress', JSON.stringify(valueaddress.value));

}
const search = ref('')
const valueaddress = ref([])
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
      data.info.toLowerCase().includes(search.value.toLowerCase())||
      data.name.toLowerCase().includes(search.value.toLowerCase())||
      data.address.toLowerCase().includes(search.value.toLowerCase())
    
    ) &&
    (!valueaddress.value.length || 
      valueaddress.value.some(address => data.address.toLowerCase().includes(address.toLowerCase()))
    )
  )
)
// const tableData=[
// 	{
// 		name:"成都市第二人民医院",
// 		address:"四川省成都市青羊区草市街165号",
// 		info:"三级甲等，综合医院，公立公办医院",
// 		phone:"028-67833912",
// 		rate:'10',
// 		star:true,
// 		image:["src/assets/medical/medical1.jpg","src/assets/medical/medical2.jpg","src/assets/medical/medical3.jpg"],
// 		description:"成都市第二人民医院，是一所集医疗、教学、科研、预防、保健、康复为一体的国家三级甲等综合医院。始建于1892年，由加拿大基督教会医学传教士创办，原名福音医院，后又称四川红十字会福音医院、仁济医院，是西医入川原点、四川红十字运动发源地和第一家红十字医院，也是四川大学华西医院的前身。为“全国文明单位”、全国总工会“模范职工之家”、全国卫生系统“卫生文化建设先进单位”、全国“改革创新医院”“改善服务创新医院”“改善医疗服务示范医院”。",
//      },
// 	 {
// 	 	name:"成都市第二人民医院",
// 	 	address:"四川省成都市青龙街82号",
// 	 	info:"三级甲等，综合医院，公立公办医院",
// 	 	phone:"028-67833333",
// 		rate:'20',
// 		star:false,
// 	 	image:["src/assets/medical/medical2.jpg","src/assets/medical/medical2.jpg","src/assets/medical/medical1.jpg"],
// 	 	description:"成都市第三人民医院，始建于1941年7月，是集医疗、科研、教学、预防、保健和康复为一体的国家三级甲等综合性医院。2014年被省卫生计生委确定为四川省九大区域医疗中心之一，是四川省首批现代医院管理制度试点单位，承担成都中心城区医疗保障的核心功能。医院占地面积138亩，开放床位2200张，年诊疗约154万余人次，出院6.4万余人次，住院手术3.8万余台次。 [5]",
// 	},
// 	{
// 	 	name:"成都市第五人民医院",
// 	 	address:"成都市温江区麻市街33号",
// 	 	info:"三级甲等,综合医院",
// 	 	phone:"028-6783555555",
// 		rate:'30',
// 		star:true,
// 	 	image:["src/assets/medical/medical3.jpg","src/assets/medical/medical2.jpg","src/assets/medical/medical1.jpg"],
// 	 	description:"成都市第五人民医院始建于1938年， [1]是一所历史悠久、文化底蕴丰厚，以肿瘤疾病防治和老年病综合治疗为特色的集医疗、教学、科研、预防、康复、急救于一体的国家三级甲等综合医院，是成都中医药大学附属第五人民医院/第二临床医学院。是国家级胸痛中心、国家级高级卒中中心、国家级心衰中心、成都西部危重孕产妇新生儿救治中心、成都西部创伤急救中心、生殖医学中心、成都西部区域医疗中心。医院包含三区（综合医疗区、老年健康产业园区、教学园区），三所（中国成都—法国蒙彼利埃医疗中心老年疾病研究所、成都市老年疾病研究所、成都市肿瘤疾病防治所）。 [3]",
// 		}
// ]

const tableData = ref([]); // 使用 ref 创建响应式数据
const pageSize = 8; // 每页加载的数据量
const flag=ref()
const page=ref();
const scoll=ref();
const fetchData = () => {
	    
	    page.value++;
		localStorage.setItem('medpage',page.value);
		console.log(flag.value)
		if(flag.value){
			console.log('fetch')
			fetch(`http://101.35.20.149:8081/hospitals/get?page=${page.value}&pageSize=${pageSize}&userId=${userId}`, {
			    method: 'GET',
			    headers: {
			        'Content-Type': 'application/json'
			    }
			}).then(response => {
			    return response.json(); // 将响应转换为 JSON 格式
			}).then(data => {
			    tableData.value = [...tableData.value, ...data.data]; // 追加新数据
				localStorage.setItem('meddata', JSON.stringify(tableData.value));
				console.log(data.data.length);
				if(data.data==0){
					ifmorecares.value=false;
					flag.value=false;
					localStorage.setItem('medflag',flag.value);
					console.log('finiallll')
				}
				else{
					ifmorecares.value=true;
					localStorage.setItem('medflag',flag.value);
					
				}
			    
			}).catch(error => {
			    console.error('请求出错', error);
			});
		}
        
    // }
};

const handleScroll = () => {
	
	console.log('scroll')
	console.log('外面的scroll为'+parseInt(localStorage.getItem('medscroll')))
	if(window.scrollY<16&&parseInt(localStorage.getItem('medscroll'))!=0){
		// console.log('doing')
		// console.log(parseInt(localStorage.getItem('medscroll')))
		window.scrollTo(0,parseInt(localStorage.getItem('medscroll')));
	}
	
	scoll.value=window.scrollY
	localStorage.setItem('medscroll',scoll.value)
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



window.addEventListener('scroll', handleScroll);

onMounted(() => {
	console.log('打开界面')
	
	
	flag.value=localStorage.getItem('medflag')||true;
	tableData.value=JSON.parse(localStorage.getItem('meddata') || '[]');
	//window.scrollTo(0,parseInt(localStorage.getItem('medscroll')));
	page.value=localStorage.getItem('medpage') || 0;
	//console.log(flag.value)
	//console.log(tableData.value)
	//console.log(page.value)
    fetchData();
	
	const storedAddress = JSON.parse(localStorage.getItem('medaddress') || '[]');
	valueaddress.value = storedAddress;
	search.value=localStorage.getItem('medsearch')||'';
	console.log('mounted')

});

onUnmounted(() => {
	console.log('unmounted')
	console.log(localStorage.getItem('medflag'),flag.value)
	console.log(localStorage.getItem('meddata').length)
	console.log(localStorage.getItem('medpage'))
    window.removeEventListener('scroll',handleScroll);

	
})

</script>
<style>
.morecares{
	height: 2vh;
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
.searchall{
	width: 30vw;
	margin-left: 3vw;
	
}
.careprice{
	color:#E6A23C;
	font-size: 23px;
	text-align: right;
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
}
.careaname{
	font-size: 23px;
	font-weight: bold;
	text-align: left;
}
.carerate{
	color:#E6A23C;
	font-size: 20px;
	text-align: left;
	font-weight: bold;

}
.card{
	height:40vh ;
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


</style>