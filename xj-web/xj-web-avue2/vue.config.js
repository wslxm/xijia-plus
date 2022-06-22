// 基础路径 注意发布之前要先修改这里
//const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin;

// 获取请求地址
const env = require('./src/config/env');
let targetPath = env.targetPath;

module.exports = {
    lintOnSave: true,
    productionSourceMap: false,
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
};