function isRealDate(datestr) {
	var reg = /(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)/;
    var result = datestr.match(reg);
    if (result == null) {
        return false;
    }
    return true;
}

String.prototype.trim = function() {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}

//此方法以上面效果一致
function isDate2(datestr) {
    var result = datestr.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
    if (result == null) return false;
    var d = new Date(result[1], result[3] - 1, result[4]);
    if ((d.getFullYear() == result[1] && (d.getMonth() + 1) == result[3] && d.getDate() == result[4])) {
        return true;
    }
    return false;
}

//判断输入的字符是否为中文
function IsChinese(str) {
    if (str.length != 0) {
        reg = /^[\u0391-\uFFE5]+$/;
        if (!reg.test(str)) {
            //alert("对不起，您输入的字符串类型格式不正确!");
            return false;
        }
    }
    return true;
}

//判断是否为空
function isEmpty(str) {
    if (str == null || typeof str == "undefined" || str.trim() == "") {
        return true;
    } else {
        return false;
    }
}

//固定电话
function testTelephone(phone) {
    var phone_reg = new RegExp(/^([+]{0,1}\d{3,4}|\d{3,4}-)?\d{7,8}$/);
    if (!phone_reg.test(phone)) {
        return false;
    }
    return true;
}
//折扣
function isDiscount(discount) {
    var phone_reg = new RegExp(/^(0([\.]\d{1,2})|1|1.00|1.0)$/);
    if (!phone_reg.test(discount)) {
        return false;
    }
    return true;
}
//手机号码
function testMobile(mobile) {
    var mobile_reg = new RegExp(/^0{0,1}1[0-9]{10}$/);
    if (!mobile_reg.test(mobile)) {
        return false;
    }
    return true;
}

//电子邮件
function testEmail(email) {
    var email_reg = new RegExp(/^\w+([-+.]\w+)*@\w+([-.]\w+)*.\w+([-.]\w+)*$/);
    if (!email_reg.test(email)) {
        return false;
    }
    return true;
}

//不带符号的正整数
function testPlusDigit(digit) {
    var plusDigit_reg = new RegExp(/^\d+$/);
    if (!plusDigit_reg.test(digit)) {
        return false;
    }
    return true;
}



//身份证
function testIDCard(str) {
    var IDCardReg = new RegExp(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/);
    if (!IDCardReg.test(str)) {
        return false;
    }
    return true;
}


//浮点数精确运算(加法)
function accAdd(arg1, arg2) {
    var r1, r2, m, n;
    try {
        r1 = arg1.toString().split(".")[1].length
    } catch(e) {
        r1 = 0
    }
    try {
        r2 = arg2.toString().split(".")[1].length
    } catch(e) {
        r2 = 0
    }
    m = Math.pow(10, Math.max(r1, r2));
    n = (r1 >= r2) ? r1: r2;
    return ((arg1 * m + arg2 * m) / m).toFixed(n);
}
Number.prototype.add = function(arg) {
    return accAdd(arg, this);
}

//浮点数精确运算(减法)
function accSub(arg1, arg2) {
    return accAdd(arg1, -arg2);
}
Number.prototype.subtract = function(arg) {
    return accSub(this, arg);
}

//浮点数精确运算(乘法)
function accMul(arg1, arg2) {
    var m = 0,
    s1 = arg1.toString(),
    s2 = arg2.toString();
    try {
        m += s1.split(".")[1].length
    } catch(e) {}
    try {
        m += s2.split(".")[1].length
    } catch(e) {}
    return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m)
}
Number.prototype.mul = function(arg) {
    return accMul(arg, this);
}

//浮点数精确运算(除法)
function accDiv(arg1, arg2) {
    var t1 = 0,
    t2 = 0,
    r1, r2;
    try {
        t1 = arg1.toString().split(".")[1].length
    } catch(e) {}
    try {
        t2 = arg2.toString().split(".")[1].length
    } catch(e) {}
    with(Math) {
        r1 = Number(arg1.toString().replace(".", "")) ;
        r2 = Number(arg2.toString().replace(".", "")) ;
        return (r1 / r2) * pow(10, t2 - t1);
    }
}
Number.prototype.div = function(arg) {
    return accDiv(this, arg);
}

//限制输入数字
function isNumber(e) {
    if ($.browser.msie) {
        if (((event.keyCode > 47) && (event.keyCode < 58)) || (event.keyCode == 8)) {
            return true;
        } else {
            return false;
        }
    } else {
        if (((e.which > 47) && (e.which < 58)) || (e.which == 8)) {
            return true;
        } else {
            return false;
        }
    }
}

