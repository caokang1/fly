layui.use(['element','jquery','layer'], function() {
    var element = layui.element;
    var $ = layui.jquery,
        layer = layui.layer;

    //页面加载就执行此ajax：渲染菜单栏
    $.ajax({
        type : "POST",
        url : basePath()+"login/selectMenuList",
        dataType : "JSON",
        success : function(obj) {
            $("#menu").find('span.layui-nav-bar').remove();
            var count = "";
            if (obj.code == "0") {
                $("#theinv").append(obj.theinv);
                //$("#userInfo").append(obj.userInfo.userCode);
                $.each(obj.data, function(index, value) {
                    //一级菜单 begin----------------------
                    if(value.PARENTNODECODE=='0'){
                        var li_Html = "<li class='layui-nav-item' id='"+ value.NODECODE +"'>" +
                            "<a href='javascript:;'>" +
                            "<strong><i class='layui-icon layui-icon-home'></i>" +
                            "<cite>&nbsp;"+ value.NODENAME +"</cite></strong>" +
                            "<span class='layui-nav-more'></span>" +
                            "</a>" +
                            "</li>";
                        $("#menu").append(li_Html);
                        //一级菜单 end-------------------------


                        $.each(obj.data, function(index1, value1) {
                            //二级菜单 begin------------------------
                            if(value1.PARENTNODECODE==value.NODECODE){
                                var count = 0;
                                //二级菜单的一个判断：有的二级菜单下面还有三级菜单，有的没有 begin------------------------
                                $.each(obj.data, function(index2, value2) {
                                    if(value2.PARENTNODECODE==value1.NODECODE){
                                        count = 1;
                                        return false;
                                        //continue;(注意：jQuery没有break和continue)
                                    }
                                });

                                if(count==0){

                                    if(value1.CONNECT_BY_ISLEAF == "1"){

                                        //如果是叶子节点，说明是真正的菜单
                                        //如果二级菜单下面没有子菜单，则不需要展示了
                                        var dl_Html = "<dl class='layui-nav-child'>" +
                                            "<dd id='"+value1.NODECODE+"'>" +
                                            "<a href='javascript:;' class='site-url' style='font-size:14px;' data-title='"+value1.NODENAME+"' data-id='"+value1.NODECODE+"' data-url='"+value1.RUNSCRIPT+"' title='"+value1.NODENAME+"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+value1.NODENAME+"</a>" +
                                            "</dd>" +
                                            "</dl>";
                                        $("#"+value.NODECODE).append(dl_Html);

                                    }

                                }else{
                                    var dl_Html = "<dl class='layui-nav-child'>" +
                                        "<dd id='"+value1.NODECODE+"'>" +
                                        "<a href='javascript:;' class='site-url1 smart-icon icon-plus-sign' style='padding-left:30px;font-size:14px;' title='"+value1.NODENAME+"'>&nbsp;"+value1.NODENAME+"</a>" +
                                        "</dd>" +
                                        "</dl>";
                                    $("#"+value.NODECODE).append(dl_Html);
                                }
                                //二级菜单的一个判断：有的二级菜单下面还有三级菜单，有的没有 end------------------------
                                //二级菜单 end------------------------

                                var dd_Html1 = "<dl class='layui-nav-child'>";
                                var dd_Html2 = '';
                                $.each(obj.data, function(index2, value2) {
                                    //三级菜单 begin------------------------
                                    if(value2.PARENTNODECODE==value1.NODECODE){
                                        dd_Html2 += "<dd>" +
                                            "<a href='javascript:;' class='site-url' style='font-size:12px;' data-title='"+value2.NODENAME+"' data-id='"+value2.NODECODE+"' data-url='"+value2.RUNSCRIPT+"' title='"+value2.NODENAME+"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+value2.NODENAME+"</a>" +
                                            "</dd>";
                                    }
                                });
                                if(!dd_Html2==""){
                                    $("#"+value1.NODECODE).append(dd_Html1+dd_Html2+"</dl>");
                                }
                                //三级菜单 end----------------------------
                            }
                        });

                    }

                });
                element.render();
                element.init();
            } else {
                layer.alert(obj.msg);
            }
        }
    });

    //二级菜单“+” “-”开关图标的点击事件
    $(document).on("click","a.site-url1",function(){
        //alert("1111");
        if($(this).attr("class")=="site-url1 smart-icon icon-plus-sign"){
            $(this).removeClass("smart-icon icon-plus-sign");
            $(this).addClass("smart-icon icon-minus-sign");
        } else{
            $(this).removeClass("smart-icon icon-minus-sign");
            $(this).addClass("smart-icon icon-plus-sign");
        }
    });




//菜单的一系列操作 begin----------------------------------------------------------------------
    var active = {
        tabAdd : function(url, id, name) {
            element.tabAdd('contentnav', {
                title : name,
                content : '<iframe data-frameid="' + id
                    + '" scrolling="auto" frameborder="0" src="' + url
                    + '" style="width:100%;"></iframe>',
                id : id
            });
            rightMenu();
            iframeWH();
        },
        tabChange : function(id) {
            element.tabChange('contentnav', id);
        },
        tabDelete : function(id) {
            element.tabDelete('contentnav', id);
        },
        tabDeleteAll : function(ids) {
            $.each(ids, function(index, item) {
                element.tabDelete('contentnav', item);
            });
        }
    };

    //手风琴菜单 begin------------------------------
    $(document).on("click","ul.layui-nav > li.layui-nav-item",function(){
        $(this).siblings().removeClass('layui-nav-itemed');
    });
    //手风琴菜单 end--------------------------------

    $(document).on("click",".site-url",function(){
        $(this).siblings().removeClass('layui-nav-itemed');
        var nav = $(this);
        var length = $("ul.layui-tab-title li").length;
        if (length <= 0) {
            active.tabAdd(nav.attr("data-url"), nav.attr("data-id"),nav.attr("data-title"));
        } else {
            var isData = false;
            $.each($("ul.layui-tab-title li"), function() {
                if ($(this).attr("lay-id") == nav.attr("data-id")) {
                    isData = true;
                }
            });
            if (isData == false) {
                active.tabAdd(nav.attr("data-url"),
                    nav.attr("data-id"), nav.attr("data-title"));
            }
            active.tabChange(nav.attr("data-id"));
        }
    });

    function rightMenu() {
        //右击弹出
        $(".layui-tab-title li").on('contextmenu', function(e) {
            var menu = $(".rightmenu");
            menu.find("li").attr('data-id', $(this).attr("lay-id"));
            l = e.clientX;
            t = e.clientY;
            menu.css({
                left : l,
                top : t
            }).show();
            return false;
        });

        //左键点击隐藏
        $("body,.layui-tab-title li").click(function() {
            $(".rightmenu").hide();
        });
    }

    $(".rightmenu li").click(function() {
        if ($(this).attr("data-type") == "closethis") {
            active.tabDelete($(this).attr("data-id"));
        } else if ($(this).attr("data-type") == "closeall") {
            var tabtitle = $(".layui-tab-title li");
            var ids = new Array();
            tabtitle.each(function(i) {
                ids.push($(this).attr("lay-id"));
            });
            //如果关闭所有 ，即将所有的lay-id放进数组，执行tabDeleteAll
            active.tabDeleteAll(ids);
        }
        $('.rightmenu').hide(); //最后再隐藏右键菜单
    });

    function iframeWH() {
        var H = $(window).height() * 0.85;
        $("iframe").css("height", H + "px");
    }

    $(window).resize(function() {
        iframeWH();
    });
//菜单的一系列操作 end----------------------------------------------------------------------
    $(".logout").on("click",function(){
        layer.confirm('确定要退出？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            $.ajax({
                type : "POST",
                url :  "./../login/logout",
                dataType : "JSON",
                success : function(obj) {
                    if(obj && obj.data){
                        window.location.href = obj.data;
                    }
                    else{
                        window.location.href = "../indexlis.jsp";
                    }
                }
            });
        });


    });
});

