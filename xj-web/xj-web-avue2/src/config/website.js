/**
 * 全局配置文件
 */
export default {
    title: "",    // 登录页项目名
    logo: "A",
    key: 'avue',  // 配置主键,目前用于存储
    indexTitle: 'spring-boot-plus2',
    lockPage: '/lock',
    tokenTime: 60 * 60 * 24 * 15, //token过期时间(秒)
    Authorization: 'TOKEN',
    // http的status默认放行不才用统一处理的
    statusWhiteList: [400],
    // 标签是否可关闭
    isFirstPage: false,
    // 系统端及权限配置(子系统), 当Terminal != 1 时, 建议 isLoginUser=true, isTerminalSearch=false
    Terminal: 1,               // 终端 (1=主系统 2-扩展系统1) （接口参数中添加 isTerminal:true 进行动态扩展）
    isLoginUser: false,        // 只查询自己权限及以下的用户/角色/菜单数据 （接口参数中添加 isOwnData:true 进行动态扩展）
    isTerminalSearch: true,    // 用户/角色/菜单列表 是否开启终端搜索和编辑
    // 普通导航主题
    setting: {
        sidebar: 'vertical',    // 选项-- vertical -正常左菜单|  horizontal -左菜单在顶部横向展示
        tag: true,              // 开关--是否开启页面 tag
        debug: false,           // 开关-- dobug 功能项
        collapse: true,         // 开关--菜单折叠按钮
        search: true,           // 开关--搜索
        lock: true,             // 开关--锁屏
        screenshot: true,       // 开关--搜索
        fullscren: false,       // 开关--全屏
        menu: true,             // 开关--是否开启顶部菜单
        theme: true,            // 开关--主题
        color: true,            // 开关--主题色
        colorName: "#FF5E2C",      // 默认主题色 setting.colorName
        themeName: "theme-hey",    // 默认主题  setting.themeName
    },
    // 配置首页路径，只能配置静态路由,在登录页动态路由还没有进行注册,
    fistPage: {
        label: "首页",
        value: "/",
        params: {},
        query: {},
        meta: {
            i18n: 'dashboard'
        },
        close: false
    },
    //配置菜单的属性
    menu: {
        iconDefault: 'icon-caidan',
        label: 'name',
        path: 'url',
        icon: 'icon',
        children: 'menus',
        query: 'query',
        href: 'href',
        meta: 'meta'
    },
    /**
     * 分页参数 统一配置
     * pageSize = 默认调试
     * pagerCount = 显示几个页标
     * pageSizes = 切换页数选项值
     * @type
     */
    pageParams: {
        pageSize: 10,
        pagerCount: 5,
        pageSizes: [10, 15, 20],
    },
    /**
     * 数据列表基础配置 统一配置
     * @author wangsong
     * @mail  1720696548@qq.com
     * @date  2021/10/16 0016 10:14
     * @version 1.0.0
     */
    optionConfig: {
        card: true,              // 是否开启卡片模式
        searchMenuSpan: 2,       // 搜索按钮于搜索框的间隙
        border: true,            // 是否开启列分割线
        // align: 'center',      // 内容是否居中(默认左对齐)
        // menuAlign:'center',   // 菜单是否居中(开启了行编辑无效)
        index: true,             // 开启序号
        indexLabel: '序号',       // 序号命令
        emptyBtn: false,          // 是否开启列表搜索的清空按钮
        // align: 'center',
        // menuAlign: 'center',
        // searchIcon: true,
        searchShowBtn: true,     // 是否需要关闭搜索按钮
        refreshBtn: true,        // 是否需要刷新按钮
        columnBtn: false,        // 是否需求显隐列按钮
        // updateBtn: true,
        // saveBtn: false,        // 新增页是否需要添加按钮（自定义按钮无效）
        // cancelBtn: false,      // 新增页是否需要取消按钮（自定义按钮无效）
        // addRowBtn: false,      // 是否开启新增按钮
        cellBtn: false,           // 是否开启行编辑功能（可在指定页面重新设值,勿修改此处）
        addBtn: false,            // 是否需要新增数据按钮（自定义按钮无效）
        menu: true,               // 是否开启操作栏
        // menuWidth:380,         // 操作栏宽度
        editBtn: false,           // 操作栏是否需要编辑按钮(开启操作栏生效)
        delBtn: false,            // 操作栏是否需要删除按钮(开启操作栏生效，行编辑功能开启时不展示删除按钮)
    },
    /**
     * 左边树通用配置
     */
    treeOption: {
        elAsideWidth: "250px",     // 宽度
        //title:'标题',                // 新增编辑弹层标题
        //dialogWidth:'50%',           // 新增编辑弹层宽度
        filterText: "搜索关键字",     // 搜索提示
        defaultExpandAll: true,     // 是否展开节点
        filter: true,               // 是否显示搜索
        addBtn: false,              // 是否展示新增按钮
        editBtn: false,             // 是否展示删除按钮
        delBtn: false,              // 是否展示编辑按钮
        menu: false,                // 菜单开关
        // 字段配置(页面重配置)
        // this.website.treeOption.props.label = "name";
        // this.website.treeOption.props.children = "menus";
        props: {
            labelText: '标题',     // 添加弹窗标题
            label: 'name',        // 标题字段
            children: 'menus'     // 层级字段
        }
    },
    /**
     * 字典key, 根据后台数据字典配置更新即可
     */
    Dict: {
        // 系统枚举(固定值)
        Base: {
            Default: "DEFAULT",  // 默认字典(代码生成默认字典)
            Deleted: "DELETED",  // 逻辑删除
            Disable: "DISABLE",  // 是否禁用
            Gender: "GENDER",  // 性别
            AuthorityState: "AUTHORITY_STATE",  // 权限状态
            AuthorityType: "AUTHORITY_TYPE",  // 权限类型
            MenuRoot: "MENU_ROOT",  // 菜单级别
            BannerIsSkip: "BANNER_IS_SKIP",  // banner是否跳转
            IsRead: "IS_READ",  // 是否已读
            BlacklistType: "BLACKLIST_TYPE",  // 黑/白名单类型
            PayChannel: "PAY_CHANNEL",  // 支付渠道
            PayState: "PAY_STATE",  // 支付状态
            PayType: "PAY_TYPE",  // 支付类型
            WalletType: "WALLET_TYPE",  // 流水类型
            VueFieldType: "VUE_FIELD_TYPE",  // vue
            ConfigType: "CONFIG_TYPE",  // vue
        },
        // 系统枚举(动态值)
        Admin: {
            Terminal: "TERMINAL",  // 终端
            Position: "POSITION",  // 部门职位
            BannerPosition: "BANNER_POSITION",  // banner 位置
            MsgType: "MSG_TYPE",  // 及时消息类型
            MsgUserType: "MSG_USER_TYPE",  // 及时消息终端
            PayBusiness: "PAY_BUSINESS",  // 支付业务
        },
    }
}