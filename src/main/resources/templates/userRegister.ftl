<#import "/spring.ftl" as spring/>

<html>
<head>
    <link rel='stylesheet' type="text/css" href="/static/css/common.css">
</head>

<body>
<div id="header">
    <H2>
        회원 등록 페이지입니다.
    </H2>
</div>

<div id="content">
    <fieldset>
        <legend>회원 등록</legend>
        <form id="userForm" name="user" action="/users" method="post">
            name : <input type="text" id="name" name="name"/>	<br/>
            <label id="nameError">
                <#if nameError?has_content>
                    ${nameError}
                </#if>
            </label> <br/>
            birthday : <input type="date" id="birthday" name="birthday"/>	<br/>
            <label id="birthdayError">
                <#if birthdayError?has_content>
                    ${birthdayError}
                </#if>
            </label> <br/>
            <input type="submit" value="저장"/>
        </form>
    </fieldset>
</div>
</body>
</html>