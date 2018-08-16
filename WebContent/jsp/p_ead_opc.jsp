<!DOCTYPE html>


<!--[if IE 9]>
  <link rel="stylesheet" type="text/css" href="../estilos/die-ie9.css" />
<![endif]-->
<%@ page language="java"
	import="java.util.ArrayList,java.util.HashMap,pantallas.Informativa"%>
<html>
<head>
<link href="../imagen/LOGO.ico"  type="image/x-icon" rel="shortcut icon">
<title>Pleneaci&oacute;n EAD</title>


<!-- STYLESHEETS  -->
<link href="../estilos/normalize.min.css" rel="stylesheet" media="screen" />
<link href="../estilos/main.css" rel="stylesheet" media="screen" />
<!--
 <link href="../estilos/foundation.css" rel="stylesheet" type="text/css" /> 
 -->
<link href="../estilos/font_futura_stylesheet.css" rel="stylesheet" type="text/css" />
<!--
<link href="../estilos/font-awesome.css" rel="stylesheet" type="text/css" />
-->

<link rel="stylesheet" type="text/css" href="../estilos/hoja.css" />
<link rel="stylesheet" type="text/css" href="../estilos/ipad-landscape.css">
<link rel="stylesheet" type="text/css" href="../estilos/ipad-portrait.css">



<!-- vendor js -->
<script src="../jQueryAssets/jquery-1.11.1.min.js" type="text/javascript"></script>

<!-- smooothState js -->
<script src="../js/smoothState/jquery.smoothState.js"></script>

<!-- este inicia el smoothState -->
<script src="../js/smoothState/smoothState-index.js"></script>





<!-- Navegadores obsoletos  -->

<!--[if lt IE 9]>
            <script src="../js/vendor/html5.js"></script>
<script>window.html5 || document.write('<script src="js/vendor/html5shiv.js"><\/script>')
</script>
<![endif]-->






<!-- 
=============================
 STYLES 
=============================
 -->
 
<style>

body, html, td, th {
font-family: Helvetica,Arial,sans-serif;
}


body {
  background-color: #E6E7E9;
}


h1 {
font-size: 2em;
font-weight: 400;
color: #0D4E94;
line-height: 1.3;
margin-top: 10px;
}
h1, h2, h3, h4, h5, h6 {
color: #0D4E94;
font-style: normal;
padding: 0;
margin-top: 0;
line-height: 1.1em;
top: 0px;
right: 0px;
left: 0px;
bottom: 0px;
border: 0;
/*font-size: 100%;*/
vertical-align: baseline;
-moz-box-sizing: border-box;
-webkit-box-sizing: border-box;
box-sizing: border-box;
}






a {
color: #0D86DF;
}





/*------------------------------------------------------------------
  						MEDIA QUERIES STYLES
-------------------------------------------------------------------*/
@media only screen and (max-width: 1100px) {
  main .work div {
    -webkit-justify-content: space-around;
    -ms-flex-pack: justify;
    justify-content: space-around;
  }

/*
  main figure:first-child {
    -webkit-order: 4;
    -ms-flex-order: 4;
    order: 4;
  }

  main figure:last-child {
    -webkit-order: -1;
    -ms-flex-order: -1;
    order: -1;
  }
*/
  main figure {
    -webkit-flex: 0 30%;
    -ms-flex: 0 30%;
    flex: 0 30%;
  }
  
  main img {
width: 90%;
/* border-bottom: 2px solid #ADD8E6; */
}

}
@media only screen and (max-width: 800px) {
  h2 {
    font-size: 2em;
    padding: 7px 0;
  }

  main {
    -webkit-flex-direction: column;
    -ms-flex-direction: column;
    flex-direction: column;
  }

  main .work {
    -webkit-flex: 0 0 auto;
    -ms-flex: 0 0 auto;
    flex: 0 0 auto;
  }

  main .work {
    border-top: 0;
  }

  main .work div {
    height: auto;
  }
}
@media only screen and (min-width: 501px) and (max-width: 800px) {
  main figure {
    -webkit-flex: 0 46%;
    -ms-flex: 0 46%;
    flex: 0 46%;
  }
}
@media only screen and (max-width: 500px) {
  header ul {
    -webkit-justify-content: center;
    -ms-flex-pack: center;
    justify-content: center;
  }

  header li:first-child {
    display: none;
  }

  header li {
    -webkit-flex: 1;
    -ms-flex: 1;
    flex: 1;
  }

  header li:not(:first-child) a {
    font-size: 1em;
  }

  header li:nth-child(2) {
    margin-left: 15px;
  }

  h2 {
    font-size: 1.5em;
  }

  main .work figure {
    -webkit-flex: 0 98%;
    -ms-flex: 0 98%;
    flex: 0 98%;
  }


  footer {
    height: auto;
    -webkit-flex-direction: column;
    -ms-flex-direction: column;
    flex-direction: column;
  }

  footer p {
    height: auto;
    line-height: 1;
    margin: 10px 0;
    text-align: center;
    padding: 5px;
    border-bottom: 1px solid #b9dfec;
  }

  footer ul {
    margin-bottom: 10px;
  }
  

}
@media only screen and (max-width: 350px) {
  header li:not(:first-child) a {
    font-size: .8em;
  }
}
h1, h2, h3, h4, h5, h6 {
  margin: 0 0 10px 0;
}

