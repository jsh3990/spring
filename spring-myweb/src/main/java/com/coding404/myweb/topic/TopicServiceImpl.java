package com.coding404.myweb.topic;

import com.coding404.myweb.command.TopicVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("topicService")
public class TopicServiceImpl implements TopicService{
    @Autowired
    private TopicMapper topicMapper;

    @Override
    public int topicRegist(TopicVO topicVO) {
        return topicMapper.topicRegist(topicVO);
    }

    @Override
    public List<TopicVO> getList(String topicWriter) {
        return topicMapper.getList(topicWriter);
    }

    @Override
    public TopicVO getDetail(long topicId) {
        return topicMapper.getDetail(topicId);
    }

    @Override
    public int topicDelete(long topicId) {
        return topicMapper.topicDelete(topicId);
    }

    @Override
    public int topicUpdate(TopicVO topicVO) {
        return topicMapper.topicUpdate(topicVO);
    }
}
