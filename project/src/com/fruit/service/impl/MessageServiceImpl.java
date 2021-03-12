package com.fruit.service.impl;

import com.fruit.base.BaseDao;
import com.fruit.base.BaseServiceImpl;
import com.fruit.mapper.MessageMapper;
import com.fruit.po.Message;
import com.fruit.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 公告 service 层
 */
@Service
public class MessageServiceImpl extends BaseServiceImpl<Message> implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public BaseDao<Message> getBaseDao() {
        return messageMapper;
    }
}
