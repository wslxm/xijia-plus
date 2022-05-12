<!--

部分参考文档： https://blog.csdn.net/weixin_42623929/article/details/122864736
             https://blog.csdn.net/longzhoufeng/article/details/107555005
-->

<template>
    <quill-editor
            :id="randomId(3)"
            v-model="content"
            :ref="editorRef"
            :options="editorOption"
            @focus="onEditorFocus($event)"
            @blur="onEditorBlur($event)"
            @change="onEditorChange($event)"
            class="editor"
    />
</template>
<script>

    /* 图片处理, 弹窗层使用有问题，后面在说 */
    // import Quill from 'quill';
    // import {ImageResize} from 'quill-image-resize-module'  // 调整大小组件。
    // import {ImageDrop} from 'quill-image-drop-module';  // 拖动加载图片组件。
    // Quill.register('modules/imageResize', ImageResize);
    // Quill.register('modules/imageDrop', ImageDrop);


    const toolbarOptions = [
        ['bold', 'italic', 'underline', 'strike'], //加粗，斜体，下划线，删除线
        ['blockquote', 'code-block'], //引用，代码块
        [{header: 1}, {header: 2}], // 标题，键值对的形式；1、2表示字体大小
        [{list: 'ordered'}, {list: 'bullet'}], //列表
        [{script: 'sub'}, {script: 'super'}], // 上下标
        [{indent: '-1'}, {indent: '+1'}], // 缩进
        [{direction: 'rtl'}], // 文本方向
        [{header: [1, 2, 3, 4, 5, 6, false]}], //几级标题
        [{color: []}, {background: []}], // 字体颜色，字体背景颜色
        [{font: []}], //字体
        [{align: []}], //对齐方式
        ['clean'], //清除字体样式
        ['image', 'video'] //上传图片、上传视频
    ];

    export default {
        name: 'xjQuillEditor',
        props: {
            // 编辑器的内容
            editorContent: {
                type: String,
                required: false
            },
            editorRef: {
                type: String,
                required: true
            }
        },
        data() {
            return {
                content: this.editorContent,
                editorOption: {
                    theme: 'snow',
                    modules: {
                        toolbar: toolbarOptions,
                        // 就是这个，配置图片缩放
                        // imageResize: {
                        //     handleStyles: {
                        //         backgroundColor: 'black',
                        //         border: 'none',
                        //         borderRadius: '6px'
                        //         // other camelCase styles for resize handles
                        //     },
                        //     displaySize: true,
                        //     displayStyles: {
                        //         backgroundColor: 'black',
                        //         border: 'none',
                        //         // color: white
                        //         // other camelCase styles for size display
                        //     }
                        // }

                        //   handlers: {
                        //     image: function(value) {
                        //       if (value) {
                        //         // 调用iview图片上传
                        //         console.log(123)
                        //         document.querySelector('.ivu-upload .ivu-btn').click()
                        //       } else {
                        //         this.quill.format('image', false)
                        //       }
                        //     }
                        //   }
                    }
                }
            }
        },
        computed: {
            //当前富文本实例
            editor() {
                return this.$refs.editorRef.quillEditor
            }
        },
        watch: {
            editorContent() {
                this.content = this.editorContent
            }
        },
        methods: {
            randomId(len) {
                var chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678'
                var tempLen = chars.length,
                    tempStr = '';
                for (var i = 0; i < len; ++i) {
                    tempStr += chars.charAt(Math.floor(Math.random() * tempLen))
                }
                return tempStr
            },
            // 准备富文本编辑器
            onEditorReady() {
            },
            // 富文本编辑器 失去焦点事件
            onEditorBlur() {
            },
            // 富文本编辑器 获得焦点事件
            onEditorFocus() {
            },
            // 富文本编辑器 内容改变事件
            onEditorChange({html}) {
                //内容改变事件
                // console.log('内容改变事件');
                this.$emit('input', this.content)
            }
        }
    }
</script>


<style>

</style>

