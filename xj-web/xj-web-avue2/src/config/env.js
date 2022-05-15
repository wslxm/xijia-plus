

// 请求地址
let targetPath = ""

// 配置编译环境和线上环境之间的切换
const env = process.env;
if (env.NODE_ENV == 'development') {
    // 开发环境地址
    targetPath = `127.0.0.1:9048`;
} else if (env.NODE_ENV == 'production') {
    // 生产环境地址
    targetPath = `xijia.plus`;
} else if (env.NODE_ENV == 'test') {
    // 测试环境地址
    // baseUrl = ``;
}


// 使用代理模式, 使用 /api 进行转发
let baseProxyPathRewrite = '/api';
// 文件上传地址统一配置
let baseUploadUrl = baseProxyPathRewrite + '/api/open/aliOssFile/upload?resType=2&filePath=';
// websocket 地址统一配置
let baseWebSocketUrl = "ws://" + targetPath + "/websocket/{userId}/{fullName}/{head}";

// 阿里图标地址配置
let iconfontVersion = ['567566_82imxaft0by'];
let iconfontUrl = `//at.alicdn.com/t/font_$key.css`;
// 未知
let codeUrl = `${baseProxyPathRewrite}/code`;

export {
    targetPath,
    baseProxyPathRewrite,
    baseUploadUrl,
    baseWebSocketUrl,
    iconfontUrl,
    iconfontVersion,
    codeUrl,
    env,
}
