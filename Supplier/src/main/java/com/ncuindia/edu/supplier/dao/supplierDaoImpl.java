package com.ncuindia.edu.supplier.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.ncuindia.edu.supplier.model.Supplier;

@Repository
public class supplierDaoImpl implements supplierDao {

    private final JdbcTemplate jdbcTemplate;

    public supplierDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //to map the query output to model file
    @SuppressWarnings("Convert2Lambda")
    public RowMapper<Supplier> productRowMapper = new RowMapper<Supplier>() {
        @Override
        public Supplier mapRow(ResultSet rs, int rowNum) throws SQLException{
            return new Supplier(
                rs.getInt("sid"),
                rs.getString("name"),
                rs.getString("contact"),
                rs.getString("email")
            );
        }
    };

    @Override
    public List<Supplier> getAllSuppliers() {
        String SQL="Select * from supplier";
        return jdbcTemplate.query(SQL, productRowMapper);
    }

    @Override
    public Supplier getSupplierById(int sid) {
        String SQL="Select * from supplier where sid=?";
        return jdbcTemplate.queryForObject(SQL, productRowMapper, sid);
    }

    @Override
    public String addSupplier(Supplier supplier) {
        String SQL="Insert into supplier(sid, name, contact, email) values (?,?,?,?)";
        jdbcTemplate.update(SQL, supplier.getSid(), supplier.getName(), supplier.getcontact(), supplier.getEmail());
        return "Supplier Added Successfully";
    }

    @Override
    public String updateProduct(Supplier supplier, int sid) {
        String SQL="Update supplier set name=?, contact=?, email=? where sid=?";
        jdbcTemplate.update(SQL, supplier.getName(), supplier.getcontact(), supplier.getEmail(), supplier.getSid());
        return "Supplier Updated Successfully";
    }

    @Override
    public String deleteSupplier(int sid) {
        String SQL="Delete from supplier where sid=?";
        jdbcTemplate.update(SQL, sid);
        return "Supplier Deleted Successfully";
    }

}
