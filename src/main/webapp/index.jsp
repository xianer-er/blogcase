<%@ page import="com.xianer.controller.PageBean" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>仙儿的博客</title>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <%--    <link rel="stylesheet" type="text/css" href="../../../target/blog/css/index.css">--%>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script>

        //单个删除
        function deleteBlog(id) {
            if (confirm("您确定要删除吗")) {
                location.href = "${pageContext.request.contextPath}/delBlogServlet?id=" + id;
            }

        }

        //    删除选中
        window.onload = function () {
            document.getElementById("delSelected").onclick = function () {
                if (confirm("你确定删除选中条目吗")) {
                    var flag = false;
                    var cbs = document.getElementsByName("bid");
                    for (var i = 0; i < cbs.length; i++) {
                        if (cbs[i].checked) {
                            flag = true;
                            break;
                        }
                    }
                }
                if (flag) {
                    document.getElementById("form").submit();
                } else (confirm("请选择至少一个条目"))
            }
            //控制全选按钮
            document.getElementById("firstcb").onclick = function () {
                var cbs = document.getElementsByName("bid");
                for (var i = 0; i < cbs.length; i++) {
                    cbs[i].checked = this.checked;
                }
            }

        }

    </script>
</head>
<body>
<%--个人信息--%>
<div class="container" style="border-bottom: 10px; border-bottom-color: cadetblue;">
    <div class="row">
        <div class="col-lg-2">
            仙儿
        </div>
        <div class="col-lg-2">
            粉丝数：10
        </div>
        <div class="col-lg-1">
            点赞数：
        </div>
        <div class="col-lg-1">
            评论数：
        </div>
    </div>
    <%--增删的按钮--%>
    <%--博客的查询表单--%>
    <div class="blogs_search">
        <form class="form-inline" method="post" action="${pageContext.request.contextPath}/findBlogByPageServlet">
            <div class="alter_buttons" style="float: right;">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/categoryServlet">写博客</a>

                <a class="btn btn-primary" href="javascript:void(0)" id="delSelected">删除选中</a>
            </div>
            <div class="form-group">
                <label for="exampleInputName2">博客标题</label>
                <input type="text" name="title" value="${condition.title[0]}" class="form-control" id="exampleInputName2">

            </div>
                <div class="form-group">
                    <div class="dropdown">
                        <select  name="category" onchange="${condition.category[0]}">
                            <option value="0">全部类型</option>
                            <c:forEach items="${cg}" var="cg">
                                <option value="${cg.idcategory}" >${cg.categorycol}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            <button type="submit" class="btn btn-primary">查询</button>
        </form>
    </div>
   <p><%= request.getAttribute("s_category")%></p>
    <%--博客展示--%>
    <div class="blogs">
<%--        <%PageBean pb = new PageBean();--%>
<%--              String cg= (String) pb.getCategorycol().get(1);--%>
<%--        %>--%>
<%--        <%=cg--%>
<%--        %>--%>
        <form id="form" action="${pageContext.request.contextPath}/delSelectedServlet" method="post">
            <table border="1" class="table ">
                <tr class="titles">
                    <td><input type="checkbox" id="firstcb"></td>
                    <th>编号</th>
                    <th>标题</th>
                    <th>类型</th>
                    <th>更新时间</th>
                    <th>操作</th>
                </tr>
                    <c:forEach begin="0" end="${pb.rows-1}" var="i" varStatus="ss">
                    <tr>
                        <td><input type="checkbox" name="bid" value="${pb.list[i].id}"></td>
                        <td>${ss.count}</td>
                        <td>${pb.list[i].title}</td>
                        <td>${pb.categorycol[i]}</td>
                        <td>${pb.list[i].updatetime}</td>
                        <td><a class="btn btn-default btn-sm"
                               href="${pageContext.request.contextPath}/findBlogServlet?id=${pb.list[i].id}">编辑</a>&nbsp;
                            &nbsp;
                            <a class="btn btn-default btn-sm" href="javascript:deleteBlog(${pb.list[i].id});">删除</a></td>
                    </tr>
                    </c:forEach>
            </table>
        </form>

    </div>
    <%--分页--%>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${pb.currentPage==1}">
                <li class="disabled">
                    </c:if>
                    <c:if test="${pb.currentPage!=1}">
                <li>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/findBlogByPageServlet?currentPage=${pb.currentPage-1}&rows=5&title=${condition.title[0]}&category=${condition.category[0]}"
                       aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach begin="1" end="${pb.totalPage}" var="i">
                    <c:if test="${pb.currentPage==i}">
                        <li class="active"><a
                                href="${pageContext.request.contextPath}/findBlogByPageServlet?currentPage=${i}&rows=5&title=${condition.title[0]}&category=${condition.category[0]}">${i}</a>
                        </li>
                    </c:if>
                    <c:if test="${pb.currentPage!=i}">
                        <li>
                            <a href="${pageContext.request.contextPath}/findBlogByPageServlet?currentPage=${i}&rows=5&title=${condition.title[0]}&category=${condition.category[0]}">${i}</a>
                        </li>
                    </c:if>
                </c:forEach>
                <c:if test="${pb.currentPage>=pb.totalPage}">
                <li class="disabled">
                    <a href="${pageContext.request.contextPath}/findBlogByPageServlet?currentPage=${pb.currentPage}&rows=5&title=${condition.title[0]}&category=${condition.category[0]}"
                       aria-label="Next">                </c:if>
                        <c:if test="${pb.currentPage<pb.totalPage}">
                <li>
                    <a href="${pageContext.request.contextPath}/findBlogByPageServlet?currentPage=${pb.currentPage+1}&rows=5&title=${condition.title[0]}&category=${condition.category[0]}"
                       aria-label="Next">
                        </c:if>

                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span class="blog_data" style="font-size: 24px; margin-left: 10px;">
                    共${pb.totalCount}条记录，共${pb.totalPage}页
                </span>

            </ul>
        </nav>

    </div>

</div>
</body>
</html>
