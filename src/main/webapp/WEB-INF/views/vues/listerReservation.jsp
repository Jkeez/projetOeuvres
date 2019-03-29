<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>

	<div class="jumbotron text-center">
		<h1>Listing des reservations</h1>
	</div>

	<div class="container">
		<a class="btn btn-secondary" href="../index.htm" role="button"><span class="glyphicon glyphicon-menu-left"></span> Retour accueil</a>
		<h2>Tableau des Reservations</h2>
		<div class="container">
			<h3>Liste des Reservations</h3>
			<table class="table table-hover">
				<tr>
					<th class="col-md-2">Oeuvre</th>
					<th class="col-md-2">Adherent</th>
					<th class="col-md-4">Date de reservation</th>
					<th class="col-md-4">Prix de l'oeuvre</th>
					<th class="col-md-4">Statut</th>
					<th class="col-md-4">Reserver / Modifier / Confirmer achat</th>


				</tr>

				<c:forEach items="${mesReservations}" var="item">
					<tr>
						<td>${item.oeuvrevente.titreOeuvrevente}</td>
						<td>${item.adherent.prenomAdherent}</td>
						<td>${item.dateReservation}</td>
						<td>${item.oeuvrevente.prixOeuvrevente} €</td>
						<td>${item.statut}</td>
						<td>
							<a class="btn btn-info" href="modifierReservation.htm?id=${item.idReservationOeuvrevente}" role="button"><span
								class="glyphicon glyphicon-pencil"></span> Modifier</a>
							<a class="btn btn-info" href="supprimerReservation.htm?id=${item.idReservationOeuvrevente}" role="button"><span
									class="glyphicon glyphicon-pencil"></span> Supprimer</a>
							<c:choose>
							<c:when test="${item.oeuvrevente.etatOeuvrevente == 'L'}">
								<a class="btn btn-info disabled" href="confirmerReservation.htm?id=${item.idReservationOeuvrevente}" role="button"><span
										class="glyphicon glyphicon-pencil"></span> Confirmer achat</a>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${item.oeuvrevente.etatOeuvrevente == 'V'}">
										<a class="btn btn-info disabled" href="confirmerReservation.htm?id=${item.idReservationOeuvrevente}" role="button"><span
												class="glyphicon glyphicon-pencil"></span> Vendue</a>
									</c:when>
									<c:otherwise>
										<a class="btn btn-info" href="confirmerReservation.htm?id=${item.idReservationOeuvrevente}" role="button"><span
												class="glyphicon glyphicon-pencil"></span> Confirmer achat</a>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
    </div>
<%@include file="footer.jsp"%>
</body>
</html>