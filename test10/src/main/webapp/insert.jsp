<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body class="is-preload">

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Main -->
					<div id="main">

						<!-- Introduction -->
							<section id="intro" class="main">
								<div class="spotlight">
									<div class="content">
										<header class="major">
											<h2>글작성하기</h2>
										</header>
										<form action="insert.do" method="post" enctype="multipart/form-data">
										<table class="alt">
												<tbody>
													<tr>
														<td>글제목</td>
														<td colspan="2"><input type="text" name="title" required></td>
													</tr>
													<tr>
														<td>내용</td>
														<td colspan="2"><input type="text" name="content" required></td>
													</tr>
													<tr>
														<td>작성자</td>
														<td colspan="2"><input type="text" name="writer"  value="${member.name}"required></td>
													</tr>
																		
													<tr>
													
														<td colspan="3" align="right"><input type="submit" class="button primary" value="글등록"> </td>
													</tr>
												</tbody>
										</table>
										<input type="file" name="uploadFile" onchange="setThumbnail(event);" />
										<div id="image_container"></div>
										</form>
										        
										<a href="login.jsp">go to login</a>
									</div>
									<span class="image"><img src="images/pic01.jpg" alt="" /></span>
								</div>
							</section>

					</div>

				

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.scrollex.min.js"></script>
			<script src="assets/js/jquery.scrolly.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>
			<script>
      function setThumbnail(event) {
        var reader = new FileReader();

        reader.onload = function(event) {
          var img = document.createElement("img");
          img.setAttribute("src", event.target.result);
          document.querySelector("div#image_container").appendChild(img);
        };
        reader.readAsDataURL(event.target.files[0]);
      }
    </script>


	</body>
</html>