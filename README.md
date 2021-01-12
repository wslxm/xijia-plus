# spring-boot-plus2

QQ群：

### 一、项目简述

项目宗旨：只为急速开发而生, 主要针对于中小型的项目快速开发

这是开发一个基于spring-boot + mybatis-plus+ druid+ mysql8 + layui + swagger2 的后台快速开发骨架，后台采用级简化代码，
所有基本操作无需写任何的dao，service层代码，包括(增删改查，及多添加/动态修改，多删除，分页/条件/排序查询,添加，修改，展示的html代码


#### 展示地址
[http://xijia.plus/](http://xijia.plus/) 

账号：10000  
密码：10000

#### 文档地址：
 [http://xijia.plus/help](http://xijia.plus/help) 

#### 最新说明
- 当前主分支为v-0.0.7 的分支最后更新代码,可稳定运行
- 更新文档: [https://gitee.com/wslxm/spring-boot-plus2/blob/master/VERSION_LOG.md](https://gitee.com/wslxm/spring-boot-plus2/blob/master/VERSION_LOG.md) 

#### 更多

- 支持跨域,支持前后端代码抽离并独立开发(springboot（后端)/layui（前端）, 如有前端人员，也可使用其他开发语言来进行对接，如：vue.js
- swagger2 文档, 地址：http://localhost/swagger-ui.html


### 二、项目结构目录(后端代码)

```base

pets
   └──client  用户端接口 (目录结构同 modules)
   └──common  工具
        └──function     Lambda8 获取字段名工具
        └──result       统一返回
        └──utils        util工具类
   └──config  系统配置
        └──aspect       aop 接口入口(登录认证/权限/日志/黑名单/幂等拦截处理等)
        └──auth         jwt登录（获取用户信息工具类 / 登录处理核心逻辑，aop调用） 
        └──datasource   数据源配置(mybatis-plus/druid)
        └──datetime     统一时间处理 (yyyy-MM-dd HH:mm:ss)
        └──error        全局异常
        └──idempotent   幂等
        └──init         系统启动存放,初始化相关数据
        └──mvc          mvc 配置
        └──swagger      接口文档配置
   └──enums   常量/枚举
   └──modules
        └──business     业务模块
               └──pets         宠物核心逻辑
               └──statistics   宠物统计相关
        └──sys          系统模块
               └──admin        系统核心功能
               └──base         系统核心通用层
               └──gc           代码生成器
               └──pay          支付封装
               └──xj           系统增强功能
        └──third        第三方服务
               └──aliyun       阿里云API
                       └──oss           阿里云对象存储(文件)
                       └──sms           阿里云短信
               └──baidu        百度api
            
               └──kuaidi       快递
                       └──kuaidi100     快递100查询物流
                       └──sf            顺丰寄件
               └──qiniu        七牛云oss
               └──websocket    及时通知/聊天(注意与xjAdminMsg及时通知表有关联)
               └──wechat       微信API
                       └──app           小程序登录 
                       └──mq            公众号(网页授权/模板信息推送)
                       └──pay           微信支付
   └──task     定时任务

```


### 三、当前功能列表
#### 基础功能
- 1、用户管理
- 2、角色管理
- 3、菜单管理
- 4、资源url管理
- 5、动态用户/菜单/角色/资源权限管理 
- 6、权限管理
- 7、字典管理（完全可当枚举字典使用）


#### 增强功能
- 1、代码生成/代码生成在线预览   
- 2、操作记录，请求响应完整记录请求响应数据
- 3、黑名单管理, 一键拉黑非法请求
- 4、banner 管理, 快速进行运营推广
- 5、全局配置，让页面数据动起来，不用因为修改页面内容而去改代码的难题
- 6、帮助中心, 提供文档添加，查看功能

#### 第三方API功能
- 1、集成 springBootAdmn 系统监控
- 2、集成 knife4j 接口文档,可生成离线html，word, pdf 文档， 服务地址：http://localhost/doc.html，
- 3、集成 swagger2 接口文档,地址：http://localhost/swagger-ui.html
- 4、阿里云oss文件管理
- 5、阿里云sms短信服务
- 6、快递100 快递 查询api对接
- 7、顺丰快递 寄件api对接
- 8、websocket 及时通知，和简易在线聊天室
- 9、微信小程序登录
- 10、微信公众号模板消息推送
- 11、微信网页授权
- 12、七牛云oss文件管理
- 13、接入 markdown 编辑器editor，并实现cv上传图片，满足你的文本编辑需求



#### 四、项目外观展示

![输入图片说明](https://images.gitee.com/uploads/images/2020/1206/114540_8a29dc40_2208600.png "屏幕截图.png")

更多自行访问展示地址





