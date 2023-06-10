package com.xuecheng.media.service.jobhandler;

import com.xuecheng.media.model.po.MediaProcess;
import com.xuecheng.media.service.MediaFileProcessService;
import com.xuecheng.media.service.MediaFileService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;

/**
 * 任务处理类
 */
@Slf4j
@Component
public class VideoTask {

    @Autowired
    MediaFileProcessService mediaFileProcessService;

    @Autowired
    MediaFileService mediaFileService;

    //ffmpeg的路径
    @Value("${videoprocess.ffmpegpath}")
    private String ffmpegpath;

    /**
     * 视频处理任务
     */
    @XxlJob("videoJobHandler")
    public void videoJobHandler() throws Exception {
        //分片参数
        int shardIndex = XxlJobHelper.getShardIndex();
        int shardTotal = XxlJobHelper.getShardTotal();

        //确定cpu的核心数
        //通过java代码实现
        int processors = Runtime.getRuntime().availableProcessors();

        List<MediaProcess> mediaProcessList = mediaFileProcessService.getMediaProcessList(shardIndex, shardTotal, processors);

        //任务数量
        int size = mediaProcessList.size();

        ExecutorService executorService = Executors.newFixedThreadPool(size);

        mediaProcessList.forEach(
                mediaProcess -> {
                    //将任务加入线程池
                    executorService.execute(() -> {
                        //任务id
                        Long taskId = mediaProcess.getId();
                        //开启任务
                        boolean flag = mediaFileProcessService.startTask(taskId);
                        if (!flag) {
                            log.debug("抢占任务失败,任务id{}", taskId);
                            return;
                        }


                    });
                }
        );


    }

    private String getFilePath(String fileMd5, String fileExt) {
        return fileMd5.substring(0, 1) + "/" + fileMd5.substring(1, 2) + "/" + fileMd5 + "/" + fileMd5 + fileExt;
    }
}
