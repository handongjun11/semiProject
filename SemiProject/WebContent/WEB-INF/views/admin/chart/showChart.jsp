<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,semi.kh.chart.model.vo.Favorite"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/chart.css" />
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@0.5.0"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
 <nav id="high-rank-category">  <!-- 왼쪽 카테고리 -->
            <!-- <div class="btn-group" data-toggle="buttons">
           	<button type="button" class="btn btn-primary" name="high-rank" id="high-favorite" value="favorite">선호도 통계</button>
           	<button type="button" class="btn btn-primary" name="high-rank" value="sale">총 판매 통계</button>
           </div>  -->
   
	    
      <div class="dlk-radio btn-group">
	    <label class="btn btn-primary" style="float : left;">
	        <input name="high-rank" id="high-favorite" class="form-control" type="radio" value="favorite">선호도 통계
	        <i class="fa fa-check glyphicon glyphicon-ok"></i>
	   </label>
	   <label class="btn btn-primary" style="position :relative; top : -2.45em; left : 9em;" >
	       <input name="high-rank" class="form-control" type="radio" value="sale" > 판매량 통계
	       <i class="fa fa-times glyphicon glyphicon-ok"></i>
       </label>
	   
    </div>
	<hr style="border : 1px solid; margin : 0;"/>
	
	<div id="low-rank-category1" class="selectChart" style="display : none;">
			<!-- 나이/성 별 선호도 선택 -->
		<div class="dlk-radio btn-group">
	    	<label class="btn btn-primary" style="float : left;">
	        <input name="favorite" id="favorite-gender" class="form-control" type="radio" value="gender">성별 선호도
	        <i class="fa fa-check glyphicon glyphicon-ok"></i>
	   		</label>
	   <label class="btn btn-primary" style="position :relative; top : -2.4em; left : 8.5em;" >
	       <input name="favorite" class="form-control" id="favorite-age" type="radio" value="age" >나이별 선호도
	       <i class="fa fa-times glyphicon glyphicon-ok"></i>
       	</label>
	   </div> 
           <!-- 남,여 선택 라디오박스 구현 -->
			<div class="btn-group" data-toggle="buttons">
           	<button type="button" class="btn btn-primary" name="gender-select" id="genderSelectF" value="F">여</button>
           	<button type="button" class="btn btn-primary" name="gender-select" id="genderSelectM" value="M">남</button>
           </div>
           
		<!-- 나이 선택 체크박스 버튼 -->
		<div class="age-form-controller" style="position : relative; top : -5em;">
			 <div class=" form-group " style="float : left;">
	            <input type="checkbox" name="selectAge" id="fancy-checkbox-default" autocomplete="off" value="10"/>
	            <div class="btn-group">
	                <label for="fancy-checkbox-default" class="[ btn btn-xs btn-default ]">
	                    <span class="[ glyphicon glyphicon-ok ]"></span>
	                    <span> </span>
	                </label>
	                <label for="fancy-checkbox-default" class="[ btn btn-default btn-xs active ]">
	                    10대
	                </label>
	            </div>
	        </div>
	        <div class=" form-group " style="float : left;">
	            <input type="checkbox" name="selectAge" id="fancy-checkbox-primary" autocomplete="off" value="20"/>
	            <div class="[ btn-group ]">
	                <label for="fancy-checkbox-primary" class="[ btn btn-primary btn-xs ]">
	                    <span class="[ glyphicon glyphicon-ok ]"></span>
	                    <span> </span>
	                </label>
	                <label for="fancy-checkbox-primary" class="[ btn btn-default btn-xs active ]">
	                   20대
	                </label>
	            </div>
	        </div>
        <div class=" form-group " style="float : left;">
            <input type="checkbox" name="selectAge" id="fancy-checkbox-success" autocomplete="off" value="30"/>
            <div class="[ btn-group ]">
                <label for="fancy-checkbox-success" class="[ btn btn-success btn-xs ]">
                    <span class="[ glyphicon glyphicon-ok ]"></span>
                    <span> </span>
                </label>
                <label for="fancy-checkbox-success" class="[ btn btn-default btn-xs active ]">
                    30대
                </label>
            </div>
        </div>
        <div class="form-group " style="float : left;">
            <input type="checkbox" name="selectAge" id="fancy-checkbox-info" autocomplete="off" value="40"/>
            <div class="[ btn-group ]">
                <label for="fancy-checkbox-info" class="[ btn btn-info btn-xs ]">
                    <span class="[ glyphicon glyphicon-ok ]"></span>
                    <span> </span>
                </label>
                <label for="fancy-checkbox-info" class="[ btn btn-default btn-xs active ]">
                    40대
                </label>
            </div>
        </div>
        <div class=" form-group " style="float : left;">
            <input type="checkbox" name="selectAge" id="fancy-checkbox-warning" autocomplete="off" value="50"/>
            <div class="[ btn-group ]">
                <label for="fancy-checkbox-warning" class="[ btn btn-warning btn-xs ]">
                    <span class="[ glyphicon glyphicon-ok ]"></span>
                    <span> </span>
                </label>
                <label for="fancy-checkbox-warning" class="[ btn btn-default btn-xs active ]">
                   50대
                </label>
            </div>
        </div>
        <div class=" form-group " style="float : left;">
            <input type="checkbox" name="selectAge" id="fancy-checkbox-danger" autocomplete="off" value="60"/>
            <div class="[ btn-group ]">
                <label for="fancy-checkbox-danger" class="[ btn btn-xs btn-danger ]">
                    <span class="[ glyphicon glyphicon-ok ]"></span>
                    <span> </span>
                </label>
                <label for="fancy-checkbox-danger" class="[ btn btn-default btn-xs active ]">
                    60대
                </label>
            </div>
        </div>
        <button type="button" id="btn-age" class="btn btn-primary btn-md">확인</button>
		</div>
		<!-- <input type="radio" name="favorite" id="genderChart" value="gender"><label for="genderChart">성별</label> -->
		
		<!-- <div id="genderSelect" class="selectDetail">
		<input type="radio" name="favorite-gender" id="genderChartF" value="F"  /><label for="genderChartF">여</label> <br />
		<input type="radio" name="favorite-gender" id="genderChartM" value="M" /><label for="genderChartM">남</label>
		</div> -->
	</div>

	<div id="low-rank-category2" style="display : none;" class="selectChart">
		<!-- <input type="radio" name="sale" id="region" value="region"><label for="region">지역</label><br /> 
		<input type="radio" name="sale" id="grade" value="grade"><label for="grade">등급</label> -->
		 <!-- 남,여 선택 라디오박스 구현 -->
			<div class="btn-group" data-toggle="buttons" style="padding : 0; position : relative; top : 1em;">
           	<button type="button" class="btn btn-primary" name="sale" value="region">지역별 판매량</button>
           	<button type="button" class="btn btn-primary" name="sale" value="grade">등급별 판매량</button>
           </div>
           <!-- 각 지역 판매 정보 -->
       <div class="dlk-radio btn-group" id="region-select" style="display : none; margin : 0.5em;">
	    <label class="btn btn-primary" >
	        <input name="region-detail" id="high-favorite" class="form-control" type="radio" value="seoul">서울/경기
	        <i class="fa fa-check glyphicon glyphicon-ok"></i>
	   </label>
	   <br />
	   <label class="btn btn-primary"  >
	       <input name="region-detail" class="form-control" type="radio" value="kangwon" > 강원도	       <i class="fa fa-times glyphicon glyphicon-ok"></i>
       </label>
       <br />
	   <label class="btn btn-primary"  >
	       <input name="region-detail" class="form-control" type="radio" value="chungcheong" > 충청도
	       <i class="fa fa-times glyphicon glyphicon-ok"></i>
       </label>
       <br />
	   <label class="btn btn-primary"  >
	       <input name="region-detail" class="form-control" type="radio" value="jeonla" > 전라도
	       <i class="fa fa-times glyphicon glyphicon-ok"></i>
       </label>
       <br />
	   <label class="btn btn-primary"  >
	       <input name="region-detail" class="form-control" type="radio" value="gyeongsang" > 경상도 
	       <i class="fa fa-times glyphicon glyphicon-ok"></i>
       </label>
    </div>
	</div>
	
 </nav> 

