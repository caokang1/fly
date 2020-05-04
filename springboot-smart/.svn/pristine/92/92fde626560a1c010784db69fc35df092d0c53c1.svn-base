function basePath(){
	//获取当前网址，如： http://localhost:8080/ccms/Pages/Basic/Person.jsp
	var curWwwPath = window.document.location.href;
	//获取主机地址之后的目录，如： /ccms/Pages/Basic/Person.jsp
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	//获取主机地址，如： http://localhost:8080
	var localhostPath = curWwwPath.substring(0, pos);
	//获取带"/"的项目名，如：/ems
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	//获取项目的basePath   http://localhost:8080/ccms/
	var basePath=localhostPath+projectName+"/";
	return basePath;
};

function IEVersion() {
	var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
	var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1; //判断是否IE<11浏览器
	var isEdge = userAgent.indexOf("Edge") > -1 && !isIE; //判断是否IE的Edge浏览器
	var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
	if(isIE) {
		var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
		reIE.test(userAgent);
		var fIEVersion = parseFloat(RegExp["$1"]);
		if(fIEVersion == 7) {
			return 7;
		} else if(fIEVersion == 8) {
			return 8;
		} else if(fIEVersion == 9) {
			return 9;
		} else if(fIEVersion == 10) {
			return 10;
		} else {
			return 6;//IE版本<=7
		}
	} else if(isEdge) {
		return 'edge';//edge
	} else if(isIE11) {
		return 11; //IE11
	}else{
		return -1;//不是ie浏览器
	}
}

var plugins = {
//	xmSelect: 'xm-select',
//	smart: 'smart',
	formSelects: 'formSelects-v4'
}

if(IEVersion() == -1 || IEVersion() >= 10 || IEVersion() == "edge"){
	$.extend(plugins,{
		xmSelect: 'xm-select',
		smart: 'smart'
	});
}
else{
	$.extend(plugins,{
		smart: 'smart-for-ie'
	});
}

//配置layui全局扩展插件
layui.config({
	base: basePath() + 'pages/plugins/layui-extends/'
}).extend(plugins);

//禁用Backspace按钮 防止误操作导致页面后退
$(document).keydown(function(event){
		if(event.keyCode == 8){
			var ev =  event;//获取event对象
			var obj = ev.target || ev.srcElement;//获取事件源
			var t = obj.type || obj.getAttribute('type');//获取事件源类型

			//获取作为判断条件的事件类型
			var vReadOnly = obj.getAttribute('readonly');
			var vEnabled = obj.getAttribute('enabled');
			//处理null值情况
			vReadOnly = (vReadOnly == null) ? false : vReadOnly;
			vEnabled = (vEnabled == null) ? true : vEnabled;
			//当敲Backspace键时，事件源类型为密码或单行、多行文本的，
			//并且readonly属性为true或enabled属性为false的，则退格键失效
			var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea")
				&& (vReadOnly==true || vEnabled!=true))?true:false;

			//当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
			var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea")
				?true:false;
			//判断
			if(flag2||flag1){
				//禁止后退键 作用于Firefox、Opera
//				document.onkeypress=banBackSpace;
				//禁止后退键 作用于IE、Chrome
//				document.onkeydown=banBackSpace;
				return false;
			}

		}
	}
);

(function($){
	//备份jquery的ajax方法
	var _ajax=$.ajax;
	//遮罩
	var index  ;
	//重写jquery的ajax方法
	$.ajax=function(opt){
		//备份opt中error和success方法
		var fn = {
			error:function(XMLHttpRequest, textStatus, errorThrown){},
			success:function(data, textStatus){},
			beforeSend:function(){}
		}
		if(opt.error){
			fn.error=opt.error;
		}
		if(opt.success){
			fn.success=opt.success;
		}
		if(opt.beforeSend){
			fn.beforeSend=opt.beforeSend;
		}

		//扩展增强处理
		var _opt = $.extend(opt,{
			error:function(XMLHttpRequest, textStatus, errorThrown){
				if( opt.mask){
					layer.close(index);  //关闭遮罩
				}
				//错误方法增强处理
				if (layer) {
					layer.msg("出错了,请联系管理员!", 2, 3, null, true);
				} else {
					alert("出错了,请联系管理员!");
				}
				fn.error(XMLHttpRequest, textStatus, errorThrown);
			},
			beforeSend : function(){
//		            	if(!fn.beforeSend()){
//		            		return false;
//		            	}
				fn.beforeSend();
				if(opt.mask){
					index = layer.load(1, {shade: [0.2] }); //打开遮罩
				}
			},
			success:function(response, textStatus){
				if(opt.mask){
					layer.close(index);  //关闭遮罩
				}
				//成功回调方法增强处理
				var code = response.code;
				if (code != undefined && code != "0") {
					//Code = 1 为 Excel导入返回信息
					if(code == "1"){
						var errList =  response.data;
						var errMsgHTML = "";
						//循环拼接错误信息
						if(errList.length >=1000){
							//errMsgHTML += "<p  ><span class = 'smart-importerrlist'>错误信息只展示前1000条</span></p>";
							errMsgHTML += "<p  ><span class = 'smart-importerrlist'>前1000条错误信息如下，超过部分不展示！</span></p>";
						}

						for(var i=0;i<errList.length;i++){
							errMsgHTML += "<p  ><span class = 'smart-importerrlist'>" + errList [i] + "</span></p>";
						}
						$('#errList').html(errMsgHTML);
						errMsgHTML = "";
					}else{

						if (layer) {
							layer.alert(response.msg);
						} else {
							alert(response.msg);
						}
						return;
					}

				}

				//Boolean转换，由于使用json进行传输，如果前端需要使用Boolean类型的值，则需要将字符串转换成Boolean
				if(opt.booleanTrans){
					$.each(response.data, function(index, item){
						$.each(opt.booleanTrans, function(index1, item1){
							item[item1] = item[item1] === "true";
						});
					});
				}

				//如果contentType不是application/json，则需要将参数里带[]的key-value删除，否则springmvc默认获取参数的方式会报错
				if(opt.contentType && opt.contentType.indexOf("application/json") < 0){
					$.each(opt.data, function(key){
						if(key.indexOf("[") >= 0 && key.indexOf("]") >= 0){
							delete opt.data[key];
						}
					});
				}

				fn.success(response, textStatus);
			}
		});
		return _ajax(_opt);
	};
})(jQuery);


function showErrList(res){
	if(res.code == "1"){
		var errList =  res.data;
		var errMsgHTML = "";
		var maxRow = 0;
		//循环拼接错误信息

		if(errList.length >=99){
			maxRow = 100;
			//errMsgHTML += "<p  ><span class = 'smart-importerrlist'>错误信息只展示前1000条</span></p>";
			errMsgHTML += "<p  ><span class = 'smart-importerrlist'>前100条错误信息如下，超过部分不展示！</span></p>";
		}else{
			maxRow = errList.length;
		}
		for(var i=0;i<maxRow;i++){
			errMsgHTML += "<p  ><span class = 'smart-importerrlist'>  " + errList [i] + "</span></p>";
		}
		$('#errList').html(errMsgHTML);

	}
	else if(res.code == "0"){
		layer.alert(res.msg);
	}
	else{
		if (layer) {
			layer.msg("出错了,请联系管理员!错误信息:"+res.msg, 2, 3, null, true);
		} else {
			alert("出错了,请联系管理员!");
		}
		var errMsgHTML = "<p  ><span class = 'smart-importerrlist'>  " + "出错了,请联系管理员!错误信息:"+res.msg + "</span></p>";
		$('#errList').html(errMsgHTML);
	}
}
