<template>
	<div class="healthmain">
		<div class="basic">
			<el-popover
			    placement="bottom-start"
			    :width="250"
			    trigger="hover"
			    :title="'体温: ' + healthinfo.temperature+'℃'"
			    content="直肠的正常温度为36.9~37.9℃、口腔温度为36.3~37.2℃、腋下温度为36.1~37℃"
			>
			    <template #reference>
			      
				  
				  <el-progress
				      type="circle" 
				      :percentage="healthinfo.temperature" 
				      :stroke-width="15" 
				      :width="250" 
					  class="tempt"
				      color="#67C23A"
					  >
								 <el-progress
									 type="circle" 
									 :percentage="heartbeat/1.2" 
									 class="heartbeat"
									 :stroke-width="20" 
									 :width="200" 
									 color="#F56C6C"
													  
								 >
									 <template #default>
										 <el-popover
										     placement="top"
										     :width="50"
										     trigger="hover"
										     content="成年人的正常心率范围是60~100次/分"
										   >
										     <template #reference>
										 <div class="heartbeat">
										 	<span class="percentage-label">心跳</span><br />
										 	<span class="percentage-value">每分{{ healthinfo.heartbeat }}次</span>
										 </div>
										 </template>
										 </el-popover>	 
									 </template>
								 </el-progress> 

					  
					  
					  
				  </el-progress>

			    </template>
			  </el-popover>
			  
			  <br />
			  <el-progress type="dashboard" :percentage="healthinfo.height/2" class="board" :stroke-width="15" :width="150" >
			        <template #default>
			          
			          <span class="percentage-label">身高</span><br />
			  		<span class="percentage-value">{{healthinfo.height }}cm</span>
			        </template>
			  </el-progress>
			  
			  <el-progress type="dashboard" :percentage="healthinfo.weight/1.8" class="board" :stroke-width="15" :width="150" >
			        <template #default>
			          
			          <span class="percentage-label">体重</span><br />
			  		<span class="percentage-value">{{healthinfo.weight }}kg</span>
			        </template>
			  </el-progress>
			  
			  
			  
			  
			  
			  
			  
			  
			  
			  <el-popover
			      placement="top-end"
			      :width="250"
			      trigger="hover"
			      content="总胆固醇正常标准值一般小于5.18mmol/L"
			    >
			      <template #reference>
			        <el-progress
			             :text-inside="true"
			             :stroke-width="30"
			             :percentage="(healthinfo.bloodFat)*10"
			             status="warning"
			        	 class="bloodleft"
						 striped
					     striped-flow
						:duration="15"
						
			           >
			             <span>血脂：{{healthinfo.bloodFat}}</span>
			        </el-progress>
			      </template>
			    </el-popover>
			  
			  <el-popover
			      placement="bottom-end"
			      :width="250"
			      trigger="hover"
			      content="正常的空腹血糖值在3.9～6.1毫摩尔/升"
			    >
			      <template #reference>
			        <el-progress
			             :text-inside="true"
			             :stroke-width="30"
			             :percentage="(healthinfo.bloodSugar)*10"
			             status="exception"
			        	 class="bloodleft"
			  			 striped
			  			 striped-flow
			  			 :duration="15"
			           >
			             <span>血糖：{{healthinfo.bloodSugar}}</span>
			        </el-progress>
			      </template>
			    </el-popover>
			  
			<el-popover
			    placement="top-start"
			    :width="250"
			    trigger="hover"
			    content="低压的正常值为60~89mmHg"
			  >
			    <template #reference>
			      <el-progress
			          :text-inside="true"
			          :stroke-width="30"
			          :percentage="healthinfo.bloodLowPressure/1.5"
			          status="success"
			          class="bloodright custom-progress"
					  striped
					  striped-flow
					  :duration="30"
			      >
			          <span class="textleft">低压：{{healthinfo.bloodLowPressure}}</span>
			      </el-progress>
			    </template>
			  </el-popover>
			
			<el-popover
			    placement="bottom-start"
			    :width="250"
			    trigger="hover"
			    content="高压的正常值为90~140mmHg"
			  >
			    <template #reference>
			      <el-progress
			          :text-inside="true"
			          :stroke-width="30"
			          :percentage="healthinfo.bloodHighPressure/1.6"
			          status="success"
			          class="bloodright custom-progress"
					  striped
					  striped-flow
					  :duration="30"
			      >
			          <span class="textleft">高压：{{healthinfo.bloodHighPressure}}</span>
			      </el-progress>
			    </template>
			  </el-popover>
			
			<el-button type="warning"  class="changehealth" @click="changehealth">修改健康信息</el-button>
			<el-button type="warning"  class="checkhealth" @click="checkhealth">查看健康报告</el-button>   
             
			 <br /><br /><br /><br /><br /><br /><br /><br />
			 <div class="developer">
			   项目小组：<br />
			   智慧医养大数据部<br /><br />
			   
			   本项目技术人员：<br />
			   邓子祎、卿柯序、张师仪、步一凡、李嘉仪、龙小毅<br />
			   (以上排名不分先后)<br /><br />
			   
			   特别鸣谢：<br />
			   感谢项目组所有成员的积极配合！<br /><br />
			   
			   特别声明：<br />
			   本项目所有数据均来自网络，若有侵权请联系我们!<br /><br />
			   
			   特别提醒：<br />
			   本网站不收取任何费用，请勿向任何声称是本平台的机构或个人转帐！！！<br /><br />
			   
			   平台服务：<br />
			   若您不会使用本平台，请联系我们！我们将为您提供帮助服务！<br /><br />
			   
			   建议收集：<br />
			   如果您对本项目有任何建议，欢迎联系我们！<br /><br />
			   
			   入驻平台：<br />
			   若您是医疗机构或者养老机构，欢迎入驻本平台！<br /><br />
			   
			 </div>
			 
			 
			 
			 
			 
			<el-drawer v-model="drawer1" :with-header="false"  direction="ttb" size="75%" >
			    <el-form :model="changehealthinfo" class="drawer">
			      <el-form-item label="血脂" class="changelabels">
			        <el-input v-model="changehealthinfo.bloodFat" class="changeinput"/>
			      </el-form-item>
                  <el-form-item label="血糖" class="changelabels">
                    <el-input v-model="changehealthinfo.bloodSugar" class="changeinput"/>
                  </el-form-item>
			      <el-form-item label="低压" class="changelabels">
			        <el-input v-model="changehealthinfo.bloodLowPressure" class="changeinput"/>
			      </el-form-item>
				  <el-form-item label="高压" class="changelabels">
				    <el-input v-model="changehealthinfo.bloodHighPressure" class="changeinput"/>
				  </el-form-item>
				  <el-form-item label="心率" class="changelabels">
				    <el-input v-model="changehealthinfo.heartbeat" class="changeinput"/>
				  </el-form-item>
				  <el-form-item label="身高" class="changelabels">
				    <el-input v-model="changehealthinfo.height" class="changeinput"/>
				  </el-form-item>
				  <el-form-item label="体重" class="changelabels">
				    <el-input v-model="changehealthinfo.weight" class="changeinput"/>
				  </el-form-item>
				  <el-form-item label="体温" class="changelabels">
				    <el-input v-model="changehealthinfo.temperature" class="changeinput"/>
				  </el-form-item>
				  <el-form-item label="医疗史" class="changelabels">
				    <el-input v-model="changehealthinfo.medicalHistory" class="changeinput"/>
				  </el-form-item>
				  <el-form-item label="特殊" class="changelabels">
				    <el-input v-model="changehealthinfo.special" class="changeinput"/>
				  </el-form-item>

				  
			       <el-button type="warning"  class="submit" @click="submit">确认</el-button>
			       <el-button type="warning"  class="withdraw" @click="withdraw">取消</el-button>   
			       			
			    </el-form>
				<br /><br /><br />
			</el-drawer>
			
			
          <el-drawer v-model="drawer2" :with-header="false" direction="ttb" size="75%">
			   <div class="healthsuggestion">
			   	  <div class="healthtitle">
			   	  	健康报告
			   	  </div>
			   	  您的症状有: <br />
			   	  <div class="healthcontent">
			   	  	{{checkhealthinfo.symp}}
			   	  </div>
			   	  饮食建议:  <br />
			   	  <div class="healthcontent">
			   	  	{{checkhealthinfo.diet}}
			   	  </div>
			   	  生活建议:  <br />
			   	  <div class="healthcontent">
			   	  {{checkhealthinfo.daily}}
			   	  </div>
			   	  运动建议:  <br />
			   	  <div class="healthcontent">
			   	  	{{checkhealthinfo.sport}}
			   	  </div>
			   </div>
			   
                <div class="explain">
					<br /><br />
                	以上报告不代表专业医生诊断，仅供参考，若您发现部分信息有所矛盾，说明您的部分症状之间有相互排斥的效果，建议咨询医生详情，若有身体不适建议尽快就医。
                </div>
           </el-drawer>
			
			
	
			
			<br /><br /><br /><br />
		</div>
	</div>
