$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sport/student/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '学号', name: 'stuNumber', index: 'stu_number', width: 80 }, 			
			{ label: '联系方式', name: 'mobile', index: 'mobile', width: 80 }, 			
			{ label: '学生姓名', name: 'realname', index: 'realname', width: 80 }, 			
			{ label: '性别', name: 'gender', index: 'gender', width: 60 ,
                formatter: function (cellvalue, options, rowObject) {
                    if(cellvalue == 1) return '男'; else return '女';
                }
            },
			{ label: '出生日期', name: 'birthday', index: 'birthday', width: 80 }, 			
			{ label: '家长姓名', name: 'familyName', index: 'family_name', width: 80 }, 			
			{ label: '家长联系方式', name: 'familyMobile', index: 'family_mobile', width: 100 },
			{ label: '注册/报名时间', name: 'registerTime', index: 'register_time', width: 100 },
            //{ label: '备注', name: 'remark', index: 'remark', width: 120 },
            { label: '创建时间', name: 'createTime', index: 'create_time', width: 80 },
			{ label: '修改时间', name: 'updateTime', index: 'update_time', width: 80 },
            { label: '操作', name: 'state', index: 'state', width: 200, edittype:"button",
                formatter: function(cellVal,grid,rows,id){
                    let addGradeBtn = "<button class='btn btn-primary ' onclick='vm.addGrade("+rows.id+")' >添加成绩</button>" ;

                    let updateLastGradeBtn = "<button class='btn btn-primary ' onclick='vm.updateGrade("+rows.id+")' >修改成绩</button>" ;
                    let queryGrade =  "<a class='btn btn-warning' target='_blank' href='/admin/sport/grade/page?studentId="+rows.id+"'>查看</a>" ;
                    if(rows.gradeFlag == 1){
                        return addGradeBtn + updateLastGradeBtn + queryGrade;
                    }
                    return addGradeBtn;
                }
            }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        // rownumbers: true,
        // rownumWidth: 25,
        autowidth:true,
        multiselect: true,
        shrinkToFit:false,
        autoScroll: true, //开启水平滚动条
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	//$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    })
        //.navGrid('#jqGridPager',{edit:false,add:true,del:false,search:false})
    ;
});



