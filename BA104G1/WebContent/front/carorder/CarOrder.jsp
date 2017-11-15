<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cartype.model.*"%>
<%@ page import="java.util.*"%>



<html>
<head>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<!-- <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script> -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script src="<%=request.getContextPath()%>/front/MDP/jquery-ui.multidatespicker.js"></script><!-- 複數日期選擇器 -->


<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front/bootstrap-3.3.7/dist/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front/MDP/jquery-ui.multidatespicker.css"">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/css/carorder/carorder.css">


<%-- <script src="<%=request.getContextPath()%>/front/jquery-twzipcode-master/jquery.twzipcode.js"></script> <!-- 台灣地址選擇器 --> --%>
<script src="<%=request.getContextPath()%>/front/js/carorder/tw-city-selector.min.js"></script>
<script src="<%=request.getContextPath()%>/front/js/carorder/bootstrap.js"></script><!-- bootstrap3.7 -->
<script src="<%=request.getContextPath()%>/front/js/carorder/carorder.js"></script><!--自定義js-->
<script src="<%=request.getContextPath()%>/front/js/carorder/carousel.js"></script><!-- carousel -->

<jsp:useBean id="cartypeSvc" scope="page" class="com.cartype.model.CarTypeService" />

<style>
.wizard .tab-pane {
    position: relative;
    padding-top: 50px;
    height:50%;
}

a.car {
    position: relative;
    display: block;
    padding: 10px 15px;
}

.bbb {
    display: table-cell;
    width: 1%;
    float: none;
}

.nav-pills > li.active > a, .nav-pills > li.active > a:hover, .nav-pills > li.active > a:focus {
    color: #fff;
    background-color: rgb(253, 182, 3);
}

</style>
<title>有我罩你-派車服務申請</title>
</head>
<body bgcolor='white'>

