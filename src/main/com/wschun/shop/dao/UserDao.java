package wschun.shop.dao;

import wschun.shop.bean.User;

/**
 * Created by wschun on 2018/3/26.
 */
public interface UserDao {
    boolean checkName(String username);

    int register(User user);
}
