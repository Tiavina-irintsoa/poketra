<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="stock.matiere.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
    <%  
        EtatStockMatiere etat= (EtatStockMatiere)request.getAttribute("etat");
    %>
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
                    <h4 class="py-3 mb-4"><span class="text-muted fw-light">Etat de stock /</span> Matiere</h4>

                    <!-- Basic Bootstrap Table -->
                    <div class="card">
                    <br>
                        <form action="etatStockMatiere" method="get" class="form" style="display:flex; justify-content: space-between;padding:2vh;">
                            <div class="input-group">
                                <span style="width:7.5%;display:flex;justify-content:center;align-items:center;">Du</span>
                                <input type="date" class="form-control" name="date1" style="width:30%" value="<%= etat.getDate1() %>">
                                <span style="width:7.5%;display:flex;justify-content:center;align-items:center;">Au</span>
                                <input type="date" value="<%= etat.getDate2() %>"class="form-control" name="date2" style="width:30%">
                                <input class="btn btn-outline-primary" type="submit" id="button-addon2" value="Rechercher">
                            </div>
                        </form>
                        <br>
                        <h5 class="card-header">Etat de stock</h5>
                        
                        <div class="card-body">
                        <div class="table-responsive text-nowrap">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>Matiere</th>
                                    <th>Début</th>
                                    <th>Fin</th>
                                    <th>Prix unitaire</th>
                                    <th>Montant total</th>
                                </tr>
                                </thead>
                                <tbody class="table-border-bottom-0">
                                <%
                                    for (StockMatiere s : etat.getStockMatieres()) {%>
                                <tr>
                                    <td><%=s.getMatiere().getNomMatiere()%></td>
                                    <td class="text-end"><%=s.getStockDate1()%></td>
                                    <td class="text-end"><%=s.getStockDate2()%></td>
                                    <td class="text-end"><%=s.getMatiere().getPrix()%></td>
                                    <td class="text-end"><%=s.getMontantTotal()%></td>
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