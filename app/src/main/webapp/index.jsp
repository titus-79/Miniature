<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fr.simplon.models.User" %>
<%
    User userSession = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Miniature</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

    <nav>
        <a href="/" class="nav__logo">Miniature</a>
        <ul class="nav__links">
            <li><a href="feed">Explorer</a></li>
            <li><a href="#">À propos</a></li>
        </ul>
    </nav>

    <section class="page">

        <div class="hero__content">
            <p class="label">Bienvenue sur Miniature</p>
            <h1>L'art de<br>partager <em>l'instant</em></h1>
            <p class="tagline">
                Publiez, commentez, explorez.<br>
                Un réseau pensé pour l'essentiel.
            </p>
            <div class="actions">
                <% if (userSession != null) { %>
                    <a href="/feed" class="btn btn--primary">Explorer le fil</a>
                    <a href="/logout" class="btn btn--ghost">Déconnexion</a>
                <% } else { %>
                    <a href="register.jsp" class="btn btn--primary">Rejoindre</a>
                    <a href="login.jsp"    class="btn btn--ghost">Se connecter</a>
                <% } %>
            </div>
        </div>

        <div class="hero__image panel-image gradient-right">
            <img src="https://simplonline.co/_next/image?url=https%3A%2F%2Fsimplonline-v3-prod.s3.eu-west-3.amazonaws.com%2Fmedia%2Fimage%2Fwebp%2Fcomfyui-00455-66bf526b2172e266974676-69b1218a1b993958720385.webp&w=1280&q=75"
                 alt="Miniature illustration">
        </div>

    </section>

    <% if (userSession != null) { %>
    <div class="session-bar">
        Connecté en tant que&nbsp;<span><%= userSession.getUserName() %></span>
        &mdash;
        <%= userSession.getEmail() %>
        <a href="/logout">Déconnexion</a>
    </div>
    <% } %>

</body>
</html>