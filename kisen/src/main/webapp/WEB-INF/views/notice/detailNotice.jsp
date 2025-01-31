﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="공지사항" name="title"/>
</jsp:include>

<style>
    .btn-main{
        background-color: #C8A9C8;
        color: white;
        font-weight: bold;
    }
    .notice{
        height: 600px;
        width: 100%;
    }
</style>

<div class="container">
    <h5 class="mt-5" style="font-weight: bold;">| NOTICE</h5>
    <hr>
    <table class="table">
        <thead>
            <tr>
                <th>SUBJECT</th>
                <th> ${notice.noticeTitle }</th>
            </tr>
            <tr>
                <th>WRITER</th>
                <th>${notice.noticeWriter }</th>
            </tr>
            <tr>
                <th>DATE</th>
                <th>${notice.uploadDate }</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td colspan="2" class="text-center">
                	${notice.noticeContent}
                	<c:if test="${not empty notice.noticeImg.renamedFilename}">
		        	<img src="<c:url value='/resources/upload/notice/${notice.noticeImg.renamedFilename}'/>" class="card-img"  style="height: 100%">
                	</c:if>
                </td>
            </tr>
        </tbody>
    </table>
    <hr>
    <button type="button" class="btn btn-light" onclick="list();"> 목록</button>
    <hr>
    <div>
        <table class="table">
            <thead>
                <tr>
                    <td>다음글</td>
                    <td> <a href="${pageContext.request.contextPath}/notice/detail/${notice.nextNo}">${notice.nextTitle} </a></td>
                </tr>
                <tr>
                    <td>이전글</td>
                    <td> <a href="${pageContext.request.contextPath}/notice/detail/${notice.preNo}">${notice.preTitle} </a></td>
                </tr>
        </table>
    </div>
</div>

<script>
function list(){
	location.href=`${pageContext.request.contextPath}/notice`
}
function noticeUpdate(event){
	const target = event.target;
	const noticeNo = target.dataset.no;
	location.href=`${pageContext.request.contextPath}/admin/adminNoticeUpdate/`+noticeNo
}
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
