<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<%@include file="../includes/header.jsp"%>
<link rel="stylesheet" href="/resources/css/attach.css">


<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Modify</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Modify</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<form role="form" action="/board/modify" method="post">
				
				<input type='hidden' name ='pageNum' value='<c:out value="${cri.pageNum }"/>'>
				<input type='hidden' name ='amount' value='<c:out value="${cri.amount }"/>'>
				<input type='hidden' name ='type' value='<c:out value="${cri.type}"/>'>
				<input type='hidden' name ='keyword' value='<c:out value="${cri.keyword}"/>'>
			

					<div class="form-group">
						<label>Bno</label> <input class="form-control" name="bno"
							value='<c:out value="${board.bno}"/>' readonly="readonly">
					</div>

					<div class="form-group">
						<label>Title</label> <input class="form-control" name="title"
							value='<c:out value="${board.title }"/>'>
					</div>

					<div class="form-group">
						<label>Text Area</label>
						<textarea class="form-control" rows="3" name="content"><c:out
								value="${board.content }" /> </textarea>

					</div>

					<div class="form-group">
						<label>Writer</label> <input class="form-control" name="writer"
							value='<c:out value="${board.writer }"/>' readonly="readonly">
					</div>
						

					
					<sec:authentication property="principal" var="pinfo" />
					<sec:authorize access="isAuthenticated()">
						<c:if test="${pinfo.username eq board.writer }">
							<button type="submit" data-oper='modify' class="btn btn-default">Modify</button>
							<button type="submit" data-oper='remove' class="btn btn-danger">Remove</button>
						</c:if>
					</sec:authorize>

					<button type="submit" data-oper='list' class="btn btn-info">List</button>
				</form>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>

<div class='bigPictureWrapper'>
	<div class='bigPicture'>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class = "panel-heading">첨부 파일</div>
			
			<div class="panel-body">
				<div class="form-group uploadDiv">
					<input type="file" name = 'uploadFile' multiple="multiple">
				</div>
							
				<div class='uploadResult'>
					<ul>
						<!--  ajax로 작성 -->
					</ul>	
				</div>	
			</div>
		</div>
	</div>
</div>


	<script>
		$(document).ready(function() {
				
			
			var formObj = $("form");
			$('button').on("click",function(e) {
				e.preventDefault();
				var operation = $(this).data("oper");
				
				if(operation ==='remove') {
					formObj.attr("action","/board/remove");
				}else if(operation === 'list') {
					
					formObj.attr("action","/board/list").attr("method","get");
					var pageNumTag = $("input[name='pageNum']").clone();
					var amountTag =  $("input[name='amount']").clone();
					var typeTag = $("input[name='type']").clone();
					var keywordTag = $("input[name='keyword']").clone();
					
					formObj.empty();
					formObj.append(pageNumTag);
					formObj.append(amountTag);
					formObj.append(typeTag);
					formObj.append(keywordTag);
				}else if(operation === 'modify') {
					console.log("submit clicked");
					
					var str ="";
					$(".uploadResult ul li").each(function(i,obj){
						
						var jobj = $(obj);
						console.dir(jobj);
						
						str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
						str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
						str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
						str += "<input type='hidden' name='attachList["+i+"].fileType' value='"+jobj.data("type")+"'>";
						
						
					});
					formObj.append(str).submit();
					return;
				}
				formObj.submit();
			});
		});
	</script>
	<script>
	
	
	$(document).ready(function(){
		(function(){
			var bno = '<c:out value="${board.bno}"/>';
			$.getJSON("/board/getAttachList", {bno:bno},function(arr){
			console.log(arr);
			var str = "";
				
				$(arr).each(function(i, attach) {
					if(attach.fileType) {
						
						var fileCallPath = encodeURIComponent(attach.uploadPath + "/s_" + attach.uuid + "_" + attach.fileName);					
									
						str += "<li data-path='"+attach.uploadPath+"'";
						str += "data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+ "' data-type='"+attach.fileType+"'"
						str + "><div>";
						str += "<span>" + attach.fileName + "</span>";
						str += "<button type='button' data-file =\'"+fileCallPath+"\' data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
						str += "<img src='/display?fileName="+fileCallPath+"'>";
						str += "</div>";
						str + "</li>";
							
						
					}else {						
						
						str += "<li data-path='"+attach.uploadPath+"'";
						str += "data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+ "'data-type='"+attach.fileType+"'"
						str + "><div>";
						str += "<span>" + attach.fileName + "</span>";
						str += "<button type='button' data-file =\'"+fileCallPath+"\' data-type='file' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
						str += "<img src='/resources/img/attach.png'></a>";
						str += "</div>";
						str + "</li>";								
					}
					
				});
				
				$(".uploadResult ul").html(str);
					
			});
		})();
		
		$(".uploadResult").on("click","button",function(e) {
			if(confirm("삭제하시겠습니까?")) {
				var targetLi = $(this).closest("li");
				targetLi.remove();
			}
			
		})
		var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
		var maxSize = 5242880;
		
		function checkExtension(fileName, fileSize) {
			if(fileSize >= maxSize) {
				alert("파일 사이즈 초과");
				return false;
			}
			if(regex.test(fileName)) {
				alert("해당 종류의 파일은 업로드할 수 없습니다");
				return false;
			}
			return true;
		}
		
		
		var csrfHeaderName = "${_csrf.headerName}";
		var csrfTokenValue = "${_csrf.token}";
		$("input[type='file']").change(function(e) {
			var formData = new FormData();
			var inputFile = $("input[name='uploadFile']");
			var files = inputFile[0].files;
			console.log(files);
			
			for(var i=0; i<files.length; i++) {
				if(!checkExtension(files[i].name, files[i].size)) {
					return false;
				}
				formData.append("uploadFile", files[i]);
			}
			
			$.ajax({
				url: '/uploadAjaxAction',
				processData: false,
				contentType: false,
				data: formData,
				type: 'POST',
				beforeSend: function(xhr){
					xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
				},
				success: function(result) {
					console.log(result);
					showUploadResult(result);
					
				}
			})
		});
		
		function showUploadResult(uploadResultArr) {
			
			if(!uploadResultArr || uploadResultArr.length == 0){return;}
			var uploadUL = $(".uploadResult ul");
			
			var str = "";
			
			$(uploadResultArr).each(function(i, obj) {
				if(obj.image) {
					
					var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);					
								
					str += "<li data-path='"+obj.uploadPath+"'";
					str += "data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+ "' data-type='"+obj.image+"'"
					str + "><div>";
					str += "<span>" + obj.fileName + "</span>";
					str += "<button type='button' data-file =\'"+fileCallPath+"\' data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
					str += "<img src='/display?fileName="+fileCallPath+"'>";
					str += "</div>";
					str + "</li>";
						
					
				}else {						
					var fileCallPath = encodeURIComponent(obj.uploadPath +"/" + obj.uuid + "_" + obj.fileName);
					var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
					str += "<li data-path='"+obj.uploadPath+"'";
					str += "data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+ "'data-type='"+obj.image+"'"
					str + "><div>";
					str += "<span>" + obj.fileName + "</span>";
					str += "<button type='button' data-file =\'"+fileCallPath+"\' data-type='file' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
					str += "<img src='/resources/img/attach.png'></a>";
					str += "</div>";
					str + "</li>";								
				}
				
			});
			
			uploadUL.append(str);
		}
		
	});	
	
	
	
	</script>
	
	

<%@ include file="../includes/footer.jsp"%>