# spring-boot-plus2

#### 介绍
这是开发一个基于spring-boot + mybatis-plus(原jpa分支) + druid+ mysql8 + layui + swagger2 的后台快速开发骨架，后台采用级简化代码，
所有基本操作无需写任何的dao，service层代码，包括(增删改查，及多添加/动态修改，多删除，分页/条件/排序查询,添加，修改，展示的html代码)


#### 当前功能列表

- 1、菜单管理         
- 2、动态菜单    
- 3、菜单权限   
- 4、用户管理           
- 5、url权限         
- 6、字典功能                      
- 7、代码生成+预览   
- 8、使用swagger2 接口文档         地址：http://localhost/swagger-ui.html

--更多

- 任务管理
- BUG消费
- 验签


#### 更新笔记-3 版本:0.0.1 2020/7/31 14:23
- 1、所有模块重新划分
- 2、代码生成器根据重新划分的模块进行优化
- 3、所有id字段修改为String
- 4、增加MD编辑器
- 5、增加websocket模块 + 前端->简易聊天室
- 6、增加阿里云oss文件处理
- 7、数据库增加所有表通用字段
- 8、增加任务管理+bug管理（仿禅道）


#### 更新笔记-2 版本:无(mybatis-plus分支)  2020/4/16 14:23
 - 1、数据层已全部更换为 mybatis-plus，--- 新分支
 - 2、通用IService 编写完成
代处理：
- 1、后台分页数据变动，页面数据暂无法显示，登录功能已正常，其他代处理


#### 更新笔记-1 版本:无(jpa分支) 2020/4/15
 - 1、放弃 jpa， jpa分支已放弃更新，目前基础功能一切正常运行，代码生成器功能正常，带内部数据暂未处理
 - 2、计划下版本使用mybatis-plus 3.3.1


#### 软件架构
![输入图片说明](https://images.gitee.com/uploads/images/2019/1117/024447_b426895b_2208600.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1117/024625_4836e129_2208600.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1123/164312_6a8e6a2c_2208600.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1123/164615_5679f96f_2208600.png "屏幕截图.png")
service层通用查询方法，支持任意类各种条件查询，及分页，排序
![输入图片说明](https://images.gitee.com/uploads/images/2019/1123/164659_80e5ece
b_2208600.png "屏幕截图.png")




