package com.holden.wxproject.ageiport;

import com.alibaba.ageiport.common.utils.BeanUtils;
import com.alibaba.ageiport.processor.core.annotation.ExportSpecification;
import com.alibaba.ageiport.processor.core.exception.BizException;
import com.alibaba.ageiport.processor.core.model.api.BizExportPage;
import com.alibaba.ageiport.processor.core.model.api.BizUser;
import com.alibaba.ageiport.processor.core.task.exporter.ExportProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName wxproject-StandaloneExportProcessor
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年12月13日10:12 - 周二
 * @Describe
 */
@ExportSpecification(code = "StandaloneExportProcessor", name = "StandaloneExportProcessor")
public class StandaloneExportProcessor implements ExportProcessor<Query, Data, View> {

    //2.实现ExportProcessor接口的TotalCount方法
    @Override
    public Integer totalCount(BizUser bizUser, Query query) throws BizException {
        return query.getTotalCount();
    }

    //3.实现ExportProcessor接口的queryData方法
    @Override
    public List<Data> queryData(BizUser user, Query query, BizExportPage bizExportPage) throws BizException {
        List<Data> dataList = new ArrayList<>();

        Integer totalCount = query.getTotalCount();
        for (int i = 1; i <= totalCount; i++) {
            final Data data = new Data();
            data.setId(i);
            data.setName("name" + i);
            dataList.add(data);
        }
        return dataList;
    }

    //4.实现ExportProcessor接口的convert方法
    @Override
    public List<View> convert(BizUser user, Query query, List<Data> data) throws BizException {
        List<View> dataList = new ArrayList<>();
        for (Data datum : data) {
            View view = BeanUtils.cloneProp(datum, View.class);
            dataList.add(view);
        }
        return dataList;
    }
}
