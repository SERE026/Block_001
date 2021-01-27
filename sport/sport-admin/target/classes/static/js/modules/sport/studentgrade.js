$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sport/studentgrade/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '学号', name: 'studentId', index: 'student_id', width: 80 },
			{ label: '年龄段', name: 'ageRange', index: 'age_range', width: 80 }, 			
			{ label: '测试员', name: 'teacherName', index: 'teacher_name', width: 80 }, 			
			{ label: '身高/cm', name: 'height', index: 'height', width: 80 }, 			
			{ label: '体重/kg', name: 'weight', index: 'weight', width: 80 }, 			
			{ label: '速度 m/s', name: 'speed', index: 'speed', width: 80 }, 			
			{ label: '下肢力量', name: 'lowStrength', index: 'low_strength', width: 80 }, 			
			{ label: '上肢力量', name: 'upperStrength', index: 'upper_strength', width: 80 }, 			
			{ label: '柔韧性', name: 'flexibility', index: 'flexibility', width: 80 }, 			
			{ label: '心肺能力', name: 'cardiopulmonary', index: 'cardiopulmonary', width: 80 }, 			
			{ label: '核心力量', name: 'coreStrength', index: 'core_strength', width: 80 }, 			
			{ label: 'TGMD-3测试', name: 'tgmd3Check', index: 'tgmd3_check', width: 80 }, 			
			{ label: '检测时间', name: 'checkDate', index: 'check_date', width: 80 }, 			
			{ label: '综合评分', name: 'score', index: 'score', width: 80 }, 			
			{ label: '评测建议', name: 'suggestion', index: 'suggestion', width: 80 }, 			
			{ label: '备注', name: 'remark', index: 'remark', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }, 			
			{ label: '修改时间', name: 'updateTime', index: 'update_time', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
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
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		studentGrade: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.studentGrade = {};
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
                var url = vm.studentGrade.id == null ? "sport/studentgrade/save" : "sport/studentgrade/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.studentGrade),
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
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			var lock = false;
            layer.confirm('确定要删除选中的记录？', {
                btn: ['确定','取消'] //按钮
            }, function(){
               if(!lock) {
                    lock = true;
		            $.ajax({
                        type: "POST",
                        url: baseURL + "sport/studentgrade/delete",
                        contentType: "application/json",
                        data: JSON.stringify(ids),
                        success: function(r){
                            if(r.code == 0){
                                layer.msg("操作成功", {icon: 1});
                                $("#jqGrid").trigger("reloadGrid");
                            }else{
                                layer.alert(r.msg);
                            }
                        }
				    });
			    }
             }, function(){
             });
		},
		getInfo: function(id){
			$.get(baseURL + "sport/studentgrade/info/"+id, function(r){
                vm.studentGrade = r.studentGrade;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});