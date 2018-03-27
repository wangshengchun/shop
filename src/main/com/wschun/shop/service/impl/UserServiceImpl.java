package wschun.shop.service.impl;

import wschun.shop.bean.User;
import wschun.shop.dao.UserDao;
import wschun.shop.dao.impl.USerDaoImpl;
import wschun.shop.service.UserService;
import wschun.shop.utils.MD5Utils;

/**
 * Created by wschun on 2018/3/26.
 */
public class UserServiceImpl implements UserService {
    public int register(User user) {
        UserDao userDao = new USerDaoImpl();
        boolean exists = userDao.checkName(user.getUsername());
        if (exists) {
            return 2;
        }
        String str2MD5 = MD5Utils.str2MD5(user.getPassword());
        user.setPassword(str2MD5);
        return userDao.register(user);


    }
}
