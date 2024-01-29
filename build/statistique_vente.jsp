<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="stats.*" %>
<%@ page import="java.util.*" %>
<%@ page import="mapping.*" %>
<!DOCTYPE html>
<html lang="en">
<%
    
    StatistiqueVenteGlobal stat = (StatistiqueVenteGlobal)request.getAttribute("stat");
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
            <!-- Content -->
            <div class="row">
              <div class="col-md-8"></div>
              <div class="col-md-4 text-center">
                <div class="dropdown">
                  <div class="card-header px-4">
                    <form action="statVenteGlobal" class="form">
                      <div class="input-group">
                      <input type="hidden" name="annee" value="<%= stat.getAnnee() %>">
                      <select name="sac" class="form-select" id="">
                      <option value="0">Tous</option>
                      <%
                          for (int i = 0;i<stat.getSacs().size();i++){ %>
                              <option value="<%= stat.getSacs().get(i).getIdSac() %>" 
                                  <% if(stat.getSacs().get(i).getIdSac() == Integer.valueOf(stat.getIdSac())){
                                          out.print("selected");
                                      } %>
                                  >
                                      <%= stat.getSacs().get(i).getNomSac() %>
                                  </option>
                          <%    } %>
                    </select>
                    <input type="submit" class="btn btn-outline-primary"  value="ok">
                    </form>
                    </div>
                  </div>
                 </form>
                  
                </div>
              </div>
            </div>
            <div class="container-xxl flex-grow-1 container-p-y">
              <div class="row">
                <div class="col-lg-12 col-md-12 order-1">
                  <div class="col-12 col-md-8 col-lg-12 order-3 order-md-2">
                    <div class="row">
                      <div class="col-3 mb-4">
                        <div class="card">
                          <div class="card-body">
                            <div class="card-title d-flex align-items-start justify-content-between">
                              <div class="avatar flex-shrink-0">
                                <svg  viewBox="0 0 24 24" id="shopping-cart-alt"><path fill="#6563FF" d="M21.5,15a3,3,0,0,0-1.9-2.78l1.87-7a1,1,0,0,0-.18-.87A1,1,0,0,0,20.5,4H6.8L6.47,2.74A1,1,0,0,0,5.5,2h-2V4H4.73l2.48,9.26a1,1,0,0,0,1,.74H18.5a1,1,0,0,1,0,2H5.5a1,1,0,0,0,0,2H6.68a3,3,0,1,0,5.64,0h2.36a3,3,0,1,0,5.82,1,2.94,2.94,0,0,0-.4-1.47A3,3,0,0,0,21.5,15Zm-3.91-3H9L7.34,6H19.2ZM9.5,20a1,1,0,1,1,1-1A1,1,0,0,1,9.5,20Zm8,0a1,1,0,1,1,1-1A1,1,0,0,1,17.5,20Z"></path></svg>
                              </div>
                            
                            </div>
                            <span class="d-block mb-1">Total des ventes</span>
                            <h3 class="card-title text-nowrap mb-2"><%= stat.getStatistiqueVente().getTotalVente() %>sacs</h3>
                          </div>
                        </div>
                      </div>
                      <div class="col-3 mb-4">
                        <div class="card">
                          <div class="card-body">
                            <div class="card-title d-flex align-items-start justify-content-between">
                              <div class="avatar flex-shrink-0">
                                <svg  viewBox="0 0 24 24" id="wallet"><path fill="#6563FF" d="M19,7H18V6a3,3,0,0,0-3-3H5A3,3,0,0,0,2,6H2V18a3,3,0,0,0,3,3H19a3,3,0,0,0,3-3V10A3,3,0,0,0,19,7ZM5,5H15a1,1,0,0,1,1,1V7H5A1,1,0,0,1,5,5ZM20,15H19a1,1,0,0,1,0-2h1Zm0-4H19a3,3,0,0,0,0,6h1v1a1,1,0,0,1-1,1H5a1,1,0,0,1-1-1V8.83A3,3,0,0,0,5,9H19a1,1,0,0,1,1,1Z"></path></svg>
                              </div>
                              
                            </div>
                            <span class="fw-medium d-block mb-1">Bénéfice total</span>
                            <h3 class="card-title mb-2">MGA <%= stat.getStatistiqueVente().getBeneficeTotal() %></h3>
                      
                          </div>
                        </div>
                      </div>
                      <!-- </div>
      <div class="row"> -->
        <div class="col-3 mb-4">
          <div class="card">
            <div class="card-body">
              <div class="card-title d-flex align-items-start justify-content-between">
                <div class="avatar flex-shrink-0">
                  <svg  data-name="Layer 1" viewBox="0 0 24 24" id="balance-scale"><path fill="#6563FF" d="M22.96423,13.82263a.94762.94762,0,0,0-.02819-.17419L20.63135,7.51135A2.99558,2.99558,0,0,0,22,5a1,1,0,0,0-2,0,1.00037,1.00037,0,0,1-1.88184.47266A2.8934,2.8934,0,0,0,15.54,4H13V3a1,1,0,0,0-2,0V4H8.46A2.8934,2.8934,0,0,0,5.88184,5.47266,1.00037,1.00037,0,0,1,4,5,1,1,0,0,0,2,5,2.99558,2.99558,0,0,0,3.36865,7.51135L1.064,13.64844a.94762.94762,0,0,0-.02819.17419A.94855.94855,0,0,0,1,14c0,.00928.00269.01782.00275.0271.0003.01318.003.02533.0039.03845a3.99379,3.99379,0,0,0,7.9867,0c.00085-.01312.0036-.02527.0039-.03845C8.99731,14.01782,9,14.00928,9,14a.94855.94855,0,0,0-.03577-.17737.94762.94762,0,0,0-.02819-.17419L6.62866,7.50421A2.98961,2.98961,0,0,0,7.64258,6.41992.917.917,0,0,1,8.46,6H11V20H8a1,1,0,0,0,0,2h8a1,1,0,0,0,0-2H13V6h2.54a.917.917,0,0,1,.81738.41992,2.98961,2.98961,0,0,0,1.01392,1.08429L15.064,13.64844a.94762.94762,0,0,0-.02819.17419A.94855.94855,0,0,0,15,14c0,.00928.00269.01782.00275.0271.0003.01318.003.02533.0039.03845a3.99379,3.99379,0,0,0,7.9867,0c.00085-.01312.0036-.02527.0039-.03845C22.99731,14.01782,23,14.00928,23,14A.94855.94855,0,0,0,22.96423,13.82263ZM5,8.85553,6.5564,13H3.4436ZM6.72266,15A2.02306,2.02306,0,0,1,5,16a2.00023,2.00023,0,0,1-1.73145-1ZM19,8.85553,20.5564,13H17.4436ZM19,16a2.00023,2.00023,0,0,1-1.73145-1h3.45411A2.02306,2.02306,0,0,1,19,16Z"></path></svg>
                </div>
                
              </div>
              <span class="fw-medium d-block mb-1">Charges</span>
              <h3 class="card-title mb-2">MGA <%= stat.getStatistiqueVente().getCharge() %></h3>
        
            </div>
          </div>
        </div>
        <div class="col-3 mb-4">
          <div class="card">
            <div class="card-body">
              <div class="card-title d-flex align-items-start justify-content-between">
                <div class="avatar flex-shrink-0">
                  <svg  data-name="Layer 1" viewBox="0 0 24 24" id="transaction"><path fill="#6563FF" d="M20 2H10a3 3 0 0 0-3 3v7a3 3 0 0 0 3 3h10a3 3 0 0 0 3-3V5a3 3 0 0 0-3-3Zm1 10a1 1 0 0 1-1 1H10a1 1 0 0 1-1-1V5a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1Zm-3.5-4a1.49 1.49 0 0 0-1 .39 1.5 1.5 0 1 0 0 2.22 1.5 1.5 0 1 0 1-2.61ZM16 17a1 1 0 0 0-1 1v1a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1v-4h1a1 1 0 0 0 0-2H3v-1a1 1 0 0 1 1-1 1 1 0 0 0 0-2 3 3 0 0 0-3 3v7a3 3 0 0 0 3 3h10a3 3 0 0 0 3-3v-1a1 1 0 0 0-1-1ZM6 18h1a1 1 0 0 0 0-2H6a1 1 0 0 0 0 2Z"></path></svg>              </div>
                
              </div>
              <span class="fw-medium d-block mb-1">Chiffres d'affaires</span>
              <h3 class="card-title mb-2">MGA <%= stat.getStatistiqueVente().getChiffreAffaire() %></h3>
        
            </div>
          </div>
        </div>
                    </div>
                  </div>
                </div>
                <!-- Total Revenue -->
                <div class="col-12 col-lg-8 order-2 order-md-3 order-lg-2 mb-4">
                  <div class="card row">
                   
                    
                    <div class="row row-bordered g-0">
                      <div class="col-md-12">
                        <div class="row card-header">
                          <div class="col-8">
                            <h5 class="m-0 me-2 pb-2">Nombre de vente par mois</h5>
                          </div>
                          <div class="col-4">
                            <form action="statVenteGlobal" class="form">
                            <div class="input-group">
                              <select name="annee" class="form-select" id="">
                              
                              <%
                                  for (int i = 0;i<stat.getAnnees().size();i++){ %>
                                      <option value="<%= stat.getAnnees().get(i) %>" 
                                          <% if(stat.getAnnees().get(i).equals(Integer.valueOf(stat.getAnnee()))){
                                                  out.print("selected");
                                              } %>
                                          >
                                              <%= stat.getAnnees().get(i) %>
                                          </option>
                                  <%    } %>
                                  <input type="hidden" name="sac" value="<%= stat.getIdSac() %>">
                                  <input type="submit" class="btn btn-outline-primary"  value="Choisir">
                            </select>
                          </div>
                          </div>
                        </div>
                        <div id="totalRevenueChart" class="px-2"></div>
                      </div>
                    </div>
                  </div>
                  
                </div>
                <div class="col-md-6 col-lg-4 order-2 mb-4">
                  <div class="card h-100">

                    <div class="card-body px-0">
                      <div class="tab-content p-0">
                        <div class="tab-pane fade show active" id="navs-tabs-line-card-income" role="tabpanel">
                          <div class="d-flex p-4 pt-3">
                            <div class="col-12">
                              <h5 class="m-0 me-2 pb-2">Nombre de vente par genre</h5>
                            </div>
                          </div>
                          <div id="genreChart"></div>
                          
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <!--/ Total Revenue -->
                
              </div>
             
            </div>

            <!-- Footer -->
           
            <!-- / Footer -->

            <div class="content-backdrop fade"></div>
          </div>
          <!-- Content wrapper -->
        </div>
        <!-- / Layout page -->
      </div>

      <!-- Overlay -->
      <div class="layout-overlay layout-menu-toggle"></div>
    </div>
    <!-- / Layout wrapper -->

    <!-- Core JS -->
    <!-- build:js assets/vendor/js/core.js -->

    <script src="assets/vendor/libs/jquery/jquery.js"></script>
    <script src="assets/vendor/libs/popper/popper.js"></script>
    <script src="assets/vendor/js/bootstrap.js"></script>
    <script src="assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>
    <script src="assets/vendor/js/menu.js"></script>

    <!-- endbuild -->

    <!-- Vendors JS -->
    <script src="assets/vendor/libs/apex-charts/apexcharts.js"></script>

    <!-- Main JS -->
    <script src="assets/js/main.js"></script>

    <!-- Page JS -->

    <!-- Place this tag in your head or just before your close body tag. -->
    <script async defer src="https://buttons.github.io/buttons.js"></script>
    <script>
      /**
 * Dashboard Analytics
 */


