<template>
    <div id="app">
        <router-view/>
        <!--<TinymceEditor/>-->
    </div>
</template>

<script>

    //引入组件
    export default {
        name: "app",
        data() {
            return {};
        },
        created() {
        },
        methods: {},
        computed: {}
    };
</script>
<style lang="scss">
    #app {
        width: 100%;
        height: 100%;
        overflow: hidden;
    }
</style>