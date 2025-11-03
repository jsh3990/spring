package com.coding404.myweb.topic;

import com.coding404.myweb.command.TopicVO;

import java.util.List;

public interface TopicService {
    int topicRegist(TopicVO topicVO);
    List<TopicVO> getList(String topicWriter);
    TopicVO getDetail(long topicId);
    int topicUpdate(TopicVO topicVO);
    int topicDelete(long topicId);
}
