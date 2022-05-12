<!--

 富文本组件： vue-tinymce 配置教程

 1、 main.js 配置
   /* 富文本*/
   import tinymce from 'tinymce'
   import VueTinymce from '@packy-tang/vue-tinymce'
   Vue.prototype.$tinymce = tinymce;
   Vue.use(VueTinymce);
   /* 二次封装  */
   import vueTinymceEditor from '@/components/vue-tinymce/index';
   Vue.component('TinymceEditor', vueTinymceEditor);

 2、package.json 添加依赖及对应版本

    "tinymce": "^5.3.1",
    "@packy-tang/vue-tinymce": "^1.0.0",

 3、任意页面使用 (调用当前页面子组件)
    <TinymceEditor :content.sync="obj.content"/>


  注意：改插件不能点浏览器上的刷新，点了会改变网页路径，将导致找不到跟目录下相关引用文件，暂未找到处理方法
 -->

<template>
    <div id="vue-tinymce">
        <vue-tinymce
                v-model="editorVlaue"
                :setting="setting"/>
    </div>
</template>

<script>
    export default {
        name: 'vueTinymceEditor',
        data() {
            return {
                editorVlaue: this.content != null ? this.content : "",
                setting: {
                    menubar: false,
                    toolbar: "undo redo | fullscreen | formatselect alignleft aligncenter alignright alignjustify | link unlink | numlist bullist | image media table | fontselect fontsizeselect forecolor backcolor | bold italic underline strikethrough | indent outdent | superscript subscript | removeformat |",
                    toolbar_drawer: "sliding",
                    quickbars_selection_toolbar: "removeformat | bold italic underline strikethrough | fontsizeselect forecolor backcolor",
                    plugins: "link image media table lists fullscreen quickbars",
                    language: 'zh_CN',
                    height: 350
                }
            }
        },
        props: {
            // 接收值父组件传递值
            content: String
        }
        ,
        watch: {
            editorVlaue: function (newNum, oldNum) {
                // 修改调用者传入的值
                this.$emit('update:content', newNum)
            }
        }
    }
</script>

<style>
    /* 富文本按钮功能优先级,最好 > 2500  弹出默认2000+ */
    .tox-tinymce-aux {
        z-index: 9999 !important;
    }


</style>