<%--
  Created by IntelliJ IDEA.
  Date: 2016/11/23
  Time: 13:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css"
          type="text/css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
</head>
<body>
<ul id="ztree1" class="ztree"></ul>
<script type="text/javascript">
    $(function () {
        //当页面加载完成后，动态创建ztree菜单
        var setting2 = {
            data: {
                simpleData: {
                    enable: true//启用简单json数据描述节点数据
                }
            }
        };//设置ztree相关的属性
        //构造json数据
        var zNodes2 = [
            {id: '1', pId: '0', name: '系统管理'},//每个json对象对应一个节点数据
            {id: '2', pId: '0', name: '用户管理'},//每个json对象对应一个节点数据
            {id: '21', pId: '2', name: '用户添加'},//每个json对象对应一个节点数据
            {id: '22', pId: '2', name: '用户修改'},//每个json对象对应一个节点数据
            {id: '3', pId: '0', name: '权限管理'}//每个json对象对应一个节点数据
        ];
        //创建ztree
        $.fn.zTree.init($("#ztree1"), setting2, zNodes2);
    });

</script>

</body>
</html>
