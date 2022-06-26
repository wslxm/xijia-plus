<!--
详见： https://blog.csdn.net/qq_41463655/article/details/124768421
 -->

<template>
    <div id="vue-tinymce">
        <vue-tinymce
                v-model="editorValue"
                :setting="setting"/>
    </div>
</template>

<script>
    import {baseUploadUrl} from "@/config/env";

    export default {
        name: 'vueTinymceEditor',
        data() {
            return {
                editorValue: this.content != null ? this.content : "",
                setting: {
                    menubar: true,  // 菜单栏
                    //inline: true,   //开启内联模式
                    readonly: false, // 只读
                    toolbar:
                        "undo redo | fullscreen | formatselect alignleft aligncenter alignright alignjustify | link unlink | numlist bullist | image media table emoticons | fontselect fontsizeselect forecolor backcolor | bold italic underline strikethrough | indent outdent | superscript subscript | removeformat | wordcount code",
                    toolbar_drawer: "sliding",
                    quickbars_selection_toolbar: "removeformat | bold italic underline strikethrough | fontsizeselect forecolor backcolor",
                    plugins: "link image media table lists fullscreen quickbars wordcount paste emoticons code",
                    language: 'zh_CN',
                    height: 350,
                    // 图片上传地址(自动上传)
                    // images_upload_url: baseUploadUrl + 'image/vueTinymce/',
                    // 允许粘贴图片上传,及格式
                    paste_data_images: true,
                    images_file_types: 'jpeg,jpg,png,gif,bmp,webp',
                    // 此处为图片上传处理函数 (手动上传)
                    images_upload_handler: (blobInfo, success, failure, progress) => {
                        console.log('上传处理器：');
                        // 方法1：用base64的图片形式上传图片
                        // const img = 'data:image/jpeg;base64,' + blobInfo.base64()
                        // success(img)

                        // 方法2：上传oos
                        const xhr = new XMLHttpRequest();
                        xhr.withCredentials = false;
                        xhr.open('POST', baseUploadUrl + 'image/vueTinymce/');
                        // xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded;charset=utf-8')
                        // xhr.setRequestHeader('x-token', this.$store.getters.token)
                        xhr.upload.onprogress = function (e) {
                            progress(e.loaded / e.total * 100)
                        };
                        // 成功结果
                        xhr.onload = function () {
                            if (xhr.status != 200) {
                                failure('HTTP Error: ' + xhr.status, {remove: true});
                                return
                            }
                            const json = JSON.parse(xhr.responseText);
                            success(json.data.url);
                        };
                        // 失败结果
                        xhr.onerror = function () {
                            failure('Image upload failed due to a XHR Transport error. Code: ' + xhr.status)
                        };
                        // 请求数据
                        const formData = new FormData()
                        formData.append('file', blobInfo.blob(), blobInfo.filename());
                        xhr.send(formData)
                    },
                }
            }
        },
        props: {
            // 接收值父组件传递值
            content: String
        }
        ,
        created() {
            console.log("--------加载 vue-tinymce 富文本编辑器")
        },
        watch: {
            editorValue: function (newNum, oldNum) {
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