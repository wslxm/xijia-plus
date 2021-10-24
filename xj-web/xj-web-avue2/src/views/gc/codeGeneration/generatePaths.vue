<template>
    <div>
        <table width="100%" border="1px" style="border-collapse:collapse;"><!-- border-spacing:20px 40px;-->
            <tr class="pathsTr" >
                <td class="pathsTd">layui 访问路径</td>
                <td class="pathsTd">{{obj.index}}</td>
            </tr>
            <tr class="pathsTr">
                <td class="pathsTd">vue 访问路径</td>
                <td class="pathsTd">{{obj.vueIndex}}</td>
            </tr>
            <tr class="pathsTr">
                <td class="pathsTd">entity</td>
                <td class="pathsTd">{{obj.entity}}</td>
            </tr>
            <tr class="pathsTr">
                <td class="pathsTd">vo</td>
                <td class="pathsTd">{{obj.vo}}</td>
            </tr>
            <tr class="pathsTr">
                <td class="pathsTd">dto</td>
                <td class="pathsTd">{{obj.vo}}</td>
            </tr>
            <tr class="pathsTr">
                <td class="pathsTd">query</td>
                <td class="pathsTd">{{obj.query}}</td>
            </tr>
            <tr class="pathsTr">
                <td class="pathsTd">controller</td>
                <td class="pathsTd">{{obj.controller}}</td>
            </tr>
            <tr class="pathsTr">
                <td class="pathsTd">service</td>
                <td class="pathsTd">{{obj.service}}</td>
            </tr>
            <tr class="pathsTr">
                <td class="pathsTd">serviceImpl</td>
                <td class="pathsTd">{{obj.serviceImpl}}</td>
            </tr>
            <tr class="pathsTr">
                <td class="pathsTd">mapper</td>
                <td class="pathsTd">{{obj.mapper}}</td>
            </tr>
            <tr class="pathsTr">
                <td class="pathsTd">mapperXml</td>
                <td class="pathsTd">{{obj.mapperXml}}</td>
            </tr>
            <tr class="pathsTr">
                <td class="pathsTd">htmlMain</td>
                <td class="pathsTd">{{obj.htmlMain}}</td>
            </tr>
            <tr class="pathsTr">
                <td class="pathsTd">htmlAdd</td>
                <td class="pathsTd">{{obj.htmlAdd}}</td>
            </tr>
            <tr class="pathsTr">
                <td class="pathsTd">htmlUpd</td>
                <td class="pathsTd">{{obj.htmlUpd}}</td>
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
