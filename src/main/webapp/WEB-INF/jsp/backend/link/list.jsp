<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/base.jsp"%>
<!DOCTYPE Html>
<html>
 <head>
   <%@ include file="../common/bootstrap.jsp"%>
 </head>
 <body style="margin-top: 50px;">
  <%@ include file="../common/navbar.jsp"%>
  <div class="container-fluid">
    <div class="row">
      <div class="col-sm-3 col-md-2" id="sidebar" style="padding: 0;">
         <%@ include file="../common/sidebar.jsp"%>
      </div>
      <div class="col-sm-9 col-md-10">
        <ol class="breadcrumb header">
          <li><span class="icon glyphicon glyphicon-home"></span>主菜单</li>
          <li class="active">链接</li>
        </ol>
        <div class="panel panel-default">
          <div class="panel-heading"><span class="icon glyphicon glyphicon-list"></span>链接列表</div>
          <div class="panel-body">
           <table id="post-table" class="table table-striped list-table">
              <thead><tr>
                 <th>名称</th>
                 <th>站点</th>
                 <th>可见性</th>
                 <th>创建时间</th>
                 <th class="center">操作</th>
               </tr></thead>
              <tbody>
               <c:forEach items="${page.content}" var="link">
                 <tr><td>${link.name}</td><td><a target="_blank" href='${link.url}'>${z:getDomainLink(link.url)}</a></td>
                     <td>${link.visible?"是":"否"}</td><td><fmt:formatDate value="${link.createTime}" pattern="yyyy-MM-dd"/></td>
                     <td class="center"><span class="icon glyphicon glyphicon-pencil pointer" onclick="zblog.link.edit('${link.id}')"></span>
                       <span class="glyphicon glyphicon-trash pointer" onclick="zblog.link.remove('${link.id}')"></span></td></tr>
               </c:forEach>
              </tbody>
            </table>
            <div class="row-fulid">
              <div class="col-sm-6 col-md-6">
                <div class="page-info">第 ${page.pageIndex}页, 共 ${page.totalPage}页, ${page.totalCount} 项</div>
              </div>
              <div class="col-sm-6 col-md-6">
                <div id="pager">
                <%@ include file="../common/pagination.jsp"%>
                </div>
              </div>
            </div>
         </div>
       </div>
      </div>
    </div>
  </div>
  <b:script src="resource/js/backend/admin.link.js"></b:script>
 </body>
</html>
