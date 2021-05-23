<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Item" %>
<%@ page import="entity.Category" %>


<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/22/2021
  Time: 5:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Item> listItem = (ArrayList<Item>) request.getAttribute("ListItem");
    ArrayList<Category> listCate = (ArrayList<Category>) request.getAttribute("ListCate");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Star Admin Premium Bootstrap Admin Dashboard Template</title>
    <!-- plugins:css -->
    <link rel="stylesheet" href="/assets/vendors/iconfonts/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="/assets/vendors/iconfonts/ionicons/dist/css/ionicons.css">
    <link rel="stylesheet" href="/assets/vendors/iconfonts/flag-icon-css/css/flag-icon.min.css">
    <link rel="stylesheet" href="/assets/vendors/css/vendor.bundle.base.css">
    <link rel="stylesheet" href="/assets/vendors/css/vendor.bundle.addons.css">
    <!-- endinject -->
    <!-- plugin css for this page -->
    <!-- End plugin css for this page -->
    <!-- inject:css -->
    <link rel="stylesheet" href="/assets/css/shared/style.css">
    <!-- endinject -->
    <!-- Layout styles -->
    <link rel="stylesheet" href="/assets/css/demo_1/style.css">
    <!-- End Layout styles -->
    <link rel="shortcut icon" href="/assets/images/favicon.ico"/>
</head>
<body>
<div class="container-scroller">
    <!-- partial:../../partials/_navbar.html -->
    <jsp:include page="/partials/_navbar.jsp"></jsp:include>
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
        <!-- partial:../../partials/_sidebar.html -->
        <jsp:include page="/partials/_sidebar.jsp"></jsp:include>
        <!-- partial -->
        <div class="main-panel">
            <div class="content-wrapper">
                <div class="row">

                    <div class="col-lg-12 stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <h2 class="card-title">Item on sale in menu</h2>
                                <a href="/item/create">tạo mới</a>
                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th> Id</th>
                                        <th> Name</th>
                                        <th> Category</th>
                                        <th> Avatar</th>
                                        <th> Description</th>
                                        <th> Price</th>
                                        <th> Status</th>
                                        <th> StartDay</th>
                                        <th> UpdateDay</th>
                                        <th> Action</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <% if (listItem != null && listItem.size() > 0) {
                                        for (int i = 0; i < listItem.size(); i++) {
                                    %>
                                    <tr>
                                        <td><%= listItem.get(i).getItemId()%>
                                        </td>
                                        <td><%= listItem.get(i).getItemName()%>
                                        </td>
                                        <td><%=listCate.get(listItem.get(i).getCategoryId()).getName() %>
                                        </td>
                                        <td>
                                            <img src="<%=listItem.get(i).getImage()%>"/>
                                        </td>
                                        <td><%= listItem.get(i).getDescription()%>
                                        </td>
                                        <td><%= listItem.get(i).getPrice()%>
                                        </td>
                                        <td><%= listItem.get(i).getStatus()%>
                                        </td>
                                        <td><%= listItem.get(i).getStartDay()%>
                                        </td>
                                        <td><%= listItem.get(i).getUpdateDay()%>
                                        </td>
                                        <td>


                                            <a href='/item/edit?id=<%=listItem.get(i).getItemId()%>' >Edit</a> |
                                            <a href='/item/delete?id<%=listItem.get(i).getItemId()%>'>delete</a>

                                        </td>

                                    </tr>
                                    <%
                                            }
                                        }
                                    %>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- content-wrapper ends -->
            <!-- partial:../../partials/_footer.html -->
            <jsp:include page="/partials/_footer.jsp"></jsp:include>
            <!-- partial -->
        </div>
        <!-- main-panel ends -->
    </div>
    <!-- page-body-wrapper ends -->
</div>
<!-- container-scroller -->
<!-- plugins:js -->
<script src="/assets/vendors/js/vendor.bundle.base.js"></script>
<script src="/assets/vendors/js/vendor.bundle.addons.js"></script>
<!-- endinject -->
<!-- Plugin js for this page-->
<!-- End plugin js for this page-->
<!-- inject:js -->
<script src="/assets/js/shared/off-canvas.js"></script>
<script src="/assets/js/shared/misc.js"></script>
<!-- endinject -->
<!-- Custom js for this page-->
<script src="/assets/js/shared/jquery.cookie.js" type="text/javascript"></script>
<!-- End custom js for this page-->
</body>
</html>
