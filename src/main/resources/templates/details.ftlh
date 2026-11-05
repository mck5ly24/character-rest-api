<#import "layout.ftlh" as layout>

<@layout.page title=character.name>
    <section class="details-page">
        <a href="/">Back to the gallery</a>

        <h2>${character.name}</h2>

        <#if character.imageUrl?has_content>
            <img class="details-image"
                 src="${character.imageUrl}"
                 alt="${character.name}"
                 width="300">
        <#else>
            <p class="image-placeholder">No image available</p>
        </#if>

        <p>
            <strong>ID:</strong>
            ${character.characterId?c}
        </p>

        <p>
            <strong>Universe:</strong>
            ${character.universe!'Not specified'}
        </p>

        <p>
            <strong>Role:</strong>
            ${character.role!'Not specified'}
        </p>

        <h3>Description</h3>
        <p>${character.description}</p>

        <a href="/characters/${character.characterId?c}/edit">
            Edit Character
        </a>

        <form action="/characters/${character.characterId?c}/delete"
              method="post"
              onsubmit="return confirm('Are you sure you want to delete this character?');">
            <button type="submit">Delete Character</button>
        </form>
    </section>
</@layout.page>