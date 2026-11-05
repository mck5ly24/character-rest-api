<#import "layout.ftlh" as layout>

    <@layout.page title="Home">
        <h2>Character Gallery</h2>

        <form action="/" method="get" class="search-form">
            <label for="name">Character name</label>
            <input type="text" id="name" name="name" value="${name!''}" placeholder="Search by name">

            <label for="universe">Universe</label>
            <input type="text" id="universe" name="universe" value="${universe!''}" placeholder="For example, Marvel">

            <button type="submit">Search</button>
            <a href="/">Clear</a>
        </form>

        <section class="character-grid">
            <#list characters as character>
                <article class="character-card">
                    <a href="/characters/${character.characterId?c}">
                        <#if (character.imageUrl!'')?trim?has_content>
                            <img src="${character.imageUrl}" alt="${character.name}">
                            <#else>
                                <p class="image-placeholder">No image available</p>
                        </#if>

                        <h3>${character.name}</h3>
                    </a>

                    <p><strong>Universe:</strong> ${character.universe!'Not specified'}</p>
                    <p><strong>Role:</strong> ${character.role!'Not specified'}</p>
                </article>
                <#else>
                    <p>No characters found. Try another search.</p>
            </#list>
        </section>
    </@layout.page>