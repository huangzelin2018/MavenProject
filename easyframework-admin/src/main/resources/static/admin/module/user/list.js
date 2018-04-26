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
		limits : [ 10, 20, 30, 50 ],// 数据分页条

	});

	// 监听工具条
	table.on('tool(listBox)', function(obj) {
		var data = obj.data;
		if (obj.event === 'del') {
			top.layer.confirm('真的删除行么', function(index) {
				obj.del();
				top.layer.close(index);
			});
		} else if (obj.event === 'edit') {
			top.layer.alert('编辑行：<br>' + JSON.stringify(data))
		}
	});

	// 监听排序
	table.on('sort(listBox)', function(obj) {
		//注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		//尽管我们的 table 自带排序功能，但并没有请求服务端。
		//有些时候，你可能需要根据当前排序的字段，重新向服务端发送请求，从而实现服务端排序，如：
		table.reload('listBox', {
			initSort : obj, //记录初始排序，如果不设的话，将无法标记表头的排序状态。 layui 2.1.1 新增参数
			where : { //请求参数（注意：这里面的参数可任意定义，并非下面固定的格式）
				field : obj.field, //排序字段
				order : obj.type,//排序方式
			}
		});
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