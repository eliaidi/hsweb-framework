package ${packageName}.controller.${module};

import ${packageName}.po.${module}.${beanName};
import ${packageName}.service.${module}.${beanName}Service;
import org.hsweb.web.controller.GenericController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

/**
* ${beanRemark!''}控制器，继承自GenericController,使用rest+json
* Created by hsweb-generator ${.now}
*/
@RestController
@RequestMapping(value = "/${beanName?uncap_first}")
public class ${beanName}Controller extends GenericController<${beanName},String> {

    //默认服务类
    @Resource
    private ${beanName}Service ${beanName?uncap_first}Service;

    @Override
    public ${beanName}Service getService(){
        return this.${beanName?uncap_first}Service;
    }

}