<div id="chartOfGenderM" class="chartOfGender">
	<canvas id="maleChart" width="30em" height="20em" style="position : relative; top:-5em;"></canvas>

</div>
<div id="chartOfGenderF" class="chartOfGender" >
<canvas id="femaleChart"  style="position : relative; top:-5em;"></canvas>
</div>

<div id="chartOfAge" >
	<canvas id="chart-age" ></canvas>
</div>
<div id="chartOfGrade" >
	<canvas id="chart-grade"  style="position: relative; top: -5em;"></canvas>
</div>
<div id="chartOfRegion" style="position : relative; top : -30em; left : 18em;" >
</div>
<div id="chartOfRegionDetail" style="width : 19em; height : 20em; position : relative; top : -53em; left : 48em; padding : 0;">
	<canvas id="chart-region"></canvas>
</div> 
<script>
//캔버스에 있는  차트들을 지우기 위해서 따로 빼논 차트변수
var femaleChart = null;
var maleChart = null;
var ageChart = null;
var gradeChart = null;
var regionChart = null;
var regionDetailChart = null;
//카테고리별 개수
var smartphone = 0;
var monitor = 0;
var notebook = 0;
var desktop = 0;
var tablet = 0;
var wearable = 0;
//카테고리별 가격
var smartphonePrice = 0;
var monitorPrice = 0;
var notebookPrice = 0;
var desktopPrice = 0;
var tabletPrice = 0;
var wearablePrice = 0;
var totalPrice = 0;
//마커 관련
var markers = [];

var totalpriceOfSeoul = 0;
var totalpriceOfKangwon = 0;
var totalpriceOfGyeongsang = 0;
var totalpriceOfJeonla = 0;
var totalpriceOfChungcheong = 0;


$("input[name=high-rank]").on("click",function(){
	//선호도 버튼 선택시
	if($(this).val() == "favorite"){
	
	$("#chartOfRegion").css("display","none");
	//그 전에 선택했었던 다른 라디오박스들을 리셋해준다.
     /*  $("#low-rank-category2").children("button[name=sale]").each(function(){
    	  $("button[name=sale]").prop("checked",false);
      });*/
 	  $("#low-rank-category1").css("display","block"); //선호도 하위카테고리를 보여줌
 	  $(".age-form-controller").css("display","none");
 	  $("button[name=gender-select]").css("display","none");
      $("#low-rank-category2").css("display","none");
      $("input[name=region-detail]").each(function(){
    	  $("input[name=region-detail]").prop("checked",false);
      });
      $("#region-select").css("display","none");
      if(gradeChart!= null ){
    		gradeChart.destroy();
      }
      if(regionDetailChart != null){
    	  regionDetailChart.destroy();
      }
	}else{
		//판매 통계
		//그 전에 선택했었던 다른 라디오박스들을 리셋해준다.
			 $("input[name=favorite]").prop("checked",false);
		 $("#low-rank-category1").children("input[name=favorite]").each(function(){
			 $(".age-group").prop("checked",false);
			 $("input[name=favorite-gender]").prop("checked",false);
			 $("#genderSelect").css("display","none");
			 $('.age-group').css("display","none");
				
		 });
		  $("#low-rank-category1").css("display","none");
	      $("#low-rank-category2").css("display","block");//판매 통계를 보여줌.
	      if(ageChart != null){
		  		ageChart.destroy();
		  		}
		  	if(femaleChart != null && maleChart != null){
		  		femaleChart.destroy();
		  		maleChart.destroy();
		  	}
	}	 
});




