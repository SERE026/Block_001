$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sport/projectconfig/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
            { label: '项目ID', name: 'projectId', index: 'project_id', width: 80 },
            { label: '项目类型', name: 'projectType', index: 'project_type', width: 80 },
			{ label: '训练项目', name: 'projectName', index: 'project_name', width: 150  },
			{ label: '分值范围', name: 'score', index: 'min_score', width: 80,
                formatter: function (cellvalue, options, rowObject) {
                    return rowObject.minScore+"-"+rowObject.maxScore;
                }
            },
			//{ label: '分值范围最高值', name: 'maxScore', index: 'max_score', width: 80 },
			{ label: '综合评分级别', name: 'scoreLevel', index: 'score_level', width: 80 }, 			
			{ label: '年龄段', name: 'age', index: 'age', width: 80 ,
                formatter: function (cellvalue, options, rowObject) {
                    return rowObject.minAge+"-"+rowObject.maxAge;
                }
            },
			{ label: '性别', name: 'gender', index: 'gender', width: 80 ,
                formatter: function (cellvalue, options, rowObject) {
                    if(cellvalue == 1) return '男'; else return '女';
                }
            },
			{ label: '备注', name: 'remark', index: 'remark', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }, 			
			{ label: '修改时间', name: 'updateTime', index: 'update_time', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 50,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        sortname: 'id',

        /*grouping:true,
        groupingView : {
            groupField : ['projectType','projectId'],//分组属性，多个用逗号分隔
            groupColumnShow : [true,true],//是否显示分组列
            //groupText : ['<b>{0} - {1} 条记录</b>'],//表头显示数据(每组中包含的数据量)
            groupCollapse :false,//加载数据时是否只显示分组的组信息
            groupSummary : [false,false],//是否显示汇总  如果为true需要在colModel中进行配置summaryType:'max',summaryTpl:'<b>Max: {0}</b>'
            groupDataSorted : false,//分组中的数据是否排序
            groupOrder:['desc','desc'] , //分组后组的排列顺序
            //showSummaryOnHide: true//是否在分组底部显示汇总信息并且当收起表格时是否隐藏下面的分组
        },*/
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
		projectConfig: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.projectConfig = {};
            vm.getProjectList();
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
                var url = vm.projectConfig.id == null ? "sport/projectconfig/save" : "sport/projectconfig/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.projectConfig),
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
			$.get(baseURL + "sport/projectconfig/info/"+id, function(r){
                vm.projectConfig = r.projectConfig;
            });
		},
        getProjectList: function(id){
            //动态生成select内容
            $.ajax({
                type:"post",
                async:false,
                url:baseURL + 'sport/project/list',
                data:{
                    page:1,
                    limit: 1000
                },
                success:function(data){
                    var jsonobj = data.page.list;
                    if (jsonobj != null) {
                        var length=jsonobj.length;
                        $.each(jsonobj, function(i){
                            $("<option value='" + jsonobj[i].id + "'>" + jsonobj[i].projectName+ "</option>").appendTo($("#projectId"));
                        });
                    }
                }
            });
        },
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page,
                postData:{
                    'age':$("#ageInput").val(),
                    'gender':$("#genderSelect").val(),
                    'projectName':$("#projectNameInput").val()
                }, //发送数据
            }).trigger("reloadGrid");
		}
	}
});