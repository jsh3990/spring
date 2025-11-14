package com.coding404.myweb.product;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductUploadVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    //파일 업로드 경로
    @Value("${project.upload.path}")
    String uploadPath;

    //폴더 생성 메서드
    public String makeFolder() {
        String path = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        File file = new File(uploadPath + "/" + path);
        if (!file.exists()) {
            file.mkdirs(); //폴더를 생성
        }
        return path;
    }

    //메서드를 트랜잭션 처리
    //한프로세스 안에서 예외 발생시, 롤백을 처리
    //에러가 try~catch 되어있으면 처리 불가
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int prodRegist(ProductVO productVO, List<MultipartFile> files) {

        //1. productVO -> 상품 인서트
        //2. 업로드
        //3. productVO의 pk를 fk로 쓰는 upload테이블 인서트
        //4. 결과 반환

        //1.
        int result = productMapper.prodRegist(productVO);

        //2.
        for(MultipartFile file : files) {
            //위와 동일
            String originalFilename = file.getOriginalFilename(); //파일의 이름
            String fileName = originalFilename.substring(originalFilename.lastIndexOf("\\") + 1);

            String uuid = UUID.randomUUID().toString(); //랜덤 문자열값
            String filePath = makeFolder(); //폴더 경로
            String path = uploadPath + "/" + filePath + "/" + uuid + "_" + fileName;
            File saveFile = new File(path);

            try {
                file.transferTo(saveFile); //업로드처리
            } catch (Exception e) {
                System.out.println("업로드 중 예외 발생: ");
                e.printStackTrace();
                throw new RuntimeException("예외 발생");
            }

            //3.
            productMapper.fileRegist(
                    ProductUploadVO
                            .builder()
                            .filename(fileName)
                            .filepath(filePath)
                            .uuid(uuid)
                            .prodWriter(productVO.getProdWriter() )
                            .build()
            );
        }
        return result;
    }

    @Override
    public List<ProductVO> getList(String prodWriter, Criteria cri) {
        return productMapper.getList(prodWriter, cri);
    }

    @Override
    public int getTotal(String prodWriter, Criteria cri) {
        return productMapper.getTotal(prodWriter, cri);
    }

    @Override
    public ProductVO getDetail(long prodId) {
        return productMapper.getDetail(prodId);
    }

    @Override
    public int prodUpdate(ProductVO productVO) {
        return productMapper.prodUpdate(productVO);
    }

    @Override
    public int prodDelete(long prodId) {
        return productMapper.prodDelete(prodId);
    }

    @Override
    public List<CategoryVO> getCategory() {
        return productMapper.getCategory();
    }

    @Override
    public List<CategoryVO> getCategoryChild(CategoryVO categoryVO) {
        return productMapper.getCategoryChild(categoryVO);
    }

    @Override
    public List<ProductUploadVO> getDetailFile(long prodId) {
        return productMapper.getDetailFile(prodId);
    }
}
