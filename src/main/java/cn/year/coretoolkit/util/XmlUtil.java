package cn.year.coretoolkit.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.TypePermission;

/**
 * 使用单例模式构建XStream解析对象
 * @author YearOfTheRain
 * @create 2024-02-19  15:01
 */
public class XmlUtil {

    private XmlUtil() {
        // 私有构造方法
    }

//    /**
//     * 使用单例模式
//     * 1、如果只在一个启动项目中使用，则可以直接使用懒汉单例。简单粗暴直接有效
//     * 2、如果在多个启动项目中，则需要改写成静态内部类的方式，降低一丢丢的内存开销
//     */
//    private static final XStream X_STREAM = new XStream();
//
//    static {
//        // 初始化安全框架
//        XStream.setupDefaultSecurity(X_STREAM);
//        X_STREAM.addPermission(AnyTypePermission.ANY);
//        // 添加白名单，只允许反序列化指定的类
//        TypePermission typePermission = new TypePermission() {
//            @Override
//            public boolean allows(Class type) {
//                return type.getPackage().getName().startsWith("com.retail"); // 设置允许的包名
//            }
//        };
//        X_STREAM.addPermission(typePermission);
//    }
//
//    /**
//     * 将XML反序列化还原为Java对象
//     * java对象类型可配置
//     *
//     * @param xmlStr
//     * @return
//     */
//    public static <T> T xmlToBean(String xmlStr, Class<T> cls) {
//        X_STREAM.processAnnotations(cls);
//        X_STREAM.allowTypesByRegExp(new String[]{".*"});
//        return (T) X_STREAM.fromXML(xmlStr);
//    }

    /**
     * 采用静态内部类的方式初始化，实现延迟加载
     */
    private static class XStreamHolder {
        private static final XStream X_STREAM = new XStream();

        static {
            // 初始化安全框架
            XStream.setupDefaultSecurity(X_STREAM);
            X_STREAM.addPermission(AnyTypePermission.ANY);
            // 添加白名单，只允许反序列化指定的类
            TypePermission typePermission = new TypePermission() {
                @Override
                public boolean allows(Class type) {
                    return type.getPackage().getName().startsWith("com.retail"); // 设置允许的包名
                }
            };
            X_STREAM.addPermission(typePermission);
        }
    }


    /**
     * 将XML反序列化还原为Java对象
     * java对象类型可配置
     *
     * @param xmlStr
     * @return
     */
    public static <T> T xmlToBean(String xmlStr, Class<T> cls) {
        XStream xStream = XStreamHolder.X_STREAM;
        xStream.processAnnotations(cls);
        xStream.allowTypesByRegExp(new String[]{".*"});
        return (T) xStream.fromXML(xmlStr);
    }

}