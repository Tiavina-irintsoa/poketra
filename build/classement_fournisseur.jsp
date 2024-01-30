<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="classement.*" %>
<%@ page import="java.util.*" %>
<%@ page import="mapping.*" %>
<!DOCTYPE html>
<html lang="en">
<% 
  ClassementFournisseur classement = (ClassementFournisseur) request.getAttribute("classement");
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
    <!-- Layout wrapper -->
    <div class="layout-wrapper layout-content-navbar">
      <div class="layout-container">
        <%@ include file="navbar.jsp"%>
          <!-- Content wrapper -->
          <div class="content-wrapper">
            <div class="row">
              <div class="col-md-8"></div>
              <div class="col-md-4 text-center">
                <div class="dropdown">
                  <div class="card-header px-4">
                    <form action="classementFournisseur" class="form">
                      <div class="input-group">
                  
                      <select name="matiere" class="form-select" id="">
                      <%
                          for (int i = 0;i<classement.getMatieres().size();i++){ %>
                              <option value="<%= classement.getMatieres().get(i).getIdMatiere() %>" 
                                  <% if( classement.getMatieres().get(i).getIdMatiere()  == classement.getIdMatiere()){
                                          out.print("selected");
                                      } %>
                                  >
                                      <%= classement.getMatieres().get(i).getNomMatiere() %>
                                  </option>
                          <%    } %>
                    </select>
                    <input name="classement" value="<%= classement.getClassement()%>" type="hidden">
                    <input type="submit" class="btn btn-outline-primary"  value="ok">
                    </form>
                    </div>
                  </div>
                 </form>
                  
                </div>
              </div>
            </div>
          <div class="container-xxl flex-grow-1 container-p-y">
                    <h4 class="py-3 mb-4"><span class="text-muted fw-light">Fournisseur /</span> Classement</h4>

                    <!-- Basic Bootstrap Table -->
                    <div class="card" style="padding:20px;" >
                    <%-- </div> --%>

          <div class="row">
            <div class="col-12 col-md-12 col-lg-12 mb-4 order-1">
              <div class="">
                <div class="row row-bordered g-0">
        
                  <div class="col-md-12">
                    <div class="card-header d-flex align-items-center justify-content-between mb-4">
                      <h5 class="card-title m-0 me-2">Classement des fournisseurs par<span class="text-primary">  <%= classement.getClassementString()%></span></h5>
                      <div class="dropdown">
                        <button class="btn p-0" type="button" id="topVolume" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                          <i class="bx bx-dots-vertical-rounded"></i>
                        </button>
                        <div class="dropdown-menu dropdown-menu-end" aria-labelledby="topVolume">
                          <a class="dropdown-item" href="classementFournisseur?classement=0&matiere=<%=classement.getIdMatiere()%>">Classement par meilleure qualite</a>
                            <a class="dropdown-item" href="classementFournisseur?classement=1&matiere=<%=classement.getIdMatiere()%>">Classement par moindre pénalité</a>
                            <a class="dropdown-item" href="classementFournisseur?classement=2&matiere=<%=classement.getIdMatiere()%>">Classement par meilleur rapport qualité-prix</a>
                            <a class="dropdown-item" href="classementFournisseur?classement=3&matiere=<%=classement.getIdMatiere()%>">Classement par meilleur qualité et service</a>
                            <a class="dropdown-item" href="classementFournisseur?classement=4&matiere=<%=classement.getIdMatiere()%>">Classement par meilleure qualité et moindre variation</a>
                            <a class="dropdown-item" href="classementFournisseur?classement=5&matiere=<%=classement.getIdMatiere()%>">Classement par prix unitaire moyen</a>
                        </div>
                      </div>
                    </div>
                    <div class="table-responsive text-nowrap">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>Fournisseur</th>
                                    <th>Qualite</th>
                                    <th>Rapport qualite prix</th>
                                    <th>Prix unitaire</th>
                                    <th>Pénalité</th>
                                    <th>Qualité et service</th>
                                    <th>Variation de la qualité</th>
                                </tr>
                                </thead>
                                <tbody class="table-border-bottom-0">
                       <% for (FournisseurEfficacite f : classement.getResults()){%>
                            <tr>
                               <td><%= f.getFournisseur().getNom() %></td>
                                <td class="text-end"><%= f.getQualite()%></td>
                                <td class="text-end"><%= f.getRapportQualitePrix()%></td>
                                <td class="text-end"><%= f.getPrixUnitaire()%></td>
                                <td class="text-end"><%= f.getPenalite()%></td>
                                <td class="text-end"><%= f.getQualiteService()%></td>
                                <td class="text-end"><%= f.getVariationQualite()%></td>
                            </tr>
                       <%}%>
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