package com.coding404.myweb.product;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public int prodRegist(ProductVO productVO) {
        return productMapper.prodRegist(productVO);
    }

    @Override
    public List<ProductVO> getList(String prodWriter, Criteria cri) {
        return productMapper.getList(prodWriter, cri);
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
    public int getTotal(String prodWriter, Criteria cri) {
        return productMapper.getTotal(prodWriter, cri);
    }
}
