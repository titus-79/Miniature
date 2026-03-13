<%@ page contentType="text/html;charset=UTF-8" language ="java" %>

<!doctype html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <title>Connexion</title>
    <link rel="stylesheet" href="style.css">

</head>
<body>
<nav>
    <a href="/" class="nav__logo">Miniature</a>
    <a href="/" class="nav__back">← Retour</a>
</nav>
<main>

    <div class="page">

        <div class="panel-image gradient-left">
            <img src="https://simplonline.co/_next/image?url=https%3A%2F%2Fsimplonline-v3-prod.s3.eu-west-3.amazonaws.com%2Fmedia%2Fimage%2Fwebp%2Fcomfyui-00455-66bf526b2172e266974676-69b1218a1b993958720385.webp&w=1280&q=75"
                 alt="Miniature illustration">
            <div class="panel-image__quote">
                <p>Rejoignez une communauté<br>qui partage l'essentiel.</p>
            </div>
        </div>
        <div class="panel-form">

            <p class="label">Bienvenu</p>
            <h1>Connecter vous <em> à votre espace</em></h1>

            <% if (request.getAttribute("erreur") != null) { %>
            <p style="color:red;"><%= request.getAttribute("erreur") %></p>
            <% } %>

            <form method="POST" action="/login">
                <div class="field">
                <label for="login">Login</label>
                <input type="text" id="login" name="login" required>
                </div>

                <div class="field">
                <label for="pass">Mot de passe :</label>
                <input type="password" id="pass" name="password" required>

                </div>
                <input type="submit" class="btn-submit" value="Se connecter">
            </form>
        </div>
    </div>
</main>


</body>
</html>

