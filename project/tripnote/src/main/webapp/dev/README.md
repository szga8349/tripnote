# 元数据资源管理系统

> wherehows

### 简介

   主要描述了在本系统根据业务需要如何通过本系统查找不同存储系统不同类型数据的路径等meta信息和血缘关系等元数据分析结果。
	
### 技术栈
```
基于 vue2 + vue-router + vuex + vue-resource + vue-i18n
 + ES6 + less 并使用webpack打包
```
### 使用项目
```
1.克隆项目：      git clone git@10.100.216.157:LEAP/Metadata.git
2.安装nodejs
3.安装依赖：      npm install
4.启动服务：      npm run dev
5.发布代码：      npm run build
6.单元测试：      npm run unit
7.e2e测试：		 npm run e2e
8.测试：          npm test
```

### 项目目录说明
```
.
|-- build                            // 项目构建(webpack)相关代码
|   |-- build.js                     // 生产环境构建代码
|   |-- dev-client.js                // 热重载相关
|   |-- dev-server.js                // 构建本地服务器
|   |-- utils.js                     // 构建工具相关
|   |-- webpack.base.conf.js         // webpack基础配置
|   |-- webpack.dev.conf.js          // webpack开发环境配置
|   |-- webpack.prod.conf.js         // webpack生产环境配置
|-- config                           // 项目开发环境配置
|   |-- dev.env.js                   // 开发环境变量
|   |-- index.js                     // 项目一些配置变量
|   |-- prod.env.js                  // 生产环境变量
|   |-- test.env.js                  // 测试环境变量
|-- dist							       //项目build后生成文件，文件内容需放置web app目录下
|   |-- common.js
|   |-- index.html
|   |-- static
|-- src                              // 源码目录
|   |-- components                   // 公共组件
|       |-- common                   // 页面内容公共组件
|       |-- MetaData                 // 元数据
|       |   |-- Dataset                 // 数据集
|       |   |-- Personal                // 我的数据
|       |-- DataMain               	// 主数据
|       |-- LifeCycle                // 生命周期
|       |-- DataQuality              // 数据质量
|       |-- DataStandard             // 数据标准
|       |-- Security                 // 安全隐私
|       |-- Monitor                  // 监控
|       |-- MainNav.vue              //左侧总导航
|       |-- MainPage.vue             //页面渲染
|       |-- Header.vue               //页头组件
|       |-- Footer.vue               //页脚组件
|   |-- assets                       //静态文件，比如一些图片,第三方库插件
|       |-- css                   	//公用样式
|       |-- images                  //图片资源
|       |-- less                    //less样式文件
|       |-- libs                    // 第三方插件
|   |-- directives                   // 自定义指令
|   |-- locale                   		// 国际化文档
|   |-- mixins                   		// 组件混合的方法
|   |-- locale                   		// 国际化文档
|   |-- plugins                     // 自定义插件
|   |-- utils                   		// 实用工具
|   |-- mixins                   		// 组件混合的方法
|   |-- locale                   		// 国际化文档
|   |-- router                   		// 项目路由文件
|   |-- store                       // vuex的状态管理
|   |   |-- index.js   
|   |   |-- action.js    
|   |   |-- mutation.js     
|   |   |-- mutation-types.js    
|   |-- App.vue                       // 页面入口文件
|   |-- main.js                       // 程序入口文件   
|-- .babelrc                         // ES6语法编译配置
|-- .editorconfig                    // 定义代码格式
|-- .eslintrcignore   
|-- .eslintrc.js                      
|-- .gitignore                       // git上传需要忽略的文件格式
|-- README.md                        // 项目说明
|-- index.html                       // 入口页面
|-- package.json                     // 项目基本信息
.
```
