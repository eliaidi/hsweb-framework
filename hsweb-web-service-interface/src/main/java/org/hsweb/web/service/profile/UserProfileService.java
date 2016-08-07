package org.hsweb.web.service.profile;

import org.hsweb.web.bean.common.QueryParam;
import org.hsweb.web.bean.po.profile.UserProfile;
import org.hsweb.web.service.GenericService;

public interface UserProfileService extends GenericService<UserProfile, String> {

    default UserProfile selectByUserIdAndType(String userId, String type) {
        return selectSingle(QueryParam.build().where("userId", userId).and("type", type));
    }
}
