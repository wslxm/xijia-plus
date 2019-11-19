/**
 * TODO  List 集合
 * @author 王松
 * @WX-QQ 1720696548
 * @date  2019/11/19 12:55
 */
export interface List<E> {
    /**  LinkedList + ArrayList 通用接口 */
    add(object: E);                           //添加对象
    add(index: number, object: E);            //指定索引添加对象
    remove(index: number): boolean;           //指定索引删除对象
    remove(object: E): boolean;               //具体对象删除
    get(index: number): Object;               //通过索引获取对象，for循环获取必备
    size(): Number;                           //获取list长度
    update(index: number, Object: E): void;   //修改指定索引数据


}





