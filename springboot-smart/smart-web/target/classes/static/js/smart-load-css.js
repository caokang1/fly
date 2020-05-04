/**
 * 给layui的数据table的表头增加鼠标滑动事件
 * @returns
 */
function load_layui_table_th() {
	$(".layui-table th").hover(function() {
		$(this).attr("title", $(this).text());
	}, function() {
		$(this).removeAttr("title");
	});
}

/**
 * 给layui的数据table的字段增加鼠标滑动事件
 * @returns
 */
function load_layui_table_td() {
	$(".layui-table td").hover(function() {
		$(this).attr("title", $(this).text());
	}, function() {
		$(this).removeAttr("title");
	});
}