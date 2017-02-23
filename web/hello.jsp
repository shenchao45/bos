<%--
  Created by IntelliJ IDEA.
  User: shenchao
  Date: 2016/11/25
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
    <script>
        var Rectangle=function (w,h) {
            var rectangle=Object.create(Rectangle.prototype);
            rectangle.width=w;
            rectangle.height=h;
            return rectangle;
        };
        Rectangle.prototype={
            area:function () {
                return this.width*this.height;
            },
            perimeter:function () {
                return 2*(this.width * this.height);
            }
        };
        alert(Rectangle(2,3).area());
    </script>
</head>
<body>
<s:debug/>
</body>
</html>
