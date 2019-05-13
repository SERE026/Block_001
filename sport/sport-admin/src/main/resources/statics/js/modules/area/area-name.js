/**
 * @author：shangYaNan
 * @Date： 11:08 2019/4/14
 * @Description：//TODO  三级联动省市县
 * @Modified By：
 * @Version:
 */
/*全局变量(json文本内容)*/
var cityJson;

/**
 *获取area.json内容,初始化cityJson
 */
/**
 *获取area.json内容,初始化cityJson
 */
function loadCityData(){
    if(cityJson) return;
    $.getJSON(
        "/admin/statics/js/modules/area/area.json",
        function (obj) {
            cityJson=obj;
        }
    );
}
loadCityData();
/**
 * 加载省份时,如果cityJson!=null ,不在重复获取
 * @param province
 * @param provinceVal
 */
function loadProvince(province, provinceVal) {
    $.each(cityJson, function (index, val) {
        var code = val.code;
        if (code.substr(2, 4) == '0000') {
            if (code == provinceVal) {
                $(province).append("<option value='" + val.name + "' selected='selected'>" + val.name + "</option>");
            } else {
                $(province).append("<option value='" + val.name + "'>" + val.name + "</option>");
            }
        }
    });

}

/**
 *  省值变化时 处理市
 * @param province
 * @param city
 * @param area
 * @param cityVal
 */
function doProvAndCityRelation(province,city,area,cityVal){
    $(city).empty().append("<option value='' selected='selected'>请选择您所在城市</option>");
    if(area)
        $(area).empty().append("<option  value=''selected='selected'>请选择您所在区/县</option>");
   /* $(city).append("<option value=''>请选择您所在城市</option>");
    $(area).append("<option  value=''>请选择您所在区/县</option>");*/
    $.each(cityJson, function(i, val) {
        var code=val.name;
        if(code== $(province).val()){
            var cityList=val.cityList;
            $.each(cityList,function (i, val) {
                if (val.name == cityVal) {
                    $(city).append("<option value='"+val.name+"' selected='selected'>"+val.name+"</option>");
                } else {
                    $(city).append("<option value='"+val.name+"'>"+val.name+"</option>");
                }
            })
            return;
        }
    });
}

/**
 * 市值变化时 处理区/县
 * @param province
 * @param city
 * @param area
 * @param areaVal
 */
function doCityAndCountyRelation(province,city,area,areaVal){
    var cityVal = $(city).val();
    $(area).empty();
    $(area).append("<option  value='' selected='selected'>请选择您所在区/县</option>");
    $.each(cityJson, function(i, val) {
        var code=val.name;
        if(code== $(province).val()){
            var cityList=val.cityList;
            $.each(cityList,function (indexCity, valCity) {
                var cityCode=valCity.name;
                if(cityCode== $(city).val()){
                    var areaList=valCity.areaList;
                    if (areaList.length <= 0) {
                        if (valCity.code == areaVal) {
                            $(area).append("<option value='"+valCity.name+"' selected='selected'>"+valCity.name+"</option>");
                        } else {
                            $(area).append("<option value='"+valCity.name+"'>"+valCity.name+"</option>");
                        }
                        return;
                    }
                    $.each(areaList,function (indexArea, valArea) {
                        if (valArea.name == areaVal) {
                            $(area).append("<option value='"+valArea.name+"' selected='selected'>"+valArea.name+"</option>");
                        } else {
                            $(area).append("<option value='"+valArea.name+"'>"+valArea.name+"</option>");
                        }
                    });
                    return;
                }
            })
            return;
        }
    });
};


