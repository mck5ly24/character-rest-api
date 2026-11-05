<#import "layout.ftlh" as layout>

<@layout.page title="About">
    <section class="about-page">
        <h2>About This Website</h2>

        <img class="about-image"
             src="/images/iron-man.webp"
             alt="Iron Man">

        <p>
            I created this character gallery to showcase some of my favorite
            characters from the Marvel universe. I chose Black Panther, Thor,
            Iron Man, Hulk, and Vision because each character has different
            abilities, personalities, and roles.
        </p>

        <p>
            The gallery lets you browse characters, search by name, and filter
            by universe. Each character has a details page, and you can add,
            edit, or delete characters.
        </p>

        <h2>How This App Works</h2>

        <p>
            The front end uses HTML, CSS, and FreeMarker templates.
            A Spring MVC controller handles page requests, gets data from
            the service layer, and passes it to a template to display.
        </p>

        <p>
            The service layer handles searching and changes to characters.
            It uses a Spring Data JPA repository to read and save data
            in the Neon PostgreSQL database.
        </p>

        <p>
            The REST controller provides JSON through /api/characters.
            The page controller uses the same service layer directly
            to build the website's HTML pages.
        </p>
    </section>
</@layout.page>