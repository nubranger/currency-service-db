<!doctype html>
<html lang="en">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

    <title>Currency Service</title>

</head>
<body>

<div class="container p-3 my-3 border">
    <h1 class="display-4">Exchange rate list</h1>
    <a href="/">BACK</a>

    <h2 class="lead alert alert-primary">Rate of EURO to ${selectedCurrencyName}</h2>
    <h3 class="lead alert alert-primary">From ${fromDate} to ${toDate}</h3>


    <form class="form-inline" method="get" action="/currencyinfo">

        <select class="form-control mb-2 mr-sm-2" name="selectedCurrencyCode">
            <option value="${selectedCurrencyCode}"> ${selectedCurrencyName} </option>
            <c:forEach var="entry" items="${currentFxRates}">
                <option value="${entry.currency}"> ${entry.currency} - ${currencyMap.get( entry.currency )} </option>
            </c:forEach>
        </select>

        <label class="mr-sm-2">From </label>
        <input class="form-control mb-2 mr-sm-2" type="date" name="fromDate">
        <label class="mr-sm-2">To</label>
        <input class="form-control mb-2 mr-sm-2" type="date" name="toDate">
        <input type="submit" value="Get" class="btn btn-outline-primary mb-2 ">
    </form>

    <hr class="my-4">

    <table class="table table-hover">
        <tr>
            <th>Date</th>
            <th>Rate</th>
        </tr>

        <c:forEach var="item" items="${ratesList}">
            <tr>
                <td>${item.date}</td>
                <td>${item.rate}</td>
            </tr>
        </c:forEach>

    </table>
</div>

</body>
</html>