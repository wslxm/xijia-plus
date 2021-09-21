<template>
    <avue-crud :data="data" :option="option" v-model="form"></avue-crud>
</template>

<script>
    export default {
        data() {
            return {
                form: {},
                data: [
                    {
                        name: '张三',
                        sex: '男'
                    }, {
                        name: '李四',
                        sex: '女'
                    }, {
                        name: '王五',
                        sex: '女'
                    }, {
                        name: '赵六',
                        sex: '男'
                    }
                ],
                option: {
                    align: 'center',
                    menuAlign: 'center',
                    column: [
                        {
                            label: '姓名',
                            prop: 'name'
                        },
                        {
                            label: '性别',
                            prop: 'sex'
                        }
                    ]
                }
            }
        },
    }

</script>