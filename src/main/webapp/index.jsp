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
    <h1 class="display-4"> Currency Converter</h1>

    <form class="form-inline" method="get" action="/">

        <label class="mr-sm-2">Amount of EUR </label>
        <input type="text" class="form-control mb-2 mr-sm-2" name="amount">
        <label class="mr-sm-2">To</label>
        <select class="form-control mb-2 mr-sm-2" name="selectedCurrencyCode">
            <option value="${selectedCurrencyCode}"> ${selectedCurrencyName} </option>
            <c:forEach var="entry" items="${currentFxRates}">
                <option value="${entry.currency}"> ${entry.currency} - ${currencyMap.get( entry.currency )} </option>
            </c:forEach>
        </select>
        <input type="submit" value="Convert" class="btn btn-outline-primary mb-2 ">
    </form>

    <h2 class="lead alert alert-primary"> ${amount} EUR to ${selectedCurrencyCode}
        = ${currencyResult} ${selectedCurrencyName} </h2>
    <h3 class="lead alert alert-primary">Rate is ${currencyRate} </h3>

    <hr class="my-4">

    <h1 class="display-4"> Currency List :: ${date} </h1>

    <table class="table table-hover">
        <tr>
            <th>Currency name</th>
            <th>Currency code</th>
            <th>Rate (1 EUR)</th>
        </tr>
        <c:forEach var="item" items="${currentFxRates}">
            <tr>
                <td>
                    <a href="/currencyinfo?selectedCurrencyCode=${item.currency}">
                            ${currencyMap.get( item.currency )}
                    </a>
                </td>
                <td>${item.currency}</td>
                <td>${item.rate}</td>
            </tr>
        </c:forEach>

    </table>
</div>

</body>
</html>