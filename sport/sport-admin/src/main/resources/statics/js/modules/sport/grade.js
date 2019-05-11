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

                debugger;
                radarChartFun(r.radarChart);
                var tgmdChart = r.tgmd3Chart;
                tgmdChartFun(tgmdChart);
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