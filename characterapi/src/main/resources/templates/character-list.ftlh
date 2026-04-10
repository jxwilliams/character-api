<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Superhuman Gallery</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>

    <!-- Main nav for the MVC pages. These stay on the /characters routes, not the REST API routes. -->
    <nav class="nav">
        <a href="/characters">Home</a>
        <a href="/characters/about">About</a>
        <a href="/characters/create">Form</a>
    </nav>

    <!-- Little intro section so the page does not just jump straight into cards. -->
    <header class="header">
        <h1>Superhuman Gallery</h1>
        <p class="sub">A simple gallery of famous heroes</p>
    </header>

    <main class="main">
        <div class="grid">
            <!-- Loop through every character the controller put in the model. -->
            <#list characters as character>
                <div class="card">
                    <div class="content">
                        <!-- Character name is the main thing people should notice first. -->
                        <h3 class="name">${character.name}</h3>
                        <div class="tags">
                            <!-- These checks help avoid showing empty tags if a field is blank. -->
                            <#if character.universe?has_content>
                                <span class="tag">${character.universe}</span>
                            </#if>
                            <#if character.species?has_content>
                                <span class="tag">${character.species}</span>
                            </#if>
                        </div>
                        <!-- Quick summary text for the card preview. -->
                        <p class="desc">${character.description}</p>
                        <p class="detail-actions">
                            <!-- These links are the basic MVC actions for each character. -->
                            <a class="button-link" href="/characters/${character.characterId}">View Details</a>
                            <a class="button-link" href="/characters/update/${character.characterId}">Update</a>
                            <a class="button-link" href="/characters/delete/${character.characterId}">Delete</a>
                        </p>
                    </div>
                </div>
            <!-- If the list is empty, FreeMarker falls into this branch instead. -->
            <#else>
                <p class="desc">No characters have been added yet.</p>
            </#list>
        </div>
    </main>

</body>
</html>
