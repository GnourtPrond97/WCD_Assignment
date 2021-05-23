<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Category" %>

<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/22/2021
  Time: 5:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
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
    <link rel="stylesheet" href="/assets/vendors/icheck/skins/all.css">
    <!-- End plugin css for this page -->
    <!-- inject:css -->
    <link rel="stylesheet" href="/assets/css/shared/style.css">
    <!-- endinject -->
    <!-- Layout styles -->
    <link rel="stylesheet" href="/assets/css/demo_1/style.css">
    <!-- End Layout styles -->
    <link rel="shortcut icon" href="/assets/images/favicon.ico" />
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

                    <div class="col-12 grid-margin">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Horizontal Two column</h4>
                                <form class="form-sample" action="/item/create" method="post" id="create-item">
                                    <p class="card-description"> Personal info </p>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <label class="col-sm-3 col-form-label">itemId</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" name="itemId"   required/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <label class="col-sm-3 col-form-label">itemName</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" name="itemName" required/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <label class="col-sm-3 col-form-label">categoryId</label>
                                                <div class="col-sm-9">
                                                    <select name ="categoryId"  id ="categoryId" class="form-control">
                                                        <% if(listCate != null && listCate.size() > 0){
                                                            for(int i = 0; i < listCate.size(); i++){
                                                        %>
                                                        <li>
                                                          <option value=<%= listCate.get(i).getId()%>><%= listCate.get(i).getName()%></option>


                                                        </li>
                                                        <%
                                                                }
                                                            }
                                                        %>

                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <label class="col-sm-3 col-form-label">status</label>
                                                <div class="col-sm-9">
                                                    <select name="status" id="status" class="form-control">
                                                        <option value="ON_SALE">on sell</option>
                                                        <option value="STOP_SELLING">not selling</option>
                                                        <option value="DELETE">delete</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <label class="col-sm-3 col-form-label">avatar</label>
                                                <div class="col-sm-9">
<%--                                                    <input type="text" class="form-control" name="avatar"/>--%>
                                                    <button type="button" id="upload_widget" class="cloudinary-button">Upload files</button>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <label class="col-sm-3 col-form-label">price</label>
                                                <div class="col-sm-9 " >
                                                    <input type="text" class="form-control" name="price" required/>

                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <label class="col-sm-3 col-form-label"></label>
                                                <div class="col-sm-9 cover" >
<%--                                                    <img class="form-control" id="avatar"/>--%>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <label class="col-sm-3 col-form-label">description</label>
                                                <div class="col-sm-9">
                                                    <textarea type="text" class="form-control" name="description" ></textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <button type="submit" class="btn btn-success mr-2">Submit</button>
                                    <button  type="button" class="btn btn-light">Cancel</button>


                                </form>
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
<script src="https://upload-widget.cloudinary.com/global/all.js" type="text/javascript"></script>
<script src="https://widget.cloudinary.com/v2.0/global/all.js" type="text/javascript"></script>

<script type="text/javascript">
    document.addEventListener('DOMContentLoaded', function () {
        var myWidget = cloudinary.createUploadWidget({
                cloudName: 'dryj1v2fn',
                uploadPreset: 'cp4rqwxd',
                multiple: false,
                form: '#create-item',
                fieldName: 'avatar',
                thumbnails: '.cover',
                cropping: true,


            }, (error, result) => {
                if (!error && result && result.event === "success") {
                    var inputValues = document.querySelectorAll('input[name="avatar"]');
                    for (let i = 0; i < inputValues.length; i++) {
                        inputValues[i].value = inputValues[i].getAttribute('data-cloudinary-public-id')
                    }
                }
            }
        )
        document.getElementById("upload_widget").addEventListener("click", function () {
            myWidget.open();
        }, false);



        $('body').on('click', '.cloudinary-delete', function () {
            var splittedImg = $(this).parent().find('img').attr('src').split('/');
            var imgName = splittedImg[splittedImg.length - 1];
            var splittedImgName = imgName.split('.');
            var imgCode = splittedImgName[0];
            $('input[data-cloudinary-public-id="' + imgCode + '"]').remove();
        });
    })

</script>
<!-- End custom js for this page-->
</body>
</html>
