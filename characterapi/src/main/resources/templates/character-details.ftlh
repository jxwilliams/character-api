<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Hero Details</title>
  <link rel="stylesheet" href="/css/styles.css">
</head>
<body>

<!-- Same site navigation used across the MVC pages. -->
<nav class="nav">
  <a href="/characters">Home</a>
  <a href="/characters/about">About</a>
  <a href="/characters/create">Form</a>
</nav>

<!-- Header section for the details page. -->
<header class="header">
  <h1>Hero Details</h1>
  <p class="sub">Profile for one character from the gallery</p>
</header>

<main class="main">
  <!-- This page shows one Character object sent from the controller. -->
  <article class="card detail-card">
    <div class="content">
      <!-- Basic text fields from the Character entity. -->
      <p class="detail-label">Name</p>
      <h2 class="name">${character.name}</h2>

      <p class="detail-label">Species</p>
      <p class="desc">${character.species}</p>

      <p class="detail-label">Universe</p>
      <p class="desc">${character.universe}</p>

      <p class="detail-label">Description</p>
      <p class="desc">${character.description}</p>

      <p class="detail-actions">
        <!-- These links let the user update or delete the current character from the MVC flow. -->
        <a class="button-link" href="/characters/update/${character.characterId}">Update</a>
        <a class="button-link" href="/characters/delete/${character.characterId}">Delete</a>
        <a class="button-link" href="/characters">Back to Home</a>
      </p>
    </div>
  </article>
</main>

</body>
</html>
