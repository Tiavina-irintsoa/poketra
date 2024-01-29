<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="mapping.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<%  
        Vector<Sac> sacs= (Vector<Sac>)request.getAttribute("sacs");
        Vector<Matiere> matieres= (Vector<Matiere>)request.getAttribute("matieres");
    %>
<head>
    <head>
        <meta charset="utf-8" />
        <meta
                name="viewport"
                content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0" />

        <title>Poketra</title>

        <meta name="description" content="" />

        <!-- Favicon -->
        <link rel="icon" type="image/x-icon" href="assets/img/favicon/favicon.ico" />

        <!-- Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
                href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
                rel="stylesheet" />

        <link rel="stylesheet" href="assets/vendor/fonts/boxicons.css" />

        <!-- Core CSS -->
        <link rel="stylesheet" href="assets/vendor/css/core.css" class="template-customizer-core-css" />
        <link rel="stylesheet" href="assets/vendor/css/theme-default.css" class="template-customizer-theme-css" />
        <link rel="stylesheet" href="assets/css/demo.css" />

        <!-- Vendors CSS -->
        <link rel="stylesheet" href="assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" />

        <!-- Page CSS -->

        <!-- Helpers -->
        <script src="assets/vendor/js/helpers.js"></script>
        <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
        <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
        <script src="assets/js/config.js"></script>
    </head>

<body>
<header></header>

<main>
<div class="layout-wrapper layout-content-navbar">
      <div class="layout-container">
        <%@ include file="navbar.jsp"%>
    <div class="content-wrapper">
        <!-- Content -->

        <div class="container-xxl flex-grow-1 container-p-y">
            <h4 class="py-3 mb-6"><span class="text-muted fw-light"></span> Formule de confection</h4>
            <div class="row">

                <div class="col-md-12">
                    <div class="card mb-12">
                        <div class="card-header d-flex align-items-center justify-content-between">
                            <h5 class="mb-0">Formulaire de confection</h5>
                        </div>
                        <div class="card-body">
                            <form action="ajoutFormuleSubmit" method="post" class="form">
                                <div class="row mb-3">
                                    <div class="input-group">
                                        <label class="input-group-text" for="inputGroupSelect03">Sac</label>
                                        <select class="form-select" id="inputGroupSelect03" name="sac">
                                            <% 
                                                for (int i = 0;i<sacs.size();i++){ %>
                                                    <option value="<%= sacs.get(i).getIdSac()() %>"><%= sacs.get(i).getNomSac()() %></option>
                                            <%    }
                                            %>
                                            
                                        </select>
                                    </div>
                                </div>
                                <% 
                                    for(int i = 0;i<matieres.size();i++){ %>
                                        <label class="col-sm-2 col-form-label" for="mat1"><%= matieres.get(i).getNomMatiere()%></label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="mat1"  name="quantite-<%= matieres.get(i).getIdMatiere()%>"/>
                                        </div>
                               <%     }
                                %>
                               
                                <div class="row justify-content-end">
                                    <div class="col-sm-10">
                                        <button type="submit" class="btn btn-primary mt-3">Send</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
        </div>
</main>

<footer></footer>

<script src="assets/vendor/libs/jquery/jquery.js"></script>
<script src="assets/vendor/libs/popper/popper.js"></script>
<script src="assets/vendor/js/bootstrap.js"></script>
<script src="assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>
<script src="assets/vendor/js/menu.js"></script>

<!-- endbuild -->

<!-- Vendors JS -->

<!-- Main JS -->
<script src="assets/js/main.js"></script>

<!-- Page JS -->

<!-- Place this tag in your head or just before your close body tag. -->
<script async defer src="https://buttons.github.io/buttons.js"></script>

</body>
<!-- Core JS -->
<!-- build:js assets/vendor/js/core.js -->

<script src="assets/vendor/libs/jquery/jquery.js"></script>
<script src="assets/vendor/libs/popper/popper.js"></script>
<script src="assets/vendor/js/bootstrap.js"></script>
<script src="assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>
<script src="assets/vendor/js/menu.js"></script>

<!-- endbuild -->

<!-- Vendors JS -->

<!-- Main JS -->
<script src="assets/js/main.js"></script>

<!-- Page JS -->

<!-- Place this tag in your head or just before your close body tag. -->
<script async defer src="https://buttons.github.io/buttons.js"></script>

</html>