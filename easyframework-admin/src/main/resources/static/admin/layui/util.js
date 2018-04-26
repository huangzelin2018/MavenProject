var util = {};

layui.use('layer', function() {
	
	 var layer = layui.layer;
	 var $ = layui.$;
	 
	 
	/**
	 * 工具类
	 * 
	 * @type type
	 */
	util.delete = function (obj) {
	    var options = {
	        url: $(obj).attr('_href')
	    };
	    var title = $(obj).attr('_title') || '你确定删除？';
	    layer.confirm(title, {
	        btn: ['确定', '取消']
	    }, function (index, layero) {
	        layer.close(index);
	        util.ajax(options, function (data) {
	            $(obj).parents("tr").fadeOut();
	            layer.msg('删除成功!');
	            // window.location.reload();
	        });
	    });
	};

	/**
	 * 确认
	 * 
	 * @param {Object}
	 *            obj
	 */
	util.confirm = function (obj) {
	    var options = {
	        url: $(obj).attr('_href')
	    };
	    var title = $(obj).attr('_title') || '你确定？';
	    layer.confirm(title, {
	        btn: ['确定', '取消']
	    }, function (index, layero) {
	        layer.close(index);
	        util.ajax(options, function (data) {
	            layer.msg('操作成功!');
	            window.location.reload();
	        });
	    });
	};

	util.checkForm = function () {
	    var index;
	    // 表单验证
	    $("#validForm").Validform({
	        ajaxPost: true,
	        callback: function (data) {
	            layer.close(index);
	            layer.alert(data.msg, function (alertIndex) {
	                layer.close(alertIndex);
	                if (data.data.opflag == null || data.data.opflag == 0) {
	                    layer.closeAll();
	                    if (data.code == 1) {
	                        // 返回当前控制器index页面
	                        // window.location.href = think.indexUrl;
	                        window.history.back(-2);
	                    }
	                } else {
	                    window.history.back();
	                }
	            });
	        },
	        beforeSubmit: function (curform) {
	            // 在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
	            // 这里明确return false的话表单将不会提交;
	            index = layer.load();
	        },
	        tiptype: function (msg, o, cssctl) {
	            // msg：提示信息;
	            // o:{obj:*,type:*,curform:*},
				// obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4，
				// 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
	            // cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和
				// 当前提示的状态（既形参o中的type）;
	            if (!o.obj.is("form")) { // 验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
	                var objtip = o.obj.parents('.formControls').next('div');
	                var objspan = objtip.find('span');
	                if (objspan.length == 0) {
	                    objtip.append('<span></span>');
	                }
	                objtip = objtip.find('span');
	                cssctl(objtip, o.type);
	                objtip.text(msg);
	            }
	        },
	        datatype: {
	            isExist: function (gets, obj, curform, regxp) {
	                if (gets == '') {
	                    return false;
	                }
	                var result = '数据已经存在!';
	                $.ajax({
	                    url: $(obj).attr('valid_url'),
	                    dataType: 'json',
	                    type: "post",
	                    async: false,
	                    data: {
	                        account: gets
	                    },
	                    success: function (r) {
	                        result = r.code == 1 ? true : r.msg;
	                    }
	                });
	                return result;
	            }
	        }
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
	        layer.msg('是否忘记配置url数据源?', {
	            icon: 2,
	            time: 1500 // 2秒关闭（如果不配置，默认是3秒）
	        });
	        return false;
	    }
	    var index = layer.load();
	    var _options = {
	        dataType: 'json',
	        type: "post",
	        url: '',
	        cache: false,
	        data: {},
	        success: function (data) {
	            if (data.code == 0) {
	                layer.alert(data.msg);
	                return false;
	            }
	            _callback(data);
	        },
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	            layer.msg('可能网络异常,操作失败!', {
	                icon: 2,
	                time: 1500 // 2秒关闭（如果不配置，默认是3秒）
	            });
	        },
	        complete: function () {
	            layer.close(index);
	        }
	    };
	    $.ajax($.extend(_options, options));
	};
	/**
	 * 获取路径 getUrl(['controller','action'])
	 * getUrl(['controller','action'])+'?'+$.param($.extend(op1,op2));
	 * 
	 * @param {[type]}
	 *            arr [description] *
	 * @return {[type]} [description]
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
	        		layui.data('layui_add_form', null); //删除layui_add_form表
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

	/**
	 * 插入模板
	 * 
	 * @param {Object}
	 *            ueditorId
	 */
	util.addTemplate = function (ueditorId) {
	    var tmpl_id = $("#tmpl_id_" + ueditorId).val();
	    if (tmpl_id == '') {
	        layer.msg('请选择模板', {
	            icon: 2,
	            time: 1500
	        });
	        return false;
	    }
	    var options = {
	        url: think.addTemplate + "?id=" + tmpl_id
	    };
	    util.ajax(options, function (data) {
	        UE.getEditor(ueditorId).setContent(data.html, true);
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
	util.ajaxApp = function (options, _callback) {
	    if (options.url == null || options.url == '') {
	        $.notifyBar({cssClass: "warning", html: "是否忘记配置url数据源?"});
	        return false;
	    }
	    var index = layer.load();
	    var _options = {
	        dataType: 'json',
	        type: "post",
	        timeout: 10000, // 超时时间：10秒
	        url: '',
	        cache: false,
	        data: {},
	        success: function (data) {
	            if (data.code == 0) {
	                $.notifyBar({cssClass: "error", html: data.msg});
	                return false;
	            }
	            _callback(data);
	        },
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	            $.notifyBar({cssClass: "error", html: '可能网络异常,操作失败!'});
	        },
	        complete: function () {
	            layer.close(index);
	        }
	    };
	    $.ajax($.extend(_options, options));
	};

	/**
	 * 填充表单
	 * 
	 * @param {type}
	 *            formId
	 * @param {type}
	 *            options
	 * @returns {undefined}
	 */
	util.setForm = function (formId, options) {
	    util.ajaxApp(options, function (data) {
	        $("#" + formId).setForm(data);// 返回json数据
	    });
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
