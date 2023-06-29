package com.example.SpringBootCrudJdbcTemplate.dao;

import com.example.SpringBootCrudJdbcTemplate.entity.egovUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final String INSERT_USER_QUERY="INSERT INTO egov_user (id,name,gender,mobileNumber,address) values(?,?,?,?,?)";
     private static final String UPDATE_USER_BY_ID_QUERY = "UPDATE egov_user SET name=?, address=?, mobileNumber=?, gender=? WHERE ID=?";
    private static final String GET_USER_BY_ID_QUERY="SELECT * FROM egov_user WHERE ID=?";
    private static final String DELETE_USER_BY_ID_QUERY=" DELETE FROM egov_user WHERE ID =?";
    private static final String GET_USERS_QUERY= "SELECT * FROM egov_user";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void createTableIfNotExists() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS egov_user (" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(255)," +
                "gender VARCHAR(255)," +
                "mobileNumber VARCHAR(255)," +
                "address VARCHAR(255)" +
                ")";
        jdbcTemplate.execute(createTableQuery);
    }

    @Override
    public egovUser saveUser(egovUser egovUser) {
        jdbcTemplate.update(INSERT_USER_QUERY, egovUser.getId(), egovUser.getName(), egovUser.getGender(), egovUser.getMobileNumber(), egovUser.getAddress());
        return egovUser;
    }
 @Override
    public egovUser updateUser(egovUser egovUser) {
        jdbcTemplate.update(
                UPDATE_USER_BY_ID_QUERY,
                egovUser.getName(),
                egovUser.getAddress(),
                egovUser.getMobileNumber(),
                egovUser.getGender(),
                egovUser.getId()
        );
        return egovUser;
    }

    @Override
    public egovUser getbyId(long id) {
        return jdbcTemplate.queryForObject(GET_USER_BY_ID_QUERY,(rs, rowNum) -> {
            return new egovUser( rs.getLong("id"), rs.getString("name" ), rs.getString("gender"), rs.getString("mobileNumber"), rs.getString("address"));
            },id);
    }



    @Override
    public String deletebyId(long id) {
        jdbcTemplate.update(DELETE_USER_BY_ID_QUERY, id);
        return "egovUser got deleted with id " + id;
    }

    @Override
    public List<egovUser> allUsers() {
        return jdbcTemplate.query(GET_USERS_QUERY,(rs, rowNum) -> {
            return new egovUser( rs.getLong("id"), rs.getString("name" ), rs.getString("gender"), rs.getString("mobileNumber"), rs.getString("address"));
        });
    }
}
