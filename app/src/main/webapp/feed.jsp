<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fr.simplon.models.User" %>
<%@ page import="fr.simplon.models.Post" %>
<%@ page import="java.util.List" %>

<%
    User userSession = (User) session.getAttribute("user");
    String typeActif  = (String) request.getAttribute("typeActif");
    String feedTitre  = (String) request.getAttribute("feedTitre");
    String feedVide   = (String) request.getAttribute("feedVide");
    List<Post> posts  = (List<Post>) request.getAttribute("posts");
    if (posts == null) posts = java.util.Collections.emptyList();
    boolean connecte = userSession != null;
    String currentUrl = "/feed?type=" + (typeActif != null ? typeActif : "recommande");
%>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Miniature — Feed</title>
    <link rel="stylesheet" href="/style.css">
</head>

<body>

    <nav>
        <a href="/" class="nav__logo">Miniature</a>
        <ul class="nav__links">
            <li>
                <a href="/feed?type=recommande"
                   class="<%= "recommande".equals(typeActif) ? "active" : "" %>">
                    Recommandations
                </a>
            </li>
            <li>
                <a href="/feed?type=abonne"
                   class="<%= "abonne".equals(typeActif) ? "active" : "" %>">
                    Abonnements
                </a>
            </li>
        </ul>
    </nav>

    <div class="feed-container">

        <%-- En-tête du fil --%>
        <h2 class="feed__title"><%= feedTitre %></h2>

        <% if (connecte) { %>
        <div class="post-form">
            <p class="label">Nouvelle publication</p>
            <form action="/feed" method="post">
                <div class="field">
                    <label for="title">Titre</label>
                    <input type="text" name="title" id="title"
                           placeholder="Donnez un titre à votre post">
                </div>
                <div class="field">
                    <label for="content">Contenu</label>
                    <input type="text" name="content" id="content"
                           placeholder="Exprimez-vous…">
                </div>
                <button type="submit" class="btn-submit">Publier</button>
            </form>
        </div>
        <% } %>

        <% if (!connecte) { %>
        <div class="auth-banner">
            <p>Rejoignez Miniature pour publier et interagir</p>
            <div class="auth-banner__actions">
                <a href="/login.jsp"    class="btn btn--primary">Se connecter</a>
                <a href="/register.jsp" class="btn btn--ghost">S'inscrire</a>
            </div>
        </div>
        <% } %>

        <% if (posts.isEmpty() && "abonne".equals(typeActif)) { %>
        <div class="feed-empty">
            <p>Vous ne suivez encore personne.</p>
            <a href="/feed?type=recommande" class="btn btn--ghost">Découvrir des publications</a>
        </div>
        <% } %>

        <% for (Post post : posts) {
            boolean estAuteur = connecte && userSession.getId().equals(post.getAuthor().getId());
            boolean dejaSuivi = connecte && !estAuteur && userSession.isFollowing(post.getAuthor());
        %>
        <article class="post-card">

            <div class="post-card__header">
                <div class="post-card__avatar">
                    <%= post.getAuthor().getUserName().substring(0, 1).toUpperCase() %>
                </div>
                <div class="post-card__meta">
                    <span class="post-card__author"><%= post.getAuthor().getUserName() %></span>
                    <span class="post-card__date">
                        <%= post.getCreatedAt().getHour() %>h<%= String.format("%02d", post.getCreatedAt().getMinute()) %>
                    </span>
                </div>

                <% if (connecte && !estAuteur) { %>
                <form action="/follow" method="post" class="follow-form">
                    <input type="hidden" name="targetId" value="<%= post.getAuthor().getId() %>">
                    <input type="hidden" name="action"   value="<%= dejaSuivi ? "unfollow" : "follow" %>">
                    <input type="hidden" name="redirect" value="<%= currentUrl %>">
                    <button type="submit"
                            class="follow-btn <%= dejaSuivi ? "follow-btn--following" : "" %>">
                        <%= dejaSuivi ? "✓ Abonné" : "+ Suivre" %>
                    </button>
                </form>
                <% } %>
                <% if (estAuteur) { %>
                    <span class="follow-btn follow-btn--self"> Vous </span>
                <% } %>
            </div>

            <h3 class="post-card__title"><%= post.getTitle() %></h3>
            <p  class="post-card__content"><%= post.getContent() %></p>

            <%-- Footer : actions si connecté, invitation sinon --%>
            <div class="post-card__footer">
                <% if (connecte) { %>
                    <button class="post-card__action">♡ J'aime</button>
                    <button class="post-card__action">💬 Commenter</button>
                <% } else { %>
                    <a href="/login.jsp" class="post-card__action post-card__action--invite">
                        Connectez-vous pour interagir →
                    </a>
                <% } %>
            </div>

        </article>
        <% } %>

    </div>

</body>
</html>
