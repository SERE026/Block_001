function print(){
    //获取打印的页面内容
    let subOutputRankPrint = document.getElementById('main');
    let newContent = subOutputRankPrint.innerHTML;
    let oldContent = document.body.innerHTML;
    document.body.innerHTML = newContent;
    //页面打印缩放比例设置
   // document.getElementsByTagName('body')[0].style.zoom=0.92;
    subOutputRankPrint.style.zoom=0.92
    //检测是否是IE 如果是ie进行页眉页脚
    if(!!window.ActiveXObject || "ActiveXObject" in window){
        var hkey_root, hkey_path, hkey_key;
        hkey_path = "HKEY_CURRENT_USER\\Software\\Microsoft\\InternetExplorer\\PageSetup\\";
        try {
            var RegWsh = new ActiveXObject("WScript.Shell");
            RegWsh.RegWrite(hkey_path + "header", "");
            RegWsh.RegWrite(hkey_path + "footer", "");
        } catch (e) {}
    }
    window.print();
    window.location.reload();
    //将原有页面还原到页面
    document.body.innerHTML = oldContent;
    return false;
}


function jqPrint(id) {

    $("#"+id).css("width","155mm");
    $("#"+id).css("height","205mm");

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
                $("#"+id).jqprint({
                    globalStyles: true,
                    debug: false,
                    importCSS: true,
                    printContainer: true,
                    operaSupport: false
                });
            })
        })
    });
    // 图片加载完成之后

}

