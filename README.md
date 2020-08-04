# spring-boot-plus2

### 介绍

这是开发一个基于spring-boot + mybatis-plus+ druid+ mysql8 + layui + swagger2 的后台快速开发骨架，后台采用级简化代码，
所有基本操作无需写任何的dao，service层代码，包括(增删改查，及多添加/动态修改，多删除，分页/条件/排序查询,添加，修改，展示的html代码

展示地址: [http://xijia.plus/](http://xijia.plus/) 账号：admin  密码：admin

### 当前功能列表

- 1、菜单管理         
- 2、动态菜单    
- 3、菜单权限   
- 4、用户管理           
- 5、url权限         
- 6、字典功能                      
- 7、代码生成+预览   
- 8、使用swagger2 接口文档         地址：http://localhost/swagger-ui.html
- 9、阿里云oss
- 10、websocket 集成及在线聊天室
--更多
- 任务管理
- BUG消费


### 更新笔记-3 版本: v-0.0.1
--------------------------------2020-07-031
- 1、所有模块重新划分
- 2、代码生成器根据重新划分的模块进行优化
- 3、所有id字段修改为String
- 4、增加MD编辑器
- 5、增加websocket模块 + 前端->简易聊天室
- 6、增加阿里云oss文件处理
- 7、数据库增加所有表通用字段
- 8、增加任务管理+bug管理（仿禅道）
--------------------------------2020-08-03
- 9、请求日志优化 请求log 日志处理
- 10、接口名称统一 部分接口名称不统一 1、分页查询 --> findPage 2、查询使用 --> findList 3、id查询 --> findId 4、id编辑 --> upd 5、添加 --> insert 6 、id删除 --> del 7、多id删除 delByIds 
- 11、req 没有请求参数异常信息返回处理 优化请求错误返回信息
- 12、完善代码生成器，DTO 类根据数据库是否为空，大小生成对应的传递参数限制
- 13、分页查询排序按创建时间
- 14、用户md5密码加密
- 15、多环境可直接切换配置
- 16、修复权限扫描异常，修复id 删除异常
- 17、全局异常，请求错误优化返回，jsr303参数验证错误优化返回 2、通用返回类优化
--------------------------------2020-08-04
- 18、字典管理删除优化，删除父级时同时删除所有子层级实际 优化查询，无极限 优化添加，code 不重复 优化应该，code 不重复
- 19、jwt TOKEN 自动刷新优化，token到期会自动失效，任意操作后自动刷新token
- 20、点击第二页未携带第一页的查询条件优化

--------------------------------2020-08-04
- 21、logback 日志未在linux上生成日志文件优化
- 22、启动信息优化,启动信息不在放入启动类中
- 23、增加阿里云短信整合工具类 SmsUtil
- 24、增加字典表增加 findCodeGroup Code分组查询方法（带版本号+缓存控制,及大的减轻了服务器的压力）,使用字典来管理所有的动态字段/状态字段
- 25、字典优化，使用操作需手动刷新,层次错误处理
- 26、java按规范文件重命名

### 更新笔记-2 版本:无(mybatis-plus分支)  2020/4/16 14:23
 - 1、数据层已全部更换为 mybatis-plus，--- 新分支
 - 2、通用IService 编写完成
代处理：
- 1、后台分页数据变动，页面数据暂无法显示，登录功能已正常，其他代处理


### 更新笔记-1 版本:无(jpa分支) 2020/4/15
 - 1、放弃 jpa， jpa分支已放弃更新，目前基础功能一切正常运行，代码生成器功能正常，带内部数据暂未处理
 - 2、计划下版本使用mybatis-plus 3.3.1


### 软件架构
![输入图片说明](https://images.gitee.com/uploads/images/2019/1117/024447_b426895b_2208600.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1117/024625_4836e129_2208600.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1123/164312_6a8e6a2c_2208600.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1123/164615_5679f96f_2208600.png "屏幕截图.png")
service层通用查询方法，支持任意类各种条件查询，及分页，排序
![输入图片说明](https://images.gitee.com/uploads/images/2019/1123/164659_80e5ece
b_2208600.png "屏幕截图.png")




