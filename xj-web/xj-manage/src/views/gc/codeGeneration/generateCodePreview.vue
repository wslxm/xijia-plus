<template>
    <div>
        <el-tabs style="margin-top: -30px" v-model="activeName">
            <el-tab-pane v-for="(value,key) in obj" :label="key" :name="key">
                <iframe class="iframe" :src='proxyPath+value'></iframe>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
    import {proxyPath} from '@/config/env';

    export default {
        // name: "RoleAdd",
        data() {
            return {
                // 当前数据
                obj: this.generateCodePreviews,
                activeName: "X-Entity",
                // 走代理访问生成的文件
                proxyPath: proxyPath + "/",
            }
        },
        // 接收值父组件传递值
        props: {
            closeDialog: [],    // 关闭弹层方法
            generateCodePreviews: [],
        },
        // 监听数据的变化,更新当前行数据
        watch: {
            generateCodePreviews: function (newData, oldData) {
                this.obj = newData;
            }
        },
    }
</script>

<style>
    .iframe {
        width: 100%;
        height: 600px;
        margin-top: 1%
    }
</style>
