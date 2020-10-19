# spring-boot-plus2

### 介绍

这是开发一个基于spring-boot + mybatis-plus+ druid+ mysql8 + layui + swagger2 的后台快速开发骨架，后台采用级简化代码，
所有基本操作无需写任何的dao，service层代码，包括(增删改查，及多添加/动态修改，多删除，分页/条件/排序查询,添加，修改，展示的html代码

展示地址:
 [http://xijia.plus/](http://xijia.plus/) 
 
账号：10000  
密码：10000

### 最新说明
- 当前主分支为v-0.0.5 的分支最后更新代码,可稳定运行, 下个更新版本的版本 v-0.0.6 
- v-0.0.2 版本时增加 maven 版本标识
- v-0.0.3 + v-0.0.4 因代码变动代码生成器的html代码部分生成数据未处理
- v-0.0.4 各功能可正常运行,权限+角色数据全部修改为热更新-[禁用资源,需登录(状态),需授权(状态),禁用角色,分配用户角色,分配角色权限],已登录的正在使用的用户都将在用户的下一次有效操作中被刷新权限
- v-0.0.5 更新/优化第三方接口(微信openId获取,公众号消息推送，阿里云短信,快递100,顺丰寄送),增加了banner管理, 全局配置管理, Excel导出的ExcelUtil工具类,优化了websocket,阿里云oss等

详细更新内容文档: [https://gitee.com/wslxm/spring-boot-plus2/blob/master/VERSION_LOG.md](https://gitee.com/wslxm/spring-boot-plus2/blob/master/VERSION_LOG.md) 

### 当前功能列表

- 1、动态菜单 
- 2、用户管理           
- 3、url权限         
- 4、字典管理（完全可当枚举字典使用）                      
- 5、代码生成+预览   
- 6、使用swagger2 接口文档         地址：http://localhost/swagger-ui.html
- 7、阿里云oss文件管理
- 8、websocket 集成及在线聊天室
- 9、任务/BUG管理（防禅道）
- 10、支持跨域，支持前后端代码抽离并独立开发(springboot（后端） / layui（前端）), 如有前端人员，可使用其他开发语言，如：vue.js



## 项目展示
![菜单管理](https://images.gitee.com/uploads/images/2020/0824/003416_67f0845e_2208600.png "屏幕截图.png")
![用户管理](https://images.gitee.com/uploads/images/2020/0824/003649_366041fa_2208600.png "屏幕截图.png")
![字典管理](https://images.gitee.com/uploads/images/2020/0824/003721_804ff0cb_2208600.png "屏幕截图.png")
![接口管理](https://images.gitee.com/uploads/images/2020/0824/003832_7b34376d_2208600.png "屏幕截图.png")
![菜单权限分配](https://images.gitee.com/uploads/images/2020/0824/003803_c0a7e10e_2208600.png "屏幕截图.png")
![数据表,代码生成](https://images.gitee.com/uploads/images/2020/0824/003919_71e7253c_2208600.png "屏幕截图.png")
![代码生成预览](https://images.gitee.com/uploads/images/2020/0824/004742_e68409f5_2208600.png "屏幕截图.png")
![socket聊天测试](https://images.gitee.com/uploads/images/2020/0824/004826_f35a8b57_2208600.png "屏幕截图.png")
更多自行访问展示地址



