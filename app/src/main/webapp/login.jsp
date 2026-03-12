<%@ page contentType="text/html;charset=UTF-8" language ="java" %>

<!doctype html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Connexion</title>
</head>
<body>
<main>
    <h1>Login</h1>

    <% if (request.getAttribute("erreur") != null) { %>
    <p style="color:red;"><%= request.getAttribute("erreur") %></p>
    <% } %>

    <form method="POST" action="/login">
        <label for="login">Login</label>
        <input type="text" id="login" name="login" required>

        <label for="pass">Mot de passe :</label>
        <input type="password" id="pass" name="password" required>
        <input type="submit" value="Se connecter">
    </form>


</main>

</body>
</html>

