<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/base.jsp"%>
<!DOCTYPE Html>
<html>
 <head>
  <%@ include file="../common/bootstrap.jsp"%>
  <link rel="stylesheet" href="${g.domain}/resource/webuploader-0.1.5/webuploader.css" />
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
          <li>多媒体</li>
          <li class="active">上传附件</li>
        </ol>
         <div class="panel panel-default">
           <div class="panel-heading"><span class="icon icon-upload-alt icon-large"></span>内容</div>
           <div class="panel-body">
             <div id="uploader">
               <div class="upload-list">
                 <div class="placeholder">
                   <div id="picker"></div>
                   <p>或将文件拖到这里，单次最多可选300个</p>
                 </div>
               </div>
               <div class="tip">最大上传文件大小：2MB.</div>
               <div id="status-bar" style="display: none;">
                <div class="info"></div>
                <div class="btns">
                  <div id="append-picker"></div>
                  <div class="uploadBtn" onclick="zblog.upload.upload();">开始上传</div>
                </div>
               </div>
             </div>
           </div>
          </div>

      </div>
    </div>
  </div>
  <b:script src="resource/webuploader-0.1.5/webuploader.min.js"></b:script>
  <b:script src="resource/js/backend/upload.js"></b:script>
 </body>
</html>