//字符串长度截取
function cutstr(str, len) {
    var temp;
    var icount = 0;
    var patrn = /[^\x00-\xff]/;
    var strre = "";
    for (var i = 0; i < str.length; i++) {
        if (icount < len - 1) {
            temp = str.substr(i, 1);
            if (patrn.exec(temp) == null) {
                icount = icount + 1;
            } else {
                icount = icount + 2;
            }
            strre += temp;
        } else {
            break;
        }
    }
    return strre + "...";
}

//获取域名主机
function getHost(url) {
    var host = "null";
    if (typeof url == "undefined" || null == url) {
        url = window.location.href;
    }
    var regex = /^\w+\:\/\/([^\/]*).*/;
    var match = url.match(regex);
    if (typeof match != "undefined" && null != match) {
        host = match[1];
    }
    return host;
}

//判断某个值是否在所在范围
//rang=1 表示正整数[0,2147483647] 2表示float[0,3.4028235E38]
//return= 'empty' 表示输入为空,
function isRang(str, rang) {
    if (typeof str == "number") {
        var num = Number(str);
        //判断是否在正整数范围
        if (rang == 1) {
            if (testPlusDigit(num) == "yes") {
                if (num >= 0 && num <= 2147483647) {
                    return "is_int";
                } else {
                    return "is_not_int_rang";
                }
            } else {
                return "is_not_int";
            }
        } else if (rang == 2) {
            if (testPriceFormat(num) == "yes") {
                if (num >= 0 && num <= 3.4028235E38) {
                    return "is_float";
                } else {
                    return "is_not_float_rang";
                }
            } else {
                return "is_not_float";
            }
        } else {
            return "rang_is_not_right";
        }
    } else {
        return "is_not_number";
    }
}

function isYearMonth(cYearMonth){
	var reg = /^[1-9]{1}[0-9]{3}(0[1-9]|1[0-2])$/;
	if(cYearMonth.match(reg)){
		return true;
	}else{
		return false;
	}
}

function isObject(obj){
	return Object.prototype.toString.call(obj) === "[Object Object]";
}

function castObjKeys(obj, type){
	
	var ret = {};
	
	if(isObject(obj)){
		return obj;
	}
	
	for(key in obj){
		var newKey;
		if(type == "lower"){
			newKey = key.toLowerCase();
		}
		else if(type == "upper"){
			newKey = key.toUpperCase();
		}
		else{
			newKey = key;
		}
		
		ret[newKey] = obj[key];
	}
	
	return ret;
	
}
/**公共excel模板下载function*/

function downloadExcelTemp(temlName) {
	$('body').append("<form id='dlet'></form>");
	var options = {
		success : function() {

			$('#dlet').attr("method", "post");
			$('#dlet').attr(
					"action",
					basePath()+"file/downloadtempexcel?tempName="
							+ temlName);
			$('#dlet').submit();
		}

	};
	$("#dlet").ajaxSubmit(options);
//	$("#dlet").remove();

}

//*******处理异步遮罩
var downLoadFlag = {};

function closeShadeForDownLoad(template){
	
	var loop = setInterval(function(){
		if(downLoadFlag[template]){
			clearInterval(loop);//停止定时任务
			
			if(layer){
				layer.close(layer.index);
			}
			
		}
		else{
			getDownLoadFlag(template);
		}
	}, 500);
	
}

function getDownLoadFlag(template){
	$.ajax({
		type:"POST",
		url:basePath() + "session/getDownLoadFlag",
		dataType:"json",
		data: {template : template},
		success : function(obj){
			if(obj.code=="0"){
				downLoadFlag[template] = obj.data;
			}else{
				
			}
		
		}
	});
}
//*******处理异步遮罩


