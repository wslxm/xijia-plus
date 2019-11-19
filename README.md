# spring-boot-plus2

#### 介绍
励志开发一个基于spring-boot +layui 的后台快速开发骨架，后台采用级简化代码，
所有基本操作无需写任何的dao，service层代码，包括(增删改查，及多添加/动态修改，多删除，分页/条件/排序查询)
所有基本操作后续将会添加代码生成功能


目标：
1、通用dao，service  条件查询待处理，等暂未用到的通用方法，多删除目前采用for，id删除，代优）   --> 已完成 80%
2、菜单功能          树菜单数据列表，并可操作添加/删除/修改菜单,父级变更未功能处理             --> 已完成 90%
3、动态菜单，        顶部系统级菜单/左侧导航菜单                                            --> 已完成 100%
4、菜单权限          根据用户/角色/菜单表的关系只展示相关权限的菜单                           --> 已完成 100%
5、用户模板          登录拦截/密码修改/角色管理/角色用户分配                    
6、url权限           代开发
7、组织机构          代开发  
8、字典功能          代开发  
9、代码生成          代开发  
10、admin项目专用静态缓存         代开发 


#### 项目结构
项目采用模块化开发，分为 
admin-core，      负责通用方法编写，如BaseDao，BaseService，通用静态资源文件存放，springboot 核心配置
admin-console，   负责后台管理功能开发，如：菜单/用户/代码生成等等 
 
分布式部署:模块化开发，需自定义请求根目录，admin-core/app.js 中定义，所有url请求添加根目录地址，Nginx 根据根目录地址区分部署的项目
单项目部署：建议也定义地址根目录，置空即可，有需要分布式部署，直接修改根目录，及yml 配置文件 项目更路径即可


#### 软件架构
![输入图片说明](https://images.gitee.com/uploads/images/2019/1117/024447_b426895b_2208600.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1117/024625_4836e129_2208600.png "屏幕截图.png")

#### 安装教程

1.  xxxx
2.  xxxx
3.  xxxx

#### 使用说明

1.  xxxx
2.  xxxx
3.  xxxx

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 码云特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5.  码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
