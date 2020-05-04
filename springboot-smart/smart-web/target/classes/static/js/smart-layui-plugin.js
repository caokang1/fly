/*
 * 集成layui一些通用方法
 */

/*
 * 公共的初始化数据，通常用于弹出窗口的传值
 * */

/*
 * 从浏览器session中获取初始化数据
 * */
function getInitJson(key){
	
	var href = window.location.href;
	var _key = key || href.substring(href.lastIndexOf('/') + 1, href.length);
	
	var val = sessionStorage.getItem(_key);
	/*var initJson = JSON.parse(sessionStorage.getItem("NHBchargerateupdate.html"));*/
	
	return val ? JSON.parse(val) : {};
}

function initForm(id, obj){
	if(!layui){
		return;
	}
	
	var _obj = JSON.parse(JSON.stringify(obj));
	
	var form = layui.form;
	
	form.val(id, _obj);
	
	if(layui.xmSelect){
		xmSelect = layui.xmSelect;
		
		var xmList = xmSelect.get();
		
		$.each(xmList, function(index, item){
			var name = item.options.name;
			var radio = item.options.radio;
			
			if(radio){
				item.setValue([_obj[name]]);
			}
			else{
				item.setValue(_obj[name].split(","));
			}
		});
	}
}

/*
 * 简单下拉框的渲染，需要直接定义数据
 * */
function layuiXmSelect_Simple(id, option){
	if(!layui){
		return null;
	}
	
	if(!layui.xmSelect){
		return null;
	}
	
	var xmSelect = layui.xmSelect;
	
	var _div = $("div[id*='" + id + "']");
	var _id = "#" + _div.attr("id");
	
	var _initJson = !option ? getInitJson() : (option.initJson ? option.initJson : (option.key ? getInitJson(option.key) : getInitJson()));
	
	var _option = {
		el: _id, 
		size: 'mini',
		name: id,
		clickClose: true,
		filterable: true,
		radio: true,
	    template: function(data/*{item, sels, name, value}*/){
	    	//{item, sels, name, value}这种语法是ES6语法，为了兼容ie10以上的浏览器，要使用ES5语法
	    	return data.value + "--" + data.name;
	    }
	}
	
	$.extend(_option, option);
	
	var simple = xmSelect.render(_option);
	
	if(_initJson && _initJson[id]){
		simple.setValue([_initJson[id]]);
	}
	
	return simple;
	
}

/*
 * 标准ldcode下拉框，需要传入codeType
 * */
function layuiXmSelect_Mullist(id, option){
	if(!layui){
		return null;
	}
	
	if(!layui.xmSelect){
		return null;
	}
	
	var xmSelect = layui.xmSelect;
	
	var _initJson = !option ? getInitJson() : (option.initJson ? option.initJson : (option.key ? getInitJson(option.key) : getInitJson()));
	var _codetype = option.codeType;
	var _div = $("div[id*='" + id + "']");
	var _id = "#" + _div.attr("id");
	
	var mullist = xmSelect.render({
	    el: _id, 
	    size: 'mini',
	    name: id,
	    clickClose: true,
	    radio: true,
	    autoRow: true,
	    filterable: true,
	    prop: {
	    	name: "codeName",
	    	value: "code"
	    },
	    filterMethod: function(val, item, index, prop){
	    	if(!val){
	    		return true;
	    	}
	    	
	    	if(item.codeName.indexOf(val) != -1 || item.code.toLowerCase().indexOf(val.toLowerCase()) != -1){
	    		return true;
	    	}
	    	
	    	return false;
	    },
	    model: {
	        label: {
	            type: 'block',
	            block: {
	                //最大显示数量, 0:不限制
	                showCount: 3,
	                //是否显示删除图标
	                showIcon: true,
	            }
	        }
	    },
	    template: function(data/*{item, sels, name, value}*/){
	    	//{item, sels, name, value}这种语法是ES6语法，为了兼容ie10以上的浏览器，要使用ES5语法
	    	return data.value + "--" + data.name;
	    }
	});
	
	$.ajax({
		type:"POST",
		url:basePath() + "ldcode/Mullist",
		data:{codetype : _codetype},
		dataType:"json",
		success : function(obj){
			if(obj.code=="0"){
				mullist.update({
					data: obj.data
				});
				
				if(_initJson && _initJson[id]){
					mullist.setValue([_initJson[id]]);
				}
			}else{
				layer.alert(obj.msg)
			}
		}
	});
	
	return mullist;
}

/*
 * 健康险——经代机构下拉框，默认单选
 * */
