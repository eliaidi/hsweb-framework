package org.hsweb.web.dao.user;

import org.hsweb.web.dao.GenericMapper;
import org.hsweb.web.bean.po.user.User;

/**
 * 后台管理用户数据映射接口
 */
public interface UserMapper extends GenericMapper<User, String> {
    User selectByUserName(String userName);

    void updatePassword(User user);
}
