// 环境
// development | production | test
const env = process.env;

// 请求地址 (本地, 部署请使用 nginx 转发)
let basePath = "127.0.0.1:9048";
// 转发代理地址
let proxyPath = '/api';
// websocket 接口地址
let webSocketPath = proxyPath + "/websocket/{userId}/{fullName}";
// 文件上传地址统一配置
let uploadPath = proxyPath + '/api/open/file/upload?resType=2&filePath=';

// 启动输出
console.log("当前 请求服务器地址: " + basePath);
console.log("当前 websocket请求地址: " + webSocketPath);
console.log("当前 转发代理地址: " + proxyPath);
console.log("当前 文件上传地址: " + uploadPath);

// 阿里图标地址配置(暂未使用)
let iconfontVersion = ['567566_82imxaft0by'];
let iconfontUrl = `//at.alicdn.com/t/font_$key.css`;

// 验证码
let codeUrl = `${proxyPath}/code`;

module.exports = {
    basePath,
    proxyPath,
    uploadPath,
    webSocketPath,
    iconfontUrl,
    iconfontVersion,
    codeUrl,
    env,
};
