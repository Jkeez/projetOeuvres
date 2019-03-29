<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<H1> Modifier une Oeuvre </H1>
<form method="post" action="ajoutModificationOeuvre.htm">
<div class="col-md-12 well well-md">
    <h1>Modifier Oeuvre</h1>
    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">Titre : </label>
        <div class="col-md-3">
            <INPUT type="hidden" name="id" value='<c:out value="${oeuvre.getIdOeuvrevente()}" />' class="form-control">
            <INPUT type="text" name="txtnom" value='<c:out value="${oeuvre.getTitreOeuvrevente()}" />' id="nom" class="form-control" min="0">
        </div>

    </div>
    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">Prix : </label>
        <div class="col-md-3">
            <INPUT type="number" step="0.01" name="txtprix" value='<c:out value="${oeuvre.getPrixOeuvrevente()}" />' id="prix" class="form-control" min="0">
        </div>
    </div>
    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">Proprietaire : </label>
        <div class="col-md-3">
            <SELECT name="proprietaire" id="proprietaire" class="form-control" min="0">
                <c:forEach items="${props}" var="item">
                    <c:choose>
                        <c:when test="${oeuvre.proprietaire.nomProprietaire == item.nomProprietaire}"><OPTION value="${item.idProprietaire}" selected="selected">${item.nomProprietaire}</OPTION></c:when>
                        <c:otherwise><OPTION value="${item.idProprietaire}">${item.nomProprietaire}</OPTION></c:otherwise>
                    </c:choose>
                </c:forEach>
            </SELECT>
        </div>
    </div>
    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-ok"></span>
            Modifier
        </button>

        <button type="button" class="btn btn-default btn-primary"
                onclick="{
                            window.location = '../index.htm';
                        }">
            <span class="glyphicon glyphicon-remove"></span> Annuler

        </button>
    </div>
</div>
</form>
</body>
<%@include file="footer.jsp"%>
</html>