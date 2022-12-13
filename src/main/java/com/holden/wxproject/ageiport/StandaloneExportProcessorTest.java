package com.holden.wxproject.ageiport;

import com.alibaba.ageiport.common.utils.JsonUtil;
import com.alibaba.ageiport.processor.core.AgeiPort;
import com.alibaba.ageiport.processor.core.AgeiPortOptions;
import com.alibaba.ageiport.processor.core.spi.service.TaskExecuteParam;
import com.alibaba.ageiport.processor.core.spi.service.TaskExecuteResult;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @ClassName wxproject-StandaloneExportProcessorTest
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年12月13日10:21 - 周二
 * @Describe
 */
public class StandaloneExportProcessorTest {
    @SneakyThrows
    @Test
    public void test() {
        //1.初始化AgeiPort实例
        AgeiPortOptions options = new AgeiPortOptions();
        AgeiPort ageiPort = AgeiPort.ageiPort(options);

        //2.构造查询参数TaskExecuteParam
        Query query = new Query();
        query.setTotalCount(100);

        //3.调用本地方法executeTask，开始执行任务，并获取任务实例ID。
        TaskExecuteParam request = new TaskExecuteParam();
        request.setTaskSpecificationCode(StandaloneExportProcessor.class.getSimpleName());
        request.setBizUserId("userId");
        request.setBizQuery(JsonUtil.toJsonString(query));//{"totalCount":1000}
        TaskExecuteResult response = ageiPort.getTaskService().executeTask(request);
        Assertions.assertTrue(response.getSuccess());


        //4.使用内部封装的TaskHelp方法判断任务是否执行成功
        //Helper testHelper = new Helper(ageiPort);
        //testHelper.assertWithFile(response.getMainTaskId(), query.getTotalCount());
    }
}
