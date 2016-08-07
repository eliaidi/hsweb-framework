package ${packageName}.po.${beanName};

/**
* ${beanRemark!''}
* Created by hsweb-generator ${.now!''}
*/
public class ${beanName} {
    <#list tableMeta.fields as field>

    //${field.comment!field.name}
    private ${field.javaType.getName()} ${field.name};
    </#list>
    <#list tableMeta.fields as field>

    /**
    * 获取 ${field.comment}
    * @return ${field.javaType.getName()} ${field.comment}
    */
    public ${field.javaType.getName()} ${utils.getGetter(field.name,field.javaType)}(){
        <#if field.javaType.getName()=='java.lang.String'||field.javaType.getName()=='String'>
           if(this.${field.name}==null)
              return "${field.defaultValue!''}";
        </#if>
        return this.${field.name};
    }

    /**
    * 设置 ${field.comment}
    */
    public void ${utils.getSetter(field.name)}(${field.javaType.getName()} ${field.name}){
        this.${field.name}=${field.name};
    }
    </#list>
}
