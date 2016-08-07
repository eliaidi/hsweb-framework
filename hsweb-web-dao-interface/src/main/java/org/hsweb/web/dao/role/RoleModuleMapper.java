package org.hsweb.web.dao.role;

import org.hsweb.web.dao.GenericMapper;
import org.hsweb.web.bean.po.role.RoleModule;

import java.util.List;

/**
 * 系统模块角色绑定数据映射接口
 */
public interface RoleModuleMapper extends GenericMapper<RoleModule, String> {
    List<RoleModule> selectByRoleId(String roleId);

    int deleteByRoleId(String roleId);

    int deleteByModuleId(String moduleId);
}