</template>

<script lang="ts" setup>
import { computed, ref ,watch} from 'vue'
import type { CheckboxValueType } from 'element-plus'
import { defineEmits } from 'vue'
import { onMounted} from 'vue'
import type { Action } from 'element-plus'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useRouter } from "vue-router";
const router = useRouter();
const userId= Number(localStorage.getItem('userId'))
const drawer1 = ref(false)
const drawer2 = ref(false)

// const healthinfo=ref({
// 	height:158,
// 	weight:100,
// 	bloodpressurelow:100,
// 	bloodpressureup:110,
// 	bloodglucose:5.3,
// 	bloodfat:4.18,
// 	heartbeat:80,
// 	temperature:36.5,
// 	medicalhistory:"患有白内障，心脏瓣膜关闭不全，有痛风史",
// 	special:"独居",
// })
const changehealthinfo=ref({
	userId:userId,
	height:null,
	weight:null,
	bloodLowPressure:null,
	bloodHighPressure:null,
	bloodSugar:null,
	bloodFat:null,
	heartbeat:null,
	temperature:null,
	medicalHistory:null,
	special:null,
})


const changeput = () => {
  fetch('http://101.35.20.149:8081/users/health/update', {
    method: 'POST', // 使用 PUT 方法
    body: JSON.stringify(changehealthinfo.value), // 添加需要更新的数据
    headers: {
      'Content-Type': 'application/json'
    }
  }).then(response => {
    return response.json(); // 将响应转换为 JSON 格式
  }).then(data => {
    console.log(data)
	if(data.code==1){
		fetchData();
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
       message: '修改健康信息成功',
     });
   })
   .catch(() => {
     ElMessage({
       type: 'info',
       message: '取消修改健康信息',
     });
   });
}








