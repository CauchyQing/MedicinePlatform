// main.js

import { createApp } from 'vue';
import App from './App.vue';
import router from './router/index';
import ElementPlus from 'element-plus';
import * as echarts from 'echarts';
import 'element-plus/dist/index.css';
import * as ElementPlusIconsVue from '@element-plus/icons-vue'; // 确保正确导入 Element Plus Icons Vue 库



export const app = createApp(App);

// 全局挂载 echarts
app.config.globalProperties.$echarts = echarts;
app.use(ElementPlus);

if (ElementPlusIconsVue) {
  for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component);
  }
}

app.use(router);
app.mount('#app');