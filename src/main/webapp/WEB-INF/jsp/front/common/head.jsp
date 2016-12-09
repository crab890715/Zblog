<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="${g.description}" />
<meta name="keywords" content="${g.keywords}" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<title>${ptitle}${ptitle!=null?' | ':''}${g.title}</title>
<link rel="icon" href="${g.domain}/resource/img/favicon.ico"
	type="image/x-icon" />
<link rel="alternate" type="application/rss+xml" href="${g.domain}/feed"
	title="${g.title}" />
<link rel="EditURI" type="application/rsd+xml" title="RSD"
	href="${g.domain}/xmlrpc/rsd" />
<link rel="wlwmanifest" type="application/wlwmanifest+xml"
	href="${g.domain}/xmlrpc/wlwmanifest" />
<b:css href="resource/css/style.css"></b:css>
<b:css href="resource/css/responsive.css"
	media="screen and (max-width:770px)"></b:css>
<b:script src="resource/js/jquery-1.9.1.min.js"></b:script>
<!--[if IE 6]>
<b:script src="resource/js/ie6.js"></b:script>
<![endif]-->
