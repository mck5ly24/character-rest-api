<#macro page title>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title} - Character Gallery</title>
    <link rel="stylesheet" href="/style.css">
</head>

<body>
    <header>
        <h1>Marvel Character Gallery</h1>

        <nav>
            <a href="/">Home</a>
            <a href="/about">About</a>
            <a href="/characters/new">Add a Character</a>
        </nav>
    </header>

    <#if successMessage??>
        <p class="success-message" role="status">${successMessage}</p>
    </#if>

    <main>
        <#nested>
    </main>
</body>
</html>
</#macro>