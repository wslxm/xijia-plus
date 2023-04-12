// 基础路径 注意发布之前要先修改这里
//const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin;

// 获取请求地址
const env = require('./src/config/env');
let basePath = env.basePath;

module.exports = {
    //关闭eslint检查（语法检查）
    lintOnSave: true,
    productionSourceMap: false,
    devServer: {
        open: true,       // 是否自动弹出浏览器页面
        // https: false,  // 是否使用https协议
        // hotOnly: true, // 是否开启热更新
        port: 9000,
        proxy: {
            // 请求请求代理
            '/api': {
                //API服务器的地址
                target: "http://" + basePath + "/",
                ws: true,
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            },
        },
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