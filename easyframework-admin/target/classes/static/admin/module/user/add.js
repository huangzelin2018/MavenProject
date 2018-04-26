layui.use([ 'form' ], function() {
	var form = layui.form, layer = layui.layer;

	//监听提交
	form.on('submit(submitForm)', function(data) {
		top.layer.alert(JSON.stringify(data.field), {
			title : '最终的提交信息'
		}, function(index) {
			top.layer.close(index);
			var frameIndex = top.layer.getFrameIndex(window.name);
			top.layer.close(frameIndex);
			//回调给父页面，是否刷新
			layui.data('layui_add_form', {
				key : 'isRefresh',
				value : '1'
			});
		});
		return false;
	});

});