const heartbeat=ref(0)
const increaseHeartbeat = () => {
  const timer = setInterval(() => {
    heartbeat.value += 10;

    if (heartbeat.value >= healthinfo.value.heartbeat) {
      heartbeat.value=0;
    }
  }, 500);
};

const changehealth=()=>{
	drawer1.value=true
}
const checkhealthinfo=ref({
	"symp":"",
	"diet":"",
	"daily":"",
	"sport":""
})
const suggestions=ref(
   [
     {
       "symp": "高血压",
       "diet": "饮食清淡，低盐、低脂饮食，每日食盐摄入量，应在六克以下，每餐不宜吃得过饱，多食新鲜的蔬菜和水果，戒烟限酒，品茶清淡。",
       "daily": "合理休息，早睡不熬夜，每日保证充足的睡眠时间，避免劳累，紧张，郁闷等不良因素。保持心情舒畅，尽量做到心情愉快，情绪稳定，乐观向上。",
       "sport": "缓慢起床，早晨醒来，不要急于起床，先在床上活动一下四肢和头颈部，慢慢坐起，然后再下床，以适应起床时的体位变化，避免头晕。坚持长期适量、适时的锻炼，不宜做剧烈运动，以选择步行，太极拳等活动。"
     },
       {
       "symp": "低血压",
       "diet": "在治疗期间要做好饮食调节，宜营养均衡，可以适当多补充盐分和高胆固醇的食物，如动物肝脏、蛋黄、鱼子酱等。平时可以每天喝一杯淡盐水，避免饮酒及喝咖啡，",
       "daily": "造成低血压的原因有很多，其主要是因为血压下降后，导致血管内的血液循环不畅，从而影响血液中的氧气和营养在体内的供给。可以在医生的指导下进行正确的药物治疗，常用药物有氟氢可的松片和米多君片等，可以有效收缩血管，维持血容量，从而升高血压。",
       "sport": "日常调节：下蹲时尽量避免快速直接起身，可以穿压缩长袜和腹带，来做好肢体的保暖工作适当加强运动。"
     },
         {
       "symp": "高血糖",
       "diet": "平时需要注意三餐七成饱，控制主食量，不吃甜食，多吃新鲜蔬菜，选择低糖水果食用，及时补充微量元素和膳食纤维，不吃高脂肪、高热量的食物，如动物内脏、油炸食品等，烹饪时尽量选择植物油。少喝或不喝稀饭，多喝白开水、苦丁茶、菊花茶。严禁抽烟喝酒，口袋常备糖果，防止低血糖。",
       "daily": "定期复查空腹、餐后血糖，随时调整药物。定期监测血糖，根据具体病情制定血糖监测计划，一般至少一个月监测一次全天血糖，坚持遵医嘱服用药物。",
       "sport": "经常锻炼，保证每周累计运动时间不少于150分钟，运动量不宜过大，餐后运动效果最好。"
     },
         {
       "symp": "低血糖",
       "diet": "多吃膳食纤维。在平时可以多吃一些可溶性膳食纤维的食物，并且尽量选用天然膳食纤维的蔬菜，包括芹菜，竹笋等。除此以外，木耳和蘑菇类的食物也比较适合低血糖的患者。如果想要提高血糖，那么就一定要给身体补充必须的营养，并且要采取少量多餐的饮食原则，同时食物的种类一定要保证多样化，平时可以适当的吃一些甜点类的小零食。保证营养的食物。在日常生活中一定要保证营养的均衡，可以多吃一些蔬菜，糙米，坚果，瘦肉，鱼肉，牛奶，鸡蛋等食物，除了给身体补充所需的多种维生素和纤维素以外，还需要补充一些优质的蛋白质，包括肉类，奶类等。",
       "daily": "如突发低血糖可使用5%~10%葡萄糖液迅速提升血糖。如因肾上腺皮质功能不全所致低血糖，可予以糖皮质激素如氢化可的松替代治疗。",
       "sport": "运动前检查血糖值。有些人习惯早起开始运动，这个时候一般都是空腹进行，在这种情况下就要先测量血糖，如果血糖偏低，那么就应该先吃一点小零食，这样才能避免出现低血糖。"
     },
         {
       "symp": "肥胖",
       "diet": "在控制总热量的基础上，坚持谷类为主的平衡膳食模式有利于减重；每日膳食应包括谷薯类、蔬菜水果、畜禽鱼蛋奶和豆类食物，减少油、盐、糖摄入。控制总热量、改变三大供能营养素配比和间歇性断食有助于短期减重，但长期效果缺乏证据支持。",
       "daily": "夜间饮食和不吃早餐增加肥胖风险，不利于减重。降低进食速度有利于恢复和保持健康体重。每天睡眠时间＜6h增加肥胖风险，＞7h有利于减重，特别是有利于预防腹型肥胖。长期持续的心理压力可增加肥胖风险，保持良好的心理和情绪有助于保持健康体重。",
       "sport": "有氧、抗阻、高强度间歇运动均可有效减重。维持中等强度运动＞250min/周可帮助减重，较高强度运动减重效果更明显。"
     },
           {
       "symp": "过瘦",
       "diet": "合理营养搭配。患者可以多吃脂肪类、高蛋白质类的食物，比如鱼肉、鸡蛋等。这些食物不仅可以给人体补充能量，增强体质，同时还可以起到增加体重的效果。可以多吃脂肪类、高蛋白质类的食物，比如鱼肉、鸡蛋等。这些食物不仅可以给人体补充能量，增强体质，同时还可以起到增加体重的效果。喝浓汤。可以喝猪蹄汤、排骨汤、鲤鱼汤等浓汤，可以使患者的体重逐渐增加。",
       "daily": "",
       "sport": ""
     },
           {
       "symp": "高血脂",
       "diet": "血脂高的患者平时要避免吃煎炸、烧烤的肉类食物，还有就是动物内脏以及各种高热量食物及高脂肪类食物。对于一些糖分比较高的食物也应避免，如各种糖果、蜜饯、蛋糕、冰淇淋等，同时不要喝酒和咖啡，烹调食物时尽量用植物油，烹调方式则建议选择蒸、煮、炖的方式。平时可多吃各种新鲜的蔬菜、水果及含高维生素高纤维素的食物。注意改变生活方式，像好吃肉类食物的患者，应改掉这种饮食习惯，可多吃冬瓜、菇类、花菜、猕猴桃、水果以及酸奶、海鱼等不饱和脂肪酸含量高的食物，以更好的控制血脂。",
       "daily": "应注意做好监测工作，可每三到六个月时间去医院复查一下血脂，以更好的了解血脂的控制水平。一旦发现血脂升高过快，应及时采取有效的措施防治，以避免引发各种并发症发生。血脂高的患者多数会有超重现象，所以在防治时应注意控制体重，以尽量达到标准体重。另，如果患者是在采用药物治疗的，在治疗期间也应定期检查心肌酶、肝功能、血脂及血常规等，以更好的了解治疗效果。",
       "sport": "平时应适当锻炼，合理参加一些适合自己的运动，如可定期参加有氧运动，包括羽毛球、登山，晚饭后可散散步，多走走。"
     },
           {
       "symp": "低血脂",
       "diet": "由于人体的碳水化合物、血脂以及蛋白质三大营养物质之间是一个平衡状态，如果营养物质摄入不足，容易导致血脂偏低。可以适量进食鱼、虾、大豆、猪肝、绿豆等食物，有利于补充人体所需要的营养，改善低血脂的情况；调整饮食可以降低血脂。有效的食物有:一，每天早上喝一碗燕麦粥，坚持7-8个礼拜。因为燕麦中含有丰富的可溶性及不溶性纤维，能在肠胃道中阻止胆固醇及脂肪的吸收。二，中午可以吃豆类食品，因为豆类中含有可溶性及不溶性纤维。三。可以吃大蒜和洋葱。四，每天可以吃1个苹果，因为苹果中含有丰富的果胶，有降低胆固醇的功效。五，还可以吃海带、玉米，山楂。",
       "daily": "如果有服用他汀类或其他降血脂类药物，也会出现低血脂，属于正常现象，一般不需要进行处理。如果血脂严重降低可以前往医院咨询医生是否需要停药。",
       "sport": ""
     },
           {
       "symp": "心率过快",
       "diet": "平时要多食高热量、富含维生素且易消化的食物，如瘦肉、鱼、蛋、奶制品、紫菜等。多吃新鲜蔬菜和水果，如芹菜、萝卜、菠菜、梨、葡萄、香蕉、柠檬、西红柿等。少食辛辣刺激性食物，如大蒜、姜、葱、辣椒、腌制品、咖喱等。少喝浓茶或咖啡及刺激性饮料等。如伴有心力衰竭，需注意减少钠盐和水的摄入量。",
       "daily": "避免过度劳累和长时间地紧张工作；注意气候变化，衣着要保暖，避免感冒；限酒戒烟；要保持良好的心情，消除思想顾虑，以平常的心态对待事情。避免情绪激动，保持心理平衡。",
       "sport": "平时应该避免剧烈的运动锻炼，过于剧烈的运动锻炼可能会使心率过快。"
     },
           {
       "symp": "心率过慢",
       "diet": "不要吃辛辣肥腻的食物，应该多吃蔬菜和水果，还要注意饮食调整。例如患者便秘持续时间比较长，而且大便成粗条状无腹痛症状，就被称为驰张性便秘，可以多吃一些维生素含量丰富的食物和蔬菜。如果便秘的情况间断发生，而且在精神紧张时加重，大便带有尿液，虽然有便意但难以排出，就被称为经常性便秘，需要多吃一些柔软容易消化的食物。日常生活中要注意多喝水，尤其是在夏天要补充体内的水分，每天至少喝三杯以上的水，大约1200毫升左右，加上饮食中的水分，每日摄水量不能低于2200毫升。如果本身就患有严重心力衰竭，那么每日饮水量就要在医生的指导下进行。",
       "daily": "在日常生活中就要特别注意情绪，不要随意的发脾气，如果情绪波动比较大，就会对身体造成一定的影响，还可能会使心率过缓的现象加重。除此之外，还要多注意休息，保持充足的睡眠时间，尤其不要熬夜。要改掉不良习惯，尤其不能抽烟酗酒，还应该保持心情愉悦；除此之外，在日常生活中不要从事过重的工作，否则会使心率过缓的症状更加严重。每天定时检查自己的心率和脉搏节律，如发现跳动不规律或过慢，应及时就医。心跳过慢时往往伴有乏力和气短，此时应立即停止运动，可泡服人参片或口服丹参片。如果头晕和乏力症状严重，且不见缓解，应立即就医。",
       "sport": "要注意劳逸结合，可以适当的做一些户外运动，这样能够增加肠胃蠕动力，还要按摩腹部，从而改善心率不缓的情况。"
     }
   ]
)
const checksymp=(symp)=>{
	if(symp==""){
		checkhealthinfo.value.symp="您的身体各项常规指标都很正常！！"
		checkhealthinfo.value.diet="请保持目前的健康饮食状态！(≧▽≦)"
		checkhealthinfo.value.daily="请保持目前的健康生活状态！祝您生活愉快(o^^o)♪"
		checkhealthinfo.value.sport="请保持目前的健康运动状态！适当运动！身体更加健康ヽ(^0^)ﾉ"
	}
}

