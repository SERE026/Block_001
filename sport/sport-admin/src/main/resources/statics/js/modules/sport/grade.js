'use strict';
var baseURL = "../../";

function formatDate (date, fmt) {
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))
    }
    var o = {
        'M+': date.getMonth() + 1,
        'd+': date.getDate(),
        'h+': date.getHours(),
        'm+': date.getMinutes(),
        's+': date.getSeconds()
    }
    for (var k in o) {
        if (new RegExp("("+ k +")").test(fmt)) {
            var str = o[k] + ''
            fmt = fmt.replace(RegExp.$1, RegExp.$1.length === 1 ? str : padLeftZero(str))
        }
    }
    return fmt
}
function padLeftZero (str) {
    return ('00' + str).substr(str.length)
}

var url = function(name) {
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
};
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
            var realVal = parseFloat(value).toFixed(2)
            return realVal
        },
        formatDate: function(val) {
            var date = new Date(val);
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