/*
body {
  background-color: rgba(13,78,148,1);
}*/


</style>

<meta charset="utf-8">

<!--
<body id="seccion-index">
-->
<%
	Informativa bitacoras = new Informativa();
	
   String sucursal  = request.getParameter("sucursal");
   String nombre = bitacoras.getnombresite(sucursal);
   
   
     
     
     String ipCustom = request.getRemoteAddr();
     String hostName = request.getRemoteHost();
	
     


%>

<body>
<!-- 1.- Home page -->

 <!-- Every smoothState container needs an id -->
 <div class="m-scene" id="main">

<!-- 1.1.1 keyframe animation wrapper -->
<div class="page-wrap">
  
  <header class="m-header scene_element scene_element--fadein site-header">
    
    <a href="../jsp/index_ead.jsp" class="logo"><img src="../imagen/paquetexpress-logo-invertido.svg"  width="180" height="38" onerror="this.src='../imagen/logo-inv.png';this.onerror=null;" alt=""/> </a>
     <!--
    <nav>
      <ul>
     
        <li><a href="#"></a></li>
        <li><a href="#"></a></li>
        <li><a href="#"></a></li>
        <li><a href="#"></a></li>
      
      </ul>
    </nav> 
      -->
  </header>
  
  <div class="m-header scene_element scene_element--fadein banner">
 <!--   <h1>Flexbox makes things easier!</h1>-->
   
   
  <h2>SUCURSAL&nbsp;&nbsp;<%=nombre%></h2> 
  </div>
  
  <div class="m-page scene_element scene_element--fadeinup">
  <main>

    <section class="work">
      <h3>&nbsp;</h3>
      <div>
        <figure> <a href="../jsp/p_ead_piso.jsp?sucursal=<%=sucursal%>" target="_blank"><img src="../imagen/mercancia-en-piso-icon-trazabilidad.svg" alt=""/></a>
        <br />
            <a href="../jsp/p_ead_piso.jsp?sucursal=<%=sucursal%>" target="_blank" class="btn-oldstyle-nofloat centered"> 
            <figcaption>
            <span class="underline-white"><strong>Mercancía en Piso</strong></span>
            </figcaption> 
          </a>
        </figure>
        <figure> 
        <a href="../jsp/p_llegadas.jsp?sucursal=<%=sucursal%>" target="_blank"><img src="../imagen/llegadas pendientes - iconos trazabilidad.svg" alt=""/></a>
        <br />
       
            <a href="../jsp/p_llegadas.jsp?sucursal=<%=sucursal%>"  target="_blank" class="btn-oldstyle-nofloat centered">
              <figcaption><span class="underline-white"><strong>Mercancía en Tr&aacute;nsito</strong></span>
            </figcaption> 
          </a>
        </figure>
      </div>
    </section>
   
    
  </main>
  

  
</div>  

</div> <!-- PAGEWRAP keyframe animation wrapper END -->


</div> <!--  MAIN Every smoothState container needs an id -->


</body>
</html>

