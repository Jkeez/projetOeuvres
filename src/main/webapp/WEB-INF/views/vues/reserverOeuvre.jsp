<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<H1> Reserver une Oeuvre </H1>
<form method="post" action="ajouterReservationOeuvre.htm">
<div class="col-md-12 well well-md">
    <h1>Reserver Oeuvre</h1>
    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">Titre : </label>
        <div class="col-md-3">
            <INPUT type="hidden" name="oeuvre" value='<c:out value="${oeuvre.getIdOeuvrevente()}" />' id="nom" class="form-control" min="0">
            <INPUT type="text" name="txtnom" value='<c:out value="${oeuvre.getTitreOeuvrevente()}" />' id="nom" class="form-control" min="0" disabled>
        </div>

    </div>
    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">Prix : </label>
        <div class="col-md-3">
            <INPUT type="text" name="txtprix" value='<c:out value="${oeuvre.getPrixOeuvrevente()}" />' id="prix" class="form-control" min="0" disabled>
        </div>
    </div>

    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">Proprietaire : </label>
        <div class="col-md-3">
            <INPUT type="hidden" name="prop" value='<c:out value="${oeuvre.proprietaire.idProprietaire}" />' id="prop" class="form-control" min="0">
            <INPUT type="text" name="txtprix" value='<c:out value="${oeuvre.proprietaire.nomProprietaire}" />' id="prix" class="form-control" min="0" disabled>
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-3 control-label">Adherent : </label>
        <div class="col-md-3">
            <SELECT name="adherent" id="adherent" class="form-control" min="0">
                <c:forEach items="${adherents}" var="item">
                        <OPTION value="${item.idAdherent}" selected="selected">${item.nomAdherent}</OPTION>
                </c:forEach>
            </SELECT>
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-3 control-label">Date : </label>
        <div class="col-md-3">
            <input type="date" id="dateRes" name="dateRes"
                   value="<c:out value="${currentDate}" />"
                   min="<c:out value="${currentDate}" />" max="<c:out value="${maxDate}" />">
        </div>
    </div>



    <div class="form-group">
        <label class="col-md-3 control-label">Prix : </label>
        <div class="col-md-3">
            <input type="checkbox" name="achat" value="achatImmediat"> Achat immédiat<br>
        </div>
    </div>


    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-ok"></span>
            Reserver
        </button>

        <button type="button" class="btn btn-default btn-primary"
                onclick="{
                            window.location = './listerOeuvre.htm';
                        }">
            <span class="glyphicon glyphicon-remove"></span> Annuler

        </button>
    </div>
</div>
</form>

</body>
<%@include file="footer.jsp"%>
</html>