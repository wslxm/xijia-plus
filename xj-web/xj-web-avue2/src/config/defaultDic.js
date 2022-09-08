/**
 * 默认数据
 * @author wangsong
 * @mail  1720696548@qq.com
 * @date  2022/9/8 0008 12:19
 * @version 1.0.0
 */
export default {
    /**
     * 默认级联 字典数据
     */
    dicData: [{
        id: '1',
        name: '指南',
        children: [{
            id: '2',
            name: '设计原则',
            children: [{
                id: '3',
                name: '一致'
            }, {
                id: '4',
                name: '反馈'
            }]
        },
            {
                id: '11',
                name: '设计原则2',
                children: [{
                    id: '12',
                    name: '一致2'
                }, {
                    id: '13',
                    name: '反馈3'
                }]
            }
        ]
    }],
    timeOptions: {
        shortcuts: [
            {
                text: '最近一天',
                onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    start.setTime(start.getTime() - 3600 * 1000 * 24 * 1);
                    picker.$emit('pick', [start, end]);
                }
            },
            {
                text: '最近三天',
                onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    start.setTime(start.getTime() - 3600 * 1000 * 24 * 3);
                    picker.$emit('pick', [start, end]);
                }
            },
            {
                text: '最近一周',
                onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                    picker.$emit('pick', [start, end]);
                }
            }, {
                text: '最近一个月',
                onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                    picker.$emit('pick', [start, end]);
                }
            }, {
                text: '最近三个月',
                onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                    picker.$emit('pick', [start, end]);
                }
            }, {
                text: '最近半年',
                onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    start.setTime(start.getTime() - 3600 * 1000 * 24 * 182);
                    picker.$emit('pick', [start, end]);
                }
            }, {
                text: '最近一年',
                onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    start.setTime(start.getTime() - 3600 * 1000 * 24 * 365);
                    picker.$emit('pick', [start, end]);
                }
            }, {
                text: '最近三年',
                onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    start.setTime(start.getTime() - 3600 * 1000 * 24 * 365 * 3);
                    picker.$emit('pick', [start, end]);
                }
            }
        ]
    }
}