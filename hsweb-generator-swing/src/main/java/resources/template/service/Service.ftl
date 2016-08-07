package ${packageName}.service.${module};

import org.hsweb.web.service.GenericService;
import ${packageName}.po.${module}.${beanName};
import ${packageName}.dao.${module}.${beanName}Mapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
* ${beanRemark!''}服务类
* Created by generator ${create_time!''}
*/
@Service
public class ${beanName}Service extends GenericService<${beanName},String> {

    //默认数据映射接口
    @Resource
    protected ${beanName}Mapper ${beanName?uncap_first}Mapper;

    @Override
    protected ${beanName}Mapper getMapper(){
        return this.${beanName?uncap_first}Mapper;
    }

}
