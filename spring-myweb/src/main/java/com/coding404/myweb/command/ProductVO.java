package com.coding404.myweb.command;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

//@Getter
//@Setter
//@ToString
@Data //getter + setter + toString 한 번에
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더패턴으로 객체생성을 제공해줌
public class ProductVO {
    private long prodId;
    private LocalDateTime prodRegdate;
    private String prodEnddate;
    private String prodCategory;
    private String prodWriter;
    private String prodName;
    private long prodPrice;
    private long prodCount;
    private long prodDiscount;
    private String prodPurchaseYn;
    private String prodContent;
    private String prodComment;

    //조인된 컬럼 결과 - 단순히 가져갈 컬럼이 1개
    private String categoryNav;

}
