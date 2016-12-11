<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="${g.description}" />
<meta name="keywords" content="${g.keywords}" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<title>${ptitle}${ptitle!=null?' | ':''}${g.title}</title>
<b:link rel="icon" href="resource/img/favicon.ico"
	type="image/x-icon"></b:link>
<b:link rel="alternate" type="application/rss+xml" href="feed"
	title="${g.title}"></b:link>
<b:link rel="EditURI" type="application/rsd+xml" title="RSD"
	href="xmlrpc/rsd"></b:link>
<b:link rel="wlwmanifest" type="application/wlwmanifest+xml"
	href="xmlrpc/wlwmanifest" ></b:link>
<b:link href="resource/css/style.css"></b:link>
<b:link href="resource/css/responsive.css"
	media="screen and (max-width:770px)"></b:link>
<b:script src="resource/js/jquery-1.9.1.min.js"></b:script>
<!--[if IE 6]>
<b:script src="resource/js/ie6.js"></b:script>
<![endif]-->
