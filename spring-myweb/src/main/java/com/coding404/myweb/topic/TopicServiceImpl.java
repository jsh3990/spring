package com.coding404.myweb.topic;

import com.coding404.myweb.command.TopicVO;
import com.coding404.myweb.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("topicService")
public class TopicServiceImpl implements TopicService{
    @Autowired
    private TopicMapper topicMapper;

    @Override
    public int topicReg(TopicVO topicVO) {
        return topicMapper.topicReg(topicVO);
    }

    @Override
    public int topicModify(TopicVO topicVO) {
        return topicMapper.topicModify(topicVO);
    }

    @Override
    public List<TopicVO> getList(String topicWriter, Criteria cri) {
        return topicMapper.getList(topicWriter, cri);
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

    @Override
    public int getTotal(String topicWriter) {
        return topicMapper.getTotal(topicWriter);
    }
}
