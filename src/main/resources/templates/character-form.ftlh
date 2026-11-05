<#import "layout.ftlh" as layout>

<@layout.page title=formTitle>
    <section class="form-page">
        <h2>${formTitle}</h2>

        <#if errors??>
            <div role="alert">
                <p>Please fix the following:</p>
                <ul>
                    <#list errors as error>
                        <li>${error.defaultMessage!'Invalid value'}</li>
                    </#list>
                </ul>
            </div>
        </#if>

        <form action="${formAction}" method="post">
            <div>
                <label for="name">Name</label>
                <input type="text"
                       id="name"
                       name="name"
                       value="${character.name!''}"
                       maxlength="255"
                       required>
            </div>

            <div>
                <label for="universe">Universe</label>
                <input type="text"
                       id="universe"
                       name="universe"
                       value="${character.universe!''}"
                       maxlength="255">
            </div>

            <div>
                <label for="role">Role</label>
                <input type="text"
                       id="role"
                       name="role"
                       value="${character.role!''}"
                       maxlength="255">
            </div>

            <div>
                <label for="imageUrl">Image URL or local image path</label>
                <input type="text"
                       id="imageUrl"
                       name="imageUrl"
                       value="${character.imageUrl!''}"
                       maxlength="2000"
                       placeholder="/images/black-panther.jpg">
            </div>

            <div>
                <label for="description">Description</label>
                <textarea id="description"
                          name="description"
                          rows="6"
                          maxlength="2000"
                          required>${character.description!''}</textarea>
            </div>

            <button type="submit">Save Character</button>
            <a href="/">Cancel</a>
        </form>
    </section>
</@layout.page>