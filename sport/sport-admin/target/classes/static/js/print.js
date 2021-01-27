'use strict';

function jqPrint(id) {

    //210×297；
    $("#"+id).css("width","210mm");
    $("#"+id).css("height","297mm");
    var printFlag = true;
    $('#barChartImg').attr("src",tgmdChart.getDataURL());
    $('#barChartImg').on('load', function() {
        $('#bmiChartImg').attr("src",bmiChart.getDataURL());
        $('#bmiChartImg').on('load', function() {
            $('#radarImg').attr('src',radarChart.getDataURL());
            $('#radarImg').on('load', function() {
                $('#barChartImg').show();
                $('#bmiChartImg').show();
                $('#radarImg').show();

                $('#radarId').hide();
                $('#barChart').hide();
                $('#bmiChart').hide();
                if(!printFlag){
                    return;
                }
                $("#"+id).jqprint({
                    globalStyles: true,
                    debug: false,
                    importCSS: true,
                    printContainer: true,
                    operaSupport: false
                });

                printFlag = false;
                //window.location.reload();
            })
        })
    });
    // 图片加载完成之后

}

