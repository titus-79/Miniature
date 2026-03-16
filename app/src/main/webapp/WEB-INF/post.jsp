<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fr.simplon.models.User" %>
<%@ page import="fr.simplon.models.Post" %>
<%@ page import="fr.simplon.models.Comment" %>
<%@ page import="java.util.List" %>

<%
    User    userSession  = (User)    request.getAttribute("userSession");
    Post    post         = (Post)    request.getAttribute("post");
    List<Comment> topComments = (List<Comment>) request.getAttribute("topComments");
    if (topComments == null) topComments = java.util.Collections.emptyList();
    boolean connecte = userSession != null;
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Miniature — <%= post.getTitle() %></title>
    <link rel="stylesheet" href="/style.css">
</head>

<body>
    <nav>
        <a href="/" class="nav__logo">Miniature</a>
        <ul class="nav__links">
            <li><a href="/feed?type=recommande">Recommandations</a></li>
            <li><a href="/feed?type=abonne">Abonnements</a></li>
        </ul>
    </nav>

    <div class="feed-container">

        <a href="javascript:history.back()" class="back-link">← Retour</a>

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
            </div>
            <h3 class="post-card__title"><%= post.getTitle() %></h3>
            <p  class="post-card__content"><%= post.getContent() %></p>
            <div class="post-card__footer">
                <span class="post-card__action" style="cursor:default;">
                    💬 <%= post.getCommentCount() %> commentaire<%= post.getCommentCount() > 1 ? "s" : "" %>
                </span>
            </div>
        </article>

        <% if (connecte) { %>
        <div class="post-form">
            <p class="label">Ajouter un commentaire</p>
            <form action="/post" method="post">
                <input type="hidden" name="id" value="<%= post.getId() %>">
                <div class="field">
                    <textarea name="content" rows="3"
                              placeholder="Votre commentaire…"
                              style="width:100%;background:transparent;border:none;border-bottom:1px solid var(--border);padding:.35rem 0;font-family:'Jost',sans-serif;font-size:.95rem;font-weight:300;color:var(--ink);outline:none;resize:none;transition:border-color .2s;"
                              onfocus="this.style.borderBottomColor='var(--ink)'"
                              onblur="this.style.borderBottomColor='var(--border)'"
                    ></textarea>
                </div>
                <button type="submit" class="btn-submit">Commenter</button>

            </form>
        </div>
        <% } else { %>
        <div class="auth-banner">
            <p>Connectez-vous pour laisser un commentaire</p>
            <div class="auth-banner__actions">
                <a href="/login.jsp" class="btn btn--primary">Se connecter</a>
                <a href="/register.jsp" class="btn btn--ghost">S'inscrire</a>
            </div>
        </div>
        <% } %>

        <div class="comments-section">
            <p class="comments-section__title">
                Commentaires (<%= post.getCommentCount() %>)
            </p>

            <% if (topComments.isEmpty()) { %>
            <p class="no-comments">Aucun commentaire pour l'instant. Soyez le premier !</p>
            <% } %>

            <% for (Comment comment : topComments) { %>

            <div class="comment-card" id="comment-<%= comment.getId() %>">
                <div class="comment-card__header">
                    <div class="comment-card__avatar">
                        <%= comment.getUser().getUserName().substring(0, 1).toUpperCase() %>
                    </div>
                    <span class="comment-card__author"><%= comment.getUser().getUserName() %></span>
                    <span class="comment-card__date">
                        <%= comment.getCreateAt().getHour() %>h<%= String.format("%02d", comment.getCreateAt().getMinute()) %>
                    </span>
                </div>
                <p class="comment-card__content"><%= comment.getComment() %></p>

                <% if (connecte) { %>
                <%-- bouton like --%>
                <form method="post" action="/like">
                    <input type="hidden" name="commentId" value="<%= comment.getId() %>">
                    <button type="submit" class="btn-submit">👍<%= post.getLikesCount()%></button>

                </form>

                <%-- Bouton Répondre --%>
                <button class="reply-toggle"
                        onclick="toggleReply('reply-form-<%= comment.getId() %>')">
                    ↩ Répondre
                </button>

                <%-- Formulaire de réponse (caché) --%>
                <div class="reply-form-wrapper" id="reply-form-<%= comment.getId() %>">
                    <form action="/post" method="post">
                        <input type="hidden" name="id"              value="<%= post.getId() %>">
                        <input type="hidden" name="parentCommentId" value="<%= comment.getId() %>">
                        <textarea name="content" rows="2"
                                  placeholder="Votre réponse…"></textarea>
                        <button type="submit" class="btn-submit">Répondre</button>
                    </form>
                </div>
                <% } %>
            </div>

            <%-- Réponses imbriquées --%>
            <% for (Comment reply : comment.getRepliesSortedByDate()) { %>
            <div class="comment-card comment-card--reply" id="comment-<%= reply.getId() %>">
                <div class="comment-card__header">
                    <div class="comment-card__avatar">
                        <%= reply.getUser().getUserName().substring(0, 1).toUpperCase() %>
                    </div>
                    <span class="comment-card__author"><%= reply.getUser().getUserName() %></span>
                    <span style="font-size:0.6rem;color:var(--muted);letter-spacing:.06em;">
                        ↩ <em><%= comment.getUser().getUserName() %></em>
                    </span>
                    <span class="comment-card__date">
                        <%= reply.getCreateAt().getHour() %>h<%= String.format("%02d", reply.getCreateAt().getMinute()) %>
                    </span>
                </div>
                <p class="comment-card__content"><%= reply.getComment() %></p>
            </div>
            <% } %>

            <% } %>
        </div>

    </div>

    <script>
        function toggleReply(id) {
            const form = document.getElementById(id);
            form.classList.toggle('open');
            if (form.classList.contains('open')) {
                form.querySelector('textarea').focus();
            }
        }
    </script>
</body>
</html>
