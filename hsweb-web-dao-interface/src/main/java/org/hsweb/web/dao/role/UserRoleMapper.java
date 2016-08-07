package org.hsweb.web.dao.role;

import org.hsweb.web.dao.GenericMapper;
import org.hsweb.web.bean.po.role.UserRole;

import java.util.List;

/**
 * 后台管理用户角色绑定数据映射接口
 */
public interface UserRoleMapper extends GenericMapper<UserRole, String> {
    List<UserRole> selectByUserId(String userId);

    int deleteByUserId(String userId);
}
