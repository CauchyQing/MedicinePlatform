<template>
	<div class="caresback">
		

	<div class="caresmain">
        <br /><br />
			<el-image
			      class="caresimage"
			      :src="props.cares.image[0]"
			      :zoom-rate="1.2"
			      :max-scale="7"
			      :min-scale="0.2"
			      :initial-index="4"
				  :preview-src-list="props.cares.image"
			      fit="cover"
			    />
	
		<div class="caresname">
			
			 <el-icon @click="toggleStar" class="stars">
			    <StarFilled v-if="ifstar" />
			    <Star v-else />
			  </el-icon>
			  {{props.cares.name}}
		</div>

		<div class="caresaddress">
			{{props.cares.address}}
		</div>
		<div class="caresinfo">
			{{props.cares.info}}
		</div>
<!-- 		<div class="caresrate">
			评分：{{props.cares.rate}}分
		</div> -->
		<div class="caresbed">
			 床位：{{props.cares.oldHomeBeds}}
		</div>
		<div class="caresprice">
			￥{{props.cares.price}}元起
		</div>

		<div class="caresdes">
			{{props.cares.description}}
		</div>
		<div class="caresphone">
		    联系电话：{{props.cares.phone}}
		</div>
		<el-button type="warning" class="reserve" @click="reserve(props.cares.name,props.cares.phone)">咨询</el-button>
		<br /><br /><br /><br />
		<div class="medicalscomment">
			&nbsp;&nbsp;&nbsp;评论
			<div class="medicalscommentput">
				&nbsp;&nbsp;&nbsp;{{username}}
				  <div class="mputoutm">
				  	  <el-input
				  	    v-model="textarea"
				  	    style="width: 70vw"
				  	    autosize
				  	    type="textarea"
				  	    placeholder="输入评论"
				  	  />&nbsp;&nbsp;
				  	  <el-button type="warning" @click="output">发布评论</el-button>
				  </div>
			</div>
			<div class="allmcoms">
				<el-divider />
				<div v-for="comment in comments" :key="comment.username">
					     <div class="mcoms">
							 <div class="commentname">
							 	{{ comment.username }}&nbsp;&nbsp;&nbsp;{{comment.postTime}}
							 </div>
							 <div class="commenttext">
							 	{{ comment.comment }}
							 </div>
					     	
					     	
					     </div>
						
				<el-divider />
				</div>
			</div>
		</div>
		
		
		<div class="temp"></div>
	</div>
	<el-backtop :right="40" :bottom="100" />
	<el-button circle class="back" color="white" @click="back"><el-icon><ArrowLeftBold /></el-icon></el-button>
		</div>
</template>

<script script lang="ts" setup>
import { defineEmits } from 'vue';
import {defineProps ,ref } from 'vue';
import { ElMessage, ElMessageBox } from 'element-plus'
import type { Action } from 'element-plus'
import { onMounted,onUnmounted} from 'vue'
const emit = defineEmits(['from'])
const props = defineProps(['cares',"from"]);
const ifstar = ref(props.cares.star);
const userId =  Number(localStorage.getItem('userId'));
const username=localStorage.getItem('username')
const starinfo=ref({
	userId:userId,
	orgId:props.cares.oldHomeId,
})
const textarea = ref('')


const comments=ref([])

const fetchData = () => {
   
			fetch(`http://101.35.20.149:8081/resthomes/comment/${props.cares.oldHomeId}`, {
			    method: 'GET',
			    headers: {
			        'Content-Type': 'application/json'
			    }
			}).then(response => {
			    return response.json(); // 将响应转换为 JSON 格式
			}).then(data => {
			    comments.value = data.data; // 追加新数据
				console.log(comments.value);
			    
			}).catch(error => {
			    console.error('请求出错', error);
			});
	
        
};


onMounted(() => {
	window.scrollTo(0,0);
    fetchData();
});
onUnmounted(() => {
	console.log(parseInt(localStorage.getItem('carescroll')))
    window.scrollTo(0,0);
})