//layui的时间样式（范围时间）
function layuiDate(startdateid,enddateid) {
	var laydate = layui.laydate;
	
	// 开始时间
	 var startDate = laydate.render({
		    elem: startdateid
		    ,type: 'date'
		    ,max:"2099-12-31"
//		    ,zIndex: 99999999
		    ,change: function(value, date){
//		    	console.log(value);
		    }
		    ,done: function(value, date){
		    	endDate.config.min = {
		    		year:date.year,   
			  		month:date.month-1,  
			  		date: date.date
		    	}
		    }
	  });
	
	// 截止时间
	 var endDate = laydate.render({
		   elem: enddateid
		   ,type: 'date'
		   ,min:"1970-1-1"
//		   ,zIndex: 99999999
		   ,done: function(value, date){
		    	startDate.config.max = {
			    	year:date.year,   
				  	month:date.month-1,  
				  	date: date.date
		    	}
			 }
	  });
	
	
	$(startdateid).bind("input propertychange",function(){
		var startVal = $(this).val();
		
		if(!startVal){
			endDate.config.min = {
				year : 1970,
				month : 0,
				date : 1
			};
		}
		else{
			if(layuiDateCheck(startVal)){
				var endVal = $(enddateid).val();
				var value = getDateItems(startVal);
				
				endDate.config.min = {
					year:parseInt(value.year,10),   
				  	month:parseInt(value.month,10)-1,  
				  	date: parseInt(value.date,10)
				}
					
				if(startVal > endVal){
					$(enddateid).val("");
					
//					startDate.config.max = {
//						year:2099,   
//						month:11,  
//						date: 31
//					}
				}
				
			}
		}
		
	});
	
	$(enddateid).bind("input propertychange",function(){
		var endVal = $(this).val();
		
		if(!endVal){
			startDate.config.max = {
				year : 2099,
				month : 11,
				date : 31
			};
		}
		else{
			if(layuiDateCheck(endVal)){
				var startVal = $(startdateid).val();
				var value = getDateItems(endVal);
				
				startDate.config.max = {
					year:parseInt(value.year,10),   
				  	month:parseInt(value.month,10)-1,  
				  	date: parseInt(value.date,10)
				}
					
				if(startVal > endVal){
					$(startdateid).val("");
					
//					endDate.config.min = {
//						year:1970,   
//						month:0,  
//						date: 1
//					}
				}
				
			}
		}
		
	});
	
	//根据enddate的值重新定义最大值
	$(startdateid).focus(function(){
		var startVal = $(startdateid).val();
		var endVal = $(enddateid).val();
		
		var value = getDateItems(endVal);
		  
		if($.isEmptyObject(value)){
	    	startDate.config.max={
	    		year:"2099",   
			  	month:"11",  
			  	date: "1"
			  };
	      }
	      else{
	    	 startDate.config.max={
	  		    year:parseInt(value.year,10),   
	  		    month:parseInt(value.month,10)-1,  
	  		    date: parseInt(value.date,10)
	  		 }
	    	 
	    	 if(startVal > endVal){
	    		 $(startdateid).val("");
	    	 }
	      }
	  });
	
	//判断enddate的值<min，则需要清空
//	$(startdateid).blur(function(){
//		var startVal = $(startdateid).val();
//		var endVal = $(enddateid).val();
//		
//		var value = getDateItems(startVal);
//		
//		if(endVal){		
//			if(endVal < startVal){
//				$(enddateid).val("");
//			}
//		}
//		
//		endDate.config.min = {
//			year:parseInt(value.year,10),   
//		  	month:parseInt(value.month,10)-1,  
//		  	date: parseInt(value.date,10)
//		}
//		
//	});
	
	//根据startdate定义最小值
	$(enddateid).focus(function() {
		var startVal = $(startdateid).val();
		var endVal = $(enddateid).val();
		
		var value = getDateItems(startVal);

		if ($.isEmptyObject(value)) {
			endDate.config.min = {
				year : "1970",
				month : "0",
				date : "1"
			};
		} else {
			endDate.config.min = {
				year:parseInt(value.year,10),   
		  		month:parseInt(value.month,10)-1,  
		  		date: parseInt(value.date,10)
			}
			
			if(startVal > endVal){
				$(enddateid).val("");
			}
		}
	});
//	
	//判断startdate的值>max，则需要清空
//	$(enddateid).blur(function(){
//		var startVal = $(startdateid).val();
//		var endVal = $(enddateid).val();
//		
//		var value = getDateItems(endVal);
//		
//		if(startVal){			
//			if(startVal > endVal){
//				$(startdateid).val("");
//			}
//		}
//		
//		startDate.config.max = {
//			year:parseInt(value.year,10),   
//		  	month:parseInt(value.month,10)-1,  
//		  	date: parseInt(value.date,10)
//		}
//		
//	});
	  
}

// 将日期拆分成一个对象
// 2019-09-09 23:00:00 ==> {year:"2019", month: "09", date:"09", hour: "23",
// min:"00", second: "00"}
function getDateItems(value){
	if(!value){
		return {};
	}
	var arr = value.replace(" ", "-").replace(/\:/g, "-").split("-");
	var o = {
		year : arr[0] || "",
		month: arr[1] || "",
		date: arr[2] || "",
		hour: arr[3] || "",
		min: arr[4] || "",
		second: arr[5] || ""
	};
	
	return o;
	
}

