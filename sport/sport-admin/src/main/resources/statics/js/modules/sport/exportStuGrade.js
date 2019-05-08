
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
			title: {
				text: ''
			},
			tooltip: {},
			legend: {
				data:['']
			},
			xAxis: {
				data: ["",""]
			},
			yAxis: {},
			series: [{
				name: 'bmi',
				type: 'bar',
				splitNumber:3,
				splitArea:{
					show:true
				},
				data: bmiDataY
			}]
		};

		// 使用刚指定的配置项和数据显示图表。
		bmiChart.setOption(bmiOption);

		echartToImg(bmiChart,"bmiChartImg")
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
