/**
 * ��layui������table�ı�ͷ������껬���¼�
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
 * ��layui������table���ֶ�������껬���¼�
 * @returns
 */
function load_layui_table_td() {
	$(".layui-table td").hover(function() {
		$(this).attr("title", $(this).text());
	}, function() {
		$(this).removeAttr("title");
	});
}