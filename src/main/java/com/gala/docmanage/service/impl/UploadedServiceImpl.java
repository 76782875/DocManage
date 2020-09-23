package com.gala.docmanage.service.impl;

import com.gala.docmanage.dao.UploadedDAO;
import com.gala.docmanage.util.ServiceUtils;
import com.gala.docmanage.model.UploadedRecord;
import com.gala.docmanage.service.IUploadedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UploadedServiceImpl implements IUploadedService {

    private final UploadedDAO uploadedDAO;

    @Autowired
    public UploadedServiceImpl(UploadedDAO uploadedDAO) {this.uploadedDAO = uploadedDAO;}

    @SuppressWarnings("unchecked")
    @Override
    public List<UploadedRecord> list(String user, String file, String category, int offset) {
        return (List<UploadedRecord>) ServiceUtils.invokeFileFilter(uploadedDAO, "listUploadedBy", user, file,
                category, offset);
    }
}
