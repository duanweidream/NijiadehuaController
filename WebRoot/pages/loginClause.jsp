<%@page import="com.wooboo.dsp.system.util.Config"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="/">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>蜂巢精销运营支撑系统<%=Config.getValue("version")%></title>
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
<style type="text/css">
.bodycl {
	margin: 0;
	padding: 0;
	font-family: BlinkMacSystemFont, "Helvetica", "lucida Grande",
		"PingFang SC", "SCHeiti", "Microsoft YaHei";
	background: #E7F1F4;
}
.frame_cntWrap {
	width: 1080px;
	margin: 24px auto 37px;
	background-color: #FFFFFF;
	overflow: hidden;
	border-radius: 4px;
	box-shadow: 0 1px 2px 0px rgba(0, 0, 0, 0.15);
}
.frame_cnt {
	padding: 40px 60px;
	font-size: 14px;
	border-top: 1px solid #CACACA;
	line-height: 22px;
}
.F_index_ctit {
	background: url("public_img/F_index_ctit.gif") no-repeat scroll 0 0
		rgba(0, 0, 0, 0);
	color: #2B85CB;
	height: 21px;
	padding: 0px 0 0 0px;
	width: 134px;
}
.F_main_tit {
	background: url("public_img/F_coin_tit.gif") no-repeat scroll left 9px
		rgba(0, 0, 0, 0);
	font-size: 18px;
	margin-left: 5px;
	padding: 7px 0 3px 23px;
}
.frame_foot {
	text-align: center;
	padding-bottom: 23px;
	color: #9E9E9E;
	font-size: 12px;
}
.frame_can {
	width: 1080px;
	margin: 0 auto;
	overflow: hidden;
}
.frame_foot_copyright {
	line-height: 1.08;
	margin: 0;
}
.frame_head {
	background-color: #335883;
	min-width: 1080px;
}
.frame_logo {
	float: left;
	background: url(//rescdn.qqmail.com/node/ww/wwmng/style/images/independent/error/WeworkLogo$63ebc024.png) no-repeat left top;
	background-size: 89px 23px;
	width: 89px;
	height: 23px;
	margin-top: 4px;
}
.frame_operation {
	float: right;
	height: 31px;
	line-height: 31px;
	font-size: 12px;
}
a {
	text-decoration: none;
}
</style>
</head>
<!-- END HEAD -->
<body class="bodycl">
<div style="display: block;">
	<div></div>
	<div class="frame_cntWrap">
		<p class="F_main_tit">信息安全规则公示</p>
		<div class="frame_cnt">
			<div class="F_index_ctit">信息安全规则</div>
			<div>
				<h2>一、遵守国家法律和行政法规，不得利用国际联网危害国家安全、泄露国家秘密，不得侵犯国家的、社会的、集体的利益和公民的合法权益，不得从事违法犯罪活动。</h2>
				<h2>二、不得利用本网站平台、系统制作、发布和传播下列违法信息：</h2>
				<strong>（一）</strong>煽动抗拒、破坏宪法和法律、行政法规实施的；<br> <strong>（二）</strong>煽动颠覆国家政权，推翻社会主义制度的；<br>
				<strong>（三）</strong>煽动分裂国家、破坏国家统一的；<br> <strong>（四）</strong>煽动民族仇恨、民族歧视，破坏民族团结的；<br>
				<strong>（五）</strong>捏造或者歪曲事实，散布谣言，扰乱社会秩序的；<br> <strong>（六）</strong>宣扬封建迷信、淫秽、色情、赌博、暴力、凶杀、恐怖，教唆犯罪的；<br>
				<strong>（七）</strong>公然侮辱他人或者捏造事实诽谤他人的；<br> <strong>（八）</strong>损害国家机关信誉的；<br>
				<strong>（九）</strong>其他违反宪法和法律、行政法规的。<br>
	
				<h2>三、不得利用网站平台、系统从事下列危害计算机信息网络安全的活动：</h2>
				<strong>（一）</strong>未经允许，进入计算机信息网络或者使用计算机信息网络资源的；<br> <strong>（二）</strong>未经允许，对计算机信息网络功能进行删除、修改或者增加的；<br>
				<strong>（三）</strong>未经允许，对计算机信息网络中存储、处理或者传输的数据和应用程序进行删除、修改或者增加的；<br>
				<strong>（四）</strong>故意制作、传播计算机病毒等破坏程序的；<br> <strong>（五）</strong>其他危害计算机信息网络安全的。<br>
			</div>
		</div>
	</div>
	<div class="frame_foot">
		<div class="frame_can">
			<p class="frame_foot_copyright"> 2017 &copy; HiveData.Express. version <%=Config.getValue("version")%> </p>
		</div>
	</div>
</div>
<!-- <div class="footer">© 广东移动大数据商业平台 粤ICP备16075807号-2 广东移动版权所有</div> -->
</body>
</html>