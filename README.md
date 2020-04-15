# spring-boot-plus2

#### 介绍
这是开发一个基于spring-boot + mybatis-plus(原jpa分支) + druid+ mysql8 + layui + swagger2 的后台快速开发骨架，后台采用级简化代码，
所有基本操作无需写任何的dao，service层代码，包括(增删改查，及多添加/动态修改，多删除，分页/条件/排序查询)

#### 当前功能列表

- 1、通用dao，service 已完成
- 2、菜单管理         已完成
- 3、动态菜单         已完成 （分左侧菜单和上菜单）
- 4、菜单权限         已完成（菜单切换上级目录未完成） 
- 5、用户管理         已完成         
- 6、url权限         已完成（基于aop +注解实现，前台提供按钮自动扫描权限接口）
- 7、组织机构         代开发  
- 8、字典功能         已完成                
- 9、代码生成？预览    已完成
- 10、验签（只针对 /public 接口），xss攻击防御（只针对string字符串参数），URL特殊字符转译，防盗链, 允许跨域，
- 11、接口日志               已完成 （log4j，每天产生一个新的日志文件，按日志级别）
- 12、通用查询方法fingPage    已完成 （针对fingPage方法提供了QueryCriteria类提供添加搜索条件Api，具体使用可参考TestController）
- 13、使用swagger2 接口文档         地址：http://localhost/swagger-ui.html

#### 更新笔记-1 2020/4/15
 1、放弃 jpa， jpa分支已放弃更新，目前基础功能一切正常运行，代码生成器功能正常，带内部数据暂未处理
 - 计划： 使用mybatis-plus 3.3.1






#### 软件架构
![输入图片说明](https://images.gitee.com/uploads/images/2019/1117/024447_b426895b_2208600.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1117/024625_4836e129_2208600.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1123/164312_6a8e6a2c_2208600.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/1123/164615_5679f96f_2208600.png "屏幕截图.png")
service层通用查询方法，支持任意类各种条件查询，及分页，排序
![输入图片说明](https://images.gitee.com/uploads/images/2019/1123/164659_80e5ece
b_2208600.png "屏幕截图.png")




