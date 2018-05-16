<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title></title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h3>商品列表</h3>
        <div>
            <form class="form-inline">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="商品名称" name="productName" value="${param.productName}">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="产地" name="place" value="${param.place}">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="最低价格" name="minPrice" value="${param.minPrice}">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="最高价格" name="maxPrice" value="${param.maxPrice}">
                </div>
                <div class="form-group">
                    <select name="typeId" class="form-control">
                        <option value=""></option>
                        <c:forEach items="${typeList}" var="type">
                            <option ${param.typeId == type.id ? "selected" : ""} value="${type.id}">${type.typeName}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-default">搜索</button>
            </form>
        </div>
        <c:if test="${not empty message}">
            <div class="alert alert-success">${message}</div>
        </c:if>
        <a href="/kaola/new" class="btn btn-info pull-right">新增商品</a>
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>商品名称</th>
                    <th>价格</th>
                    <th>市场价</th>
                    <th>产地</th>
                    <th>所属分类</th>
                    <th>#</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${kaolaList.list}" var="kaola">
                <tr>
                    <td><a href="/kaola/${kaola.id}">${kaola.productName}</a></td>
                    <td>${kaola.price}</td>
                    <td>${kaola.marketPrice}</td>
                    <td>${kaola.place}</td>
                    <td>${kaola.kaolaType.typeName}</td>
                    <td>
                        <a href="/kaola/${kaola.id}/edit">编辑</a>
                        <a href="javascript:;" class="delLink" rel="${kaola.id}">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div><h4>共有${kaolaList.total}条数据</h4></div>
        <ul id="pagination-demo" class="pagination pull-right"></ul>
    </div>
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>
    <script src="/static/js/jquery.twbsPagination.js"></script>
    <script>
        $(function(){
            //分页
            $('#pagination-demo').twbsPagination({
                totalPages: ${kaolaList.pages},
                visiblePages: 10,
                first:'首页',
                last:'末页',
                prev:'上一页',
                next:'下一页',
                href:"?productName=" + encodeURIComponent('${param.productName}') +
                        "&place=" + encodeURIComponent('${param.place}') +
                        "&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}&typeId=${param.typeId}&p={{number}}"
            });
            //删除
            $(".delLink").click(function () {
                var id = $(this).attr("rel");
                if(confirm("确定要删除吗")) {
                    window.location.href = "/kaola/"+id+"/del";
                }
            });
        });
    </script>
</body>
</html>