const commentinfo=ref({
	userId:userId,
	orgId:props.cares.oldHomeId,
	comment:"",
})
const output=()=>{
	//console.log(textarea.value)
	commentinfo.value.comment=textarea.value;
	console.log(commentinfo.value)
	
	ElMessage({
	    message: '发布成功',
	    type: 'success',
	  })
	
	
	fetch('http://101.35.20.149:8081/resthomes/comment', {
	  method: 'POST', 
	  body: JSON.stringify(commentinfo.value), // 添加需要更新的数据
	  headers: {
	    'Content-Type': 'application/json'
	  }
	}).then(response => {
	  return response.json(); // 将响应转换为 JSON 格式
	}).then(data => {
      if (data.code == 1) {
          fetchData();
          console.log(data);
      }
	 
	}).catch(error => {
	  console.error('请求出错', error);
	});
	
    
	textarea.value="";
}













const toggleStar = () => {
      ifstar.value = !ifstar.value;
	  console.log(starinfo)
	  if(ifstar.value==true){
	  		  fetch('http://101.35.20.149:8081/resthomes/star', {
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
	  		  fetch('http://101.35.20.149:8081/resthomes/unstar', {
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
const reserve = (name,phone) => {
  let title = name + '注意事项';
  let message = ``;
  message += `<p>1.请确认咨询${name} 。</p>`;
  message += `<p>2.提交信息之后，工作人员将在7个工作日内与您联系！请保持手机畅通！</p>`;
  message += `<p>3.本平台提供的信息仅供参考。</p>`;
  message += `<p>4.本平台不收取任何费用，请勿向任何声称是本平台的机构转账。</p>`;
  message += `<p>5.任何问题请致电${name}，联系电话：${phone} 。</p>`;
  ElMessageBox.confirm(
    message,
    title,
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
	  dangerouslyUseHTMLString: true, // 允许显示 HTML 字符串
    }
  )
    .then(() => {
      fetch('http://101.35.20.149:8081/resthomes/consult', {
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
	  
	  

      ElMessage({
        type: 'success',
        message: '请求咨询成功',
      });
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '取消咨询',
      });
    });
}
const back=()=>{
	emit('from', props.from)
}
</script>

<style>
.commentname{
	font-size: 18px;
	color:#999
}
.commenttext{
	font-size: 20px;
	margin-top: 1vh;
}
.mcoms{
	//margin-left:5vw ;
	margin: 0 5vw;
}
.mputoutm{
	//float: right;
	//margin-right: 20px;
	//width: 80px;
	text-align: right;
	margin-right: 20px;
	margin-top: 5px;
}
.mtextm{
	float: right;
	margin-right: 10px;
	width: 90px;
	margin-top: 5px;
}
.medicalscommentput{
	font-size: 22px;
	font-weight: 400;
    color: #999;
}
.medicalscomment{
	background-color:#fff;
	//height: 30vh;
	width: 80vw;
	border-radius: 10px; 
	text-align: left;
	font-size: 23px;
	font-weight: bold;
}
.temp{
	height: 20vh;
}
.stars{
	font-size: 40px;
	
}
.reserve{
	float: left;
	margin-left: 2vw;
	margin-top: 2vh;
	width: 20vw;
	height: 5vh;
	font-size:18px;
}
.caresphone{
	font-size: 18px;
	text-align: right;
	color: #999;
}
.caresdes{
	font-size: 20px;
	text-align: left;
	text-indent: 2em;
}	
.caresprice{
	font-size: 25px;
	font-weight: bold;
	text-align: left;

	color:#E6A23C;
}
.caresbed{
	font-size: 25px;
	font-weight: bold;
	text-align: left;
	float: left;
	color:#E6A23C;
}
.caresinfo{
	font-size: 18px;
	text-align: left;
	color: #b88230;
	text-indent: 1em;
}
.caresname{
	font-size: 30px;
	font-weight: bold;
	text-align: left;
}
.caresaddress{
	font-size: 18px;
	text-align: left;
	color: #999;
}
.caresrate{
	font-size: 25px;
	font-weight: bold;
	text-align: left;

	color:#E6A23C;
}
.caresmain{
	margin: 8vw;
	
}
.caresback{
	background-color: #FFDDAA;
}
.caresimage{
	width: 80vw;
	height: 40vh;
}
</style>