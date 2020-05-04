/*
 * 集成layui一些通用方法
 */
layui.define(["form","xmSelect","jquery","layer","laydate","transfer"],function(exports){
	"use strict";
	
	var form = layui.form
		,xmSelect = layui.xmSelect
		,$ = layui.jquery
		,laydate = layui.laydate
		,transfer = layui.transfer
		,layer = layui.layer;
	
	var link = "--";
	
	var inner = {
		
		getInitJson: function(key){
			var href = window.location.href;
			var _key = key || href.substring(href.lastIndexOf('/') + 1, href.length);
			
			var val = sessionStorage.getItem(_key);
			/*var initJson = JSON.parse(sessionStorage.getItem("NHBchargerateupdate.html"));*/
			
			return val ? JSON.parse(val) : {};
		},
		
		setValueForXmSelect: function(item, _initJson){
			var name = item.options.name;
			var radio = item.options.radio;
			var other = item.options.other;
			
			if(radio){
				if(_initJson && _initJson[name]){
					if(other && other.next){
						//如果有联动，则触发on监听
						item.setValue([_initJson[name]], null, true);
					}
					else{
						item.setValue([_initJson[name]]);
					}
				}
			}
			else{
				if(_initJson && _initJson[name]){
					if(other && other.next){
						//如果有联动，则触发on监听
						item.setValue(_initJson[name].split(","), null, true);
					}
					else{
						item.setValue(_initJson[name].split(","));
					}
					
				}
			}
			
			/*if(other && other.next){
				var next = other.next;
				
				return (function innerFn(){
					//说明该下拉框影响联动下拉框的值，因此在这里处理被联动下拉框的值
					var _div = $("div[id*='" + next + "']");
					var _id = "#" + _div.attr("id");
					
					var nextSelect = xmSelect.get(_id, true);
					
					if(nextSelect){
						if(nextSelect.options.radio){
							nextSelect.setValue([_obj[other.next]], null, true);
						}
						else{
							nextSelect.setValue(_obj[other.next].split(","), null, true);
						}
						
						var childOther = nextSelect.options.other;
						if(childOther && childOther.next){
							next = childOther.next;
							innerFn();
						}
					}
				})();
			}*/
			
		}
		
	}
	
	var obj = {
		
		/*
		* 从浏览器session中获取初始化数据
		* */
		getInitJson: function(key){
			return inner.getInitJson(key);
		},
		
		/*
		 * 给xmselect的下拉框赋值
		 * */
		setXmSelectValue: function(select, initJson){
			inner.setValueForXmSelect(select, initJson);
		},
		
		/*
		 * 初始化form表单的数据
		 * */
		initForm: function(id, obj){
			if(!obj){
				return;
			}
			
			var _obj = JSON.parse(JSON.stringify(obj));
			
			form.val(id, _obj);
			form.render(null, id);
			
			if(xmSelect){
				var xmList = xmSelect.get();
				var nexts = [];
				
				//首先遍历下拉框中有没有联动的，如果有的话则记录被联动的下拉框
				$.each(xmList, function(index, item){
					var other = item.options.other;
					if(other){
						if(other.next){
							nexts.push(other.next);
						}
					}
				});
				
				$.each(xmList, function(index, item){
					var name = item.options.name;
					
					if(nexts && nexts.length > 0){
						//说明这个下拉框是联动的，受上一层下拉框影响，则不管
						if($.inArray(name, nexts) != -1){
							return true;
						}
					}
					
					inner.setValueForXmSelect(item, _obj);
					
				});
			}
		},
		
		initTransfer: function(option, callback){
			
			$.ajax({
				type : "POST",
				url : option.url,
				dataType : "json",
				data:option.data,
				success : function(res) {
					if(res.code == "0"){
						var value = [];
						
						$.each(res.data, function(index, item){
							$.extend(item, {
								selected: item.selected === "true",
								disabled: item.disabled === "true"
							});
							
							if(item.selected){
								value.push(item[option.key]);
							}
							
						});
						transfer.reload(option.id, {
							data: res.data,
							value: value
						});
						
						var data = {
							res: res,
							value: value
						};
						
						(callback && typeof(callback)==="function") && callback(data);
						
					}
					else{
						layer.alert("加载数据失败");
					}
				}
			});
		},
		
		/*
		 * 简单下拉框的渲染，需要直接定义数据
		 * */
		xmSelect_Simple: function(id, option){
			if(!xmSelect){
				return null;
			}
			
			var _div = $("div[id*='" + id + "']");
			var _id = "#" + _div.attr("id");
			
			var _initJson = !option ? inner.getInitJson() : (option.initJson ? option.initJson : (option.key ? inner.getInitJson(option.key) : inner.getInitJson()));
			
			var _option = {
				el: _id, 
				size: 'mini',
				name: id,
				clickClose: true,
				filterable: true,
				radio: true,
			    template: function(data/*{item, sels, name, value}*/){
			    	//{item, sels, name, value}这种语法是ES6语法，为了兼容ie10以上的浏览器，要使用ES5语法
			    	return data.value + link + data.name;
			    }
			}
			
			$.extend(_option, option);
			
			var simple = xmSelect.render(_option);
			
			if(_initJson && _initJson[id]){
				simple.setValue([_initJson[id]]);
			}
			
			return simple;
		},
		
		/**
		 * 普通下拉框
		 */
		xmSelect_Common: function(id, option){
			if(!xmSelect){
				return null;
			}
			
			var _div = $("div[id*='" + id + "']");
			var _id = "#" + _div.attr("id");
			var _radio = (!option || option.radio == undefined || typeof(option.radio) == "undefined" || option.radio == null) ? true : option.radio;
			
			var _initJson = !option ? inner.getInitJson() : (option.initJson ? option.initJson : (option.key ? inner.getInitJson(option.key) : inner.getInitJson()));
			
			var common = xmSelect.render({
				el: _id,
				size: 'mini',
				name: id,
				clickClose: true,
				filterable: true,
				radio: _radio,
				prop: option.prop || {},
				filterMethod: function(val, item, index, prop){
			    	if(!val){
			    		return true;
			    	}
			    	
			    	if(item[prop.name].indexOf(val) != -1 || item[prop.value].toLowerCase().indexOf(val.toLowerCase()) != -1){
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
			    	return data.value + link + data.name;
			    }
			});
			
			$.ajax({
				type: "POST",
				url: option.url,
				dataType:"json",
				success : function(obj){
					if(obj.code=="0"){
						common.update({
							data: obj.data
						});
						
						if(_initJson && _initJson[id]){
							common.setValue([_initJson[id]]);
						}
					}else{
						layer.alert(obj.msg)
					}
				}
			});
			
			return common;
			
		},
		
		/*
		 * 标准ldcode下拉框，需要传入codeType
		 * */
		xmSelect_Mullist: function(id, option){
			if(!xmSelect){
				return null;
			}
			
			var _initJson = !option ? inner.getInitJson() : (option.initJson ? option.initJson : (option.key ? inner.getInitJson(option.key) : inner.getInitJson()));
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
			    	return data.value + link + data.name;
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
		},
		
		/*
		 * 健康险——经代机构下拉框，默认单选
		 * */
		xmSelect_HBAgentcom: function(id, option){
			if(!xmSelect){
				return null;
			}
			
			var _initJson = !option ? inner.getInitJson() : (option.initJson ? option.initJson : (option.key ? inner.getInitJson(option.key) : inner.getInitJson()));
			
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
			    	return data.value + link + data.name;
			    }
			});
			//经代机构下拉框
			$.ajax({
				type:"POST",
				url:basePath() + "hbbrmanagecominfo/select",
				dataType:"json",
				success : function(obj){
					if(obj.code=="0"){
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
		},
		
		/*
		 * 健康险——产品下拉框
		 * 默认选中时显示riskcode，默认单选
		 * */
		xmSelect_HBRiskcode: function(id, option){
			if(!xmSelect){
				return null;
			}
			
			var _initJson = !option ? inner.getInitJson() : (option.initJson ? option.initJson : (option.key ? inner.getInitJson(option.key) : inner.getInitJson()));
			
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
			    	return data.value + link + data.item.riskname;
			    }
			});
			//产品代码下拉框
			$.ajax({
				type:"POST",
				url:basePath() + "ccmslmriskapp/select",
				dataType:"json",
				success : function(obj){
					if(obj.code=="0"){
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
		},
		
		/*
		 * 默认创建type=2的弹出窗口，并在浏览器session中存放初始化值
		 * */
		open: function(option){
			var i1 = document.body.clientWidth - 250;
		    var i2 = document.body.clientHeight - 100;
		    var _content = option.content;
		    var key = option.key || _content.substring(_content.lastIndexOf('/') + 1, _content.length);
		    
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
		},
		
		date_relate: function(startdateid,enddateid){
			// 开始时间
			 var startDate = laydate.render({
				    elem: startdateid
				    ,type: 'date'
				    ,max:"2099-12-31"
				    ,change: function(value, date){
				    	
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
		}
		
	};
	
	exports("smart", obj);
})