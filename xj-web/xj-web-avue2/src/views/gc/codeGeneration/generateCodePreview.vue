<template>
    <div>
        <el-tabs style="margin-top: -30px" v-model="activeName">
            <el-tab-pane label="entity" name="entity">
                <iframe class="iframe" :src='obj.entity'></iframe>
            </el-tab-pane>
            <el-tab-pane label="DTO" name="DTO">
                <iframe class="iframe" :src='obj.DTO'></iframe>
            </el-tab-pane>
            <el-tab-pane label="VO" name="VO">
                <iframe class="iframe" :src='obj.VO'></iframe>
            </el-tab-pane>
            <el-tab-pane label="Query" name="Query">
                <iframe class="iframe" :src='obj.Query'></iframe>
            </el-tab-pane>
            <el-tab-pane label="controller" name="controller">
                <iframe class="iframe" :src='obj.controller'></iframe>
            </el-tab-pane>
            <el-tab-pane label="service" name="service">
                <iframe class="iframe" :src='obj.service'></iframe>
            </el-tab-pane>
            <el-tab-pane label="serviceImpl" name="serviceImpl">
                <iframe class="iframe" :src='obj.serviceImpl'></iframe>
            </el-tab-pane>
            <el-tab-pane label="mapper" name="mapper">
                <iframe class="iframe" :src='obj.mapper'></iframe>
            </el-tab-pane>
            <el-tab-pane label="mapperXml" name="mapperXml">
                <iframe class="iframe" :src='obj.mapperXml'></iframe>
            </el-tab-pane>
            <el-tab-pane label="layui表格页" name="main">
                <iframe class="iframe" :src='obj.main'></iframe>
            </el-tab-pane>
            <el-tab-pane label="layui添加页" name="mainAdd">
                <iframe class="iframe" :src='obj.mainAdd'></iframe>
            </el-tab-pane>
            <el-tab-pane label="layui编辑页" name="mainUpd">
                <iframe class="iframe" :src='obj.mainUpd'></iframe>
            </el-tab-pane>
            <el-tab-pane label="vue表格页" name="vueMain">
                <iframe class="iframe" :src='obj.vueMain'></iframe>
            </el-tab-pane>
            <el-tab-pane label="vue添加页" name="vueMainAdd">
                <iframe class="iframe" :src='obj.vueMainAdd'></iframe>
            </el-tab-pane>
            <el-tab-pane label="vue编辑页" name="vueMainUpd">
                <iframe class="iframe" :src='obj.vueMainUpd'></iframe>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>

    export default {
        // name: "RoleAdd",
        data() {
            return {
                // 当前数据
                obj: this.generateCodePreviews,
                activeName: "entity"
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
