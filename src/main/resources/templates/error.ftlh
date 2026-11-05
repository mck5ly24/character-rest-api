<#import "layout.ftlh" as layout>

<@layout.page title="Character Not Found">
    <h2>Character Not Found</h2>

    <p>${message!'The requested character could not be found.'}</p>

    <a href="/">Back to the gallery</a>
</@layout.page>