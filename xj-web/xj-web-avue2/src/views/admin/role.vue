
<template>
    <div>角色管理
        <avue-crud
                :data="data"
                :option="option"
                :page.sync="page"
                @on-load="onLoad"
        ></avue-crud>

    </div>
</template>


<script>
    export default {
        data() {
            return {
                page: {
                    pageSize: 20,
                    pagerCount:5
                },
                data: [],
                option: {
                    align: 'center',
                    menuAlign: 'center',
                    column: [
                        {
                            label: '序号',
                            prop: 'id'
                        },
                        {
                            label: '角色名称',
                            prop: 'name'
                        },
                        {
                            label: 'code',
                            prop: 'code'
                        },
                        {
                            label: '描叙',
                            prop: 'desc'
                        }
                    ]
                }
            }
        },
        methods: {
            onLoad(page) {
                //this.$message.success('分页信息:' + JSON.stringify(page))
                this.page.total = 40
                //模拟分页
                if (this.page.currentPage === 1) {
                    this.data = [
                        {
                            id:1,
                            name: '超级管理员',
                            code: 'SYS',
                            desc: ''
                        },{
                            id:2,
                            name: '测试',
                            code: 'TEST',
                            desc: ''
                        }
                    ]
                } else if (this.page.currentPage == 2) {
                    this.data = [
                        {
                            id:1,
                            name: '超级管理员2',
                            code: 'SYS2',
                            desc: ''
                        },{
                            id:2,
                            name: '测试2',
                            code: 'TEST2',
                            desc: ''
                        }
                    ]
                }
            }
        }
    }
</script>