var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
        showGrade: true,
		title: null,
        project: {}, //点击添加成绩弹出项目
        inputGradeParam:{}, //添加成绩信息
        rowData: {},  //选中行数据
		student: {},   //学生信息
        studentId: null,
        layerIndex: null,
        checkTime: null,
        schoolList: {},
        lastProGradeList: {},
        bmiGrade: {},
        lastStuGrade:{},
        isUpdateProGrade: false
	},
    mounted: function(){
	    this.getSchoolList();
	    this.layDateRegisterTime();
	    this.layDateBirthday();
	    this.layDateFun();
    },
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.student = {};
            vm.getProvince();
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.student.id == null ? "sport/student/save" : "sport/student/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.student),
                    success: function(r){
                        if(r.code === 0){
                             layer.msg("操作成功", {icon: 1});
                             vm.reload();
                             $('#btnSaveOrUpdate').button('reset');
                             $('#btnSaveOrUpdate').dequeue();
                        }else{
                            layer.alert(r.msg);
                            $('#btnSaveOrUpdate').button('reset');
                            $('#btnSaveOrUpdate').dequeue();
                        }
                    }
                });
			});
		},

		getInfo: function(id){
			$.get(baseURL + "sport/student/info/"+id, function(r){
                vm.student = r.student;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page,
                realname: $("#realnameInput").val()
            }).trigger("reloadGrid");
		},
        getSchoolList: function(){
            //动态生成select内容
            $.ajax({
                type:"post",
                async:false,
                url:baseURL + 'sport/school/list',
                data:{
                    page:1,
                    limit: 1000
                },
                success:function(data){
                    var jsonobj = data.page.list;
                    if (jsonobj != null) {
                        var length=jsonobj.length;
                        $.each(jsonobj, function(i){
                            $("<option value='" + jsonobj[i].id + "'>" + jsonobj[i].schoolName+ "</option>").appendTo($("#schoolId"));
                        });
                    }
                }
            });
        },
        layerClose: function(event){
		   layer.close(vm.layerIndex);
        },
        getProjectInfo: function(id){
            vm.rowData = getSelectedRowData(id);
            var birthday = vm.rowData.birthday;
            vm.studentId = vm.rowData.id;
            $.get(baseURL + "sport/project/listByBirthday?birthday="+birthday, function(r){
                vm.project = r.data;
                $("#form-project-group").empty();
                $("#form-project-group").append("<div class=\"form-group\">")
                for(i=0; i<r.data.length;i++) {
                    var pro = r.data[i];
                    let input = "<div class='col-sm-3 control-label'>"+pro.projectName+"</div> <div class='col-sm-3'>" +
                        "<input type='text' class='form-control' projectcode='"+pro.projectCode+"' projectid='"+pro.id+"' onchange='vm.getGradeParams(this)' placeholder='请输入'/>" +
                        "</div>"
                    if(i>0 && i%2==0){
                        $("#form-project-group").append("</div><div class=\"form-group\">")
                        $("#form-project-group").append(input);
                    }else{
                        $("#form-project-group").append(input);
                    }
                    if(i==r.data.length-1){
                        $("#form-project-group").append("</div>");
                    }
                }
            });
        },
        getLastProjectGradeList: function(id){
            vm.rowData = getSelectedRowData(id);
            var birthday = vm.rowData.birthday;
            var studentId = vm.rowData.id;
            vm.studentId = studentId;
            $.get(baseURL + "sport/project/listByBirthday?birthday="+birthday, function(r){
                vm.project = r.data;
                $.get(baseURL + "sport/grade/lastProGradeList?studentId="+studentId, function(resultData){
                    vm.lastProGradeList = resultData.lastProGradeList;
                    vm.bmiGrade = resultData.bmiGrade;
                    vm.lastStuGrade = resultData.lastStuGrade;
                     $("#height").val(resultData.bmiGrade.height);
                     $("#weight").val(resultData.bmiGrade.weight);
                     $("#teacherName").val(resultData.lastStuGrade.teacherName);
                     $("#trainHours").val(resultData.lastStuGrade.trainHours);
                     $("#attendance").val(resultData.lastStuGrade.attendance);
                     $("#checkTime").val(resultData.lastStuGrade.checkTime);
                    $("#form-project-group").empty();
                    $("#form-project-group").append("<div class=\"form-group\">")
                    for(var i=0; i<r.data.length;i++) {
                        var pro = r.data[i];
                        var proGradeVal = '';
                        for(var j=0;j<resultData.lastProGradeList.length;j++){
                            var prevPro = resultData.lastProGradeList[j];
                            if(pro.id== prevPro.projectId){
                                proGradeVal = prevPro.projectGrade;
                                let project = {
                                    projectId: prevPro.projectId,
                                    projectCode: prevPro.projectCode,
                                    proGrade: proGradeVal
                                }
                                vm.inputGradeParam[prevPro.projectCode] = project;
                            }
                        }

                        let input = "<div class='col-sm-3 control-label'>"+pro.projectName+"</div> <div class='col-sm-3'>" +
                            "<input type='text' class='form-control' value='"+proGradeVal+"' projectcode='"+pro.projectCode+"' projectid='"+pro.id+"' onchange='vm.getGradeParams(this)' placeholder='请输入'/>" +
                            "</div>"
                        if(i>0 && i%2==0){
                            $("#form-project-group").append("</div><div class=\"form-group\">")
                            $("#form-project-group").append(input);
                        }else{
                            $("#form-project-group").append(input);
                        }
                        if(i==r.data.length-1){
                            $("#form-project-group").append("</div>");
                        }
                    }
                });

            });
        },
        addGrade: function(id){
            vm.isUpdateProGrade=false;
		    vm.inputGradeParam = {};
		    vm.showGrade = false;
		    vm.lastBmiGrade = {};
		    vm.studentGrade={};
            $("#height").val();
            $("#weight").val();
            $("#teacherName").val();
            $("#trainHours").val();
            $("#attendance").val();
            $("#checkTime").val();
            vm.getProjectInfo(id);
            vm.layerIndex = layer.open({
                type: 1,
                skin: 'layui-layer-rim', //加上边框
                area: ['80%', '450px'], //宽高
                closeBtn: 1, //不显示关闭按钮
                anim: 2,
                shadeClose: true, //开启遮罩关闭
                title: '添加体测数据',
                content: $("#addGradeId")
            });
        },
        updateGrade: function(id){
            vm.isUpdateProGrade = true;
            vm.showGrade = false;
            //获取最近一次测试成绩
            vm.getLastProjectGradeList(id);
            vm.layerIndex = layer.open({
                type: 1,
                skin: 'layui-layer-rim', //加上边框
                area: ['80%', '450px'], //宽高
                closeBtn: 1, //不显示关闭按钮
                anim: 2,
                shadeClose: true, //开启遮罩关闭
                title: '修改体测数据',
                content: $("#addGradeId")
            });
        },
        getGradeParams: function(event){
		    let proId = $(event).attr("projectid");
            let proCode = $(event).attr("projectcode");
		    let value = $(event).val();
		    let project = {
		        projectId: proId,
                projectCode: proCode,
                proGrade: value
            }
		    vm.inputGradeParam[proCode] = project;
        },
        saveOrUpdateProjectGrade: function (event) {
		    let proList = []
            for(let k in vm.inputGradeParam){
                proList.push(vm.inputGradeParam[k]);
            }
            let data = {
                    studentId: vm.studentId,
                    height: $("#height").val(),
                    weight: $("#weight").val(),
                    teacherName: $("#teacherName").val(),
                    trainHours: $("#trainHours").val(),
                    attendance:$("#attendance").val(),
                    checkTime: $("#checkTime").val(),
                    proList: proList
                };
            $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                let url = vm.isUpdateProGrade ? "sport/grade/update" : "sport/grade/save";
                $.ajax({
                    type: "POST",
                    url: baseURL+url,
                    contentType: "application/json",
                    //data: vm.inputGradeParam,
                    data:JSON.stringify(data),
                    success: function(r){
                        if(r.code === 0){
                            layer.msg("操作成功", {icon: 1});
                            vm.reload();
                            $('#saveStudentGrade').button('reset');
                            $('#saveStudentGrade').dequeue();
                        }else{
                            layer.alert(r.msg);
                            $('#btnSaveOrUpdate').button('reset');
                            $('#btnSaveOrUpdate').dequeue();
                        }
                        layer.close(vm.layerIndex);
                    }
                });
            })

        },
        layDateFun: function (event) {
            layui.use('laydate', function(){
                var laydate = layui.laydate.render({
                    elem: '#checkTime', //指定元素
                    type: 'datetime',
                    done: function(value, date, endDate){
                        alert(value)
                        vm.checkTime = value;
                    }
                });
            });
        },
        layDateRegisterTime: function (event) {
            layui.use('laydate', function(){
                layui.laydate.render({
                    elem: '#registerTime', //指定元素
                    type: 'datetime',
                    done: function(value, date, endDate){
                        vm.student.registerTime = value;
                    }
                });
            });
        },
        layDateBirthday: function (event) {
            layui.use('laydate', function(){
                layui.laydate.render({
                    elem: '#birthday', //指定元素
                    type: 'datetime',
                    done: function(value, date, endDate){
                        vm.student.birthday = value;
                    }
                });
            });
        },

        getProvince: function(){
            loadProvince($("#province"),'');
        },
        fillCityByParentId: function(){
            $("#city").empty();
            doProvAndCityRelation($("#province"),$("#city"),null,'');
        }
	}
});


/*layui.use('laydate', function(){
    var laydate = layui.laydate.render({
        elem: '#checkTime', //指定元素
        type: 'datetime',
        event: 'focus'
        // done: function(value, date, endDate){
        //     alert(value); //得到日期生成的值，如：2017-08-18
        //     vm.checkTime = value;
        // }
    });

    var laydateRegTime = layui.laydate.render({
        elem: '#registerTime', //指定元素
        type: 'datetime',
        event: 'focus'
    });

    var laydateBirthDay = layui.laydate.render({
        elem: '#birthday', //指定元素
        type: 'datetime',
        event: 'focus'
    });
});*/

