var util = {};

layui.use([ 'form','layer'], function() {
	
	 var layer = layui.layer;
	 var form = layui.form;
	 var $ = layui.$;
	 
	 // 监听提交
	 form.on('submit(submitForm)', function(data) {
		var options = {
			url : $(data.form).attr("action"),
			data : data.field
		};
		util.addModel(options);
		return false;
	 });
	

	/**
	 * 确认
	 * 
	 * @param {Object}
	 *            obj
	 */
	util.confirm = function (obj,callback) {
	    var options = {
	        url: $(obj).attr('_href')
	    };
	    var title = $(obj).attr('_title') || '你确定？';
	    top.layer.confirm(title, {
	    	title:'温馨提示',
	        btn: ['确定', '取消']
	    }, function (index, layero) {
	        top.layer.close(index);
	        util.ajax(options, function (data) {
	            top.layer.msg('操作成功!');
	            if(callback){
	            	callback(data);
	            }else{
	            	window.location.reload();
	            }
	        });
	    });
	};

	/**
	 * ajax操作
	 * 
	 * @param options
	 * @param layui
	 * @param _callback
	 * @returns {boolean}
	 */
	util.ajax = function (options, _callback) {
	    if (options.url == null || options.url == '') {
	        top.layer.msg('是否忘记配置url数据源?', {
	            icon: 2,
	            time: 1500 // 2秒关闭（如果不配置，默认是3秒）
	        });
	        return false;
	    }
	    var index = top.layer.load();
	    var _options = {
	        dataType: 'json',
	        type: "post",
	        url: '',
	        cache: false,
	        data: {},
	        success: function (data) {
	            if (data.code == 0) {
	                top.layer.alert(data.msg);
	                return false;
	            }
	            _callback(data);
	        },
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	            top.layer.msg('可能网络异常,操作失败!', {
	                icon: 2,
	                time: 1500 // 2秒关闭（如果不配置，默认是3秒）
	            });
	        },
	        complete: function () {
	            top.layer.close(index);
	        }
	    };
	    $.ajax($.extend(_options, options));
	};
	
	util.addModel = function(options){
		util.ajax(options, function(response) {
			top.layer.alert(response.msg, {
				title : '温馨提示'
			}, function(index) {
				top.layer.close(index);
				var frameIndex = top.layer.getFrameIndex(window.name);
				top.layer.close(frameIndex);
				// 回调给父页面，是否刷新
				layui.data('layui_add_form', {
					key : 'isRefresh',
					value : '1'
				});
			});
		});
	};
	
	/**
	 * 获取路径 getUrl(['controller','action'])
	 * getUrl(['controller','action'])+'?'+$.param($.extend(op1,op2));
	 */
	util.getUrl = function (arr, start, end) { // js
	    var ar = location.pathname.split('/');
	    start = start || 1;
	    end = end || 2;
	    ar = ar.slice(start, end);
	    Array.prototype.push.apply(ar, arr);
	    return 'http://' + location.host + '/' + ar.join("/");
	};
	util.boxshow = function (obj) {
		
	    var title = $(obj).attr('_title') || '系统提示';
	    var url = $(obj).attr('_href');
	    var _width = $(top.window).width() - 200;
	    var width = $(obj).attr('_width')||_width;
	    var _height = $(top.window).height() - 200;
	    var height = $(obj).attr('_height')||_height;
	    var is_show_btn = $(obj).attr('is_show_btn') || true; // 是否显示确定取消按钮
	    if (url == null || url == '') {
	        top.layer.msg('是否忘记配置url数据源?', {
	            icon: 2,
	            time: 1500 // 2秒关闭（如果不配置，默认是3秒）
	        });
	        return false;
	    }
	    
	    var options = {
	        type: 2,
	        title: title,
	        maxmin: false,
	        area: [width + 'px', height + 'px'],
	        content: url,
	        success: function (layero, index) {
	            var body = top.layer.getChildFrame('body', index);
	            if ($(body).height() < height) {
	                layer.iframeAuto(index);
	            }
	        },
	        end: function () {
	        	var layui_add_form = layui.data('layui_add_form');
	        	if(layui_add_form.isRefresh){
	        		layui.data('layui_add_form', null); // 删除layui_add_form表
	        		location.reload();
	        	}             
            }
	    };
	    if (is_show_btn) {
	        options = $.extend(options, {
	            btn: ['确定', '关闭'],
	            yes: function (index, layero) {
	                var body = top.layer.getChildFrame('body', index);
	                $(body).find("#btnSubmit").click();
	            }
	        });
	    }
	    top.layer.open(options);
	};

	/**
	 * iframe高度自适应内容
	 * 
	 * @param {type}
	 *            iframe
	 * @returns {undefined} <iframe id="testIframe" name="testIframe"
	 *          width="100%" frameborder="0" onload="setIframeHeight(this)"
	 *          scrolling="no" src="__CONTROLLER__/add"></iframe>
	 */
	util.setIframeHeight = function (iframe) {
	    if (iframe) {
	        var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
	        if (iframeWin.document.body) {
	            iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
	        }
	    }
	};


	$.fn.setForm = function (jsonValue) {
	    var obj = this;
	    $.each(jsonValue, function (name, ival) {
	        var $oinput = obj.find("input[name=" + name + "]");
	        if ($oinput.attr("type") === "radio" || $oinput.attr("type") === "checkbox") {
	            $oinput.each(function () {
	                if (Object.prototype.toString.apply(ival) === '[object Array]') {// 是复选框，并且是数组
	                    for (var i = 0; i < ival.length; i++) {
	                        if ($(this).val() === ival[i])
	                            $(this).attr("checked", "checked");
	                    }
	                } else {
	                    if ($(this).val() === ival)
	                        $(this).attr("checked", "checked");
	                }
	            });
	        } else if ($oinput.attr("type") === "textarea") {// 多行文本框
	            obj.find("[name=" + name + "]").html(ival);
	        } else {
	            obj.find("[name=" + name + "]").val(ival);
	        }
	    });
	};
});
