
function radarChartFun (radarCharData) {
	let indicatorType=[];
	let dataVal = [];

	for(let index in radarChartData){
		var indType={};
		indType['text']=radarChartData[index].projectType;
		indType['max']=5;
		indicatorType.push(indType);
		dataVal.push(radarChartData[index].score);
	}
	var radarChart = echarts.init(document.getElementById('radarId'));
	var radarOption = {
		title : {
		},
		tooltip : {
			trigger: 'axis'
		},
		legend: {
			data:['满分', '测试']
		},
		calculable : true,
		polar : [
			{
				name: { show: true,textStyle:{fontSize:16,color:"#32cd32"}},
				indicator : indicatorType,
				center : ['50%','50%'],
				radius : 50 //半径，可放大放小雷达图
			}
		],
		series : [
			{
				name:'数据对比图',
				type: 'radar',//图表类型 radar为雷达图
				itemStyle: {
					normal: {
						lineStyle: {
							color :"#87cefa",
							width : 2
						},
						areaStyle: {
							color:"#87cefa",
							type: 'default'
						}
					}
				},
				symbolSize :3,
				data : [{
					value:dataVal
				}]
			}
		]
	}
	radarChart.setOption(radarOption);

	echartToImg(radarChart,"radarImg")
}

function barChartFun(dataX,fullDataY,checkDataY) {
	// 基于准备好的dom，初始化echarts实例
	var barChart = echarts.init(document.getElementById('barChart'));

	// 指定图表的配置项和数据
	var barOption = {
		title: {
			text: ''
		},
		tooltip : {
			trigger : 'axis',
			showDelay : 0, // 显示延迟，添加显示延迟可以避免频繁切换，单位ms
			axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		legend: {
			data:['测试数据', '满分数据']
		},
		calculable : true,
		xAxis : [{
			type : 'category',
			data : dataX,  //横坐标日期
			axisLabel:{
				textStyle:{
					color:"#222"
				}
			}
		}],
		yAxis : [{
			type : 'value',
			// boundaryGap: [0, 0.1]
		}],
		series : [
			{
				name:'测试数据',
				type:'bar',
				stack: 'sum',
				barCategoryGap: '50%',
				/*itemStyle : { normal: {label : {show: true, position: 'insideTop',textStyle:{color:'#000'}}}},*/
				data:checkDataY
			},
			{
				name:'满分数据',
				type:'bar',
				stack: 'sum',
				barWidth : 20,//柱图宽度
				barCategoryGap: '50%',
				/*itemStyle : { normal: {label : {show: true, position: 'insideTop',textStyle:{color:'#000'}}}},*/
				data:fullDataY
			}
		]
	};

	// 使用刚指定的配置项和数据显示图表。
	barChart.setOption(barOption);

	echartToImg(barChart,"barChartImg")
}

function bmiChartFun(bmiDataY) {
	// 基于准备好的dom，初始化echarts实例
	var bmiChart = echarts.init(document.getElementById('bmiChart'));

	// 指定图表的配置项和数据
	var bmiOption = {
		title:{
			text:"带统计的堆叠柱状图"
		},
		legend: {
			data:['正常','偏瘦','偏胖','肥胖']
		},
		tooltip : {//过滤掉统计的series
			trigger: 'axis',
			axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			},
			formatter: function(params){
				var res=params[0].name+"<br/>";
				for(let i=0;i<params.length-1;i++){
					res+=params[i].seriesName+":"+params[i].value+"<br/>"
				}
				return res;
			}
		},
		grid: {
			left: '3%',
			right: '4%',
			bottom: '3%',
			containLabel: true
		},
		xAxis : [
			{
				type : 'category',
				data : ['','']
			}
		],
		yAxis : [
			{
				type : 'value'
			}
		],
		series : [

			{
				name:'正常',
				type:'bar',
				barWidth : 35,
				stack: '搜索引擎',
				data:[620, 732, 701, 734, 1090, 1130, 1120]
			},
			{
				name:'偏瘦',
				type:'bar',
				stack: '搜索引擎',
				data:[120, 132, 101, 134, 290, 230, 220]
			},
			{
				name:'偏胖',
				type:'bar',
				stack: '搜索引擎',
				data:[60, 72, 71, 74, 190, 130, 110]
			},
			{
				name:'肥胖',
				type:'bar',
				stack: '搜索引擎',
				data:[62, 82, 91, 84, 109, 110, 120]
			},
			/*{
				name:'统计',
				type:'bar',
				stack: '搜索引擎',
				data:[0.3, 0.6, 0.9, 0.84, 0.36, 0.11, 0.66],//模拟数据
				label: {
					normal: {
						offset:[0, -10],//左右，上下
						show: true,
						position: 'insideLeft',
						formatter:function(params){
							if(params.data==0){
								return "0%";
							}else{
								return params.data*100+"%";
							}
						},
						fontSize:14,
						fontWeight:'bold',
						textStyle:{ color:'#199ED8' }
					}
				},
				itemStyle:{
					normal:{
						color:'rgba(128, 128, 128, 0)'
					}
				},
			}*/
		]
	};

		// 使用刚指定的配置项和数据显示图表。
		bmiChart.setOption(bmiOption);

		echartToImg(bmiChart,"bmiChartImg")
}

function tgmdChartFun(dataX,dataY1,dataY2,) {
	// 基于准备好的dom，初始化echarts实例
	var bmiChart = echarts.init(document.getElementById('barChart'));

	// 指定图表的配置项和数据
	var tgmdOption = {
		title:{
			text:"带统计的堆叠柱状图"
		},
		legend: {
			data:['满分','测试']
		},
		tooltip : {//过滤掉统计的series
			trigger: 'axis',
			axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			},
			formatter: function(params){
				var res=params[0].name+"<br/>";
				for(let i=0;i<params.length-1;i++){
					res+=params[i].seriesName+":"+params[i].value+"<br/>"
				}
				return res;
			}
		},
		grid: {
			left: '3%',
			right: '4%',
			bottom: '3%',
			containLabel: true
		},
		xAxis : [
			{
				type : 'category',
				data : dataX
			}
		],
		yAxis : [
			{
				type : 'value'
			}
		],
		series : [

			{
				name:'满分',
				type:'bar',
				barWidth : 35,
				stack: '搜索引擎',
				data:[dataY1[1],dataY2[1]]
			},
			{
				name:'测试',
				type:'bar',
				stack: '搜索引擎',
				data:[dataY1[0],dataY2[0]]
			}
			/*{
				name:'统计',
				type:'bar',
				stack: '搜索引擎',
				data:[0.3, 0.6, 0.9, 0.84, 0.36, 0.11, 0.66],//模拟数据
				label: {
					normal: {
						offset:[0, -10],//左右，上下
						show: true,
						position: 'insideLeft',
						formatter:function(params){
							if(params.data==0){
								return "0%";
							}else{
								return params.data*100+"%";
							}
						},
						fontSize:14,
						fontWeight:'bold',
						textStyle:{ color:'#199ED8' }
					}
				},
				itemStyle:{
					normal:{
						color:'rgba(128, 128, 128, 0)'
					}
				},
			}*/
		]
	};

	// 使用刚指定的配置项和数据显示图表。
	tgmdChart.setOption(tgmdOption);

	echartToImg(tgmdChart,"barChartImg")
}

function echartToImg(echart,imgId){
	var img = document.getElementById(imgId);
	var imgSrc = echart.getDataURL();
	// 渲染到图表上面，遮住图表
	img.src = imgSrc;
	// 图片加载完成之后
	img.onload = function() {
		// 执行打印
		console.log("pic is cover");
	}
}
