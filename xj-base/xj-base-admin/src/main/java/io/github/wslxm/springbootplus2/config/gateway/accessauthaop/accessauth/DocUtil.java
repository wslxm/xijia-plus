//package io.github.wslxm.springbootplus2.config.gateway.accessauthaop.accessauth;
//
//
//import com.sun.javadoc.ClassDoc;
//import com.sun.javadoc.FieldDoc;
//import com.sun.javadoc.RootDoc;
//import com.sun.tools.javadoc.Main;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
///**
// * Description:
// *
// * @author jack
// * @date 2021/7/13 5:10 下午
// */
//public class DocUtil {
//
//    /**
//     * 会自动注入
//     */
//    private static RootDoc rootDoc;
//
//    /**
//     * 会自动调用这个方法
//     *
//     * @param root root
//     * @return true
//     */
//    public static boolean start(RootDoc root) {
//        rootDoc = root;
//        return true;
//    }
//
//    /**
//     * 生成文档
//     *
//     * @param beanFilePath 注意这里是.java文件绝对路径
//     * @return 文档注释
//     */
//    public static DocVO execute(String beanFilePath) {
//        Main.execute(new String[]{"-doclet", DocUtil.class.getName(), "-docletpath",
//                DocUtil.class.getResource("/").getPath(), "-encoding", "utf-8", beanFilePath});
//
//        ClassDoc[] classes = rootDoc.classes();
//
//        if (classes == null || classes.length == 0) {
//            return null;
//        }
//        ClassDoc classDoc = classes[0];
//        // 获取属性名称和注释
//        FieldDoc[] fields = classDoc.fields(false);
//
//        List<DocVO.FieldVO> fieldVOList = new ArrayList<>(fields.length);
//
//        for (FieldDoc field : fields) {
//            fieldVOList.add(new DocVO.FieldVO(field.name(), field.type().typeName(), field.commentText()));
//        }
//        return new DocVO(fieldVOList);
//    }
//
//
//    /**
//     * Description:
//     *
//     * @author jack
//     * @date 2021/7/13 4:11 下午
//     */
//    public static void main(String[] args) {
//
//        String beanFilePath = "./SysLog.java";
//        DocUtil.execute(beanFilePath);
//        DocVO docVO = DocUtil.execute(beanFilePath);
//        if (Objects.nonNull(docVO) && Objects.nonNull(docVO.getFieldVOList())) {
//            docVO.getFieldVOList().forEach(System.out::println);
//        }
//    }
//
//}
