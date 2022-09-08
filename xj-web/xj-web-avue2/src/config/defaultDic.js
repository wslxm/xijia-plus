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
}