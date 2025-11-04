package com.coding404.myweb.topic;

import com.coding404.myweb.command.TopicVO;
import com.coding404.myweb.util.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TopicService {
    int topicReg(TopicVO topicVO);
    int topicModify(TopicVO topicVO);
    //List<TopicVO> getList(String topicWriter);
    List<TopicVO> getList(@Param("topicWriter") String topicWriter,
                          @Param("cri") Criteria cri);
    int getTotal(String topicWriter);

    TopicVO getDetail(long topicId);
    int topicUpdate(TopicVO topicVO);
    int topicDelete(long topicId);
}
