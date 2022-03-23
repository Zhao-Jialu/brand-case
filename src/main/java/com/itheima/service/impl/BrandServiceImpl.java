package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    //创建sqlsessionfactory工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public List<Brand> selectAll() {
        //获取sqlsession对象
        SqlSession sqlSession = factory.openSession();
        //获取brandmapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        List<Brand> brands = mapper.selectAll();
        //释放资源
        sqlSession.close();
        return brands;
    }

    @Override
    public void add(Brand brand) {
        //获取sqlsession对象
        SqlSession sqlSession = factory.openSession();
        //获取brandmapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        mapper.add(brand);
        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        //获取sqlsession对象
        SqlSession sqlSession = factory.openSession();
        //获取brandmapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        mapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();

    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        //获取sqlsession对象
        SqlSession sqlSession = factory.openSession();
        //获取brandmapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //计算开始索引
        int begin = (currentPage-1)*pageSize;
        //计算查询条目数
        int size = pageSize;

        //查询当前页数据
        List<Brand> rows = mapper.selectByPage(begin, size);

        //查询总记录数
        int totalCount = mapper.selectTotalCount();

        //封装pagebean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        sqlSession.close();
        return pageBean;
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        //获取sqlsession对象
        SqlSession sqlSession = factory.openSession();
        //获取brandmapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //计算开始索引
        int begin = (currentPage-1)*pageSize;
        //计算查询条目数
        int size = pageSize;

        //处理brand的条件，模糊表达式
        String brandName = brand.getBrandName();
        if(brandName != null && brandName.length() > 0){
                brand.setBrandName("%"+brandName+"%");
        }
        String companyName = brand.getCompanyName();
        if(companyName != null && companyName.length() > 0){
            brand.setCompanyName("%"+companyName+"%");
        }

        //查询当前页数据
        List<Brand> rows = mapper.selectByPageAndCondition(begin, size,brand);

        //查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(brand);

        //封装pagebean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        sqlSession.close();
        return pageBean;
    }

    @Override
    public void deleteById(int id) {
        //获取sqlsession对象
        SqlSession sqlSession = factory.openSession();
        //获取brandmapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        mapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void update(Brand brand) {
        //获取sqlsession对象
        SqlSession sqlSession = factory.openSession();
        //获取brandmapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        mapper.update(brand);
        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }
}
