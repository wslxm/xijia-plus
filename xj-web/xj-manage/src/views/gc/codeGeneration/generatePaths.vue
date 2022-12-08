<template>
    <div>
        <table width="100%" border="1px" style="border-collapse:collapse;"><!-- border-spacing:20px 40px;-->
            <tr class="pathsTr"  v-for="(value,key)  in obj">
                <td class="pathsTd">{{key}}</td>
                <td class="pathsTd">{{value}}</td>
            </tr>
        </table>
    </div>
</template>

<script>

    export default {
        // name: "RoleAdd",
        data() {
            return {
                // 当前数据
                obj: this.generatePaths,
            }
        },
        // 接收值父组件传递值
        props: {
            closeDialog: [],    // 关闭弹层方法
            generatePaths: [],
        },
        // 监听数据的变化,更新当前行数据
        watch: {
            generatePaths: function (newData, oldData) {
                this.obj = newData;
            }
        },
    }
</script>

<style>
    .pathsTr {
        height: 40px;
    }

    .pathsTd {
        border: 1.5px solid #dac5cc;
    }
</style>