//layui的时间格式校验
function layuiDateCheck(date,id) {
    var yyyymmdd = /^([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-9])))$/;
    if((date != undefined && date != "") && !yyyymmdd.test(date)){
    	if(id){
    		layer.tips('日期格式不对！', id, {tips: [2, '#FF5722']});
    	}
        return false;
    }
    return true;
}

//layui的时间范围大小校验
function layuiDateLimitCheck(startdate,enddate,id) {
	//开始时间不能早于截止时间
	if (startdate != undefined && startdate != ""
			&& enddate != undefined && enddate != "") {

		var startTmp = startdate.replace(/\:/g, "-").split("-");
		var endTmp = enddate.replace(/\:/g, "-").split("-");
		var sd = new Date(startTmp[0], startTmp[1], startTmp[2]);
		var ed = new Date(endTmp[0], endTmp[1], endTmp[2]);

		if (sd.getTime() > ed.getTime()) {
			// layer.alert("签约开始时间不能早于签约截止时间！");
			//layer.tips('开始时间不能早于截止时间！', id, {tips : [2, '#FF5722']});
			layer.msg('开始时间不能早于截止时间！', {icon: 5});
			return false;
		}
	}
	return true;
}

//layui 文件上传重新resetform
function resetUploadForm(){
	var tt = $('.layui-upload-form')[0];
    if(tt!=undefined){
     $('.layui-upload-form')[0].reset();
    }
}


/**
 * 选择定义好的颜色type:
 * 1：原始数据#bebfc1;（灰色）
 * 2：提交审核#ddeb5d;（黄色）
 * 3：通过#95f4d4;（绿色）
 * 4：不通过#f9b2a4;（红色）
 * @param type（颜色的选择，如上1234）
 * @param title（要显示的标题信息）
 * @returns 字符串
 */
function flagSelColor(type,title){
	if(type==1){
		return "<div style='height: 20px;line-height: 20px;'>"+title+"</div>";
	}
	if(type==2){
		return "<div style='height: 20px;line-height: 20px;'>"+title+"</div>";
	}
	if(type==3){
		return "<div style='height: 20px;line-height: 20px;'>"+title+"</div>";
	}
	if(type==4){
		return "<div style='height: 20px;line-height: 20px;'>"+title+"</div>";
	}
	
}

/**
 * 自定义颜色
 * @param color（自定义颜色符）
 * @param title（要显示的标题信息）
 * @returns 字符串
 */
function flagDiyColor(color,title){
	return "<div style='height: 20px;line-height: 20px;background-color:"+color+";' class='layui-btn layui-btn-sm'>"+title+"</div>";	
}


function initForm(options){  
            //默认参数  
            var defaults = {  
                jsonValue:options,  
                isDebug:false   //是否需要调试，这个用于开发阶段，发布阶段请将设置为false，默认为false,true将会把name value打印出来  
            }  
            //设置参数  
            var setting = defaults;  
            var form = this;  
            jsonValue = setting.jsonValue;  
            //如果传入的json字符串，将转为json对象  
            if($.type(setting.jsonValue) === "string"){  
                jsonValue = $.parseJSON(jsonValue);  
            }  
            //如果传入的json对象为空，则不做任何操作  
            if(!$.isEmptyObject(jsonValue)){  
                var debugInfo = "";  
                $.each(jsonValue,function(key,value){  
                    //是否开启调试，开启将会把name value打印出来  
                    if(setting.isDebug){  
                        alert("name:"+key+"; value:"+value);  
                        debugInfo += "name:"+key+"; value:"+value+" || ";  
                    }  
                    var formField = form.find("[name='"+key+"']");  
                    if($.type(formField[0]) === "undefined"){  
                        if(setting.isDebug){  
                            alert("can not find name:["+key+"] in form!!!");    //没找到指定name的表单  
                        }  
                    } else {  
                        var fieldTagName = formField[0].tagName.toLowerCase();  
                        if(fieldTagName == "input"){  
                            if(formField.attr("type") == "radio"){  
                                $("input:radio[name='"+key+"'][value='"+value+"']").attr("checked","checked");  
                            } else {  
                                formField.val(value);  
                            }  
                        } else if(fieldTagName == "select"){  
                            //do something special  
                            formField.val(value);  
                        } else if(fieldTagName == "textarea"){  
                            //do something special  
                            formField.val(value);
                        } else {  
                            formField.val(value);  
                        }  

                    }  
                })  
                if(setting.isDebug){  
                    alert(debugInfo);  
                }  
           }  
      return form;    //返回对象，提供链式操作  
}  
 



