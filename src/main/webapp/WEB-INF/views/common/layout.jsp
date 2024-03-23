<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"
 %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"  %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <style>
        html, body{
            margin:0;
            padding: 0;
  			overflow-x: hidden;
        }
        .wrap{
        	min-width: 100vw;
            min-height : 100vh;
            position:relative;  /*footer 가 바닥에 붙어 있으려면. (푸터 : position:absolute bottom:0;) */ 
            padding-bottom: 110px; /*footer height.*/
        }
        header{
            background-color: #2d3539;
    		width: 100%;
    		height: 43px;
    		font-size: 20px;
    		font-weight: bold;
    		color: white;
    		padding: 10px 0 0 10px;
        }
        main{
            display: flex;
    		min-height: 100vh;
    		width: 100%;
        }
        aside{
        	background-color: #3f4b5c;
        	width: 150px;
        	padding:50px 10px;
        	display: flex;
        	flex-direction: column;        
        }
        section{
            width: 100%;
            background-color: white;
        }
        footer{
            background-color: rgb(35, 58, 58);
    		height: 110px;
    		width: 100%;
    		padding: 8px 8px 0 8px;
    		font-size: 12px;
    		position: absolute;
    		bottom: 0;
    		color: white;
}
        }
    </style>
    <title><tiles:insertAttribute name="title" /></title>
</head>
<body>
    <div class ="wrap">
    <header>
        <tiles:insertAttribute name="header"/>
    </header>
    <main>
        <aside>
            <tiles:insertAttribute name="side"/> 
        </aside>
        <section>
             <tiles:insertAttribute name="body"/>
        </section>
    </main>
    <footer>
       <tiles:insertAttribute name="footer"/>
    </footer>
</div>
</body>
</html>