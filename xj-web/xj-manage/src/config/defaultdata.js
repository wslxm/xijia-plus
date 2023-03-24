import dayjs from 'dayjs'

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
     * <p>
     *     获取周和月采用 dayjs 工具实现
     * </p>
     * @author wangsong
     * @mail  1720696548@qq.com
     */
    timeOptions: {
        shortcuts: [
            {
                text: '今天',
                onClick(picker) {
                    const start = new Date(new Date(new Date(new Date().toLocaleDateString()).getTime()));
                    const end = new Date(new Date(new Date(new Date().toLocaleDateString()).getTime() + 24 * 60 * 60 * 1000 - 1));
                    picker.$emit('pick', [start, end]);
                }
            },
            {
                text: '昨天',
                onClick(picker) {
                    const start = new Date(new Date(new Date(new Date().toLocaleDateString()).getTime() - (24 * 60 * 60 * 1000)));
                    const end = new Date(new Date(new Date(new Date().toLocaleDateString()).getTime() - 1));
                    picker.$emit('pick', [start, end]);
                }
            },
            {
                text: '最近1天',
                onClick(picker) {
                    const start = new Date(new Date(new Date().toLocaleDateString()).getTime() - (24 * 60 * 60 * 1000 * 3));
                    const end = new Date(new Date(new Date(new Date().toLocaleDateString()).getTime() - 1));
                    picker.$emit('pick', [start, end]);
                }
            },
            {
                text: '最近3天',
                onClick(picker) {
                    const start = new Date(new Date(new Date().toLocaleDateString()).getTime() - (24 * 60 * 60 * 1000 * 7));
                    const end = new Date(new Date(new Date(new Date().toLocaleDateString()).getTime() - 1));
                    picker.$emit('pick', [start, end]);
                }
            },
            {
                text: '本周',
                onClick(picker) {
                    const start = new Date(dayjs().startOf('week').add(1, 'day').format('YYYY-MM-DD HH:mm:ss'));
                    const end = new Date(dayjs().endOf('week').add(1, 'day').format('YYYY-MM-DD HH:mm:ss'));
                    picker.$emit('pick', [start, end]);
                }
            },
            {
                text: '上1周',
                onClick(picker) {
                    const start = new Date(dayjs().add(-1, 'week').startOf('week').add(1, 'day').format('YYYY-MM-DD HH:mm:ss'));
                    const end = new Date(dayjs().add(-1, 'week').endOf('week').add(1, 'day').format('YYYY-MM-DD HH:mm:ss'));
                    picker.$emit('pick', [start, end]);
                }
            },
            {
                text: '上2周',
                onClick(picker) {
                    const start = new Date(dayjs().add(-2, 'week').startOf('week').add(1, 'day').format('YYYY-MM-DD HH:mm:ss'));
                    const end = new Date(dayjs().add(-2, 'week').endOf('week').add(1, 'day').format('YYYY-MM-DD HH:mm:ss'));
                    picker.$emit('pick', [start, end]);
                }
            },
            {
                text: '本月',
                onClick(picker) {
                    const start = new Date(dayjs().startOf('month').format('YYYY-MM-DD HH:mm:ss'));
                    const end = new Date(dayjs().endOf('month').format('YYYY-MM-DD HH:mm:ss'));
                    picker.$emit('pick', [start, end]);
                }
            },
            {
                text: '上1月',
                onClick(picker) {
                    const start = new Date(dayjs().add(-1, 'month').startOf('month').format('YYYY-MM-DD HH:mm:ss'));
                    const end = new Date(dayjs().add(-1, 'month').endOf('month').format('YYYY-MM-DD HH:mm:ss'));
                    picker.$emit('pick', [start, end]);
                }
            },
            {
                text: '上2月',
                onClick(picker) {
                    const start = new Date(dayjs().add(-2, 'month').startOf('month').format('YYYY-MM-DD HH:mm:ss'));
                    const end = new Date(dayjs().add(-2, 'month').endOf('month').format('YYYY-MM-DD HH:mm:ss'));
                    picker.$emit('pick', [start, end]);
                }
            },
            {
                text: '上3月',
                onClick(picker) {
                    const start = new Date(dayjs().add(-3, 'month').startOf('month').format('YYYY-MM-DD HH:mm:ss'));
                    const end = new Date(dayjs().add(-3, 'month').endOf('month').format('YYYY-MM-DD HH:mm:ss'));
                    picker.$emit('pick', [start, end]);
                }
            },
        ]
    },
}