<%@ include file="/front/navbar.jsp" %> 
	<!-- =================================訂車流程TABS開始================================= -->
	<div class="container">
		<div class="row">
			<section>
				<div class="wizard">
					<div class="wizard-inner">
						<div class="connecting-line"></div>
						<ul class="nav nav-tabs" role="tablist">

							<li role="presentation" id="presentation1"><a href="#step1"
								data-toggle="tab" aria-controls="step1" role="tab"
								title="步驟一 :選擇服務車型"> <span class="round-tab"> <i
										class="glyphicon glyphicon-folder-open"></i>
								</span>
							</a></li>

							<li role="presentation" class="disabled"><a href="#step2"
								data-toggle="tab" aria-controls="step2" role="tab"
								title="步驟二 :選擇服務日期與時段"> <span class="round-tab"> <i
										class="glyphicon glyphicon-pencil"></i>
								</span>
							</a></li>
							<li role="presentation" class="disabled"><a href="#step3"
								data-toggle="tab" aria-controls="step3" role="tab"
								title="步驟三 :選擇往來地點"> <span class="round-tab"> <i
										class="glyphicon glyphicon-picture"></i>
								</span>
							</a></li>

							<li role="presentation" class="disabled"><a href="#complete"
								data-toggle="tab" aria-controls="complete" role="tab"
								title="完成訂單"> <span class="round-tab"> <i
										class="glyphicon glyphicon-ok"></i>
								</span>
							</a></li>
						</ul>
					</div>

					
						<div class="tab-content">
							<div class="tab-pane active" role="tabpanel" id="step1">
								<h3>Step 1</h3>
								<p>選擇您想搭乘的服務車型:</p>
								<!-- =================================動態展示車型選單================================= -->
								<div class="container">
									<div id="myCarousel" class="carousel slide"
										data-ride="carousel" data-interval="false">

										<!-- Wrapper for slides -->
										<div class="carousel-inner">

											<div class="item active">
												<img src="http://placehold.it/1200x400/cccccc/ffffff">
													<div class="carousel-caption">
														<h3>選擇您想要的車型</h3>
														<p>這邊是動態展示現有車型的地方</p>
													</div>
											</div>


											<c:forEach var="cartypeVO" items="${cartypeSvc.all}">

												<div class="item">
													<img class="aaa"
														src="<%=request.getContextPath()%>/cartype/DBG.do?cartypeno=${cartypeVO.cartypeno}">
													<div class="carousel-caption">
														<h3>
															<b>${cartypeVO.cartypename}</b>
														</h3>
														<p>
															<b>${cartypeVO.description}</b>
														</p>
														
														<h3>
															<b>${cartypeVO.rentalrates}新台幣/1小時</b>
														</h3>
													</div>
												</div>
												<!-- End Item -->
											</c:forEach>

										</div>
										<!-- End Carousel Inner -->
										<div class="ulbtn">
										<ul class="nav nav-pills nav-justified">
											<c:forEach var="cartypeVO" items="${cartypeSvc.all}"
												varStatus="i">
												<li class="bbb ulbtn" data-target="#myCarousel"
													data-slide-to="${i.count}"><!-- <label for="ulbtn"> --><a class="car ulbtn" href="#">${cartypeVO.cartypename}</a><!-- </label> -->
												</li>
											</c:forEach>

										</ul>
										</div>
									</div>
									<!-- End Carousel -->
								</div>

								<input type="hidden" class="inputcarname" name="cartypename"
									value="現在選擇的車型">
								<!-- =================================動態展示車型選單================================= -->
								<br>
								<ul class="list-inline pull-right">
									<li><button type="button"
											class="btn btn-primary next-step" id="savebtn">下一步>選擇日期與時段</button></li>
								</ul>
							</div>
							<div class="tab-pane" role="tabpanel" id="step2">
								<h3>Step 2</h3>
								<p>選擇您需要服務的日期與時段:</p><br>
								<!-- =================================日期選擇器================================= -->
								<!-- <label for="from">從</label> <input type="text" id="from"
									name="from"> <label for="to">到</label> <input
									type="text" id="to" name="to"> -->
									選擇服務日期:
									<div class= "addDatePick"><input id="chooseDate"></div><br><br>
									選擇服務時段:
										<div class="radio daypart">
  										<label><input type="radio" name="optradio" value="0" text="早">早上(8~12)</label>
										</div>
										<div class="radio daypart">
										  <label><input type="radio" name="optradio" value="1" text="中">下午(1~5)</label>
										</div>
										<div class="radio daypart">
										  <label><input type="radio" name="optradio" value="2" text="晚">晚上(6~10)</label>
										</div>
										<div class="radio daypart">
										  <label><input type="radio" name="optradio" value="3" text="全天">全天</label>
										</div> 
										<%-- <img src="<%=request.getContextPath()%>/front/image/calendar-series.jpg"> --%>
										
							
								<!-- =================================日期選擇器結束================================= -->
								
								
								<br><br><br>
								<ul class="list-inline pull-right">
									<li><button type="button"
											class="btn btn-default prev-step">回到上一步</button></li>
									<li><button type="button"
											class="btn btn-primary next-step">下一步>選擇接送地點</button></li>
								</ul>
							</div>
							<div class="tab-pane" role="tabpanel" id="step3">
								<h3>Step 3</h3>
								<p>選擇您的上車地點與目的地:</p>
								
								<!-- =================================地址選擇================================= -->
	
										<!-- <div id="twzipcode">
									    </div>
									    <div id="twzipcode2">
									    </div> -->
									    
										    <div class="my-selector-c">
												  <div>
												    <select class="county"></select>
												  </div>
												  <div>
												    <select class="district"></select>
												  </div>
							                      <div>
							                      	<input type="text" name="address" id="address">
							                      </div>			
											</div>
									    
									    
									    
									    
									     
								<!-- =================================地址選擇=================================a -->
								
								
								<ul class="list-inline pull-right">
									<li><button type="button"
											class="btn btn-default prev-step">上一步</button></li>
									<li><button type="button"
											class="btn btn-default next-step">跳過</button></li>
									<li><button type="button"
											class="btn btn-primary btn-info-full next-step">下一步</button></li>
								</ul>
							</div>
							<div class="tab-pane" role="tabpanel" id="complete">
								<h3 style="color:#D68B00">恭喜!</h3>
								<p>您已經完成了訂車流程，請確認下方訂單資訊無誤後送出訂單，</p>
									<p>如有任何疑問請撥打以下客服專線。</p>
									<h2>0979-498988</h2>
									<table id="showFormMsg">
									<tr><th>車型</th><th>日期</th><th>時段</th><th>接送地點</th></tr>
									<tr><td id="inputmsg1"></td><td id="inputmsg2"></td><td id="inputmsg3"></td><td id="inputmsg4"></td></tr>
									</table>
									<form role="form" action="<%=request.getContextPath()%>/cardetail/cardetail.do" method="post">
											<input type="hidden" name="action" value="insert">
											<input type="hidden" name="cartypename" id="cartypefor">
										    <input type="hidden" name="detail_date" id="datefor" 	>
										    <input type="hidden" name="detail_time" id="detail_time" >
										    <input type="hidden" name="detail_time_no" id="daypartfor" >
										    <input type="hidden" name="county" 		id="countyfor" 	>
										    <input type="hidden" name="district" 	id="districtfor">
										    <input type="hidden" name="address" 	id="addressfor" >
										    <input type="hidden" name="passenger_name" 	id="passenger_name" value="黃安">  
										    <input type="hidden" name="passenger_phone" 	id="passenger_phone"  value="09787877878">
										    <input type="hidden" name="arrival_address" 	id="arrival_address"  value="桃園市平鎮區中大路300號">
										    <input type="hidden" name="sendcar_status" 	id="sendcar_status" value="A" >
										    <input type="hidden" name="formCheck" 	id="formCheck" value="${formCheck}">
										    
										    
									<ul class="list-inline pull-right">
									<li><button type="button"
											class="btn btn-default prev-step">上一步</button></li>	
									<li><button type="submit"
											class="btn btn-warning btn-lg btn-info-full submit-form">確認送出訂單</button></li>
								</ul>
								</form>
							</div>
							<div class="clearfix"></div>
						</div>
					
				</div>
			</section>
		</div>
	</div>

							<!-- =================================訂車流程TABS結束================================= -->
				
						
						    <!-- =================================表單資料轉送區=================================== -->
						 
						     <script type="text/javascript">
									     
									    	$(document).ready(function(){
									    	
									    	 new TwCitySelector({
									    		    el: ".my-selector-c",
									    		    elCounty: ".county", // 在 el 裡查找 dom
									    		    elDistrict: ".district", // 在 el 裡查找 dom
									    		  });
											//var adddates = "";
												
						
									  
									    	$('.next-step').click(
													function() {
														$("#inputmsg1").html($('li.active>a').text());
														$("#inputmsg2").html($('#chooseDate').val());
														var txt1 =$('.county').val();
														var txt2 =$('.district').val();
														var txt3 =$('#address').val();
														$("#inputmsg4").html( txt1+ txt2+ txt3);
														
					
														$("#cartypefor").val($('li.active>a').text());
														$("#datefor").val($('#chooseDate').val());
														$("#daypartfor").val($("input[name='optradio']:checked").val());
														$("#detail_time").val($("input[name='optradio']:checked").attr("text"));
														
														
														$("#countyfor").val($('.county').val());
														$("#districtfor").val($('.district').val());
														$("#addressfor").val($('#address').val());
														
													});
											$("input[name='optradio']").change(function(){
												$("#inputmsg3").html($("input[name='optradio']:checked").attr("text"));
											});
											
											
												$(".ulbtn").mouseover(function(){
													$("#presentation1").addClass("active");
												});	
												
												$(".ulbtn").mouseleave(function(){
													$("#presentation1").addClass("active");
												});	
												
												var formCheck = "ok";
										    	var formCheckVal = $("#formCheck").val();
										    	console.log(formCheckVal);
												if(formCheck == formCheckVal){
												swal({
													  position: 'center',
													  type: 'success',
													  title: '您的訂單已經成立',
													  showConfirmButton: false,
													  timer: 3500
													});
												}
														
												 
										    	 
												var datelist = [];
												var datelist2 = []; 
												
												 $("input[name='optradio']").change(function(){
													 var cartypename =  $('li.active>a').text();
													 var cartypeName = cartypename.trim();
														
													 	$.ajax({
															 type: "POST",
															 url: "<%=request.getContextPath()%>/cardetail/cardetail.do",
															 data: creatQueryString($(this).val(), cartypeName),
															 dataType: "json",
															 
															 success: function (data){
																 $("#chooseDate").remove();
																 
																 datelist = data.dayStatusList;
																 dateList2Add();
																 $(".addDatePick").append("<input id='chooseDate'>");
													
																	$('#chooseDate').multiDatesPicker({
															    		minDate: 1,
															    		maxDate: 30, 
															    		maxPicks: 1,
															    		dateFormat: "yy-m-d",
															    		addDisabledDates: datelist2
																	});//多選日期	
																	
																
																	
																	
																/*  $(data.dayStatusList).each(function(index,dayStatusList) {
																	 console.log(dayStatusList)
																 }) */
														     },
												            error: function(){alert("AJAX-class發生錯誤囉!")}
												        })
													})
													
													function dateList2Add(){
										    		 var date = new Date();
												    	 for(i=0;i<datelist.length;i++){
												    		 datelist2[i] = date.setDate(datelist[i]);
												    	 } 
											    	 	};
													function dateList2Clear(){
														datelist2 =0;
													};
													function creatQueryString(detail_time_no, cartypename){
														console.log("detail_time_no:"+detail_time_no+"; cartypename:"+cartypename);
														var queryString= {"action":"getDisableDates", "detail_time_no":detail_time_no, "cartypename":cartypename};
														console.log("list:"+datelist);
														return queryString;
												
									    	 		};
									    	
									    	});
									    </script>
									    <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.5/sweetalert2.all.js"></script><!-- 甜甜的sweetalert2 -->
						    
							<!-- <button type="button" class="btn btn-primary" id="testbtn">測試用按鈕</button> -->
							<%@ include file="/front/footerbar.jsp" %> 
</body>
</html>