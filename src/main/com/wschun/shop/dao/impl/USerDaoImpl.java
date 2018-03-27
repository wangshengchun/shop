package wschun.shop.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import wschun.shop.bean.User;
import wschun.shop.dao.UserDao;
import wschun.shop.utils.JDBCUtils;

import java.sql.SQLException;

/**
 * Created by wschun on 2018/3/26.
 */
public class USerDaoImpl implements UserDao {
    public boolean checkName(String username) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select count(*) from user where username=?";
        try {
            Long count = queryRunner.query(sql, new ScalarHandler<Long>(), username);
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int register(User user) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "insert into user values(?,?,?,?,?,?,?,?)";
        try {
           return  queryRunner.update(sql, user.getId(), user.getUsername(), user.getPassword(), user.getNickname()
                    , user.getEmail(), user.getTelephone(), user.getActivationcode(), user.getIsactivated());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
