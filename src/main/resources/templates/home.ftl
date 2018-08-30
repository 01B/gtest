<#import "/spring.ftl" as spring/>

<html>
<head>
    <#--<link rel='stylesheet' type="text/css" href="/static/css/common.css">-->
        <link rel='stylesheet' type="text/css" href="/gamehubtest/static/css/common.css">
</head>
<body>
<div id="header">
    <H2>
        회원 목록
    </H2>
</div>
<div id="content">

    <#if users.content?has_content>
        <table class="datatable">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>생년월일</th>
                <th>가입일</th>
            </tr>
                <#list users.content as user>
                <tr>
                    <td>${user.id}</td>
                    <td>${user.firstName} ${user.middleName} ${user.lastName}</td>
                    <td>${user.birthday}</td>
                    <td>${user.joinDate}</td>
                </tr>
                </#list>
        </table>
        <#if !users.first>
            <a href="?page=${users.number-1}">&larr; next page</a>
        </#if>
        <#if !users.last>
        <a href="?page=${users.number+1}">previous page &rarr;</a>
        </#if>
    <#else>
        등록된 회원이 없습니다.
    </#if>
    <div>
        <input type="button" value="회원등록" onclick="location.href='/users/register'"/>
    </div>
</div>
</body>
</html>