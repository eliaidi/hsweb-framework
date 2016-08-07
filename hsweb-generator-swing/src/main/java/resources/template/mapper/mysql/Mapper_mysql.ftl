<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://www.mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${packageName}.dao.${module}.${beanName}Mapper">
    <resultMap id="${beanName}ResultMap" type="${beanName}" >
        <id property="u_id" column="u_id" javaType="string" jdbcType="VARCHAR" />
    <#list  tableMeta.fields as field >
        <result property="${field.name}" column="${field.name}" javaType="${field.javaType.getName()}" jdbcType="${utils.getJdbcType(field.javaType)}" />
    </#list>
    </resultMap>

    <!--字段配置-->
    <sql id="fieldConfig">
        <bind name="$fieldsType"
              value="${r'#{'}<#list  tableMeta.fields as field ><#if field_index!=0>,</#if>${'\''+field.name+'\':\''+field.javaType.getSimpleName()?lower_case+'\''}</#list>${r'}'}"/>
        <bind name="$fields" value="$fieldsType.keySet()"/>
    </sql>

    <!--表名-->
    <sql id="tableName">
        <bind name="$tableName" value="'${table\.name}'"/>
    </sql>

    <insert id="insert" parameterType="${beanName}" useGeneratedKeys="true" keyProperty="u_id" keyColumn="U_ID">
        INSERT INTO ${table\.name!beanName}
        (<#list tableMeta.fields as field ><#if field_index!=0>,</#if>${field.name}</#list>)
        VALUES
        (<#list  tableMeta.fields as field ><#if field_index!=0>,</#if>${r'#{'+field.name+r',jdbcType='+utils.getJdbcType(field.javaType)+'}'}</#list>)
    </insert>

    <delete id="delete" parameterType="${beanName}" >
        DELETE FROM ${table\.name!beanName} WHERE u_id=${r'#{u_id}'}
    </delete>

    <update id="update" parameterType="${beanName}" >
        UPDATE ${table\.name!beanName}
        <set>
        <#list  tableMeta.fields as field >
            <#if !field.canUpdate>
                <if test="${field.name} != null">
                ${field.name}=${r'#{'+field.name+r',jdbcType='+utils.getJdbcType(field.javaType)+'}'},
                </if>
            </#if>
        </#list>
        </set>
        WHERE u_id=${r'#{u_id}'}
    </update>

    <select id="selectByPk" parameterType="string" resultMap="${beanName}ResultMap">
        SELECT * FROM ${table\.name!beanName} WHERE u_id=${r'#{u_id}'}
    </select>

    <select id="select" parameterType="map" resultMap="${beanName}ResultMap">
        <include refid="fieldConfig"/>
        <include refid="tableName"/>
        <include refid="BasicMapper.selectSql"/>
    </select>

    <select id="total" parameterType="map" resultType="int">
        <include refid="fieldConfig"/>
        <include refid="tableName"/>
        <include refid="BasicMapper.totalSql"/>
    </select>
</mapper>