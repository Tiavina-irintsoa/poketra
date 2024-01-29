<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Vector" %>
<%@ page import="mapping.FicheLook" %>
<!DOCTYPE html>
<html lang="en">

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
<%
    FicheLook ficheLook= (FicheLook) request.getAttribute("look");
%>

<main>
<div class="layout-wrapper layout-content-navbar">
      <div class="layout-container">
        <%@ include file="navbar.jsp"%>
    <div class="content-wrapper">
        <!-- Content -->

        <div class="container-xxl flex-grow-1 container-p-y">
            <h4 class="py-3 mb-4"><span class="text-muted fw-light"></span>Détails du look</h4>

            <!-- Basic Bootstrap Table -->
            <div class="card">
                <div class="card-body">
                        <div class="table-responsive text-nowrap">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Look</th>
                            <th>Dimension</th>
                            <th>Type</th>
                            <th>Quantiter</th>
                        </tr>
                        </thead>
                        <tbody class="table-border-bottom-0">
                        <%
                            for (int i = 0; i <ficheLook.getListMatieres().size(); i++) {%>
                        <tr>
                            <td>
                                <span class="fw-medium"><%= ficheLook.getListMatieres().get(i).getNomMatiere()%></span>
                            </td>
                            <td>
                                <span class="fw-medium"><%= ficheLook.getListMatieres().get(i).getNomMatiere()%></span>
                            </td>
                            <td>
                                <span class="fw-medium"><%= ficheLook.getListMatieres().get(i).getNomMatiere()%></span>
                            </td>
                            <td>
                                <span class="fw-medium"><%= ficheLook.getListMatieres().get(i).getNomMatiere()%></span>
                            </td>
                        </tr>
                        <%
                            }
                        %>

                        </tbody>
                    </table>
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

</html>