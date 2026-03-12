<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Miniature — Inscription</title>
    <link rel="stylesheet" href="/style.css">
</head>
<body>

    <nav>
        <a href="/" class="nav__logo">Miniature</a>
        <a href="/" class="nav__back">← Retour</a>
    </nav>

    <div class="page">

        <div class="panel-image gradient-left">
            <img src="https://simplonline.co/_next/image?url=https%3A%2F%2Fsimplonline-v3-prod.s3.eu-west-3.amazonaws.com%2Fmedia%2Fimage%2Fwebp%2Fcomfyui-00455-66bf526b2172e266974676-69b1218a1b993958720385.webp&w=1280&q=75"
                 alt="Miniature illustration">
            <div class="panel-image__quote">
                <p>Rejoignez une communauté<br>qui partage l'essentiel.</p>
            </div>
        </div>

        <div class="panel-form">

            <p class="label">Nouveau compte</p>
            <h1>Créer <em>votre espace</em></h1>
            <p class="subtitle">Quelques secondes suffisent.</p>

            <% if ("true".equals(request.getParameter("error"))) { %>
                <div class="error-msg">Veuillez vérifier vos informations.</div>
            <% } %>

            <form action="/register" method="post">

                <div class="field">
                    <label for="login">Login</label>
                    <input type="text" name="login" id="login"
                           placeholder="Entre 4 et 8 caractères"
                           required minlength="4" maxlength="8">
                    <p class="field__hint">4 à 8 caractères</p>
                </div>

                <div class="field">
                    <label for="email">Adresse email</label>
                    <input type="email" name="email" id="email"
                           placeholder="vous@exemple.com"
                           pattern="[-a-zA-Z0-9._]+@[-a-zA-Z0-9._]+\.[a-zA-Z.]{2,15}"
                           required>
                </div>

                <div class="field">
                    <label for="pass">Mot de passe</label>
                    <input type="password" id="pass" name="password"
                           placeholder="8 caractères minimum"
                           minlength="8" required>
                    <p class="field__hint">8 caractères minimum</p>
                </div>

                <div class="field">
                    <label for="passConf">Confirmer le mot de passe</label>
                    <input type="password" id="passConf" name="passwordConf"
                           placeholder="Répétez votre mot de passe"
                           minlength="8" required>
                </div>

                <button type="submit" class="btn-submit">Créer mon compte</button>

            </form>

            <p class="form-footer">
                Déjà membre ? <a href="login.jsp">Se connecter</a>
            </p>

        </div>
    </div>

</body>
</html>
