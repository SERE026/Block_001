
function radarChartFun (radarChartData) {
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

function bmiChartFun(bmiDataY,bmiGrade) {
	// 基于准备好的dom，初始化echarts实例
	var bmiChart = echarts.init(document.getElementById('bmiChart'));
	debugger;
	var normalData = bmiDataY.bmiConf.normWeightRange.split('-');
	var lowData = bmiDataY.bmiConf.lowWeightRange.split('-');
	var overData = bmiDataY.bmiConf.overWeightRange.split('-')
	var fatData = bmiDataY.bmiConf.fatWeightRange.split('-')
	// 指定图表的配置项和数据
	var bmiOption = {
		title:{
			text: 'BMI',
			subtext: '测试数据'
		},
		legend: {
			data:['正常','偏瘦','偏胖','肥胖']
		},
		tooltip : {//过滤掉统计的series
			trigger: 'item',
			formatter: function (params) {
				debugger
				if(params.seriesName=='肥胖'){
					return '肥胖: >'+fatData[0];
				}if(params.seriesName=='偏胖'){
					return '偏胖: '+bmiDataY.bmiConf.overWeightRange;
				}if(params.seriesName=='正常'){
					return '正常: '+bmiDataY.bmiConf.normWeightRange;
				}if(params.seriesName=='偏瘦'){
					return '偏瘦: '+bmiDataY.bmiConf.lowWeightRange;
				}
				return params.seriesName + ': ' + params.value + ' ';
			}
		},
		calculable : true,
		grid: {
			left: '3%',
			right: '2%',
			bottom: '1%',
			containLabel: true
		},
		xAxis : [
			{
				type : 'category',
				data : ['', '']
			},
			{
				type : 'category',
				axisLine: {show:false},
				axisTick: {show:false},
				axisLabel: {show:true},
				splitArea: {show:false},
				splitLine: {show:false},
				data : ['', '']
			}
		],
		yAxis : [
			{
				type : 'value',
				axisLabel:{formatter:'{value} ' }
			}
		],
		series : [
			{
				name:'偏瘦',
				type:'bar',
				stack: 'BMI',
				xAxisIndex:1,
				itemStyle: {normal: {color:'rgb(241, 237, 117)', label:{
							show:true,
							position: 'inside',
							formatter:function(p){
								return '偏瘦';
							}
						}}},
				data: [normalData[0]]
			},
			{
				name:'正常',
				type:'bar',
				xAxisIndex:1,
				itemStyle: {normal: {color:'rgb(54, 228, 124)', label:{
							show:true,
							position: 'inside',
							formatter:function(p){
								return '正常';
							}
						}}},
				stack: 'BMI',
				data: [normalData[1]-normalData[0]]
			},
			{
				name:'偏胖',
				type:'bar',
				stack: 'BMI',
				xAxisIndex:1,
				itemStyle: {normal: {color:'rgb(243, 174, 190)', label:{
							show:true,
							position: 'right',
							formatter:function(p){
								return '偏胖';
							}
						}}},
				data: [overData[1]-overData[0]]
			},
			{
				name:'肥胖',
				type:'bar',
				stack: 'BMI',
				xAxisIndex:1,
				itemStyle: {normal: {color:'rgba(199,35,43,1)', label:{
							show:true,
							position: 'inside',
							formatter:function(p){
								return '肥胖';
							}
						}}},

				data: [30-overData[1]]
			},
			{
				name:'BMI指标',
				type:'bar',
				xAxisIndex:1,
				itemStyle: {normal: {color:'rgba(197,35,43,1)', label:{
							show:true,
							position: 'inside',
							formatter:function(p){
								if(p.value<15.7){
									return '肥胖';
								}else if(p.value<20.29){
									return '正常';
								}else if(p.value<21.12){
									return '偏胖';
								}else{
									return '肥胖';
								}
							}
						}}},

				data: [bmiGrade.bmiGrade]
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

function tgmdChartFun(tgmdData) {
	// 基于准备好的dom，初始化echarts实例
	var tgmdChart = echarts.init(document.getElementById('barChart'));
	// 指定图表的配置项和数据

	var tgmdOption = {
		title:{
			text: 'TGMD3测试',
			subtext: '大肌肉测试'
		},
		legend: {
			data:['满分','测试']
		},
		tooltip : {//过滤掉统计的series
			trigger: 'item',
			formatter: function (params) {
				debugger
				if(params.seriesName=='满分'){
					return '满分: '+fullScore;
				}
				return params.seriesName + ': ' + params.value + ' ';
			}
		},
		calculable : true,
		grid: {
			left: '3%',
			right: '2%',
			bottom: '1%',
			containLabel: true
		},
		dataset: {
			source: tgmdData
		},
		xAxis : [
			{
				type : 'category',
			}
		],
		yAxis : [
			{
				type : 'value',
				axisLabel:{formatter:'{value} ' }
			}
		],
		series : [
			{
				type:'bar',
				stack: 'TGMD',
				itemStyle: {normal: {color:'rgb(241, 237, 117)', label:{
							show:true,
							position: 'inside',
							formatter:function(p){
								return '满分';
							}
						}}},
			},
			{
				type:'bar',
				stack: 'TGMD',
				itemStyle: {normal: {color:'rgba(197,35,43,1)', label:{
							show:true,
							position: 'inside',
							formatter:function(p){
								return p.value;
							}
						}}},

			},

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



function testBmi(){
	var testBmiChart = echarts.init(document.getElementById('bmiChart'));
	option = {
		title : {
			text: 'BMI',
			subtext: '测试数据'
		},
		tooltip : {
			trigger: 'item',
			formatter: function (params) {
				debugger
				if(params.seriesName=='肥胖'){
					return '肥胖: >21.13';
				}if(params.seriesName=='偏胖'){
					return '偏胖: 20.29-21.12';
				}if(params.seriesName=='正常'){
					return '正常: 15.57-20.29';
				}if(params.seriesName=='偏瘦'){
					return '偏瘦: <14.52';
				}
				return params.seriesName + ': ' + params.value + ' ';
			}
		},
		legend: {
			data:[
				'正常','偏瘦','偏胖','肥胖'
			]
		},
		toolbox: {
			show : true,
			feature : {
				mark : {show: true},
				dataView : {show: true, readOnly: false},
				magicType : {show: true, type: ['参考', '测试']},
				restore : {show: true},
				saveAsImage : {show: true}
			}
		},
		calculable : true,
		grid: {y: 70, y2:40, x2:50},
		xAxis : [
			{
				type : 'category',
				boundaryGap : [0, 0],
				data : ['', '']
			},
			{
				type : 'category',
				boundaryGap : [0, 0],
				axisLine: {show:false},
				axisTick: {show:false},
				axisLabel: {show:true},
				splitArea: {show:false},
				splitLine: {show:false},
				data : ['', '']
			}
		],
		yAxis : [
			{
				type : 'value',
				axisLabel:{formatter:'{value} ' 	}
			}
		],
		series : [

			{
				name:'偏瘦',
				type:'bar',
				stack: 'BMI',
				xAxisIndex:1,
				itemStyle: {normal: {color:'rgb(241, 237, 117)', label:{
							show:true,
							position: 'inside',
							formatter:function(p){
								return '偏瘦';
							}
						}}},
				data: [15.57]
			},
			{
				name:'正常',
				type:'bar',
				xAxisIndex:1,
				itemStyle: {normal: {color:'rgb(54, 228, 124)', label:{
							show:true,
							position: 'inside',
							formatter:function(p){
								return '正常';
							}
						}}},
				stack: 'BMI',
				data: [20.29-15.57]
			},
			{
				name:'偏胖',
				type:'bar',
				stack: 'BMI',
				xAxisIndex:1,
				itemStyle: {normal: {color:'rgb(243, 174, 190)', label:{
							show:true,
							position: 'right',
							formatter:function(p){
								return '偏胖';
							}
						}}},
				data: [21.12-20.29]
			},
			{
				name:'肥胖',
				type:'bar',
				stack: 'BMI',
				xAxisIndex:1,
				itemStyle: {normal: {color:'rgba(199,35,43,1)', label:{
							show:true,
							position: 'inside',
							formatter:function(p){
								return '肥胖';
							}
						}}},

				data: [30-21.12]
			},
			{
				name:'BMI指标',
				type:'bar',
				xAxisIndex:1,
				itemStyle: {normal: {color:'rgba(197,35,43,1)', label:{
							show:true,
							position: 'inside',
							formatter:function(p){
								if(p.value<15.7){
									return '肥胖';
								}else if(p.value<20.29){
									return '正常';
								}else if(p.value<21.12){
									return '偏胖';
								}else{
									return '肥胖';
								}
							}
						}}},

				data: [19.83]
			},
		]
	};
	testBmiChart.setOption(tgmdOption);

	echartToImg(tgmdChart,"bmiChartImg")
}