
<%@ page import="it.prova.model.Editore" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'editore.label', default: 'Editore')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><st:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></st:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
           
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn controller="editore" property="id" title="${message(code: 'editore.id.label', default: 'Id')}" />
                        
                            <th><g:message code="editore.autori.label" default="Autori" /></th>
                   	    
                            <g:sortableColumn controller="editore" property="nome" title="${message(code: 'editore.nome.label', default: 'Nome')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${editoreInstanceList}" status="i" var="editoreInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><st:link controller="editore" action="show" id="${editoreInstance.id}">${fieldValue(bean: editoreInstance, field: "id")}</st:link></td>
                        
                            <td>${fieldValue(bean: editoreInstance, field: "autori")}</td>
                        
                            <td>${fieldValue(bean: editoreInstance, field: "nome")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${editoreInstanceTotal}"  action="list.dispatch"/>
            </div>
        </div>
    </body>
</html>
