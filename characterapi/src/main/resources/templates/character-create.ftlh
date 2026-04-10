<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Hero Form</title>
  <link rel="stylesheet" href="/css/styles.css">
</head>
<body>

<!-- Nav stays consistent so moving around the MVC pages feels simple. -->
<nav class="nav">
  <a href="/characters">Home</a>
  <a href="/characters/about">About</a>
  <a href="/characters/create">Form</a>
</nav>

<!-- The title/subtitle are dynamic now, so this same file works for create and update. -->
<header class="header">
  <h1>${formTitle!"Add a Hero"}</h1>
  <p class="sub">${formSubtitle!"Create a new character and save it to the gallery."}</p>
</header>

<main class="main">
  <!-- formAction changes depending on whether we are creating a new character
       or editing one that already exists. -->
  <form class="character-form" action="${formAction!'/characters/create'}" method="post">
    <!-- These input names match the Character entity fields so Spring can bind them automatically. -->
    <label for="name">Name:</label><br>
    <input id="name" name="name" type="text" value="${character.name!}" required><br><br>

    <label for="species">Species:</label><br>
    <input id="species" name="species" type="text" value="${character.species!}"><br><br>

    <label for="universe">Universe:</label><br>
    <input id="universe" name="universe" type="text" value="${character.universe!}"><br><br>

    <label for="description">Description:</label><br>
    <textarea id="description" name="description" rows="4" required>${character.description!}</textarea><br><br>

    <!-- submitLabel also changes so the button text matches the page mode. -->
    <input type="submit" value="${submitLabel!'Submit'}">
  </form>
</main>

</body>
</html>
