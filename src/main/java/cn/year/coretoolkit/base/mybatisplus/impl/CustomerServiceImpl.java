//package cn.year.coretoolkit.base.mybatisplus.impl;
//
//import cn.hutool.core.util.ObjectUtil;
//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
//import com.baomidou.mybatisplus.core.metadata.TableInfo;
//import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
//import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.retail.exception.RRException;
//import com.retail.response.Result;
//import com.retail.service.ICustomerService;
//import com.retail.util.CommonMethodUtils;
//import org.springframework.util.CollectionUtils;
//
//import java.util.List;
//
///**
// * @author YearOfTheRain
// * @create 2023-06-25  14:04
// */
//public class CustomerServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M,T> implements ICustomerService<T> {
//
//    @Override
//    public Result<String> importData(List<T> importList, Boolean isUpdateSupport) {
//        if (CollectionUtils.isEmpty(importList)) {
//            throw new RRException("导入数据不能为空！");
//        }
//        int successNum = 0;
//        int failureNum = 0;
//        StringBuilder successMsg = new StringBuilder();
//        StringBuilder failureMsg = new StringBuilder();
//        for (T importData : importList) {
//            String dataId = this.getSinglePrimaryKey(importData);
//            try {
//                // 验证是否存在这条数据
//                T data = this.baseMapper.selectById(dataId);
//                if (ObjectUtil.isNull(data)) {
//                    this.baseMapper.insert(importData);
//                    successNum++;
//                    successMsg.append("<br/>" + successNum + "、数据ID " + dataId + " 导入成功");
//                } else if (isUpdateSupport) {
//                    this.baseMapper.updateById(importData);
//                    successNum++;
//                    successMsg.append("<br/>" + successNum + "、数据ID " + dataId + " 更新成功");
//                } else {
//                    failureNum++;
//                    failureMsg.append("<br/>" + failureNum + "、数据ID " + dataId + " 已存在");
//                }
//            } catch (Exception e) {
//                failureNum++;
//                String msg = "<br/>" + failureNum + "、数据ID " + dataId + " 导入失败：";
//                failureMsg.append(msg + e.getMessage());
//                log.error(msg, e);
//            }
//        }
//        return CommonMethodUtils.getImportResult(successNum, failureNum, successMsg, failureMsg);
//    }
//
//    /**
//     * 获取单个主键的值
//     * @param obj 对象表
//     * @return
//     */
//    public String getSinglePrimaryKey(T obj) {
//        TableInfo tableInfo = TableInfoHelper.getTableInfo(obj.getClass());
//        String primaryKeyColumnName = tableInfo.getKeyColumn();
//        Object fieldValue = ReflectionKit.getFieldValue(obj, primaryKeyColumnName);
//        return String.valueOf(fieldValue);
//    }
//}
