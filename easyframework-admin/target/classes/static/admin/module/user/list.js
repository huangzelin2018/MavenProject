/**
 * 列表表格
 * 
 * @returns
 */
layui.use('table', function() {
	var table = layui.table;
	table.render({
		elem : '#listBox',
		url : '/admin/user/list',
		cols : [ [ {
			type : 'numbers',
			fixed : 'left',
		}, {
			type : 'checkbox',
			fixed : 'left',
		}, {
			field : 'id',
			width : 80,
			title : 'ID',
			sort : true
		}, {
			field : 'username',
			width : 200,
			title : '用户名'
		}, {
			field : 'sex',
			width : 120,
			title : '性别',
			templet : '#switchTpl',
			unresize : true
		}, {
			field : 'nickName',
			width : 120,
			title : '昵称'
		}, {
			field : 'registerDate',
			title : '注册日期',

			sort : true
		}, {
			fixed : 'right',
			width : 178,
			align : 'center',
			toolbar : '#toolbar'
		} ] ],
		page : true,
		limit : 10, // 默认十条数据一页
		limits : [ 10, 20, 30, 50 ]
	// 数据分页条
	});

	// 监听工具条
	table.on('tool(listBox)', function(obj) {
		var data = obj.data;
		if (obj.event === 'detail') {
			top.layer.msg('ID：' + data.id + ' 的查看操作');
		} else if (obj.event === 'del') {
			top.layer.confirm('真的删除行么', function(index) {
				obj.del();
				layer.close(index);
			});
		} else if (obj.event === 'edit') {
			top.layer.alert('编辑行：<br>' + JSON.stringify(data))
		}
	});

	// 监听搜索
	var $ = layui.$, active = {
		reload : function() {
			// 执行重载
			table.reload('listBox', {
				page : {
					curr : 1
				// 重新从第 1 页开始
				},
				where : {
					id : $("#userId").val(),
					username : $("#userName").val()
				}
			});
		}
	};

	$('#btnSearch').on('click', function() {
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	});
});