'use strict';
var baseURL = "../../";
var vm = new Vue({
    el:'#main',
    data:{
        showList: true,
        student:{},
        trainGoal:{},
        lastStuGrade:{},  //最近一次成绩
        lastBmiGrade:{},  //最近一次
        proFullScore:{},  //满配
        lastProGradeList:[],  //最近一次项目成绩
        prevProGradeList:[],
        passDesc:{},
        scoreDesc:{},
        radarChart:{},
        tgmd3Chart:{},
        bmiChart:{}
    },
    mounted: function(){
        this.getInfo(url("studentId"));
        this.loadData();
    },
    filters: {
        numFilter: function(value) {
            // 截取当前数据到小数点后两位
            let realVal = parseFloat(value).toFixed(2)
            return realVal
        },
        formatDate: function(val) {
            let date = new Date(val);
            return formatDate(date,'yyyy-MM-dd')
        }
    },
    methods: {
        getInfo: function(studentId){
            $.get(baseURL + "sport/grade/info?studentId="+studentId, function(r){
                vm.student = r.student;
                vm.trainGoal = r.trainGoal;
                vm.lastStuGrade = r.lastStuGrade;  //最近一次成绩
                vm.lastBmiGrade = r.lastBmiGrade;//最近一次
                vm.proFullScore = r.proFullScore;  //满配
                vm.lastProGradeList = r.lastProGradeList; //最近一次项目成绩
                vm.prevProGradeList = r.prevProGradeList;
                vm.passDesc = r.passDesc;
                vm.scoreDesc = r.scoreDesc;
                vm.radarChart = r.radarChart;
                vm.tgmd3Chart = r.tgmd3Chart;
                vm.bmiChart = r.bmiChart;

                radarChartFun(r.radarChart);
                tgmdChartFun(r.tgmd3Chart);
                bmiChartFun(r.bmiChart,r.lastBmiGrade.bmiGrade);
            });
        },
        loadData: function(){

        },
        reload: function (event) {
            vm.showList = true;

        }
    }
});