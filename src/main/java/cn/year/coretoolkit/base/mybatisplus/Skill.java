package cn.year.coretoolkit.base.mybatisplus;

/**
 * 一些使用小技巧
 * @author YearOfTheRain
 * @create 2024-03-15  10:32
 */
public class Skill {

    /**
     * 获取单个主键的值
     * @param obj 对象表
     * @return
     */
    public String getSinglePrimaryKey(T obj) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(obj.getClass());
        String primaryKeyColumnName = tableInfo.getKeyColumn();
        Object fieldValue = ReflectionKit.getFieldValue(obj, primaryKeyColumnName);
        return String.valueOf(fieldValue);
    }
}
