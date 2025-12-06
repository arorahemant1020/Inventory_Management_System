package com.ncuindia.edu.product.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ncuindia.edu.product.model.Product;

@Repository
public class productDaoImpl implements productDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public productDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //to map the query output to model file
    @SuppressWarnings("Convert2Lambda")
    public RowMapper<Product> productRowMapper = new RowMapper<Product>() {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException{
            return new Product(
                rs.getInt("pid"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getInt("stock"),
                rs.getInt("sid")
            );
        }
    };

    @Override
    public List<Product> getAllProducts() {
        String SQL="Select * from product";
        return jdbcTemplate.query(SQL, productRowMapper);
    }

    @Override
    public Product getProductById(int pid) {
        String SQL="Select * from product where pid=?";
        return jdbcTemplate.queryForObject(SQL, productRowMapper, pid);
    }

    @Override
    public String addProduct(Product product) {
        String SQL="Insert into product(name, price, stock, sid)" + "values (?,?,?,?) RETURNING pid";
        // jdbcTemplate.update(SQL, Integer.class, product.getName(), product.getPrice(), product.getStock(), product.getSid());
        Integer newPid = jdbcTemplate.queryForObject(
        SQL,
        Integer.class,
        product.getName(),
        product.getPrice(),
        product.getStock(),
        product.getSid()
    );

    product.setPid(newPid);
        return "Product Added Successfully";
    }

    @Override
    public String updateProduct(Product product, int pid) {
        String SQL;
        SQL = "Update product set name=?, price=?, stock=?, sid=? where pid=?";
        jdbcTemplate.update(SQL, product.getName(), product.getPrice(), product.getStock(), product.getSid(), pid);
        return "Product Updated Successfully";
    }

    @Override
    public String deleteProduct(int pid) {
        String SQL="Delete from product where pid=?";
        jdbcTemplate.update(SQL, pid);
        return "Product Deleted Successfully";
    }
}
