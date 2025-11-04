package com.coding404.myweb.topic;

import com.coding404.myweb.command.TopicVO;
import com.coding404.myweb.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TopicMapper {
    int topicRegist(TopicVO topicVO);
    List<TopicVO> topicListAll(Criteria cri);
    int getTotal(Criteria cri);
    int getTotalMe(String topicWriter);
    List<TopicVO> topicListMe(@Param("topicWriter") String topicWriter,
                              @Param("cri") Criteria cri);
    TopicVO topicDetail(long topicId);
    int topicModify(TopicVO topicVO);
    int topicDelete(TopicVO topicVO);
}
