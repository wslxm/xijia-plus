# spring-boot-plus2

QQ群：1037211892

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
- 当前主分支为v-0.1.1 的分支最后更新代码,可稳定运行
- 更新日志文档: [https://gitee.com/wslxm/spring-boot-plus2/blob/master/VERSION_LOG.md](https://gitee.com/wslxm/spring-boot-plus2/blob/master/VERSION_LOG.md) 
- 项目于0.0.9+模块化，模块化目的:减轻项目大小,按需加载

#### 更多

- 支持跨域,支持前后端代码抽离并独立开发(springboot（后端)/layui（前端）, 如有前端人员，也可使用其他开发语言来进行对接，如：vue.js
- swagger2 文档, 地址：http://localhost/swagger-ui.html


### 二、项目结构目录(代码结构未 v-0.0.8 - v-0.0.9)

#### 1、项目模块划分
v-0.0.9+ 版本后端代码目录结构(模块化后结构)
```base
1、模块
spring-boot-plus2
        └──xj-base
               └──xj-base-admin            核心包: 管理系统saever
               └──xj-base-admin-ui         核心包: 管理系统ui, 访问：ip:端口
               └──xj-base-pay              整合包: 聚合支付
               └──xj-base-core             核心包: 通用base类层+枚举字典
               └──xj-base-jwt              核心包: 登录认证
               └──xj-base-result           核心包: 统一返回 + 全局异常
        └──xj-server     
               └──xj-test-server           业务代码(个人功能开发/架构测试)
               └──xxx                      业务代码(开发具体项目业务代码模块)
        └──xj-third
               └──xj-api-aliyun-oss        阿里云oss集成(文件存储)
               └──xj-api-aliyun-sms        阿里云sms集成(短信)
               └──xj-api-baidu             百度api集成(图片/身份证/银行卡识别)
               └──xj-api-kuaidi-kuaidi100  快递100集成(查询物流)
               └──xj-api-kuaidi-sf         顺丰-丰桥(顺丰寄件)
               └──xj-api-qiniu-oss         七牛云oss集成(文件存储)
               └──xj-api-wx-app            微信小程序集成(小程序登录/小程序订阅消息)
               └──xj-api-wx-mp             微信公众号集成(公众号网页授权/公众号模块消息)
               └──xj-api-wx-pay            微信支付集成(支付/退款/打款)
               └──xj-api-sms1086           sms1086 短信服务集成   
               └──xj-doc-knife4j           接口文档ui集成(依赖swagger2：访问地址: ip/doc.html)
               └──xj-doc-swagger2          接口文档集成
               └──xj-doc-swagger2-ui       接口文档ui集成(依赖swagger2：访问地址: ip/swagger-ui.html)
               └──xj-fw-boot-admin         监控中心集成    
               └──xj-fw-redis              redis集成    
               └──xj-fw-websocket          websocket集成    
        └──xj-utils
               └──xj-util-core             核心工具(注意：使用了核心工具类非服务模块启动服务需禁数据源默认启用)
               └──xj-util-fastjson         fastjson工具
               └──xj-util-poi              excel 工具
               └──xj-util-qrcode           二维码 工具
               └──xi-util-geodesy          通过经纬度距离计算 工具(DistanceUtil)
```


#### 2、后端-核心代码目录结构
默认包：com.ws.ldy
下方默认所在模块(除指定模块)： xj-base-admin  
```base
spring-boot-plus2
            └──client  一、用户端 (目录结构同modules)
                 └──sys          系统功能用户端接口
                    └──xj        系统增强功能用户端接口

                 └──xxx xxx项目模块
                    └──user      示例：用户模块用户端接口
                    └──order     示例：订单模块用户端接口

            └──common  二、工具
                 └──function     Lambda8 获取字段名工具
                 └──cache        系统jvm缓存(字典/全局配置/接口信息缓存)
                 └──utils        util工具类(xj-utils 的子模块中，其中 xj-util-core 模块为核心工具类)
                 └──result       统一返回 (xj-base-result 模块中)

            └──config  三、系统配置
                 └──aspect       aop 接口入口(登录认证/权限/日志/黑名单/幂等拦截处理等)
                 └──datasource   数据源配置(mybatis-plus/druid)
                 └──datetime     统一时间处理 (yyyy-MM-dd HH:mm:ss)
                 └──datetime     请求响应时间格式处理
                 └──init         系统启动存放,初始化相关数据
                 └──mvc          mvc 配置
                 └──sing         接口验签核心代码
                 └──swagger      接口文档配置
                 └──auth         登录token生成，获取用户信息获取工具类JwtUser，aop调用 (xj-base-core 模块中)
                 └──error        全局异常,各种异常直接拦截进行解析返回 (xj-base-result 模块中)

            └──enums    四、常量/枚举(xj-base-core 模块中)
            └──modules  五、管理端 
                 └──sys          系统功能管理端接口
                    └──base      通用baseController/service/dto/vo等 (xj-base-core 模块中)
                    └──admin     基础核心功能(用户/角色/菜单/权限/字典)
                    └──xj        系统增强功能(banner/全局配置/黑名单/日志跟踪/消息)
                    └──gc        自值代码生成器(一键完成前后端的接口及页面，正常直接生成字典，单选，多选，图片上传),模板位置：resources/static/template
                    └──third     第三方工具集成 (xj-third 模块的子模块中)
                        
                        └──aliyun              当前系统默认使用 xj-api-aliyun-oss[阿里云oss]存储文件  
                        └──xj-doc-swagger2     当前系统默认使用swagger 为接口文档生成 
                        └──xj-doc-swagger2-ui  当前系统默认使用swagger-ui 为接口文档页面展示 
                        └──...                 更多
                 
                 └──xxx 业务模块大类(下方为子模块示例值参考)
                    └──user      
                    └──order   
            └──task     六、定时任务(下方为子模块示例值参考)
                 └──user    
                 └──order   
```

#### 3、前端-核心代码目录结构
代码所在模块： xj-base-admin-ui
```
  └──  java
       └── ....... -> PageController  页面路由统一跳转配置
  └── resources       
       └──static         一、静态资源 （在业务中需要定义额外资源时，在自己的业务模块定义对应目录并存放，不要放在 xj-base-admin-ui 模块，防止xj-base-admin-ui后续无法升级）   
            └──base          通用js/css/等静态资源
            └──client        用户相关端 js/css 
            └──modules       管理端相关 js/css
            └──components    组件

       └──templates      二、页面代码        
            └──base          1、通用页面
                └──error        统一错误页

            └──modules       2、管理端
                └──sys          管理系统功能代码存放模块
                    └──admin       基础核心功能(用户/角色/菜单/权限/字典)
                    └──xj          系统增强功能(banner/全局配置/黑名单/日志跟踪/消息)
                    └──gc          代码生成器功能
                └──xxx          xxx业务模块 
                    └──user        示例参考：用户模块(业务模块小类)
                    └──order       示例参考：订单模块

            └──client        3、用户端（请在业务模块创建该下方的代码，不要定义在xj-base-admin-ui，防止xj-base-admin-ui后续无法升级）
                └──xxx          xxx业务模块 
                    └──user        示例参考：用户模块(业务模块小类)
                    └──order       示例参考：订单模块
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



### 四、项目外观展示

![输入图片说明](https://images.gitee.com/uploads/images/2020/1206/114540_8a29dc40_2208600.png "屏幕截图.png")

更多自行访问展示地址



### 五、项目使用

- 1、拉取代码
- 2、创建数据库[spring-boot-plus2]
- 3、导入跟目录下的 sql 目录下对应的最新版本 sql, 注意主分支对应的版本，历史sql版本在old下，非sql/old下的sql,如 sql/file 如当前版本有使用到也一起导入到数据库
- 4、启动项目

注：最新分支的代码与当前项目测试库的是外网连接，拉取最新分支的最新代码在本地可直接使用当前项目的测试服数据库来启动，切勿修改测试库数据，否则到时测试地址无法使用，谢谢