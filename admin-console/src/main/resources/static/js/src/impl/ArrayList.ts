import {List} from "../List";

/**
 * TODO ArrayList
 * @author 王松
 * @WX-QQ 1720696548
 * @date  2019/11/19 12:57
 */
class ArrayList<E> implements List<E> {

    // 定义属性集合
    elementData: Object[];

    // ArrayList实际数量
    sizeNum: number = 0;


    /**
     * TODO  构造函数初始化集合大小
     * @param null
     * @date  2019/11/19 15:18
     * @return
     */
    constructor()
    constructor(initialCapacity: number)
    constructor(initialCapacity?) {
        if (typeof initialCapacity === 'number') {
            //设置初始集合大小
            if (initialCapacity < 0) {
                throw new Error("is no arrayList index : " + initialCapacity);
            }
            this.elementData = new Array<Object>(initialCapacity);
        } else {
            //初始化集合大小
            this.constructor(10);
        }
    }

    /**
     * TODO  添加
     *
     * @param arg0 = 只有一个参数的时候为对象值，存在两个参数的时候为下标（index）
     * @param arg1 = 当存在两个参数的时候为对象值
     * @date  2019/11/19 17:30
     * @return
     */
    add(object: E)
    add(index: number, object: E)
    add(arg0?, arg1?) {
        if (typeof arg0 === 'number') {
            //索引添加
            this.ensureExplicitVapacity();
            this.rangeCheck(arg0);
            this.elementData.splice(arg0, 0, arg1);
            this.sizeNum++;
        } else {
            //普通添加,容量计算
            this.ensureExplicitVapacity();
            this.elementData[this.sizeNum] = arg0;
            this.sizeNum++;
        }
    }


    /**
     * TODO  通过下标查询对象
     * @param index 索引
     * @date  2019/11/19 16:15
     * @return
     */
    get(index: number): Object {
        this.rangeCheck(index);
        return this.elementData[index];
    }


    /**
     * TODO  更新数据
     * @param index 下标
     * @param 对象数据
     * @date  2019/11/19 18:19
     * @return
     */
    update(index: number, Object: E): void {
        this.rangeCheck(index);
        this.elementData[index] = Object;
    }


    /**
     * TODO  删除
     *
     * @param null
     * @date  2019/11/19 16:15
     * @return
     */
    remove(object: E): boolean;
    remove(index: number): boolean;
    remove(arg0: number | E): boolean {
        if (typeof arg0 === 'number') {
            //删除指定下标数据
            this.elementData.splice(arg0, 1);
            this.sizeNum--;
            return true;
        } else {
            //删除具体数据,数据多不建议使用
            let result = false;
            for (let i = 0; i < this.sizeNum; i++) {
                if (this.get(i) === arg0) {
                    result = this.remove(i);
                }
            }
            if (result == false) {
                console.log("is no object?");
                return result;
            }
        }
    }


    /**
     * TODO 获取集合长度
     * @date  2019/11/19 16:15
     * @return
     */
    public size(): number {
        return this.sizeNum;
    }

    /**
     * TODO 检测数组是否下标越界，是抛出越界异常
     *
     * @param index
     */
    private rangeCheck(index: number): void {
        if (index >= this.sizeNum || index < 0) {
            throw new Error("is no index--->" + index);
        }
    }


    /**
     *  TODO 自动扩容 1.5X
     */
    public ensureExplicitVapacity(): void {

        if (this.elementData.length < this.sizeNum + 1) {
            // 当前集合实际容量
            let oldCapacity: number = this.elementData.length;
            //扩容1.5倍后的数
            let newCapacity: number = oldCapacity + (oldCapacity >> 1);
            //修改集合容量
            this.elementData.length = newCapacity;
            //console.log(this.elementData.length+"--> "+newCapacity + "--》"+this.elementData);
        }
    }
}


var list = new ArrayList(10);
list.add("张三");
list.add("李四");
list.add("王五");
console.log("-------------测试添加-----------------");
for (var i = 0; i < list.size(); i++) {
    console.log(list.get(i));
}

console.log("-------------测试修改，下标1改为赵六-------------");
list.update(1, "赵六");
for (var i = 0; i < list.size(); i++) {
    console.log(list.get(i));
}


console.log("-------------测试删除，删除下标为2的-------------");
list.remove(2);
for (var i = 0; i < list.size(); i++) {
    console.log(list.get(i));

}

console.log("-------------测试删除，删除'张三'-------------");
list.remove('张三');
for (var i = 0; i < list.size(); i++) {
    console.log(list.get(i));
}