const checkhealth = () => {
    drawer2.value = true;
    checkhealthinfo.value.symp=""
    checkhealthinfo.value.diet=""
    checkhealthinfo.value.daily=""
    checkhealthinfo.value.sport=""
    if (healthinfo.value.bloodFat > 5.18) {
        checkhealthinfo.value.symp+="高血脂  "
        checkhealthinfo.value.diet+=suggestions.value[6].diet
        checkhealthinfo.value.daily+=suggestions.value[6].daily
        checkhealthinfo.value.sport+=suggestions.value[6].sport
    }
    if (healthinfo.value.bloodSugar < 3.9) {
       checkhealthinfo.value.symp+="低血糖  "
       checkhealthinfo.value.diet+=suggestions.value[3].diet
       checkhealthinfo.value.daily+=suggestions.value[3].daily
       checkhealthinfo.value.sport+=suggestions.value[3].sport
    } else if (healthinfo.value.bloodSugar > 6.1) {

		
		checkhealthinfo.value.symp+="高血糖  "
		checkhealthinfo.value.diet+=suggestions.value[2].diet
		checkhealthinfo.value.daily+=suggestions.value[2].daily
		checkhealthinfo.value.sport+=suggestions.value[2].sport
    }


    if (healthinfo.value.bloodHighPressure < 90 || healthinfo.value.bloodLowPressure < 60) {
       checkhealthinfo.value.symp+="低血压  "
       checkhealthinfo.value.diet+=suggestions.value[1].diet
       checkhealthinfo.value.daily+=suggestions.value[1].daily
       checkhealthinfo.value.sport+=suggestions.value[1].sport
    } else if (healthinfo.value.bloodHighPressure > 140 && healthinfo.value.bloodLowPressure > 90) {
      checkhealthinfo.value.symp+="高血压  "
      checkhealthinfo.value.diet+=suggestions.value[0].diet
      checkhealthinfo.value.daily+=suggestions.value[0].daily
      checkhealthinfo.value.sport+=suggestions.value[0].sport
    }

    if (healthinfo.value.heartbeat < 60) {
       checkhealthinfo.value.symp+="心率过慢  "
       checkhealthinfo.value.diet+=suggestions.value[9].diet
       checkhealthinfo.value.daily+=suggestions.value[9].daily
       checkhealthinfo.value.sport+=suggestions.value[9].sport
    } else if (healthinfo.value.heartbeat > 100) {
        checkhealthinfo.value.symp+="心率过快  "
        checkhealthinfo.value.diet+=suggestions.value[8].diet
        checkhealthinfo.value.daily+=suggestions.value[8].daily
        checkhealthinfo.value.sport+=suggestions.value[8].sport
    }


    let bmi = healthinfo.value.weight / (healthinfo.value.height * healthinfo.value.height / 10000);
    if (bmi < 18.5) {
        checkhealthinfo.value.symp+="过瘦  "
        checkhealthinfo.value.diet+=suggestions.value[5].diet
        checkhealthinfo.value.daily+=suggestions.value[5].daily
        checkhealthinfo.value.sport+=suggestions.value[5].sport
    } else if (bmi > 24.9) {
        checkhealthinfo.value.symp+="过胖  "
        checkhealthinfo.value.diet+=suggestions.value[4].diet
        checkhealthinfo.value.daily+=suggestions.value[4].daily
        checkhealthinfo.value.sport+=suggestions.value[4].sport
    }
	checksymp(checkhealthinfo.value.symp)
}

