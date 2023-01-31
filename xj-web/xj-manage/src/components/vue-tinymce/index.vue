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
                    // images_upload_url: uploadPath + 'image/vueTinymce/',
                    // 允许粘贴图片上传,及格式
                    paste_data_images: true,
                    images_file_types: 'jpeg,jpg,png,gif,bmp,webp',
                    // 此处为图片上传处理函数 (手动上传)
                    images_upload_handler: (blobInfo, success, failure, progress) => {
                        console.log('上传处理器111：');
                        // const formData = new FormData()
                        // formData.append('file', blobInfo.blob(), blobInfo.filename());
                        let file = new window.File([blobInfo.blob()], blobInfo.filename());
                        this.crud.upload(file, "image/vueTinymce/").then(res => {
                            // 获取返回数据
                            let data = res.data.data;
                            // 添加图片到内容
                            success(data.url, data.name)
                        });
                    },
                    file_picker_callback: function(callback, value, meta) {
                        // Provide file and text for the link dialog
                        if (meta.filetype == 'file') {
                            callback('mypage.html', {text: 'My text'});
                        }
                        // Provide image and alt text for the image dialog
                        if (meta.filetype == 'image') {
                            callback('myimage.jpg', {alt: 'My alt text'});
                        }
                        // Provide alternative source and posted for the media dialog
                        if (meta.filetype == 'media') {
                            callback('movie.mp4', {source2: 'alt.ogg', poster: 'image.jpg'});
                        }
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