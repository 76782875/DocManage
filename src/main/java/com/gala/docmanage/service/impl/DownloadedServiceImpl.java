package com.gala.docmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gala.docmanage.dao.DownloadedDAO;
import com.gala.docmanage.entity.Download;
import com.gala.docmanage.model.DownloadRecord;
import com.gala.docmanage.util.ServiceUtils;
import com.gala.docmanage.service.IDownloadedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DownloadedServiceImpl extends ServiceImpl<DownloadedDAO, Download> implements IDownloadedService {

    private final DownloadedDAO downloadDAO;

    @Autowired
    public DownloadedServiceImpl(DownloadedDAO downloadDAO) {
        this.downloadDAO = downloadDAO;
    }

    @Override
    public void insertDownload(int userId, long fileId) {
        downloadDAO.insertDownload(userId, fileId);
    }

    @Override
    public void removeByFileId(long fileId) {
        downloadDAO.removeByFileId(fileId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DownloadRecord> list(String user, String file, String category, int offset) {
        return (List<DownloadRecord>) ServiceUtils.invokeFileFilter(downloadDAO, "listDownloadedBy", user, file,
                category, offset);
    }
}
