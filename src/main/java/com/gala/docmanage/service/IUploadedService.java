package com.gala.docmanage.service;

import com.gala.docmanage.model.UploadedRecord;

import java.util.List;


public interface IUploadedService {

    /**
     * 获取所有上传记录
     *
     * @param user 用户名或邮箱
     * @param category 分类名称
     * @param file 文件名
     * @param offset 偏移
     *
     * @return {@link List}
     */
    List<UploadedRecord> list(String user, String file, String category, int offset);
}
