'use strict';
var radarChart, bmiChart,tgmdChart;
function radarChartFun (radarChartData) {
	var indicatorType=[];
	var dataVal = [];

	for(var index in radarChartData){
		var indType = {
			'text':radarChartData[index].projectType,
			'max': 5,
			//若将此属性放在radar下，则每条indicator都会显示圈上的数值，放在这儿，只在通信这条indicator上显示
			// axisLabel: {
			// 	show: true,
			// 	fontSize: 12,
			// 	color: '#838D9E',
			// 	showMaxLabel: false, //不显示最大值，即外圈不显示数字30
			// 	showMinLabel: true, //显示最小数字，即中心点显示0
			// }
		};
		
		indicatorType.push(indType);
		dataVal.push(radarChartData[index].score);
	}
	radarChart = echarts.init(document.getElementById('radarId'));
	var radarOption = {
		title : {
		},
		tooltip : {
			trigger: 'axis',
			showDelay : 0, // 显示延迟，添加显示延迟可以避免频繁切换，单位ms
			axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		legend: {
			data:['满分', '测试']
		},
		calculable : true,
		grid: { // 控制图的大小，调整下面这些值就可以，
			position:'center'
			// x: 60,
			// x2: 100,
			// y2: 150,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
		},

		polar : [
			{
				name: { show: true,textStyle:{fontSize:14,color:"#32cd32"}},
				indicator : indicatorType,
				center : ['50%','50%'],
				radius : 70 //半径，可放大放小雷达图
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

	//echartToImg(radarChart,"radarImg")
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

function bmiChartFun(bmiDataY,bmiGrade){
	// 基于准备好的dom，初始化echarts实例
	bmiChart = echarts.init(document.getElementById('bmiChart'));
	var normalData = bmiDataY.bmiConf.normWeightRange.split('-');
	var lowData = bmiDataY.bmiConf.lowWeightRange.split('-');
	var overData = bmiDataY.bmiConf.overWeightRange.split('-')
	var fatData = bmiDataY.bmiConf.fatWeightRange.split('-')
	// 指定图表的配置项和数据
	var bmiOption = {
		//backgroundColor: '#1b1b1b',//背景色
		title:{
			text: '身体质量指数',
			subtext: 'BMI测试数据'
		},
		legend: {
			orient: 'horizontal', // 'vertical'
			x: 'right', // 'center' | 'left' | {number},
			y: 'bottom', // 'center' | 'bottom' | {number}
			itemWidth: 10,
			itemHeight: 10,
			itemGap: 10,
			data:['正常','偏瘦','偏胖','肥胖']
		},
		tooltip : {//过滤掉统计的series
			trigger: 'item',
			formatter: function (params) {
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
			position:'center',
			left: '2%',
			right: '1%',
			bottom: '8%',
			containLabel: true
		},
		xAxis : [
			{
				type : 'category',
				data : ['']
			},
			{
				type : 'category',
				axisLine: {show:false},
				axisTick: {show:false},
				axisLabel: {show:true},
				splitArea: {show:false},
				splitLine: {show:false},
				data : ['']
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
                // barGap:'1%',
                // barCategoryGap:'10%',
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
                // barGap:'30%',
                // barCategoryGap:'30%',
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
                // barGap:'30%',
                // barCategoryGap:'30%',
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
                // barGap:'30%',
                // barCategoryGap:'30%',
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
                // barGap:'30%',
                // barCategoryGap:'30%',
				xAxisIndex:1,
				itemStyle: {
					normal: {
						color: function (p) {
							if(p.value<15.7){
								return 'rgb(241, 237, 117)';
							}else if(p.value<20.29){
								return 'rgb(54, 228, 124)';
							}else if(p.value<21.12){
								return 'rgb(243, 174, 190)';
							}else{
								return 'rgba(199,35,43,1)';
							}
						}, 
						label:{
							show:true,
							position: 'inside',
							formatter:function(p){

								if(p.value<normalData[0]){
									return '偏瘦';
								}else if(p.value<normalData[1]){
									return '正常';
								}else if(p.value<overData[1]){
									return '偏胖';
								}else{
									return '肥胖';
								}
							}
						}}},

				data: [bmiGrade]
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

	//echartToImg(bmiChart,"bmiChartImg")
}

function tgmdChartFun(tgmdData) {
	// 基于准备好的dom，初始化echarts实例
	tgmdChart = echarts.init(document.getElementById('barChart'));
	// 指定图表的配置项和数据

	var tgmdOption = {
		title:{
			text: 'TGMD3测试',
			subtext: '大肌肉测试'
		},
		legend: {
			orient: 'horizontal', // 'vertical'
			x: 'right', // 'center' | 'left' | {number},
			y: 'bottom', // 'center' | 'bottom' | {number}
			itemWidth: 20,
			itemHeight: 10,
			itemGap: 30,
			data:['满分','测试']
		},
		tooltip : {//过滤掉统计的series
			trigger: 'item',
			formatter: function (params) {
				if(params.seriesName=='满分'){
					return '满分: 96';
				}
				return  '测试: ' + params.value[1];
			}
		},
		calculable : true,
		grid: {
			left: '5%',
			right: '1%',
			bottom: '8%',
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
				itemStyle: {
					normal:{
						color:'#8cbedc',
						label:{
							show:true,
							position: 'inside',
							formatter:function(p){
								return p.value[1];
							}
						}}}

			},
			{
				type:'bar',
				stack: 'TGMD',
				itemStyle: {
					normal: {
						color:'#e08665',
						label:{
							show:true,
							position: 'inside',
							formatter:function(p){
								return '96';
							}
						}}}
			}
		]
	};

	// 使用刚指定的配置项和数据显示图表。
	tgmdChart.setOption(tgmdOption);

	//echartToImg(tgmdChart,"barChartImg")
}




function testBmi(){
	var testBmiChart = echarts.init(document.getElementById('bmiChart'));
	var option = {
		title : {
			text: 'BMI',
			subtext: '测试数据'
		},
		tooltip : {
			trigger: 'item',
			formatter: function (params) {
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