$("input[name=favorite]").on("click",function(){
	
	var searchType = $(this).val();
	$("#chartOfRegion").css("display","none");
	$("#chartOfGrade").css("display","none");
	$("#chartOfRegionDetail").css("display","none");
	
	if($(this).val() === 'gender'){
		$("button[name=gender-select]").css("display","block");
		 $(".age-form-controller").css("display","none");
		//성별과 관련된 태그만 남기고 나머지는 영역이 안겹치게 처리함.
		$("#genderSelect").css("display","block");
		$("#chartOfAge").css("display","none");
		$("#chartOfGrade").css("display","none");
		$("input[name=selectAge]").prop("checked",false);
		$('#ageSelect').css("visibility","hidden");
		
		if(ageChart != null){
			ageChart.destroy();
		}
		
	//여자 차트
	$("#genderSelectF").on("click",function(){
		
		$("#chartOfGenderF").css("display","block");
		$("#chartOfGenderM").css("display","none");
		
		//남자 차트가 먼저 그려져있으면 차트를 지운다 라는 뜻.
		if(maleChart != null){
			maleChart.destroy();
		}
	$.ajax({
		url : "<%=request.getContextPath()%>/admin/chartOfFavorite",
		data : "searchType="+ searchType,
		success : function(data){
			
			//$('.chartOfGender').css("display","block");
			
			$("#chartOfAge").css("display","none");
			for(var i in data){
				if(i == 0){
			var favoriteChart = data[i];
			var totalNum = favoriteChart.smartPhone+ favoriteChart.monitor+ favoriteChart.notebook+favoriteChart.desktop+favoriteChart.tablet+favoriteChart.wearable;
			var dataSet = [{
	            data: [favoriteChart.smartPhone, favoriteChart.monitor, favoriteChart.notebook, favoriteChart.desktop, favoriteChart.tablet, favoriteChart.wearable],
	            backgroundColor: [
	                'rgba(255, 99, 132, 0.2)',
	                'rgba(54, 162, 235, 0.2)',
	                'rgba(255, 206, 86, 0.2)',
	                'rgba(75, 192, 192, 0.2)',
	                'rgba(153, 102, 255, 0.2)',
	                'rgba(255, 159, 64, 0.2)'
	            ],
	            borderColor: [
	                'rgba(255,99,132,1)',
	                'rgba(54, 162, 235, 1)',
	                'rgba(255, 206, 86, 1)',
	                'rgba(75, 192, 192, 1)',
	                'rgba(153, 102, 255, 1)',
	                'rgba(255, 159, 64, 1)'
	            ],
	            borderWidth: 2
	        }];
			var ctx = $("#femaleChart");
			$(ctx).attr("width","20em").attr("height","20em");
			femaleChart = new Chart(ctx, {
			    type: 'pie',
			    data: {
			        datasets: dataSet,
			        labels: ["스마트폰", "모니터", "노트북", "데스크탑", "태블릿", "웨어러블"]
			    },
			    options: {
			    	title :{
			    		display: true,
			    		text: '여자 선호도',	
			    	},
			    	legend : {
			    		display : true,
			    		position:'right'	
			    		
			    	},
			    	animation : {
			    		animateScale : true
			    	}
			    	 ,plugins : {
				    		datalabels : {
				    			formatter : function(value, context){	
				    				var percentage;
				    				if(value != 0){
				    					 percentage =  Math.round(value/totalNum*100) + '%';
				    				}else{
				    					percentage = '';
				    				}
				    				return percentage;
				    			}
				    		}
				    	}
			    }
			});
			femaleChart.render();//여자 선호도 차트를 랜더링
			
		}//end of if
				
	}//end of forin(data)
}
		
	});//end of ajax
	});//click of female  
		
	
	$("#genderSelectM").click(function(){
		$("#chartOfGenderF").css("display","none");
		$("#chartOfGenderM").css("display","block");
		
		if(femaleChart != null){
			femaleChart.destroy();
		}
	$.ajax({
		url : "<%=request.getContextPath()%>/admin/chartOfFavorite",
		data : "searchType="+ searchType,
		success : function(data){
			$('#ageSelect').css("visibility","hidden");
			$("#chartOfAge").css("display","none");
			for(var i in data){
				 if(i == 1){
					 var favoriteChart = data[i];
					 var totalNum = favoriteChart.smartPhone+ favoriteChart.monitor+ favoriteChart.notebook+favoriteChart.desktop+favoriteChart.tablet+favoriteChart.wearable;
					 	var dataSet = [{
					 		  data: [favoriteChart.smartPhone, favoriteChart.monitor, favoriteChart.notebook, favoriteChart.desktop, favoriteChart.tablet, favoriteChart.wearable],
					            backgroundColor: [
					                'rgba(255, 99, 132, 0.2)',
					                'rgba(54, 162, 235, 0.2)',
					                'rgba(255, 206, 86, 0.2)',
					                'rgba(75, 192, 192, 0.2)',
					                'rgba(153, 102, 255, 0.2)',
					                'rgba(255, 159, 64, 0.2)'
					            ],
					            borderColor: [
					                'rgba(255,99,132,1)',
					                'rgba(54, 162, 235, 1)',
					                'rgba(255, 206, 86, 1)',
					                'rgba(75, 192, 192, 1)',
					                'rgba(153, 102, 255, 1)',
					                'rgba(255, 159, 64, 1)'
					            ],
					            borderWidth: 2,
					            
					 	}];
						var ctx = $("#maleChart");
						
						$(ctx).attr("width","20em").attr("height","20em");
						maleChart = new Chart(ctx, {
						    
							type: 'pie',
						    
						    data: {
						        datasets: dataSet,
						        labels:["스마트폰", "모니터", "노트북", "데스크탑", "태블릿", "웨어러블"]
						    },
						    options: {
						    	/* rotation: -Math.PI,
						    	  cutoutPercentage: 30,
						    	  circumference: Math.PI, */
						    	title :{
						    		display: true,
						    		text: '남자 선호도',
						    		
						    	},
						    	legend : {
						    		display : true,
						    		position:'right'	
						    		
						    	},
						    	animation : {
						    		animateScale : true
						    	},
						    	plugins : {
						    		datalabels : {
						    			formatter : function(value, context){
						    				
						    				var percentage;
						    				if(value != 0){
						    					 percentage =  Math.round(value/totalNum*100) + '%';
						    				}else{
						    					percentage = '';
						    				}
						    				return percentage;
						    			}
						    		}
						    	}
						    	/* elements: {
						    	      center: {
						    	      text: 'GenderFavorite',
						    	      color: '#36A2EB', //Default black
						    	      sidePadding: 15 //Default 20 (as a percentage)
						    	    }
						    	  } */
						        /* scales: {
						            yAxes: [{
						                ticks: {
						                    beginAtZero:true
						                }
						            }]
						        } */
						    }
						});
						maleChart.render();//남자 선호도 차트를 랜더링함.
						
					}//end of if
			}//end of forin
		}//end of success
		});//end of ajax
		});//end of click male
	
		
	

	}else{
		//나이 차트시작
		if(femaleChart != null && maleChart != null){
			femaleChart.destroy();
			maleChart.destroy();
		}
		//나이 체크박스는 보이게, 성별 라디오는 안보이게함.
		$("button[name=gender-select]").css("display","none");
		$(".age-form-controller").css("display","block");
		//나이 차트 빼고 나머지  차트영역 div는 안겹치게 한다.
		$("#chartOfAge").css("display","block");
		$("#genderSelect").css("display","none");
		$("#chartOfGenderF").css("display","none");
		$("#chartOfGenderM").css("display","none");
		
		
		/* $("input[name=favorite-gender]").each(function(){
			$("input[name=favorite-gender]").prop("checked",false);
		});
		
		$("input[name=checkAgeSelect]").each(function(){
			 $("input[name=checkAgeSelect]").prop('checked',false);
		}); */
		//$('.chartOfGender').css("display","none");
		
		// $('#ageSelect').css("visibility","visible").css("font-size","0.5em").css("width","14em").css("height","5em").css("margin","0").css("padding","0");
		//ctx.get(0).getContext("2d").clearRect(0,0,ctx.width,ctx.height);
		
		$("#btn-age").on("click",function(){
			
			var checkVal="";
			$("input[name=selectAge]:checked").each(function(){
				 checkVal += $(this).val();
			});
		
		var param ={
				searchType : searchType,
				checkVal :checkVal
		}
		$.ajax({
			url : "<%=request.getContextPath()%>/admin/chartOfFavorite",
			data : param,
			success: function(data){
				var ctx = $("#chart-age");
				///console.log(data);
				$(ctx).attr("width","40em").attr("height","40em");
				if(ageChart != null){
					ageChart.destroy();
				}
				
				var dataSet;
				
				var ageData = {};
				var ageArr = [];
				for(var i in data){
					smartphone = data[i].smartPhone;
					monitor = data[i].monitor;
					notebook = data[i].notebook;
					desktop = data[i].desktop;
					tablet = data[i].tablet;
					wearable = data[i].wearable;
				 ageData = {
						smartphone : smartphone,
						monitor:monitor,
						notebook:notebook,
						desktop:desktop,
						tablet:tablet,
						wearable:wearable
					};
				 ageArr.push(ageData);
				}
				//var totalOne = ageArr[0].smartphone+ageArr[0].monitor+ageArr[0].notebook
					dataSet={
						labels: ["10대", "20대", "30대", "40대", "50대", "60대"],
						datasets:[{
							label:'스마트폰',
							backgroundColor:'#1E90FF',
							data:[
								ageArr[0].smartphone,
								ageArr[1].smartphone,
								ageArr[2].smartphone,
								ageArr[3].smartphone,
								ageArr[4].smartphone,
								ageArr[5].smartphone
							]
						},{
							label:'모니터',
							backgroundColor:'#F7464A',
							data:[
								ageArr[0].monitor,
								ageArr[1].monitor,
								ageArr[2].monitor,
								ageArr[3].monitor,
								ageArr[4].monitor,
								ageArr[5].monitor
								
							]
						},{
							label:'노트북',
							backgroundColor:'#DBC000',
							data:[
								ageArr[0].notebook,
								ageArr[1].notebook,
								ageArr[2].notebook,
								ageArr[3].notebook,
								ageArr[4].notebook,
								ageArr[5].notebook
							]
						},{
							label:'데스크탑',
							backgroundColor:'#ABF200',
							data:[
								ageArr[0].desktop,
								ageArr[1].desktop,
								ageArr[2].desktop,
								ageArr[3].desktop,
								ageArr[4].desktop,
								ageArr[5].desktop
								
							]
						},{
							label:'태블릿',
							backgroundColor:'#90FFFF',
							data:[
								ageArr[0].tablet,
								ageArr[1].tablet,
								ageArr[2].tablet,
								ageArr[3].tablet,
								ageArr[4].tablet,
								ageArr[5].tablet
							]
						
						},{
							label:'웨어러블',
							backgroundColor:'#EDAEFF',
							data:[
								ageArr[0].wearable,
								ageArr[1].wearable,
								ageArr[2].wearable,
								ageArr[3].wearable,
								ageArr[4].wearable,
								ageArr[5].wearable
							]
						}]	
					};//end of dataSet
				
					ageChart = new Chart(ctx, {
						type : 'bar',
						data : dataSet, 
						showTooltips: false,
						options: {
							
					        scales: { //X,Y축 옵션
					            yAxes: [{
					                ticks: {
					                    beginAtZero:true  //Y축의 값이 0부터 시작
					                }, scaleLabel:{
					                	display : true,
					                	labelString : "인원(명)"
					            
					                }
					            }]
					        },	
					        title :{
					    		display: true,
					    		text: '나이별 선호도',
					    		
					    	},
					    	legend : {
					    		display : true,
					    		position:'bottom'		
					    },	
					   plugins : {
				    		datalabels : {
				    			formatter : function(value, context){
				    				
				    				if(value != 0){
				    					return value; 
				    				}else{
				    					return '';
				    				}
				    			},
				    			anchor: 'end'
				    		}
				    	} 
					   
						}
						
					}); //end of chart
					ageChart.render();
			} //end of success
		}); //end of ajax(age)
		});//end of checkBox event
	}//end of if($(this.val())
	
});//end of favorite event

