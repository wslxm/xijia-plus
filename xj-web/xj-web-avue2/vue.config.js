// 基础路径 注意发布之前要先修改这里
// const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin

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
                target: "http://127.0.0.1:9048/", //API服务器的地址
                //target: "http://xijia.plus/" //API服务器的地址
                ws: true,
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    },
    chainWebpack: (config) => {
        const entry = config.entry('app')
        entry
            .add('babel-polyfill')
            .end()
        entry
            .add('classlist-polyfill')
            .end()
        entry
            .add('@/mock')
            .end()
    },
    css: {
        extract: {ignoreOrder: true}
    }
}