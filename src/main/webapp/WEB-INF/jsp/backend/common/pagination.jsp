<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<ul class="pagination">
  <page:page model="${page}" pageUrl="" showPage="8">
    <page:prev>
       <li><a href="${pageUrl}"><span aria-hidden="true">&laquo;</span></a></li>
    </page:prev>
    <page:pager>
      <c:choose>
        <c:when test="${pageNumber==page.pageIndex}">
          <li><a href="#">${pageNumber}</a></li>
        </c:when>
        <c:otherwise>
          <li><a href="${pageUrl}">${pageNumber}</a></li>
        </c:otherwise>
      </c:choose>
    </page:pager>
    <page:next>
      <li><a href="${pageUrl}"><span aria-hidden="true">&raquo;</span></a></li>
    </page:next>
  </page:page>
</ul>