$("button[name=sale]").on("click",function(){
	if($(this).val() == 'grade'){
		//등급 판매량
		//변수 선언
		var gold = new Object();
		var vip = new Object();
		var vvip = new Object();
		var normal = new Object();
		
		//등급 차트영역을 제외한 나머지 영역들을 display none해서 보여줌.
		$("#chartOfGrade").css("display","block");
		$("#chartOfAge").css("display","none");
		$("#chartOfGenderF").css("display","none");
		$("#chartOfGenderM").css("display","none");
		$("#chartOfRegion").css("display","none");
		$("#region-select").css("display","none");
		$.ajax({
			url:"<%=request.getContextPath()%>/admin/chartOfSale",
			data: "searchType="+$(this).val(),
			success: function(data){
				var ctx = $("#chart-grade");
				$(ctx).attr("width","33em").attr("height","30em");
				if(gradeChart != null){
					gradeChart.destroy();
				}
				if(regionDetailChart != null){
					regionDetailChart.destroy();
				}
				for(var i in data){
					//각각의 등급당 카테고리별 판매량
					if(data[i].grade == "GOLD"){
						switch(data[i].description){
						case "데스크탑" : desktopPrice += data[i].salePrice; gold.desktopPrice = desktopPrice; break;
						case "스마트폰" : smartphonePrice += data[i].salePrice; gold.smartphonePrice = smartphonePrice; break;
						case "웨어러블" : wearablePrice += data[i].salePrice; gold.wearablePrice = wearablePrice; break;
						case "모니터" :  monitorPrice += data[i].salePrice; gold.monitorPrice = monitorPrice;  break;
						case "노트북" :  notebookPrice += data[i].salePrice; gold.notebookPrice = notebookPrice; break;
						case "태블릿" :  tabletPrice += data[i].salePrice; gold.tabletPrice = tabletPrice; break;
						}
					
					}else if(data[i].grade == "VIP"){
						desktopPrice = 0;
						smartphonePrice = 0;
						wearablePrice = 0;
						monitorPrice = 0;
						notebookPrice = 0;
						tabletPrice = 0;
						
						switch(data[i].description){
						case "데스크탑" : desktopPrice += data[i].salePrice; vip.desktopPrice = desktopPrice; break;
						case "스마트폰" : smartphonePrice += data[i].salePrice; vip.smartphonePrice = smartphonePrice; break;
						case "웨어러블" : wearablePrice += data[i].salePrice; vip.wearablePrice = wearablePrice; break;
						case "모니터" :  monitorPrice += data[i].salePrice; vip.monitorPrice = monitorPrice;  break;
						case "노트북" :  notebookPrice += data[i].salePrice; vip.notebookPrice = notebookPrice; break;
						case "태블릿" :  tabletPrice += data[i].salePrice; vip.tabletPrice = tabletPrice; break;
						}
						
					}else if(data[i].grade == "VVIP"){
						desktopPrice = 0;
						smartphonePrice = 0;
						wearablePrice = 0;
						monitorPrice = 0;
						notebookPrice = 0;
						tabletPrice = 0;
						
						switch(data[i].description){
						case "데스크탑" : desktopPrice += data[i].salePrice; vvip.desktopPrice = desktopPrice; break;
						case "스마트폰" : smartphonePrice += data[i].salePrice; vvip.smartphonePrice = smartphonePrice; break;
						case "웨어러블" : wearablePrice += data[i].salePrice; vvip.wearablePrice = wearablePrice; break;
						case "모니터" :  monitorPrice += data[i].salePrice; vvip.monitorPrice = monitorPrice;  break;
						case "노트북" :  notebookPrice += data[i].salePrice; vvip.notebookPrice = notebookPrice; break;
						case "태블릿" :  tabletPrice += data[i].salePrice; vvip.tabletPrice = tabletPrice; break;
						}
						
					}else if(data[i].grade == "일반"){
						desktopPrice = 0;
						smartphonePrice = 0;
						wearablePrice = 0;
						monitorPrice = 0;
						notebookPrice = 0;
						tabletPrice = 0;
						
						switch(data[i].description){
						case "데스크탑" : desktopPrice += data[i].salePrice; normal.desktopPrice = desktopPrice; break;
						case "스마트폰" : smartphonePrice += data[i].salePrice; normal.smartphonePrice = smartphonePrice; break;
						case "웨어러블" : wearablePrice += data[i].salePrice; normal.wearablePrice = wearablePrice; break;
						case "모니터" :  monitorPrice += data[i].salePrice; normal.monitorPrice = monitorPrice;  break;
						case "노트북" :  notebookPrice += data[i].salePrice; normal.notebookPrice = notebookPrice; break;
						case "태블릿" :  tabletPrice += data[i].salePrice; normals.tabletPrice = tabletPrice; break;
						}
						
					}//end of if data[i].grade
					
				}//end of forin
				//각 등급당 판매한 총 가격을 담을 변수.
				var goldPrice = 0
				var vipPrice = 0
				var vvipPrice = 0
				var norPrice = 0
				//객체는 형이 String형인거같다 그래서 for문을 통해서 key에 접근해서 하나씩 가져와 바꿔줌.
				for(var key in gold){
					goldPrice += parseInt(gold[key]);
				}
				for(var key in vip){
					vipPrice += parseInt(vip[key]);					
				}
				for(var key in vvip){
					vvipPrice += parseInt(vvip[key]);					
				}
				for(var key in normal){
					norPrice += parseInt(normal[key]);					
				}
				
				var dataSet = {
						labels: ["GOLD", "VIP", "VVIP", "일반"],
						datasets:[{
							label:'스마트폰',
							backgroundColor:'#1E90FF',
							data:[
								 parseInt(gold.smartphonePrice),
								 parseInt(vip.smartphonePrice),
								 parseInt(vvip.smartphonePrice),
								 parseInt(normal.smartphonePrice),
								
							]
						},{
							label:'데스크탑',
							backgroundColor:'#F7464A',
							data:[
								gold.desktopPrice,
								vip.desktopPrice,
								vvip.desktopPrice,
								normal.desktopPrice,
								
							]
						},{
							label:'노트북',
							backgroundColor:'#DBC000',
							data:[
								gold.notebookPrice,
								vip.notebookPrice,
								vvip.notebookPrice,
								normal.notebookPrice,
							
							]
						},{
							label:'모니터',
							backgroundColor:'#ABF200',
							data:[
								gold.monitorPrice,
								vip.monitorPrice,
								vvip.monitorPrice,
								normal.monitorPrice,
								
								
							]
						},{
							label:'태블릿',
							backgroundColor:'#90FFFF',
							data:[
								gold.tabletPrice,
								vip.tabletPrice,
								vvip.tabletPrice,
								normal.tabletPrice
								
							]
						
						},{
							label:'웨어러블',
							backgroundColor:'#EDAEFF',
							data:[
								gold.wearablePrice,
								vip.wearablePrice,
								vvip.wearablePrice,
								normal.wearablePrice
							
							]
						},{
							label:'총 가격',
							backgroundColor:'#F29661',
							data : [
								goldPrice,
								vipPrice,
								vvipPrice,
								norPrice
								
							]
						}]
				};//end of dataSet
				gradeChart = new Chart(ctx,{
					type : 'bar',
					data : dataSet, 
					showTooltips: false,
					options: {
						
				        scales: { //X,Y축 옵션
				            yAxes: [{
				            	display:true,	
				                ticks: {
				                	
				                    beginAtZero:true  //Y축의 값이 0부터 시작
				                	
				                },
				                scaleLabel:{
				                	display : true,
				                	labelString : "판매 가격(원)"
				            
				                }
				            }]
				        },	
				        title :{
				    		display: true,
				    		text: '등급별 판매량',
				    		
				    	},
				    	legend : {
				    		display : true,
				    		position:'bottom'	
				    		
				    	
				    	},   plugins : {
				    		datalabels : {
				    			formatter : function(value, context){
				    				
				    				
				    					return '';
				    				
				    			},
				    			anchor: 'end'	
				    		}
				    	} 
					   
					}
					
				});//end of Chart
				gradeChart.render();
			}
		});//end of ajax
	}//end of if(grade)
	else{
		//지역 판매량
		$("#chartOfRegion").css("display","block");
		$("#chartOfGrade").css("display","none");
		$("#chartOfAge").css("display","none");
		$("#chartOfGenderF").css("display","none");
		$("#chartOfGenderM").css("display","none");
		$("#region-select").css("display","block");
		$("#chartOfRegionDetail").css("display","block");
		$("input[name=region-detail]").prop("checked",false);
		$("#chartOfRegion").css("width","600px").css("height","400px");
		
		$.ajax({
			url:"<%=request.getContextPath()%>/admin/chartOfSale",
			data: "searchType="+$(this).val(),
			success : function(data){
				if(regionDetailChart != null){
					regionDetailChart.destroy();
				}
				 totalpriceOfSeoul = 0;
				 totalpriceOfKangwon = 0;
				 totalpriceOfGyeongsang = 0;
				 totalpriceOfJeonla = 0;
				 totalpriceOfChungcheong = 0;
				for(var i in data){
					var address = data[i].address;
					if(address != null){
					if(address.indexOf("서울") != -1 || address.indexOf("경기") != -1 || address.indexOf("인천") != -1){
						totalpriceOfSeoul += data[i].salePrice;
					}else if(address.indexOf("강원") != -1){
						totalpriceOfKangwon += data[i].salePrice;
					}else if(address.indexOf("경북") != -1){
						totalpriceOfGyeongsang += data[i].salePrice;
						
					}else if(address.indexOf("경남") != -1){
						totalpriceOfGyeongsang += data[i].salePrice;
					}else if(address.indexOf("부산") != -1){
						totalpriceOfGyeongsang += data[i].salePrice;
					}else if(address.indexOf("충북") != -1){
						totalpriceOfchungcheong += data[i].salePrice;
					}else if(address.indexOf("충남") != -1){
						totalpriceOfChungcheong += data[i].salePrice;
					}else if(address.indexOf("대전") != -1){
						totalpriceOfChungcheong += data[i].salePrice;
					}else if(address.indexOf("전북") != -1){
						totalpriceOfJeonla += data[i].salePrice;
					}else if(address.indexOf("전남") != -1){
						totalpriceOfJeonla += data[i].salePrice;
					}else if(address.indexOf("광주") != -1){
						totalpriceOfJeonla += data[i].salePrice;
					}else if(address.indexOf("대구") != -1){
						totalpriceOfGyeongsang += data[i].salePrice;
					}else if(address.indexOf("울산") != -1){
						totalpriceOfGyeongsang += data[i].salePrice;
					}
					}
				}
				google.charts.load('current', {
				    'packages':['geochart'],
				    // Note: you will need to get a mapsApiKey for your project.
				    // See: https://developers.google.com/chart/interactive/docs/basic_load_libs#load-settings
				    'mapsApiKey': 'AIzaSyCH2su4q92rlC2jQ2cpAxdWsaCp14RyozQ',
				     callback: drawRegionsMap
				  } );
				// google.charts.setOnLoadCallback(drawRegionsMap);
				      
		/* 	var citymap = {
						kangwon: {
						   center: {lat :37.955428, lng:128.3172303},
						   totalPrice : totalpriceOfKangwon
						 },
						 seoul: {	
						   center: {lat: 37.5650172, lng: 126.8494654},
						   totalPrice : totalpriceOfSeoul
						 },
						 gyeongsang: {
						   center: {lat : 35.728765, lng : 128.7535593},
						   totalPrice : totalpriceOfGyeongsang
						 },
						 jeonla: {
						   center: {lat: 35.1766798, lng: 126.7737599},
						   totalPrice : totalpriceOfJeonla
						 },
						 chungcheong : {
							 center: {lat :36.714379, lng:127.1339553},
							   totalPrice : totalpriceOfChungcheong
						 }
					}; */
	
				// Create the map.
		        /* var map = new google.maps.Map($("#chartOfRegion")[0], {
		          zoom: 6.5,
		          center: {lat: 36.4168987, lng: 128.1433404},
		          mapTypeId: 'terrain',
		          panControl: false,
		          zoomControl: false,
		          scaleControl: false,
		          mapTypeControl:false,
		          rotateControl : false,
		          overviewMapControl : false
		        });
		        function numberWithCommas(x) {
		            return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		        }		  */    
		      //각 지역마다 마커 찍기
		
		      //서울마크
			/* 	markerSeoul = new google.maps.Marker({
				    map: map,
				    draggable: false,
				    animation: google.maps.Animation.DROP,
				    position: {lat: 37.3750172, lng: 126.9994654},
				   
				  });

				 markerSeoul.addListener('click',infowindows); 
 
				
				 //강원도 마크
				markerKangwon = new google.maps.Marker({
				    map: map,
				    draggable: false,
				    animation: google.maps.Animation.DROP,
				    position: {lat :37.955428, lng:128.3172303}
				  });
			
					 markerKangwon.addListener('click',infowindowk); 
				
				 //충청도 마크
				markerChungcheong = new google.maps.Marker({
				    map: map,
				    draggable: false,
				    animation: google.maps.Animation.DROP,
				    position: {lat :36.514379, lng:127.2339553}
				  });
			
				
				 markerChungcheong.addListener('click',infowindowc);
				  //전라도 마크
				markerJeonla = new google.maps.Marker({
				    map: map,
				    draggable: false,
				    animation: google.maps.Animation.DROP,
				    position: {lat: 35.1766798, lng: 126.7737599}
				  });
			
				 
				 markerJeonla.addListener('click',infowindowj);
				  //경상도 마크
				markerGyeongsang = new google.maps.Marker({
				    map: map,
				    draggable: false,
				    animation: google.maps.Animation.DROP,
				    position: {lat : 35.728765, lng : 128.7535593}
				  });
				
				 markerGyeongsang.addListener('click',infowindowg);
				 
				 //info에 지역 총 판매량을 담는다.
				 var infowindowseoul;
				 var infowindowkangwon;
				 var infowindowchungcheong;
				 var infowindowjeonla;
				 var infowindowgyeongsang;
				 function infowindows(){
					 infowindowseoul = new google.maps.InfoWindow({
							content :"총 판매량 : "+ numberWithCommas(totalpriceOfSeoul)+"원"
						});
					 infowindowseoul.open(map,markerSeoul);
					 // infowindowseoul를 열때 나머지 지역의 indowindow는 닫아준다.
					 if(infowindowkangwon != null){
						 infowindowkangwon.close();
						
					 }
					 if(infowindowchungcheong != null){
						 infowindowchungcheong.close();
						
					 }
					 if(infowindowjeonla != null){
						 infowindowjeonla.close();
						 
					 }
					 if(infowindowgyeongsang != null){
						 infowindowgyeongsang.close();
						
					 }
				 }
				 function infowindowk(){
					 infowindowkangwon = new google.maps.InfoWindow({
							content :"총 판매량 : "+ numberWithCommas(totalpriceOfKangwon)+"원"
						}); 
					 infowindowkangwon.open(map,markerKangwon);
					 if(infowindowseoul != null){
						 infowindowseoul.close();
					 }
					 if(infowindowchungcheong != null){
						 infowindowchungcheong.close();
					 }
					 if(infowindowjeonla != null){
						 infowindowjeonla.close();
					 }
					 if(infowindowgyeongsang != null){
						 infowindowgyeongsang.close();
					 }
				 }
				 function infowindowc(){
					 infowindowchungcheong = new google.maps.InfoWindow({
							content :"총 판매량 : "+ numberWithCommas(totalpriceOfChungcheong)+"원"
						}); 
					 infowindowchungcheong.open(map,markerChungcheong);
					 if(infowindowkangwon != null){
						 infowindowkangwon.close();
					 }
					 if(infowindowseoul != null){
						 infowindowseoul.close();
					 }
					 if(infowindowjeonla != null){
						 infowindowjeonla.close();
					 }
					 if(infowindowgyeongsang != null){
						 infowindowgyeongsang.close();
					 }
				 }
				 function infowindowj(){
					 infowindowjeonla = new google.maps.InfoWindow({
							content : "총 판매량 : "+numberWithCommas(totalpriceOfJeonla)+"원"
					});
					 infowindowjeonla.open(map,markerJeonla);
					 if(infowindowkangwon != null){
						 infowindowkangwon.close();
					 }
					 if(infowindowchungcheong != null){
						 infowindowchungcheong.close();
					 }
					 if(infowindowseoul != null){
						 infowindowseoul.close();
					 }
					 if(infowindowgyeongsang != null){
						 infowindowgyeongsang.close();
					 }
				 }
				 function infowindowg(){
					 infowindowgyeongsang = new google.maps.InfoWindow({
							content :"총 판매량 : "+ numberWithCommas(totalpriceOfGyeongsang)+"원"
						}); 
					 infowindowgyeongsang.open(map,markerGyeongsang);
					 if(infowindowkangwon != null){
						 infowindowkangwon.close();
					 }
					 if(infowindowchungcheong != null){
						 infowindowchungcheong.close();
					 }
					 if(infowindowjeonla != null){
						 infowindowjeonla.close();
					 }
					 if(infowindowseoul != null){
						 infowindowseoul.close();
					 }
				 }
		        for (var city in citymap) {
		            // Add the circle for this city to the map
		            var cityCircle = new google.maps.Circle({
		              strokeColor: '#FF0000',
		              strokeOpacity: 0.8,
		              strokeWeight: 2,
		              fillColor: '#FF0000',
		              fillOpacity: 0.35,
		              map: map,
		              center: citymap[city].center,
		              radius: Math.sqrt(citymap[city].totalPrice) * 15
		            });
		          }
		         */
			
			} //end of success
		});//end of ajax(region)
	}//end of else(region)
	
	//지역 상세보기
	$("input[name=region-detail]").on("click",function(){
		var searchType=$(this).val();
		$("#chartOfRegion").css("display","block");
		$("#chartOfGrade").css("display","none");
		$("#chartOfAge").css("display","none");
		$("#chartOfGenderF").css("display","none");
		$("#chartOfGenderM").css("display","none");
		$("#region-select").css("display","block");
		$.ajax({
			url:"<%=request.getContextPath()%>/admin/chartOfSale",
			data: "searchType="+searchType,
			success : function(data){
				if(regionDetailChart != null){
					regionDetailChart.destroy();
				}
				smartphonePrice = 0;
				monitorPrice = 0;
				notebookPrice = 0;
				desktopPrice = 0;
				tabletPrice = 0;
				wearablePrice = 0;
				totalPrice = 0;
				for(var i in data){
					var description = data[i].description;
					switch(description){
					case "스마트폰" : smartphonePrice += data[i].salePrice; break;
					case "모니터" : monitorPrice += data[i].salePrice; break;
					case "노트북" : notebookPrice += data[i].salePrice; break;
					case "태블릿" : tabletPrice += data[i].salePrice; break;
					case "웨어러블" : wearablePrice += data[i].salePrice; break;
					case "데스크탑" : desktopPrice += data[i].salePrice; break;
					}
					
				}//end of for in
				totalPrice = smartphonePrice+monitorPrice+notebookPrice+tabletPrice+wearablePrice+desktopPrice;
				var ctx = $("#chart-region");
				$(ctx).attr("width","19em").attr("height","20em");
				$("#chartOfRegion").css("width","30em").css("height","28em");
				var dataSet = [{
					 data: [smartphonePrice, monitorPrice, notebookPrice, desktopPrice,tabletPrice, wearablePrice],
			            backgroundColor: [
			                'rgba(255, 99, 132, 0.2)',
			                'rgba(54, 162, 235, 0.2)',
			                'rgba(255, 206, 86, 0.2)',
			                'rgba(75, 192, 192, 0.2)',
			                'rgba(153, 102, 255, 0.2)',
			                'rgba(255, 159, 64, 0.2)'
			            ],
			            borderColor: [
			                'rgba(255,99,132,1)',
			                'rgba(54, 162, 235, 1)',
			                'rgba(255, 206, 86, 1)',
			                'rgba(75, 192, 192, 1)',
			                'rgba(153, 102, 255, 1)',
			                'rgba(255, 159, 64, 1)'
			            ],
			            borderWidth: 2
				}];
				
				regionDetailChart = new Chart(ctx, {
				    type: 'pie',
				    data: {
				        datasets: dataSet,
				        labels: 
				        		["스마트폰", "모니터", "노트북", "데스크탑", "태블릿", "웨어러블"]
				
				    },
				    options: {
				    	/* rotation: -Math.PI,
				    	  cutoutPercentage: 30,
				    	  circumference: Math.PI, */
				    	title :{
				    		display: true,
				    		text: searchType,
				    		
				    	},
				    	legend : {
				    		display : true,
				    		position:'bottom'	
				    		
				    	},
				    	animation : {
				    		animateScale : true
				    	}
				    	 ,plugins : {
					    		datalabels : {
					    			formatter : function(value, context){
					    				
					    				var percentage;
					    				if(value != 0){
					    					 percentage =  Math.round(value/totalPrice*100) + '%';
					    				}else{
					    					percentage = '';
					    				}
					    				return percentage;
					    			}
					    		}
					    	}
				    	 
				    	 			    	 
		
				    }
				});
				regionDetailChart.render();//지역별 판매도 차트를 랜더링
				var lat = 0;
				var lng = 0;
				switch(searchType){
				case  "seoul" : lat =37.4760053; lng=126.9671619; break;
				case  "kangwon" : lat =37.955428; lng=128.3172303; break;
				case  "chungcheong" : lat =36.714379; lng=127.1339553; break;
				case  "jeonla" : lat =35.11002; lng=126.7704733; break;
				case  "gyeongsang" :lat =35.728765; lng=128.7535593;  break;
				}
				var map = new google.maps.Map($("#chartOfRegion")[0], {
			          zoom: 9,
			          center: {lat: lat, lng: lng},
			          mapTypeId: 'roadmap',
			          panControl: false,
			          zoomControl: false,
			          scaleControl: false,
			          mapTypeControl:false,
			          rotateControl : false,
			          overviewMapControl : false
			        });
			}//end of success
		});//end of ajax(region-detail)
	});//end of region-detail
});//end of sale event

