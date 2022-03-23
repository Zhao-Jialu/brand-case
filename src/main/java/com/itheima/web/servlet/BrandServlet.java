package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
    private BrandService brandService = new BrandServiceImpl();
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //调用service查询
        List<Brand> brands = brandService.selectAll();

        //转为JSON
        String jsonString = JSON.toJSONString(brands);

        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //接收品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        //调用service进行添加
        brandService.add(brand);

        //响应成功的标识
        response.getWriter().write("success");
    }

    /**
     * 批量删除
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //接收数据【1,2,3】
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为int数组
        int[] ids = JSON.parseObject(params, int[].class);

        //调用service进行添加
        brandService.deleteByIds(ids);

        //响应成功的标识
        response.getWriter().write("success");
    }

    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       //接收当前页码和每页展示条数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //调用service进行查询
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);
        //转为JSON
        String jsonString = JSON.toJSONString(pageBean);

        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 分页条件查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //接收当前页码和每页展示条数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //获取条件查询对象
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为brand
        Brand brand = JSON.parseObject(params, Brand.class);
        //调用service进行查询
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage, pageSize,brand);
        //转为JSON
        String jsonString = JSON.toJSONString(pageBean);

        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 根据id删除
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //接收数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为int数组
        int id = JSON.parseObject(params, int.class);

        //调用service进行添加
        brandService.deleteById(id);

        //响应成功的标识
        response.getWriter().write("success");
    }
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //接收品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        //调用service进行添加
        brandService.update(brand);

        //响应成功的标识
        response.getWriter().write("success");
    }
}