const withdraw=()=>{
	window.location.reload();
}




const healthinfo=ref({
	height:null,
	weight:null,
	bloodLowPressure:null,
	bloodHighPressure:null,
	bloodSugar:null,
	bloodFat:null,
	heartbeat:null,
	temperature:null,
	medicalHistory:null,
	special:null,
})
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
	fetch(`http://101.35.20.149:8081/users/health/${userId}`, {
			    method: 'GET',
			    headers: {
			        'Content-Type': 'application/json'
			    }
			}).then(response => {
			    return response.json(); // 将响应转换为 JSON 格式
			}).then(data => {
			    healthinfo.value = data.data; // 追加新数据
				console.log(healthinfo.value);
			    
			}).catch(error => {
			    console.error('请求出错', error);
			});
	}
        
};





onMounted(() => {
  increaseHeartbeat();
  fetchData();
});
</script>

<style>	
.developer{
	text-align: center;
	color: #999;
	font-size: 20px;
}
.explain{
	text-align: right;
	color: #999;
	font-size: 15px;
}
.healthcontent{
	text-indent: 2em;
	font-weight: 50;
    font-size: 18px;
}
.healthsuggestion{
	text-align: left;
	font-size: 20px;
	font-weight: bold;
}
	.changehealth{
	   float: left;
	   margin-left: 15vw;
	   margin-top: 3vh;
	   width: 120px;
	   height: 35px;
	   font-size:18px;
	}
.healthtitle{
	font-size: 30px;
	font-weight: bold;
}
.healthtext{
	font-size: 25px;

}
.drawer{
	width: 330px;
	margin: 3vh auto;
}
	.checkhealth{
	   float: right;
	   margin-right: 15vw;
	   margin-top: 3vh;
	   width: 120px;
	   height: 35px;
	   font-size:18px;
	}
.board{
	margin: 3vh 4vw;
}
.healthmain{
	margin-top: 5vh;
	background-color: #FFDDAA;
}
.heartbeat{
	transform: rotate(-180deg);
}
.tempt{
	margin-top: 3vh;
}
.bloodleft{
	margin-top: 5vh;
	width:60vw;
	margin-left: 2vw;
}
.bloodright{
	margin-top: 3.5vh;
	width:60vw;
	margin-left: 34vw;
}
.custom-progress .el-progress-bar__inner {
    right: 0;
    left: auto;
}
.textleft {
    position: absolute;
    left: 10px; /* 调整文本距离进度条的左侧位置 */
    top: 50%; /* 让文本垂直居中 */
    transform: translateY(-50%); /* 通过 translateY 调整垂直居中 */
}
</style>