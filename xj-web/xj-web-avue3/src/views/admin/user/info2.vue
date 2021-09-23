<template>
    <avue-crud :data="data" :option="option3" v-model="obj" @date-change="dateChange">
        <template #menu="scope">
            <el-button size="small" @click.native="tip" icon="el-icon-share"></el-button>
        </template>
    </avue-crud>
</template>

<script>
    export default {
        data() {
            return {
                obj:{},
                data: [
                    {
                        name:'张三',
                        sex:'男'
                    }, {
                        name:'李四',
                        sex:'女'
                    }, {
                        name:'王五',
                        sex:'女'
                    }, {
                        name:'赵六',
                        sex:'男'
                    }
                ],
                option3:{
                    menuType:'icon',
                    dateBtn:true,
                    dateDefault:true,
                    column:[
                        {
                            label:'姓名',
                            prop:'name'
                        },
                        {
                            label:'性别',
                            prop:'sex'
                        }
                    ]
                }
            }
        },
        methods: {
            dateChange(date){
                this.$message.success(date);
            },
            tip(){
                this.$message.success('自定义按钮');
            }
        }
    }
</script>