function layuiXmSelect_HBAgentcom(id, option){
	if(!layui){
		return null;
	}
	
	if(!layui.xmSelect){
		return null;
	}
	
	var xmSelect = layui.xmSelect;
	
	var _initJson = !option ? getInitJson() : (option.initJson ? option.initJson : (option.key ? getInitJson(option.key) : getInitJson()));
	
	var _div = $("div[id*='" + id + "']");
	var _id = "#" + _div.attr("id");
	var _radio = (!option || option.radio == undefined || typeof(option.radio) == "undefined" || option.radio == null) ? true : option.radio;
	
	var agentcom = xmSelect.render({
	    el: _id, 
	    size: 'mini',
	    name: id,
	    //如果单选的话就点击关闭
	    clickClose: _radio,
	    radio: _radio,
	    autoRow: true,
	    filterable: true,
	    toolbar: {
	    	show: !_radio,
	    	list: ['ALL','CLEAR','REVERSE']
	    },
	    prop: {
	    	name: "bragentcomname",
	    	value: "agentcom"
	    },
	    filterMethod: function(val, item, index, prop){
	    	if(!val){
	    		return true;
	    	}
	    	
	    	if(item.bragentcomname.indexOf(val) != -1 || item.agentcom.toLowerCase().indexOf(val.toLowerCase()) != -1){
	    		return true;
	    	}
	    	
	    	return false;
	    },
	    model: {
	        label: {
	            type: 'block',
	            block: {
	                //最大显示数量, 0:不限制
	                showCount: 3,
	                //是否显示删除图标
	                showIcon: true,
	            }
	        }
	    },
	    template: function(data/*{item, sels, name, value}*/){
	    	//{item, sels, name, value}这种语法是ES6语法，为了兼容ie10以上的浏览器，要使用ES5语法
	    	return data.value + "--" + data.name;
	    }
	});
	//经代机构下拉框
	$.ajax({
		type:"POST",
		url:basePath() + "hbbrmanagecominfo/select",
		dataType:"json",
		success : function(obj){
			if(obj.code=="0"){
				/*var htmlagentcom ="<option value=''>选择或搜索选择</option>";
				$.each(obj.data,function(index,value){
					htmlagentcom += "<option value='"+value.agentcom+"'>"+value.agentcom+"-"+value.bragentcomname+"</option>";
				});
				$("#agentcom").html(htmlagentcom);
				form.render("select");*/
				agentcom.update({
					data: obj.data
				});
				
				if(_initJson && _initJson[id]){
					var _initVal = _initJson[id];
					if(_radio){
						agentcom.setValue([_initVal]);
					}
					else{
						agentcom.setValue(_initVal.split(","));
					}
				}
			}else{
				layer.alert(obj.msg);
			}
		}
	});
	
	return agentcom;
}

/*
 * 健康险——产品下拉框
 * 默认选中时显示riskcode，默认单选
 * */
function layuiXmSelect_HBRiskcode(id, option){
	if(!layui){
		return null;
	}
	
	if(!layui.xmSelect){
		return null;
	}
	
	var xmSelect = layui.xmSelect;
	
	var _initJson = !option ? getInitJson() : (option.initJson ? option.initJson : (option.key ? getInitJson(option.key) : getInitJson()));
	
	var _div = $("div[id*='" + id + "']");
	var _id = "#" + _div.attr("id");
	var _radio = (!option || typeof(option.radio) == "undefined" || option.radio == undefined || option.radio == null) ? true : option.radio;
	
	var riskcode = xmSelect.render({
	    el: _id, 
	    size: 'mini',
	    name: id,
	    //如果单选的话就点击关闭
	    clickClose: _radio,
	    radio: _radio,
	    toolbar: {
	    	show: !_radio,
	    	list: ['ALL','CLEAR','REVERSE']
	    },
	    autoRow: true,
	    filterable: true,
	    prop: {
	    	name: "riskcode",
	    	value: "riskcode"
	    },
	    /*show(){
	    	$("input[name=" + id + "]").focus();
	    },*/
	    filterMethod: function(val, item, index, prop){
	    	if(!val){
	    		return true;
	    	}
	    	
	    	if(item.riskname.indexOf(val) != -1 || item.riskcode.toLowerCase().indexOf(val.toLowerCase()) != -1){
	    		return true;
	    	}
	    	
	    	return false;
	    },
	    model: {
	        label: {
	            type: 'block',
	            block: {
	                //最大显示数量, 0:不限制
	                showCount: 3,
	                //是否显示删除图标
	                showIcon: true,
	            }
	        }
	    },
	    template: function(data/*{item, sels, name, value}*/){
	    	//{item, sels, name, value}这种语法是ES6语法，为了兼容ie10以上的浏览器，要使用ES5语法
	    	return data.value + "--" + data.item.riskname;
	    }
	});
	//产品代码下拉框
	$.ajax({
		type:"POST",
		url:basePath() + "ccmslmriskapp/select",
		dataType:"json",
		success : function(obj){
			if(obj.code=="0"){
				/*var htmlriskcode ="<option value=''>选择或搜索选择</option>";
				$.each(obj.data,function(index,value){
					htmlriskcode += "<option value='"+value.riskcode+"'>"+value.riskcode+"-"+value.riskname+"</option>";
				});
				$("#riskcode").html(htmlriskcode);
				form.render("select");*/
				riskcode.update({
					data: obj.data
				});
				
				if(_initJson && _initJson[id]){
					var _initVal = _initJson[id];
					if(_radio){
						riskcode.setValue([_initVal]);
					}
					else{
						riskcode.setValue(_initVal.split(","));
					}
				}
				
			}else{
				layer.alert(obj.msg);
			}
		}
	});
	
	return riskcode;
}

/*
 * 默认创建type=2的弹出窗口，并在浏览器session中存放初始化值
 * */
function layui_openWin(option){
	if(!layui){
		return null;
	}
	
	var layer = layui.layer;
	
	var i1 = document.body.clientWidth - 250;
    var i2 = document.body.clientHeight - 100;
    var key = option.key || option.content;
    
    if(option.data){
    	sessionStorage.setItem(key, JSON.stringify(option.data));
    }
    
    var _option = {
    	type: 2,
    	area: [i1+'px', i2+'px'],
    	fixed: false, //不固定
    	cancel: function (layero, index) { 
			 /*var childw = layero.find("iframe")[0].contentWindow;
			 childw.transmit(data);*/
    		sessionStorage.removeItem(key);
		},
		maxmin: true
    }
    
    $.extend(_option, option);
    
    var index = layer.open(_option);
    layer.full(index);
    
    return index;
	
}