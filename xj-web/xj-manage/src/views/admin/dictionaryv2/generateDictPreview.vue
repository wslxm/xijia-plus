<template>
    <div>
        <el-tabs style="margin-top: -30px" v-model="activeName">
            <el-tab-pane label="java枚举类" name="java">
                <el-input type="textarea" rows="30" v-model="obj.java"></el-input>
            </el-tab-pane>
            <el-tab-pane label="js 缓存key " name="js">
                <el-input type="textarea" rows="30" v-model="obj.js"></el-input>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                // 当前数据
                obj: this.generateDictData,
                activeName: "java",
            }
        },
        // 接收值父组件传递值
        props: {
            closeDialog: [],    // 关闭弹层方法
            generateDictData: {},
        },
    }
</script>

<style>

</style>