(function () {
  let cardColor, headingColor, axisColor, shadeColor, borderColor;

  cardColor = config.colors.cardColor;
  headingColor = config.colors.headingColor;
  axisColor = config.colors.axisColor;
  borderColor = config.colors.borderColor;

  // Total Revenue Report Chart - Bar Chart
  // --------------------------------------------------------------------
  const totalRevenueChartEl = document.querySelector('#totalRevenueChart'),
    totalRevenueChartOptions = {
      series: [
        {
          name: <%= stat.getAnnee() %>,
          data: <%= stat.getStatistiqueParMois().getStatMoisString()%>
        }
      ],
      chart: {
        height: 300,
        stacked: true,
        type: 'bar',
        toolbar: { show: false }
      },
      plotOptions: {
        bar: {
          horizontal: false,
          columnWidth: '33%',
          borderRadius: 12,
          startingShape: 'rounded',
          endingShape: 'rounded'
        }
      },
      colors: [config.colors.primary, config.colors.info],
      dataLabels: {
        enabled: false
      },
      stroke: {
        curve: 'smooth',
        width: 6,
        lineCap: 'round',
        colors: [cardColor]
      },
      legend: {
        show: true,
        horizontalAlign: 'left',
        position: 'top',
        markers: {
          height: 8,
          width: 8,
          radius: 12,
          offsetX: -3
        },
        labels: {
          colors: axisColor
        },
        itemMargin: {
          horizontal: 10
        }
      },
      grid: {
        borderColor: borderColor,
        padding: {
          top: 0,
          bottom: -8,
          left: 20,
          right: 20
        }
      },
      xaxis: {
        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul','Aug','Sept','Oct','Nov','Dec'],
        labels: {
          style: {
            fontSize: '13px',
            colors: axisColor
          }
        },
        axisTicks: {
          show: false
        },
        axisBorder: {
          show: false
        }
      },
      yaxis: {
        labels: {
          style: {
            fontSize: '13px',
            colors: axisColor
          }
        }
      },
      responsive: [
        {
          breakpoint: 1700,
          options: {
            plotOptions: {
              bar: {
                borderRadius: 10,
                columnWidth: '32%'
              }
            }
          }
        },
        {
          breakpoint: 1580,
          options: {
            plotOptions: {
              bar: {
                borderRadius: 10,
                columnWidth: '35%'
              }
            }
          }
        },
        {
          breakpoint: 1440,
          options: {
            plotOptions: {
              bar: {
                borderRadius: 10,
                columnWidth: '42%'
              }
            }
          }
        },
        {
          breakpoint: 1300,
          options: {
            plotOptions: {
              bar: {
                borderRadius: 10,
                columnWidth: '48%'
              }
            }
          }
        },
        {
          breakpoint: 1200,
          options: {
            plotOptions: {
              bar: {
                borderRadius: 10,
                columnWidth: '40%'
              }
            }
          }
        },
        {
          breakpoint: 1040,
          options: {
            plotOptions: {
              bar: {
                borderRadius: 11,
                columnWidth: '48%'
              }
            }
          }
        },
        {
          breakpoint: 991,
          options: {
            plotOptions: {
              bar: {
                borderRadius: 10,
                columnWidth: '30%'
              }
            }
          }
        },
        {
          breakpoint: 840,
          options: {
            plotOptions: {
              bar: {
                borderRadius: 10,
                columnWidth: '35%'
              }
            }
          }
        },
        {
          breakpoint: 768,
          options: {
            plotOptions: {
              bar: {
                borderRadius: 10,
                columnWidth: '28%'
              }
            }
          }
        },
        {
          breakpoint: 640,
          options: {
            plotOptions: {
              bar: {
                borderRadius: 10,
                columnWidth: '32%'
              }
            }
          }
        },
        {
          breakpoint: 576,
          options: {
            plotOptions: {
              bar: {
                borderRadius: 10,
                columnWidth: '37%'
              }
            }
          }
        },
        {
          breakpoint: 480,
          options: {
            plotOptions: {
              bar: {
                borderRadius: 10,
                columnWidth: '45%'
              }
            }
          }
        },
        {
          breakpoint: 420,
          options: {
            plotOptions: {
              bar: {
                borderRadius: 10,
                columnWidth: '52%'
              }
            }
          }
        },
        {
          breakpoint: 380,
          options: {
            plotOptions: {
              bar: {
                borderRadius: 10,
                columnWidth: '60%'
              }
            }
          }
        }
      ],
      states: {
        hover: {
          filter: {
            type: 'none'
          }
        },
        active: {
          filter: {
            type: 'none'
          }
        }
      }
    };
  if (typeof totalRevenueChartEl !== undefined && totalRevenueChartEl !== null) {
    const fabrication = new ApexCharts(totalRevenueChartEl, totalRevenueChartOptions);
    fabrication.render();
  }


// Income Chart - Area chart
// --------------------------------------------------------------------
const incomeChartEl = document.querySelector('#genreChart');
let incomeChartConfig = {
 series: [<%= stat.getStatistiquesParGenre().getStatFemme() %>, <%= stat.getStatistiquesParGenre().getStatHomme() %>],
 labels: ['Femme', 'Homme'],
 chart: {
    height: 250,
    parentHeightOffset: 0,
    parentWidthOffset: 0,
    toolbar: {
      show: false
    },
    type: 'donut'
 },
 dataLabels: {
    enabled: false
 },
 stroke: {
    width: 2,
    curve: 'smooth'
 },
 legend: {
    show: true, // Show the legend
    position: 'bottom', // Position of the legend
    horizontalAlign: 'center', // Horizontal alignment of the legend
    fontSize: '14px', // Font size of the legend text
    fontFamily: 'Helvetica, Arial', // Font family of the legend text
    fontWeight: 400, // Font weight of the legend text
 },
 markers: {
    size: 6,
    colors: 'transparent',
    strokeColors: 'transparent',
    strokeWidth: 4,
    discrete: [
      {
        seriesIndex: 0,
        dataPointIndex: 7,
        strokeWidth: 2,
        size: 6,
        radius: 8
      }
    ],
    hover: {
      size: 7
    }
 },
 colors: ['rgb(255, 99, 132)', 'rgb(54, 162, 235)'], 
 grid: {
    borderColor: 'grey', 
    strokeDashArray: 3,
 }
};
if (incomeChartEl !== null) {
 const incomeChart = new ApexCharts(incomeChartEl, incomeChartConfig);
 incomeChart.render();
}

})();

    </script>
  </body>
</html>
