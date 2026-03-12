<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fr.simplon.models.User" %>
<%@ page import="fr.simplon.models.Post" %>
<%@ page import="java.util.List" %>
<% User userSession=(User) session.getAttribute("user"); String typeActif=(String)
                request.getAttribute("typeActif"); String feedTitre=(String) request.getAttribute("feedTitre"); String
                feedVide=(String) request.getAttribute("feedVide"); %>
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
                            <%-- Le lien actif reçoit la classe "active" --%>
                                <li>
                                    <a href="/feed?type=recommande" class="<%= "recommande".equals(typeActif)
                                        ? "active" : "" %>">
                                        Recommandations
                                    </a>
                                </li>
                                <li>
                                    <a href="/feed?type=abonne" class="<%= "abonne".equals(typeActif) ? "active" : ""
                                        %>">
                                        Abonnements
                                    </a>
                                </li>
                        </ul>
                    </nav>

                    <div class="feed-container">
                        <h2>
                            <%= feedTitre %>
                        </h2>
                        <p class="subtitle">
                            <%= feedVide %>
                        </p>
                        <article>
                            <form action="" method="post">
                                <label for="title">Titre</label>
                                <input type="text" name="title" id="title"><br>
                                <label for="content">texte</label>
                                <input type="text" name="content" id="content"><br>
                                <button type="submit">Poster</button>
                            </form>

                        </article>

                        <% List<Post> posts = (List<Post>) request.getAttribute("posts");
                             if (posts == null) posts = java.util.Collections.emptyList(); %>

                                <% for(Post post : posts) {%>

                                    <article>
                                        <h3>
                                            <%=post.getTitle() %>
                                        </h3>
                                        <p>
                                            <%=post.getContent() %>
                                        </p>
                                        <p>
                                            <%=post.getCreatedAt() %>
                                        </p>
                                    </article>
                                    <%}%>

                    </div>

                </body>

                </html>