function drawRegionsMap() {
    var data = google.visualization.arrayToDataTable([
      ['City', 'Sale'],
      ['서울', totalpriceOfSeoul],
      ['Gyeonggi', totalpriceOfSeoul],
      ['Incheon',totalpriceOfSeoul],
      ['Gangwon', totalpriceOfKangwon],
      ['Jeonbuk',totalpriceOfJeonla],
      ['Jeonnam',totalpriceOfJeonla],
      ['Gwangju',totalpriceOfJeonla],
      ['Gyeongnam',totalpriceOfGyeongsang],
      ['Gyeongbuk',totalpriceOfGyeongsang],
      ['Ulsan',totalpriceOfGyeongsang],
      ['Daegu',totalpriceOfGyeongsang],
      ['Busan', totalpriceOfGyeongsang],
      ['Chungbuk',	totalpriceOfChungcheong],
      ['Chungnam',totalpriceOfChungcheong],
      ['Daejeon',totalpriceOfChungcheong],
      ['Jeju',661511]
    ]);

    var options = {
    		title : 'Region Sale',
    		region : 'KR', //korea,
    		resolution: 'provinces',
    		colorAxis: {colors: ['white', 'yellow', 'red']},
             /*backgroundColor: '#8C8C8C',*/
            datalessRegionColor: '#D5D5D5',
            defaultColor: '#f5f5f5' 
    };
    var chart = new google.visualization.GeoChart($("#chartOfRegion")[0]);
    chart.draw(data, options);
  }
</script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCH2su4q92rlC2jQ2cpAxdWsaCp14RyozQ"></script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
