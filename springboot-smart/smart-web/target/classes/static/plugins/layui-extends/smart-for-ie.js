/*
 * 集成layui一些通用方法
 */
layui.define(["form","jquery","layer","laydate","transfer"],function(exports){
	"use strict";
	
	var form = layui.form
		,$ = layui.jquery
		,laydate = layui.laydate
		,transfer = layui.transfer
		,layer = layui.layer;
	
	var inner = {
		
		getInitJson: function(key){
			var href = window.location.href;
			var _key = key || href.substring(href.lastIndexOf('/') + 1, href.length);
			
			var val = sessionStorage.getItem(_key);
			/*var initJson = JSON.parse(sessionStorage.getItem("NHBchargerateupdate.html"));*/
			
			return val ? JSON.parse(val) : {};
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
		 * 初始化form表单的数据
		 * */
		initForm: function(id, obj){
			if(!obj){
				return;
			}
			
			var _obj = JSON.parse(JSON.stringify(obj));
			
			form.val(id, _obj);
			form.render(null, id);
			
			/*if(xmSelect){
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
			}*/
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