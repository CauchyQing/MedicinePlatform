import {createRouter,createWebHashHistory} from "vue-router"
const router = createRouter({
  //内部提供了history模式的实现。为了简单起见，我们在这里使用hash模式。
  history: createWebHashHistory(),
  routes:[
	 
	  {name:"homepage",path:"/",component:()=>import("../views/homepage.vue")},
	  {name:"loginpage",path:"/login",component:()=>import("../views/loginpage.vue")},
      {name:"singuppage",path:"/singup",component:()=>import("../views/singuppage.vue")},
	  {name:"mypage",path:"/my",component:()=>import("../components/my/mypage.vue")},
	
  ]
})
export default router

