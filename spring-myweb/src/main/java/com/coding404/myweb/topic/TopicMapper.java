package com.coding404.myweb.topic;

import com.coding404.myweb.command.TopicVO;
import com.coding404.myweb.util.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TopicMapper {
    int topicReg(TopicVO topicVO); //등록
    int topicModify(TopicVO topicVO);
    //List<TopicVO> getList(String topicWriter);
    List<TopicVO> getList(String topicWriter, Criteria cri);
    int getTotal(String topicWriter);

    TopicVO getDetail(long topicId);
    int topicUpdate(TopicVO topicVO);
    int topicDelete(long topicId);
}
