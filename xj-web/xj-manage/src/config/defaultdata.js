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
    /**
     * 时间默认值
     * @author wangsong
     * @mail  1720696548@qq.com
     * @date  2023/2/1 0001 10:58
     * @version 1.0.0
     */
    timeOptions: {
        shortcuts: [
            {
                text: '今天',
                onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    start.setTime(new Date(new Date(new Date().toLocaleDateString()).getTime()));
                    end.setTime(new Date(new Date(new Date().toLocaleDateString()).getTime() + 24 * 60 * 60 * 1000 - 1));
                    picker.$emit('pick', [start, end]);
                }
            },
            {
                text: '昨天',
                onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    start.setTime(new Date(new Date(new Date().toLocaleDateString()).getTime() - (24 * 60 * 60 * 1000)));
                    end.setTime(new Date(new Date(new Date().toLocaleDateString()).getTime() - 1));
                    picker.$emit('pick', [start, end]);
                }
            },
            {
                text: '最近三天',
                onClick(picker) {
                    const end = new Date();
                    const start = new Date();
                    start.setTime(new Date(new Date(new Date().toLocaleDateString()).getTime() - (24 * 60 * 60 * 1000 * 7)));
                    end.setTime(new Date(new Date(new Date().toLocaleDateString()).getTime() - 1));
                    picker.$emit('pick', [start, end]);
                }
            },
        ]
    },
}