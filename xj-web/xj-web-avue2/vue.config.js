// 基础路径 注意发布之前要先修改这里
// const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin


// 请求地址
let targetPath = "xijia.plus";

// 配置编译环境和线上环境之间的切换
const env = process.env;
if (env.NODE_ENV == 'development') {
    // 开发环境地址
    targetPath = `127.0.0.1:9048`;
    console.log("开发环境......" + targetPath)
} else if (env.NODE_ENV == 'production') {
    // 生产环境地址
    targetPath = `xijia.plus`;
    console.log("生产环境......" + targetPath)
} else if (env.NODE_ENV == 'test') {
    // 测试环境地址
    // baseUrl = ``;
    console.log("测试环境......" + targetPath)
}

module.exports = {
    lintOnSave: true,
    productionSourceMap: false,
    // configureWebpack: config => {
    //     if (process.env.NODE_ENV === 'production') {
    //         return {
    //             plugins: [
    //                 new BundleAnalyzerPlugin()
    //             ]
    //         }
    //     }
    // },
    devServer: {
        open: true,       // 是否自动弹出浏览器页面
        // https: false,  // 是否使用https协议
        // hotOnly: true, // 是否开启热更新
        port: 9000,
        proxy: {
            '/api': {
                //API服务器的地址
                target: "http://" + targetPath + "/",
                ws: true,
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    },
    chainWebpack: (config) => {
        const entry = config.entry('app');
        entry
            .add('babel-polyfill')
            .end();
        entry
            .add('classlist-polyfill')
            .end()
        // entry
        //     .add('@/mock')
        //     .end()
    },
    css: {
        extract: {ignoreOrder: true}
    }
}