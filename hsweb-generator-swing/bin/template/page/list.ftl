<#list tableMeta.fields as field>
 <#if field.attr('search_cdt')>
${field.comment}<input name="${field.name}" />
 </#if>
</#list>
